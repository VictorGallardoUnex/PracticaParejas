package es.unex.practicaparejas.BD.Modelos;

import java.sql.Date;

public class Proyectos {
    int id;
    int id_servicio;
    Date fecha_inicio;
    String denomicacionC;
    String denominacionL;

    public Proyectos(int id_servicio, Date fecha_inicio, String denomicacionC, String denominacionL){
        this.id_servicio = id_servicio;
        this.fecha_inicio = fecha_inicio;
        this.denomicacionC = denomicacionC;
        this.denominacionL = denominacionL;
    }


    public int getId() {
        return id;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public String getDenomicacionC() {
        return denomicacionC;
    }

    public String getDenominacionL() {
        return denominacionL;
    }
}
