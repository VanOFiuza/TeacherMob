package pi.br.com.teacher.provider;//package pi.example.com.myapplication.provider;
//
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.example.vanessafiuza.sgs_skytef_mobile.Activities.Login.LoginActivity;
//import com.example.vanessafiuza.sgs_skytef_mobile.Activities.Login.LoginHelper;
//import com.example.vanessafiuza.sgs_skytef_mobile.MenuActivity;
//import com.example.vanessafiuza.sgs_skytef_mobile.Activities.Login.SlapshScreenActivity;
//import com.example.vanessafiuza.sgs_skytef_mobile.Activities.Agenda.AdicionarEventoActivity;
//import com.example.vanessafiuza.sgs_skytef_mobile.NotificationActivity;
//import com.example.vanessafiuza.sgs_skytef_mobile.WebClient.Permissoes.Permissoes;
//import com.example.vanessafiuza.sgs_skytef_mobile.WebClient.WebClient;
//import com.example.vanessafiuza.sgs_skytef_mobile.dao.UsuarioDAO;
//import com.example.vanessafiuza.sgs_skytef_mobile.model.RespostasJson.RespostaPermissaoJson;
//import com.example.vanessafiuza.sgs_skytef_mobile.model.StatusResposta;
//import com.example.vanessafiuza.sgs_skytef_mobile.model.UsuarioRest;
//import com.google.gson.Gson;
//
//import pi.example.com.myapplication.model.UsuarioLogin;
//import pi.example.com.myapplication.dao.UsuarioDAO;
//
//
//public class WebClientAutentica extends AsyncTask<Void, Void, String> {
//
//    private Context context;
//    private ProgressDialog alertDialog;
//    String resposta,tipo;
//
//
//
//    public WebClientAutentica(Context context, String tipo ){
//
//        this.context = context;
//        this.tipo = tipo;
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//    }
//
//    @Override
//    protected String doInBackground(Void... params) {
//
//        WebClient webClient = new WebClient();
//
//        UsuarioDAO usuarioDAO =  new UsuarioDAO(context);
//        UsuarioLogin usuario = (UsuarioLogin) usuarioDAO.validarLogin();
//
//        Log.d("URL", WebClient.urlServidor + "usuarioAndroid/autenticar");
//
//        if (usuario !=null) {
//            resposta = webClient.post(WebClient.urlServidor + "usuarioAndroid/autenticar", usuario.toJson(), context, "POST");
//            return resposta;
//        }
//        else
//            return resposta = null;
//    }
//
//    @Override
//    protected void onPostExecute(String resposta) {
//
//
//        if(resposta==null){
//            Intent intent = new Intent(context, LoginActivity.class);
//
//            ((SlapshScreenActivity) context).finish();
//
//            }
//        }else {
//
//            if(resposta.equals("NÃO CONECTADO"))
//            {
//                Toast.makeText(context, "Dispositivo não conectado a uma rede", Toast.LENGTH_LONG).show();
//                return;
//            }
//            RespostaPermissaoJson resp = new Gson().fromJson(resposta, RespostaPermissaoJson.class);
//
//            Log.d("URL AUTENTICAR", resposta);
//            if (resp.getStatus().equals(StatusResposta.STATUS_OK)) {
//
//
//                if (resp.getRetorno().getPermissao().equals(Permissoes.GERENTE_CONTAS)) {
//                    Permissoes.nome_usuario = resp.getRetorno().getNome_usuario();
//                    Permissoes.permissao = Permissoes.GERENTE_CONTAS;
//                }
//                if (resp.getRetorno().getPermissao().equals(Permissoes.ADMINISTRADOR)) {
//                    Permissoes.nome_usuario = resp.getRetorno().getNome_usuario();
//                    Permissoes.permissao = Permissoes.ADMINISTRADOR;
//                }
//                if (tipo.equals("login")) {
//                    Intent intent = new Intent(context, MenuActivity.class);
//                    context.startActivity(intent);
//                    ((SlapshScreenActivity) context).finish();
//                }
//                else if (tipo.equals("alerta")){
//                    Intent intent = new Intent(context, AdicionarEventoActivity.class);
//                    context.startActivity(intent);
//                    ((NotificationActivity) context).finish();
//                }
//            } else {
//
//                Intent intent = new Intent(context, LoginActivity.class);
//                context.startActivity(intent);
//                if (tipo.equals("login"))
//                    ((SlapshScreenActivity) context).finish();
//                else if(tipo.equals("alerta")){
//                    ((NotificationActivity) context).finish();
//                }
//
//            }
//        }
//    }
//
//}
//
