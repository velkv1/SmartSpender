package ch.bfh.medicaldispenser.ui.PlanErfassen;

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
import com.google.android.gms.vision.barcode.BarcodeDetector;

import ch.bfh.medicaldispenser.R;

public class MediplanErfassenFragment extends Fragment {

    private MediplanErfassenViewModel MediplanErfassenViewModel;

    SurfaceView surfaceView;
    CameraSource cameraSource;
    TextView textView;
    BarcodeDetector barcodeDetector;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MediplanErfassenViewModel =
                ViewModelProviders.of(this).get(MediplanErfassenViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mediplan_erfassen, container, false);

        surfaceView = (SurfaceView)root.findViewById(R.id.camerapreview);
        textView = (TextView)root.findViewById(R.id.textViewPointCamera);

        //barcodeDetector = new BarcodeDetector(this)
        //       .setBarcodeFormats(Barcode.QR_CODE).build();

        //cameraSource = new CameraSource.Builder(this, barcodeDetector)
        //       .setRequestedPreviewSize(640, 460).build();


        MediplanErfassenViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
}
