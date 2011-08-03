define(['lib/resthub', 'shorturl/create.js', 'shorturl/infos.js', 'shorturl/list.js'], function() {
        
    // Define routes
    $.route('#!', function() {
        $('#form').create_shorturl().show();
        $('#content').html('<span>Loading...</span>').list_shorturl();
    });
            
    $.route('#!/:shortkey', function(params) {
        $('#form').hide().html('');
        $('#content').infos_shorturl({
            shortkey:params.shortkey
        });
    });
    
    // Run current route
    $.route(location.hash);

});

/*
$(document).ready(function() {

    // Tabs Activation for skeleton
    var tabs = $('ul.tabs');

    tabs.each(function(i) {
        var tab = $(this).find('> li > a');
        tab.click(function(e) {

            var contentLocation = $(this).attr('href') + "Tab";

            if(contentLocation.charAt(0)=="#") {
                e.preventDefault();
                tab.removeClass('active');
                $(this).addClass('active');
                $(contentLocation).show().addClass('active').siblings().hide().removeClass('active');
            } 
        });
    }); 
});
*/