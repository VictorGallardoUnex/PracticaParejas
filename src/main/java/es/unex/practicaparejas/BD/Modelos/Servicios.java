package es.unex.practicaparejas.BD.Modelos;

public class Servicios {

    int SRV_id_servicio;
    int SRV_id_dirgen;

    public Servicios(int SRV_id_servicio, int SRV_id_dirgen){
        this.SRV_id_servicio = SRV_id_servicio;
        this.SRV_id_dirgen = SRV_id_dirgen;
    }

    public Servicios(int SRV_id_dirgen){
        this.SRV_id_dirgen = SRV_id_dirgen;
    }

    public int getSRV_id_servicio() {
        return SRV_id_servicio;
    }

    public int getSRV_id_dirgen() {
        return SRV_id_dirgen;
    }

    public void setSRV_id_servicio(int SRV_id_servicio) {
        this.SRV_id_servicio = SRV_id_servicio;
    }

    public void setSRV_id_dirgen(int SRV_id_dirgen) {
        this.SRV_id_dirgen = SRV_id_dirgen;
    }
}
