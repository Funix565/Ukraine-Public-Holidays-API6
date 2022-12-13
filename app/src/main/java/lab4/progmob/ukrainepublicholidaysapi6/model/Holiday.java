package lab4.progmob.ukrainepublicholidaysapi6.model;

import java.time.LocalDate;
import java.time.Year;

public class Holiday {
    private final LocalDate date;
    private final String localName;
    private final String name;
    private final String countryCode;
    private final boolean fixed;
    private final boolean global;
    private final Year launchYear;

    public Holiday(LocalDate date, String localName, String name, String countryCode, boolean fixed, boolean global, Year launchYear) {
        this.date = date;
        this.localName = localName;
        this.name = name;
        this.countryCode = countryCode;
        this.fixed = fixed;
        this.global = global;
        this.launchYear = launchYear;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLocalName() {
        return localName;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public boolean isFixed() {
        return fixed;
    }

    public boolean isGlobal() {
        return global;
    }

    public Year getLaunchYear() {
        return launchYear;
    }
}
