package com.ijianbao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ijianbao.mapper.PatientMapper;
import com.ijianbao.model.Patient;

@Service
@Transactional
public class PatientService {
	
	@Autowired
    private PatientMapper patientMapper;
	
	public List<Patient> getPatient(int uid) {
		return patientMapper.getPatient(uid);
	}
	
	public int savePatient(Patient patient) {
		return patientMapper.savePatient(patient);
	}
	
	public int updatePatient(Patient patient) {
		return patientMapper.updatePatient(patient);
	}
}
