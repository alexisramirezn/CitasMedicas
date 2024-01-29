package com.jesale10.citasmedicas.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository){
        this.doctorRepository  = doctorRepository;
    }

    public List<Doctor> getDoctores(){
        return doctorRepository.findAll();
    }
}
