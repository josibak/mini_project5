package miniproject.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ServiceProvider {

    @Autowired
    private Service service;

    public static Service INSTANCE;

    @PostConstruct
    public void init() {
        INSTANCE = service;
    }
}
