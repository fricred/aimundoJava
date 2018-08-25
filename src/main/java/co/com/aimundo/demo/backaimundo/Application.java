/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.aimundo.demo.backaimundo;

import java.util.concurrent.Executor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * @author jearm
 */
@SpringBootApplication
@EnableAsync
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Configuración de la funcionalidad asyncrona de la aplicación 1 - Se
     * asigna una disponiblidad inicial de 10 hilos 2 - Se asigna un tamaño
     * maximo de la cola de 500
     *
     * Quiere decir que soporta 10 hilos concurrentes y los demas estaran en la
     * cola esperando su turno a ser ejecutados
     *
     * @return
     */
    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("EmpleadoService-");
        executor.initialize();
        return executor;
    }
}
