(function(){
'use strict';var Pf=function(a,b){return $APP.aa[a]=b},Qf=function(a){return null==a},Rf=function(a){if(null!=a&&null!=a.va)a=a.va(a);else{var b=Rf[$APP.q(null==a?null:a)];if(null!=b)a=b.g?b.g(a):b.call(null,a);else if(b=Rf._,null!=b)a=b.g?b.g(a):b.call(null,a);else throw $APP.x("IStack.-peek",a);}return a},Sf=function(a){if(null!=a&&null!=a.wa)a=a.wa(a);else{var b=Sf[$APP.q(null==a?null:a)];if(null!=b)a=b.g?b.g(a):b.call(null,a);else if(b=Sf._,null!=b)a=b.g?b.g(a):b.call(null,a);else throw $APP.x("IStack.-pop",
a);}return a},Tf=function(){},Uf=function(a){if(null!=a&&null!=a.Ta)a=a.Ta(a);else{var b=Uf[$APP.q(null==a?null:a)];if(null!=b)a=b.g?b.g(a):b.call(null,a);else if(b=Uf._,null!=b)a=b.g?b.g(a):b.call(null,a);else throw $APP.x("IReversible.-rseq",a);}return a},Vf=function(a,b){if(null!=$APP.Lf&&null!=$APP.Lf.xb)$APP.Lf.xb($APP.Lf,a,b);else{var c=Vf[$APP.q(null==$APP.Lf?null:$APP.Lf)];if(null!=c)c.j?c.j($APP.Lf,a,b):c.call(null,$APP.Lf,a,b);else if(c=Vf._,null!=c)c.j?c.j($APP.Lf,a,b):c.call(null,$APP.Lf,
a,b);else throw $APP.x("IWatchable.-add-watch",$APP.Lf);}},Wf=function(a,b){if(null!=a&&null!=a.Sb)a=a.Sb(a,b);else{var c=Wf[$APP.q(null==a?null:a)];if(null!=c)a=c.h?c.h(a,b):c.call(null,a,b);else if(c=Wf._,null!=c)a=c.h?c.h(a,b):c.call(null,a,b);else throw $APP.x("IReset.-reset!",a);}return a},Yf=function(a){return a+1},Zf=function(a){if(null==$APP.ra)throw Error("No *print-fn* fn set for evaluation environment");$APP.ra.call(null,a)},$f=function(){return function(){function a(f,h){return $APP.ua(Qf.h?
Qf.h(f,h):Qf.call(null,f,h))}function b(f){return $APP.ua(Qf.g?Qf.g(f):Qf.call(null,f))}function c(){return $APP.ua(Qf.C?Qf.C():Qf.call(null))}var d=null,e=function(){function f(k,l,m){var n=null;if(2<arguments.length){n=0;for(var p=Array(arguments.length-2);n<p.length;)p[n]=arguments[n+2],++n;n=new $APP.E(p,0,null)}return h.call(this,k,l,n)}function h(k,l,m){return $APP.ua($APP.Vc(Qf,k,l,m))}f.I=2;f.G=function(k){var l=$APP.G(k);k=$APP.J(k);var m=$APP.G(k);k=$APP.Gb(k);return h(l,m,k)};f.s=h;return f}();
d=function(f,h,k){switch(arguments.length){case 0:return c.call(this);case 1:return b.call(this,f);case 2:return a.call(this,f,h);default:var l=null;if(2<arguments.length){l=0;for(var m=Array(arguments.length-2);l<m.length;)m[l]=arguments[l+2],++l;l=new $APP.E(m,0,null)}return e.s(f,h,l)}throw Error("Invalid arity: "+arguments.length);};d.I=2;d.G=e.G;d.C=c;d.g=b;d.h=a;d.s=e.s;return d}()},bg=function(a){var b=ag;return function(){function c(l,m,n){return b.B?b.B(a,l,m,n):b.call(null,a,l,m,n)}function d(l,
m){return b.j?b.j(a,l,m):b.call(null,a,l,m)}function e(l){return b.h?b.h(a,l):b.call(null,a,l)}function f(){return b.g?b.g(a):b.call(null,a)}var h=null,k=function(){function l(n,p,t,v){var y=null;if(3<arguments.length){y=0;for(var A=Array(arguments.length-3);y<A.length;)A[y]=arguments[y+3],++y;y=new $APP.E(A,0,null)}return m.call(this,n,p,t,y)}function m(n,p,t,v){return $APP.Xc(b,a,n,p,t,$APP.P([v]))}l.I=3;l.G=function(n){var p=$APP.G(n);n=$APP.J(n);var t=$APP.G(n);n=$APP.J(n);var v=$APP.G(n);n=$APP.Gb(n);
return m(p,t,v,n)};l.s=m;return l}();h=function(l,m,n,p){switch(arguments.length){case 0:return f.call(this);case 1:return e.call(this,l);case 2:return d.call(this,l,m);case 3:return c.call(this,l,m,n);default:var t=null;if(3<arguments.length){t=0;for(var v=Array(arguments.length-3);t<v.length;)v[t]=arguments[t+3],++t;t=new $APP.E(v,0,null)}return k.s(l,m,n,t)}throw Error("Invalid arity: "+arguments.length);};h.I=3;h.G=k.G;h.C=f;h.g=e;h.h=d;h.j=c;h.s=k.s;return h}()},dg=function(a){var b=/([a-z]+)\(([^)]+)\)\s*/;
if("string"===typeof a)return cg(b,a);throw new TypeError("re-seq must match against a string.");},eg=function(a,b){return new $APP.Fc(null,function(){var c=$APP.F(b);if(c){if($APP.hc(c)){for(var d=$APP.mb(c),e=$APP.K(d),f=new $APP.Hc(Array(e)),h=0;;)if(h<e){var k=$APP.Yb(d,h);k=a.g?a.g(k):a.call(null,k);$APP.u(k)&&(k=$APP.Yb(d,h),f.add(k));h+=1}else break;return $APP.Kc(f.la(),eg(a,$APP.nb(c)))}d=$APP.G(c);c=$APP.Gb(c);return $APP.u(a.g?a.g(d):a.call(null,d))?$APP.R(d,eg(a,c)):eg(a,c)}return null},
null)},fg=function(a){return(null!=a?a.l&134217728||$APP.D===a.oc||(a.l?0:$APP.w(Tf,a)):$APP.w(Tf,a))?(a=Uf(a))?a:$APP.H:$APP.qc($APP.gd,$APP.H,a)},gg=function(a){var b=$APP.Z.j($APP.pa(),$APP.la,!1);Zf($APP.Se(a,b));$APP.u($APP.qa)?(a=$APP.pa(),Zf("\n"),a=($APP.kc.h(a,$APP.ka),null)):a=null;return a},hg=function(a,b){if(a instanceof $APP.fd){var c=a.cc;if(null!=c&&!$APP.u(c.g?c.g(b):c.call(null,b)))throw Error("Validator rejected reference state");c=a.state;a.state=b;if(null!=a.rb)a:for(var d=$APP.F(a.rb),
e=null,f=0,h=0;;)if(h<f){var k=e.J(null,h),l=$APP.Q(k,0,null);k=$APP.Q(k,1,null);k.B?k.B(l,a,c,b):k.call(null,l,a,c,b);h+=1}else if(d=$APP.F(d))$APP.hc(d)?(e=$APP.mb(d),d=$APP.nb(d),l=e,f=$APP.K(e),e=l):(e=$APP.G(d),l=$APP.Q(e,0,null),k=$APP.Q(e,1,null),k.B?k.B(l,a,c,b):k.call(null,l,a,c,b),d=$APP.J(d),e=null,f=0),h=0;else break a;return b}return Wf(a,b)},kg=function(a){null==ig&&(ig=new $APP.fd(0));var b=$APP.Te.g([$APP.z.g("G__"),$APP.z.g(jg.h(ig,Yf))].join(""));Vf(b,a)},lg=function(a){this.f=a;
this.value=null;this.l=2147516416;this.D=1},mg=function(a){if("string"===typeof a)return $APP.Fe(/[\x00-\x20]*[+-]?NaN[\x00-\x20]*/,a)?NaN:$APP.Fe(/[\x00-\x20]*[+-]?(Infinity|((\d+\.?\d*|\.\d+)([eE][+-]?\d+)?)[dDfF]?)[\x00-\x20]*/,a)?parseFloat(a):null;throw Error(["Expected string, got: ",$APP.z.g(null==a?"nil":$APP.q(a))].join(""));},ng=function(a,b,c){kg(function(d,e,f,h){d=$APP.kc.h(f,a);var k=$APP.kc.h(h,a);return $APP.M.h(d,k)?null:setTimeout(function(){return $APP.Uc(b,k,c)},0)})},og=function(a){for(var b=
[],c=arguments.length,d=0;;)if(d<c)b.push(arguments[d]),d+=1;else break;ng(arguments[0],arguments[1],2<b.length?new $APP.E(b.slice(2),0,null):null)},pg=function(a){for(var b=[],c=arguments.length,d=0;;)if(d<c)b.push(arguments[d]),d+=1;else break;a:{c=arguments[0];d=arguments[1];var e=arguments[2];for(b=3<b.length?new $APP.E(b.slice(3),0,null):null;;)if(c[d]=e,$APP.F(b))d=$APP.G(b),e=$APP.G($APP.J(b)),b=$APP.J($APP.J(b));else break a}},qg=function(a){return"string"===typeof a?document.getElementById(a):
a},rg=function(a){return $APP.$e(qg(a),"classList")},sg=function(a,b){a=rg(a);b=$APP.F(b);for(var c=null,d=0,e=0;;)if(e<d){var f=c.J(null,e);a.add(f);e+=1}else if(b=$APP.F(b))c=b,$APP.hc(c)?(b=$APP.mb(c),e=$APP.nb(c),c=b,d=$APP.K(b),b=e):(b=$APP.G(c),a.add(b),b=$APP.J(c),c=null,d=0),e=0;else break},ug=function(){var a=$APP.P([tg,"none"]),b=qg("fullscreen"),c=$APP.F(a);$APP.G(c);c=$APP.J(c);$APP.G(c);for($APP.J(c);;){c=$APP.F(a);a=$APP.G(c);c=$APP.J(c);var d=$APP.G(c);c=$APP.J(c);b.setAttribute($APP.Ee(a),
$APP.z.g(d));if($APP.F(c))a=c;else break}},vg=function(a,b){a=qg(a);pg(a,"textContent",b);return a},wg=function(a,b){a=qg(a);b=$APP.F(b);for(var c=null,d=0,e=0;;)if(e<d){var f=c.J(null,e);a.append(f);e+=1}else if(b=$APP.F(b))c=b,$APP.hc(c)?(b=$APP.mb(c),e=$APP.nb(c),c=b,d=$APP.K(b),b=e):(b=$APP.G(c),a.append(b),b=$APP.J(c),c=null,d=0),e=0;else break},xg=function(a,b){b=$APP.ec($APP.G(b))?new $APP.V(null,2,5,$APP.W,[$APP.G(b),$APP.Gb(b)],null):new $APP.V(null,2,5,$APP.W,[null,b],null);var c=$APP.Q(b,
0,null);b=$APP.Q(b,1,null);a=document.createElement(a);c=$APP.F(c);for(var d=null,e=0,f=0;;)if(f<e){var h=d.J(null,f),k=$APP.Q(h,0,null);h=$APP.Q(h,1,null);$APP.M.h(k,$APP.Bf)?sg(a,$APP.Y.h($APP.z,$APP.dc(h)?eg($f(),h):$APP.R(h,null))):a.setAttribute($APP.Ee(k),$APP.z.g(h));f+=1}else if(c=$APP.F(c))$APP.hc(c)?(e=$APP.mb(c),c=$APP.nb(c),d=e,e=$APP.K(e)):(e=$APP.G(c),d=$APP.Q(e,0,null),e=$APP.Q(e,1,null),$APP.M.h(d,$APP.Bf)?sg(a,$APP.Y.h($APP.z,$APP.dc(e)?eg($f(),e):$APP.R(e,null))):a.setAttribute($APP.Ee(d),
$APP.z.g(e)),c=$APP.J(c),d=null,e=0),f=0;else break;b=$APP.F(b);c=null;for(e=d=0;;)if(e<d)f=c.J(null,e),$APP.dc(f)?wg(a,f):wg(a,$APP.P([f])),e+=1;else if(b=$APP.F(b))c=b,$APP.hc(c)?(b=$APP.mb(c),e=$APP.nb(c),c=b,d=$APP.K(b),b=e):(b=$APP.G(c),$APP.dc(b)?wg(a,b):wg(a,$APP.P([b])),b=$APP.J(c),c=null,d=0),e=0;else break;return a},yg=function(a,b,c){a=qg(a);a.addEventListener($APP.Ee(b),c,null);return a},zg=function(a,b,c){return yg(a,b,c)},Ag=function(a){a=$APP.Of.g?$APP.Of.g(a):$APP.Of.call(null,a);
$APP.u(null)&&a.stereo(null);return a.play()},Bg=function(a){console.log("set-sound-on",a);$APP.u(a)?rg("sound").add("active"):rg("sound").remove("active");return $APP.Mf.Howler.mute($APP.ua(a))},Dg=function(){zg("sound",$APP.Af,function(){return jg.B($APP.Lf,Cg,$APP.xf,$APP.ua)});og($APP.xf,Bg);jg.B($APP.Lf,$APP.Z,$APP.xf,!0)},Eg=function(a){if($APP.u(a))return rg("fullscreen").add("active"),qg("wrapper").requestFullscreen().then(function(){return gg($APP.P(["fullscreen: success"]))},function(){return gg($APP.P(["fullscreen: rejected"]))});
rg("fullscreen").remove("active");return document.exitFullscreen().then(function(){return gg($APP.P(["fullscreen: success"]))},function(){return gg($APP.P(["fullscreen: rejected"]))})},Hg=function(){var a=null!=$APP.$e(document,"exitFullscreen"),b=null!=$APP.$e(document,"fullscreenElement");a||ug();a&&(zg("fullscreen",$APP.Af,function(){return jg.B($APP.Lf,Cg,Fg,$APP.ua)}),og(Fg,Eg));jg.j($APP.Lf,$APP.tf,new $APP.r(null,2,[Gg,a,Fg,b],null))},Kg=function(a){return function(b){if(!$APP.u($APP.$e(b,
"repeat"))){var c=$APP.$e(b,"ctrlKey"),d=$APP.$e(b,"metaKey");$APP.ua($APP.$e(b,"shiftKey"))&&$APP.ua(c)&&$APP.ua(d)&&(c=function(){switch($APP.$e(b,"code")){case "ArrowUp":return $APP.Hf;case "Space":return $APP.Ff;case "ArrowLeft":return $APP.Kf;case "ArrowRight":return $APP.If;case "KeyD":return Ig;case "KeyR":return Jg;default:return null}}(),$APP.u(c)&&(b.preventDefault(),jg.B($APP.Lf,$APP.Z,c,a)))}return null}},Lg=function(a){return function(b){b.preventDefault();return jg.B($APP.Lf,$APP.Z,
a,!0)}},Mg=function(a){return function(b){b.preventDefault();return jg.B($APP.Lf,$APP.Z,a,!1)}},Ng=function(a){return function(b){b.preventDefault();console.log("on-touch-start",$APP.Ee(a));return jg.B($APP.Lf,$APP.Z,a,!0)}},Og=function(a){return function(b){b.preventDefault();console.log("on-touch-end",$APP.Ee(a));return jg.B($APP.Lf,$APP.Z,a,!1)}},Pg=function(a){return function(b){b.preventDefault();console.log("on-touch-cancel",$APP.Ee(a));return jg.B($APP.Lf,$APP.Z,a,!1)}},Qg=function(a){return $APP.u(a)?
$APP.qc(function(b,c){$APP.Q(c,0,null);var d=$APP.Q(c,1,null),e=$APP.Q(c,2,null);c=$APP.Z.j;d=$APP.Ne.g(d);var f=$APP.Y.h,h=/[\s,]/;e="/(?:)/"===$APP.z.g(h)?$APP.gd.h($APP.wd($APP.R("",$APP.Y.h($APP.z,$APP.F(e)))),""):$APP.wd($APP.z.g(e).split(h));if(1<$APP.K(e))a:for(;;)if(""===(null==e?null:Rf(e)))e=null==e?null:Sf(e);else break a;return c.call($APP.Z,b,d,f.call($APP.Y,mg,e))},$APP.ad,dg(a)):null},Sg=function(a){return $APP.qc(function(b,c){var d=c.g?c.g(a):c.call(null,a);if($APP.u(d)){b=$APP.z.g(b);
c=$APP.Ee(c);var e=$APP.z,f=e.g;if($APP.dc(d))a:{var h=new $APP.ja;for(d=$APP.F(d);;)if(null!=d)h.append($APP.z.g($APP.G(d))),d=$APP.J(d),null!=d&&h.append(" ");else{d=h.toString();break a}}b=[b,c,"(",f.call(e,d),") "].join("")}return b},"",Rg)},Tg=function(a){return $APP.qc(function(b,c){var d=$APP.ia(a,c);return $APP.u(d)?$APP.Z.j(b,c,d):b},$APP.ad,Rg)},Vg=function(a,b){return $APP.qc(function(c,d){var e=$APP.Q(d,0,null);d=$APP.Q(d,1,null);return $APP.M.h(e,Ug)?Qg(d):$APP.Z.j(c,e,d)},Tg(a),b)},
Wg=function(a,b){b=$APP.F(b);for(var c=null,d=0,e=0;;)if(e<d){var f=c.J(null,e);a.appendChild(f);e+=1}else if(b=$APP.F(b))c=b,$APP.hc(c)?(b=$APP.mb(c),e=$APP.nb(c),c=b,d=$APP.K(b),b=e):(b=$APP.G(c),a.appendChild(b),b=$APP.J(c),c=null,d=0),e=0;else break},Yg=function(a){return Cg.j(a,Xg,function(b){return $APP.gc(b)?$APP.qc(function(c,d){var e=$APP.Q(d,0,null);d=$APP.Q(d,1,null);return[$APP.z.g(c),$APP.z.g(e),",",$APP.z.g(d)," "].join("")},"",b):b})},Zg=function(a){for(var b=[],c=arguments.length,
d=0;;)if(d<c)b.push(arguments[d]),d+=1;else break;b=1<b.length?new $APP.E(b.slice(1),0,null):null;return $APP.Vc(ag,"polygon",Yg(arguments[0]),b)},$g=function(a){return $APP.u(a)?a.toFixed(2):"-"},ah=function(a){return $APP.u(a)?"ON":"OFF"},fh=function(){console.log(bh);wg("wrapper",$APP.P([xg("div",$APP.P([new $APP.r(null,1,[ch,"debug"],null),xg("table",$APP.P([xg("tbody",$APP.P([function(){return function c(b){return new $APP.Fc(null,function(){for(;;){var d=$APP.F(b);if(d){if($APP.hc(d)){var e=
$APP.mb(d),f=$APP.K(e),h=new $APP.Hc(Array(f));a:for(var k=0;;)if(k<f){var l=$APP.Yb(e,k);l=$APP.Q(l,0,null);l=xg("tr",$APP.P([xg("td",$APP.P([new $APP.r(null,1,[dh,l],null),$APP.Se($APP.P([l]),$APP.pa())])),xg("td",$APP.P([new $APP.r(null,1,[ch,["debug-data-",$APP.z.g(l)].join("")],null),"-"]))]));h.add(l);k+=1}else{e=!0;break a}return e?$APP.Kc(h.la(),c($APP.nb(d))):$APP.Kc(h.la(),null)}h=$APP.G(d);h=$APP.Q(h,0,null);return $APP.R(xg("tr",$APP.P([xg("td",$APP.P([new $APP.r(null,1,[dh,h],null),$APP.Se($APP.P([h]),
$APP.pa())])),xg("td",$APP.P([new $APP.r(null,1,[ch,["debug-data-",$APP.z.g(h)].join("")],null),"-"]))])),c($APP.Gb(d)))}return null}},null)}(eh)}()]))]))]))]))},hh=function(a){if($APP.u(gh.g(a)))for(var b=$APP.F(eh),c=null,d=0,e=0;;)if(e<d){var f=c.J(null,e),h=$APP.Q(f,0,null),k=$APP.Q(f,1,null);vg(["debug-data-",$APP.z.g(h)].join(""),function(){var n=h.g?h.g(a):h.call(null,a);return k.g?k.g(n):k.call(null,n)}());e+=1}else if(b=$APP.F(b)){c=b;if($APP.hc(c))b=$APP.mb(c),e=$APP.nb(c),c=b,d=$APP.K(b),
b=e;else{b=$APP.G(c);var l=$APP.Q(b,0,null),m=$APP.Q(b,1,null);vg(["debug-data-",$APP.z.g(l)].join(""),function(){var n=l.g?l.g(a):l.call(null,a);return m.g?m.g(n):m.call(null,n)}());b=$APP.J(c);c=null;d=0}e=0}else break;return a},kh=function(){og(Ig,function(a){console.log(":debug-toggle",a);return $APP.u(a)?jg.B($APP.Lf,Cg,gh,ih.h(function(b){if($APP.u(b))fh();else{console.log(jh);var c=qg("wrapper"),d=qg("debug");$APP.u($APP.u(c)?d:c)&&c.removeChild(d)}return b},$APP.ua)):null})},ph=function(){var a=
$APP.Sa($APP.Lf);a=lh.g(a);var b=qg("wrapper"),c=$APP.$e(b,"clientWidth");b=$APP.$e(b,"clientHeight");var d=c/2E3;var e=b/1E3;d=d<e?d:e;mh.s(qg("game"),$APP.P([nh,["0 0 ",$APP.z.g(c)," ",$APP.z.g(b)].join("")]));return mh.s(a,$APP.P([oh,d]))},Eh=function(a,b,c){return qh(new $APP.r(null,3,[rh,new $APP.V(null,2,5,$APP.W,[a,b],null),sh,c,tg,"none"],null),th(new $APP.r(null,5,[uh,"yellow",vh,0,wh,0,xh,3,yh,3],null),zh(new $APP.r(null,4,[Ah,"ry",Bh,"3;6;3",Ch,"100ms",Dh,"indefinite"],null)),zh(new $APP.r(null,
4,[Ah,"fill-opacity",Bh,"0.5;1.0;0.5",Ch,"100ms",Dh,"indefinite"],null))))},Xh=function(){var a=Fh(new $APP.r(null,3,[Gh,"none",uh,"green",Hh,"m 2025,850 100,100 h 450 l 300,-300 h 500 l 150,250 h 800 l 150,-250 h 450 l 150,300 H 7125 V 152 l -650,-2 v 550 l 50,50 h 350 L 6975,650 V 400 l -50,-50 h -200 l -50,50 v 200 h -50 V 350 l 50,-50 h 300 l 50,50 V 700 L 6925,800 H 6225 L 6075,650 V 100 H 5075 L 4925,400 H 4475 L 4335,150 H 3525 L 3375,300 H 2625 V 650 L 2475,800 H 2175 V 600 h 300 V 350 H 2175 L 2025,450 V 700 H 1325 L 775,500 1325,300 h 700 V 150 H 775 L 275,350 h -500 v 300 h 500 l 500,200 Z"],
null));var b=Ih(new $APP.r(null,6,[Gh,"green",Jh,2,Kh,0,Lh,0,Mh,0,Nh,0],null));var c=Eh(8,-8,90),d=Eh(-8,-8,-90),e=Eh(0,20,0),f=qh(c,d,e,Zg(new $APP.r(null,4,[Gh,"red",Jh,3,uh,"var(--text-color)",Xg,Oh],null))),h=qh(f,b),k=qh(a,h),l=qh(k);a:for(a=[c,b,a,h,f,d,e,k,l],b=[Ph,Qh,Rh,Sh,Th,Uh,Vh,Wh,lh],c=b.length,d=0,f=$APP.hb($APP.Ud);;)if(d<c){if(a.length<=d)throw Error(["No value supplied for key: ",$APP.z.g(b[d])].join(""));e=d+1;f=$APP.kb(f,b[d],a[d]);d=e}else{b=$APP.jb(f);break a}jg.j($APP.Lf,$APP.tf,
b);a=vg("game","");b=$APP.P([lh.g(b)]);wg(a,b);setTimeout(ph,0)},ai=function(a,b,c,d){var e=Yh.g?Yh.g(d):Yh.call(null,d),f=Zh.g?Zh.g(d):Zh.call(null,d);return function(h){var k=$APP.Q(h,0,null);h=$APP.Q(h,1,null);k=b+e*k;h=c+f*h;return a.isPointInFill($h.h?$h.h(k,h):$h.call(null,k,h))}},pi=function(a,b){var c=bi.g(a),d=$APP.Z.j;a=$APP.Z.j(a,ci,b-c);var e=ci.g(a);c=$APP.u($APP.Kf.g(a))?-di:0;var f=$APP.u($APP.If.g(a))?di:0;c=ei.g(a)+(c+f);c=c<fi?fi:c>gi?gi:c;e=hi.g(a)+e*c;var h=$APP.u($APP.Hf.g(a))?
.01:0,k=ii.g(a);var l=ji.g(a);f=l*(Yh.g?Yh.g(k):Yh.call(null,k))+h*(Yh.g?Yh.g(e):Yh.call(null,e));h=l*(Zh.g?Zh.g(k):Zh.call(null,k))+h*(Zh.g?Zh.g(e):Zh.call(null,e));k=$APP.W;l=ki.h?ki.h(f,h):ki.call(null,f,h);var m=f*f+h*h;m=li.g?li.g(m):li.call(null,m);l=new $APP.V(null,4,5,k,[l,m,f,-h],null);f=$APP.Q(l,0,null);h=$APP.Q(l,1,null);k=$APP.Q(l,2,null);l=$APP.Q(l,3,null);k=mi.g(a)+k;l=ni.g(a)+l;m=Rh.g(a);m=$APP.dd(ai(m,k,l,e),Oh);console.log("ok?",m);mh.s(Wh.g(a),$APP.P([rh,new $APP.V(null,2,5,$APP.W,
[1E3-k,0],null)]));mh.s(Sh.g(a),$APP.P([rh,new $APP.V(null,2,5,$APP.W,[k,l],null)]));mh.s(Qh.g(a),$APP.P([sh,f/oi*180,Nh,-100*h]));mh.s(Th.g(a),$APP.P([sh,e/oi*180]));a=$APP.Z.s(a,mi,k,$APP.P([ni,l,ii,f,ji,h,hi,e,ei,c]));return d.call($APP.Z,hh(a),bi,b)},ri=function(a){var b=$APP.Ne.g([$APP.Ee(a),"-cone"].join(""));return function(c){return jg.h($APP.Lf,function(d){return Cg.$(d,qi,Cg,a,function(e){if($APP.u(c))return mh.s(d.g?d.g(b):d.call(null,b),$APP.P([tg,""])),Ag(a);mh.s(d.g?d.g(b):d.call(null,
b),$APP.P([tg,"none"]));var f=$APP.Of.g?$APP.Of.g(a):$APP.Of.call(null,a);f.fade(f.volume(),0,200,e);return null})})}},ti=function(){og($APP.Kf,ri($APP.Kf));og($APP.If,ri($APP.If));og($APP.Hf,ri($APP.Hf));og($APP.Ff,function(a){return $APP.u(a)?Ag($APP.Ff):null});og(Jg,function(a){return $APP.u(a)?jg.j($APP.Lf,$APP.tf,si):null});jg.j($APP.Lf,$APP.tf,si)},vi=function(){return $APP.Sa(ui)},wi=function wi(a,b,c){var e=a.m-2>>>b&31;if(5<b){b-=5;var f=c.i[e];a=wi.j?wi.j(a,b,f):wi.call(null,a,b,f);if(null==
a&&0===e)return null;c=$APP.kd(c);c.i[e]=a;return c}if(0===e)return null;c=$APP.kd(c);c.i[e]=null;return c};$APP.fd.prototype.xb=Pf(11,function(a,b,c){this.rb=$APP.Z.j(this.rb,b,c);return this});$APP.Bc.prototype.wa=Pf(10,function(){return this.ea(null)});$APP.Cc.prototype.wa=Pf(9,function(){throw Error("Can't pop empty list");});
$APP.V.prototype.wa=Pf(8,function(){if(0===this.m)throw Error("Can't pop empty vector");if(1===this.m)return $APP.Va($APP.vd,this.u);if(1<this.m-$APP.ld(this))return new $APP.V(this.u,this.m-1,this.shift,this.root,this.ha.slice(0,-1),null);var a=$APP.nd(this,this.m-2),b=wi(this,this.shift,this.root);b=null==b?$APP.W:b;var c=this.m-1;return 5<this.shift&&null==b.i[1]?new $APP.V(this.u,c,this.shift-5,b.i[0],a,null):new $APP.V(this.u,c,this.shift,b,a,null)});
$APP.Md.prototype.wa=Pf(7,function(){return new $APP.V(null,1,5,$APP.W,[this.key],null)});$APP.Bc.prototype.va=Pf(6,function(){return this.first});$APP.Cc.prototype.va=Pf(5,function(){return null});$APP.V.prototype.va=Pf(4,function(){return 0<this.m?this.J(null,this.m-1):null});$APP.Md.prototype.va=Pf(3,function(){return this.N});$APP.E.prototype.Ta=Pf(2,function(){var a=this.V(null);return 0<a?new $APP.Xb(this,a-1,null):null});
$APP.V.prototype.Ta=Pf(1,function(){return 0<this.m?new $APP.Xb(this,this.m-1,null):null});$APP.Md.prototype.Ta=Pf(0,function(){return new $APP.E([this.N,this.key],0,null)});
var Cg=function Cg(a){switch(arguments.length){case 3:return Cg.j(arguments[0],arguments[1],arguments[2]);case 4:return Cg.B(arguments[0],arguments[1],arguments[2],arguments[3]);case 5:return Cg.$(arguments[0],arguments[1],arguments[2],arguments[3],arguments[4]);case 6:return Cg.ua(arguments[0],arguments[1],arguments[2],arguments[3],arguments[4],arguments[5]);default:for(var c=[],d=arguments.length,e=0;;)if(e<d)c.push(arguments[e]),e+=1;else break;return Cg.s(arguments[0],arguments[1],arguments[2],
arguments[3],arguments[4],arguments[5],new $APP.E(c.slice(6),0,null))}};Cg.j=function(a,b,c){var d=$APP.Z.j,e=$APP.kc.h(a,b);c=c.g?c.g(e):c.call(null,e);return d.call($APP.Z,a,b,c)};Cg.B=function(a,b,c,d){var e=$APP.Z.j,f=$APP.kc.h(a,b);c=c.h?c.h(f,d):c.call(null,f,d);return e.call($APP.Z,a,b,c)};Cg.$=function(a,b,c,d,e){var f=$APP.Z.j,h=$APP.kc.h(a,b);c=c.j?c.j(h,d,e):c.call(null,h,d,e);return f.call($APP.Z,a,b,c)};
Cg.ua=function(a,b,c,d,e,f){var h=$APP.Z.j,k=$APP.kc.h(a,b);c=c.B?c.B(k,d,e,f):c.call(null,k,d,e,f);return h.call($APP.Z,a,b,c)};Cg.s=function(a,b,c,d,e,f,h){return $APP.Z.j(a,b,$APP.Xc(c,$APP.kc.h(a,b),d,e,f,$APP.P([h])))};Cg.G=function(a){var b=$APP.G(a),c=$APP.J(a);a=$APP.G(c);var d=$APP.J(c);c=$APP.G(d);var e=$APP.J(d);d=$APP.G(e);var f=$APP.J(e);e=$APP.G(f);var h=$APP.J(f);f=$APP.G(h);h=$APP.J(h);return this.s(b,a,c,d,e,f,h)};Cg.I=6;
var cg=function cg(a,b){var d=a.exec(b);if(null==d)return null;var e=d[0],f=1===d.length?e:$APP.wd(d);return $APP.R(f,new $APP.Fc(null,function(){var h=e.length;h=d.index+(1>h?1:h);return h<=b.length?(h=b.substring(h),cg.h?cg.h(a,h):cg.call(null,a,h)):null},null))},ih=function ih(a){switch(arguments.length){case 0:return ih.C();case 1:return ih.g(arguments[0]);case 2:return ih.h(arguments[0],arguments[1]);case 3:return ih.j(arguments[0],arguments[1],arguments[2]);default:for(var c=[],d=arguments.length,
e=0;;)if(e<d)c.push(arguments[e]),e+=1;else break;return ih.s(arguments[0],arguments[1],arguments[2],new $APP.E(c.slice(3),0,null))}};ih.C=function(){return $APP.xc};ih.g=function(a){return a};
ih.h=function(a,b){return function(){function c(l,m,n){l=b.j?b.j(l,m,n):b.call(null,l,m,n);return a.g?a.g(l):a.call(null,l)}function d(l,m){l=b.h?b.h(l,m):b.call(null,l,m);return a.g?a.g(l):a.call(null,l)}function e(l){l=b.g?b.g(l):b.call(null,l);return a.g?a.g(l):a.call(null,l)}function f(){var l=b.C?b.C():b.call(null);return a.g?a.g(l):a.call(null,l)}var h=null,k=function(){function l(n,p,t,v){var y=null;if(3<arguments.length){y=0;for(var A=Array(arguments.length-3);y<A.length;)A[y]=arguments[y+
3],++y;y=new $APP.E(A,0,null)}return m.call(this,n,p,t,y)}function m(n,p,t,v){n=$APP.Wc(b,n,p,t,v);return a.g?a.g(n):a.call(null,n)}l.I=3;l.G=function(n){var p=$APP.G(n);n=$APP.J(n);var t=$APP.G(n);n=$APP.J(n);var v=$APP.G(n);n=$APP.Gb(n);return m(p,t,v,n)};l.s=m;return l}();h=function(l,m,n,p){switch(arguments.length){case 0:return f.call(this);case 1:return e.call(this,l);case 2:return d.call(this,l,m);case 3:return c.call(this,l,m,n);default:var t=null;if(3<arguments.length){t=0;for(var v=Array(arguments.length-
3);t<v.length;)v[t]=arguments[t+3],++t;t=new $APP.E(v,0,null)}return k.s(l,m,n,t)}throw Error("Invalid arity: "+arguments.length);};h.I=3;h.G=k.G;h.C=f;h.g=e;h.h=d;h.j=c;h.s=k.s;return h}()};
ih.j=function(a,b,c){return function(){function d(m,n,p){m=c.j?c.j(m,n,p):c.call(null,m,n,p);m=b.g?b.g(m):b.call(null,m);return a.g?a.g(m):a.call(null,m)}function e(m,n){m=c.h?c.h(m,n):c.call(null,m,n);m=b.g?b.g(m):b.call(null,m);return a.g?a.g(m):a.call(null,m)}function f(m){m=c.g?c.g(m):c.call(null,m);m=b.g?b.g(m):b.call(null,m);return a.g?a.g(m):a.call(null,m)}function h(){var m=c.C?c.C():c.call(null);m=b.g?b.g(m):b.call(null,m);return a.g?a.g(m):a.call(null,m)}var k=null,l=function(){function m(p,
t,v,y){var A=null;if(3<arguments.length){A=0;for(var I=Array(arguments.length-3);A<I.length;)I[A]=arguments[A+3],++A;A=new $APP.E(I,0,null)}return n.call(this,p,t,v,A)}function n(p,t,v,y){p=$APP.Wc(c,p,t,v,y);p=b.g?b.g(p):b.call(null,p);return a.g?a.g(p):a.call(null,p)}m.I=3;m.G=function(p){var t=$APP.G(p);p=$APP.J(p);var v=$APP.G(p);p=$APP.J(p);var y=$APP.G(p);p=$APP.Gb(p);return n(t,v,y,p)};m.s=n;return m}();k=function(m,n,p,t){switch(arguments.length){case 0:return h.call(this);case 1:return f.call(this,
m);case 2:return e.call(this,m,n);case 3:return d.call(this,m,n,p);default:var v=null;if(3<arguments.length){v=0;for(var y=Array(arguments.length-3);v<y.length;)y[v]=arguments[v+3],++v;v=new $APP.E(y,0,null)}return l.s(m,n,p,v)}throw Error("Invalid arity: "+arguments.length);};k.I=3;k.G=l.G;k.C=h;k.g=f;k.h=e;k.j=d;k.s=l.s;return k}()};
ih.s=function(a,b,c,d){var e=fg($APP.R(a,$APP.R(b,$APP.R(c,d))));return function(){function f(k){var l=null;if(0<arguments.length){l=0;for(var m=Array(arguments.length-0);l<m.length;)m[l]=arguments[l+0],++l;l=new $APP.E(m,0,null)}return h.call(this,l)}function h(k){k=$APP.Tc($APP.G(e),k);for(var l=$APP.J(e);;)if(l){var m=$APP.G(l);k=m.g?m.g(k):m.call(null,k);l=$APP.J(l)}else return k}f.I=0;f.G=function(k){k=$APP.F(k);return h(k)};f.s=h;return f}()};
ih.G=function(a){var b=$APP.G(a),c=$APP.J(a);a=$APP.G(c);var d=$APP.J(c);c=$APP.G(d);d=$APP.J(d);return this.s(b,a,c,d)};ih.I=3;
var jg=function jg(a){switch(arguments.length){case 2:return jg.h(arguments[0],arguments[1]);case 3:return jg.j(arguments[0],arguments[1],arguments[2]);case 4:return jg.B(arguments[0],arguments[1],arguments[2],arguments[3]);default:for(var c=[],d=arguments.length,e=0;;)if(e<d)c.push(arguments[e]),e+=1;else break;return jg.s(arguments[0],arguments[1],arguments[2],arguments[3],new $APP.E(c.slice(4),0,null))}};
jg.h=function(a,b){if(a instanceof $APP.fd){var c=a.state;b=b.g?b.g(c):b.call(null,c);a=hg(a,b)}else a=$APP.kf(a,b);return a};jg.j=function(a,b,c){if(a instanceof $APP.fd){var d=a.state;b=b.h?b.h(d,c):b.call(null,d,c);a=hg(a,b)}else a=$APP.kf(a,b,c);return a};jg.B=function(a,b,c,d){if(a instanceof $APP.fd){var e=a.state;b=b.j?b.j(e,c,d):b.call(null,e,c,d);a=hg(a,b)}else a=$APP.kf(a,b,c,d);return a};
jg.s=function(a,b,c,d,e){return a instanceof $APP.fd?hg(a,$APP.Wc(b,a.state,c,d,e)):$APP.kf(a,b,c,d,e)};jg.G=function(a){var b=$APP.G(a),c=$APP.J(a);a=$APP.G(c);var d=$APP.J(c);c=$APP.G(d);var e=$APP.J(d);d=$APP.G(e);e=$APP.J(e);return this.s(b,a,c,d,e)};jg.I=4;var ig=null;lg.prototype.pb=function(){$APP.u(this.f)&&(this.value=this.f.C?this.f.C():this.f.call(null),this.f=null);return this.value};
lg.prototype.O=function(a,b,c){$APP.C(b,"#object[cljs.core.Delay ");$APP.X(new $APP.r(null,2,[$APP.yf,null==this.f?$APP.Jf:$APP.Cf,$APP.uf,this.value],null),b,c);return $APP.C(b,"]")};
var Nh=new $APP.T(null,"y2","y2",-718691301),Lh=new $APP.T(null,"y1","y1",589123466),rh=new $APP.T(null,"translate","translate",1336199447),bh=new $APP.Cb("marooned.debug","insert-debug-panel","marooned.debug/insert-debug-panel",-785597226,null),Ug=new $APP.T(null,"transform","transform",1381301764),ni=new $APP.T(null,"y","y",-1757859776),mi=new $APP.T(null,"x","x",2099068185),Ch=new $APP.T(null,"dur","dur",1464522452),xi=new $APP.T(null,"keyup","keyup",-794526927),Ah=new $APP.T(null,"attributeName",
"attributeName",-400177890),Dh=new $APP.T(null,"repeatCount","repeatCount",1447663848),Ph=new $APP.T(null,"left-thruster-cone","left-thruster-cone",331882915),hi=new $APP.T(null,"h","h",1109658740),Hh=new $APP.T(null,"d","d",1972142424),Kh=new $APP.T(null,"x1","x1",-1863922247),ii=new $APP.T(null,"vh","vh",79552846),Mh=new $APP.T(null,"x2","x2",-1362513475),Ig=new $APP.T(null,"debug-toggle","debug-toggle",292283581),ji=new $APP.T(null,"vs","vs",-2022097090),Wh=new $APP.T(null,"board","board",-1907017633),
sh=new $APP.T(null,"rotate","rotate",152705015),wh=new $APP.T(null,"cy","cy",755331060),vh=new $APP.T(null,"cx","cx",1272694324),Qh=new $APP.T(null,"speed","speed",1257663751),Xg=new $APP.T(null,"points","points",-1486596883),bi=new $APP.T(null,"ts","ts",1617209904),yi=new $APP.T(null,"touchend","touchend",-1574059019),ei=new $APP.T(null,"dh","dh",528137731),ci=new $APP.T(null,"dt","dt",-368444759),zi=new $APP.T(null,"mousedown","mousedown",1391242074),Sh=new $APP.T(null,"ship","ship",197863473),
tg=new $APP.T(null,"display","display",242065432),Vh=new $APP.T(null,"forward-thruster-cone","forward-thruster-cone",-1370539011),Bh=new $APP.T(null,"values","values",372645556),lh=new $APP.T(null,"scene","scene",1523800415),yh=new $APP.T(null,"ry","ry",-334598563),xh=new $APP.T(null,"rx","rx",1627208482),nh=new $APP.T(null,"viewBox","viewBox",-469489477),Ai=new $APP.T(null,"touchstart","touchstart",369858804),Uh=new $APP.T(null,"right-thruster-cone","right-thruster-cone",-1021778022),Bi=new $APP.T(null,
"keydown","keydown",-629268186),gh=new $APP.T(null,"debug?","debug?",-1831756173),Jh=new $APP.T(null,"stroke-width","stroke-width",716836435),qi=new $APP.T(null,"sound","sound",-2127407070),Fg=new $APP.T(null,"is-fullscreen?","is-fullscreen?",972486782),Ci=new $APP.T(null,"mouseup","mouseup",350619456),uh=new $APP.T(null,"fill","fill",883462889),Rh=new $APP.T(null,"cave","cave",-587603287),oh=new $APP.T(null,"scale","scale",-230427353),Gg=new $APP.T(null,"can-fullscreen?","can-fullscreen?",-1592427507),
jh=new $APP.Cb("marooned.debug","remove-debug-panel","marooned.debug/remove-debug-panel",1897953233,null),Th=new $APP.T(null,"hull","hull",-957096876),Gh=new $APP.T(null,"stroke","stroke",1741823555),Di=new $APP.T(null,"touchcancel","touchcancel",-669291029),ch=new $APP.T(null,"id","id",-1388402092),dh=new $APP.T(null,"key","key",-1516042587),Ei=new $APP.T(null,"resize","resize",297367086),Jg=new $APP.T(null,"reset!","reset!",-1113255895);var Rg=new $APP.V(null,4,5,$APP.W,[rh,sh,oh,new $APP.T(null,"skew","skew",-595121815)],null),Fi=$APP.De(Rg),Gi=$APP.gd.h(Fi,Ug),mh=function mh(a){for(var c=[],d=arguments.length,e=0;;)if(e<d)c.push(arguments[e]),e+=1;else break;return mh.s(arguments[0],1<c.length?new $APP.E(c.slice(1),0,null):null)};
mh.s=function(a,b){if($APP.F(b)){a:{var c=$APP.F(b);$APP.G(c);c=$APP.J(c);$APP.G(c);$APP.J(c);c=$APP.vd;for(var d=b;;){b=c;d=$APP.F(d);c=$APP.G(d);d=$APP.J(d);var e=$APP.G(d),f=$APP.J(d);d=e;e=f;if($APP.ua(c))break a;f=Gi.g?Gi.g(c):Gi.call(null,c);$APP.u(f)||a.setAttributeNS(null,$APP.Ee(c),$APP.z.g(d));b=$APP.u(f)?$APP.gd.h(b,new $APP.V(null,2,5,$APP.W,[c,d],null)):b;d=e;c=b}}if($APP.F(b)){b=Vg(a,b);c=$APP.F(Fi);d=null;for(f=e=0;;)if(f<e){var h=d.J(null,f),k=h.g?h.g(b):h.call(null,b);a[h]=k;f+=1}else if(c=
$APP.F(c))$APP.hc(c)?(e=$APP.mb(c),c=$APP.nb(c),d=e,e=$APP.K(e)):(d=$APP.G(c),e=d.g?d.g(b):d.call(null,b),a[d]=e,c=$APP.J(c),d=null,e=0),f=0;else break;a.setAttributeNS(null,"transform",Sg(b))}}return a};mh.I=1;mh.G=function(a){var b=$APP.G(a);a=$APP.J(a);return this.s(b,a)};var ag=function ag(a){for(var c=[],d=arguments.length,e=0;;)if(e<d)c.push(arguments[e]),e+=1;else break;return ag.s(arguments[0],1<c.length?new $APP.E(c.slice(1),0,null):null)};
ag.s=function(a,b){var c=document.createElementNS("http://www.w3.org/2000/svg",a);b=$APP.ec($APP.G(b))?new $APP.V(null,2,5,$APP.W,[$APP.G(b),$APP.Gb(b)],null):new $APP.V(null,2,5,$APP.W,[null,b],null);var d=$APP.Q(b,0,null);b=$APP.Q(b,1,null);c.translate=new $APP.V(null,2,5,$APP.W,[0,0],null);c.rotate=0;c.scale=1;$APP.Uc(mh,c,$APP.Tc($APP.of,$APP.Uc($APP.Y,$APP.xc,$APP.P([d]))));d=new $APP.Ae(null,new $APP.r(null,2,["path",null,"polygon",null],null),null);d=d.g?d.g(a):d.call(null,a);$APP.u(d)&&console.log("create-element:",
a,$APP.Se($APP.P([b]),$APP.pa()));a=$APP.F(b);b=null;for(var e=d=0;;)if(e<d){var f=b.J(null,e);$APP.dc(f)?Wg(c,f):Wg(c,$APP.P([f]));e+=1}else if(a=$APP.F(a))b=a,$APP.hc(b)?(a=$APP.mb(b),e=$APP.nb(b),b=a,d=$APP.K(a),a=e):(a=$APP.G(b),$APP.dc(a)?Wg(c,a):Wg(c,$APP.P([a])),a=$APP.J(b),b=null,d=0),e=0;else break;return c};ag.I=1;ag.G=function(a){var b=$APP.G(a);a=$APP.J(a);return this.s(b,a)};
var qh=bg("g"),Hi=bg("circle"),th=bg("ellipse"),Fh=bg("path"),Ih=bg("line"),zh=bg("animate"),$h=function(){try{return Hi($APP.ad).isPointInFill(DOMPoint(0,0)),function(b,c){return new DOMPoint(b,c)}}catch(b){var a=document.getElementsByTagName("svg")[0];return function(c,d){var e=a.createSVGPoint();e.x=c;e.y=d;return e}}}();var eh=new $APP.V(null,10,5,$APP.W,[new $APP.V(null,2,5,$APP.W,[ci,$g],null),new $APP.V(null,2,5,$APP.W,[mi,$g],null),new $APP.V(null,2,5,$APP.W,[ni,$g],null),new $APP.V(null,2,5,$APP.W,[ii,$g],null),new $APP.V(null,2,5,$APP.W,[ji,$g],null),new $APP.V(null,2,5,$APP.W,[hi,$g],null),new $APP.V(null,2,5,$APP.W,[ei,$g],null),new $APP.V(null,2,5,$APP.W,[$APP.Kf,ah],null),new $APP.V(null,2,5,$APP.W,[$APP.If,ah],null),new $APP.V(null,2,5,$APP.W,[$APP.Hf,ah],null)],null);var Oh=new $APP.V(null,5,5,$APP.W,[new $APP.V(null,2,5,$APP.W,[0,-20],null),new $APP.V(null,2,5,$APP.W,[18,12],null),new $APP.V(null,2,5,$APP.W,[12,20],null),new $APP.V(null,2,5,$APP.W,[-12,20],null),new $APP.V(null,2,5,$APP.W,[-18,12],null)],null);var oi=Math.PI,Yh=Math.sin,Zh=Math.cos,ki=Math.atan2,li=Math.sqrt,gi=oi/1E3,fi=-gi,di=gi/80,si=new $APP.r(null,7,[bi,0,mi,0,ni,500,ii,0,ji,0,hi,Math.PI/2,ei,0],null);var ui=new lg(function(){gg($APP.P(["init: registering worker..."]));var a=$APP.$e(navigator,"serviceWorker");$APP.u(a)&&a.register("worker.js",{scope:"./"}).then(function(){return gg($APP.P(["init: worker registered"]))}).catch(function(c){return gg($APP.P(["init: worker failed",c]))});gg($APP.P(["init: init modules"]));Dg();Hg();yg(window,Bi,Kg(!0));yg(window,xi,Kg(!1));zg(zg(zg(zg(yg("left-thruster",zi,Lg($APP.Kf)),Ci,Mg($APP.Kf)),Ai,Ng($APP.Kf)),yi,Og($APP.Kf)),Di,Pg($APP.Kf));zg(zg(zg(zg(yg("right-thruster",
zi,Lg($APP.If)),Ci,Mg($APP.If)),Ai,Ng($APP.If)),yi,Og($APP.If)),Di,Pg($APP.If));zg(zg(zg(zg(yg("forward-thruster",zi,Lg($APP.Hf)),Ci,Mg($APP.Hf)),Ai,Ng($APP.Hf)),yi,Og($APP.Hf)),Di,Pg($APP.Hf));zg(zg(zg(zg(yg("cannon",zi,Lg($APP.Ff)),Ci,Mg($APP.Ff)),Ai,Ng($APP.Ff)),yi,Og($APP.Ff)),Di,Pg($APP.Ff));gg($APP.P(["init: game init"]));ti();gg($APP.P(["init: scene"]));yg(window,Ei,ph);Xh();gg($APP.P(["init: debug"]));kh();gg($APP.P(["init: animation"]));var b=$APP.$e(window,"performance").now();window.requestAnimationFrame(function e(d){jg.j($APP.Lf,
pi,d-b);return window.requestAnimationFrame(e)});return gg($APP.P(["init: done"]))}),Ii=["marooned","app","run"],Ji=$APP.df;Ii[0]in Ji||"undefined"==typeof Ji.execScript||Ji.execScript("var "+Ii[0]);for(var Ki;Ii.length&&(Ki=Ii.shift());)Ii.length||void 0===vi?Ji=Ji[Ki]&&Ji[Ki]!==Object.prototype[Ki]?Ji[Ki]:Ji[Ki]={}:Ji[Ki]=vi;
}).call(this);