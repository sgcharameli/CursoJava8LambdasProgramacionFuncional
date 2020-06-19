package es.sgcharameli.cursos.programacionfuncional.highorderfunctions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HighOrderFunctions implements SumarInterfaz {

    public static void main(String[] args) {
        HighOrderFunctions highOrderFunctions = new HighOrderFunctions();

        // Función
        System.out.println(highOrderFunctions.suma(2, 3));

        // Interfaz
        System.out.println(highOrderFunctions.apply(2, 4));

        // High Order Functions
        SumarInterfaz sumarInterfaz = (a, b) -> a + b;
        System.out.println(highOrderFunctions.sumarHighOrderFunction(sumarInterfaz, 2, 5));
        // -------------------------------------------------------------------

        // Interfaz Funcional Function<T, R>
        /*
            interface Function<T t, R r> {
                R apply(T t);
            }
         */
        Function<String, String> convertirAMayusculas = elemento -> elemento.toUpperCase();
        highOrderFunctions.imprimirMayuscula(convertirAMayusculas, "Sergio");
        // -------------------------------------------------------------------

        // Interfaz Funcional BiFunction<T, U, R>
        /*
            interface BiFunction<T, U, R> {
                R apply(T y, U u)
            }
         */
        // Interfaz  Funcional Predicate<T>
        /*
            interface Predicate<T> {
                Boolean test (T t)
            }
         */
        List<Integer> numeros = Arrays.asList(7, 23, -5, 4, 68, -9, -67, 46);
        BiFunction<
                List<Integer>,
                Predicate<Integer>,
                List<Integer>
        > filtrar = (lista, predicado) -> lista.stream().filter(e->predicado.test(e)).collect(Collectors.toList());
        System.out.println(filtrar.apply(numeros, e -> e>0));
        // -------------------------------------------------------------------

        // Interfaz Funcional Consumer<T>
        /*
        interfaz Consumer<T> {
            void accept(T t)
        }
         */
        List<String> nombres = new ArrayList<>();
        nombres.add("Alberto");
        nombres.add("Sergio");
        nombres.add("Vir");
        highOrderFunctions.filtrar(nombres, e -> System.out.println(e), 6);

    }

    public int suma(int a, int b) {
        return a + b;
    }

    @Override
    public int apply(int a, int b) {
        return a + b;
    }

    public int sumarHighOrderFunction(SumarInterfaz sumar, int a, int b) {
        return sumar.apply(a, b);
    }

    public void imprimirMayuscula(Function<String, String> funcion, String nombre) {
        System.out.println(funcion.apply(nombre));
    }

    public void filtrar(List<String> lista, Consumer<String> consumer, int maximoCaracteres) {
        lista.stream().filter(logicaPredicado(maximoCaracteres)).forEach(consumer);
    }

    public Predicate<String> logicaPredicado(int maximoCaracteres) {
        return e -> e.length() < maximoCaracteres;
    }
}
