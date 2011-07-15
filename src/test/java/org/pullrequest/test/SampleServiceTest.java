package org.pullrequest.test;


import javax.inject.Inject;
import javax.inject.Named;

import org.resthub.core.test.service.AbstractServiceTest;
import org.pullrequest.model.Sample;
import org.pullrequest.service.SampleService;

public class SampleServiceTest extends AbstractServiceTest<Sample, Long, SampleService> {

    @Inject
    @Named("sampleService")
    @Override
    public void setService(SampleService service) {
        super.setService(service);
    }

    @Override
    public void testUpdate() {
        // Not implemented yet
    }
   
}
