package ch.bfh.medicaldispenser.ui.logs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ch.bfh.medicaldispenser.Log;
import ch.bfh.medicaldispenser.R;

public class LogAdapter extends BaseAdapter {


    LayoutInflater mInflater;
    ArrayList<Log> logs;

    public LogAdapter(Context c, ArrayList<Log> logs) {
        this.logs = logs;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return logs.size();
    }

    @Override
    public Object getItem(int i) {
        return logs.get(i).getMedName();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.log_list, null);
        TextView dateTV = v.findViewById(R.id.dateTV);
        TextView timeTV = v.findViewById(R.id.timeTV);
        TextView medNameTV = v.findViewById(R.id.mednameTV);
        TextView intakeTV = v.findViewById(R.id.intakeTV);

        String date = logs.get(i).getDate();
        String time = logs.get(i).getTime();
        String medName = logs.get(i).getMedName();
        String intake = logs.get(i).getIntake();

        dateTV.setText(date);
        timeTV.setText(time);
        medNameTV.setText(medName);
        intakeTV.setText(intake);

        return v;
    }
}
