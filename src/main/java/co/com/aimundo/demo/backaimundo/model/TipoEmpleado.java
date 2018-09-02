package co.com.aimundo.demo.backaimundo.model;

/**
 * Enum para determinar el tipo de empleado en el callcenter
 *
 * @author jearm
 */
public enum TipoEmpleado {
    OPERADOR(1),
    SUPERVISOR(2),
    DIRECTOR(3);
    private final int prioridad;

    TipoEmpleado(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getPrioridad() {
        return prioridad;
    }
}
