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

@Named("shortURLController")
@Singleton
public class ShortURLControllerImpl implements ShortURLController {

    @Inject
    @Named("shortURLService")
    ShortURLService service;

    @Override
    public ShortURL createShortURL(String url, String shortKey) {
        return this.service.createShortURL(url, shortKey);
    }

    @Override
    public Response finByShortKey(String shortKey) {

        ShortURL foundShortURL = this.service.resolveURL(shortKey);
        URI location = null;

        if (foundShortURL == null) {
            throw new NotFoundException();
        }

        try {
            location = foundShortURL.getUrl().toURI();
        } catch (URISyntaxException ex) {
            throw new NotFoundException();
        }

        return Response.temporaryRedirect(location).entity(foundShortURL).build();
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
    public PageResponse<ShortURL> findAll(@QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("size") @DefaultValue("5") Integer size) {
        return new PageResponse<ShortURL>(this.service.findAll(new PageRequest(page, size)));
    }
}
