package es.sgcharameli.cursos.programacionfuncional.referenciametodo;

public class Usuario {

    private String nombre;

    public Usuario(final String nombre) {
        this.nombre = nombre;
    }

    public static void referenciaAMetodoEstatico() {
        System.out.println("\tProbando referencia a método estático");
    }

    public void referenciaAMetodoDeObjeto() {
        System.out.println("\tProbando referencia a método de objeto");
    }

    public void mostrarNombre() {
        System.out.println("\tMostrando nombre: " + this.nombre);
    }
}
