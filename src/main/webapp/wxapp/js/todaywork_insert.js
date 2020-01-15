//项目录入
console.log("日报录入");
//判断是否在企业微信中打开  不是就跳到错误提示页
var ua = window.navigator.userAgent.toLowerCase();
if ((ua.match(/MicroMessenger/i) == 'micromessenger') && (ua.match(/wxwork/i) == 'wxwork')) {
    //企业微信客户端
   //

} else if (ua.match(/micromessenger/i) == 'micromessenger') {
    //微信客户端
    //window.location.replace('../pages/error.html')
} else {
    //浏览器
    window.location.replace('../error.html')
}

//初始化选项卡高度
var winHeight = document.documentElement.clientHeight;//页面高度

// var buttonHeight = winHeight - $("#layuiAll").height();//底部高度

// var detailHeight = winHeight - buttonHeight - 153;//内页高度

var detailHeight = $("#layuiAll").height() - 50 - 58 - 41 - 20 - 20;

$("#todayDetail").attr("style", "height: " + detailHeight + "px;overflow: scroll;");//设置表单高度

$("#projectSummary").attr("style", "resize: none;height:" + (detailHeight - 50) + "px");//设置工作总结高度

$("body").attr("style", "height: 100%;opacity: 1;");//显示body内容

sliderInit();//初始化滑块
function sliderInit() {
    $(".js-range-slider").ionRangeSlider({
        skin: "round",
        min: 0,
        max: 100,
        step: 5,
        postfix: "%",
        from: 0,
        hide_min_max: true,    // show/hide MIN and MAX labels
        onStart: function (data) {
            // Called right after range slider instance initialised

            /*            console.log(data.input);        // jQuery-link to input
                        console.log(data.slider);       // jQuery-link to range sliders container
                        console.log(data.min);          // MIN value
                        console.log(data.max);          // MAX values
                        console.log(data.from);         // FROM value
                        console.log(data.from_percent); // FROM value in percent
                        console.log(data.from_value);   // FROM index in values array (if used)
                        console.log(data.to);           // TO value
                        console.log(data.to_percent);   // TO value in percent
                        console.log(data.to_value);     // TO index in values array (if used)
                        console.log(data.min_pretty);   // MIN prettified (if used)
                        console.log(data.max_pretty);   // MAX prettified (if used)
                        console.log(data.from_pretty);  // FROM prettified (if used)
                        console.log(data.to_pretty);    // TO prettified (if used)*/
        },

        onChange: function (data) {
            // Called every time handle position is changed

            console.log(data.from);
        },

        onFinish: function (data) {
            // Called then action is done and mouse is released

            console.log(data.to);
        },

        onUpdate: function (data) {
            // Called then slider is changed using Update public method

            console.log(data.from_percent);
        }
    });
}


formStarted();

//layui初始化
function formStarted() {
    layui.use(['form', 'slider', 'layer', 'element', 'table'], function () {
        var layer = layui.layer
            , form = layui.form
            , slider = layui.slider
            , element = layui.element
            , table = layui.table;

        /*
                table.render({
                    elem: '#test'
                    /!*  ,url:'/demo/table/user/'*!/
                    ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                    ,cols: [[
                        {field:'projectname', title: '项目名称' }
                        ,{field:'workcontent',  title: '工作内容'}
                        ,{field:'finishpercent',  title: '完成比例'}
                        ,{field:'operate',  title: '操作'}
                    ]]
                    ,data: [{
                        "projectname": "1104统计报送"
                        ,"workcontent": "请填写工作内容"
                        ,"finishpercent": "100%"
                    }]
                    ,skin: 'line' //表格风格
                    ,even: true
                    ,page: false //是否显示分页
                });
        */

/*        addcookie("userId","ZhaoZulong");*/
/*        var userCode001= getcookie("userId");
        console.log("get cookies"+userCode001);*/
        //获取当前用户用户userId
        var urlCode = location.href.split("code=")[1].split('&')[0];
        console.log(urlCode);
        var userId = "";
        console.log(urlCode);
        $.ajax({
            url: "http://krmsoft.natapp1.cc/main/wxgetJSSUser",
            data: {
                data: urlCode
            },
            type: "POST",
            success: function (result) {
                console.log(result);
                userId=result;

                addcookie("userId",userId);
               /* layer.msg("打开页面，将usrId放到cookie中："+userId, {icon: 2});*/
                // var userCode1= getcookie("userId");
             /*   layer.msg(userCode1, {icon: 2});*/
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
        });






        form.on('submit(formDemo)', function (data) {
            var formTodayData = new FormData($("#formToday")[0]);

            var jsonData01 = {};
            formTodayData.forEach((value, key) => jsonData01[key] = value);
            jsonData01.worktype = 'today';

            var formTomorrowData = new FormData($("#formTomorrow")[0]);
            var jsonData02 = {};
            formTomorrowData.forEach((value, key) => jsonData02[key] = value);
            jsonData02.worktype = 'tomorrow';

            var formSubmitData = new FormData($("#formSubmit")[0]);
            var jsonData03 = {};
            formSubmitData.forEach((value, key) => jsonData03[key] = value);
            jsonData03.worktype = 'submit';
            var jsonCommit = {};
            var jsonUserInfo = {};

            var saveUrl = "http://krmsoft.natapp1.cc/todaywork/doufuTodayWork/wxworksave";


            var loading = layer.msg('正在保存', {icon: 16, shade: 0.3, time: 0});

            jsonUserInfo.userCode = getcookie("userId");
           /* layer.msg("保存页面时，得到usrId："+getcookie("userId"), {icon: 2});*/
            jsonCommit.userIfno = jsonUserInfo;
            jsonCommit.today = jsonData01;
            jsonCommit.tomorrow = jsonData02;
            jsonCommit.submit = jsonData03;
            console.log(getformTodayData);
            jsonCommit = JSON.stringify(jsonCommit);

            $.ajax({
                method: "POST",
                url: saveUrl,       //提交表单的地址
                contentType: "application/json;charset-UTF-8",
                data: jsonCommit,
                dataType: 'json',
                success: function (res) {
                    layer.close(loading);
                    if (res.code == 1) {
                        layer.msg(res.msg, {icon: 6});
                        $("#formToday")[0].reset();
                        $("#formTomorrow")[0].reset();
                        $("#formSubmit")[0].reset();
                    }
                    if (res.code == 0) {
                        layer.msg(res.msg, {icon: 6});
                        $("#formToday")[0].reset();
                        $("#formTomorrow")[0].reset();
                        $("#formSubmit")[0].reset();
                    }
                },
                error: function () {
                    layer.close(loading);
                    layer.msg("日报提交失败！", {icon: 2});
                }
            });
            /*return false;  //防止表单提交两次*/
        });

        function getformTodayData() {
            console.log($('#formToday').serializeJSON());
            console.log(JSON.stringify($('#formToday').serializeJSON()));
            return JSON.stringify($('#formToday').serializeJSON());
        }

    });
}
$("#btnResetForm").on('click', function () {
    $("#formToday")[0].reset();
    $("#formTomorrow")[0].reset();
    $("#formSubmit")[0].reset();
})

//事件处理

//今天项目 添加
var todayAddNum = "1"
$("#todayOptionAdd").on('click', function () {
    var todayOptionItem = ' <div id="todayOption">\
        <fieldset class="layui-elem-field">\
        <div class="layui-field-box">\
        <div class="layui-form-item" style="width: auto">\
        <select id="projectName_' + todayAddNum + '"  name="projectName_' + todayAddNum + '"lay-verify="required">\
        <option value=""></option>\
        <option value="1104监管报送">1104监管报送</option>\
        <option value="人行统计报送">人行统计报送</option>\
        <option value="PISA支付报送">PISA支付报送</option>\
        <option value="EAST数据报送">EAST数据报送</option>\
        <option value="支付结算报送">支付结算报送</option>\
        <option value="客户风险报送">客户风险报送</option>\
        <option value="非居民涉税报送">非居民涉税报送</option>\
        <option value="境外银行卡交易">境外银行卡交易</option>\
        <option value="理财报送">理财报送</option>\
        <option value="信贷小微报送">信贷小微报送</option>\
        <option value="助农取款报送">助农取款报送</option>\
        <option value="监管数据治理">监管数据治理</option>\
        <option value="大数据分析平台">大数据分析平台</option>\
        <option value="行内报表平台">行内报表平台</option>\
        <option value="MAST非现场合规监测">MAST非现场合规监测</option>\
        <option value="反洗钱报送">反洗钱报送</option>\
        <option value="其它临时工作">其它临时工作</option>\
        </select>\
        </div>\
        <div class="layui-form-item layui-form-text" style="width: auto;">\
        <textarea id="projectMessage_' + todayAddNum + '" name ="projectMessage_' + todayAddNum + '" class="layui-textarea"\
         rows="2" style="resize: none;min-height: auto;" placeholder="请输入工作内容"></textarea>\
        </div>\
        <div class="layui-form-item">\
        <input type="text" class="js-range-slider" id="finishPercent_' + todayAddNum + '" name="finishPercent_' + todayAddNum + '"\
         value=""/>\
        </div>\
        </div>\
       </fieldset>\
        </div>';
    $("#todayOption").append(todayOptionItem);
    todayAddNum++;

    //重新渲染表单元素
    layui.use('form', function () {
        var form = layui.form;
        form.render();
    });

    sliderInit();//初始化滑块
})

//明天工作添加
var tomorrowAddNum = "1"
$("#tomorrowOptionAdd").on('click', function () {

    var tomorrowOptionItem = ' <div id="tomorrowOption">\
        <fieldset class="layui-elem-field">\
        <div class="layui-field-box">\
        <div class="layui-form-item" style="width: auto">\
        <select id="tomorrow_projectName_' + tomorrowAddNum + '" lay-verify="required">\
        <option value=""></option>\
        <option value="1104监管报送">1104监管报送</option>\
        <option value="人行统计报送">人行统计报送</option>\
        <option value="PISA支付报送">PISA支付报送</option>\
        <option value="EAST数据报送">EAST数据报送</option>\
        <option value="支付结算报送">支付结算报送</option>\
        <option value="客户风险报送">客户风险报送</option>\
        <option value="非居民涉税报送">非居民涉税报送</option>\
        <option value="境外银行卡交易">境外银行卡交易</option>\
        <option value="理财报送">理财报送</option>\
        <option value="信贷小微报送">信贷小微报送</option>\
        <option value="助农取款报送">助农取款报送</option>\
        <option value="监管数据治理">监管数据治理</option>\
        <option value="大数据分析平台">大数据分析平台</option>\
        <option value="行内报表平台">行内报表平台</option>\
        <option value="MAST非现场合规监测">MAST非现场合规监测</option>\
        <option value="反洗钱报送">反洗钱报送</option>\
        <option value="其它临时工作">其它临时工作</option>\
        </select>\
        </div>\
        <div class="layui-form-item layui-form-text" style="width: auto;">\
        <textarea id="tomorrow_projectMessage_' + tomorrowAddNum + '" class="layui-textarea"\
         rows="2" style="resize: none;min-height: auto;" placeholder="请输入工作内容"></textarea>\
        </div>\
        </div>\
       </fieldset>\
        </div>';
    $("#tomorrowOption").append(tomorrowOptionItem);
    tomorrowAddNum++;

    //重新渲染表单元素
    layui.use('form', function () {
        var form = layui.form;
        form.render();
    });

    sliderInit();//初始化滑块
})


var form = layui.form;

//存Cookie的值 仅同级文件可用
function addcookie(name,value,expireHours){
    var exp = new Date();
    exp.setTime(exp.getTime() + expireHours);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}


//存Cookie的值 加了cookie作用域 会产生全局有效的cookie
function ADDcookie(name,value,expireHours){
    var exp = new Date();
    exp.setTime(exp.getTime() + expireHours);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString() +"; path=/";
}

function GetCookies(offset) {
    var endstr = document.cookie.indexOf(";", offset);
    if (endstr == -1) endstr = document.cookie.length;
    return unescape(document.cookie.substring(offset, endstr));
}


//取Cookie的值
function getcookie(name) {
    var arg = name + "=";
    var alen = arg.length;
    var clen = document.cookie.length;
    var i = 0;
    while (i < clen) {
        var j = i + alen;
        if (document.cookie.substring(i, j) == arg) return GetCookies(j);
        i = document.cookie.indexOf(" ", i) + 1;
        if (i == 0) break;
    }
    return "";
}



/*
$.ajax({
    //请求方式
    type: "post",
    //请求的媒体类型
    contentType: "application/json;charset=UTF-8",
    //请求地址
    url: "http://krmsoft.natapp1.cc/main/wxgetJSSConfig",
    dataType: 'text',
    //数据，json字符串
    data: {},
    //请求成功
    success: function (result) {
        /!*  console.log(result);*!/
        var data = eval("(" + result + ")");
        /!* console.log(data);*!/

        wx.config({
            beta: true,// 必须这么写，否则wx.invoke调用形式的jsapi会有问题
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: data.appId, // 必填，企业微信的corpID
            timestamp: data.timestamp, // 必填，生成签名的时间戳
            nonceStr: data.nonceStr, // 必填，生成签名的随机串
            signature: data.signature,// 必填，签名，见附录1
            jsApiList: ['checkJsApi', 'chooseImage',
                'openEnterpriseChat']
            // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
        wx.ready(function () {
            wx.checkJsApi({
                jsApiList: ['chooseImage', 'openEnterpriseChat'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
                success: function (ress) {
                    console.log("检测通过");
                    // 以键值对的形式返回，可用的api值true，不可用为false
                    // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
                }
            });

            //获取当前用户用户userId
            var urlCode = location.href.split("code=")[1].split('&')[0]



        });
    },
    //请求失败，包含具体的错误信息
    error: function (e) {
        console.log(e.status);
        console.log(e.responseText);
    }
});*/


