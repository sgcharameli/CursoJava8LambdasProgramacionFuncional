package es.sgcharameli.cursos.programacionfuncional;

import es.sgcharameli.cursos.programacionfuncional.lambdas.Lambda;
import es.sgcharameli.cursos.programacionfuncional.referenciametodo.ReferenciaMetodo;

public class Main {

    public static void main(String[] args) {
        System.out.println("\nCurso: Java 8 Programación Funcional con Lambdas y Streams\n");
        System.out.println(Main.class + ": Java version: " + System.getProperty("java.runtime.version"));

        Lambda.main(args);
        ReferenciaMetodo.main(args);

    }
}
