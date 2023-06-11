
package sspger.modelos.pojo;
    
   
    
public class TipoUsuario {
    
     private int idTipoUsuario;
     private String descripcion;
     private int codigoRespuesta;
     
    public TipoUsuario() {
    }

    public TipoUsuario(int idTipoUsuario, String descripcion) {
        this.idTipoUsuario = idTipoUsuario;
        this.descripcion = descripcion;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

  
    
    
    

     @Override
    public String toString() {
        return descripcion;
    }

     
}
