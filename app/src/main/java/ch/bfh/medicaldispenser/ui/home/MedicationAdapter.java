package ch.bfh.medicaldispenser.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ch.bfh.medicaldispenser.Medication;
import ch.bfh.medicaldispenser.R;


public class MedicationAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    ArrayList<Medication> medications;

    public MedicationAdapter(Context c, ArrayList<Medication> med) {
        this.medications = med;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return medications.size();
    }

    @Override
    public Object getItem(int i) {
        return medications.get(i).getName();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.upcoming_medication_list, null);
        TextView upMedNameTV = v.findViewById(R.id.upMedNameTV);
        TextView upReasonTV = v.findViewById(R.id.upReasonTV);
        TextView takingTimeTV = v.findViewById(R.id.takingTimeTV);

        String name = medications.get(i).getName();
        String reason = medications.get(i).getReason();
        String takingTime = medications.get(i).getTakingTime();

        upMedNameTV.setText(name);
        upReasonTV.setText(reason);
        takingTimeTV.setText(takingTime);

        return v;
    }
}
