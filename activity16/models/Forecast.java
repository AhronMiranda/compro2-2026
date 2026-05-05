package com.weather.models;

import com.google.gson.annotations.SerializedName;

public class Forecast {
    private int timepoint;

    @SerializedName("temp2m")
    private int temperature;

    @SerializedName("wind10m")
    public Wind wind;

    public int getTimepoint() {
        return timepoint;
    }

    public void setTimepoint(int timepoint) {
        this.timepoint = timepoint;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }
}
