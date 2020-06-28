package org.car.catalog.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.car.catalog.service.CarCatalogWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfServerConfig {

    @Autowired
    private Bus bus;

    @Bean
    public ServletRegistrationBean<CXFServlet> servletRegistrationBean() {
        // 預設為 /services/*
        return new ServletRegistrationBean<>(new CXFServlet(), "/wsdl/*");
    }

    @Bean
    public Endpoint endpoint(CarCatalogWebService carCatalogWebService) {
        EndpointImpl endpoint = new EndpointImpl(bus, carCatalogWebService);
        endpoint.publish("/carCatalog");
        return endpoint;
    }

}
