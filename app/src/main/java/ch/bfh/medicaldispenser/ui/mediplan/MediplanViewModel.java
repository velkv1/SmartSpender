package ch.bfh.medicaldispenser.ui.mediplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MediplanViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MediplanViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}