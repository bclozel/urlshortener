define(['lib/controller', 'lib/jquery/jquery.validate', 'repositories/shorturl.repository'], function(Controller) {


    return Controller.extend("CreateShorturlController", {
        template: 'shorturl/create.html',
        shorturl: {},
        
        init: function() {
            this._displayCreateForm();
        },
        _displayCreateForm: function() {

            this.render();

            $('input#create-button').unbind();
            $('input#create-button').bind('click', $.proxy(this._createShortURL, this));
        },

        _createShortURL: function() {

                
            this.shorturl.url = $('input#create-text').val();
            ShortUrlRepository.save(function(){
                console.log("created")
                },$.toJSON(this.shorturl));
            $.route('#!');
        }
    });
});