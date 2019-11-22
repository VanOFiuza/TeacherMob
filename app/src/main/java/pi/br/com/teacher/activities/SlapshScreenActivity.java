package pi.br.com.teacher.activities;

import androidx.appcompat.app.AppCompatActivity;
import pi.br.com.teacher.R;
import pi.br.com.teacher.dao.UsuarioDAO;
import pi.br.com.teacher.interfaces.MetodoCallback;
import pi.br.com.teacher.model.UsuarioLogado;
import pi.br.com.teacher.model.UsuarioLogin;
import pi.br.com.teacher.provider.AutenticarWebClient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class SlapshScreenActivity extends AppCompatActivity {
    public static UsuarioLogin usuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slapsh_screen);

        new AutenticarWebClient(this, new MetodoCallback() {
            @Override
            public void metodo(Object obj) {
                if (obj == null) {

                    Log.d("NÃO SALVOU NADA ", "");

                    Intent intent = new Intent(SlapshScreenActivity.this, LoginActivity.class);
                    startActivity(intent);

                    finish();
                } else {
                    String resp = (String) obj;


                    Gson gson = new Gson();
                    JsonObject json = gson.fromJson(resp, JsonObject.class);
                    UsuarioLogin usuarioDao = new UsuarioDAO(SlapshScreenActivity.this).validarLogin();
                    UsuarioLogin usuarioLogin = new UsuarioLogin();


                    usuarioLogin.setTipo(usuarioDao.getTipo());
                    usuarioLogin.setUsuario(usuarioDao.getUsuario());


                    Log.d("URL AUTENTICAR", resp);
                    UsuarioLogado.usuarioLogin = usuarioLogin;

                    Log.d("URL USUARIO MODIFICADO", usuarioLogin.toString());

                    if (usuarioLogin.getTipo().equals("ALUNO")) {
                        usuarioLogin.setRa(json.get("ra").toString());
                        usuarioLogin.setId(json.get("turma_id").toString());
                        Intent intent = new Intent(SlapshScreenActivity.this, AlunoMenuACtivity.class);
                        startActivity(intent);

                        finish();
                    } else if (usuarioLogin.getTipo().equals("PROFESSOR")) {
                        usuarioLogin.setId(json.get("id").toString());
                        usuarioLogin.setRa(json.get("nome").toString());
                        Intent intent = new Intent(SlapshScreenActivity.this, ProfessorMenuActivity.class);
                        startActivity(intent);

                        finish();

                    } else {

                        Intent intent = new Intent(SlapshScreenActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

            }
        }).execute();

    }
}
