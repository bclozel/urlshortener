define([ 'lib/repository' ], function(Repository) {
    return Repository.extend("ShortUrlRepository", {
        root : 'api/',
                
        findByShortKey : function(callback, shortkey) {
            this._get(this.root + shortkey +'/infos', callback);
        },
                
        createNamed : function(callback, url, shortkey) {
            this._post(this.root, callback, {
                shortKey:shortkey,
                url:url
            });
        },                

        create : function(callback, url) {
            this._post(this.root, callback, {
                'url':url
            });
        }
                
    }, {});
});