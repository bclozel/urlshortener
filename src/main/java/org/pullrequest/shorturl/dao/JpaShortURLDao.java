package org.pullrequest.shorturl.dao;

import javax.inject.Named;
import javax.persistence.Query;

import org.resthub.core.dao.GenericJpaDao;
import org.pullrequest.shorturl.model.ShortURL;

@Named("shortURLDao")
public class JpaShortURLDao extends GenericJpaDao<ShortURL, Long> implements ShortURLDao {

    @Override
    public ShortURL findByShortKey(String shortKey) {

        String queryString = "from " + this.getDomainClass().getName() + " where shortKey = :shortKey";
        Query q = this.getEntityManager().createQuery(queryString);
        q.setParameter("shortKey", shortKey);
        return (ShortURL) q.getSingleResult();
    }

    @Override
    public boolean existsWithShortKey(String shortKey) {
        String queryString = "from " + this.getDomainClass().getName() + " where shortKey = :shortKey";
        Query q = this.getEntityManager().createQuery(queryString);
        q.setParameter("shortKey", shortKey);
        return q.getResultList().size()>0;
    }
}

