package resistance.jaequeima;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static resistance.jaequeima.R.id.tabHost;

public class DaysActivity extends AppCompatActivity {

    private TextView tvDay, tvHour, tvMinute, tvSecond, txtTvDay, txtTvHour, txtTvMinute, txtTvSecond, tvEvent, tvEvent2;
    private ImageButton logoResistance;
    private LinearLayout linearLayout2;
    private Handler handler;
    private Runnable runnable;
    private Button btnDias;
    private int startDay;
    private TabHost host;
    private List<String> day1, day2, day3, day4, day5, day6, day7, day8;

    //detect swipe
    private float x1,x2;
    static final int MIN_DISTANCE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);

        startDay = 5;

        initUI();
        countDownStart();
        listaArtistas();

        logoResistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.resistance.pt");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        host = (TabHost)findViewById(tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("5");
        spec.setContent(R.id.tab1);
        spec.setIndicator("5");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("6");
        spec.setContent(R.id.tab2);
        spec.setIndicator("6");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("7");
        spec.setContent(R.id.tab3);
        spec.setIndicator("7");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("8");
        spec.setContent(R.id.tab4);
        spec.setIndicator("8");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("9");
        spec.setContent(R.id.tab5);
        spec.setIndicator("9");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("10");
        spec.setContent(R.id.tab6);
        spec.setIndicator("10");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("11");
        spec.setContent(R.id.tab7);
        spec.setIndicator("11");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("12");
        spec.setContent(R.id.tab8);
        spec.setIndicator("12");
        host.addTab(spec);

        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        LinearLayout.LayoutParams currentLayout;
        ArrayAdapter<String> arrayAdapter;
        List[] days = new List[] {day1, day2, day3, day4, day5, day6, day7, day8};
        ListView[] listViewsID = new ListView[] {(ListView)findViewById(R.id.listView1), (ListView)findViewById(R.id.listView2),(ListView)findViewById(R.id.listView3),(ListView)findViewById(R.id.listView4),(ListView)findViewById(R.id.listView5),(ListView)findViewById(R.id.listView6),(ListView)findViewById(R.id.listView7),(ListView)findViewById(R.id.listView8)};

        String day = (String) DateFormat.format("dd", new Date());

        if(day.equals("6"))
            host.setCurrentTab(1);
        else if(day.equals("7"))
            host.setCurrentTab(2);
        else if(day.equals("8"))
            host.setCurrentTab(3);
        else if(day.equals("9"))
            host.setCurrentTab(4);
        else if(day.equals("10"))
            host.setCurrentTab(5);
        else if(day.equals("11"))
            host.setCurrentTab(6);
        else if(day.equals("12"))
            host.setCurrentTab(7);

        for (int i = 0; i < host.getTabWidget().getChildCount(); ++i)
        {

            if((i+startDay) < 10){
                host.getTabWidget().getChildAt(i).setLayoutParams(new LinearLayout.LayoutParams((width/8)-2,75));
            }
            else{
                host.getTabWidget().getChildAt(i).setLayoutParams(new LinearLayout.LayoutParams((width/8)+2,75));
            }

            TextView tv = (TextView) host.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            //tv.setShadowLayer(5,2,2,255);

            tv.setShadowLayer(5, 2, 2, android.R.color.holo_green_dark);

            if (i == 5 || i == 6 || i == 7)
                tv.setTextSize(12);

            Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Oswald_Bold.ttf");
            tv.setTypeface(tf,Typeface.BOLD);

            arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_artists, days[i]) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView textView = (TextView) view.findViewById(android.R.id.text1);

                    Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Oswald_Bold.ttf");
                    textView.setTypeface(tf,Typeface.BOLD);

                    System.out.println(textView.getText().toString());

                    if ( textView.getText().toString().equals("JAMES ARTUR") ||
                         textView.getText().toString().equals("DIOGO PIÇARRA") ||
                         textView.getText().toString().equals("DAVID CARREIRA") ||
                         textView.getText().toString().equals("QUIM BARREIROS") ||
                         textView.getText().toString().equals("Piruka") ||
                         textView.getText().toString().equals("RICHIE CAMPBELL") ||
                         textView.getText().toString().equals("NICKY ROMERO") ||
                         textView.getText().toString().equals("VIRGUL") ||
                         textView.getText().toString().equals("Mishlawi") ||
                         textView.getText().toString().equals("KAISER CHIEFS") ||
                         textView.getText().toString().equals("Flying Cages") ||
                         textView.getText().toString().equals("DJ Ride") ||
                         textView.getText().toString().equals("KAISER CHIEFS") )
                        textView.setTextSize(30);


                    return view;
                }
            };

            listViewsID[i].setAdapter(arrayAdapter);
            listViewsID[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return onTouchEvent(event);
                }
            });
        }

        //new MyTask().execute();

    }

    @SuppressLint("SimpleDateFormat")
    private void initUI() {
        linearLayout2 = (LinearLayout) findViewById(R.id.ll2);

        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Oswald_Bold.ttf");

        tvDay = (TextView) findViewById(R.id.txtTimerDay);
        tvDay.setTypeface(type);
        tvHour = (TextView) findViewById(R.id.txtTimerHour);
        tvHour.setTypeface(type);
        tvMinute = (TextView) findViewById(R.id.txtTimerMinute);
        tvMinute.setTypeface(type);
        tvSecond = (TextView) findViewById(R.id.txtTimerSecond);
        tvSecond.setTypeface(type);

        type = Typeface.createFromAsset(getAssets(),"fonts/Oswald_Light.ttf");

        txtTvDay = (TextView) findViewById(R.id.txt_TimerDay);
        txtTvDay.setTypeface(type);
        txtTvHour = (TextView) findViewById(R.id.txt_TimerHour);
        txtTvHour.setTypeface(type);
        txtTvMinute = (TextView) findViewById(R.id.txt_TimerMinute);
        txtTvMinute.setTypeface(type);
        txtTvSecond = (TextView) findViewById(R.id.txt_TimerSecond);
        txtTvSecond.setTypeface(type);

        logoResistance = (ImageButton) findViewById(R.id.imageButton);

        btnDias = (Button) findViewById(R.id.button);

        tvEvent = (TextView) findViewById(R.id.textView);
        tvEvent2 = (TextView) findViewById(R.id.textView2);
    }

    public void countDownStart() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                    Date eventDate = dateFormat.parse("2017-05-05 21:00:00");
                    Date currentDate = new Date();
                    if (!currentDate.after(eventDate)) {
                        long diff = eventDate.getTime() - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;

                        tvDay.setText(String.format("%02d", days));
                        tvHour.setText(String.format("%02d", hours));
                        tvMinute.setText(String.format("%02d", minutes));
                        tvSecond.setText(String.format("%02d", seconds));

                    } else {
                        linearLayout2.setVisibility(View.GONE);
                        tvEvent2.setVisibility(View.GONE);
                        tvEvent.setText("JÁ É QUEIMA!");
                        tvEvent.setPadding(0,10,0,0);

                        handler.removeCallbacks(runnable);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }

    public void listaArtistas(){
        day1 = Arrays.asList("TFMUC","You Can't Win Charlie Brown","JAMES ARTUR","TMUC",""," Estudante - 10€","Não Estudante - 15€");
        day2 = Arrays.asList("Orquestra Típica e Rancho","DIOGO PIÇARRA","DAVID CARREIRA","Estudantina Universitária de Coimbra",""," Estudante - 8€","Não Estudante - 13€");
        day3 = Arrays.asList("Quantunna","Miguel Azevedo", "HI-FI","QUIM BARREIROS","FAN-Farra",""," Estudante - 5€","Não Estudante - 8€");
        day4 = Arrays.asList("Mondeguinas","Piruka", "MUNDO SEGUNDO & SAM THE KID","Estudantina Feminina de Coimbra",""," Estudante - 6€","Não Estudante - 9€");
        day5 = Arrays.asList("Phartuna","Mishlawi", "RICHIE CAMPBELL","Imperial TAFFUC",""," Estudante - 8€","Não Estudante - 13€");
        day6 = Arrays.asList("Reyc Gi","DJ Kwan", "NICKY ROMERO","Coral Quecofónico do Cifrão",""," Estudante - 9€","Não Estudante - 14€");
        day7 = Arrays.asList("Desconcertuna","VIRGUL", "DJ Ride","Orxestra Pitagórica",""," Estudante - 7€","Não Estudante - 12€");
        day8 = Arrays.asList("As FANS","Flying Cages", "KAISER CHIEFS","Grupo de Cordas","Grupo de Fado",""," Estudante - 10€","Não Estudante - 15€");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_left_int,R.anim.right_left_out);
    }

    /*private class MyTask extends AsyncTask<String, String, String> {

        HttpURLConnection urlConnection;
        String finalResult;


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

                finalResult = "";
                int diaQueima;
                int[] webViewIDs = new int[] {R.id.webview1, R.id.webview2, R.id.webview3, R.id.webview4, R.id.webview5, R.id.webview6, R.id.webview7, R.id.webview8};
                for (int i = 0; i < queima.length(); i++){

                    JSONObject noites = queima.getJSONObject(i);
                    Log.d("json ",
                            " Cantor: " + noites.get("inf").toString() +
                                    " Dia: " + noites.get("day").toString() +
                                    " Imagem: http://resistance.pt/jaequeima/assets/img/bandas/" + noites.get("id").toString() +".jpg\n" );

                    finalResult = "<center style=\"margin-top: 20px\"><img height='150' src='http://resistance.pt/jaequeima/assets/img/bandas/" + noites.get("id").toString() +".jpg'>" +
                            "<br><p><b>" + noites.get("inf").toString() + "</b></p><br></center>";

                    diaQueima = Integer.parseInt(noites.get("day").toString()) - startDay;

                    WebView webview = (WebView) DaysActivity.this.findViewById(webViewIDs[diaQueima]);
                    webview.loadData(finalResult, "text/html; charset=utf-8", "utf-8");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }*/

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    // Left to Right swipe action
                    if (x2 > x1)
                    {
                        //Toast.makeText(this, "Left to Right swipe [Next]", Toast.LENGTH_SHORT).show ();
                        switchTabs(false);
                    }

                    // Right to left swipe action
                    else
                    {
                        //Toast.makeText(this, "Right to Left swipe [Previous]", Toast.LENGTH_SHORT).show ();
                        switchTabs(true);
                    }

                }
                else
                {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void switchTabs(boolean direction) {
        if (direction) // true = move left
        {
            if (host.getCurrentTab() != (host.getTabWidget().getTabCount() - 1))
                host.setCurrentTab(host.getCurrentTab() + 1);
            else
                host.setCurrentTab(0);
        } else
        // move right
        {
            if (host.getCurrentTab() == 0)
                host.setCurrentTab(host.getTabWidget().getTabCount() - 1);
            else
                host.setCurrentTab(host.getCurrentTab() - 1);
        }
    }
}