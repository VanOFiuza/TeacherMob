package pi.br.com.teacher.provider;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import pi.br.com.teacher.activities.LoginActivity;
import pi.br.com.teacher.activities.SlapshScreenActivity;
import pi.br.com.teacher.dao.UsuarioDAO;
import pi.br.com.teacher.interfaces.MetodoCallback;
import pi.br.com.teacher.model.Resposta;
import pi.br.com.teacher.model.UsuarioLogado;
import pi.br.com.teacher.model.UsuarioLogin;

public class AutenticarWebClient extends AsyncTask<Void, Void, String> {

    private Context context;
    private ProgressDialog alertDialog;
    Resposta resposta;
    private MetodoCallback metodoCallback;


    public AutenticarWebClient(Context context, MetodoCallback metodoCallback) {

        this.context = context;
        this.metodoCallback = metodoCallback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected String doInBackground(Void... params) {

        WebClient webClient = new WebClient();

        UsuarioDAO usuarioDAO = new UsuarioDAO(context);
        UsuarioLogin usuario = (UsuarioLogin) usuarioDAO.validarLogin();



        if (usuario != null) {
            UsuarioLogin user = new UsuarioLogin();
            user.setSenha(usuario.getSenha());
            user.setUsuario(usuario.getUsuario());
            String tipo = usuario.getTipo();
            usuario.setTipo(null);

            resposta = webClient.post(WebClient.urlServidor + "login/?userType="+tipo.toLowerCase(), user.toJson(), context, "POST");;
            Log.d("USUARIO", resposta.getResponse());
            return resposta.getResponse();
        } else
            return null;
    }


    @Override
    protected void onPostExecute(String s) {

        metodoCallback.metodo(s);
    }
}


