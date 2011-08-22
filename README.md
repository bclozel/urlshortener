# urlshortener

You'd think urlshortener is yet another flavor of URL shortener made with "the coolest framework".

urlshortener is in fact:

* a sample application made with RESThub and RESThub-js (so the "cool framework" part was actually right)
* an experiment on making a javascript application AJAX-crawlable 

# RESThub and RESThub-js

Both frameworks are hosted on [github](http://github.com/pullrequest) and their documentation is available
on [the official RESThub website](http://resthub.org).

# Crawlers and AJAX crawling

Usually AJAX content/javascript based UIs and web crawlers [don't mix](http://code.google.com/web/ajaxcrawling/docs/learn-more.html). 
Google engineers dealt with this problem with [a "contract" that any website can implement](http://code.google.com/web/ajaxcrawling/docs/getting-started.html) - basically a way of telling web spiders how to crawl your website.

This project is using a Filter with an embedded [HTMLUnit](http://htmlunit.sourceforge.net/) that [generates HTML snapshots](http://code.google.com/web/ajaxcrawling/docs/html-snapshot.html) of your website.

# Doing it "the right way™"

This technique fits pretty well for legacy applications. I don't think it's the best solution for a brand new application, though...