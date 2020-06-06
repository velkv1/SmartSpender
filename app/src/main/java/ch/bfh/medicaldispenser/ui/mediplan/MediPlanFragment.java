package ch.bfh.medicaldispenser.ui.mediplan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import ch.bfh.medicaldispenser.R;

public class MediPlanFragment extends Fragment {

    private MediplanViewModel mediplanViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mediplanViewModel =
                ViewModelProviders.of(this).get(MediplanViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mediplan, container, false);
        //final TextView textView = root.findViewById(R.id.text_mediplan);




        mediplanViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
        //        textView.setText(s);
            }
        });
        return root;
    }


}
