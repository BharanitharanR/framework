package com.batty.framework;

import com.batty.framework.interfaces.RegisterServiceInterface;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.batty.registry.api.DefaultApi;
import com.batty.registry.api.client.ApiClient;
import com.batty.registry.api.client.ApiException;
import com.batty.registry.api.model.ServiceSchema;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component("frameworkRegistryBean")
public class FrameworkRegistry {

    protected DefaultApi service;

    // Set as a property for each service
    @Value("${serviceName}")
    protected String serviceName;

    @PostConstruct
    public void init() {
        com.batty.registry.api.client.Configuration.setDefaultApiClient(new ApiClient().setBasePath("http://localhost:8080"));
        this.service = new DefaultApi();
        registerService(true);

    }

    public void registerService(boolean value) {
        try {
       ServiceSchema schema = new ServiceSchema();
        schema.setServiceId(serviceName);
        schema.setServiceName(serviceName);
        schema.setServiceHostIP(InetAddress.getLocalHost().getHostAddress());
        if( value ) { schema.setStatus("Ok"); } else { schema.setStatus("Off"); };

            this.service.addService("service",schema);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
        catch(UnknownHostException ignored) {
            throw new RuntimeException(ignored);
        }
    }
}
