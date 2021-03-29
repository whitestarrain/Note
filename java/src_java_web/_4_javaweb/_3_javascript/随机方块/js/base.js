//工具类
var tools = {
    getRandom: function(min, max) {
        /* 这里加1以及floor是因为random取头不取尾 */
        return Math.floor(Math.random() * (max - min + 1) + min);
    }
};

// box
function box(parent, options) {
    options = options || {}; /* 防止无传参时出错 */

    //设置对应参数
    this.backgroundColor = options.backgroundColor || this.randomColor();
    this.width = options.width || 20;
    this.height = options.height || 20;

    //创建对应div
    this.div = document.createElement('div');
    parent.appendChild(
        this.div
    ); /* 此时div是成员变量而不是局部变量，，要调用的话需要加this */

    this.parent = parent;

    /* 设置好随机属性 */
    this.random();

    if (options.x) {
        this.x = options.x;
    }
    if (options.y) {
        this.y = options.y;
    }

    /* 开始构建 */
    this.init(); /* 调用构建对象。再次强调，this指向调用者 */
}

box.prototype.init = function() {
    var div = this.div;
    div.style.backgroundColor = this.backgroundColor;
    div.style.width = this.width + 'px';
    div.style.height = this.height + 'px';
    div.style.left = this.x + 'px';
    div.style.top = this.y + 'px';
    /* 别忘了加px */

    /* 脱离文档流 */

    div.style.position = 'absolute';
};

/* 生成随机位置 */
box.prototype.random = function() {
    var x =
        tools.getRandom(0, this.parent.offsetWidth / this.width - 1) *
        this.width;
    var y =
        tools.getRandom(0, this.parent.offsetHeight / this.height - 1) *
        this.width;
    this.x = x;
    this.y = y;
};

box.prototype.randomColor = function() {
    return (
        'rgb(' +
        tools.getRandom(0, 255) +
        ',' +
        tools.getRandom(0, 255) +
        ',' +
        tools.getRandom(0, 255) +
        ')'
    );
};
