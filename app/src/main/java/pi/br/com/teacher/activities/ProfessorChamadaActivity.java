package pi.br.com.teacher.activities;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import pi.br.com.teacher.R;
import pi.br.com.teacher.adapter.AlunosAdpter;
import pi.br.com.teacher.interfaces.MetodoCallback;
import pi.br.com.teacher.model.Professor;
import pi.br.com.teacher.provider.CarregarAlunosChamadaWebClient;

public class ProfessorChamadaActivity extends AppCompatActivity {

    private ListView list_alunos;
    private ImageView btn_salva_chamada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_chamada);


        String data = getIntent().getExtras().getString("data");
        String id = getIntent().getExtras().getString("id");

        Log.d("DATAAAAA",data);

        list_alunos = (ListView) findViewById(R.id.list_alunos);
        btn_salva_chamada = (ImageView) findViewById(R.id.btn_salva_chamada);

        new CarregarAlunosChamadaWebClient(this, data, id, new MetodoCallback() {
            @Override
            public void metodo(Object obj) {
                String resp = (String) obj;
                Gson gson = new Gson();
                JsonArray lista = gson.fromJson(resp, JsonArray.class);
                AlunosAdpter adapter = new AlunosAdpter(ProfessorChamadaActivity.this, lista, id);
                list_alunos.setAdapter(adapter);
            }
        }).execute();


        btn_salva_chamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfessorChamadaActivity.this, ProfessorMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
