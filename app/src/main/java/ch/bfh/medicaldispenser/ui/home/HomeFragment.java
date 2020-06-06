package ch.bfh.medicaldispenser.ui.home;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;

import ch.bfh.medicaldispenser.MainActivity;
import ch.bfh.medicaldispenser.Medication;
import ch.bfh.medicaldispenser.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ListView medicationListView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        Resources res = getResources();
        final TextView textViewHome = root.findViewById(R.id.text_home);
        final TextView textViewUpMedication = root.findViewById(R.id.text_upMedication);
        MedicationAdapter medicationAdapter = new MedicationAdapter(getContext(), homeViewModel.getMedications());



        medicationListView = root.findViewById(R.id.medicationListView);

        medicationListView.setAdapter(medicationAdapter);



        homeViewModel.getHomeText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textViewHome.setText(s);
            }
        });
        homeViewModel.getupMedicationsText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textViewUpMedication.setText(s);
            }
        });
        return root;
    }
}
