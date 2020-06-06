package ch.bfh.medicaldispenser.ui.home;

import android.content.res.Resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.common.util.Strings;

import java.util.ArrayList;

import ch.bfh.medicaldispenser.MainActivity;
import ch.bfh.medicaldispenser.Medication;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> homeText;
    private MutableLiveData<String> upMedicationsText;
    ArrayList<Medication> medications;
    //ArrayList<String> medicationList;
    //ArrayList<String> takingTimeList;
    //ArrayList<String> descriptionList;



    public HomeViewModel() {
        homeText = new MutableLiveData<>();
        upMedicationsText = new MutableLiveData<>();


        homeText.setValue("Guten Tag Herr Brönnimann!");
        upMedicationsText.setValue("Ihre anstehenden Medikationen:");

        medications = new ArrayList<>();
        //medicationList = new ArrayList<String>();
        //takingTimeList = new ArrayList<String>();
        //descriptionList = new ArrayList<String>();

        medications.add(new Medication("Ibuprofen","Entzündungshemmende Wirkung", "Morgens"));
        medications.add(new Medication("Disulfontetrapim","Antibiotikum zur bekämpfung einer bakteriellen Infektion", "Morgens"));
        medications.add(new Medication("Antikrampf","selbsterklärende Wirkung lol", "Abends"));
        medications.add(new Medication("Ibuprofen","Entzündungshemmende Wirkung", "Abends"));
        medications.add(new Medication("Ibuprofen","Entzündungshemmende Wirkung", "Morgens"));


    }


    public LiveData<String> getHomeText() {
        return homeText;
    }
    public LiveData<String> getupMedicationsText() {
        return upMedicationsText;
    }

    /*public void fillMedicationList() {
        for(Medication med : medications) {
            medicationList.add(med.getName());
        }
        for(Medication med : medications) {
            takingTimeList.add(med.getTakingTime());
        }
        for(Medication med : medications) {
            descriptionList.add(med.getDescription());
        }
    }*/

    public ArrayList<Medication> getMedications() {
        return medications;
    }

}