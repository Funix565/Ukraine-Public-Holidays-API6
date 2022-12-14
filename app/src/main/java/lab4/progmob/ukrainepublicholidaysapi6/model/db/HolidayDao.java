package lab4.progmob.ukrainepublicholidaysapi6.model.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface HolidayDao {

    @Query("SELECT * FROM holidays WHERE year = :year")
    List<HolidayDbEntity> getHolidays(Integer year);

    @Query("SELECT * FROM holidays WHERE date = :date")
    HolidayDbEntity getByDate(String date);

    @Insert
    void insertHolidays(List<HolidayDbEntity> holidays);

    @Query("DELETE FROM holidays WHERE year = :year")
    void deleteHolidays(Integer year);

    @Transaction
    default void updateHolidaysForYear(Integer year, List<HolidayDbEntity> entities) {
        deleteHolidays(year);
        insertHolidays(entities);
    }

}
