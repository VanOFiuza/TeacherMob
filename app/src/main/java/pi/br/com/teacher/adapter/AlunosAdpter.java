package pi.br.com.teacher.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import pi.br.com.teacher.R;
import pi.br.com.teacher.activities.ProfessorChamadaActivity;
import pi.br.com.teacher.activities.ProfessorMenuActivity;
import pi.br.com.teacher.interfaces.MetodoCallback;
import pi.br.com.teacher.provider.AtribuirPresencaWebClient;
import pi.br.com.teacher.provider.DeletarPresencaWebClient;


public class AlunosAdpter  extends BaseAdapter {

    private Context context;
    private JsonArray lista;
    private String id_aula, data;

    public AlunosAdpter(Context context,JsonArray lista, String id_aula, String data ) {

        this.context = context;
        this.lista =lista;
        this.id_aula = id_aula;
        this.data = data;


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


        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lista_alunos_chamada,null);
        Gson gson = new Gson();
        JsonObject j = gson.fromJson(lista.get(position), JsonObject.class);

        TextView nomeAluno = (TextView) view.findViewById(R.id.txt_nome_aluno);
        TextView raAluno = (TextView) view.findViewById(R.id.txt_ra_aluno);
        CheckBox presenca = (CheckBox) view.findViewById(R.id.chb_presenca);

        nomeAluno.setText(j.get("nome").getAsString());
        raAluno.setText(j.get("ra").getAsString());

        presenca.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          if (isChecked){
                    new AtribuirPresencaWebClient(id_aula, j.get("ra").getAsString(), context, new MetodoCallback() {
                        @Override
                        public void metodo(Object obj) {
                          atualizaActivity();
                        }
                    }).execute();
               }else{
                   new DeletarPresencaWebClient(id_aula, j.get("ra").getAsString(), context, new MetodoCallback() {
                       @Override
                       public void metodo(Object obj) {
                           atualizaActivity();
                           Toast.makeText(context, "PRESENÇA RETIRADA", Toast.LENGTH_SHORT).show();
                       }
                   }).execute();
               }
            }
        });



        return view;

    }
    private void atualizaActivity (){

        Intent intent = new Intent(context, ProfessorChamadaActivity.class);
        intent.putExtra("data", data);
        intent.putExtra("id", id_aula);
        ((ProfessorChamadaActivity) context).finish();
        context.startActivity(intent);



    }
}
