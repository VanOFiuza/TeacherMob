package pi.br.com.teacher.provider;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import pi.br.com.teacher.interfaces.MetodoCallback;
import pi.br.com.teacher.model.Resposta;
import pi.br.com.teacher.model.UsuarioLogado;


public class PainelPresencaAlunoWebClient extends AsyncTask<Void, Void, String> {

    private Context context;
    private ProgressDialog alertDialog;
    private MetodoCallback metodoCallback;

    Resposta resposta;
    String id_disciplina;


    public Context getContext() {
        return context;
    }


    public PainelPresencaAlunoWebClient(Context context, String id_disciplina, MetodoCallback metodoCallback) {

        this.context = context;
        this.metodoCallback = metodoCallback;
        this.id_disciplina = id_disciplina;


    }

    @Override
    protected void onPreExecute() {

//        alertDialog = ProgressDialog.show(context,"Aguarde" , "Enviando para o servidor ...", true, true);
//        alertDialog.show();
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {


        WebClient webClient = new WebClient();


        resposta = webClient.post(WebClient.urlServidor + "aluno/presencaDisciplina/"+ UsuarioLogado.usuarioLogin.getRa() +"?disciplinaTurmaId="+id_disciplina, null, context, "GET");
        Log.d("REQUISIÇÃO: ", resposta.getResponse() +", STATUSCODE : "+ resposta.getStatusCode());


        return resposta.getResponse();


    }

    @Override
    protected void onPostExecute(String s) {



        metodoCallback.metodo(s);


    }


}