package resistance.jaequeima;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ParceJson extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parce_json);


            new MyTask().execute();


    }

private class MyTask extends AsyncTask<String, String, String> {

        HttpURLConnection urlConnection;



        @Override
        protected String doInBackground(String... args) {

                StringBuilder result = new StringBuilder();

                try {
                        URL url = new URL("http://resistance.pt/jaequeima/data.php");
                        urlConnection = (HttpURLConnection) url.openConnection();
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                        String line;

                        while ((line = reader.readLine()) != null) {
                                result.append(line);
                        }

                }catch( Exception e) {
                        e.printStackTrace();
                }
                finally {
                        urlConnection.disconnect();
                }

                return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {


                    try {
                        JSONObject object = new JSONObject(result);
                        JSONArray queima  = object.getJSONArray("jaequeima");

                        String cenas = "";
                        for (int i = 0; i < queima.length(); i++){

                            JSONObject noites = queima.getJSONObject(i);
                            Log.d("json ",
                                    " Cantor: " + noites.get("inf").toString() +
                                    " Dia: " + noites.get("day").toString() +
                                    " Imagem: http://resistance.pt/jaequeima/assets/img/bandas/" + noites.get("id").toString() +".jpg\n" );

                            cenas+="<img width='60' height='60' src='http://resistance.pt/jaequeima/assets/img/bandas/" + noites.get("id").toString() +".jpg'>" +
                                    " <b>" + noites.get("inf").toString() + "</b>" +
                                    " a " + noites.get("day").toString() + " de Maio<br><br>";

                        }
                        WebView webview = (WebView) ParceJson.this.findViewById(R.id.webview);

                        webview.loadData(cenas, "text/html; charset=utf-8", "utf-8");



                } catch (JSONException e) {
                        e.printStackTrace();
                }


        }

    }
}