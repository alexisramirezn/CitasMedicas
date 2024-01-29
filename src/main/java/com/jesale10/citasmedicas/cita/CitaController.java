package com.jesale10.citasmedicas.cita;

import com.jesale10.citasmedicas.consultorio.Consultorio;
import com.jesale10.citasmedicas.doctor.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/citas")
public class CitaController {
    private final CitaService citaService;

    @Autowired
    CitaController(CitaService citaService){
        this.citaService =  citaService;
    }

    @GetMapping
    public List<Cita> getCitas(){
        return citaService.getCitas();
    }

    @PostMapping
    public void addCita(@RequestBody Cita cita){
        citaService.addCita(cita);
    }

    @GetMapping("/fecha-consultorio-doctor")
    public List<Cita> getCitasPorFechaConsultorioYDoctor(@RequestBody Date fecha,
                                                         @RequestBody Consultorio consultorio,
                                                         @RequestBody Doctor doctor){
        return citaService.getCitasPorFechaConsultorioYDoctor(fecha, consultorio, doctor);
    }

    @GetMapping("/pendientes-doctor")
    public List<Cita> getCitasPendientesPorDoctor(@RequestBody Doctor doctor){
        return citaService.getCitasPendientesPorDoctor(doctor);
    }


    @DeleteMapping("/{citaId}")
    public void cancelarCita(@PathVariable long citaId){
        citaService.cancelarCita(citaId);
    }

    @PutMapping
    public void editarCita(@RequestBody Cita cita){
        citaService.editarCita(cita);
    }
}
