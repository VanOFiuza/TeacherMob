package pi.br.com.teacher.provider;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import pi.br.com.teacher.dao.UsuarioDAO;
import pi.br.com.teacher.interfaces.MetodoCallback;
import pi.br.com.teacher.model.Resposta;
import pi.br.com.teacher.model.UsuarioLogado;
import pi.br.com.teacher.model.UsuarioLogin;

public class AtribuirPresencaWebClient extends AsyncTask<Void, Void, String> {

    private Context context;
    private ProgressDialog alertDialog;
    Resposta resposta;
    private MetodoCallback metodoCallback;
    private String id_aula;


    public AtribuirPresencaWebClient(String id_aula,Context context, MetodoCallback metodoCallback) {

        this.context = context;
        this.metodoCallback = metodoCallback;
        this.id_aula = id_aula;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected String doInBackground(Void... params) {

        WebClient webClient = new WebClient();

        JSONObject postData = new JSONObject();
        try {
            postData.put("aluno_id", UsuarioLogado.usuarioLogin.getId());
            postData.put("aula_id", id_aula);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        resposta = webClient.post(WebClient.urlServidor + "aluno/presenca", postData.toString(), context, "POST");
        Log.d("USUARIO", resposta.getResponse());
        return resposta.getResponse();

    }


    @Override
    protected void onPostExecute(String s) {
        Log.d("USUARIO", s);
        metodoCallback.metodo(s);
    }
}


