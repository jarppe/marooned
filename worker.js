importScripts("shared.js");
(function(){
'use strict';var Li=function(a){return caches.open("v2").then(function(b){return b.addAll($APP.vf(a))})},Mi=function(a,b){return caches.open("v2").then(function(c){return c.put(a,b)})},Ni=function(a){var b=$APP.$e(a,"request");return Promise.resolve(b).then(fetch).then(function(c){return Mi(b,c.clone()).then(function(){return c})}).catch(function(){return caches.match(b)}).catch(function(c){console.log("cache: cache-fetch: cache failed",b,c);throw c;})};console.info("worker: initializing...");self.addEventListener("activate",function(a){console.info("worker: activate");var b=a.waitUntil;var c=$APP.qc($APP.$e,self,new $APP.V(null,2,5,$APP.W,["registration","navigationPreload"],null));c=$APP.u(c)?c.enable():null;return b.call(a,c)});
self.addEventListener("install",function(a){console.info("worker: install");return a.waitUntil(Li($APP.of.h(new $APP.V(null,5,5,$APP.W,["./index.html","./styles.css","./shared.js","./app.js","./favicon.ico"],null),$APP.De($APP.Y.h(function(b){$APP.Q(b,0,null);b=$APP.Q(b,1,null);return["./",$APP.z.g(b)].join("")},$APP.Nf)))))});self.addEventListener("fetch",function(a){return a.respondWith(Ni(a))});self.addEventListener("message",function(a){return console.info("worker: message",a)});console.info("worker: init");
}).call(this);