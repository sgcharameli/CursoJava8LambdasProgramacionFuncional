package es.sgcharameli.cursos.programacionfuncional.optionals;

import java.util.Optional;

public class OptionalPrueba {
    public static void main(String[] args) {
        probarOptional("Sergio");
//        probarOptional(null);
        orElseOptional("Sergio");
        orElseOptional(null);
        orElseThrow("Sergio");
//        orElseThrow(null);
        isPresent("Sergio");
        isPresent(null);
    }

    public static void probarOptional(final String nombre) {
        System.out.println(nombre.length());
    }

    public static void crearOptional() {
        Optional<String> optional = Optional.empty();
         optional.get();
    }

    public static void orElseOptional(final String nombre) {
        Optional<String> optional = Optional.ofNullable(nombre);
//        Optional<String> optional1 = Optional.of(nombre);

        final String nombreOfNullable = optional.orElse("Sin valor");
//        final String nombreOf = optional1.orElse("Sin valor");

        System.out.println(nombreOfNullable);
//        System.out.println(nombreOf);
    }

    public static void orElseThrow(final String nombre) {
        Optional<String> optional = Optional.ofNullable(nombre);
        String nombreRecuperado = optional.orElseThrow(NullPointerException::new);
        System.out.println("Nombre recuperado: " + nombreRecuperado);
    }

    public static void isPresent(final String nombre) {
        Optional<String> optional = Optional.ofNullable(nombre);
        System.out.println("Tiene valor: " + optional.isPresent());
    }
}
