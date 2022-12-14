package lab4.progmob.ukrainepublicholidaysapi6.model;

import com.annimon.stream.Stream;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import lab4.progmob.ukrainepublicholidaysapi6.logger.Logger;
import lab4.progmob.ukrainepublicholidaysapi6.model.db.HolidayDao;
import lab4.progmob.ukrainepublicholidaysapi6.model.db.HolidayDbEntity;
import lab4.progmob.ukrainepublicholidaysapi6.model.network.HolidayApi;
import lab4.progmob.ukrainepublicholidaysapi6.model.network.HolidayNetworkEntity;
import retrofit2.Call;
import retrofit2.Response;

// Sync, get data, save into db
public class HolidayService {

    private ExecutorService executorService;
    private HolidayDao holidayDao;
    private HolidayApi holidayApi;
    private Logger logger;

    public HolidayService(HolidayApi holidayApi, HolidayDao holidayDao, ExecutorService executorService, Logger logger) {
        this.holidayApi = holidayApi;
        this.holidayDao = holidayDao;
        this.executorService = executorService;
        this.logger = logger;
    }

    public Cancellable getHolidays(Integer year, Callback<List<Holiday>> callback) {
        Future<?> future = executorService.submit(() -> {
            try {
                List<HolidayDbEntity> entities = holidayDao.getHolidays(year);
                List<Holiday> holidays = convertToHolidays(entities);
                callback.onResults(holidays);

                Response<List<HolidayNetworkEntity>> response = holidayApi.getHolidays(year).execute();
                if (response.isSuccessful()) {
                    List<HolidayDbEntity> newDbHolidays = networkToDbEntities(year, response.body());
                    holidayDao.updateHolidaysForYear(year, newDbHolidays);
                    callback.onResults(convertToHolidays(newDbHolidays));
                } else {
                    if (!holidays.isEmpty()) {
                        RuntimeException exception = new RuntimeException("Something happened");
                        logger.e(exception);
                        callback.onError(exception);
                    }
                }
            } catch (Exception e) {
                logger.e(e);
                callback.onError(e);
            }
        });

        return new FutureCancellable(future);
    }

    public Cancellable getHolidayByDate(String date, Callback<Holiday> callback) {
        Future<?> future = executorService.submit(() -> {
            try {
                HolidayDbEntity dbEntity = holidayDao.getByDate(date);
                Holiday holiday = new Holiday(dbEntity);
                callback.onResults(holiday);
            } catch (Exception e) {
                logger.e(e);
                callback.onError(e);
            }
        });

        return new FutureCancellable(future);
    }

    private List<Holiday> convertToHolidays(List<HolidayDbEntity> entities) {
        return Stream.of(entities).map(Holiday::new).toList();
    }

    private List<HolidayDbEntity> networkToDbEntities(Integer year, List<HolidayNetworkEntity> entities) {
        return Stream.of(entities).map(networkEntity -> new HolidayDbEntity(year, networkEntity)).toList();
    }

    static class FutureCancellable implements Cancellable {
        private Future<?> future;

        public FutureCancellable(Future<?> future) {
            this.future = future;
        }

        @Override
        public void cancel() {
            future.cancel(true);
        }
    }
}
