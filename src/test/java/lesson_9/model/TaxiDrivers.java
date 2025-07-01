package lesson_9.model;

import java.util.List;

public class TaxiDrivers {

    private Integer totalDrivers;
    private String lastUpdated;
    private List<Drivers> drivers;

    public Integer getTotalDrivers() {
        return totalDrivers;
    }

    public void setTotalDrivers(Integer totalDrivers) {
        this.totalDrivers = totalDrivers;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<Drivers> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Drivers> drivers) {
        this.drivers = drivers;
    }
}
