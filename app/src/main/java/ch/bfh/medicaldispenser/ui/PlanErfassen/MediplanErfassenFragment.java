package ch.bfh.medicaldispenser.ui.PlanErfassen;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
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
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

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

        surfaceView = root.findViewById(R.id.camerapreview);
        textView = root.findViewById(R.id.textViewPointCamera);

        barcodeDetector = new BarcodeDetector.Builder(getContext())
               .setBarcodeFormats(Barcode.QR_CODE).build();

        cameraSource = new CameraSource.Builder(getContext(), barcodeDetector)
              .setRequestedPreviewSize(640, 460).build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    cameraSource.start(holder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCode = detections.getDetectedItems();

                if(qrCode.size() != 0) {
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            Vibrator vibrator = (Vibrator)getContext().getSystemService(Context.VIBRATOR_SERVICE);
                            String scanCode = qrCode.valueAt(0).displayValue;
                            if(scanCode.startsWith("CHMED")) {
                                MediplanErfassenViewModel.seteMediplan(qrCode.valueAt(0).displayValue);
                                vibrator.vibrate(1000);
                                cameraSource.stop();
                                textView.setText("worked :)");
                            } else {
                                textView.setText("kein gültiger eMediplan");
                            }
                        }
                    });
                }
            }
        });


        MediplanErfassenViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
}
