package org.pullrequest.shorturl.service;

import javax.inject.Named;
import javax.inject.Inject;
import org.pullrequest.shorturl.model.ShortURL;
import org.resthub.core.util.PostInitialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named("shortURLInit")
public class ShortURLInit {

    @Inject
    @Named("shortURLService")
    private ShortURLService shorturlService;
    
    private Logger logger = LoggerFactory.getLogger(ShortURLInit.class);

    @PostInitialize
    public void init() {

        
        ShortURL sURL1 = new ShortURL(null, "http://pullrequest.org");
        ShortURL sURL2 = new ShortURL(null, "http://www.github.com");
        
        shorturlService.create(sURL1);
        shorturlService.create(sURL2);
    }
}
