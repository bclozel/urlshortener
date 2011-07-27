define(['lib/controller', 'lib/jquery/jquery.validate', 'repositories/shorturl.repository'], function(Controller) {


    return Controller.extend("CreateShorturlController", {
        template: 'shorturl/create.html',
	
        init: function() {
            this._displayCreateForm();
        },
        _displayCreateForm: function() {

            this.render();

            $('input#create-button').unbind();
            $('input#create-button').bind('click', $.proxy(this._createShortURL, this));
        },

        _createShortURL: function() {

            var validForm = $('form#create-form').validate({
                errorElement: 'span'
            }).form();
            
            if (validForm) {
                //$.route('#')
                ShortUrlRepository.create(function(){console.log("created")},$('input#create-text').val());
            }
        }
    });
});