package com.dk.tracingblog.gama;

import org.eclipse.microprofile.opentracing.Traced;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;

@ApplicationScoped
@Traced(value = true, operationName = "testingChangedOperationName")
public class Database {
    private HashMap<Integer, String> data;

    @PostConstruct
    private void init() {
        data = new HashMap<Integer, String>();
        data.put(1, "gama");
    }

    public String get(Integer id) {
        return data.get(id);
    }

}
