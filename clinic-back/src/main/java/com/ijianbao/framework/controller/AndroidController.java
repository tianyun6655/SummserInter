package com.ijianbao.framework.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ijianbao.framework.bean.Patient;
import com.ijianbao.framework.bean.SysUser;
import com.ijianbao.framework.service.PatientService;
import com.ijianbao.framework.service.SysUserService;
import com.ruanwei.tool.SHA1Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("andapi")
public class AndroidController {

	@Autowired
	PatientService patientService;
	@Autowired
	SysUserService sysUserService;

	@RequestMapping(value = "/getpatient", method = RequestMethod.POST)
	public void getPatient(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		String mobile = request.getParameter("mobile");
		System.out.println("getpatient: mobile is: "+mobile);
		if (mobile == null) {
			json.put("code", "0");
		} else {
			SysUser user = sysUserService.getUserByMobile2(mobile);
			if (user == null) {
				json.put("code", "0");
			} else {
				int id = user.getId();
				List<Patient> list = patientService.getPatientbyservice(id);
					json.put("code", "1");
					JSONArray arrayjson = new JSONArray();
					for (Patient patient : list) {
						JSONObject signlejson = new JSONObject();
						signlejson.put("pid", patient.getPid());
						signlejson.put("name", patient.getName());
						signlejson.put("mobile", patient.getMobile());
						signlejson.put("address", patient.getAddress());
						signlejson.put("cards", patient.getCards());
						signlejson.put("hid", patient.getHid());
						signlejson.put("office", patient.getOffice());
						signlejson.put("date", patient.getDate());
						signlejson.put("type", patient.getRegisterType());
						signlejson.put("status",patient.getStatus());
						signlejson.put("sex", patient.getSex());
						signlejson.put("remark", patient.getRemark());
						signlejson.put("time", patient.getTime());
						signlejson.put("symptom",patient.getsymptom());
						arrayjson.add(signlejson);
					}

					json.put("data", arrayjson);
				
			}
		}
		try {
			response.getWriter().write(json.toString());
			response.getWriter().close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/updatestatus", method = RequestMethod.POST)
	public void updateStatuts(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getParameter("pid")+ request.getParameter("status"));
		JSONObject jsonObject = new JSONObject();
		int status = Integer.parseInt(request.getParameter("status"));
		String symptom = request.getParameter("symptom");
		System.out.println("status: " +status);
		String remark = request.getParameter("remark");
		if (request.getParameter("pid") == null || request.getParameter("status") == null||(status!=6&&status!=7)||remark==null||symptom==null) {
			jsonObject.put("code", "0");

		} else {
			int pid = Integer.parseInt(request.getParameter("pid"));
			Patient patient = patientService.getPatient(pid);
			if (patient != null&&patient.getStatus()!=0) {
				patient.setStatus(status);
				patient.setRemark(remark);
				patient.setsymptom(symptom);
				System.out.println(patient);
				patientService.updatePatient(patient);
				jsonObject.put("code", "1");

			} else {
				jsonObject.put("code", "0");

			}

		}

		try {
			response.getWriter().write(jsonObject.toString());
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	@RequestMapping(value = "/updateremark", method = RequestMethod.POST)
	public void updateRemark(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getParameter("pid")+ request.getParameter("remark"));
		JSONObject jsonObject = new JSONObject();
		String remark = request.getParameter("remark");
		 
		if (request.getParameter("pid") == null || request.getParameter("remark") == null) {
			jsonObject.put("code", "0");

		} else {
			int pid = Integer.parseInt(request.getParameter("pid"));
			Patient patient = patientService.getPatient(pid);
			if (patient != null) {
				patient.setRemark(remark);
				patientService.updatePatient(patient);
				jsonObject.put("code", "1");

			} else {
				jsonObject.put("code", "0");

			}

		}

		try {
			response.getWriter().write(jsonObject.toString());
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@RequestMapping(value = "/getpatientget", method = RequestMethod.GET)
	public void getPatientGet(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		String mobile = request.getParameter("mobile");
		System.out.println("getpatient: mobile is: "+mobile);
		if (mobile == null) {
			json.put("code", "0");
		} else {
			SysUser user = sysUserService.getUserByMobile2(mobile);
			if (user == null) {
				json.put("code", "0");
			} else {
				int id = user.getId();
				List<Patient> list = patientService.getPatientbyservice(id);
					json.put("code", "1");
					JSONArray arrayjson = new JSONArray();
					for (Patient patient : list) {
						JSONObject signlejson = new JSONObject();
						signlejson.put("pid", patient.getPid());
						signlejson.put("name", patient.getName());
						signlejson.put("mobile", patient.getMobile());
						signlejson.put("address", patient.getAddress());
						signlejson.put("cards", patient.getCards());
						signlejson.put("hid", patient.getHid());
						signlejson.put("office", patient.getOffice());
						signlejson.put("date", patient.getDate());
						signlejson.put("type", patient.getRegisterType());
						signlejson.put("status",patient.getStatus());
						signlejson.put("sex", patient.getSex());
						signlejson.put("remark", patient.getRemark());

						arrayjson.add(signlejson);
					}
					json.put("data", arrayjson);
			}
		}
		try {
			response.getWriter().write(json.toString());
			response.getWriter().close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value="/andlogin", method = RequestMethod.POST)
    public void login(HttpServletRequest request,HttpServletResponse response){
		JSONObject json = new JSONObject();
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		System.out.println(mobile+"");
		System.out.println(password+"");
		SysUser sysuser = sysUserService.getUserByMobile2(mobile);
		if(sysuser==null){
			json.put("code", "-1");
		}else{
			String sha = SHA1Util.hex_sha1(sysuser.getPassword());

			if(!password.equals(sha)){
				json.put("code", "0");
			}else{
				int id = sysuser.getId();
				List<Patient> list = patientService.getPatientbyservice(id);
					json.put("code", "1");
					JSONArray arrayjson = new JSONArray();
					for (Patient patient : list) {
						JSONObject signlejson = new JSONObject();
						signlejson.put("pid", patient.getPid());
						signlejson.put("name", patient.getName());
						signlejson.put("mobile", patient.getMobile());
						signlejson.put("address", patient.getAddress());
						signlejson.put("cards", patient.getCards());
						signlejson.put("hid", patient.getHid());
						signlejson.put("office", patient.getOffice());
						signlejson.put("date", patient.getDate());
						signlejson.put("type", patient.getRegisterType());
						signlejson.put("status",patient.getStatus());
						signlejson.put("sex", patient.getSex());
						signlejson.put("time", patient.getTime());
						signlejson.put("remark", patient.getRemark());
						signlejson.put("symptom",patient.getsymptom());

						arrayjson.add(signlejson);
					}
					json.put("data", arrayjson);
			}
		}

		try {
			response.getWriter().write(json.toString());
			response.getWriter().close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
	
	@RequestMapping(value="/andloginget", method = RequestMethod.GET)
    public void loginGet(HttpServletRequest request,HttpServletResponse response){
		JSONObject json = new JSONObject();
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		System.out.println(mobile+"");
		System.out.println(password+"");
		SysUser sysuser = sysUserService.getUserByMobile2(mobile);
		System.out.println(sysuser.toString());
		if(sysuser==null){
			json.put("code", "-1");
		}else{
			String sha = SHA1Util.str_sha1(sysuser.getPassword());
			System.out.println(sha);
			if(!password.equals(sha)){
				json.put("code", "0");
			}else{
				int id = sysuser.getId();
				List<Patient> list = patientService.getPatientbyservice(id);
					json.put("code", "1");
					JSONArray arrayjson = new JSONArray();
					for (Patient patient : list) {
						JSONObject signlejson = new JSONObject();
						signlejson.put("pid", patient.getPid());
						signlejson.put("name", patient.getName());
						signlejson.put("mobile", patient.getMobile());
						signlejson.put("address", patient.getAddress());
						signlejson.put("cards", patient.getCards());
						signlejson.put("hid", patient.getHid());
						signlejson.put("office", patient.getOffice());
						signlejson.put("date", patient.getDate());
						signlejson.put("type", patient.getRegisterType());
						signlejson.put("status",patient.getStatus());
						signlejson.put("sex", patient.getSex());
						signlejson.put("time", patient.getTime());
						arrayjson.add(signlejson);
					}
					json.put("data", arrayjson);
			}
		}
		System.out.println(json.toString());
		try {
			response.getWriter().write(json.toString());
			response.getWriter().close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
