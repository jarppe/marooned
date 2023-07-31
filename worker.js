importScripts("shared.js");
(function(){
'use strict';var Jk=function(a){return Hk.then(function(b){return b.addAll($APP.Cf(a))})},Kk=function(a,b){return Hk.then(function(c){return c.put(a,b)})};var Hk=caches.open("v2");console.info("worker: initializing...");self.addEventListener("activate",function(a){console.info("worker: on-activate");var b=self.registration.navigationPreload;return $APP.u(b)?a.waitUntil(b.enable()):null});
self.addEventListener("install",function(a){console.info("worker: on-install");return a.waitUntil(Jk($APP.vf.h(new $APP.T(null,5,5,$APP.U,["./index.html","./styles.css","./shared.js","./app.js","./favicon.ico"],null),$APP.Ge($APP.X.h(function(b){$APP.P(b,0,null);b=$APP.P(b,1,null);return["./",$APP.y.g(b)].join("")},$APP.lg)))))});
self.addEventListener("fetch",function(a){return a.respondWith(function(){var b=a.request,c=b.url;return fetch(b).then(function(d){console.log("worker: on-fetch: loaded from server",c);return Kk(b,d.clone()).then(function(){return d})}).catch(function(){return caches.match(b).then(function(d){console.log("worker: on-fetch: loaded from cache",c);return d}).catch(function(d){console.log("worker: on-fetch: failed",c,d);throw d;})})}())});console.info("worker: done");
}).call(this);