package lab4.progmob.ukrainepublicholidaysapi6;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import java.time.Year;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lab4.progmob.ukrainepublicholidaysapi6.logger.AndroidLogger;
import lab4.progmob.ukrainepublicholidaysapi6.logger.Logger;
import lab4.progmob.ukrainepublicholidaysapi6.model.HolidayService;
import lab4.progmob.ukrainepublicholidaysapi6.model.db.AppDatabase;
import lab4.progmob.ukrainepublicholidaysapi6.model.db.HolidayDao;
import lab4.progmob.ukrainepublicholidaysapi6.model.network.HolidayApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class App extends Application {

    public static final Integer CURRENT_YEAR = Year.now().getValue();

    public static final String BASE_URL = "https://date.nager.at/";

    private ViewModelProvider.Factory viewModelFactory;

    @Override
    public void onCreate() {
        super.onCreate();

        Logger logger = new AndroidLogger();

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        HolidayApi holidayApi = retrofit.create(HolidayApi.class);

        ExecutorService executorService = Executors.newCachedThreadPool();

        AppDatabase appDatabase = Room.databaseBuilder(this, AppDatabase.class, "database.db")
                .build();
        HolidayDao holidayDao = appDatabase.getHolidayDao();

        HolidayService holidayService = new HolidayService(holidayApi, holidayDao, executorService, logger);
        viewModelFactory = new ViewModelFactory(holidayService);
    }

    public ViewModelProvider.Factory getViewModelFactory() {
        return viewModelFactory;
    }
}
