package org.pullrequest.shorturl.dao;

import org.resthub.core.dao.GenericDao;
import org.pullrequest.shorturl.model.ShortURL;

public interface ShortURLDao extends GenericDao<ShortURL, Long> {

    public ShortURL findByShortKey(String shortKey);

    public boolean existsWithShortKey(String shortKey);
}
