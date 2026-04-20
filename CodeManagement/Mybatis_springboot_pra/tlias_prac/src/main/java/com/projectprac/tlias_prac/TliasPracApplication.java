package com.projectprac.tlias_prac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan //开启了对javaweb框架的支持
@SpringBootApplication
public class TliasPracApplication {

    public static void main(String[] args) {
        SpringApplication.run(TliasPracApplication.class, args);
    }

}
