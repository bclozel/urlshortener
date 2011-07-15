package org.pullrequest.dao;

import org.resthub.core.dao.GenericDao;
import org.pullrequest.model.ShortURL;

public interface ShortURLDao extends GenericDao<ShortURL, Long> {

    public ShortURL findByShortKey(String shortKey);

    public boolean existsWithShortKey(String shortKey);
}
