package com.clanchas.clanchas;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClanchasApplication implements CommandLineRunner {

    private final Log log = LogFactory.getLog(getClass());

    public static void main(String[] args) {
        SpringApplication.run(ClanchasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Servidor iniciado...");
    }

}
