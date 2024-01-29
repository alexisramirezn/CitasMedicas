package com.jesale10.citasmedicas.cita;

import com.jesale10.citasmedicas.consultorio.Consultorio;
import com.jesale10.citasmedicas.doctor.Doctor;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "consultorio_id")
    private Consultorio consultorio;

    private Date horarioConsulta;
    private String nombrePaciente;

    public Cita() {
    }

    public Cita(Doctor doctor, Consultorio consultorio, Date horarioConsulta, String nombrePaciente) {
        this.doctor = doctor;
        this.consultorio = consultorio;
        this.horarioConsulta = horarioConsulta;
        this.nombrePaciente = nombrePaciente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public Date getHorarioConsulta() {
        return horarioConsulta;
    }

    public void setHorarioConsulta(Date horarioConsulta) {
        this.horarioConsulta = horarioConsulta;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", consultorio=" + consultorio +
                ", horarioConsulta=" + horarioConsulta +
                ", nombrePaciente='" + nombrePaciente + '\'' +
                '}';
    }
}
