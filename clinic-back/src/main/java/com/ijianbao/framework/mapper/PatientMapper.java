package com.ijianbao.framework.mapper;

import java.util.List;

import com.ijianbao.framework.bean.Patient;
import com.ijianbao.framework.bean.SysUser;

public interface PatientMapper {
	
	public List<Patient> list();
	
	public Patient getPatient(int pid);
	
	public int savePatient(Patient patient);
	
	public int updatePatient(Patient patient);
	
	public int updateService(Patient patient);
	
	public List<SysUser> getServices();
	
	public int updateStatus(Patient patient);
	
	public List<Patient> getPatientbyservice (int suid);
	
	public Patient getPatientByCid(int cid);
	
	public int checkPatient(Patient patient);
}
