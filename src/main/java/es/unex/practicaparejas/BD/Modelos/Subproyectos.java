package es.unex.practicaparejas.BD.Modelos;

import java.sql.Date;

public class Subproyectos {
    private int id;
    private String denominacionC;
    private String denominacionL;
    private Date fechaInicio;
    private int idProyecto;

    public Subproyectos(int id, String denominacionC, String denominacionL, Date fechaInicio, int idProyecto) {
        this.id = id;
        this.denominacionC = denominacionC;
        this.denominacionL = denominacionL;
        this.fechaInicio = fechaInicio;
        this.idProyecto = idProyecto;
    }

    public Subproyectos(int id, String denominacionC) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenominacionC() {
        return denominacionC;
    }

    public void setDenominacionC(String denominacionC) {
        this.denominacionC = denominacionC;
    }

    public String getDenominacionL() {
        return denominacionL;
    }

    public void setDenominacionL(String denominacionL) {
        this.denominacionL = denominacionL;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }
}
