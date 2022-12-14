package lab4.progmob.ukrainepublicholidaysapi6;

import androidx.lifecycle.ViewModel;

import lab4.progmob.ukrainepublicholidaysapi6.model.HolidayService;

public class BaseViewModel extends ViewModel {

    private HolidayService holidayService;

    public BaseViewModel(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    protected final HolidayService getHolidayService() {
        return holidayService;
    }
}
