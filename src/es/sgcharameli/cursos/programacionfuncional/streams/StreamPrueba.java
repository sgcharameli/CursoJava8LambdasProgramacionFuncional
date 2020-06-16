package es.sgcharameli.cursos.programacionfuncional.streams;

import javax.sound.midi.SysexMessage;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

        // Count
        System.out.println("-------------------- Count --------------------");
        setupUsuarios();
        long numeroFiltrado = usuarios.stream()
                .filter(u -> u.getId() < 3)
                .count();
        System.out.println(numeroFiltrado);

        // Skip y Limit
        System.out.println("-------------------- Skip y Limit --------------------");
        String[] abc = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        List<String> abcFilter = Arrays.stream(abc)
                .skip(2)
                .limit(4)
                .collect(Collectors.toList());
        abcFilter.stream().forEach(letra -> System.out.println(letra));

        // Sorted
        System.out.println("-------------------- Sorted --------------------");
        setupUsuarios();
        usuarios = usuarios.stream()
                .sorted(Comparator.comparing(Usuario::getNombre))
                .collect(Collectors.toList());
        imprimirLista();

        // Min y Max
        System.out.println("-------------------- Min y Max --------------------");
        setupUsuarios();
        Usuario usuarioMin = usuarios.stream()
                .min(Comparator.comparing(Usuario::getId))
                .orElse(null);
        System.out.println("Usuario con menor id: " + usuarioMin);
        Usuario usuarioMax = usuarios.stream()
                .max(Comparator.comparing(Usuario::getId))
                .orElse(null);
        System.out.println("Usuario con mayor id: " + usuarioMax);

        // Distinct
        System.out.println("-------------------- Distinct --------------------");
        setupUsuarios();
        List<Usuario> usuariosFilter = usuarios.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Usuarios distintos: " + usuariosFilter);

        // allMatch, anyMatch, noneMatch
        System.out.println("-------------------- allMatch, anyMatch, noneMatch --------------------");
        List<Integer> listaNumeros = Arrays.asList(100, 300, 900, 5000);
        boolean allMatch = listaNumeros.stream().allMatch( numero -> numero > 300);
        System.out.println("allMatch: " + allMatch);

        boolean anyMatch = listaNumeros.stream().anyMatch( numero -> numero > 300);
        System.out.println("anyMatch: " + anyMatch);

        boolean noneMatch = listaNumeros.stream().noneMatch( numero -> numero > 7300);
        System.out.println("noneMatch: " + noneMatch);

        // Sum, average, range
        System.out.println("-------------------- Sum, average, range --------------------");
        setupUsuarios();
        double mediaIds = usuarios.stream()
                .mapToInt(Usuario::getId)
                .average()
                .orElse(0);
        System.out.println("Media de ids: " + mediaIds);

        double sumaIds = usuarios.stream()
                .mapToInt(Usuario::getId)
                .sum();
        System.out.println("Suma de ids: " + sumaIds);

        System.out.println("Rango: " + IntStream.range(0, 100).sum());

        // Reduce
        System.out.println("-------------------- Reduce --------------------");
        setupUsuarios();
        int sumaIdsReduce = usuarios.stream()
                .map(Usuario::getId)
                .reduce(100, Integer::sum);
        System.out.println("SumaIdsReduce: " + sumaIdsReduce);

        // Collectors

        // Collectors - Joining
        System.out.println("-------------------- Joining --------------------");
        setupUsuarios();
        String nombresSeparados = usuarios.stream()
                .map(Usuario::getNombre)
                .collect(Collectors.joining(" - "));
        System.out.println("nombresSeparados por '-' :" + nombresSeparados);

        // Collectors - toSet
        System.out.println("-------------------- toSet --------------------");
        setupUsuarios();
        Set<String> nombreSet = usuarios.stream()
                .map(Usuario::getNombre)
                .collect(Collectors.toSet());
        nombreSet.stream().forEach(nombre -> System.out.println(nombre));

        // Collectors - SummarizingDouble
        System.out.println("-------------------- SummarizingDouble --------------------");
        setupUsuarios();
        DoubleSummaryStatistics doubleSummaryStatistics = usuarios.stream()
                .collect(Collectors.summarizingDouble(Usuario::getId));
        System.out.println("Estadísticas: " + doubleSummaryStatistics);

        DoubleSummaryStatistics doubleSummaryStatistics1 = usuarios.stream()
                .mapToDouble(Usuario::getId)
                .summaryStatistics();
        System.out.println("Estadísticas: " + doubleSummaryStatistics1);

        // Collectors - PartitioningBy
        System.out.println("-------------------- PartitioningBy --------------------");
        setupUsuarios();
        List<Integer> numeros = Arrays.asList(5, 7,34,56, 2, 3, 67, 4, 98);
        Map<Boolean, List<Integer>> esMayor = numeros.stream()
                .collect(Collectors.partitioningBy(n -> n > 10));
        esMayor.get(true).stream().forEach(n -> System.out.println("Es mayor: " + n));
        esMayor.get(false).stream().forEach(n -> System.out.println("No es mayor: " + n));

        // Collectors - groupingBy
        System.out.println("-------------------- groupingBy --------------------");
        setupUsuarios();
        Map<Character, List<Usuario>> grupoAlfabetico = usuarios.stream()
                .collect(Collectors.groupingBy(usu -> usu.getNombre().charAt(0)));
        grupoAlfabetico.get('S').stream().forEach(n -> System.out.println("Empieza por 'S': " + n));
        grupoAlfabetico.get('A').stream().forEach(n -> System.out.println("Empieza por 'A': " + n));
        grupoAlfabetico.get('V').stream().forEach(n -> System.out.println("Empieza por 'V': " + n));
        System.out.println("gruposAlfabeticos: " + grupoAlfabetico);

        // Collectors - mapping
        System.out.println("-------------------- mapping --------------------");
        setupUsuarios();
        List<String> personas = usuarios.stream()
                .collect(Collectors.mapping(Usuario::getNombre, Collectors.toList()));
        personas.stream().forEach(p -> System.out.println("Persona: " + p));

        // Stream Paralelo
        System.out.println("-------------------- Stream Paralelo --------------------");
        setupUsuarios();
        long tiempoInicial = System.currentTimeMillis();
        usuarios.stream().forEach(u -> convertirAMayusculas(u.getNombre()));
        long tiempoFinal = System.currentTimeMillis();
        System.out.println("Tiempo total stream: " + (tiempoFinal - tiempoInicial));

        tiempoInicial = System.currentTimeMillis();
        usuarios.parallelStream().forEach(u -> convertirAMayusculas(u.getNombre()));
        tiempoFinal = System.currentTimeMillis();
        System.out.println("Tiempo total paralelizado: " + (tiempoFinal - tiempoInicial));
    }

    private static String convertirAMayusculas(final String nombre) {
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        return nombre.toUpperCase();
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
