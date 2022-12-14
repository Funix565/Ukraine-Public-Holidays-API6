package lab4.progmob.ukrainepublicholidaysapi6.model.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lab4.progmob.ukrainepublicholidaysapi6.model.network.HolidayNetworkEntity;

@Entity(tableName = "holidays")
public class HolidayDbEntity {

    @ColumnInfo(name = "year")
    private Integer year;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "local_name")
    private String localName;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "country_code")
    private String countryCode;

    @ColumnInfo(name = "fixed")
    private boolean fixed;

    @ColumnInfo(name = "global")
    private boolean global;

    @ColumnInfo(name = "launch_year")
    private Integer launchYear;

    public HolidayDbEntity() {
    }

    public HolidayDbEntity(Integer year, HolidayNetworkEntity entity) {
        this.year = year;
        this.date = entity.getDate();
        this.localName = entity.getLocalName();
        this.name = entity.getName();
        this.countryCode = entity.getCountryCode();
        this.fixed = entity.isFixed();
        this.global = entity.isGlobal();
        this.launchYear = entity.getLaunchYear();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public Integer getLaunchYear() {
        return launchYear;
    }

    public void setLaunchYear(Integer launchYear) {
        this.launchYear = launchYear;
    }
}
