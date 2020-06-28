package org.car.sale.config;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.car.catalog.service.CarCatalogWebService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfClientConfig {

    @Bean
    public CarCatalogWebService createAuthorPortTypeProxy() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(CarCatalogWebService.class);
        jaxWsProxyFactoryBean.setAddress("http://localhost:8080/car-catalog-website/wsdl/carCatalog?wsdl");
        return (CarCatalogWebService) jaxWsProxyFactoryBean.create();
    }

}
