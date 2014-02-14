package com.pmaccart.thrift.handler;

import com.pmaccart.thrift.model.Forecast;
import com.pmaccart.thrift.model.InvalidRequestException;
import com.pmaccart.thrift.model.WeatherService;
import org.apache.thrift.TException;

public class WeatherHandler implements WeatherService.Iface {

    @Override
    public void ping() throws TException {
        System.out.print("Ping received.");
    }

    @Override
    public Forecast getForecast(String date) throws InvalidRequestException, TException {
        Forecast forecast = new Forecast();
        forecast.setDate(date);
        forecast.setLow(0);
        forecast.setHigh(20);
        forecast.setComment("Cold as hell.");
        return forecast;
    }
}
