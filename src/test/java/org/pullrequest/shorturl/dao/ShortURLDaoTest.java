package org.pullrequest.shorturl.dao;

import java.net.MalformedURLException;
import java.net.URL;
import javax.inject.Inject;
import javax.inject.Named;
import org.junit.Assert;
import org.junit.Test;
import org.pullrequest.shorturl.model.ShortURL;
import org.resthub.core.test.dao.AbstractDaoTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShortURLDaoTest extends AbstractDaoTest<ShortURL, Long, ShortURLDao> {

    private int serial = 1;
    private Logger logger = LoggerFactory.getLogger(ShortURLDaoTest.class);
    private static final String uniqueKey = "abcdef";

    @Override
    @Inject
    @Named("shortURLDao")
    public void setDao(ShortURLDao shortURLDao) {
        this.dao = shortURLDao;
    }

    private ShortURL createShortURL(String key, String url) {

        ShortURL sURL = null;
        try {
            URL parsedurl = new URL(url);
            sURL = new ShortURL(key, parsedurl);
        } catch (MalformedURLException ex) {
            logger.error("could not parse url", ex);
        }

        return sURL;
    }

    @Override
    protected ShortURL createTestEntity() {

        ShortURL sURL = createShortURL("key" + serial, "http://pullrequest.org");
        serial++;

        return sURL;
    }

    @Override
    public void testUpdate() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Test
    public void testFindByShortKey() {

        ShortURL sURL = createShortURL(uniqueKey,"http://example.org");
        this.dao.save(sURL);
        
        ShortURL anotherURL = this.dao.findByShortKey(uniqueKey);
        
        Assert.assertNotNull("shorturl should be created",anotherURL);
        Assert.assertEquals("shortkeys should be equal",sURL.getShortKey(),anotherURL.getShortKey());
    }
    
    @Test
    public void testExistsWithShortKey() {

        ShortURL sURL = createShortURL(uniqueKey,"http://example.org");
        this.dao.save(sURL);
        
        boolean exists = this.dao.existsWithShortKey(uniqueKey);
        
        Assert.assertTrue("a shorturl should exist with this shortkey",exists);
    }
}
