package com.ijianbao.framework.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ijianbao.framework.bean.Card;
import com.ijianbao.framework.bean.Patient;
import com.ijianbao.framework.bean.User;
import com.ijianbao.framework.mapper.CardMapper;
import com.ijianbao.framework.mapper.PatientMapper;
import com.ijianbao.framework.mapper.UserMapper;

@Service
@Transactional
public class PatientService {
	private final int STATUS_CARD_USING =4;
	private final int STATUS_CARD_REGULAR =1;
	private final int STATUS_CARD_CHECKING=3;

	@Autowired
    private PatientMapper patientMapper;
	
	@Autowired
    private UserMapper userMapper;
	
	@Autowired
    private CardMapper cardMapper;
	
	
	public List<Patient> list() {
		return patientMapper.list();
	}
	
	public List<Patient> getPatientbyservice(int suid){
		return patientMapper.getPatientbyservice(suid);
		
	}
	
	public Patient getPatient(int pid) {
		return patientMapper.getPatient(pid);
	}
	
	public int savePatient(Patient patient) {
		Card cardTemp = cardMapper.getCardByCid(patient.getCid());
		int res;
//		Patient tempPatient = checkPatient(patient);
//		if(cardTemp.getStatus()!=1){
//			res=-1;
//		}else{
//			if(tempPatient==null){
//				
//			res = patientMapper.savePatient(patient);
//			}else{
//               
//            
//               res=-1;
//			}
//
//		}
		int count = checkPatient(patient);
		if(cardTemp.getStatus()!=1){
			res=-1;
		}else{
			if(count==0){
				
			res = patientMapper.savePatient(patient);
			}else{
               
            
               res=-1;
			}

		}
		User user = userMapper.getUserByMobile(patient.getMobile());
		if(user==null){
			user = new User();
			user.setMobile(patient.getMobile());
			user.setName(patient.getName());
			user.setTime(new Date());
			userMapper.saveUser(user);
		}
		
		if(res!=-1){
			changeCardStatus(patient.getCid(),STATUS_CARD_CHECKING, user.getMobile());
			//bindUser(user.getMobile(), patient.getCid());
		}
		
		return res;
	}
	
	public int updatePatient(Patient patient) {
		changeCardStatus(patient.getCid(), STATUS_CARD_USING,"");
        System.out.println(patient.getCid());
		return patientMapper.updatePatient(patient);
	}
	
	public int updateService(Patient patient){
		int res = patientMapper.updateService(patient);
		return res;
	}
	

	
	public int updateStatus(Patient patient){
		int res = patientMapper.updateStatus(patient);
		
		if(patient.getStatus() == 0){
			changeCardStatus(patient.getCid(),STATUS_CARD_REGULAR,"");
		}
		return res;
		
	}
		
	
    //对卡片的状态进行改变
    private void changeCardStatus(int cid,int cardStatus,String mobile){
    	
       Card tempCard = cardMapper.getCardByCid(cid);
 	   User tempuser = userMapper.getUserByMobile(mobile);
       System.out.println("the mobile is: "+mobile);
    	if(tempCard !=null){
    		if(tempuser!=null){
    			System.out.println("tempuser is null");
    			tempCard.setUid(tempuser.getUid());
    		}
    		tempCard.setStatus(cardStatus);
    		cardMapper.updateCardbyCid(tempCard);
    	}
    	
    }
    
    
    private Patient getPatientByCid(int cid){
    	return patientMapper.getPatientByCid(cid);
    }
    
    
   private void bindUser(String mobile,int cid){
	   User tempuser = userMapper.getUserByMobile(mobile);
	   Card tempcard = cardMapper.getCardByCid(cid);
	   tempcard.setUid(tempuser.getUid());
	   cardMapper.bindUser(tempcard);
	   
   }
   
   private int checkPatient(Patient patient){
	   return patientMapper.checkPatient(patient);
   }
}
