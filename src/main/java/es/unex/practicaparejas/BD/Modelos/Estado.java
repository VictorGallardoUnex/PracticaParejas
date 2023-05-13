package es.unex.practicaparejas.BD.Modelos;

public class Estado {
    private int idEstado;
    private String denominacion;

    public Estado(String denominacion) {
//        this.idEstado = idEstado;
        this.denominacion = denominacion;
    }
    public Estado(int idEstado,String denominacion) {
        this.idEstado = idEstado;
        this.denominacion = denominacion;
    }

    public int getIdEstado() {
        return idEstado;
    }


    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }
}
