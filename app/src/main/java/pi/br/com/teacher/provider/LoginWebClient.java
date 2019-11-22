package pi.br.com.teacher.provider;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import pi.br.com.teacher.interfaces.MetodoCallback;
import pi.br.com.teacher.model.Resposta;
import pi.br.com.teacher.model.UsuarioLogin;

public class LoginWebClient extends AsyncTask<Void, Void, String> {

    private Context context;
    private ProgressDialog alertDialog;

    private MetodoCallback metodoCallback;
    Resposta resposta;
    private UsuarioLogin usuarioLogin;
    private String tipo;


    public Context getContext() {
        return context;
    }


    public LoginWebClient(Context context, UsuarioLogin usuarioLogin, String tipo, MetodoCallback metodoCallback) {

        this.context = context;
        this.metodoCallback =  metodoCallback;
        this.usuarioLogin = usuarioLogin;
        this.tipo = tipo;


    }

    @Override
    protected void onPreExecute() {


        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {

        WebClient webClient = new WebClient();


        System.out.println(usuarioLogin.toJson());
        resposta = webClient.post(WebClient.urlServidor + "login/?userType="+tipo.toLowerCase(), usuarioLogin.toJson(), context, "POST");
        Log.d("REQUISIÇÃO: ", resposta.getResponse() +", STATUSCODE : "+ resposta.getStatusCode());


        return resposta.getResponse();

    }

    @Override
    protected void onPostExecute(String s) {
       // Log.d("USUARIO LOGADO: ", s);

        if(resposta.getStatusCode()<400)
        metodoCallback.metodo(s);
        else
            Toast.makeText(context, "Login e senha Incorreto", Toast.LENGTH_SHORT).show();


    }
}