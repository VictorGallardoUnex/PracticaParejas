package es.unex.practicaparejas.BD.Modelos;

import java.sql.Date;

public class Situaciones {
    private int idSubproyecto;
    private int idEstado;
    private Date fechaRef;

    public Situaciones(int idSubproyecto, int idEstado, Date fechaRef) {
        this.idSubproyecto = idSubproyecto;
        this.idEstado = idEstado;
        this.fechaRef = fechaRef;
    }

    public int getIdSubproyecto() {
        return idSubproyecto;
    }

    public void setIdSubproyecto(int idSubproyecto) {
        this.idSubproyecto = idSubproyecto;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public Date getFechaRef() {
        return fechaRef;
    }

    public void setFechaRef(Date fechaRef) {
        this.fechaRef = fechaRef;
    }
}
