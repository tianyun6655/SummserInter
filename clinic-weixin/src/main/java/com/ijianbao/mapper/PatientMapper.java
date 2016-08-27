package com.ijianbao.mapper;

import java.util.List;

import com.ijianbao.model.Patient;

public interface PatientMapper {
	
	public List<Patient> getPatient(int uid);
	
	public int savePatient(Patient patient);
	
	public int updatePatient(Patient patient);
	
	
}
