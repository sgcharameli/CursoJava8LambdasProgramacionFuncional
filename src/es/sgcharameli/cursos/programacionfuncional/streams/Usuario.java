package es.sgcharameli.cursos.programacionfuncional.streams;

public class Usuario {
    private int id;
    private String nombre;

    public Usuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        final String toString = "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
        System.out.println(toString);
        return toString;
    }
}
