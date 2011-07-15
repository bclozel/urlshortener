package org.pullrequest.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.inject.Inject;
import org.resthub.core.util.PostInitialize;
import org.pullrequest.model.ShortURL;

@Named("sampleInit")
public class ShortURLInit {

    @Inject
    @Named("shortURLService")
    private ShortURLService sampleService;

    @PostInitialize
    public void init() {

        ShortURL sURL = null;
        try {
            URL url = new URL("http://pullrequest.org");
            sURL = new ShortURL("blog", url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ShortURLInit.class.getName()).log(Level.SEVERE, null, ex);
        }

        sampleService.create(sURL);
    }
}
