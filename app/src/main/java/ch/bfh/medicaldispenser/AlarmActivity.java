package ch.bfh.medicaldispenser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import ch.bfh.medicaldispenser.Medication;


public class AlarmActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvTime;
    private Button btnNah;
    private Button btnOk;
    private Medication med1 = new Medication(1234,"TestMedi", "Macht stark", "Mittag");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        tvName = (TextView) findViewById(R.id.tvName);
        btnNah = (Button) findViewById(R.id.btnRefuse);
        //updateTimeText(cal);
        btnNah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Logihtrag
                finish();
            }
        });
        btnOk = (Button) findViewById(R.id.btnAccept);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Logitrag
                finish();
            }
        });
    }

    private void updateTimeText(Calendar c){
        tvName.setText(med1.getName());
        //tvTime.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(med1.getTime()));
    }


}