define(['lib/resthub', 'shorturl/create.js', 'shorturl/infos.js', 'shorturl/list.js'], function() {
        
    // Define routes
    $.route('#', function() {
        $('#form').create_shorturl().show();
        $('#content').html('<span>Loading...</span>').list_shorturl();
    });
            
    $.route('#/:shortkey', function(params) {
        $('#form').hide().html('');
        $('#content').infos_shorturl({shortkey:params.shortkey});
    });
    
    // Run current route
    $.route(location.hash);

});