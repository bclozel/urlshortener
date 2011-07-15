package org.pullrequest.service;

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
        ShortURL s = new ShortURL();
        s.setShortKey("testSample");
        sampleService.create(s);
    }   

}
