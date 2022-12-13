package lab4.progmob.ukrainepublicholidaysapi6.main;

import static lab4.progmob.ukrainepublicholidaysapi6.model.Result.Status.EMPTY;
import static lab4.progmob.ukrainepublicholidaysapi6.model.Result.Status.ERROR;
import static lab4.progmob.ukrainepublicholidaysapi6.model.Result.Status.LOADING;
import static lab4.progmob.ukrainepublicholidaysapi6.model.Result.Status.SUCCESS;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.time.LocalDate;
import java.time.Year;
import java.util.Arrays;
import java.util.List;

import lab4.progmob.ukrainepublicholidaysapi6.model.Holiday;
import lab4.progmob.ukrainepublicholidaysapi6.model.Result;

public class MainViewModel extends ViewModel {

    private Result<List<Holiday>> holidaysResult = Result.empty();
    private MutableLiveData<ViewState> stateLiveData = new MutableLiveData<>();

    {
        updateViewState(Result.success(Arrays.asList(
                new Holiday(LocalDate.parse("2017-01-01"), "Neujahr1", "New Year's Day0", "AT", true, true, Year.of(1967)),
                new Holiday(LocalDate.parse("2017-02-09"), "Neujahr2", "New Year's Day9", "AT", true, true, Year.of(2002)),
                new Holiday(LocalDate.parse("2017-03-08"), "Neujahr3", "New Year's Day8", "AT", true, true, Year.of(1999)),
                new Holiday(LocalDate.parse("2017-04-07"), "Neujahr4", "New Year's Day7", "AT", true, true, Year.of(2012)),
                new Holiday(LocalDate.parse("2017-05-06"), "Neujahr5", "New Year's Day6", "AT", true, true, Year.of(1634))
        )));
    }

    public LiveData<ViewState> getViewState() {
        return stateLiveData;
    }

    // TODO: optional and may be with Date class
    public void getHolidays(String year) {

    }

    private void updateViewState(Result<List<Holiday>> holidaysResult) {
        ViewState state = new ViewState();
        state.showList = holidaysResult.getStatus() == SUCCESS;
        state.showEmptyHint = holidaysResult.getStatus() == EMPTY;
        state.showError = holidaysResult.getStatus() == ERROR;
        state.showProgress = holidaysResult.getStatus() == LOADING;
        state.holidays = holidaysResult.getData();
        stateLiveData.setValue(state);
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
