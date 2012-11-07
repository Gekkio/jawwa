/*
 FastClick: polyfill to remove click delays on browsers with touch UIs.

 @version 0.3.3
 @copyright The Financial Times Limited [All Rights Reserved]
 @license MIT License (see LICENSE.txt)
*/
var b=!0,d=null,f=!1;
function g(a){var e,c=this;this.b=f;this.a=d;this.m=a;if(!a||!a.nodeType)throw new TypeError("Layer must be a document node");this.c=function(){g.prototype.c.apply(c,arguments)};this.g=function(){g.prototype.g.apply(c,arguments)};this.f=function(){g.prototype.f.apply(c,arguments)};this.e=function(){g.prototype.e.apply(c,arguments)};this.d=function(){g.prototype.d.apply(c,arguments)};"undefined"!==typeof window.ontouchstart&&(a.addEventListener("click",this.c,b),a.addEventListener("touchstart",this.g,
b),a.addEventListener("touchmove",this.f,b),a.addEventListener("touchend",this.e,b),a.addEventListener("touchcancel",this.d,b),"function"===typeof a.onclick&&(e=a.onclick,a.addEventListener("click",function(a){e(a)},f),a.onclick=d))}function i(a){switch(a.nodeName.toLowerCase()){case "label":case "video":return b;default:return/\bneedsclick\b/.test(a.className)}}
function j(a){switch(a.nodeName.toLowerCase()){case "textarea":case "select":return b;case "input":switch(a.type){case "button":case "checkbox":case "file":case "image":case "radio":case "submit":return f;default:return b}default:return/\bneedsfocus\b/.test(a.className)}}function k(a,e){var c,h;if(i(a))return f;h=e.changedTouches[0];c=document.createEvent("MouseEvents");c.initMouseEvent("click",b,b,window,1,h.screenX,h.screenY,h.clientX,h.clientY,f,f,f,f,0,d);c.h=b;a.dispatchEvent(c);return b}
g.prototype.g=function(a){var e=a.targetTouches[0];this.b=b;this.a=a.target;this.i=e.pageX;this.j=e.pageY;return b};g.prototype.f=function(a){if(!this.b)return b;var e;if(!(e=this.a!==a.target))a=a.targetTouches[0],e=10<Math.abs(a.pageX-this.i)||10<Math.abs(a.pageY-this.j)?b:f;e&&(this.b=f,this.a=d);return b};
g.prototype.e=function(a){var e,c=this.a;if(!this.b)return b;this.b=f;if("label"===c.nodeName.toLowerCase()&&c.htmlFor){if(e=document.getElementById(c.htmlFor)){c.focus();if(this.l)return f;k(e,a)&&a.preventDefault();return f}}else if(j(c))return c.focus(),"select"!==c.tagName.toLowerCase()&&a.preventDefault(),f;if(!k(c,a))return f;a.preventDefault();return f};g.prototype.d=function(){this.b=f;this.a=d};
g.prototype.c=function(a){var e;if(a.h||!this.a)return b;e=this.a;this.a=d;return!a.cancelable||"submit"===a.target.type&&0===a.detail?b:!i(e)?(a.stopImmediatePropagation&&a.stopImmediatePropagation(),a.stopPropagation(),a.preventDefault(),f):b};"function"===typeof define&&define.k&&define(function(){return g});
