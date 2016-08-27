package com.ijianbao.framework.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ijianbao.framework.bean.Code;
import com.ijianbao.framework.bean.Patient;
import com.ijianbao.framework.bean.SysUser;
import com.ijianbao.framework.service.CodeService;
import com.ijianbao.framework.service.PatientService;
import com.ijianbao.framework.service.SysUserService;
import com.ruanwei.interfacej.SmsClientSend;
import com.ruanwei.tool.Analysis;

@Controller
@RequestMapping("/patient")
public class PatientController {

	private Logger logger = Logger.getLogger(PatientController.class);

	@Autowired
	private PatientService patientService;

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	CodeService codeService;
	private final int STATUS_BOOKING = 1;
	private final int STATUS_SUCCESS = 2;
	private final int STATUS_FINISH = 3;
	private final int STATUS_CANCEL = 0;

	
	
	
	@RequestMapping(value = "/list")
	public ModelAndView list() {
		List<Patient> list = patientService.list();

				return new ModelAndView("patient/list", "list", list);
	}
	@RequestMapping(value = "/listwitherror")
	public ModelAndView listWithErroe(@RequestParam("res") int res) {
		System.out.println(res);
		List<Patient> list = patientService.list();
		ModelAndView model = new ModelAndView("patient/list");
		if(res==-1){
		model.addObject("error","添加失败，卡片已经被使用");}
		model.addObject("list",list);
		return model;
	}
	@RequestMapping(value = "/form")
	public String form(@ModelAttribute Patient patient, @RequestParam("cid") int cid,@RequestParam("cardtype")int type) {
		// todo 权限验证
		patient.setCid(cid);
//		patient.setType(type);
        patient.setProductType(type);
		return "patient/form";
	}
	
	@RequestMapping(value = "/zhenwuyou_form")
	public String zhenwuyouForm(@ModelAttribute Patient patient, @RequestParam("cid") int cid,@RequestParam("cardtype")int type) {
		// todo 权限验证
		patient.setCid(cid);
        patient.setProductType(type);
        patient.setSex(1);
		return "patient/zhenwuyou_form";
	}

	@RequestMapping("{id}/update")
	public ModelAndView view(@PathVariable("id") Patient patient) {
		return new ModelAndView("patient/form", "patient", patient);
	}

	@RequestMapping(value = "/input", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute Patient patient) {
		// 保存病患
		java.util.Date currentDate = new java.util.Date();
		System.out.println(patient);
		ModelAndView model = new ModelAndView("redirect:/patient/listwitherror");
		patient.setStatus(1);
		patient.setCreateTime(currentDate);
		patient.setUpdateTime(currentDate);
		if(patient.getIsRegister()==2){
			patient.setIsRegister(0);
		}
		
		int resule= patientService.savePatient(patient);
		if(resule==-1){
			model.addObject("res",resule);
			return model;
		}
		return new ModelAndView("redirect:/patient/list");
	}

	@RequestMapping("/detail")
	public ModelAndView createDetail(@ModelAttribute Patient patient, @RequestParam("pid") int pid) {
		Patient newPatient = patientService.getPatient(pid);
		if (newPatient == null) {
		    return new ModelAndView("patient/detail", "error", "用户不存在!");
		}
		logger.debug("detailCheck: " + newPatient);
		    return new ModelAndView("patient/detail", "patient", newPatient);
	}

	@RequestMapping("/cancelorder")
	public ModelAndView cancelOrder(@ModelAttribute Patient patient, @RequestParam("pid") int pid) {
		Patient newPatient = patientService.getPatient(pid);
		if (newPatient == null) {
			newPatient = new Patient();
		}
		logger.debug("cancelCheck: " + newPatient.getStatus());
		newPatient.setStatus(STATUS_CANCEL);
		patientService.updateStatus(newPatient);
		logger.debug("cancelCheck: " + newPatient.getStatus());
		return new ModelAndView("redirect:/patient/list");
	}

	// 更新病患信息
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(@Valid Patient patient) {
		// 检查状态，如果状态为1，则进行修改
		Patient tempPatient = patientService.getPatient(patient.getPid());
		if (tempPatient.getStatus() == STATUS_BOOKING) {
			patient.setStatus(STATUS_SUCCESS);
			patient.setUpdateTime(new Date());
			patient.setUpdateId(1);
			if(patient.getIsRegister()==0){
				patient.setRegisterType(0);
			}
			patientService.updatePatient(patient);
			System.out.println(patient);
		} else {
			System.out.println("Status is not ccrrect!");
		}
		return new ModelAndView("redirect:/patient/list");

	}

	@RequestMapping("/service")
	public ModelAndView createService(@ModelAttribute Patient patient, @RequestParam("pid") int pid) {
		Patient newPatient = patientService.getPatient(pid);
		List<SysUser> list = sysUserService.getServices();
		ModelAndView modelAndView = new ModelAndView("patient/service");
		modelAndView.addObject("patient", newPatient);
		modelAndView.addObject("list", list);
		return modelAndView;
	}

	// 更新分配人员信息
	@RequestMapping(value = "/updateservice", method = RequestMethod.POST)
	public ModelAndView updateService(@Valid Patient patient) {
		// 检查状态，如果状态为2，则进行分配人员
		Code code =new Code();
		  Patient tempPatient = patientService.getPatient(patient.getPid());
		if (tempPatient.getStatus() == STATUS_SUCCESS) {
			patient.setUpdateTime(new Date());
			patient.setUpdateId(1);
			patient.setStatus(STATUS_FINISH);
			patientService.updatePatient(patient);
			code.setType(2);
			code.setMobile(patient.getMobile());
			sendSms(code,patient);
			logger.debug("updateServerCheck: " + tempPatient);
           
		} else {
			logger.error("Status is nor correct!");
		}
		return new ModelAndView("redirect:/patient/list");

	}

	@RequestMapping("/checksuccess")
	public ModelAndView transferToFrom(@Valid Patient patient) {
		return new ModelAndView("patient/form", "patient", patient);

	}
	
	private int sendSms(Code code, Patient patient) {
		StringBuffer str = new StringBuffer();
		str.append("尊敬的")
		.append(patient.getName())
		.append("您好，您已经成功预约")
		.append(getService(patient.getProductType()))
		.append("服务。请于")
		.append(patient.getDate()+",")
		.append(patient.getTime())
		.append("准时到达")
		.append(getHidName(patient.getHid()))
		.append("，请携带好以往病例及检查报告")
		.append("，专业陪诊人员")
		.append(getSuidName(patient.getSuid()))
		.append("将在门诊大厅导诊台旁等候。");
		String smsRetCode = SmsClientSend.sendSms("http://115.28.209.21:8888/sms.aspx", "733", "hongwei106", "1qaz2wsx",
				code.getMobile(), str.toString());
		code = Analysis.xmlElements(smsRetCode, code);
		code.setCode(str.toString());
	    codeService.saveCode(code);
		return code.getStatus();
	}
	
	private String getHidName(int i){
		switch(i){
		 case 1:
			 return "儿童医院（北京西路门诊部）";
		 case 2:
			 return "儿科医院（枫林路门诊部）";
		 case 3:
			 return "儿童医学中心";
		 case 4:
			 return "新华医院儿科";
		 case 5:
			 return "东方医院南院儿科";
		 case 6:
				return  "上海交通大学附属第一人民医院（南院）";
		 case 7:
			    return  "上海交通大学附属第九人民医院（总院）";
		 case 8:
				return  "上海交通大学附属仁济医院（南院)";
		 case 9:
				return  "上海同济大学附属东方医院（南院）";
		 case 10:
				return  "上海同济大学附属同济医院";
		 case 11:
				return  "上海同济大学附属第十人民医院";
		 case 12:
				return  "上海中医药大学附属曙光医院（东院）";
		 case 13:
				return  "上海中医药大学附属龙华医院";
		 case 14:
				return  "上海同济大学附属岳阳医院";
		 case 15:
				return  "上海复旦大学附属妇产科医院（杨浦分院）";
		 case 16:
				return  "上海同济大学附属第一妇婴保健院（东院)";
		 case 17:
				return  "上海交通大学附属儿童医院（北京西路）";
		 case 18:
				return  "上海复旦大学附属华山医院";
		 case 19:
				return  "上海交通大学附属瑞金医院（卢湾分院）";
		 case 20:
				return  "上海复旦大学附属中山医院（逸仙医院）";
		 case 21:
				return  "上海复旦大学附属儿科医院（枫林院区）";
		 case 22:
				return  "上海交通大学附属上海儿童医学中心";
		 case 23:
				return  "上海交通大学附属新华医院";
		 case 24:
				return  "上海同济大学附属东方医院（北院）";
		 case 25:
				return  "上海市第五人民医院";	
		}
		return "医院";
	}
	private String getService(int i){
		switch(i){
		 case 1:
			 return "诊无忧";
		 case 2:
			 return "速诊通";
		 case 3:
			 return "安诊通";
		 
		}
		return "未知服务";
	}
	private String getSuidName(int id){
		SysUser user = sysUserService.getSysUser(id);
		
		return user.getName();
	}
	

}
