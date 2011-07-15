package org.pullrequest.service;

import javax.inject.Named;
import javax.inject.Inject;

import org.resthub.core.service.GenericServiceImpl;
import org.pullrequest.dao.ShortURLDao;
import org.pullrequest.model.ShortURL;

@Named("shortURLService")
public class ShortURLServiceImpl extends GenericServiceImpl<ShortURL, Long, ShortURLDao> implements ShortURLService {

    @Inject
    @Named("shortURLDao")
    @Override
    public void setDao(ShortURLDao dao) {
        this.dao = dao;
    }

}
