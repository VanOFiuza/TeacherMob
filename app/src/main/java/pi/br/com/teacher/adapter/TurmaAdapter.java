package pi.br.com.teacher.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

import pi.br.com.teacher.R;
import pi.br.com.teacher.model.Aula;
import pi.br.com.teacher.model.Resposta;
import pi.br.com.teacher.model.RespostaTurmaJson;


public class TurmaAdapter  extends BaseAdapter {

    private Context context;
    private JsonArray lista;

    public TurmaAdapter(JsonArray lista, Context context) {

        this.context = context;
        this.lista = lista;


    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Gson gson = new Gson();

        JsonObject j = gson.fromJson(lista.get(position), JsonObject.class);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lista_turmas, null);



        TextView cursoTurmaProfessor = (TextView) view.findViewById(R.id.cursoTurmaProfessor);
        TextView txt_horario_aula_turma = (TextView) view.findViewById(R.id.txt_horario_aula_turma);
        TextView txtDiadasemanaTurmaProfessor = (TextView) view.findViewById(R.id.txtDiadasemanaTurmaProfessor);
        TextView txt_sala_professor_turma = (TextView) view.findViewById(R.id.txt_sala_professor_turma);
        TextView txt_unidade_turma_professor = (TextView) view.findViewById(R.id.txt_unidade_turma_professor);

        RespostaTurmaJson turma = gson.fromJson(lista.get(position), RespostaTurmaJson.class);

        Aula aula =  (Aula) turma.getDisciplina().getAulas().get(0);

        txtDiadasemanaTurmaProfessor.setText(turma.getDisciplina().getDiasDaSemana());
        txt_horario_aula_turma.setText(aula.getHorario());
        cursoTurmaProfessor.setText(j.get("disciplina").getAsJsonObject().get("disciplina").getAsJsonObject().get("nome").getAsString().toUpperCase()+" "+ turma.getTurma().toUpperCase());
        txt_unidade_turma_professor.setText(turma.getDisciplina().getUnidade());




        return view;

    }
}
