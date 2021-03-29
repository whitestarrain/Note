/* 以后都有兼容性处理库，不用自己写。原理大致为下面那样 ，只是大致能用*/

/* 通过id获取元素 */
function my$(id) {
    return document.getElementById(id);
}

/* 处理nextElementSibling兼容性问题 */
function getNextElementSibling(element) {
    var e = element.nextSibling;
    while (e && 1 !== e.nodeType) e = e.nextSibling;
    return e;
}

/* 处理innerText兼容性问题 */
function setInnerText(element, content) {
    if (typeof element.innerText === 'string') {
        element.innerText = content;
    } else {
        element.textContent = content;
    }
}

/* 添加事件兼容性处理 */
function addEventListener(element, type, fn) {
    if (element.addEventListener) {
        element.addEventListener(type, fn, false);
    } else if (element.attachEvent) {
        element.attachEvent('on' + type, fn);
    } else {
        element['on' + type] = fn;
    }
}

/* 移除事件兼容性处理 */
function removeEventListener(element, type, fn) {
    if (element.removeEventListener) {
        element.removeEventListener(type, fn, false);
    } else if (element.detachEvent) {
        element.detachEvent('on' + type, fn);
    } else {
        element['on' + type] = null;
    }
}

/* 获取文档滚动距离，处理兼容性 */
function getScroll() {
    return {
        scrollLeft:
            document.body.scrollLeft || document.documentElement.scrollLeft,
        scrollTop: document.body.scrollTop || document.documentElement.scrollTop
    };
}

/* 处理获得pageX,pageY兼容性 */
function getPage(e) {
    return {
        pageX: e.pageX || e.clientX + getScroll().scrollLeft,
        pageY: e.pageY || e.clientY + getScroll.scrollTop
    };
}

//格式化日期对象，返回yyyy-MM-dd HH:mm:ss的形式
function formatDate(date) {
    // 判断参数date是否是日期对象
    // instanceof  instance 实例(对象)   of 的
    // console.log(date instanceof Date);
    if (!(date instanceof Date)) {
        console.error('date不是日期对象');
        return;
    }

    var year = date.getFullYear(),
        month = date.getMonth() + 1,
        day = date.getDate(),
        hour = date.getHours(),
        minute = date.getMinutes(),
        second = date.getSeconds();

    month = month < 10 ? '0' + month : month;
    day = day < 10 ? '0' + day : day;
    hour = hour < 10 ? '0' + hour : hour;
    minute = minute < 10 ? '0' + minute : minute;
    second = second < 10 ? '0' + second : second;

    return (
        year +
        '-' +
        month +
        '-' +
        day +
        ' ' +
        hour +
        ':' +
        minute +
        ':' +
        second
    );
}

// 获取两个日期的时间差
function getInterval(start, end) {
    // 两个日期对象，相差的毫秒数
    var interval = end - start;
    // 求 相差的天数/小时数/分钟数/秒数
    var day, hour, minute, second;

    // 两个日期对象，相差的秒数
    // interval = interval / 1000;
    interval /= 1000;

    day = Math.round(interval / 60 / 60 / 24);
    hour = Math.round((interval / 60 / 60) % 24);
    minute = Math.round((interval / 60) % 60);
    second = Math.round(interval % 60);

    return {
        day: day,
        hour: hour,
        minute: minute,
        second: second
    };
}
