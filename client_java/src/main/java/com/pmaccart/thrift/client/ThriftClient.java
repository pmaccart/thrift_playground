package com.pmaccart.thrift.client;

import com.pmaccart.thrift.model.Forecast;
import com.pmaccart.thrift.model.WeatherService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;

public class ThriftClient {

    public void invoke() {
        try {
            TTransport client = new THttpClient("http://localhost:8080/");
            TProtocol protocol = new TBinaryProtocol(client);

            WeatherService.Client weatherServiceClient = new WeatherService.Client(protocol);
            client.open();

            Forecast forecast = weatherServiceClient.getForecast("today");
            System.out.println("High: " + forecast.getHigh() + ", Low: " + forecast.getLow() +
                    ", Comment: " + forecast.getComment());

            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThriftClient client = new ThriftClient();
        client.invoke();

    }
}
