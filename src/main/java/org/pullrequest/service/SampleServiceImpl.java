package org.pullrequest.service;

import javax.inject.Named;
import javax.inject.Inject;

import org.resthub.core.service.GenericServiceImpl;
import org.pullrequest.dao.SampleDao;
import org.pullrequest.model.Sample;

@Named("sampleService")
public class SampleServiceImpl extends GenericServiceImpl<Sample, Long, SampleDao> implements SampleService {

    @Inject
    @Named("sampleDao")
    @Override
    public void setDao(SampleDao dao) {
        this.dao = dao;
    }

}
