package co.com.aimundo.demo.backaimundo.model;

import java.util.Random;

/**
 * Abstraccion de una llamada que sera atendida en el call center
 *
 * @author jearm
 */
public class LLamada {

    public static final int MIN_DURACION = 5;
    public static final int MAX_DURACION = 10;
    private int duracion;
    private String descripcion;
    private EstadoLlamada estadoLlamada;
    private Empleado empleado;

    public LLamada(String descripcion, EstadoLlamada estadoLlamada) {
        duracion = new Random().nextInt(MAX_DURACION - MIN_DURACION + 1) + MIN_DURACION;
        this.descripcion = descripcion;
        this.estadoLlamada = estadoLlamada;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoLlamada getEstadoLlamada() {
        return estadoLlamada;
    }

    public void setEstadoLlamada(EstadoLlamada estadoLlamada) {
        this.estadoLlamada = estadoLlamada;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

}
