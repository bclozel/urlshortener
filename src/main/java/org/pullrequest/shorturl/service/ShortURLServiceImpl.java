package org.pullrequest.shorturl.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.inject.Inject;

import org.resthub.core.service.GenericServiceImpl;
import org.pullrequest.shorturl.dao.ShortURLDao;
import org.pullrequest.shorturl.model.ShortURL;
import org.springframework.util.Assert;

@Named("shortURLService")
public class ShortURLServiceImpl extends GenericServiceImpl<ShortURL, Long, ShortURLDao> implements ShortURLService {


    @Inject
    @Named("shortURLDao")
    @Override
    public void setDao(ShortURLDao dao) {
        this.dao = dao;
    }

    @Override
    public ShortURL createShortURL(String url) {

        Assert.hasLength(url, "URL should not be null");

        URL parsedURL;

        try {
            parsedURL = new URL(url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ShortURLServiceImpl.class.getName()).log(Level.WARNING, null, ex);
            throw new IllegalArgumentException(url + " is not a valid URL", ex);
        }

        long uid = CodecService.generateUID();
        String shortKey = CodecService.encode(uid);

        while (dao.existsWithShortKey(shortKey)) {
            uid = CodecService.generateUID();
            shortKey = CodecService.encode(uid);
        }
        
        ShortURL shortURL = new ShortURL(shortKey, parsedURL);

        return create(shortURL);

    }

    @Override
    public ShortURL createShortURL(String url, String shortKey) {
        
        Assert.hasLength(url, "URL should not be null");
        Assert.isTrue(shortKey.length() <= CodecService.MAX_LENGTH,"shortKey is too long");

        URL parsedURL;

        try {
            parsedURL = new URL(url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ShortURLServiceImpl.class.getName()).log(Level.WARNING, null, ex);
            throw new IllegalArgumentException(url + " is not a valid URL", ex);
        }


        if (dao.existsWithShortKey(shortKey)) {
            throw new IllegalArgumentException("a shortened URL already exists with key = "+ shortKey);
        }

        ShortURL shortURL = new ShortURL(shortKey, parsedURL);

        return create(shortURL);       
    }

    @Override
    public ShortURL resolveURL(String shortKey) {
        Assert.hasLength(shortKey, "cannot find a empty shortKey");

        return dao.findByShortKey(shortKey);
    }


}
