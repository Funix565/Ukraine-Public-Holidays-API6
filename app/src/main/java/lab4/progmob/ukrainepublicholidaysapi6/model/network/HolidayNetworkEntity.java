package lab4.progmob.ukrainepublicholidaysapi6.model.network;

public class HolidayNetworkEntity {
    private String date;
    private String localName;
    private String name;
    private String countryCode;
    private boolean fixed;
    private boolean global;
    private Integer launchYear;

    public String getDate() {
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

    public Integer getLaunchYear() {
        return launchYear;
    }
}
