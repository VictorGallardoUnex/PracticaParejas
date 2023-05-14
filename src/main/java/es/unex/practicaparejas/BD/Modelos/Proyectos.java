package es.unex.practicaparejas.BD.Modelos;

import java.sql.Date;

public class Proyectos implements IModelo {
    private int PRY_id_proyecto;
    private final int PRY_id_servicio;
    private Date PRY_fechainicio;
    private String PRY_denominacionc;
    private String PRY_denominacionl;

    public Proyectos(int id,int PRY_id_servicio, Date PRY_fechainicio, String PRY_denominacionc, String PRY_denominacionl) {
        this.PRY_id_proyecto = id;
        this.PRY_id_servicio = PRY_id_servicio;
        this.PRY_fechainicio = PRY_fechainicio;
        this.PRY_denominacionc = PRY_denominacionc;
        this.PRY_denominacionl = PRY_denominacionl;
    }
    public Proyectos(int PRY_id_servicio, Date PRY_fechainicio, String PRY_denominacionc, String PRY_denominacionl){
        this.PRY_id_servicio = PRY_id_servicio;
        this.PRY_fechainicio = PRY_fechainicio;
        this.PRY_denominacionc = PRY_denominacionc;
        this.PRY_denominacionl = PRY_denominacionl;
    }
    public int getIdProyecto(){
        return PRY_id_proyecto;
    }

    public int getPRY_id_proyecto() {
        return PRY_id_proyecto;
    }

    public Date getPRY_fechainicio() {
        return PRY_fechainicio;
    }

    public int getPRY_id_servicio() {
        return PRY_id_servicio;
    }

    public String getPRY_denominacionc() {
        return PRY_denominacionc;
    }
    public void setPRY_denominacionc(String PRY_denominacionc){
        this.PRY_denominacionc = PRY_denominacionc;
    }

    public String getPRY_denominacionl() {
        return PRY_denominacionl;
    }
    public void setPRY_denominacionl(String PRY_denominacionl){
        this.PRY_denominacionl = PRY_denominacionl;
    }
    public void setPRY_fechainicio(Date PRY_fechainicio){
        this.PRY_fechainicio = PRY_fechainicio;
    }
    @Override
    public String toString() {
        return "ID: " + PRY_id_proyecto +
                " | ID Servicio: " + PRY_id_servicio +
                " | Fecha Inicio: " + PRY_fechainicio +
                " | Denominacion C: " + PRY_denominacionc +
                " | Denominacion L: " + PRY_denominacionl;
    }
}
