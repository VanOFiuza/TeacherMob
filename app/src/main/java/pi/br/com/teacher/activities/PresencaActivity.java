package pi.br.com.teacher.activities;

import androidx.appcompat.app.AppCompatActivity;
import pi.br.com.teacher.R;
import pi.br.com.teacher.interfaces.MetodoCallback;
import pi.br.com.teacher.model.Aula;
import pi.br.com.teacher.provider.AtribuirPresencaWebClient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class PresencaActivity extends AppCompatActivity implements View.OnClickListener {

    //View Objects
    private Button buttonScan;
    private TextView textViewName, textViewAddress;
    private JsonArray j = new JsonArray();
    private Gson gson = new Gson();
    //qr code scanner object
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presenca);

        String aulas = getIntent().getExtras().getString("aulas");
        JsonArray j = gson.fromJson(aulas, JsonArray.class);
        Aula aulaHoje = new Aula();


        //View objects
        buttonScan = (Button) findViewById(R.id.buttonScan);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewAddress = (TextView) findViewById(R.id.textViewAddress);

        //intializing scan object
        qrScan = new IntentIntegrator(this);

        //attaching onclick listener
        qrScan.initiateScan();
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());

                    Aula aulaHoje = gson.fromJson(result.getContents(), Aula.class);
                    //setting values to textviews
                    for (int i = 0; i < j.size(); i++) {

                        Aula aula = gson.fromJson(j.get(i), Aula.class);
                        Log.d("RETORNO",aulaHoje.getData() +"a de hoje "+ aula.getData()+"AULA DA LISTA ");
                        if (aula.getId() == aulaHoje.getId()) {
                            new AtribuirPresencaWebClient(String.valueOf(aula.getId()), PresencaActivity.this, new MetodoCallback() {
                                @Override
                                public void metodo(Object obj) {
                                    Log.d("RETORNO", obj.toString());
                                    Toast.makeText(PresencaActivity.this, "Presença atribuida com sucesso aqui lucas", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }).execute();

                        } else {
                            Toast.makeText(this, "não é a aula de hoje " + obj.getString("id"), Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View view) {
        //initiating the qr code scan

    }
}
