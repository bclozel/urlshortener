package org.pullrequest.dao;

import javax.inject.Named;

import org.resthub.core.dao.GenericJpaDao;
import org.pullrequest.dao.ShortURLDao;
import org.pullrequest.model.ShortURL;

@Named("shortURLDao")
public class JpaShortURLDao extends GenericJpaDao<ShortURL, Long> implements ShortURLDao {

}
