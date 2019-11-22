package pi.br.com.teacher.provider;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import pi.br.com.teacher.interfaces.MetodoCallback;
import pi.br.com.teacher.model.Resposta;


public class ListarDisciplinasProfessorWebClient extends AsyncTask<Void, Void, String> {

    private Context context;
    private ProgressDialog alertDialog;
    private MetodoCallback metodoCallback;

    Resposta resposta;


    public Context getContext() {
        return context;
    }


    public ListarDisciplinasProfessorWebClient(Context context, MetodoCallback metodoCallback) {

        this.context = context;
        this.metodoCallback = metodoCallback;


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


        resposta = webClient.post(WebClient.urlServidor + "/disciplinasProfessor/1" , null, context, "GET");
        Log.d("REQUISIÇÃO: ", resposta.getResponse() +", STATUSCODE : "+ resposta.getStatusCode());


        return resposta.getResponse();


    }

    @Override
    protected void onPostExecute(String s) {

        System.out.println("retorno" + s);


        metodoCallback.metodo(s);


    }

}


