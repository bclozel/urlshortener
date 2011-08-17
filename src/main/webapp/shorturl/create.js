define(['lib/controller', 'lib/jquery/jquery.validate', 'repositories/shorturl.repository'], function(Controller) {


    return Controller.extend("CreateShorturlController", {
        template: 'shorturl/create.html',
        shorturl: {},
        
        init: function() {
            this._displayCreateForm();
        },
        _displayCreateForm: function() {

            this.render();

            $('#create-form').unbind();
            $('#create-form').bind('submit', $.proxy(this._createShortURL, this));
        },

        _createShortURL: function() {

                
            this.shorturl.url = $('input#create-text').val();
            ShortUrlRepository.save(function(){
                $.route('#!');
                },$.toJSON(this.shorturl));
            
            return false;
        }
    });
});