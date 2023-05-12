package es.unex.practicaparejas.BD.Modelos;

public class Servicios {
    int id;
    int id_direccion;
    public Servicios(int id, int id_direccion){
        this.id = id;
        this.id_direccion = id_direccion;
    }

    public int getId() {
        return id;
    }

    public int getId_direccion() {
        return id_direccion;
    }
}
