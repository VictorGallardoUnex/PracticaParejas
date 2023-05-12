package es.unex.practicaparejas.BD.Modelos;

public class Direcciones {
    public int id;
    private String denominacion;
    public String getDenominacion() {
        return denominacion;
    }
    public int getId() {
        return id;
    }
    protected Direcciones(){
    }
    public Direcciones(int id, String denominacion){
        this.id = id;
        this.denominacion = denominacion;
    }
}
