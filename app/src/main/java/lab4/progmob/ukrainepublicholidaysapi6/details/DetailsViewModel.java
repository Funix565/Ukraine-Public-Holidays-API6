package lab4.progmob.ukrainepublicholidaysapi6.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import lab4.progmob.ukrainepublicholidaysapi6.BaseViewModel;
import lab4.progmob.ukrainepublicholidaysapi6.model.Callback;
import lab4.progmob.ukrainepublicholidaysapi6.model.Cancellable;
import lab4.progmob.ukrainepublicholidaysapi6.model.Holiday;
import lab4.progmob.ukrainepublicholidaysapi6.model.HolidayService;
import lab4.progmob.ukrainepublicholidaysapi6.model.Result;

public class DetailsViewModel extends BaseViewModel {

    private MutableLiveData<Result<Holiday>> holidayLiveData = new MutableLiveData<>();

    {
        holidayLiveData.setValue(Result.empty());
    }

    private Cancellable cancellable;

    public DetailsViewModel(HolidayService holidayService) {
        super(holidayService);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (cancellable != null) {
            cancellable.cancel();
        }
    }

    public void loadHolidayByDate(String date) {
        holidayLiveData.setValue(Result.loading());
        cancellable = getHolidayService().getHolidayByDate(date, new Callback<Holiday>() {
            @Override
            public void onError(Throwable error) {
                holidayLiveData.postValue(Result.error(error));
            }

            @Override
            public void onResults(Holiday data) {
                holidayLiveData.postValue(Result.success(data));
            }
        });
    }

    public LiveData<Result<Holiday>> getResults() {
        return holidayLiveData;
    }
}
