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
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(readOnly=false)
    public ShortURL create(ShortURL shorturl) {

        Assert.hasLength(shorturl.getUrl().toString(), "URL should not be null");
        String shortKey = shorturl.getShortKey();
        
        if (shortKey != null) {

            Assert.isTrue(shortKey.length() <= CodecService.MAX_LENGTH, "shortKey is too long");

            if (dao.existsWithShortKey(shortKey)) {
                throw new IllegalArgumentException("a shortened URL already exists with key = " + shortKey);
            }
        } else {
            long uid = CodecService.generateUID();
            shorturl.setShortKey(CodecService.encode(uid));   
        }

        return dao.save(shorturl);
    }

    @Override
    public ShortURL resolveURL(String shortKey) {
        Assert.hasLength(shortKey, "cannot find an empty shortKey");

        return dao.findByShortKey(shortKey);
    }
}
