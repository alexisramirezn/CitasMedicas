package com.jesale10.citasmedicas.doctor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DoctorConfig {
    @Bean
    CommandLineRunner commandLineRunnerDoctores(DoctorRepository doctorRepository) {
        return args -> {
            Doctor alexis = new Doctor(
                    "Alexis",
                    "Ramirez",
                    "Navor",
                    "Oftalmología"
            );

            Doctor jorge = new Doctor(
                    "Jorge",
                    "Garcia",
                    "Peña",
                    "Pediatría"
            );

            Doctor jose = new Doctor(
                    "Jose",
                    "Muñoz",
                    "Lopez",
                    "Dermatología"
            );

            doctorRepository.saveAll(List.of(alexis, jorge, jose));
        };
    }
}
