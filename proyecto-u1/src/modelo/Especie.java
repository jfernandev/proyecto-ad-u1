package modelo;
import java.io.Serializable;

public class Especie implements Serializable{
    private int id;
    private String nombreComun;
    private String nombreCientifico;
    private String comestibilidad;
    private String descripcion;

    public Especie(int id, String nombreComun, String nombreCientifico, String comestibilidad, String descripcion) {
        this.id = id;
        this.nombreComun = nombreComun;
        this.nombreCientifico = nombreCientifico;
        this.comestibilidad = comestibilidad;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreComun() {
        return nombreComun;
    }

    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public String getComestibilidad() {
        return comestibilidad;
    }

    public void setComestibilidad(String comestibilidad) {
        this.comestibilidad = comestibilidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return  "\nID: " + id +
                "Nombre común: " + nombreComun +
                "Nombre científico: " + nombreCientifico +
                "Comestibilidad: " + comestibilidad +
                "Descripcion: " + descripcion +
                "\n=======================================================";
    }
}
