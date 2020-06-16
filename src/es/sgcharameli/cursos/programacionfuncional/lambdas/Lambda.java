package es.sgcharameli.cursos.programacionfuncional.lambdas;

public class Lambda implements PorDefecto {

    public static void main(String[] args) {

        //        () -> "mi nombre es";
//        (n) -> n * n;
//        (n) -> n == 2;
        MiNombre miNombreAnonima = new MiNombre() {
            @Override
            public String miNombre() {
                return "Sergio Anónima";
            }
        };
        System.out.println(miNombreAnonima.miNombre());
        MiNombre miNombreLambda = () -> "Sergio Lambda";
        System.out.println(miNombreLambda.miNombre());


        Sumar sumaAnonima = new Sumar() {
            @Override
            public int suma(int a, int b) {
                return a + b;
            }
        };
        System.out.println(sumaAnonima.suma(2, 3));
        Sumar sumaLambda = (a, b) -> a + b;
        System.out.println(sumaLambda.suma(2, 3));

        Sumar sumaLambda2 = (a, b) -> {
            a = b * b;
            a = a + b;
            System.out.println("Mensaje dentro del Lambda");
            return a;
        };
        System.out.println(sumaLambda2.suma(2, 3));


        Lambda l = new Lambda();
        System.out.println(l.nombrePorDefecto("sergio"));

    }

    @Override
    public void mostrarNombre(String nombre) {
    }
}
