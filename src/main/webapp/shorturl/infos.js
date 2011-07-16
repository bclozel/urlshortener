define([ 'lib/controller', 'repositories/shorturl.repository' ], function(Controller, SampleRepository) {
	return Controller.extend("InfosShorturlController", {
		template : 'shorturl/infos.html',
                
                shortkey:null,
		
		init : function() {
			ShortUrlRepository.findByShortKey($.proxy(this, '_showShortUrl'),this.shortkey);
		},
		_showShortUrl: function(shorturl) {
			this.render({shorturl : shorturl});
		}
	});
});
