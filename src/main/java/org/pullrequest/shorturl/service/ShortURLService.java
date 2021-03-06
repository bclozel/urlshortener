package org.pullrequest.shorturl.service;

import org.resthub.core.service.GenericService;
import org.pullrequest.shorturl.model.ShortURL;

public interface ShortURLService extends GenericService<ShortURL, Long> {
    
    public ShortURL resolveURL(String shortKey);
}
