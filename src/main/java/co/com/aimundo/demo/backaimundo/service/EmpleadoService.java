/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.aimundo.demo.backaimundo.service;

import co.com.aimundo.demo.backaimundo.model.Empleado;
import co.com.aimundo.demo.backaimundo.model.LLamada;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Clase encargada de publicar los metodos de negocio que realiza el callcenter
 *
 * @author jearm
 */
public interface EmpleadoService {

    /**
     * Inicializa el listado de empleados que atienden el call center
     *
     * @param operadores cantidad de operadores
     * @param supervisores cantidad de supervisores
     * @param directores cantidad de directores
     */
    void inicializarEmpleados(int operadores, int supervisores, int directores);

    /**
     * Devuelve el listado de empleados que atiende el call center en el momento
     *
     * @return
     */
    List<Empleado> consultarEmpleados();

    /**
     * Metodo asyncronico que busca el empleado disponible desde operadores
     * hasta directores y atiende la llamada el el tiempo configurado para cada
     * llamada , en el caso de que todos esten ocupados se bloqueara el hilo y
     * cada segundo esta preguntando si un empleado ya se libero para asignarle
     * la siguiente llamada en cola
     *
     * @param lLamada llamada finalizada
     * @return
     */
    CompletableFuture<LLamada> atenderLlamada(LLamada lLamada);
}
