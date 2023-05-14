package es.unex.practicaparejas.BD.Modelos;

public class Direcciones implements IModelo {
    private int idDireccion;
    private String denominacion;

    public Direcciones(int idDireccion, String denominacion){
        this.idDireccion = idDireccion;
        this.denominacion = denominacion;
    }
    public Direcciones(String denominacion){
        this.denominacion = denominacion;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }
}
