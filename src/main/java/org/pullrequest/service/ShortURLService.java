package org.pullrequest.service;

import org.resthub.core.service.GenericService;
import org.pullrequest.model.ShortURL;

public interface ShortURLService extends GenericService<ShortURL, Long> {
    
    public ShortURL createShortURL(String url);
    
    public ShortURL createShortURL(String url, String shortKey);
    
    public ShortURL resolveURL(String shortKey);
}
