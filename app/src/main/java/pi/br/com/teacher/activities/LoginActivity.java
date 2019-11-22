package pi.br.com.teacher.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import pi.br.com.teacher.R;
import pi.br.com.teacher.dao.UsuarioDAO;
import pi.br.com.teacher.interfaces.MetodoCallback;
import pi.br.com.teacher.model.UsuarioLogado;
import pi.br.com.teacher.model.UsuarioLogin;
import pi.br.com.teacher.provider.LoginWebClient;


public class LoginActivity extends AppCompatActivity {

    public Spinner tipo_usuario;
    public String str_tipo_usuario;
    private Button btnLogin;
    public static UsuarioLogin usuarioLogin = new UsuarioLogin();

    private static final int RC_SIGN_IN = 100;
    SignInButton btn_login_rede_social;
    private FirebaseAuth mAuth;

    GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btnLogin = (Button) findViewById(R.id.btnLogin);

        tipo_usuario = (Spinner) findViewById(R.id.spn_tipo_usuario);


        final EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
        final EditText txtSenha = (EditText) findViewById(R.id.txtSenha);

        mAuth = FirebaseAuth.getInstance();
        btn_login_rede_social = findViewById(R.id.login);


        btn_login_rede_social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
        List<String> lista = new ArrayList<>();
        lista.add("ALUNO");
        lista.add("PROFESSOR");
        lista.add("ADMINISTRADOR");

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        try {
            final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(

                    this, R.layout.support_simple_spinner_dropdown_item, lista) {

                @Override
                public View getDropDownView(int position, View convertView,
                                            ViewGroup parent) {
                    View view = super.getDropDownView(position, convertView, parent);
                    TextView tv = (TextView) view;

                    if (position % 2 == 1) {
                        // Set the item background color
                        tv.setBackgroundColor(Color.parseColor("#f2f2f2"));
                    } else {
                        // Set the alternate item background color
                        tv.setBackgroundColor(Color.parseColor("#cccccc"));
                    }
                    return view;
                }

            };
            spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            tipo_usuario.setAdapter(spinnerArrayAdapter);

        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(this, "ERRO: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        tipo_usuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedItemText = (String) parent.getItemAtPosition(position);
                str_tipo_usuario = selectedItemText;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarioLogin.setUsuario(txtEmail.getText().toString());
                usuarioLogin.setSenha(txtSenha.getText().toString());

                new LoginWebClient(LoginActivity.this, usuarioLogin, str_tipo_usuario, new MetodoCallback() {
                    @Override
                    public void metodo(Object obj) {
                        String resp = (String) obj;
                        Gson gson = new Gson();
                        JsonObject json = gson.fromJson(resp, JsonObject.class);
                        UsuarioDAO dao = new UsuarioDAO(LoginActivity.this);

                        if (str_tipo_usuario.equals("ALUNO")) {

                            usuarioLogin.setId(json.get("turma_id").toString());
                            usuarioLogin.setRa(json.get("ra").toString());
                            usuarioLogin.setTipo(str_tipo_usuario);
                            dao.inserir(usuarioLogin);
                            Intent intent = new Intent(LoginActivity.this, AlunoMenuACtivity.class);
                            startActivity(intent);

                            UsuarioLogado.usuarioLogin = usuarioLogin;

                            finish();

                        }
                        if (str_tipo_usuario.equals("PROFESSOR")) {

                            usuarioLogin.setId(json.get("id").toString());
                            usuarioLogin.setTipo(str_tipo_usuario);
                            UsuarioLogado.usuarioLogin = usuarioLogin;
                            dao.inserir(usuarioLogin);
                            Intent intent = new Intent(LoginActivity.this, ProfessorMenuActivity.class);
                            startActivity(intent);

                            finish();
                        }
                    }
                }).execute();


            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e);
                Toast.makeText(this, "FALIED..." + e.getMessage(), Toast.LENGTH_SHORT).show();
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("TAG", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "FOII OLHA O USER" + user.getEmail(), Toast.LENGTH_SHORT).show();
                            Log.d("TAG VAIIIII", " " + user.toString());
                            usuarioLogin.setUsuario(user.getEmail());
                            usuarioLogin.setSenha(user.getUid());

                            new LoginWebClient(LoginActivity.this, usuarioLogin, str_tipo_usuario, new MetodoCallback() {
                                @Override
                                public void metodo(Object obj) {
                                    String resp = (String) obj;
                                    Gson gson = new Gson();
                                    JsonObject statusAula = gson.fromJson(resp, JsonObject.class);


                                    if (str_tipo_usuario.equals("ALUNO")) {
                                        usuarioLogin.setId(statusAula.get("turma_id").toString());
                                        usuarioLogin.setRa(statusAula.get("ra").toString());
                                        usuarioLogin.setTipo(str_tipo_usuario);
                                        ;
                                        Intent intent = new Intent(LoginActivity.this, AlunoMenuACtivity.class);
                                        startActivity(intent);

                                        finish();
                                    }
                                }
                            }).execute();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());

                            //  updateUI(null);
                        }
                        ;

                        // ...
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "Não foi " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
