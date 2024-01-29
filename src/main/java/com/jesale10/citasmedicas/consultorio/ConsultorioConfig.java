package com.jesale10.citasmedicas.consultorio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ConsultorioConfig {
    @Bean
    CommandLineRunner commandLineRunnerConsultorios(ConsultorioRepository consultorioRepository){
        return args -> {
            Consultorio oftalmología = new Consultorio(
                    1,
                    1
            );

            Consultorio pediatría = new Consultorio(
                    3,
                    1
            );

            Consultorio dermatología = new Consultorio(
                    4,
                    2
            );

            consultorioRepository.saveAll(List.of(oftalmología, pediatría, dermatología));
        };
    }
}
