//项目录入
console.log("项目录入");

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
    window.location.replace('../pages/error.html')
}


//初始化选项卡高度
var winHeight = document.documentElement.clientHeight;//页面高度

// var buttonHeight = winHeight - $("#layuiAll").height();//底部高度

// var detailHeight = winHeight - buttonHeight - 153;//内页高度

var detailHeight = $("#layuiAll").height() - 50 - 58 - 41 - 20 - 20;

$("#todayDetail").attr("style","height: "+detailHeight+"px;overflow: scroll;");//设置表单高度

$("#projectSummary").attr("style","resize: none;height:"+(detailHeight-50)+"px");//设置工作总结高度

$("body").attr("style","height: 100%;opacity: 1;");//显示body内容

sliderInit();//初始化滑块
function sliderInit(){
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
    
            console.log(data.input);        // jQuery-link to input
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
            console.log(data.to_pretty);    // TO prettified (if used)
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
    layui.use(['form', 'slider', 'layer', 'element'], function () {
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function (data) {
            //layer.msg(JSON.stringify(data.field));
            layer.msg("提交成功");
            return false;
        });

        //底部加号点击事件
        $("#addButton").on('click', function () {
            layer.open({
                type: 1
                , title: '更多功能'
                , closeBtn: 2
                , shadeClose: true
                , area: '300px;'
                , shade: 0.8
                , id: 'layui_detail' //设定一个id，防止重复弹出
                , btnAlign: 'c'
                , btn: ['确定']
                , moveType: 1 //拖拽模式，0或者1
                , content: $('#addButtonDetail')
                , end: function (res) {
                    $("#addButtonDetail").css("display", 'none');
                }
                , yes: function (index, layero) {
                    layer.close(index)
                }
                , cancel: function (index, layero) {
                    layer.close(index)
                }
            });

            let my_range = $("#range-slider_0").data("ionRangeSlider");//滑块取值
            console.log(my_range);
            console.log(my_range.old_from)
        });
    });
}


//事件处理

//今天项目 添加
var todayAddNum = "1"
$("#todayOptionAdd").on('click', function () {
    var optionDetail = '<hr class="layui-bg-blue" style="margin-top: 10%;margin-bottom: 10%;">\
    <div class="layui-form-item">\
    <label class="layui-form-label">项目名称</label>\
    <div class="layui-input-block">\
        <select id="projectName_'+ todayAddNum + '" lay-verify="required">\
            <option value=""></option>\
            <option value="0">客户风险</option>\
            <option value="1">金标</option>\
            <option value="2">银标</option>\
            <option value="3">数据治理</option>\
        </select>\
    </div>\
</div>\
<div class="layui-form-item layui-form-text">\
    <label class="layui-form-label">工作内容</label>\
    <div class="layui-input-block">\
        <textarea id="projectMessage_'+ todayAddNum + '" placeholder="请输入内容" class="layui-textarea" rows="2" style="resize: none;min-height: auto;"></textarea>\
    </div>\
</div>\
<div class="layui-form-item">\
    <label class="layui-form-label">完成比例</label>\
    <div class="layui-input-block" style="padding-right: 12px;">\
        <input type="text" class="js-range-slider" id="range-slider_'+ todayAddNum + '" name="my_range" value="" />\
    </div>\
</div>';
    $("#todayOption").append(optionDetail);
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
    var tomorrowOptionDetail = '<hr class="layui-bg-blue" style="margin-top: 10%;margin-bottom: 10%;">\
    <div class="layui-form-item">\
    <label class="layui-form-label">项目名称</label>\
    <div class="layui-input-block">\
        <select id="projectName_'+ tomorrowAddNum + '" lay-verify="required">\
            <option value=""></option>\
            <option value="0">客户风险</option>\
            <option value="1">金标</option>\
            <option value="2">银标</option>\
            <option value="3">数据治理</option>\
        </select>\
    </div>\
</div>\
<div class="layui-form-item layui-form-text">\
    <label class="layui-form-label">工作内容</label>\
    <div class="layui-input-block">\
        <textarea id="projectMessage_'+ tomorrowAddNum + '" placeholder="请输入内容" class="layui-textarea" rows="2" style="resize: none;min-height: auto;"></textarea>\
    </div>\
</div>\
<div class="layui-form-item">\
    <label class="layui-form-label">完成比例</label>\
    <div class="layui-input-block" style="padding-right: 12px;">\
        <input type="text" class="js-range-slider" id="range-slider_'+ tomorrowAddNum + '" name="my_range" value="" />\
    </div>\
</div>';
    $("#tomorrowOption").append(tomorrowOptionDetail);
    tomorrowAddNum++;

    //重新渲染表单元素
    layui.use('form', function () {
        var form = layui.form;
        form.render();
    });

    sliderInit();//初始化滑块
})

//底部菜单 点击事件

//点击 首页
$("#homePage").on('click',function(){
    location.href = "../pages/homePage.html";
});

//点击项目图表按钮
$(document).on('click', '#goChart', function () {
    location.href = "../pages/projectChart.html";
});