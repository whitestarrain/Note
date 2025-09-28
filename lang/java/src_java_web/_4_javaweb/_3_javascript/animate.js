// var timerId = null;
// 封装动画的函数
function animate(element, target) {
   // 通过判断，保证页面上只有一个定时器在执行动画
  if (element.timerId) {
    clearInterval(element.timerId);
    element.timerId = null;
  }

  element.timerId = setInterval(function () {
    // 步进  每次移动的距离
    var step = 10;
    // 盒子当前的位置
    var current = element.offsetLeft;
    // 当从400 到 800  执行动画
    // 当从800 到 400  不执行动画

    // 判断如果当前位置 > 目标位置 此时的step  要小于0
    if (current > target) {
      step = - Math.abs(step);
    }

    // Math.abs(current - target)   <= Math.abs(step)
    if (Math.abs(current - target)   <= Math.abs(step)) {
      // 让定时器停止
      clearInterval(element.timerId);
      // 让盒子到target的位置
      element.style.left = target + 'px';
      return;
    }
    // 移动盒子
    current += step;
    element.style.left = current + 'px';
  }, 5);
}
