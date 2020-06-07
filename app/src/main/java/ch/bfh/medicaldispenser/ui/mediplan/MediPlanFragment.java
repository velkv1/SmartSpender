package ch.bfh.medicaldispenser.ui.mediplan;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import ch.bfh.medicaldispenser.R;

public class MediPlanFragment extends Fragment {

    private MediplanViewModel mediplanViewModel;

    private final String url = "https://compendium.ch/product/1143145-amavita-paracetamol-tabl-500-mg";

    private Button testButton;
    private TextView largeTV;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mediplanViewModel =
                ViewModelProviders.of(this).get(MediplanViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mediplan, container, false);
        //final TextView textView = root.findViewById(R.id.text_mediplan);


        largeTV = root.findViewById(R.id.largeTV);
        testButton = (Button) root.findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new parseit().execute();
            }
        });


        mediplanViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
        //        textView.setText(s);
            }
        });
        return root;
    }

    public class parseit extends AsyncTask<Void, Void, Void> {
        private String words;
        @Override
        protected void onPostExecute(Void avoid) {
            super.onPostExecute(avoid);
            largeTV.setText(words);
        }
        @Override
        protected Void doInBackground(Void... voids) {
            Document document = null;
            try{
                document = (Document) Jsoup.connect(url).get();
                words = document.text();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }






}
