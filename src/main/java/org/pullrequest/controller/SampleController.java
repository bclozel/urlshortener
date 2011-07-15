package org.pullrequest.controller;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;

import org.resthub.web.controller.GenericControllerImpl;
import org.pullrequest.model.Sample;
import org.pullrequest.service.SampleService;

@Path("/sample")
@Named("sampleController")
public class SampleController extends GenericControllerImpl<Sample, Long, SampleService> {

    @Inject
    @Named("sampleService")
    @Override
    public void setService(SampleService service) {
        this.service = service;
    }
}
