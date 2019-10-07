$(function () {
    var value = $("#top").text();
/*    console.log(value);*/


    layui.use(['layer', 'form'], function () {
        var layer = layui.layer
            , form = layui.form;

/*        layer.msg('你好');*/

        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function (data) {
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });


    //WeixinJSBridge.call('hideOptionMenu');//禁菜单测试

    //图表
    // 基于准备好的dom，初始化echarts实例
    var worldMapContainer = document.getElementById('main');

    //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
    var resizeWorldMapContainer = function () {
        worldMapContainer.style.width = window.innerWidth + 'px';
        worldMapContainer.style.height = window.innerHeight + 'px';
        //rem单位
        //worldMapContainer.style.width = window.innerWidth/750*690+'px';
        //worldMapContainer.style.height = window.innerHeight/750*600+'px';
    };
    //设置容器高宽
    resizeWorldMapContainer();
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(worldMapContainer);

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'ECharts 入门示例'
        },
        tooltip: {},
        legend: {
            data: ['销量'],
            height: worldMapContainer.style.height,
            width: worldMapContainer.style.width
        },
        xAxis: {
            data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    //用于使chart自适应高度和宽度
    window.onresize = function () {
        //重置容器高宽
        resizeWorldMapContainer();
        myChart.resize();
    };




    //接口
    //请求本地json数据
    // $.ajax({
    //     type: "get",
    //     url: "../data/test.json",
    //     dataType: "json",
    //     success: function (res) {
    //         console.log(res);
    //     }, error: function () {
    //         console.log(error)
    //     }
    // });


    // 请求后台json数据
    // 请求参数
    // var requestPram = {
    //     affPersonClientCategoryCode: "",
    //     affPersonName: "",
    //     clientNum: "1",
    //     limit: 0,
    //     page: 0
    // };
    //
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
          /*  console.log(result);*/
            var data = eval("(" + result + ")");
           /* console.log(data);*/

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


                $.ajax({
                    url: "http://krmsoft.natapp1.cc/main/wxgetJSSUser",
                    data: {
                        data: eval('(' + urlCode + ')')
                    },
                    type: "POST",
                    success: function (result) {
                        console.log(result);
                        var data = eval("(" + result + ")");
                        console.log(data);

                        //打开userIds对话框，运行则微信端直接跳到聊天框
                        $('#talk').unbind("click"); //移除click事件
                        $("#talk").click(function () {
                            wx.openEnterpriseChat({
                                // 注意：userIds和externalUserIds至少选填一个，且userIds+openIds总数不能超过2000。
                                userIds: data.UserId, //参与会话的企业成员列表，格式为userid1;userid2;...，用分号隔开。
                                externalUserIds: '', // 参与会话的外部联系人列表，格式为userId1;userId2;…，用分号隔开。
                                groupName: '测试讨论组', // 必填，会话名称。单聊时该参数传入空字符串""即可。
                                success: function (res) {
                                    // 回调
                                    console.log(res)
                                },
                                fail: function (res) {
                                    if (res.errMsg.indexOf('function not exist') > -1) {
                                        alert('版本过低请升级')
                                    }
                                }
                            });
                        });

                        hideMenu();//禁菜单
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(jqXHR.responseText);
                    }
                });
            });
        },
        //请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
        }
    });







    function hideMenu() {
        //过滤部分页面
        var pasPage = ['index.html'];
        var flage = true;
        for (var i = 0; i < pasPage.length; i++) {
            if (location.href.indexOf(pasPage[i]) != "-1") {//存在过滤页面
                flage = false;
                break;
            }
        }
        if (true) {//flage
            function onBridgeReady() {
                WeixinJSBridge.call('hideOptionMenu');
            }

            if (typeof WeixinJSBridge == "undefined") {
                if (document.addEventListener) {
                    document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
                } else if (document.attachEvent) {
                    document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                    document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
                }
            } else {
                onBridgeReady();
            }
        }
    }



})