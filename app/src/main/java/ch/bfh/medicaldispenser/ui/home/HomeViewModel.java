package ch.bfh.medicaldispenser.ui.home;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import ch.bfh.medicaldispenser.DBStrings;
import ch.bfh.medicaldispenser.Medication;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> homeText;
    private MutableLiveData<String> upMedicationsText;
    ArrayList<Medication> medications;
    GetData retrieveData;
    //ArrayList<String> medicationList;
    //ArrayList<String> takingTimeList;
    //ArrayList<String> descriptionList;



    public HomeViewModel() {
        homeText = new MutableLiveData<>();
        upMedicationsText = new MutableLiveData<>();


        homeText.setValue("Guten Tag Herr Brönnimann!");
        upMedicationsText.setValue("Ihre anstehenden Medikationen:");

        medications = new ArrayList<>();
        //medicationList = new ArrayList<String>();
        //takingTimeList = new ArrayList<String>();
        //descriptionList = new ArrayList<String>();

        /*
        medications.add(new Medication(1234,"Ibuprofen","Entzündungshemmende Wirkung", "Morgens"));
        medications.add(new Medication(1234,"Disulfontetrapim","Antibiotikum zur bekämpfung einer bakteriellen Infektion", "Morgens"));
        medications.add(new Medication(1234,"Antikrampf","selbsterklärende Wirkung lol", "Abends"));
        medications.add(new Medication(1234,"Ibuprofen","Entzündungshemmende Wirkung", "Abends"));
        medications.add(new Medication(1234,"Ibuprofen","Entzündungshemmende Wirkung", "Morgens"));
         */

        retrieveData = new GetData();


    }

    public void synchData() {
        retrieveData.execute("");
    }


    public LiveData<String> getHomeText() {
        return homeText;
    }
    public LiveData<String> getupMedicationsText() {
        return upMedicationsText;
    }

    /*public void fillMedicationList() {
        for(Medication med : medications) {
            medicationList.add(med.getName());
        }
        for(Medication med : medications) {
            takingTimeList.add(med.getTakingTime());
        }
        for(Medication med : medications) {
            descriptionList.add(med.getDescription());
        }
    }*/


    public ArrayList<Medication> getMedications() {
        return medications;
    }

    private class GetData extends AsyncTask<String, String, String> {

        String msg = "";

        static final String JDBC_DRIVER = "org.postgresql.Driver";

        static final String DB_URL = "jdbc:postgresql://" +
                DBStrings.DATABASE_URL + "/" +
                DBStrings.DATABASE_NAME;

        @Override
        protected void onPreExecute() {
            homeText.setValue("Verbinde mit Datenbank...");
        }

        @Override
        protected String doInBackground(String... strings) {
            Connection conn = null;
            Statement stmt = null;
            Properties props;

            try {
                Class.forName(JDBC_DRIVER);
                //props = new Properties();
                //props.setProperty("user", "Studierende");
                //props.setProperty("password", "db-2017");
                //props.setProperty("ssl", "true");
                //conn = DriverManager.getConnection(DB_URL, props);
                conn = DriverManager.getConnection(DB_URL, DBStrings.USERNAME, DBStrings.PASSWORD);

                stmt = conn.createStatement();
                String sql = "SELECT * FROM patient NATURAL JOIN medication NATURAL JOIN medicament";
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int pharmaCode = rs.getInt("pharma_code");
                    String name = rs.getString("medname");
                    String reason = rs.getString("reason");
                    String takingTime = rs.getString("taking_time");
                    medications.add(new Medication(pharmaCode, name, reason, takingTime));
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

            homeText.setValue(this.msg);

            }

    }


} // End of HomeViewModel