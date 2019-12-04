package pi.br.com.teacher.activities;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import pi.br.com.teacher.R;
import pi.br.com.teacher.adapter.DisciplinaAdapter;
import pi.br.com.teacher.interfaces.MetodoCallback;
import pi.br.com.teacher.model.UsuarioLogado;
import pi.br.com.teacher.provider.ListarDisciplinaAlunoWebClient;
import pi.br.com.teacher.provider.PainelPresencaAlunoWebClient;


public class AlunoMenuACtivity extends AppCompatActivity {

    public ListView list_disciplinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_menu_activity);



        list_disciplinas = (ListView) findViewById(R.id.list_disciplinas);



        JsonObject evento = (JsonObject) list_disciplinas.getItemAtPosition(1);



        List<String> lista = new ArrayList<>();
        for (int i = 0; i <= 15; i++) {
            lista.add("BATATA");
        }
        new ListarDisciplinaAlunoWebClient(AlunoMenuACtivity.this, new MetodoCallback() {
            @Override
            public void metodo(Object obj) {
                String resp = (String) obj;
                Gson gson = new Gson();
                JsonArray j = gson.fromJson(resp, JsonArray.class);
                Log.d("teste", j.get(0).toString());


                DisciplinaAdapter adapter = new DisciplinaAdapter(j, AlunoMenuACtivity.this);
                list_disciplinas.setAdapter(adapter);
            }
        }).execute();

        list_disciplinas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JsonObject jsonObject = (JsonObject) list_disciplinas.getItemAtPosition(position);

                String id_turma_disciplina = jsonObject.getAsJsonObject("disciplina").get("id").getAsString();
                Log.d("DISCIPLINA SELECIONADA", id_turma_disciplina);

                new PainelPresencaAlunoWebClient(AlunoMenuACtivity.this, id_turma_disciplina , new MetodoCallback() {
                    @Override
                    public void metodo(Object obj) {



                        String resp = (String) obj;
                        Gson gson = new Gson();
                        JsonObject statusAula = gson.fromJson(resp, JsonObject.class);
                        Intent intent = new Intent(AlunoMenuACtivity.this, FaltasDetalhadasAlunoActivity.class);
                        intent.putExtra("presenca",statusAula.get("presentes").toString());
                        intent.putExtra("ausencia",statusAula.get("ausentes").toString());
                        intent.putExtra("total",statusAula.get("aulasTotais").toString());
                        startActivity(intent);

                    }
                }).execute();

            }
        });


    }


}
