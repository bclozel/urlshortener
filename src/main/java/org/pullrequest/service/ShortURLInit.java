package org.pullrequest.service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.inject.Named;
import javax.inject.Inject;
import org.resthub.core.util.PostInitialize;
import org.pullrequest.model.ShortURL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named("shortURLInit")
public class ShortURLInit {

    @Inject
    @Named("shortURLService")
    private ShortURLService sampleService;
    
    private Logger logger = LoggerFactory.getLogger(ShortURLInit.class);

    @PostInitialize
    public void init() {

        ShortURL sURL = null;
        try {
            URL url = new URL("http://pullrequest.org");
            sURL = new ShortURL("blog", url);
        } catch (MalformedURLException ex) {
            logger.error("could not parse URL", ex);
        }

        sampleService.create(sURL);
    }
}
