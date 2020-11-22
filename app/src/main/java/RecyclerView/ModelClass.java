package RecyclerView;

public class ModelClass {
    String stations,timing, platform;

    public ModelClass(String stations, String timing,String platform) {
        this.platform = platform;
        this.stations = stations;
        this.timing = timing;
    }



    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getStations() {
        return stations;
    }

    public void setStations(String stations) {
        this.stations = stations;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }
}
