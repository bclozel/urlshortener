package org.pullrequest.controller;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;

import org.resthub.web.controller.GenericControllerImpl;
import org.pullrequest.model.ShortURL;
import org.pullrequest.service.ShortURLService;

@Path("/shorturl")
@Named("shortURLController")
public class ShortURLController extends GenericControllerImpl<ShortURL, Long, ShortURLService> {

    @Inject
    @Named("shortURLService")
    @Override
    public void setService(ShortURLService service) {
        this.service = service;
    }
}
