package pi.br.com.teacher.provider;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import pi.br.com.teacher.model.Resposta;

public class WebClient {

    static java.net.CookieManager msCookieManager = new java.net.CookieManager();

    public static String urlServidor = "http://192.168.10.94:8081/api/";

    private HttpURLConnection connection;

    public Resposta post(String strUrl, String json, Context context, String method) {
        Resposta resposta = new Resposta();
        if (!isConected(context)) {
            resposta.setResponse("NÃO CONECTADO");
            return resposta;
        }
     try {



            URL url = new URL(strUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            Log.d("URL", strUrl);

            if (msCookieManager.getCookieStore().getCookies().size() > 0) {
                // While joining the Cookies, use ',' or ';' as needed. Most of the servers are using ';'
                connection.setRequestProperty("Cookie",
                        TextUtils.join(";", msCookieManager.getCookieStore().getCookies()));
            }

            connection.setRequestMethod(method);

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-type", "application/json");

            connection.setDoInput(true);

            if (json != null) {
                connection.setDoOutput(true);
                PrintStream saida = new PrintStream(connection.getOutputStream());
                saida.println(json);
            }
            connection.connect();

            connection.getErrorStream();


            InputStream inputStream = connection.getInputStream();
            System.out.print(connection.getErrorStream());

            if (inputStream != null) {

                String respostaReq = "";

                String linha = "";
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

                while ((linha = br.readLine()) != null) {
                    respostaReq += linha;
                }




                Map<String, List<String>> headerFields = connection.getHeaderFields();
                List<String> cookiesHeader = headerFields.get("Set-Cookie");

                if (cookiesHeader != null) {
                    for (String cookie : cookiesHeader) {
                        msCookieManager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
                    }
                }


                resposta.setStatusCode(connection.getResponseCode());
                resposta.setResponse(respostaReq);
                Log.d("USUARIO", resposta.getResponse());
                return resposta;
            } else {
                Log.d("USUARIO", resposta.getResponse());
                return resposta;

            }

     }catch (Exception e){

     }

        Log.d("USUARIO", resposta.toString());
        return resposta;
    }

    public static boolean isConected(Context cont) {
        ConnectivityManager conmag = (ConnectivityManager) cont.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conmag != null) {
            conmag.getActiveNetworkInfo();

            //Verifica internet pela WIFI
            if (conmag.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {

                return true;
            }
            //Verifica se tem internet móvel
            if (conmag.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {

                return true;
            }
        }

        return false;
    }

}