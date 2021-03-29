/// <reference path="../../随机方块/js/base.js"/>
//这个导入要放在最开始一行
(function () {
    var that; //记录游戏对象
    
    var map = document.getElementById('map');

    /**
     *
     * @param {*} options
     * * 这里偷下懒，直接用随即方块作为食物
     */
    function Food(parent, options) {
        this.box = new box(parent);
        this.x = this.box.x;
        this.y = this.box.y;
    }

    function Snake(parent) {
        this.parent = parent;
        this.snakeNodes = [];
        this.init();
    }

    Snake.prototype.init = function () {
        this.snakeNodes = [
            new box(this.parent, {
                backgroundColor: 'red',
                x: Math.floor(this.parent.offsetWidth / 2 / 20) * 20,
                y: Math.floor(this.parent.offsetHeight / 2 / 20) * 20,
            }),
        ];
        this.snakeNodes.push(
            this.getNeighbor(this.snakeNodes[this.snakeNodes.length - 1], -1, 0)
        );
        this.snakeNodes.push(
            this.getNeighbor(this.snakeNodes[this.snakeNodes.length - 1], -1, 0)
        );
    };

    /**
     * 以front为基准，向右移动number个格子，向下移动number_y个格子
     * 获得新box
     */
    Snake.prototype.getNeighbor = function (front, number_x, number_y) {
        return new box(this.parent, {
            backgroundColor: 'blue',
            x: front.x + number_x * 20,
            y: front.y + number_y * 20,
        });
    };
    Snake.prototype.moveNode = function (number_x, number_y) {
        number_x = number_x || 0;
        number_y = number_y || 0;

        /* 信息修改 */
        /* 往前走 */
        for (var i = this.snakeNodes.length - 1; i > 0; i--) {
            this.snakeNodes[i].x = this.snakeNodes[i - 1].x;
            this.snakeNodes[i].y = this.snakeNodes[i - 1].y;
        }
        /* 头移动 */
        this.snakeNodes[0].x = this.snakeNodes[0].x + number_x * 20;
        this.snakeNodes[0].y = this.snakeNodes[0].y + number_y * 20;

        /* 样式重新渲染 */
        this.render();
    };
    Snake.prototype.render = function () {
        for (var m = this.snakeNodes.length - 1; m >= 0; m--) {
            this.snakeNodes[m].init();
        }
    };

    function Game(parent) {
        this.parent = parent;
        this.snake = new Snake(parent);
        this.food = new Food(parent);
        this.timerId;
        this.direction = 39;
        that = this; //没用到，以后感觉也不会用到，但是一个小窍门。
        this.initEvent();
        this.start();
    }
    Game.prototype.initEvent = function () {
        var game = this; //添加事件函数中this为addEventListener调用者
        document.addEventListener(
            'keydown',
            function (e) {
                game.direction = e.keyCode;
            },
            false
        );
    };
    Game.prototype.start = function () {
        this.timerId = setInterval(() => {
            /* 在移动前记录last */
            var last = this.snake.snakeNodes[this.snake.snakeNodes.length - 1];

            switch (this.direction) {
                case 40:
                    /* 向下 */
                    this.snake.moveNode(0, 1);
                    break;
                case 39:
                    /* 向右 */
                    this.snake.moveNode(1, 0);
                    break;
                case 38:
                    /* 向上 */
                    this.snake.moveNode(0, -1);
                    break;
                case 37:
                    /* 向左 */
                    this.snake.moveNode(-1, 0);
                    break;
                default:
                    break;
            }
            if (
                this.snake.snakeNodes[0].x === this.food.x &&
                this.snake.snakeNodes[0].y === this.food.y
            ) {
                this.parent.removeChild(this.food.box.div);
                this.snake.snakeNodes.push(
                    new box(this.parent, {
                        backgroundColor: 'blue',
                        x: last.x,
                        y: last.y,
                    })
                );
                this.snake.render();
                this.food = new Food(this.parent);
            }
        }, 200);
    };
    window.Game = Game;
})();
