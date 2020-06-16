package es.sgcharameli.cursos.programacionfuncional.lambdas;

public interface PorDefecto {

    void mostrarNombre(String nombre);

    default String nombrePorDefecto(String nombre) {
        return nombre + " default";
    }
}
