package ch.bfh.medicaldispenser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import ch.bfh.medicaldispenser.Medication;
import ch.bfh.medicaldispenser.ui.home.MedicationAdapter;


public class AlarmActivity extends AppCompatActivity {

    private Context thisContext;
    private AlertAdapter alertAdapter;
    private ListView medicationAlertLV;
    private ArrayList<Medicament> medicaments;
    private Button btnNah;
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        //tvName = (TextView) findViewById(R.id.tvName);
        btnNah = findViewById(R.id.btnRefuse);
        //updateTimeText(cal);
        btnNah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hier Logeintrag generieren
                finish();
            }
        });
        btnOk = findViewById(R.id.btnAccept);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hier Logeintrag generieren
                finish();
            }
        });

        thisContext = this;

        medicaments = new ArrayList<>();


        GetData retrieveData = new GetData();
        retrieveData.execute("");




        medicationAlertLV = findViewById(R.id.medicationAlertLV);

    }

    private void updateTimeText(Calendar c){
        //tvName.setText(med1.getName());
        //tvTime.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(med1.getTime()));
    }

    private class GetData extends AsyncTask<String, String, String> {

        String msg = "";

        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

        static final String DB_URL = "jdbc:mysql://" +
                DBStrings.DATABASE_URL + "/" +
                DBStrings.DATABASE_NAME;

        @Override
        protected void onPreExecute() {
            //progressTV.setText("Connecting to database...");
        }

        @Override
        protected String doInBackground(String... strings) {
            Connection conn = null;
            Statement stmt = null;
            Properties props;

            try {
                Class.forName(JDBC_DRIVER);
                props = new Properties();
                props.setProperty("user", "Studierende");
                props.setProperty("password", "db-2017");
                props.setProperty("ssl", "true");
                conn = DriverManager.getConnection(DB_URL, props);
                //conn = DriverManager.getConnection(DB_URL, DBStrings.USERNAME, DBStrings.PASSWORD);

                stmt = conn.createStatement();
                String sql = "SELECT pharma_code, medname, composition, therapy, indication, dosis, contra_indication FROM patient NATURAL JOIN medication NATURAL JOIN medicament WHERE taking_time = 'Morgen'";
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int pharmaCode = rs.getInt("pharma_code");
                    String medName = rs.getString("medname");
                    String composition = rs.getString("composition");
                    String therapy = rs.getString("therapy");
                    String indication = rs.getString("indication");
                    String dosis = rs.getString("dosis");
                    String contraIndication = rs.getString("contra_indication");
                    medicaments.add(new Medicament(pharmaCode, medName, composition, therapy, indication, dosis, contraIndication));
                }

                //msg = "Laden abgeschlossen.";

                rs.close();
                stmt.close();
                conn.close();

            } catch (SQLException connError) {
                msg = "Exception for JDBC was thrown.";
                connError.printStackTrace();
            } catch (ClassNotFoundException connError) {
                msg = "Class not found exception was thrown.";
                connError.printStackTrace();
            } finally {

                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(String msg) {

            //progressTV.setText(this.msg);

            // if (medications.size() > 0) {
            alertAdapter = new AlertAdapter(thisContext, medicaments);
            medicationAlertLV.setAdapter(alertAdapter);
            //}

        }

    }


}