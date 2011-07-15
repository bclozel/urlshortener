package org.pullrequest.test;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;

import org.resthub.core.test.service.AbstractServiceTest;
import org.pullrequest.model.ShortURL;
import org.pullrequest.service.ShortURLService;

public class SampleServiceTest extends AbstractServiceTest<ShortURL, Long, ShortURLService> {

    @Inject
    @Named("shortURLService")
    @Override
    public void setService(ShortURLService service) {
        super.setService(service);
    }

    @Override
    protected ShortURL createTestEntity() {
        
        ShortURL sURL = null;
        try {
            URL url = new URL("http://pullrequest.org");
            sURL = new ShortURL("key",url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(SampleServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sURL;
    }
    
    

    @Override
    public void testUpdate() {
        // Not implemented yet
    }
   
}
