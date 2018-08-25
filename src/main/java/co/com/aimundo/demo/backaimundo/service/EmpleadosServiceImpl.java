/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.aimundo.demo.backaimundo.service;

import co.com.aimundo.demo.backaimundo.model.Empleado;
import co.com.aimundo.demo.backaimundo.model.EstadoEmpleado;
import co.com.aimundo.demo.backaimundo.model.EstadoLlamada;
import co.com.aimundo.demo.backaimundo.model.LLamada;
import co.com.aimundo.demo.backaimundo.model.TipoEmpleado;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Implementaciòn de los metoos del service de empleados
 *
 * @author jearm
 */
@Service
public class EmpleadosServiceImpl implements EmpleadoService {

    /**
     * Lista de empleados en el callcenter durante la ejecucion
     */
    List<Empleado> listaEmpleados;

    @Override
    public void inicializarEmpleados(int operadores, int supervisores, int directores) {
        listaEmpleados = new ArrayList<>();
        if (operadores > 0) {
            for (int i = 0; i < operadores; i++) {
                listaEmpleados.add(new Empleado(TipoEmpleado.OPERADOR, "Operador" + (i + 1)));
            }
        }
        if (supervisores > 0) {
            for (int i = 0; i < supervisores; i++) {
                listaEmpleados.add(new Empleado(TipoEmpleado.SUPERVISOR, "Supervisor" + (i + 1)));
            }
        }
        if (directores > 0) {
            for (int i = 0; i < directores; i++) {
                listaEmpleados.add(new Empleado(TipoEmpleado.DIRECTOR, "Director" + (i + 1)));
            }
        }
    }

    @Override
    public List<Empleado> consultarEmpleados() {
        return this.listaEmpleados;
    }

    @Async
    @Override
    public CompletableFuture<LLamada> atenderLlamada(LLamada llamada) {
        try {
            //Busco el empleado disponible / espero 1 segundo para volver a intenter si no hay ninguno
            Empleado empleadoDisponible;
            do {
                empleadoDisponible = buscarEmpleadoDisponible();
                if (empleadoDisponible == null) {
                    Thread.sleep(1000);
                }
            } while (buscarEmpleadoDisponible() == null);

            System.out.println(empleadoDisponible.getNombre() + " esta libre y atendera la llamada " + llamada.getDescripcion() + " durante " + llamada.getDuracion() + " segundos");
            //Cambio el estado del empleado
            empleadoDisponible.setEstadoEmpleado(EstadoEmpleado.BUSY);
            //Asigno el empleado a la llamada
            llamada.setEmpleado(empleadoDisponible);
            //Duermo el hilo el tiempo que este configurado en la llamada
            Thread.sleep(llamada.getDuracion() * 1000);
            //Cuado termine libero el empleado
            empleadoDisponible.setEstadoEmpleado(EstadoEmpleado.FREE);
            //Finalizo la llamada
            llamada.setEstadoLlamada(EstadoLlamada.FINALIZADA);
            System.out.println("LLamada finalizada " + empleadoDisponible.getNombre() + " libre");
        } catch (InterruptedException ex) {
            Logger.getLogger(EmpleadosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Ocurrio un error al procesar la llamada");
        }
        return CompletableFuture.completedFuture(llamada);
    }

    /**
     * EN el orden definido en el enum de @TipoEmpleado
     * /Operador/Supervisor/Director Busca el sigueinte empleado que este "FREE"
     *
     * @return null o el siguiente empleado disponible
     */
    private synchronized Empleado buscarEmpleadoDisponible() {
        for (TipoEmpleado tipo : TipoEmpleado.values()) {
            Optional<Empleado> findFirst = listaEmpleados.stream().filter(empleado -> empleado.getTipoEmpleado().equals(tipo) && empleado.getEstadoEmpleado().equals(EstadoEmpleado.FREE)).findFirst();
            if (findFirst.isPresent()) {
                return findFirst.get();
            }
        }
        return null;
    }

}
