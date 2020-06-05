package ch.bfh.medicaldispenser.ui.PlanErfassen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MediplanErfassenViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MediplanErfassenViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}