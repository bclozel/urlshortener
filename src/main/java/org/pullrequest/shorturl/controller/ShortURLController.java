package org.pullrequest.shorturl.controller;

import com.sun.jersey.api.view.ImplicitProduces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.pullrequest.shorturl.model.ShortURL;
import org.resthub.web.response.PageResponse;

@Path("/")
@ImplicitProduces("text/html;qs=5")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public interface ShortURLController {

    @POST
    public ShortURL createShortURL(ShortURL shorturl);

    @GET
    @Path("/{shortKey}/infos")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ShortURL findByShortKey(@PathParam("shortKey") String shortKey);
    
    @GET
    @Path("/{shortKey}")
    @Consumes({MediaType.TEXT_HTML, MediaType.TEXT_PLAIN})
    public Response redirectByShortKey(@PathParam("shortKey") String shortKey);

    @DELETE
    @Path("/{shortKey}")
    public void delete(@PathParam("shortKey") String shortKey);

    @GET
    public PageResponse<ShortURL> findAll(@QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("size") @DefaultValue("5") Integer size);
}
