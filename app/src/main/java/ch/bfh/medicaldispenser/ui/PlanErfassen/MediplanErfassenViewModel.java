package ch.bfh.medicaldispenser.ui.PlanErfassen;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import ch.bfh.medicaldispenser.DBStrings;
import ch.bfh.medicaldispenser.Medication;

public class MediplanErfassenViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private String eMediplanCompressed;
    private JSONObject eMediplanJSON;
    private ArrayList<Medication> medications;


    public MediplanErfassenViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is dashboard fragment");
        eMediplanCompressed = "CHMED16A1H4sIAAAAAAAEAMVU3W7TMBR+lcq3S4SPHdtx7raVAaKFqutAAnoREreJ2jpT4gKj6ptxx4txnCwVSKQSu0GVqvPX7+fIpwdyuXcFSYiSFCjloGKtNQnI2GGRUZAh1SGwBUASyYTqC8oSSnHgVe4HZM7z1UqF6WcqwkhxjCKdhcJkWc650GIlcXZq8sXDvSEJtHGZpTtjXUOSj4cORyuIpWpRu0EekFnVDYzxmwbtZ+l13dTV7g9t5Ij1eZVicfYWMRab9byxmF1urMH8zpbezO3iNTkGj4RRJIHH7Bwj/J2RhUAHGa+2e/fF1PnPH9bu7XqAnHEhZHzGrqeGJ5EXVVbk9T7bfHr20tTfBwRAxCQHen7f/y7guqi2pnGmLm1j7MbUA/Qx1VLz8/aftvvf7L9Pm2ZQAQgBTEX/QwL+dJa6Et8/SQ7kqr0z0DGEFPxbDsh16R48lqktZjdv8FYwnabfyl2KhRfG5giasIBMTj3nWp4JPrmEjJ93J+fxx5PGTY3X1tbSzuHpGDthCPUu3eKM4O2Kujbr29C3QcSd9ex0udibYyKUWvbLZX3Au54EdupFfSD6QD4CCBEIBcsjjpJZUVnv6yJCxlEsYMRk+59z62pj/MLuLBreoe2vZj2KsfOhvMcyp0yhAzLfbbzj4y8tzloh3gQAAA==";
        medications = new ArrayList<>();
    }

    //public void

    public LiveData<String> getText() {
        return mText;
    }

    public void seteMediplan(String eMediplan) {
        eMediplanCompressed = eMediplan;
    }

    private class PostData extends AsyncTask<String, String, String> {

        String msg = "";

        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

        static final String DB_URL = "jdbc:mysql://" +
                DBStrings.DATABASE_URL + "/" +
                DBStrings.DATABASE_NAME;

        @Override
        protected void onPreExecute() {
        //    homeText.setValue("Verbinde mit Datenbank...");
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
                String sql = "SELECT * FROM patient NATURAL JOIN medication NATURAL JOIN medicament";
                int count = stmt.executeUpdate(sql);


                //msg = "Laden abgeschlossen.";


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



        }

    }

} //End of ViewModel