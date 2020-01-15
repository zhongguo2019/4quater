$(function () {
    var value = $("#top").text();
    console.log(value);
    layui.use(['carousel', 'form','element','layer'], function(){
       var carousel = layui.carousel
            ,form = layui.form;
        var element = layui.element;

        var layer = layui.layer;

        layui.use('jquery',function(){
            var $ = layui.jquery;
            var W = $(window).width();
            /*        var b = 1920/460;//我的图片比例
                    var H = $(window).height();*/
            $(window).resize(function () {
                window.location.reload()
            });

        })

        carousel.render({
            elem: '#test10'
            ,width: '100%' //设置容器宽度
            ,height: '400px' //按比例和浏览器可视页面宽度来获取高度
            ,arrow: 'always' //始终显示箭头
            ,interval: 5000
        });

        var $ = layui.$, active = {
            set: function(othis){
                var THIS = 'layui-bg-normal'
                    ,key = othis.data('key')
                    ,options = {};

                othis.css('background-color', '#b81e21').siblings().removeAttr('style');
                options[key] = othis.data('value');
                ins3.reload(options);
            }
        };

        //监听开关
        form.on('switch(autoplay)', function(){
            ins3.reload({
                autoplay: this.checked
            });
        });

        $('.demoSet').on('keyup', function(){
            var value = this.value
                ,options = {};
            if(!/^\d+$/.test(value)) return;

            options[this.name] = value;
            ins3.reload(options);
        });

        //其它示例
        $('.demoTest .layui-btn').on('click', function(){
            var othis = $(this), type = othis.data('type');
            active[type] ? active[type].call(this, othis) : '';
        });

    });




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

            var data = eval("(" + result + ")");
            console.log(data);
        },
        //请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
        }

    });

    //获取当前用户用户userId
    var urlCode = location.href.split("code=")[1].split('&')[0];
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
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText);
        }
    });






})
