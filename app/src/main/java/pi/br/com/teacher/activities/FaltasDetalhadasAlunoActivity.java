package pi.br.com.teacher.activities;

import android.os.Bundle;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import pi.br.com.teacher.R;


public class FaltasDetalhadasAlunoActivity extends AppCompatActivity {

    private TextView txt_qtde_presenca_aluno, txt_qtde_ausencia_aluno, txt_qtd_total_aulas_aluno_disciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faltas_detalhadas_aluno);


        String presenca = getIntent().getExtras().getString("presenca");
        String ausencia = getIntent().getExtras().getString("ausencia");
        String total = getIntent().getExtras().getString("total");

        txt_qtde_presenca_aluno = (TextView) findViewById(R.id.txt_qtde_presenca_aluno);
        txt_qtde_ausencia_aluno = (TextView) findViewById(R.id.txt_qtde_ausencia_aluno);
        txt_qtd_total_aulas_aluno_disciplina = (TextView) findViewById(R.id.txt_qtd_total_aulas_aluno_disciplina);

        txt_qtde_ausencia_aluno.setText(ausencia.toString());
        txt_qtde_presenca_aluno.setText(presenca.toString());
        txt_qtd_total_aulas_aluno_disciplina.setText(total.toString());




    }
}
