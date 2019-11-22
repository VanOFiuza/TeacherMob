package pi.br.com.teacher.provider;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import pi.br.com.teacher.interfaces.MetodoCallback;
import pi.br.com.teacher.model.Resposta;
import pi.br.com.teacher.model.UsuarioLogado;

public class CarregarAlunosChamadaWebClient extends AsyncTask<Void, Void, String> {

    private Context context;
    private ProgressDialog alertDialog;
    private MetodoCallback metodoCallback;

    Resposta resposta;
    private String data;
    private String id;

    public Context getContext() {
        return context;
    }


    public CarregarAlunosChamadaWebClient(Context context, String data, String id, MetodoCallback metodoCallback) {

        this.context = context;
        this.metodoCallback = metodoCallback;
        this.data = data;
        this.id = id;



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


        resposta = webClient.post(WebClient.urlServidor + "disciplinaTurma/alunos/"+id+"/?data="+data
                , null, context, "GET");
        Log.d("REQUISIÇÃO: ", resposta.getResponse() +", STATUSCODE : "+ resposta.getStatusCode());


        return resposta.getResponse();

    }

    @Override
    protected void onPostExecute(String s) {

        System.out.println("retorno"+s);


        metodoCallback.metodo(s);




    }

}

