package ch.bfh.medicaldispenser.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import ch.bfh.medicaldispenser.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    MedicationAdapter medicationAdapter;
    Context thisContext;
    ListView medicationListView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);*/
        homeViewModel = new HomeViewModel(getContext());
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textViewHome = root.findViewById(R.id.text_home);
        final TextView textViewUpMedication = root.findViewById(R.id.text_upMedication);
        //thisContext = getContext();
        medicationListView = root.findViewById(R.id.medicationListView);

        //homeViewModel.synchData();

        /*Button synchButton = root.findViewById(R.id.logButton);
        synchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicationAdapter = new MedicationAdapter(thisContext, homeViewModel.getMedications());
                medicationListView.setAdapter(medicationAdapter);
            }
        });*/


        homeViewModel.getMedicationAdapter().observe(getViewLifecycleOwner(), new Observer<MedicationAdapter>() {
            @Override
            public void onChanged(@Nullable MedicationAdapter s) {
                medicationListView.setAdapter(s);
            }
        });

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
