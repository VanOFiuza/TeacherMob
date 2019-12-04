package pi.br.com.teacher.provider;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import pi.br.com.teacher.dao.UsuarioDAO;
import pi.br.com.teacher.interfaces.MetodoCallback;
import pi.br.com.teacher.model.Resposta;
import pi.br.com.teacher.model.UsuarioLogado;
import pi.br.com.teacher.model.UsuarioLogin;

public class DeletarPresencaWebClient extends AsyncTask<Void, Void, String> {

    private Context context;
    private ProgressDialog alertDialog;
    Resposta resposta;
    private MetodoCallback metodoCallback;
    private String id_aula;
    private String id_aluno;


    public DeletarPresencaWebClient(String id_aula,String id_aluno,Context context, MetodoCallback metodoCallback) {

        this.context = context;
        this.metodoCallback = metodoCallback;
        this.id_aula = id_aula;
        this.id_aluno = id_aluno;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected String doInBackground(Void... params) {

        WebClient webClient = new WebClient();

        String postData = "{\"aluno_id\":\""+id_aluno+"\",\"aula_id\":\""+id_aula+"\"}";
        Log.d("teste", postData);

        resposta = webClient.post(WebClient.urlServidor + "aluno/presenca", postData, context, "DELETE");

        Log.d("USUARIO", resposta.getResponse());
        return resposta.getResponse();

    }


    @Override
    protected void onPostExecute(String s) {
        Log.d("USUARIO", s);
        metodoCallback.metodo(s);
    }
}


