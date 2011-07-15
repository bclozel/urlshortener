package org.pullrequest.dao;

import javax.inject.Named;

import org.resthub.core.dao.GenericJpaDao;
import org.pullrequest.dao.SampleDao;
import org.pullrequest.model.Sample;

@Named("sampleDao")
public class JpaSampleDao extends GenericJpaDao<Sample, Long> implements SampleDao {

}
