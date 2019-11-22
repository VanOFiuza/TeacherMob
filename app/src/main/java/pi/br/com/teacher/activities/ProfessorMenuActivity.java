package pi.br.com.teacher.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment.OnDateSetListener;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import pi.br.com.teacher.R;
import pi.br.com.teacher.adapter.TurmaAdapter;
import pi.br.com.teacher.interfaces.MetodoCallback;
import pi.br.com.teacher.model.RespostaTurmaJson;
import pi.br.com.teacher.provider.CarregarAlunosChamadaWebClient;
import pi.br.com.teacher.provider.ListarDisciplinasProfessorWebClient;


public class ProfessorMenuActivity extends AppCompatActivity implements OnDateSetListener {

    private ListView list_turmas;
    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";
    private CalendarDatePickerDialogFragment cdp;
    private Dialog dialog_tipo_chamdada;
    private TextView teste;
    private RespostaTurmaJson disciplinaSelecionada;

    //DIALOG

    private LinearLayout ll_qr_code, ll_manual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_menu);

        list_turmas = (ListView) findViewById(R.id.list_turmas);
        new ListarDisciplinasProfessorWebClient(ProfessorMenuActivity.this, new MetodoCallback() {
            @Override
            public void metodo(Object obj) {
                String resp = (String) obj;
                Gson gson = new Gson();
                JsonArray j = gson.fromJson(resp, JsonArray.class);
                Log.d("LOG", resp);


                TurmaAdapter adapter = new TurmaAdapter(j, ProfessorMenuActivity.this);
                list_turmas.setAdapter(adapter);
            }
        }).execute();



        teste = (TextView) findViewById(R.id.teste);

        teste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdp = new CalendarDatePickerDialogFragment()
                        .setOnDateSetListener(ProfessorMenuActivity.this);
                cdp.show(getSupportFragmentManager(), FRAG_TAG_DATE_PICKER);
            }
        });








        list_turmas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(ProfessorMenuActivity.this, ProfessorChamadaActivity.class);
//                startActivity(intent);
//                cdp = new CalendarDatePickerDialogFragment()
//                        .setOnDateSetListener(ProfessorMenuActivity.this);
//                cdp.show(getSupportFragmentManager(), FRAG_TAG_DATE_PICKER);

                 disciplinaSelecionada = (RespostaTurmaJson) list_turmas.getItemAtPosition(position);
                cdp = new CalendarDatePickerDialogFragment()
                        .setOnDateSetListener(ProfessorMenuActivity.this);
                cdp.show(getSupportFragmentManager(), FRAG_TAG_DATE_PICKER);



            }
        });


    }
    private void dialog_seleciona_tipo_chamada(){

        dialog_tipo_chamdada  = new Dialog(this);
        dialog_tipo_chamdada.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_tipo_chamdada.setContentView(R.layout.dialog_tipo_chamada);
        dialog_tipo_chamdada.setTitle("My Custom Dialog");

        ll_manual   = (LinearLayout) dialog_tipo_chamdada.findViewById(R.id.ll_manual);
        ll_qr_code  = (LinearLayout) dialog_tipo_chamdada.findViewById(R.id.ll_qr_code);

        ll_qr_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfessorMenuActivity.this, ProfessorChamadaActivity.class);
                startActivity(intent);
            }
        });

        ll_manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfessorMenuActivity.this, ProfessorChamadaActivity.class);
                startActivity(intent);
            }
        });

        dialog_tipo_chamdada.show();
    }


    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int ano, int mes, int dia) {

        new CarregarAlunosChamadaWebClient(this, "" + ano + "" + mes + "" + dia, String.valueOf(disciplinaSelecionada.getDisciplina().getId()),
                new MetodoCallback() {
                    @Override
                    public void metodo(Object obj) {
                        String resp = (String) obj;
                        Gson gson = new Gson();
                        JsonArray j = gson.fromJson(resp, JsonArray.class);
                        Log.d("LOG", resp);
                    }
                }).execute();

    }

}
