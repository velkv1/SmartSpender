package ch.bfh.medicaldispenser.ui.logs;

import android.content.Context;
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
import ch.bfh.medicaldispenser.Log;
import ch.bfh.medicaldispenser.Medication;
import ch.bfh.medicaldispenser.ui.home.HomeViewModel;
import ch.bfh.medicaldispenser.ui.home.MedicationAdapter;

public class LogsViewModel extends ViewModel {

    public static ArrayList<Log> logs;
    private GetData retrieveData;
    private Context fragmentContext;
    public static MutableLiveData<LogAdapter> logAdapter;
    private ArrayList<Log> logsList;

    private MutableLiveData<String> mText;

    public LogsViewModel(Context fragmentContext) {
    //    mText = new MutableLiveData<>();
    //    mText.setValue("This is notifications fragment");

        this.fragmentContext = fragmentContext;
        logAdapter = new MutableLiveData<>();
        logsList = new ArrayList<>();

        retrieveData = new GetData();

        retrieveData.execute();
    }

    public LiveData<String> getText() {
        return mText;
    }

    /*public static ArrayList<Log> getLogs() {
        return logs;
    }*/

    public LiveData<LogAdapter> getLogAdapter() {
        return logAdapter;
    }

    public ArrayList<Log> getLogs() {
        return logsList;
    }


    private class GetData extends AsyncTask<String, String, String> {

        String msg = "";

        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

        static final String DB_URL = "jdbc:mysql://" +
                DBStrings.DATABASE_URL + "/" +
                DBStrings.DATABASE_NAME;

        @Override
        protected void onPreExecute() {
            //homeText.setValue("Verbinde mit Datenbank...");
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
                String sql = "SELECT date, time, medname, intake FROM log NATURAL JOIN medicament";
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    String date = rs.getString("date");
                    String time = rs.getString("time");
                    String medName = rs.getString("medname");
                    String intake = rs.getString("intake");
                    logsList.add(new Log(date, time, medName, intake));
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

            //homeText.setValue("finished");
            logAdapter.setValue(new LogAdapter(fragmentContext, getLogs()));
        }
    }

}
