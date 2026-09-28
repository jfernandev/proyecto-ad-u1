package modelo;
import java.io.Serializable;

public class Avistamiento implements Serializable{
    private int id;
    private int idEspecie;
    private int idHabitat;
    private String fecha;
    private String localizacion;
    private String observaciones;

    public Avistamiento(int id, int idEspecie, int idHabitat, String fecha, String localizacion, String observaciones) {
        this.id = id;
        this.idEspecie = idEspecie;
        this.idHabitat = idHabitat;
        this.fecha = fecha;
        this.localizacion = localizacion;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public int getIdHabitat() {
        return idHabitat;
    }

    public void setIdHabitat(int idHabitat) {
        this.idHabitat = idHabitat;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Avistamiento{" +
                "id=" + id +
                ", idEspecie=" + idEspecie +
                ", idHabitat=" + idHabitat +
                ", fecha='" + fecha + '\'' +
                ", localizacion='" + localizacion + '\'' +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
}
