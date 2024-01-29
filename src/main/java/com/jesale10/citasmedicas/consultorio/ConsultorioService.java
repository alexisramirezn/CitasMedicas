package com.jesale10.citasmedicas.consultorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultorioService {
    private final ConsultorioRepository consultorioRepository;

    @Autowired
    ConsultorioService(ConsultorioRepository consultorioRepository){
        this.consultorioRepository = consultorioRepository;
    }

    public List<Consultorio> getConsultorios(){
        return consultorioRepository.findAll();
    }
}
