package org.pullrequest.shorturl.service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.inject.Inject;
import javax.inject.Named;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import org.slf4j.LoggerFactory;

import org.resthub.core.test.service.AbstractServiceTest;
import org.pullrequest.shorturl.model.ShortURL;
import org.slf4j.Logger;

public class ShortURLServiceTest extends AbstractServiceTest<ShortURL, Long, ShortURLService> {

    private int serial = 1;
    private Logger logger = LoggerFactory.getLogger(ShortURLServiceTest.class);
    private static final String uniqueKey = "abcdef";

    @Inject
    @Named("shortURLService")
    @Override
    public void setService(ShortURLService service) {
        super.setService(service);
    }

    @Override
    protected ShortURL createTestEntity() {

        ShortURL sURL = new ShortURL("key" + serial, "http://pullrequest.org");
        serial++;

        return sURL;
    }

    @Before
    @Override
    public void setUp() {
        super.setUp();
        
        ShortURL sURL = new ShortURL(uniqueKey, "http://pullrequest.org");
        service.create(sURL);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmptyURL() {

        service.create(new ShortURL(null, " "));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateInvalidURL() {

        service.create(new ShortURL(null, "invalidURL"));
    }

    @Test
    public void testCreateShortURL() {

        ShortURL sURL = service.create(new ShortURL(null, "http://pullrequest.org"));

        Assert.assertNotNull(sURL);
        Assert.assertTrue(sURL.getShortKey().length() > 0);

        ShortURL foundURL = service.findById(sURL.getId());

        Assert.assertEquals(sURL, foundURL);
        Assert.assertTrue(service.findAll().size() > 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateDuplicateShortURL() {

        ShortURL sURL = service.create(new ShortURL(uniqueKey, "http://pullrequest.org"));
    }

    @Override
    public void testUpdate() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
