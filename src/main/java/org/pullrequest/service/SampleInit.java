package org.pullrequest.service;

import javax.inject.Named;
import javax.inject.Inject;
import org.resthub.core.util.PostInitialize;
import org.pullrequest.model.Sample;

@Named("sampleInit")
public class SampleInit {
    
    @Inject
    @Named("sampleService")
    private SampleService sampleService;
    
    @PostInitialize
    public void init() {
        Sample s = new Sample();
        s.setName("testSample");
        sampleService.create(s);
    }   

}
