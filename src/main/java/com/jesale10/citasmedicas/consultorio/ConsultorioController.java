package com.jesale10.citasmedicas.consultorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/consultorio")
public class ConsultorioController {
    private final ConsultorioService consultorioService;

    @Autowired
    ConsultorioController(ConsultorioService consultorioService){
        this.consultorioService = consultorioService;
    }

    @GetMapping
    public List<Consultorio> getConsultorios(){
        return consultorioService.getConsultorios();
    }
}
