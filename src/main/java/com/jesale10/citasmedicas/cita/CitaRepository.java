package com.jesale10.citasmedicas.cita;

import com.jesale10.citasmedicas.consultorio.Consultorio;
import com.jesale10.citasmedicas.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.Date;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByConsultorioAndHorarioConsulta(Consultorio consultorio, Date horarioConsulta);

    List<Cita> findByDoctorAndHorarioConsulta(Doctor doctor, Date horarioConsulta);

    List<Cita> findByNombrePacienteAndHorarioConsultaBetween(String nombrePaciente, Date fechaCita, Date fechaCitaMas2Horas);

    List<Cita> findByDoctorAndHorarioConsultaBetween(Doctor doctor, Date inicioDia, Date finDia);

    List<Cita> findByHorarioConsultaAndConsultorioAndDoctor(Date horarioConsulta, Consultorio consultorio, Doctor doctor);

    List<Cita> findByDoctorAndHorarioConsultaAfter(Doctor doctor, Date fechaActual);
}
