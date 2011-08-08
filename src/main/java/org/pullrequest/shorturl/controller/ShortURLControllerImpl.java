package org.pullrequest.shorturl.controller;

import com.sun.jersey.api.NotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.pullrequest.shorturl.model.ShortURL;
import org.pullrequest.shorturl.service.ShortURLService;
import org.resthub.web.response.PageResponse;
import org.synyx.hades.domain.PageRequest;

@Singleton
@Named("shortURLController")
public class ShortURLControllerImpl implements ShortURLController {

    @Inject
    @Named("shortURLService")
    ShortURLService service;

    @Override
    public ShortURL createShortURL(ShortURL shorturl) {
        return this.service.create(shorturl);
    }

    @Override
    public ShortURL findByShortKey(String shortKey) {

        ShortURL foundShortURL = this.service.resolveURL(shortKey);

        if (foundShortURL == null) {
            throw new NotFoundException();
        }

        return foundShortURL;
    }

    @Override
    public Response redirectByShortKey(String shortKey) {

        ShortURL foundShortURL = this.findByShortKey(shortKey);
        URI location = null;

        try {
            location = new URI(shortKey);
        } catch (URISyntaxException ex) {
            throw new NotFoundException();
        }

        return Response.status(Response.Status.fromStatusCode(301)).location(location).entity(foundShortURL).build();
    }

    @Override
    public void delete(String shortKey) {

        ShortURL foundShortURL = this.service.resolveURL(shortKey);

        if (foundShortURL == null) {
            throw new NotFoundException();
        }

        this.service.delete(foundShortURL);
    }

    @Override
    public PageResponse<ShortURL> findAll(Integer page, Integer size) {
        return new PageResponse<ShortURL>(this.service.findAll(new PageRequest(page, size)));
    }
}
