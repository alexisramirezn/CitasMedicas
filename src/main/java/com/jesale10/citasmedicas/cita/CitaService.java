package com.jesale10.citasmedicas.cita;

import com.jesale10.citasmedicas.consultorio.Consultorio;
import com.jesale10.citasmedicas.doctor.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {
    private final CitaRepository citaRepository;

    @Autowired
    CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public List<Cita> getCitas() {
        return citaRepository.findAll();
    }

    public void addCita(Cita cita) {
        // Verificar reglas de negocio antes de agregar la cita
        if (validarCita(cita)) {
            citaRepository.save(cita);
        } else {
            // Manejar la situación en la que la cita no cumple las reglas de negocio
            throw new IllegalArgumentException("La cita no cumple las reglas de negocio.");
        }
    }

    private boolean validarCita(Cita cita) {
        return !existeCitaMismoConsultorioYHora(cita) &&
                !existeCitaMismoDoctorYHora(cita) &&
                !existeCitaMismoPacienteEnRango(cita) &&
                !excedeCitasDelDoctorEnElDia(cita);
    }

    private boolean existeCitaMismoConsultorioYHora(Cita cita) {
        List<Cita> citasMismoConsultorioYHora = citaRepository.findByConsultorioAndHorarioConsulta(cita.getConsultorio(), cita.getHorarioConsulta());
        return !citasMismoConsultorioYHora.isEmpty();
    }

    private boolean existeCitaMismoDoctorYHora(Cita cita) {
        List<Cita> citasMismoDoctorYHora = citaRepository.findByDoctorAndHorarioConsulta(cita.getDoctor(), cita.getHorarioConsulta());
        return !citasMismoDoctorYHora.isEmpty();
    }

    private boolean existeCitaMismoPacienteEnRango(Cita cita) {
        Date fechaCita = cita.getHorarioConsulta();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaCita);
        calendar.add(Calendar.HOUR_OF_DAY, 2);
        Date fechaCitaMas2Horas = calendar.getTime();

        List<Cita> citasMismoPaciente = citaRepository.findByNombrePacienteAndHorarioConsultaBetween(cita.getNombrePaciente(), fechaCita, fechaCitaMas2Horas);
        return !citasMismoPaciente.isEmpty();
    }

    private boolean excedeCitasDelDoctorEnElDia(Cita cita) {
        Date fechaCita = cita.getHorarioConsulta();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaCita);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date inicioDia = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date finDia = calendar.getTime();

        List<Cita> citasDelDoctorEnElDia = citaRepository.findByDoctorAndHorarioConsultaBetween(
                cita.getDoctor(), inicioDia, finDia);
        return citasDelDoctorEnElDia.size() >= 8;
    }

    public List<Cita> getCitasPorFechaConsultorioYDoctor(Date fecha, Consultorio consultorio, Doctor doctor) {
        return citaRepository.findByHorarioConsultaAndConsultorioAndDoctor(fecha, consultorio, doctor);
    }

    public List<Cita> getCitasPendientesPorDoctor(Doctor doctor) {
        Date fechaActual = new Date();
        return citaRepository.findByDoctorAndHorarioConsultaAfter(doctor, fechaActual);
    }

    public void cancelarCita(long citaId) {
        Optional<Cita> optionalCita = citaRepository.findById(citaId);
        if (optionalCita.isPresent()) {
            Cita cita = optionalCita.get();
            if (cita.getHorarioConsulta().after(new Date())) {
                citaRepository.delete(cita);
            } else {
                throw new IllegalArgumentException("No se puede cancelar una cita pasada.");
            }
        } else {
            throw new IllegalArgumentException("La cita con el ID proporcionado no existe.");
        }
    }

    public void editarCita(Cita cita) {
        if (cita.getId() == null) {
            throw new IllegalArgumentException("La cita no tiene un ID válido para editar.");
        }

        Optional<Cita> optionalCita = citaRepository.findById(cita.getId());
        if (optionalCita.isPresent()) {
            Cita existingCita = optionalCita.get();
            existingCita.setDoctor(cita.getDoctor());
            existingCita.setConsultorio(cita.getConsultorio());
            existingCita.setHorarioConsulta(cita.getHorarioConsulta());
            existingCita.setNombrePaciente(cita.getNombrePaciente());
            citaRepository.save(existingCita);
        } else {
            throw new IllegalArgumentException("La cita a editar no existe en la base de datos.");
        }
    }

}
