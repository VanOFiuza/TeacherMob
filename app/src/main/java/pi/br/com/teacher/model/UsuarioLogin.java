package pi.br.com.teacher.model;

import com.google.gson.Gson;

public class UsuarioLogin {

    private String usuario;
    private String senha;
    private String tipo;
    private String id;
    private String ra;


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String toJson() {
        Gson gson = new Gson();

        String user = gson.toJson(this, UsuarioLogin.class);
        return  user;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }
}
