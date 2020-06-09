package ch.bfh.medicaldispenser.ui.logs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import ch.bfh.medicaldispenser.R;
import ch.bfh.medicaldispenser.ui.home.MedicationAdapter;

public class LogsFragment extends Fragment {

    private LogsViewModel logsViewModel;
    LogAdapter logAdapter;
    Context thisContext;
    ListView logLV;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*logsViewModel =
                ViewModelProviders.of(this).get(LogsViewModel.class);*/
        logsViewModel = new LogsViewModel(getContext());
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        //final TextView textView = root.findViewById(R.id.text_notifications);
        //final TextView textViewHome = root.findViewById(R.id.text_home);
        //final TextView textViewUpMedication = root.findViewById(R.id.text_upMedication);


        //thisContext = getContext();
        logLV = root.findViewById(R.id.logLV);

        /*
        Button logButton = root.findViewById(R.id.logButton);
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logAdapter = new LogAdapter(thisContext, LogsViewModel.logs);
                logLV.setAdapter(logAdapter);
            }
        });
        */


        LogsViewModel.logAdapter.observe(getViewLifecycleOwner(), new Observer<LogAdapter>() {
            @Override
            public void onChanged(@Nullable LogAdapter s) {
                logLV.setAdapter(s);
            }
        });

        /*
        logsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
        //        textView.setText(s);
            }
        });*/
        return root;
    }
}
