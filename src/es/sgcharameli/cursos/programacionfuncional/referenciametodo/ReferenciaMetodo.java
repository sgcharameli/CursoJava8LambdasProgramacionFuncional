package es.sgcharameli.cursos.programacionfuncional.referenciametodo;

import java.util.ArrayList;
import java.util.List;

public class ReferenciaMetodo {

    public static void main(String[] args) {

        // Referencia a método estático
        System.out.println("\nReferencia a método estático:");
        Trabajo trabajoAnonima = new Trabajo() {
            @Override
            public void accion() {
                Usuario.referenciaAMetodoEstatico();
            }
        };

        Trabajo trabajoLambda = () -> Usuario.referenciaAMetodoEstatico();
        Trabajo trabajoMetodoReferenciaEstatico = Usuario::referenciaAMetodoEstatico;

        trabajoAnonima.accion();
        trabajoLambda.accion();
        trabajoMetodoReferenciaEstatico.accion();

        // Referencia a método de instancia (objeto)
        System.out.println("\nReferencia a método de instancia (objeto):");
        Usuario usuario = new Usuario("Sergio");
        Trabajo trabajoLambdaReferenciaMetodoObjeto = () -> usuario.referenciaAMetodoDeObjeto();
        Trabajo trabajoMetodoReferenciaObjeto = usuario::referenciaAMetodoDeObjeto;

        trabajoLambdaReferenciaMetodoObjeto.accion();
        trabajoMetodoReferenciaObjeto.accion();

        // Referencia a método de instancia de un objeto arbitrario de un tipo particular
        System.out.println("\nReferencia a método de instancia de un objeto arbitrario de un tipo particular:");
        TrabajoString trabajoStringLambda = (palabra) -> palabra.toUpperCase();
        TrabajoString trabajoStringReferenciaMetodo = String::toUpperCase;

        System.out.println("\ttrabajoStringLambda: " + trabajoStringLambda.accion("kkñ"));
        System.out.println("\ttrabajoStringReferenciaMetodo: " + trabajoStringReferenciaMetodo.accion("kkñ"));


        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Sergio"));
        usuarios.add(new Usuario("Alberto"));
        usuarios.add(new Usuario("Vir"));
        usuarios.add(new Usuario("Juan"));

        usuarios.forEach(user -> user.mostrarNombre());
        usuarios.forEach(Usuario::mostrarNombre);

        // Referencia a constructor
        System.out.println("\nReferencia a constructor:");

        IUsuario iUsuario = new IUsuario() {
            @Override
            public Usuario crear(String nombre) {
                return new Usuario(nombre);
            }
        };
        IUsuario iUsuario1 = (nombre) -> new Usuario(nombre);
        IUsuario iUsuario2 = Usuario::new;
        iUsuario2.crear("Evaristo");
    }
}
