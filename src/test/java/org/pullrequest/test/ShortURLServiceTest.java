package org.pullrequest.test;

import java.net.MalformedURLException;
import java.net.URL;
import javax.inject.Inject;
import javax.inject.Named;
import org.junit.Assert;
import org.junit.Test;

import org.pullrequest.model.ShortURL;
import org.pullrequest.service.ShortURLService;
import org.resthub.core.test.AbstractTransactionAwareTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShortURLServiceTest extends AbstractTransactionAwareTest {

    @Inject
    @Named("shortURLService")
    ShortURLService service;
    Logger logger = LoggerFactory.getLogger(ShortURLServiceTest.class);

    protected ShortURL createTestEntity() {

        ShortURL sURL = null;
        try {
            URL url = new URL("http://pullrequest.org");
            sURL = new ShortURL("key", url);
        } catch (MalformedURLException ex) {
        }

        return sURL;
    }

    @Test
    public void testCreateShortURL() {

        ShortURL sURL = createTestEntity();
        logger.error(sURL.toString());
        
        sURL = service.create(sURL);

        Assert.assertNotNull(sURL);
        Assert.assertTrue(sURL.getShortKey().length() > 0);

        ShortURL foundURL = service.findById(sURL.getId());

        Assert.assertEquals(sURL, foundURL);
        Assert.assertTrue(service.findAll().size() > 0);

        logger.error("number of URLs: " + service.findAll().size());
    }
}
