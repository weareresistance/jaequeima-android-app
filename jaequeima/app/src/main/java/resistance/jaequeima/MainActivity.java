package resistance.jaequeima;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {

    private TextView tvDay, tvHour, tvMinute, tvSecond, txtTvDay, txtTvHour, txtTvMinute, txtTvSecond, tvEvent;
    private ImageButton logoResistance;
    private LinearLayout linearLayout2;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        countDownStart();

        logoResistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.resistance.pt");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
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
        tvEvent = (TextView) findViewById(R.id.textView);
        tvEvent.setTypeface(type);

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
                        tvEvent.setText("JÁ É QUEIMA");
                        handler.removeCallbacks(runnable);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }
}