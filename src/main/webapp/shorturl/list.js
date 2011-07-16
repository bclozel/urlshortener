define(['lib/controller', 'lib/jquery/jquery.validate', 'repositories/shorturl.repository'], function(Controller) {

    return Controller.extend("ListShorturlController", {
        template: 'shorturl/list.html',
	
        init: function() {
            ShortUrlRepository.list($.proxy(this, '_listShortURLs'));
        },
        _listShortURLs: function(shorturls) {

            this.render({shorturls : shorturls.elements});
        }
    });
});