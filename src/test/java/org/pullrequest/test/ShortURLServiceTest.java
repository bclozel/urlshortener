package org.pullrequest.test;

import java.net.MalformedURLException;
import java.net.URL;
import javax.inject.Inject;
import javax.inject.Named;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import org.slf4j.LoggerFactory;

import org.resthub.core.test.service.AbstractServiceTest;
import org.pullrequest.model.ShortURL;
import org.pullrequest.service.ShortURLService;
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

        ShortURL sURL = null;
        try {
            URL url = new URL("http://pullrequest.org");
            sURL = new ShortURL("key" + serial, url);
            serial++;
        } catch (MalformedURLException ex) {
            logger.error("could not parse url", ex);
        }

        return sURL;
    }

    @Before
    @Override
    public void setUp() {
        super.setUp();

        ShortURL sURL = null;
        try {
            URL url = new URL("http://pullrequest.org");
            sURL = new ShortURL(uniqueKey, url);
        } catch (MalformedURLException ex) {
            logger.error("could not parse url", ex);
        }

        service.create(sURL);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmptyURL() {

        service.createShortURL(" ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateInvalidURL() {

        service.createShortURL("invalidURL");
    }

    @Test
    public void testCreateShortURL() {

        ShortURL sURL = service.createShortURL("http://pullrequest.org");

        Assert.assertNotNull(sURL);
        Assert.assertTrue(sURL.getShortKey().length() > 0);

        ShortURL foundURL = service.findById(sURL.getId());

        Assert.assertEquals(sURL, foundURL);
        Assert.assertTrue(service.findAll().size() > 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateDuplicateShortURL() {


        ShortURL aURL = service.createShortURL("http://pullrequest.org", "abcdef");
        Assert.assertNotNull(service.findById(aURL.getId()));
    }

    @Override
    public void testUpdate() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
