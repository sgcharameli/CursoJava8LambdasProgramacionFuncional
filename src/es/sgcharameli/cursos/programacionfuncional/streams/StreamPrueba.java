package es.sgcharameli.cursos.programacionfuncional.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPrueba {

    private static List<Usuario> usuarios;

    public static void main(String[] args) {
        setupUsuarios();
        Stream stream = Stream.of(usuarios);
        // Equivalente
        stream = usuarios.stream();

        usuarios.stream().forEach(usuario -> usuario.setNombre(usuario.getNombre() + " Apellido"));
        imprimirLista();

        // Map y Collectors.toList
        List<String> listaString = usuarios.stream().map(usuario -> usuario.getNombre()).collect(Collectors.toList());
        listaString.stream().forEach(elemento -> System.out.println(elemento));

        // Filter
        System.out.println("-------------------- Filters --------------------");
        setupUsuarios();
        List<Usuario> usuariosFiltrados = usuarios.stream()
                .filter(usuario -> !usuario.getNombre().equalsIgnoreCase("alberto"))
                .filter(usuario -> usuario.getId() < 4)
                .collect(Collectors.toList());
        usuariosFiltrados.stream().forEach(u -> System.out.println(u.getId() + " - " + u.getNombre()));
        usuariosFiltrados.stream().forEach(Usuario::toString);

        // Find First
        System.out.println("-------------------- Find First --------------------");
        setupUsuarios();
        Usuario usuario = usuarios.stream()
                .filter(u -> u.getNombre().equalsIgnoreCase("Sergio"))
                .findFirst()
                .orElse(null);
        System.out.println(usuario.getId() + " - " + usuario.getNombre());

        // FlatMap
        System.out.println("-------------------- FlatMap --------------------");
        setupUsuarios();
        List<List<String>> nombresVariasListas = new ArrayList<>(
                Arrays.asList(
                        new ArrayList<>(Arrays.asList("Alberto", "María", "Pedro")),
                        new ArrayList<>(Arrays.asList("Monica", "Sergio", "Vir"))
                )
        );
        System.out.println(nombresVariasListas);
        List<String> nombresUnicaLista = nombresVariasListas.stream()
                .flatMap(lista -> lista.stream())
                .collect(Collectors.toList());
        System.out.println(nombresUnicaLista);

        // Peek
        System.out.println("-------------------- Peek --------------------");
        setupUsuarios();
        List<Usuario> usuarios1 = usuarios.stream()
                .peek(u -> u.setNombre(u.getNombre() + " Apellido"))
                .collect(Collectors.toList());
        usuarios1.forEach(usuario1 -> System.out.println(usuario1.getNombre()));


    }

    private static void setupUsuarios() {
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1, "Sergio"));
        usuarios.add(new Usuario(2, "Alberto"));
        usuarios.add(new Usuario(3, "Vir"));
        usuarios.add(new Usuario(4, "Adolfo"));
        usuarios.add(new Usuario(5, "Sergio"));
    }

    private static void imprimirLista() {
        usuarios.stream().forEach(usuario -> System.out.println(usuario.getId() + " - " + usuario.getNombre()));
    }
}
