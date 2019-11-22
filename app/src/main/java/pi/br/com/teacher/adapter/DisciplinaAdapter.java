package pi.br.com.teacher.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import pi.br.com.teacher.R;
import pi.br.com.teacher.activities.PresencaActivity;
import pi.br.com.teacher.model.Aula;


public class DisciplinaAdapter extends BaseAdapter {

    private static final int CODE_PERMISSION_CAMERA = 12;
    private Context context;
    private JsonArray lista;

    public DisciplinaAdapter(JsonArray lista, Context context) {

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
        JsonElement item = lista.get(position);
        JsonObject j = gson.fromJson(item, JsonObject.class);
        JsonObject jsonObject = new JsonObject();

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lista_disciplinas_alunos, null);

        TextView txt_horario_disciplina = (TextView) view.findViewById(R.id.txt_horario_disciplina);
        TextView txt_dia_semana_disciplina = (TextView) view.findViewById(R.id.txt_dia_semana_disciplina);
        TextView txt_nome_disciplina = (TextView) view.findViewById(R.id.txt_nome_disciplina);
        ImageView btn_presenca_qrCode = view.findViewById(R.id.btn_presenca_qrCode);

        JsonArray aulas =  j.getAsJsonArray("aulas");
//        Aula aulaHoje = new Aula();
//        for (int i = 0 ; i< aulas.size();i++){
//            Aula aula = gson.fromJson( aulas.get(i), Aula.class);
//            if()
//        }

        btn_presenca_qrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PresencaActivity.class);
                intent.putExtra("aulas", String.valueOf(aulas));
                Log.d("AULA", aulas.get(0).toString());
                context.startActivity(intent);

            }
        });

        txt_dia_semana_disciplina.setText(j.get("diasDaSemana").toString().replaceAll("\"", ""));
        txt_horario_disciplina.setText(j.getAsJsonArray("aulas").get(0).getAsJsonObject()
                .get("horario").toString());
        txt_nome_disciplina.setText(j.get("disciplina").getAsJsonObject().get("nome").toString().replaceAll("\"", ""));


        return view;

    }
    private void checkPermission() {
        // Verifica necessidade de verificacao de permissao
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(context, "Não há permissão para utilizar a camera!", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{Manifest.permission.CAMERA},
                        CODE_PERMISSION_CAMERA);
            } else {
                // Solicita permissao
                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{Manifest.permission.CAMERA},
                        CODE_PERMISSION_CAMERA);
            }
        }
    }
}
