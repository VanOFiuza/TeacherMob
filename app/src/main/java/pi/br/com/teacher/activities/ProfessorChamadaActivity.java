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
import pi.br.com.teacher.R;
import pi.br.com.teacher.adapter.AlunosAdpter;

public class ProfessorChamadaActivity extends AppCompatActivity {

    private ListView list_alunos;
    private ImageView btn_salva_chamada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_chamada);


        String data = getIntent().getExtras().getString("data");

        Log.d("DATAAAAA",data);

        list_alunos = (ListView) findViewById(R.id.list_alunos);
        btn_salva_chamada = (ImageView) findViewById(R.id.btn_salva_chamada);
        List<String> lista = new ArrayList<>();
        for(int i = 0 ; i<= 15 ; i++){
            lista.add("BATATA");
        }

        AlunosAdpter adapter = new AlunosAdpter(this, lista);
        list_alunos.setAdapter(adapter);
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
