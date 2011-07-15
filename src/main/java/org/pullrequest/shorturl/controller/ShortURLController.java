package org.pullrequest.shorturl.controller;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;

import org.resthub.web.controller.GenericControllerImpl;
import org.pullrequest.shorturl.model.ShortURL;
import org.pullrequest.shorturl.service.ShortURLService;

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
