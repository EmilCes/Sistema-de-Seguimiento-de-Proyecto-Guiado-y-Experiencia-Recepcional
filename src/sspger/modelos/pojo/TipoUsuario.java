
package sspger.modelos.pojo;
    
   
    
public class TipoUsuario {
    
     private int idTipoUsuario;
     private String descirpcion;

    public TipoUsuario() {
    }

    public TipoUsuario(int idTipoUsuario, String descirpcion) {
        this.idTipoUsuario = idTipoUsuario;
        this.descirpcion = descirpcion;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public String getDescirpcion() {
        return descirpcion;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public void setDescirpcion(String descirpcion) {
        this.descirpcion = descirpcion;
    }
     
     
}
