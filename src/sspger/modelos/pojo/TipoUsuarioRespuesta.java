
package sspger.modelos.pojo;

import java.util.ArrayList;

public class TipoUsuarioRespuesta {
    private int codigoRespuesta;
    private ArrayList<TipoUsuario> TiposUsuarios;

    public TipoUsuarioRespuesta() {
    }

    public TipoUsuarioRespuesta(int codigoRespuesta, ArrayList<TipoUsuario> TiposUsuarios) {
        this.codigoRespuesta = codigoRespuesta;
        this.TiposUsuarios = TiposUsuarios;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public ArrayList<TipoUsuario> getTiposUsuarios() {
        return TiposUsuarios;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public void setTiposUsuarios(ArrayList<TipoUsuario> TiposUsuarios) {
        this.TiposUsuarios = TiposUsuarios;
    }
    
    
}
