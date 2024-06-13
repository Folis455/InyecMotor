package front.inyecmotor;

public class Marca {
    private int id; // Variable id
    private String nombre;

    // Constructor vacío (puede ser útil para algunas operaciones)
    public Marca() {
    }

    // Constructor con parámetro para inicializar el campo nombre
    public Marca(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getter y setter para el campo id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter y setter para el campo nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Marca{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
