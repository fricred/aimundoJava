/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.aimundo.demo.backaimundo.model;

/**
 * Abstraciçon de un empleado del callcenter
 *
 * @author jearm
 */
public class Empleado {
    //Datos del empeado

    private final TipoEmpleado tipoEmpleado;
    private final String nombre;
    private EstadoEmpleado estadoEmpleado;

    public Empleado(TipoEmpleado tipoEmpleado, String nombre) {
        this.tipoEmpleado = tipoEmpleado;
        this.nombre = nombre;
        this.estadoEmpleado = EstadoEmpleado.FREE;
    }

    public int getPrioridad() {
        return this.tipoEmpleado.getPrioridad();
    }

    public String getNombre() {
        return nombre;
    }

    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public EstadoEmpleado getEstadoEmpleado() {
        return estadoEmpleado;
    }

    public void setEstadoEmpleado(EstadoEmpleado estadoEmpleado) {
        this.estadoEmpleado = estadoEmpleado;
    }

    @Override
    public String toString() {
        return "Empleado{" + "nombre=" + nombre + '}';
    }

}
