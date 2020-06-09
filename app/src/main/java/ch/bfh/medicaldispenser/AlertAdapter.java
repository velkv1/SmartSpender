package ch.bfh.medicaldispenser;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AlertAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    ArrayList<Medicament> medicaments;

    public AlertAdapter (Context c, ArrayList<Medicament> med) {
        this.medicaments = med;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return medicaments.size();
    }

    @Override
    public Object getItem(int i) {
        return medicaments.get(i).getMedName();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.alarm_list, null);
        TextView medNameTV = v.findViewById(R.id.medNameTV);
        TextView therapyTV = v.findViewById(R.id.therapyTV);
        TextView compositionTV = v.findViewById(R.id.compositionTV);
        TextView indicationTV = v.findViewById(R.id.indicationTV);

        String medName = medicaments.get(i).getMedName();
        String therapy= medicaments.get(i).getTherapy();
        String composition= medicaments.get(i).getComposition();
        String indication= medicaments.get(i).getIndication();

        medNameTV.setText(medName);
        therapyTV.setText(therapy);
        compositionTV.setText(composition);
        indicationTV.setText(indication);

        return v;
    }


}
