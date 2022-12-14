package lab4.progmob.ukrainepublicholidaysapi6.model.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HolidayApi {

    @GET("api/v3/PublicHolidays/{year}/UA")
    Call<List<HolidayNetworkEntity>> getHolidays(@Path("year") Integer year);
}
