package com.weather.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WeatherResponse {
    private String product;
    
    @SerializedName("dataseries")
    private List<Forecast> forecasts;

    public List<Forecast> getForecasts() {
        return forecasts;
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }


    
}
