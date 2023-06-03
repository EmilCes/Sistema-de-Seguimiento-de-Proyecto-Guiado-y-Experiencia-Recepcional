package sspger.modelos.pojo;



public class TipoAnteproyecto {

    private int idTipoProyecto;
    private String nombreTipoProyecto;

    public TipoAnteproyecto() {
    }

    public TipoAnteproyecto(int idTipoProyecto, String nombreTipoProyecto) {
        this.idTipoProyecto = idTipoProyecto;
        this.nombreTipoProyecto = nombreTipoProyecto;
    }

    public int getIdTipoProyecto() {
        return idTipoProyecto;
    }

    public String getNombreTipoProyecto() {
        return nombreTipoProyecto;
    }

    public void setIdTipoProyecto(int idTipoProyecto) {
        this.idTipoProyecto = idTipoProyecto;
    }

    public void setNombreTipoProyecto(String nombreTipoProyecto) {
        this.nombreTipoProyecto = nombreTipoProyecto;
    }
    
}
