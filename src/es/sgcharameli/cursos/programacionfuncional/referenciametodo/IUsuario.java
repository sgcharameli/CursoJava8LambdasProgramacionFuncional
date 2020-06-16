package es.sgcharameli.cursos.programacionfuncional.referenciametodo;

@FunctionalInterface
public interface IUsuario {
    Usuario crear(final String nombre);
}
