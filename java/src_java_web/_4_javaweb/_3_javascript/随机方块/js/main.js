/// <reference path="./base.js" />
var container = document.getElementById('container');
setInterval(function() {
    container.innerHTML = '';
    for (i = 0; i < 10; i++) {
        container.children = null;
        new box(container);
    }
}, 100);
