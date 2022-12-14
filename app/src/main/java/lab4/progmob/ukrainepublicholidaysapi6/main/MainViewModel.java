package lab4.progmob.ukrainepublicholidaysapi6.main;

import static lab4.progmob.ukrainepublicholidaysapi6.model.Result.Status.EMPTY;
import static lab4.progmob.ukrainepublicholidaysapi6.model.Result.Status.ERROR;
import static lab4.progmob.ukrainepublicholidaysapi6.model.Result.Status.LOADING;
import static lab4.progmob.ukrainepublicholidaysapi6.model.Result.Status.SUCCESS;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import lab4.progmob.ukrainepublicholidaysapi6.BaseViewModel;
import lab4.progmob.ukrainepublicholidaysapi6.model.Callback;
import lab4.progmob.ukrainepublicholidaysapi6.model.Cancellable;
import lab4.progmob.ukrainepublicholidaysapi6.model.Holiday;
import lab4.progmob.ukrainepublicholidaysapi6.model.HolidayService;
import lab4.progmob.ukrainepublicholidaysapi6.model.Result;

public class MainViewModel extends BaseViewModel {

    private Result<List<Holiday>> holidaysResult = Result.empty();
    private MutableLiveData<ViewState> stateLiveData = new MutableLiveData<>();

    {
        updateViewState(Result.empty());
    }

    private Cancellable cancellable;

    @Override
    protected void onCleared() {
        super.onCleared();
        if (cancellable != null) {
            cancellable.cancel();
        }
    }

    public MainViewModel(HolidayService holidayService) {
        super(holidayService);
    }

    public LiveData<ViewState> getViewState() {
        return stateLiveData;
    }

    public void getHolidays(Integer year) {
        updateViewState(Result.loading());
        cancellable = getHolidayService().getHolidays(year, new Callback<List<Holiday>>() {
            @Override
            public void onError(Throwable error) {
                if (holidaysResult.getStatus() != SUCCESS) {
                    updateViewState(Result.error(error));
                }
            }

            @Override
            public void onResults(List<Holiday> data) {
                updateViewState(Result.success(data));
            }
        });
    }

    private void updateViewState(Result<List<Holiday>> holidaysResult) {
        this.holidaysResult = holidaysResult;
        ViewState state = new ViewState();
        state.showList = holidaysResult.getStatus() == SUCCESS;
        state.showEmptyHint = holidaysResult.getStatus() == EMPTY;
        state.showError = holidaysResult.getStatus() == ERROR;
        state.showProgress = holidaysResult.getStatus() == LOADING;
        state.holidays = holidaysResult.getData();
        stateLiveData.postValue(state);
    }

    static class ViewState {
        private boolean showList;
        private boolean showEmptyHint;
        private boolean showError;
        private boolean showProgress;
        private List<Holiday> holidays;

        public boolean isShowList() {
            return showList;
        }

        public boolean isShowEmptyHint() {
            return showEmptyHint;
        }

        public boolean isShowError() {
            return showError;
        }

        public boolean isShowProgress() {
            return showProgress;
        }

        public List<Holiday> getHolidays() {
            return holidays;
        }
    }
}
