package com.pmaccart.thrift.web.controller;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TExtensibleServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class WeatherController extends TExtensibleServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private TProcessor processor;

    @Autowired
    private TProtocolFactory inProtocolFactory;

    @Autowired
    private TProtocolFactory outProtocolFactory;

    public WeatherController() {
        super();
        System.out.println("Creating instance of weather controller");
    }

    @RequestMapping(value = "/")
    public void invokeWeatherService(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("Request for weather service has been received.");
        super.doPost(req, resp);
    }

    @Override
    protected TProcessor getProcessor() {
        return processor;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected TProtocolFactory getInProtocolFactory() {
        return inProtocolFactory;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected TProtocolFactory getOutProtocolFactory() {
        return outProtocolFactory;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setProcessor(TProcessor processor) {
        this.processor = processor;
    }

    public void setInProtocolFactory(TProtocolFactory inProtocolFactory) {
        this.inProtocolFactory = inProtocolFactory;
    }

    public void setOutProtocolFactory(TProtocolFactory outProtocolFactory) {
        this.outProtocolFactory = outProtocolFactory;
    }
}
