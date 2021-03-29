// ==UserScript==
// @name         优学院自动静音播放、自动做练习题、自动翻页、修改播放速率
// @namespace    [url=mailto:moriartylimitter@outlook.com]moriartylimitter@outlook.com[/url]
// @version      1.3.3
// @description  自动静音播放每页视频、自动作答、修改播放速率!
// @author       EliotZhang
// @match        *://*.ulearning.cn/learnCourse/*
// @grant        none
// ==/UserScript==

(function() {
    'use strict';

    /*  ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     *  优学院自动静音播放、自动做练习题、自动翻页、修改播放速率脚本v1.3.3由EliotZhang @ 2020/02/21 最后更新
     *  使用修改播放速率功能请谨慎！！！产生的不良后果恕本人概不承担！！！
     *  如果不需要自动修改播放速率，请注释Main()函数里的最后一行
     *  如果你需要改变自动修改的播放速率，请更改本注释下第一行N的赋值(别改的太大，否则可能产生不良后果！！！默认是1.5倍速，这是正常的！！！最大为15.0，否则可能失效！！！)
     *  如果不需要自动静音功能，请注释PlayVideo()函数标注的那一行
     *  自动作答功能由于精力有限目前只支持单/多项选择、判断题、部分填空问答题，如果出现问题请尝试禁用这个功能：将AutoFillAnswer赋值为false
     *  如果脚本无效请查看脚本最后的解决方案，如果还是不行请反馈给本人，本人将会尽快修复
     *  如果在使用中还有什么问题请通过邮箱联系本人：moriartylimitter@outlook.com
     *  ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    var N = 1.5;
    // 将下面AutoFillAnswer赋值为false可以禁用自动作答功能！
    var AutoFillAnswer = true;

    function PlayVideo() {
        var video = document.getElementsByTagName('video')[0];
        if (video === undefined) {
            var player = document.getElementsByClassName(
                'mejs__overlay mejs__layer mejs__overlay-play'
            )[0];
            if (player === undefined) return;
            player.click();
            return;
        }
        autoAnswer = false;
        if (video.paused === true) {
            video.play();
        }
        if (video.muted === false) {
            // 如果不想自动静音，请注释掉下面这行
            video.muted = true;
        }
    }

    function PlaySpeedXN() {
        var video = document.getElementsByTagName('video')[0];
        if (video === undefined) return;
        if (video.playbackRate != N) {
            video.playbackRate = N;
        }
    }

    function GotoNextPage() {
        var nextPageBtn = $(
            '.mobile-next-page-btn, .next-page-btn next-page-btn cursor'
        );
        if (nextPageBtn.length === 0) return;
        autoAnswer = false;
        nextPageBtn.each((k, n) => {
            n.click();
        });
    }

    function CheckFinshVideo() {
        var videoStatus = document.getElementsByClassName('video-bottom')[0];
        if (videoStatus !== undefined) {
            var span = videoStatus.getElementsByTagName('span')[0];
            if (span !== undefined && span.innerHTML == '已看完')
                GotoNextPage();
        } else GotoNextPage();
    }

    function CheckModal() {
        var alertModal = document.getElementById('alertModal');
        if (alertModal === undefined) return;
        if (alertModal.className.match(/\sin/)) {
            var op =
                alertModal.children[0].children[0].children[2].children[1]
                    .children[1].children[0];
            if (!AutoFillAnswer)
                op =
                    alertModal.children[0].children[0].children[2].children[1]
                        .children[1].children[1];
            if (op === undefined) return;
            op.click();
            if (AutoFillAnswer) ShowAndFillAnswer();
        }
    }

    function RemoveDuplicatedItem(arr) {
        for (var i = 0; i < arr.length - 1; i++) {
            for (var j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    arr.splice(j, 1);
                    j--;
                }
            }
        }
        return arr;
    }

    function FillAnswers() {
        var ansarr = [];
        var idList = [];
        var re = [];
        var txtAreas = $('textarea, .blank-input');
        $(txtAreas).each((k, v) => {
            var id =
                $(v).attr('class') == 'blank-input'
                    ? $(v)
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .attr('id')
                    : $(v)
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .attr('id');
            id = id.replace('question', '');
            idList.push(id);
        });
        idList = RemoveDuplicatedItem(idList);
        $(idList).each((k, id) => {
            $.ajax({
                async: false,
                type: 'get',
                url: 'https://api.ulearning.cn/questionAnswer/' + id,
                datatype: 'json',
                success: function(result) {
                    re.push(result.correctAnswerList);
                }
            });
        });

        $(re).each((k1, v1) => {
            if (v1.length == 1) {
                ansarr.push(v1[0]);
            } else {
                $(v1).each(function(k2, v2) {
                    ansarr.push(v2);
                });
            }
        });
        $(txtAreas).each((k, v) => {
            $(v).trigger('click');
            v.value = ansarr.shift();
            $(v).trigger('change');
        });
    }

    function ShowAndFillAnswer() {
        if (autoAnswer) return;
        autoAnswer = true;
        var sqList = [];
        var qw = $('.question-wrapper');
        var an = [];
        qw.each(function(k, v) {
            var id = $(v).attr('id');
            sqList.push(id.replace('question', ''));
        });
        if (sqList.length <= 0) return;
        $(sqList).each(function(k, id) {
            $.ajax({
                async: false,
                type: 'get',
                url: 'https://api.ulearning.cn/questionAnswer/' + id,
                datatype: 'json',
                success: function(result) {
                    an.push(result.correctAnswerList);
                }
            });
        });
        var t = qw.find('.question-title-html');
        t.each(function(k, v) {
            var ans = an.shift();
            $(v).after('<span style="color:red;">答案：' + ans + '</span>');
            an.push(ans);
        });
        var checkBox = qw.find('.checkbox');
        var choiceBox = qw.find('.choice-btn');
        var checkList = [];
        var choiceList = [];
        let lasOffsetP = '';
        checkBox.each((k, cb) => {
            if (
                lasOffsetP ==
                $(cb)
                    .offsetParent()
                    .attr('id')
            ) {
                checkList[checkList.length - 1].push(cb);
            } else {
                var l = [];
                l.push(cb);
                checkList.push(l);
                lasOffsetP = $(cb)
                    .offsetParent()
                    .attr('id');
            }
        });
        lasOffsetP = '';
        choiceBox.each((k, cb) => {
            if (
                lasOffsetP ==
                $(cb)
                    .offsetParent()
                    .attr('id')
            ) {
                choiceList[choiceList.length - 1].push(cb);
            } else {
                var l = [];
                l.push(cb);
                choiceList.push(l);
                lasOffsetP = $(cb)
                    .offsetParent()
                    .attr('id');
            }
        });
        an.forEach(a => {
            if (a[0].match(/[A-Z]/) && a[0].length == 1) {
                var cb = checkList.shift();
                a.forEach(aa => {
                    $(cb[aa.charCodeAt() - 65]).click();
                });
            } else if (a[0].match(/(true|false)/)) {
                var ccb = choiceList.shift();
                a.forEach(aa => {
                    if (aa == 'true') ccb[0].click();
                    else ccb[1].click();
                });
            }
            return;
        });
        FillAnswers();
        $('.btn-submit').click();
        autoAnswer = false;
        GotoNextPage();
    }

    function Main() {
        PlayVideo();
        setInterval(CheckModal, '200');
        setInterval(PlayVideo, '400');
        setInterval(CheckFinshVideo, '500');
        // 如果不需要修改倍速请取消注释掉下面这行
        setInterval(PlaySpeedXN, '800');
    }

    var autoAnswer = false;

    // 如果脚本报错则有可能是你的网络太慢，请尝试修改下面的3000为更大数值！
    setTimeout(Main, '3000');
})();
