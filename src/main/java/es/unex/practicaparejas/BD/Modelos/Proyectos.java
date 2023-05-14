package es.unex.practicaparejas.BD.Modelos;

import java.sql.Date;

public class Proyectos {
    private int PRY_id_proyecto;
    private int PRY_id_servicio;
    private Date PRY_fechainicio;
    private String PRY_denominacionc;
    private String PRY_denominacionl;

    public Proyectos(int PRY_id_servicio, Date PRY_fechainicio, String PRY_denominacionc, String PRY_denominacionl){
        this.PRY_id_servicio = PRY_id_servicio;
        this.PRY_fechainicio = PRY_fechainicio;
        this.PRY_denominacionc = PRY_denominacionc;
        this.PRY_denominacionl = PRY_denominacionl;
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

    public String getPRY_denominacionl() {
        return PRY_denominacionl;
    }
}
