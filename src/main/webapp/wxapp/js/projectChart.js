//项目图表
console.log("项目图表");


//导航栏
$(".mobile-inner-header-icon").click(function () {
    $(this).toggleClass("mobile-inner-header-icon-click mobile-inner-header-icon-out");
    $(".mobile-inner-nav").slideToggle(250);
});
$(".mobile-inner-nav a").each(function (index) {
    $(this).css({
        'animation-delay': (index / 10) + 's'
    });
});

/*
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
*/
//发起Ajax请求
//getData();
// function getData() {
//     ajaxSendMessage('get', '', '../../plugin/data/table.json', 'text');//请求参数配置
// }
function ajaxCallBack(type, value) {//请求回调
    // if (type == "success") {
    //     tableData = value;
    //     console.log(tableData);

    //     //初始化表格
    //     tableStarted();

    // } else {
    //     console.log("接口调用失败");
    // }
}


tableStarted();

//layui初始化
function tableStarted() {
    layui.use(['layer', 'jquery', 'form'], function () {

        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;

        //图表类目选择    
        form.on('select(chartChange)', function (data) {
            switch (data.value) {
                case '0':
                    $("[name='chartAll']").hide();
                    $("#profitabilityChart").show();
                    tipsOne();
                    break;
                case '1':
                    $("[name='chartAll']").hide();
                    $("#incomeChart").show();
                    tipsTwo();
                    break;
                case '2':
                    $("[name='chartAll']").hide();
                    $("#saveChart").show();
                    tipsThree();
                    break;
                case '3':
                    $("[name='chartAll']").hide();
                    $("#insertOutputChart").show();
                    tipsFour();
                    break;
                case '4':
                    $("[name='chartAll']").hide();
                    $("#savingsRanking").show();
                    tipsFive();
                    break;
                case '4':
                    $("[name='chartAll']").hide();
                    $("#savingsRealPoint").show();
                    tipsSix();
                    break;
            }
            form.render('select');
            window.scrollTo(0, 0); //回到顶部
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
        });

    });
}

//图表展示
tipsOne();

//图例
function tipsOne() {

    //利润贡献度分析
    profitability();

    function profitability() {


        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('profitability');

        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';
            //rem单位
            //worldMapContainer.style.width = window.innerWidth/750*690+'px';
            //worldMapContainer.style.height = window.innerHeight/750*600+'px';
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '利润贡献度分析',
                x: 'center'
            },
            color: ['#1E264A'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                show: true,
                backgroundColor: '#DDE0F3',
                borderColor: '#000000',
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: ['2010年1月', '\n2010年2月', '2010年3月', '\n2010年4月', '2010年5月', '\n2010年6月'],
                    axisTick: {
                        alignWithLabel: true
                    },
                    axisLabel: {
                        'interval': 0
                    },
                    splitArea: {
                        show: true,
                        areaStyle: {//x轴间隔颜色
                            color: ['rgba(219,224,242,0.3)', 'rgba(255,255,255)']
                        }
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [//系列列表
                {
                    name: '净利润（亿元）',
                    type: 'bar',
                    barWidth: '60%',
                    label: {
                        show: true,
                        position: 'top'
                    },
                    data: [0.12, 0.18, 0.21, 0.19, 0.22, 0.27]
                }
            ],
            legend: {//图例组件
                type: 'plain',
                orient: 'vertical',
                top: '5%',
                left: 'right'
            }
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };

    }


    //产品利润分析
    productProfit();

    function productProfit() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('productProfit');
        var rotate;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                rotate = 50;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '产品利润分析',
                x: 'center',
                textStyle: {
                    color: '#FFFFFF'
                },
                subtextStyle: {
                    color: '#FFFFFF'
                }
            },
            color: ['#FFFFFF'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                show: true,
                backgroundColor: '#DDE0F3',
                borderColor: '#000000',
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: ['存款', '贷款', '票据', '理财产品', '股票', '同业', '外汇', '其它投资'],
                    axisTick: {
                        alignWithLabel: true
                    },
                    nameTextStyle: {
                        color: '#000000'
                    },
                    axisLabel: {
                        'interval': 0,
                        'rotate': rotate,//文字旋转,
                        textStyle: {
                            color: '#000000'
                        }
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    splitNumber: 11,//分割段数

                    axisLabel: {
                        textStyle: {
                            color: '#000000'
                        }
                    },
                    splitArea: {//间隔区域样式
                        'show': true,
                        areaStyle: {
                            color: ['rgba(250,250,250)', 'rgba(240,240,240)']
                        }
                    }
                }

            ],
            series: [//系列列表
                {
                    name: '利润（万元）',
                    type: 'bar',
                    barWidth: '60%',
                    label: {//柱子上的文子块
                        show: true,
                        position: 'top',
                        backgroundColor: '#ffffff',
                        shadowColor: '#AC9C80',//阴影颜色
                        borderColor: '#E0E0E0',//边框颜色
                        //borderWidth:'1',//边框宽度
                        shadowOffsetX: '3',//阴影偏移
                        shadowOffsetY: '3',
                        shadowBlur: '3',//阴影长度
                        padding: [4, 4, 4, 4]

                    },
                    data: [1725, 4327, 2365, 800, 987, 500, 387, 426],
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                            offset: 0,
                            color: '#FCDE01'
                        }, {
                            offset: 1,
                            color: '#EC7606'
                        }]),
                        //borderColor:'#BE914C',//描边颜色
                        shadowColor: '#AC9C80',//阴影颜色
                        shadowOffsetX: '3'//阴影水平方向偏移
                    }
                }
            ],
            legend: {//图例组件
                type: 'plain',
                orient: 'vertical',
                left: 'right'
            }
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }

    //贷款产品利润贡献度分析
    contribute();

    function contribute() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('contribute');
        var radius = 35;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 27;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '贷款产品利润贡献度分析',
                x: 'center',
                textStyle: {
                    color: '#ffffff',
                }
            },
            color: ['#F0A20A', '#00BA01', '#AF0100'],
            tooltip: {//点击后显示格式
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {//图例
                orient: 'vertical',
                left: 'right',
                top: '60%',
                data: ['短期贷款', '期中贷款', '期长贷款']
            },
            label: {//图上文字
                show: true,
                position: 'outside',
                formatter: '利润(万元):\n{b}\n{c} ；{d}%',
                color: '#000000',
                fontWeight: 'bold',
                borderColor: '#AEAEAE',
                borderWidth: '1',
                padding: 4
            },
            labelLine: {//外部文字线条样式
                show: false,
                lineStyle: {
                    color: '#fff'
                }
            },
            itemStyle: {//图案样式
                //borderColor:"#eee",
                //borderWidth:3,
                shadowColor: '#EDEDED',
                shadowOffsetX: 1.5,
                shadowOffsetY: 1.5,
                shadowBlur: 10
            },
            series: [
                {
                    name: '产品类别',
                    type: 'pie',
                    radius: radius + '%',
                    center: ['45%', '40%'],
                    data: [
                        {value: 1876, name: '短期贷款'},
                        {value: 1465, name: '期中贷款'},
                        {value: 986, name: '期长贷款'}
                    ],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }

    //贷款产品分行利润贡献度分析
    productContribute();

    function productContribute() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('productContribute');
        var radius = 30;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 20;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '贷款产品分行利润贡献度分析',
                x: 'center'
            },
            color: ['#017ECA', '#C09D01', '#00CA07'],
            tooltip: {//点击后显示格式
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {//图例
                //type: 'scroll',
                bottom: 50,
                orient: 'horizontal',
                //top:'60%',
                data: ['宝鸡分行', '咸阳', '渭南', '榆林', '汉中', '总行营业部', '大唐芙蓉园']
            },
            label: {//图上文字
                show: true,
                position: 'outside',
                fontSize: 12,
                formatter: '利润(万元):{b}\n{c} ；{d}%',
                color: '#000000',
                fontWeight: 'bold',
                // borderColor:'#AEAEAE',
                // borderWidth:'1',
                padding: 4
            },
            itemStyle: {//图案样式
                //borderColor:"#eee",
                //borderWidth:3,
                shadowColor: '#EDEDED',
                shadowOffsetX: 1.5,
                shadowOffsetY: 1.5,
                shadowBlur: 10
            },
            avoidLabelOverlap: true,
            series: [
                {
                    name: '产品类别',
                    type: 'pie',
                    radius: radius + '%',
                    center: ['55%', '40%'],
                    minAngle: 5,           　　 //最小的扇区角度（0 ~ 360），用于防止某个值过小导致扇区太小影响交互
                    avoidLabelOverlap: true,   //是否启用防止标签重叠策略
                    data: [
                        {value: 567, name: '宝鸡分行'},
                        {value: 234, name: '咸阳'},
                        {value: 569, name: '渭南'},
                        {value: 973, name: '榆林'},
                        {value: 432, name: '汉中'},
                        {value: 276, name: '总行营业部'},
                        {value: 1276, name: '大唐芙蓉园'}
                    ],
                    itemStyle: {
                        // emphasis: {
                        //     shadowBlur: 10,
                        //     shadowOffsetX: 0,
                        //     shadowColor: 'rgba(0, 0, 0, 0.5)'
                        // }
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //收入结构分析  环形图
    income();

    function income() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('income');
        var radius = 30, radiusOut = 70;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 25;
                radiusOut = 60;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '收入结构分析',
                x: 'center'
            },
            color: [new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                offset: 0,
                color: '#CF0004'
            }, {
                offset: 1,
                color: '#7D0401'
            }]),
                new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                    offset: 0,
                    color: '#CE6301'
                }, {
                    offset: 1,
                    color: '#8C4600'
                }]),
                new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                    offset: 0,
                    color: '#878605'
                }, {
                    offset: 1,
                    color: '#D1CA00'
                }]),
                new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                    offset: 0,
                    color: '#3C8510'
                }, {
                    offset: 1,
                    color: '#65C900'
                }]),
                new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                    offset: 0,
                    color: '#30BE13'
                }, {
                    offset: 1,
                    color: '#54DF19'
                }])],
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
                //type: 'scroll',
                orient: 'horizontal',
                bottom: 10,
                data: ['贷款利息收入', '金融企业往来收入', '债券利息及投资收入', '手续费收入', '其它收入']
            },
            series: [
                {
                    name: '收入结构',
                    type: 'pie',
                    radius: [radius + '%', radiusOut + '%'],
                    //minAngle: 5,           　　 //最小的扇区角度（0 ~ 360），用于防止某个值过小导致扇区太小影响交互
                    avoidLabelOverlap: true,   //是否启用防止标签重叠策略
                    label: {//图上文字
                        show: true,
                        position: 'outside',
                        fontSize: 15,
                        formatter: '{c}',
                        color: '#000000',
                        fontWeight: 'bold',
                        // borderColor:'#AEAEAE',
                        // borderWidth:'1',
                        //padding:4
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data: [
                        {value: 2.65, name: '贷款利息收入'},
                        {value: 1.56, name: '金融企业往来收入'},
                        {value: 0.57, name: '债券利息及投资收入'},
                        {value: 0.07, name: '手续费收入'},
                        {value: 0.0225, name: '其它收入'}
                    ],
                    itemStyle: {
                        //borderColor:'#BE914C',//描边颜色
                        shadowColor: '#AC9C80',//阴影颜色
                        shadowOffsetX: '3'//阴影水平方向偏移
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //各项存款时序分析
    sequence();

    function sequence() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('sequence');
        var rotate;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                rotate = 50;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '各项存款时序分析',
                x: 'center'
            },
            color: ['#1E264A'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                show: true,
                backgroundColor: '#DDE0F3',
                borderColor: '#000000',
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: ['开业日', '\n2010年3月31日', '2010年12月31日', '\n2010年3月31日'],
                    axisTick: {
                        alignWithLabel: true
                    },
                    axisLabel: {
                        'interval': 0,
                        'rotate': rotate//文字旋转
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    splitNumber: 11,//分割段数
                    splitArea: {//间隔区域样式
                        'show': true,
                        areaStyle: {
                            color: ['rgba(250,250,250)', 'rgba(240,240,240)']
                        }
                    }
                }
            ],
            series: [//系列列表
                {
                    name: '各项存款（亿元）',
                    type: 'bar',
                    barWidth: '60%',
                    label: {//柱子上的文子块
                        show: true,
                        position: 'top',
                        backgroundColor: '#ffffff',
                        shadowColor: '#AC9C80',//阴影颜色
                        borderColor: '#E0E0E0',//边框颜色
                        //borderWidth:'1',//边框宽度
                        shadowOffsetX: '3',//阴影偏移
                        shadowOffsetY: '3',
                        shadowBlur: '3',//阴影长度
                        padding: [4, 4, 4, 4]
                    },
                    data: [5583, 2907, 3739, 10097],
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                            offset: 0,
                            color: '#FCDE01'
                        }, {
                            offset: 1,
                            color: '#EC7606'
                        }]),
                        //borderColor:'#BE914C',//描边颜色
                        shadowColor: '#AC9C80',//阴影颜色
                        shadowOffsetX: '3'//阴影水平方向偏移
                    }
                }
            ],
            legend: {//图例组件
                type: 'plain',
                orient: 'vertical',
                left: 'right',
                top: '30'
            }
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }

    //分行存款时点计划完成情况分析   双柱状图
    sequenceFinish();

    function sequenceFinish() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('sequenceFinish');
        var rotate;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                rotate = 50;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '分行存款时点计划完成情况分析',
                x: 'center'
            },
            color: ['#1E264A'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                show: true,
                backgroundColor: '#DDE0F3',
                borderColor: '#000000',
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: ['宝鸡', '\n咸阳', '渭南', '\n榆林', '汉中', '\n营业部', '大唐芙蓉园'],
                    axisTick: {
                        alignWithLabel: true
                    },
                    axisLabel: {
                        'interval': 0,
                        'rotate': rotate//文字旋转
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    splitNumber: 11,//分割段数
                    splitArea: {//间隔区域样式
                        'show': true,
                        areaStyle: {
                            color: ['rgba(250,250,250)', 'rgba(240,240,240)']
                        }
                    }
                }
            ],
            series: [//系列列表
                {
                    name: '计划数(万元)',
                    type: 'bar',
                    barWidth: '20%',
                    label: {//柱子上的文子块
                        show: true,
                        position: 'top',
                        backgroundColor: '#ffffff',
                        shadowColor: '#AC9C80',//阴影颜色
                        borderColor: '#E0E0E0',//边框颜色
                        //borderWidth:'1',//边框宽度
                        shadowOffsetX: '3',//阴影偏移
                        shadowOffsetY: '3',
                        shadowBlur: '3',//阴影长度
                        padding: [4, 4, 4, 4]
                    },
                    data: [105000, 90000, 80000, 341000, 33000, 128000, 67000],
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                            offset: 0,
                            color: '#FCDE01'
                        }, {
                            offset: 1,
                            color: '#EC7606'
                        }]),
                        //borderColor:'#BE914C',//描边颜色
                        shadowColor: '#AC9C80',//阴影颜色
                        shadowOffsetX: '3'//阴影水平方向偏移
                    }
                },
                {
                    name: '完成数(万元)',
                    type: 'bar',
                    barWidth: '20%',
                    label: {//柱子上的文子块
                        show: true,
                        position: 'top',
                        backgroundColor: '#ffffff',
                        shadowColor: '#AC9C80',//阴影颜色
                        borderColor: '#E0E0E0',//边框颜色
                        //borderWidth:'1',//边框宽度
                        shadowOffsetX: '3',//阴影偏移
                        shadowOffsetY: '3',
                        shadowBlur: '3',//阴影长度
                        padding: [4, 4, 4, 4]
                    },
                    data: [-52633, -63701, -37240, 85227, 15487, 157017, 13635],
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                            offset: 0,
                            color: '#00DD03'
                        }, {
                            offset: 1,
                            color: '#009E05'
                        }]),
                        //borderColor:'#BE914C',//描边颜色
                        shadowColor: '#AC9C80',//阴影颜色
                        shadowOffsetX: '3'//阴影水平方向偏移
                    }
                }
            ],
            legend: {//图例组件
                data: ['计划数(万元)', '完成数(万元)'],
                type: 'plain',
                orient: 'horizontal',
                top: 30
            }
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //分行对公存款时点计划完成分析   条形图
    timePlan();

    function timePlan() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('timePlan');
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '分行对公存款时点计划完成分析'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            color: ['#BD0400', '#F6A719'],
            legend: {
                data: ['时点完成数(万元)', '时点计划数(万元)'],
                top: 30
            },
            grid: {
                //left: '-20%',
                right: '20%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                splitNumber: '6',
                minInterval: 1,
                axisTick: {
                    interval: 0
                },
                axisLabel: {
                    rotate: 30
                }
            },
            yAxis: {
                type: 'category',
                position: 'left',
                z: 3,
                offset: 0,
                boundaryGap: true,
                textStyle: {
                    lineHeight: '50'
                },
                data: ['宝鸡', '咸阳', '渭南', '榆林', '汉中', '营业部', '大唐芙蓉园']
            },
            series: [
                {
                    name: '时点计划数(万元)',
                    type: 'bar',
                    data: [55000, 45000, 35000, 185000, 15000, 110000, 55000],
                    label: {//柱子上的文子块
                        show: true,
                        position: 'right',
                        color: '#000000',
                        fontSize: '10',
                        backgroundColor: '#ffffff',
                        shadowColor: '#AC9C80',//阴影颜色
                        borderColor: '#E0E0E0',//边框颜色
                        //borderWidth:'1',//边框宽度
                        shadowOffsetX: '3',//阴影偏移
                        shadowOffsetY: '3',
                        shadowBlur: '3',//阴影长度
                        padding: [4, 4, 4, 4]
                    }
                },
                {
                    name: '时点完成数(万元)',
                    type: 'bar',
                    data: [-50030, -65061, -45655, 94932, 9464, 173527, 9381],
                    label: {//柱子上的文子块
                        show: true,
                        position: 'right',
                        color: '#000000',
                        fontSize: '10',
                        backgroundColor: '#ffffff',
                        shadowColor: '#AC9C80',//阴影颜色
                        borderColor: '#E0E0E0',//边框颜色
                        //borderWidth:'1',//边框宽度
                        shadowOffsetX: '3',//阴影偏移
                        shadowOffsetY: '3',
                        shadowBlur: '3',//阴影长度
                        padding: [4, 4, 4, 4]
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //分行对公存款时点计划完成分析   堆叠柱状图
    currentTerm();

    function currentTerm() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('currentTerm');
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '各分行存款结构分析(活期、定期)'
            },
            backgroundColor: "#ffffff",
            color: [
                new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                    offset: 0,
                    color: '#FCDE01'
                }, {
                    offset: 1,
                    color: '#EC7606'
                }]),
                new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                    offset: 0,
                    color: '#00DA01'
                }, {
                    offset: 1,
                    color: '#019800'
                }])
            ],
            legend: {
                top: 30,
                data: ['活期占比', '定期占比']
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            grid: {
                left: 'left',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                data: ['宝鸡', '咸阳', '渭南', '榆林', '汉中', '营业部', '大唐', '全行'],
                axisTick: {
                    alignWithLabel: true
                },
                axisLabel: {
                    interval: 0
                }

            }],
            yAxis: [{
                type: 'value',
                splitNumber: 10,
                axisLabel: {
                    formatter: '{value}%'
                }
            }],
            series: [{
                name: '活期占比',
                type: 'bar',
                stack: '总量',//配置相同的值 可以堆叠放置数据
                barWidth: '60%',
                label: {
                    show: true,
                    position: 'inside',
                    formatter: '{c}%',
                    color: '#000',
                    backgroundColor: 'rgba(255,255,255,0.8)',
                    borderColor: "#ebebeb",
                    borderWidth: 1
                },
                data: [55.83, 73.93, 39.33, 75.68, 72.78, 86.34, 84.87, 68.16]
            }, {
                name: '定期占比',
                type: 'bar',
                label: {
                    show: true,
                    position: 'inside',
                    formatter: '{c}%',
                    color: '#000',
                    backgroundColor: 'rgba(255,255,255,0.8)',
                    borderColor: "#ebebeb",
                    borderWidth: 1
                },
                stack: '总量',
                barWidth: '60%',
                data: [44.17, 26.07, 60.67, 24.32, 27.22, 13.66, 15.13, 31.84]
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
    }


    //各项贷款变化情况分析   三柱状图
    loanChange();

    function loanChange() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('loanChange');
        var rotate;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                rotate = 20;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '各项贷款变化情况分析',
                x: 'center'
            },
            color: ['#1E264A'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                show: true,
                top: 100,
                backgroundColor: '#DDE0F3',
                borderColor: '#000000',
                left: '0%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: ['开业日', '2010年3月末', '2010年12月末', '2011年3月末'],
                    axisTick: {
                        alignWithLabel: true
                    },
                    axisLabel: {
                        'interval': 0,
                        'rotate': rotate//文字旋转
                    },
                    //max:20 //关键第一句
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    splitNumber: 11,//分割段数
                    splitArea: {//间隔区域样式
                        'show': true,
                        areaStyle: {
                            color: ['rgba(250,250,250)', 'rgba(240,240,240)']
                        }
                    }
                }
            ],
            series: [//系列列表
                {
                    name: '贷款(亿元)',
                    type: 'bar',
                    barWidth: 15,//柱图宽度
                    barCategoryGap: '10', //每簇之间的间距
                    barGaps: '200', //簇内每个柱之间的距离
                    //barGap:'20%',//柱图间距
                    label: {//柱子上的文子块
                        show: true,
                        position: 'top',
                        //rotate  :-30,
                        backgroundColor: '#ffffff',
                        shadowColor: '#AC9C80',//阴影颜色
                        borderColor: '#E0E0E0',//边框颜色
                        //borderWidth:'1',//边框宽度
                        shadowOffsetX: '3',//阴影偏移
                        shadowOffsetY: '3',
                        shadowBlur: '3',//阴影长度
                        padding: [4, 4, 4, 4],
                        fontSize: 10
                    },
                    data: [64.84, 87.44, 128.74, 140.9],
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                            offset: 0,
                            color: '#FCDE01'
                        }, {
                            offset: 1,
                            color: '#EC7606'
                        }]),
                        //borderColor:'#BE914C',//描边颜色
                        shadowColor: '#AC9C80',//阴影颜色
                        shadowOffsetX: '3'//阴影水平方向偏移
                    }
                },
                {
                    name: '贴现(亿元)',
                    type: 'bar',
                    barWidth: 15,//柱图宽度
                    barCategoryGap: '10', //每簇之间的间距
                    barGaps: '200', //簇内每个柱之间的距离
                    label: {//柱子上的文子块
                        show: true,
                        position: 'top',
                        //rotate  :-30,
                        backgroundColor: '#ffffff',
                        shadowColor: '#AC9C80',//阴影颜色
                        borderColor: '#E0E0E0',//边框颜色
                        //borderWidth:'1',//边框宽度
                        shadowOffsetX: '3',//阴影偏移
                        shadowOffsetY: '3',
                        shadowBlur: '3',//阴影长度
                        padding: [4, 4, 4, 4],
                        fontSize: 10
                    },
                    data: [1, 10.69, 7.63, 7.09],
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                            offset: 0,
                            color: '#00DD03'
                        }, {
                            offset: 1,
                            color: '#009E05'
                        }]),
                        //borderColor:'#BE914C',//描边颜色
                        shadowColor: '#AC9C80',//阴影颜色
                        shadowOffsetX: '3'//阴影水平方向偏移
                    }
                },
                {
                    name: '合计(亿元)',
                    type: 'bar',
                    barWidth: 15,//柱图宽度
                    barCategoryGap: '10', //每簇之间的间距
                    barGaps: '200', //簇内每个柱之间的距离
                    barCategoryGap: '50',
                    label: {//柱子上的文子块
                        show: true,
                        position: 'top',
                        //rotate  :-30,
                        backgroundColor: '#ffffff',
                        shadowColor: '#AC9C80',//阴影颜色
                        borderColor: '#E0E0E0',//边框颜色
                        //borderWidth:'1',//边框宽度
                        shadowOffsetX: '3',//阴影偏移
                        shadowOffsetY: '3',
                        shadowBlur: '3',//阴影长度
                        padding: [4, 4, 4, 4],
                        fontSize: 10
                    },
                    data: [65.84, 98.13, 136.37, 147.99],
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                            offset: 0,
                            color: '#FB010C'
                        }, {
                            offset: 1,
                            color: '#850000'
                        }]),
                        //borderColor:'#BE914C',//描边颜色
                        shadowColor: '#AC9C80',//阴影颜色
                        shadowOffsetX: '3'//阴影水平方向偏移
                    }
                }
            ],
            legend: {//图例组件
                data: ['贷款(亿元)', '贴现(亿元)', '合计(亿元)'],
                type: 'plain',
                orient: 'horizontal',
                top: '30'
            }
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //贷款结构分析
    loanFormation();

    function loanFormation() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('loanFormation');
        var radius = 35;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 27;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '贷款结构分析',
                x: 'center'
            },
            color: ['#F0A20A', '#00BA01', '#AF0100'],
            tooltip: {//点击后显示格式
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {//图例
                orient: 'vertical',
                left: 'right',
                top: '60%',
                data: ['公司贷款', '小微型企业贷款', '个人贷款']
            },
            label: {//图上文字
                show: true,
                position: 'outside',
                formatter: '{b}\n{c};{d}%',
                color: '#000000',
                fontWeight: 'bold',
                borderColor: '#AEAEAE',
                borderWidth: '1',
                padding: 4
            },
            labelLine: {//外部文字线条样式
                show: false,
                lineStyle: {
                    color: '#fff'
                }
            },
            itemStyle: {//图案样式
                //borderColor:"#eee",
                //borderWidth:3,
                shadowColor: '#EDEDED',
                shadowOffsetX: 1.5,
                shadowOffsetY: 1.5,
                shadowBlur: 10
            },
            tooltip: {
                confine: true
            },
            series: [
                {
                    name: '贷款类别',
                    type: 'pie',
                    radius: radius + '%',
                    center: ['50%', '40%'],
                    data: [
                        {value: 80.18, name: '公司贷款'},
                        {value: 46.27, name: '小微型企业贷款'},
                        {value: 14.65, name: '个人贷款'}
                    ],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //贷款行业结构分析   分离型饼图
    loanIndustry();

    function loanIndustry() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('loanIndustry');
        var radius = 35;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 27;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '贷款行业结构分析',
                x: 'center'
            },
            color: ['#F5A814', '#C30001', '#052183', '#018B01', '#EA5D0E', '#AC11CD'],
            tooltip: {//点击后显示格式
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {//图例
                orient: 'horizontal',
                //left: 'right',
                top: '60%',
                data: ['农、林、牧渔业', '金融业', '教育', '采矿业', '房地产业', '其他......']
            },
            label: {//图上文字
                show: true,
                position: 'outside',
                formatter: '{d}%',
                color: '#000000',
                fontWeight: 'bold',
                //borderColor: '#AEAEAE',
                //borderWidth: '1',
                //padding: 4
            },
            itemStyle: {//图案样式
                //borderColor:"#eee",
                //borderWidth:3,
                shadowColor: '#EDEDED',
                shadowOffsetX: 1.5,
                shadowOffsetY: 1.5,
                shadowBlur: 10
            },
            tooltip: {
                confine: true
            },
            series: [
                {
                    name: '行业结构',
                    type: 'pie',
                    radius: radius + '%',
                    center: ['40%', '40%'],
                    data: [//设置为选中状态 图案就会分离
                        {value: 3.25, name: '农、林、牧渔业', selected: true},
                        {value: 10.47, name: '金融业', selected: true},
                        {value: 5.47, name: '教育', selected: true},
                        {value: 3.24, name: '采矿业', selected: true},
                        {value: 12.32, name: '房地产业', selected: true},
                        {value: 65.25, name: '其他......'}
                    ],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //公司贷款客户等级结构分析
    companyLoan();

    function companyLoan() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('companyLoan');
        var radius = 35;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 40;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '公司贷款客户等级结构分析',
                x: 'center'
            },
            color: [new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                offset: 0,
                color: '#EE7C00'
            }, {
                offset: 1,
                color: '#FED018'
            }]), new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                offset: 0,
                color: '#019605'
            }, {
                offset: 1,
                color: '#1ACB01'
            }]), new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                offset: 0,
                color: '#8B0100'
            }, {
                offset: 1,
                color: '#F2040A'
            }]), new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                offset: 0,
                color: '#880DA3'
            }, {
                offset: 1,
                color: '#CA1AED'
            }]), new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                offset: 0,
                color: '#057DE5'
            }, {
                offset: 1,
                color: '#37A5EA'
            }]), new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                offset: 0,
                color: '#DB2717'
            }, {
                offset: 1,
                color: '#F17F19'
            }]), new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                offset: 0,
                color: '#69B414'
            }, {
                offset: 1,
                color: '#A0E93F'
            }]), new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                offset: 0,
                color: '#8F0158'
            }, {
                offset: 1,
                color: '#C13E79'
            }])],
            tooltip: {//点击后显示格式
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {//图例
                orient: 'horizontal',
                //left: 'right',
                bottom: '10%',
                data: ['AAA', 'AA+', 'AA', 'AA-', 'A+', 'A', 'A-', 'BB']
            },
            label: {//图上文字
                show: true,
                position: 'outside',
                rotate: '30',
                lineHeight: 10,
                formatter: [//设置具体文字样式
                    '{a|{b}}',
                    '{c};{d}%'
                ].join('\n'),
                //formatter: '{b}\n{c};{d}%',
                rich: {
                    a: {
                        color: '#000000',
                        fontWeight: 'bold',
                        fontSize: 11,
                        lineHeight: 20,
                        align: 'center'
                    }
                },
                fontSize: 10,
                color: '#000000',
                fontWeight: 'bold',
                //borderColor: '#AEAEAE',
                //borderWidth: '1',
                padding: 3
            },
            itemStyle: {//图案样式
                //borderColor:"#eee",
                //borderWidth:3,
                shadowColor: '#EDEDED',
                shadowOffsetX: 1.5,
                shadowOffsetY: 1.5,
                shadowBlur: 10
            },
            tooltip: {
                confine: true//防止点击标签超出边界
            },
            series: [
                {
                    name: '等级',
                    type: 'pie',
                    radius: radius + '%',
                    center: ['50%', '40%'],
                    minAngle: 30,
                    avoidLabelOverlap: true,   //是否启用防止标签重叠策略
                    data: [
                        {value: 11.09, name: 'AAA'},
                        {value: 8.23, name: 'AA+'},
                        {value: 24.89, name: 'AA'},
                        {value: 6.29, name: 'AA-'},
                        {value: 4.58, name: 'A+'},
                        {value: 7.19, name: 'A'},
                        {value: 0.05, name: 'A-'},
                        {value: 1.58, name: 'BB'}
                    ],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }

    //公司贷款期限结构分析
    companyLoanYear();

    function companyLoanYear() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('companyLoanYear');
        var radius = 35;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 35;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '公司贷款期限结构分析',
                x: 'center'
            },
            color: [new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                offset: 0,
                color: '#EE7C00'
            }, {
                offset: 1,
                color: '#FED018'
            }]), new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                offset: 0,
                color: '#019605'
            }, {
                offset: 1,
                color: '#1ACB01'
            }]), new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                offset: 0,
                color: '#8B0100'
            }, {
                offset: 1,
                color: '#F2040A'
            }])],
            tooltip: {//点击后显示格式
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {//图例
                orient: 'horizontal',
                //left: 'right',
                bottom: '20%',
                data: ['短期贷款', '中期贷款', '长期贷款']
            },
            label: {//图上文字
                show: true,
                position: 'outside',
                rotate: '30',
                lineHeight: 15,
                formatter: '贷款余额（亿元）\n{c}；{d}%',
                fontSize: 10,
                color: '#000000',
                fontWeight: 'bold',
                //borderColor: '#AEAEAE',
                //borderWidth: '1',
                padding: 3
            },
            itemStyle: {//图案样式
                //borderColor:"#eee",
                //borderWidth:3,
                shadowColor: '#EDEDED',
                shadowOffsetX: 1.5,
                shadowOffsetY: 1.5,
                shadowBlur: 10
            },
            tooltip: {
                confine: true
            },
            series: [
                {
                    name: '期限',
                    type: 'pie',
                    radius: radius + '%',
                    center: ['50%', '40%'],
                    avoidLabelOverlap: true,   //是否启用防止标签重叠策略
                    data: [
                        {value: 38.83, name: '短期贷款'},
                        {value: 38.01, name: '中期贷款'},
                        {value: 3.34, name: '长期贷款'}
                    ],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //发卡量分析   四柱状图
    publishCard();

    function publishCard() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('publishCard');
        var rotate;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                rotate = 20;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '发卡量分析',
                x: 'center'
            },
            color: ['#1E264A'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                show: true,
                top: 70,
                backgroundColor: '#DDE0F3',
                borderColor: '#000000',
                left: '0%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: ['2010-12-31', '2011-03-31'],
                    axisTick: {
                        alignWithLabel: true
                    },
                    axisLabel: {
                        'interval': 0,
                        //'rotate': rotate//文字旋转
                    },
                    //max:20 //关键第一句
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    //splitNumber: 11,//分割段数
                    splitArea: {//间隔区域样式
                        'show': true,
                        areaStyle: {
                            color: ['rgba(250,250,250)', 'rgba(240,240,240)']
                        }
                    }
                }
            ],
            series: [//系列列表
                {
                    name: '普卡',
                    type: 'bar',
                    barWidth: 25,//柱图宽度
                    //barCategoryGap: '10', //每簇之间的间距
                    //barGaps: '2', //簇内每个柱之间的距离
                    //barGap:'20%',//柱图间距
                    label: {//柱子上的文子块
                        show: true,
                        position: 'top',
                        //rotate  :-30,
                        backgroundColor: '#ffffff',
                        shadowColor: '#AC9C80',//阴影颜色
                        borderColor: '#E0E0E0',//边框颜色
                        //borderWidth:'1',//边框宽度
                        shadowOffsetX: '3',//阴影偏移
                        shadowOffsetY: '3',
                        shadowBlur: '3',//阴影长度
                        padding: [4, 4, 4, 4],
                        fontSize: 10
                    },
                    data: [290, 15168],
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                            offset: 0,
                            color: '#FCDE01'
                        }, {
                            offset: 1,
                            color: '#EC7606'
                        }]),
                        //borderColor:'#BE914C',//描边颜色
                        shadowColor: '#AC9C80',//阴影颜色
                        shadowOffsetX: '3'//阴影水平方向偏移
                    }
                },
                {
                    name: '金卡',
                    type: 'bar',
                    barWidth: 25,//柱图宽度
                    //barCategoryGap: '10', //每簇之间的间距
                    //barGaps: '200', //簇内每个柱之间的距离
                    label: {//柱子上的文子块
                        show: true,
                        position: 'top',
                        //rotate  :-30,
                        backgroundColor: '#ffffff',
                        shadowColor: '#AC9C80',//阴影颜色
                        borderColor: '#E0E0E0',//边框颜色
                        //borderWidth:'1',//边框宽度
                        shadowOffsetX: '3',//阴影偏移
                        shadowOffsetY: '3',
                        shadowBlur: '3',//阴影长度
                        padding: [4, 4, 4, 4],
                        fontSize: 10
                    },
                    data: [1370, 5409],
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                            offset: 0,
                            color: '#00DD03'
                        }, {
                            offset: 1,
                            color: '#009E05'
                        }]),
                        //borderColor:'#BE914C',//描边颜色
                        shadowColor: '#AC9C80',//阴影颜色
                        shadowOffsetX: '3'//阴影水平方向偏移
                    }
                },
                {
                    name: '白金卡',
                    type: 'bar',
                    barWidth: 25,//柱图宽度
                    //barCategoryGap: '10', //每簇之间的间距
                    //barGaps: '200', //簇内每个柱之间的距离
                    //barCategoryGap: '50',
                    label: {//柱子上的文子块
                        show: true,
                        position: 'top',
                        //rotate  :-30,
                        backgroundColor: '#ffffff',
                        shadowColor: '#AC9C80',//阴影颜色
                        borderColor: '#E0E0E0',//边框颜色
                        //borderWidth:'1',//边框宽度
                        shadowOffsetX: '3',//阴影偏移
                        shadowOffsetY: '3',
                        shadowBlur: '3',//阴影长度
                        padding: [4, 4, 4, 4],
                        fontSize: 10
                    },
                    data: [247, 1678],
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                            offset: 0,
                            color: '#FB010C'
                        }, {
                            offset: 1,
                            color: '#850000'
                        }]),
                        //borderColor:'#BE914C',//描边颜色
                        shadowColor: '#AC9C80',//阴影颜色
                        shadowOffsetX: '3'//阴影水平方向偏移
                    }
                },
                {
                    name: '合计',
                    type: 'bar',
                    barWidth: 25,//柱图宽度
                    //barCategoryGap: '10', //每簇之间的间距
                    //barGaps: '200', //簇内每个柱之间的距离
                    label: {//柱子上的文子块
                        show: true,
                        position: 'top',
                        //rotate  :-30,
                        backgroundColor: '#ffffff',
                        shadowColor: '#AC9C80',//阴影颜色
                        borderColor: '#E0E0E0',//边框颜色
                        //borderWidth:'1',//边框宽度
                        shadowOffsetX: '3',//阴影偏移
                        shadowOffsetY: '3',
                        shadowBlur: '3',//阴影长度
                        padding: [4, 4, 4, 4],
                        fontSize: 10
                    },
                    data: [1907, 22255],
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                            offset: 0,
                            color: '#D116F9'
                        }, {
                            offset: 1,
                            color: '#7F0E94'
                        }]),
                        //borderColor:'#BE914C',//描边颜色
                        shadowColor: '#AC9C80',//阴影颜色
                        shadowOffsetX: '3'//阴影水平方向偏移
                    }
                }
            ],
            legend: {//图例组件
                data: ['普卡', '金卡', '白金卡', '合计'],
                type: 'plain',
                orient: 'horizontal',
                top: '30'
            }
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //卡存款余额分析   四柱状图
    saveCard();

    function saveCard() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('saveCard');
        var rotate;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                rotate = 20;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '卡存款余额分析',
                x: 'center'
            },
            //color: ['#1E264A'],
            backgroundColor: {//背景色 径向渐变
                type: 'linear',
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [{
                    offset: 0, color: '#E3D4AD' // 0% 处的颜色
                }, {
                    offset: 1, color: '#ffffff' // 100% 处的颜色
                }],
                global: false // 缺省为 false
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                show: true,
                top: 70,
                //backgroundColor: '#DDE0F3',
                borderColor: '#000000',
                left: '0%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: ['2010-12-31', '2011-03-31'],
                    axisTick: {
                        alignWithLabel: true
                    },
                    axisLabel: {
                        'interval': 0,
                        //'rotate': rotate//文字旋转
                    },
                    //max:20 //关键第一句
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    //splitNumber: 11,//分割段数
                    splitArea: {//间隔区域样式
                        'show': false
                    }
                }
            ],
            series: [//系列列表
                {
                    name: '普卡',
                    type: 'bar',
                    barWidth: 25,//柱图宽度
                    //barCategoryGap: '10', //每簇之间的间距
                    //barGaps: '2', //簇内每个柱之间的距离
                    //barGap:'20%',//柱图间距
                    label: {//柱子上的文子块
                        show: true,
                        position: 'top',
                        //rotate  :-30,
                        //backgroundColor: '#ffffff',
                        shadowColor: '#AC9C80',//阴影颜色
                        borderColor: '#E0E0E0',//边框颜色
                        //borderWidth:'1',//边框宽度
                        shadowOffsetX: '3',//阴影偏移
                        shadowOffsetY: '3',
                        shadowBlur: '3',//阴影长度
                        padding: [4, 4, 4, 4],
                        fontSize: 10
                    },
                    data: [0.15, 1.29],
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                            offset: 0,
                            color: '#0A8FD0'
                        }, {
                            offset: 1,
                            color: '#007AC8'
                        }]),
                        //borderColor:'#BE914C',//描边颜色
                        shadowColor: '#AC9C80',//阴影颜色
                        shadowOffsetX: '3'//阴影水平方向偏移
                    }
                },
                {
                    name: '金卡',
                    type: 'bar',
                    barWidth: 25,//柱图宽度
                    //barCategoryGap: '10', //每簇之间的间距
                    //barGaps: '200', //簇内每个柱之间的距离
                    label: {//柱子上的文子块
                        show: true,
                        position: 'top',
                        //rotate  :-30,
                        //backgroundColor: '#ffffff',
                        shadowColor: '#AC9C80',//阴影颜色
                        borderColor: '#E0E0E0',//边框颜色
                        //borderWidth:'1',//边框宽度
                        shadowOffsetX: '3',//阴影偏移
                        shadowOffsetY: '3',
                        shadowBlur: '3',//阴影长度
                        padding: [4, 4, 4, 4],
                        fontSize: 10
                    },
                    data: [0.82, 3.05],
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                            offset: 0,
                            color: '#DFAA00'
                        }, {
                            offset: 1,
                            color: '#BD9E00'
                        }]),
                        //borderColor:'#BE914C',//描边颜色
                        shadowColor: '#AC9C80',//阴影颜色
                        shadowOffsetX: '3'//阴影水平方向偏移
                    }
                },
                {
                    name: '白金卡',
                    type: 'bar',
                    barWidth: 25,//柱图宽度
                    //barCategoryGap: '10', //每簇之间的间距
                    //barGaps: '200', //簇内每个柱之间的距离
                    //barCategoryGap: '50',
                    label: {//柱子上的文子块
                        show: true,
                        position: 'top',
                        //rotate  :-30,
                        //backgroundColor: '#ffffff',
                        shadowColor: '#AC9C80',//阴影颜色
                        borderColor: '#E0E0E0',//边框颜色
                        //borderWidth:'1',//边框宽度
                        shadowOffsetX: '3',//阴影偏移
                        shadowOffsetY: '3',
                        shadowBlur: '3',//阴影长度
                        padding: [4, 4, 4, 4],
                        fontSize: 10
                    },
                    data: [1.64, 6.38],
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                            offset: 0,
                            color: '#03DC09'
                        }, {
                            offset: 1,
                            color: '#00BE0C'
                        }]),
                        //borderColor:'#BE914C',//描边颜色
                        shadowColor: '#AC9C80',//阴影颜色
                        shadowOffsetX: '3'//阴影水平方向偏移
                    }
                },
                {
                    name: '合计',
                    type: 'bar',
                    barWidth: 25,//柱图宽度
                    //barCategoryGap: '10', //每簇之间的间距
                    //barGaps: '200', //簇内每个柱之间的距离
                    label: {//柱子上的文子块
                        show: true,
                        position: 'top',
                        //rotate  :-30,
                        //backgroundColor: '#ffffff',
                        shadowColor: '#AC9C80',//阴影颜色
                        borderColor: '#E0E0E0',//边框颜色
                        //borderWidth:'1',//边框宽度
                        shadowOffsetX: '3',//阴影偏移
                        shadowOffsetY: '3',
                        shadowBlur: '3',//阴影长度
                        padding: [4, 4, 4, 4],
                        fontSize: 10
                    },
                    data: [2.61, 10.72],
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                            offset: 0,
                            color: '#027CBB'
                        }, {
                            offset: 1,
                            color: '#005CA1'
                        }]),
                        //borderColor:'#BE914C',//描边颜色
                        shadowColor: '#AC9C80',//阴影颜色
                        shadowOffsetX: '3'//阴影水平方向偏移
                    }
                }
            ],
            legend: {//图例组件
                data: ['普卡', '金卡', '白金卡', '合计'],
                type: 'plain',
                orient: 'horizontal',
                top: '30'
            }
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //业务使用情况分析   环形图  双环
    businessUse();

    function businessUse() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('businessUse');
        var radius = 30, radiusOut = 70;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 25;
                radiusOut = 60;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '业务使用情况分析',
                x: 'center',
                top: '30'
            },
            color: [new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                offset: 0,
                color: '#E5780D'
            }, {
                offset: 1,
                color: '#EFAA0D'
            }]),
                new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                    offset: 0,
                    color: '#098D04'
                }, {
                    offset: 1,
                    color: '#01AA03'
                }]),
                new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                    offset: 0,
                    color: '#9F0301'
                }, {
                    offset: 1,
                    color: '#B70700'
                }]),
                new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                    offset: 0,
                    color: '#AA0FD4'
                }, {
                    offset: 1,
                    color: '#AF27C4'
                }]),
                new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                    offset: 0,
                    color: '#279DE7'
                }, {
                    offset: 1,
                    color: '#2595C1'
                }]),
                new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                    offset: 0,
                    color: '#D2581C'
                }, {
                    offset: 1,
                    color: '#D36A3E'
                }]),
                new echarts.graphic.LinearGradient(0, 0, 0, 1, [{//渐变色
                    offset: 0,
                    color: '#78B020'
                }, {
                    offset: 1,
                    color: '#64A70F'
                }])],
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
                //type: 'scroll',
                orient: 'horizontal',
                bottom: 0,
                data: ['本行转账', '跨行转账', '手机费缴纳', '电费缴纳', '水费缴纳', '煤气费缴纳', '投资理财']
            },
            series: [
                {
                    name: '交易金额（万元）',
                    type: 'pie',
                    radius: [radius + '%', radiusOut + '%'],
                    //minAngle: 5,           　　 //最小的扇区角度（0 ~ 360），用于防止某个值过小导致扇区太小影响交互
                    avoidLabelOverlap: true,   //是否启用防止标签重叠策略
                    label: {//图上文字
                        show: true,
                        position: 'outside',
                        fontSize: 15,
                        formatter: '{c}',
                        color: '#000000',
                        fontWeight: 'bold',
                        // borderColor:'#AEAEAE',
                        // borderWidth:'1',
                        //padding:4
                    },
                    labelLine: {
                        normal: {
                            show: true
                        }
                    },
                    data: [
                        {value: 847, name: '本行转账'},
                        {value: 97, name: '跨行转账'},
                        {value: 85, name: '手机费缴纳'},
                        {value: 22, name: '电费缴纳'},
                        {value: 19, name: '水费缴纳'},
                        {value: 20, name: '煤气费缴纳'},
                        {value: 543, name: '投资理财'}
                    ],
                    itemStyle: {
                        borderColor: '#ffffff',//描边颜色
                        borderWidth: '2',
                        // shadowColor: '#AC9C80',//阴影颜色
                        // shadowOffsetX: '3'//阴影水平方向偏移
                    }
                },
                {
                    name: '使用次数（次）',
                    type: 'pie',
                    radius: [radius - 5 + '%', radiusOut - 20 + '%'],
                    //minAngle: 5,           　　 //最小的扇区角度（0 ~ 360），用于防止某个值过小导致扇区太小影响交互
                    avoidLabelOverlap: true,   //是否启用防止标签重叠策略
                    label: {//图上文字
                        show: true,
                        position: 'inside',
                        fontSize: 10,
                        formatter: '{c}',
                        color: '#000000',
                        fontWeight: 'bold',
                        // borderColor:'#AEAEAE',
                        // borderWidth:'1',
                        //padding:4
                    },
                    labelLine: {
                        normal: {
                            show: true,   //引导线显示
                            length: 15,
                            lineStyle: {
                                color: '#000',
                                width: 10
                            }
                        },

                    },
                    data: [
                        {value: 2878, name: '本行转账'},
                        {value: 487, name: '跨行转账'},
                        {value: 3874, name: '手机费缴纳'},
                        {value: 984, name: '电费缴纳'},
                        {value: 422, name: '水费缴纳'},
                        {value: 183, name: '煤气费缴纳'},
                        {value: 673, name: '投资理财'}
                    ],
                    itemStyle: {
                        borderColor: '#ffffff',//描边颜色
                        borderWidth: '2',
                        //shadowColor: '#AC9C80',//阴影颜色
                        //shadowOffsetX: '3'//阴影水平方向偏移
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }

}

//资产负债管理
//tipsTwo();
function tipsTwo() {
    //资产构成  环形图
    propertyCompose();

    function propertyCompose() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('propertyCompose');
        var radius = 30, radiusOut = 70;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 45;
                radiusOut = 60;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '资产构成',
                x: 'center',
                top: '20',
                textStyle: {
                    color: '#fff'
                },
            },
            color: ['#FFA474', '#3DA0FF', '#54F095', '#D9F056', '#F59369'],
            tooltip: {
                trigger: 'item',
                formatter: "{b}: {c} (亿元)",
                color: '#ffffff',
                confine: true//防止点击标签超出边界
            },
            grid: {
                top: '30',
                bottom: '0%'
            },
            legend: {
                icon: "circle",//  这个字段控制形状  类型包括 circle，rect ，roundRect，triangle，diamond，pin，arrow，none
                orient: 'horizontal',
                bottom: 30,
                textStyle: {
                    color: '#fff'
                },
                data: ['各项贷款', '同业及投资资产', '其他资产', '现金及存放中央银行款项', '理财资产']
            },
            series: [
                {
                    name: '资产构成',
                    type: 'pie',
                    radius: [radius + '%', radiusOut + '%'],
                    //minAngle: 5,           　　 //最小的扇区角度（0 ~ 360），用于防止某个值过小导致扇区太小影响交互
                    avoidLabelOverlap: true,   //是否启用防止标签重叠策略
                    label: {
                        normal: {
                            show: false,
                            color: '#fff',
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data: [
                        {value: 1452649.7300, name: '各项贷款'},
                        {value: 968432.6600, name: '同业及投资资产'},
                        {value: 484216.3300, name: '其他资产'},
                        {value: 200108.1600, name: '现金及存放中央银行款项'},
                        {value: 48421.6300, name: '理财资产'}
                    ],
                    itemStyle: {
                        //borderColor:'#BE914C',//描边颜色
                        //shadowColor: '#AC9C80',//阴影颜色
                        //shadowOffsetX: '3'//阴影水平方向偏移
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //负债构成  环形图
    liabilityStructure();

    function liabilityStructure() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('liabilityStructure');
        var radius = 30, radiusOut = 70;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 45;
                radiusOut = 60;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '负债构成',
                x: 'center',
                top: '20',
                textStyle: {
                    color: '#fff'
                },
            },
            color: ['#54F095', '#FDB82C', '#2DB6F4', '#7DC856'],
            tooltip: {
                trigger: 'item',
                formatter: "{b}: {c} (亿元)",
                color: '#ffffff',
                confine: true//防止点击标签超出边界
            },
            legend: {
                icon: "circle",//  这个字段控制形状  类型包括 circle，rect ，roundRect，triangle，diamond，pin，arrow，none
                orient: 'horizontal',
                bottom: 30,
                textStyle: {
                    color: '#fff'
                },
                data: ['各项存款', '同业负债', '其他负债', '向中央银行借款']
            },
            series: [
                {
                    name: '负债构成',
                    type: 'pie',
                    radius: [radius + '%', radiusOut + '%'],
                    //minAngle: 5,           　　 //最小的扇区角度（0 ~ 360），用于防止某个值过小导致扇区太小影响交互
                    avoidLabelOverlap: true,   //是否启用防止标签重叠策略
                    label: {
                        normal: {
                            show: false,
                            color: '#fff',
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data: [
                        {value: 1371937.1800, name: '各项存款'},
                        {value: 457312.3333, name: '同业负债'},
                        {value: 228656.1666, name: '其他负债'},
                        {value: 22888.6166, name: '向中央银行借款'}
                    ],
                    itemStyle: {
                        //borderColor:'#BE914C',//描边颜色
                        //shadowColor: '#AC9C80',//阴影颜色
                        //shadowOffsetX: '3'//阴影水平方向偏移
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //各项贷款  折线图
    totalLoans();

    function totalLoans() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('totalLoans');
        var radius = 30, radiusOut = 70;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 45;
                radiusOut = 60;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '各项贷款',
                top: '20',
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip: {
                trigger: 'axis',
                formatter: '{b} <br /> {a0}: {c0}亿元<br />{a1}: {c1}亿元 <br /> {a2}: {c2}亿元<br /> {a3}: {c3}亿元<br /> {a4}: {c4}亿元<br /> {a5}: {c5}亿元<br /> {a6}: {c6}亿元',
                color: '#ffffff',
                confine: true//防止点击标签超出边界
            },
            legend: {
                top: '15%',
                data: ['个人条线', '公司条线', '国际业务部', '金融市场部', '小企业条线', '银行卡部', '网络金融部'],
                textStyle: {
                    color: '#fff'
                }
            },
            grid: {
                top: '40%',
                left: '3%',
                right: '4%',
                bottom: '10%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                data: ['201807', '201808', '201809', '201810', '201811', '201810', '201901', '201902', '201903', '201904', '201905', '201906']
            },
            yAxis: {
                type: 'value',
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                }
            },
            series: [
                {
                    name: '个人条线',
                    type: 'line',
                    data: [120, 132, 101, 134, 90, 230, 210, 132, 101, 134, 90, 230]
                },
                {
                    name: '公司条线',
                    type: 'line',
                    data: [220, 182, 191, 234, 290, 330, 310, 134, 90, 230, 210, 132]
                },
                {
                    name: '国际业务部',
                    type: 'line',
                    data: [150, 232, 201, 154, 190, 330, 410, 232, 201, 154, 190, 330]
                },
                {
                    name: '金融市场部',
                    type: 'line',
                    data: [320, 332, 301, 334, 390, 330, 320, 191, 234, 290, 330, 310]
                },
                {
                    name: '小企业条线',
                    type: 'line',
                    data: [820, 932, 901, 934, 1290, 1330, 1320, 932, 901, 934, 1290, 1330]
                },
                {
                    name: '银行卡部',
                    type: 'line',
                    data: [820, 932, 901, 934, 1290, 1330, 1320, 232, 201, 154, 190, 330]
                },
                {
                    name: '网络金融部',
                    type: 'line',
                    data: [820, 932, 901, 934, 1290, 1330, 1320, 934, 1290, 1330, 1320, 232, 201]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //各项存款  折线图
    totalSaves();

    function totalSaves() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('totalSaves');
        var radius = 30, radiusOut = 70;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 45;
                radiusOut = 60;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '各项存款',
                top: '20',
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip: {
                trigger: 'axis',
                formatter: '{b} <br /> {a0}: {c0}亿元<br />{a1}: {c1}亿元 <br /> {a2}: {c2}亿元',
                color: '#ffffff',
                confine: true//防止点击标签超出边界
            },
            legend: {
                top: '15%',
                data: ['储蓄存款', '对公存款', '结构性存款'],
                textStyle: {
                    color: '#fff'
                }
            },
            grid: {
                top: '30%',
                left: '3%',
                right: '10%',
                bottom: '13%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                data: ['201812', '201901']
            },
            yAxis: {
                type: 'value',
                name: '亿元',
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                }
            },
            series: [

                {
                    name: '结构性存款',
                    type: 'line',
                    lineStyle: {
                        color: '#EDDA47'
                    },
                    data: [50, 30]
                },
                {
                    name: '对公存款',
                    type: 'line',
                    lineStyle: {
                        color: '#FFC02A'
                    },
                    data: [580, 510]
                },
                {
                    name: '储蓄存款',
                    type: 'line',
                    lineStyle: {
                        color: '#E68664'
                    },
                    data: [1190, 1160]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //存贷利差   堆叠柱状图
    saveDifferLoan();

    function saveDifferLoan() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('saveDifferLoan');
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '存贷利差',
                top: '20',
                textStyle: {
                    color: '#fff'
                }
            },
            //backgroundColor: "#ffffff",
            color: ['#01D2D1', '#FAD345', '#3DA0FF', '#FFA474', '#A67FFE'],
            legend: {
                top: '15%',
                textStyle: {
                    color: '#fff'
                },
                data: ['示例1', '示例2', '示例3', '示例4', '示例5']
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            grid: {
                top: '35%',
                left: '10%',
                bottom: '10%',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                data: ['第1组', '第2组', '第3组', '第4组', '第5组'],
                axisTick: {
                    alignWithLabel: true
                },
                axisLabel: {
                    interval: 0
                },
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },

            }],
            yAxis: [{
                type: 'value',
                name: '亿',
                axisLabel: {
                    formatter: '{value}'
                }, axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                }
            }],
            series: [{
                name: '示例1',
                type: 'bar',
                stack: '总量',//配置相同的值 可以堆叠放置数据
                barWidth: '60%',
                data: [55.83, 73.93, 39.33, 75.68, 72.78]
            }, {
                name: '示例2',
                type: 'bar',
                stack: '总量',
                barWidth: '60%',
                data: [44.17, 26.07, 60.67, 24.32, 27.22]
            }, {
                name: '示例3',
                type: 'bar',
                stack: '总量',
                barWidth: '60%',
                data: [44.17, 26.07, 60.67, 24.32, 27.22]
            }, {
                name: '示例4',
                type: 'bar',
                stack: '总量',
                barWidth: '60%',
                data: [44.17, 26.07, 60.67, 24.32, 27.22]
            }, {
                name: '示例5',
                type: 'bar',
                stack: '总量',
                barWidth: '60%',
                data: [44.17, 26.07, 60.67, 24.32, 27.22]
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
    }
}


//存款完成情况
//tipsThree();
function tipsThree() {
    //各项存款完成情况  雷达图
    totalDeposits();

    function totalDeposits() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('totalDeposits');
        var radius = 30, radiusOut = 70;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 45;
                radiusOut = 60;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);


        //指定图表的配置项和数据
        var option = {
            title: {
                text: '各项存款完成情况',
                x: 'center',
                top: '30',
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip: {
                confine: true//防止点击标签超出边界
            },
            color: ['#EBEB23', '#14CC0A'],
            legend: {
                top: '15%',
                textStyle: {
                    color: '#fff'
                },
                data: ['当日余额', '计划目标']
            },
            radar: {
                // shape: 'circle',
                name: {
                    textStyle: {
                        color: '#fff',
                        //backgroundColor: '#999',
                        //borderRadius: 3,
                        //padding: [3, 5]
                    }
                },
                center: ['50%', '50%'], // 位置
                radius: 70, //大小
                indicator: [//顺时针排列
                    {name: '包头分行', max: 50000},
                    {name: '二连浩特分行', max: 50000},
                    {name: '哈尔滨分行', max: 50000},
                    {name: '呼和浩特分行', max: 50000},
                    {name: '呼伦贝尔分行', max: 50000},
                    {name: '满洲里支行', max: 50000},
                    {name: '内蒙古银行营业部', max: 50000},
                    {name: '通辽分行', max: 50000},
                    {name: '网络金融部', max: 50000},
                    {name: '乌海分行', max: 50000},
                    {name: '乌兰察布分行', max: 50000},
                    {name: '锡林郭勒盟分行', max: 50000},
                    {name: '兴安盟分行', max: 50000},
                    {name: '总行机关', max: 50000}
                ]
            },
            series: [{
                name: '当日余额 vs 计划目标',
                type: 'radar',
                // areaStyle: {normal: {}},
                data: [
                    {
                        value: [43000, 44000, 45000, 41000, 48000, 46000, 45000, 43000, 43000, 45000, 47000, 44000, 46000, 45000],
                        name: '当日余额'
                    },
                    {
                        value: [46000, 47000, 44000, 43000, 46000, 48000, 46000, 46000, 46000, 47000, 46000, 46000, 43000, 0],
                        name: '计划目标'
                    }
                ]
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
    }


    //对公存款完成情况  雷达图
    publicDeposits();

    function publicDeposits() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('publicDeposits');
        var radius = 30, radiusOut = 70;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 45;
                radiusOut = 60;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);


        //指定图表的配置项和数据
        var option = {
            title: {
                text: '对公存款完成情况',
                x: 'center',
                top: '30',
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip: {
                confine: true//防止点击标签超出边界
            },
            color: ['#EBEB23', '#14CC0A'],
            legend: {
                top: '15%',
                textStyle: {
                    color: '#fff'
                },
                data: ['当日余额', '计划目标']
            },
            radar: {
                // shape: 'circle',
                name: {
                    textStyle: {
                        color: '#fff',
                        //backgroundColor: '#999',
                        //borderRadius: 3,
                        //padding: [3, 5]
                    }
                },
                center: ['50%', '50%'], // 位置
                radius: 70, //大小
                indicator: [//顺时针排列
                    {name: '包头分行', max: 50000},
                    {name: '二连浩特分行', max: 50000},
                    {name: '哈尔滨分行', max: 50000},
                    {name: '呼和浩特分行', max: 50000},
                    {name: '呼伦贝尔分行', max: 50000},
                    {name: '满洲里支行', max: 50000},
                    {name: '内蒙古银行营业部', max: 50000},
                    {name: '通辽分行', max: 50000},
                    {name: '网络金融部', max: 50000},
                    {name: '乌海分行', max: 50000},
                    {name: '乌兰察布分行', max: 50000},
                    {name: '锡林郭勒盟分行', max: 50000},
                    {name: '兴安盟分行', max: 50000},
                    {name: '总行机关', max: 50000}
                ]
            },
            series: [{
                name: '当日余额 vs 计划目标',
                type: 'radar',
                // areaStyle: {normal: {}},
                data: [
                    {
                        value: [43000, 44000, 46000, 45000, 43000, 43000, 45000, 47000, 44000, 45000, 41000, 48000, 46000, 45000],
                        name: '当日余额'
                    },
                    {
                        value: [46000, 47000, 44000, 46000, 46000, 46000, 47000, 46000, 46000, 43000, 46000, 48000, 43000, 0],
                        name: '计划目标'
                    }
                ]
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
    }


    //储蓄存款完成情况  雷达图
    savingsDeposits();

    function savingsDeposits() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('savingsDeposits');
        var radius = 30, radiusOut = 70;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 45;
                radiusOut = 60;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);


        //指定图表的配置项和数据
        var option = {
            title: {
                text: '储蓄存款完成情况',
                x: 'center',
                top: '30',
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip: {
                confine: true//防止点击标签超出边界
            },
            color: ['#EBEB23', '#14CC0A'],
            legend: {
                top: '15%',
                textStyle: {
                    color: '#fff'
                },
                data: ['当日余额', '计划目标']
            },
            radar: {
                // shape: 'circle',
                name: {
                    textStyle: {
                        color: '#fff',
                        //backgroundColor: '#999',
                        //borderRadius: 3,
                        //padding: [3, 5]
                    }
                },
                center: ['50%', '50%'], // 位置
                radius: 70, //大小
                indicator: [//顺时针排列
                    {name: '包头分行', max: 50000},
                    {name: '二连浩特分行', max: 50000},
                    {name: '哈尔滨分行', max: 50000},
                    {name: '呼和浩特分行', max: 50000},
                    {name: '呼伦贝尔分行', max: 50000},
                    {name: '满洲里支行', max: 50000},
                    {name: '内蒙古银行营业部', max: 50000},
                    {name: '通辽分行', max: 50000},
                    {name: '网络金融部', max: 50000},
                    {name: '乌海分行', max: 50000},
                    {name: '乌兰察布分行', max: 50000},
                    {name: '锡林郭勒盟分行', max: 50000},
                    {name: '兴安盟分行', max: 50000},
                    {name: '总行机关', max: 50000}
                ]
            },
            series: [{
                name: '当日余额 vs 计划目标',
                type: 'radar',
                // areaStyle: {normal: {}},
                data: [
                    {
                        value: [43000, 44000, 43000, 43000, 45000, 47000, 44000, 46000, 45000, 45000, 41000, 48000, 46000, 45000],
                        name: '当日余额'
                    },
                    {
                        value: [46000, 47000, 44000, 46000, 46000, 43000, 46000, 46000, 46000, 46000, 47000, 48000, 43000, 0],
                        name: '计划目标'
                    }
                ]
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
    }
}

//收入支出
//tipsFour();
function tipsFour() {

    //收入构成
    incomeConsitute();

    function incomeConsitute() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('incomeConsitute');
        var radius = 35;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 35;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '收入构成',
                x: 'center',
                top: '30',
                textStyle: {
                    color: '#fff'
                }
            },
            color: ['#617BCD', '#FDB82C', '#2DB6F4', '#7DC856', '#3ABBB3'],
            tooltip: {//点击后显示格式
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} 万元({d}%)"
            },
            legend: {//图例
                orient: 'vertical',
                left: 'left',
                top: '20%',
                textStyle: {
                    color: '#fff'
                },
                data: ['其他营业收入', '投资收入', '手续费收入', '金融机构往来利息收入', '利息收入']
            },
            itemStyle: {//图案样式

            },
            series: [
                {
                    name: '收入构成',
                    type: 'pie',
                    radius: radius + '%',
                    center: ['55%', '60%'],
                    label: {
                        show: false
                    },
                    data: [
                        {value: 876, name: '其他营业收入'},
                        {value: 3457, name: '投资收入'},
                        {value: 754, name: '手续费收入'},
                        {value: 4678, name: '金融机构往来利息收入'},
                        {value: 6875, name: '利息收入'}
                    ],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //收入构成趋势  折线图
    incomeCurrent();

    function incomeCurrent() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('incomeCurrent');
        var radius = 30, radiusOut = 70;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 45;
                radiusOut = 60;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '收入构成趋势',
                top: '20',
                textStyle: {
                    color: '#fff'
                }
            },
            color: ['#E7540A', '#F215F2', '#47D425', '#EBCA1B', '#39CABC'],
            tooltip: {
                trigger: 'axis',
                formatter: '{b} <br /> {a0}: {c0}亿元<br />{a1}: {c1}亿元 <br /> {a2}: {c2}亿元<br /> {a3}: {c3}亿元<br /> {a4}: {c4}亿元',
                color: '#ffffff',
                confine: true//防止点击标签超出边界
            },
            legend: {
                top: '15%',
                data: ['金融机构往来利息收入', '手续费收入', '利息收入', '投资收入', '其他营业收入'],
                textStyle: {
                    color: '#fff'
                }
            },
            grid: {
                top: '40%',
                left: '3%',
                right: '10%',
                bottom: '10%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                data: ['201808', '201809', '201810', '201811', '201812', '201901', '201902', '201903', '201904', '201905']
            },
            yAxis: {
                type: 'value',
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                }
            },
            series: [
                {
                    name: '金融机构往来利息收入',
                    type: 'line',
                    smooth: 1,  //true 为平滑曲线，false为直线
                    data: [25, 33, 35, 45, 18, 53, 41, 30, 28, 10]
                },
                {
                    name: '手续费收入',
                    type: 'line',
                    smooth: 1,  //true 为平滑曲线，false为直线
                    data: [6, 0, 8, 0, 1, 6, 7, 2, 2, 3]
                },
                {
                    name: '利息收入',
                    type: 'line',
                    smooth: 1,  //true 为平滑曲线，false为直线
                    data: [26, 33, 32, 46, 11, 57, 43, 38, 22, 11]
                },
                {
                    name: '投资收入',
                    type: 'line',
                    smooth: 1,  //true 为平滑曲线，false为直线
                    data: [26, 30, 38, 50, 21, 56, 47, 24, 26, 13]
                },
                {
                    name: '其他营业收入',
                    type: 'line',
                    smooth: 1,  //true 为平滑曲线，false为直线
                    data: [2, 3, 5, 5, 8, 3, 1, 0, 8, 0]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //支出构成   分离型饼图
    outCurrent();

    function outCurrent() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('outCurrent');
        var radius = 35;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 35;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '支出构成',
                x: 'center',
                top: '30',
                textStyle: {
                    color: '#fff'
                }
            },
            color: ['#617BCD', '#FFCA30', '#2DB6F4', '#7DC856', '#3ABBB3', '#39ADD0', '#617BCD'],
            tooltip: {//点击后显示格式
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {//图例
                orient: 'vertical',
                left: 'left',
                top: '20%',
                textStyle: {
                    color: '#fff'
                },
                data: ['利息支出', '金融机构往来利息支出', '手续费支出', '提取减值准备', '营业费用', '其他营业支出', '提取准备']
            },
            tooltip: {
                confine: true
            },
            series: [
                {
                    name: '支出构成',
                    type: 'pie',
                    radius: radius + '%',
                    center: ['55%', '60%'],
                    label: {
                        show: false
                    },
                    data: [//设置为选中状态 图案就会分离
                        {value: 2755, name: '利息支出'},
                        {value: 6983, name: '金融机构往来利息支出'},//分离显示
                        {value: 374, name: '手续费支出'},
                        {value: 856, name: '提取减值准备'},
                        {value: 1567, name: '营业费用'},
                        {value: 267, name: '其他其他营业支出'},
                        {value: 158, name: '提取准备'}
                    ],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);


        //设置默认选中高亮部分
        myChart.dispatchAction({type: 'highlight', seriesIndex: 0, dataIndex: 1});//(高亮，坐标系索引，数据索引)
        // 当鼠标点击饼图时 判断并显示正常区块
        myChart.on('click', function (e) {
            console.log(e)
            myChart.dispatchAction({type: 'downplay', seriesIndex: 0});//取消全部高亮
            myChart.dispatchAction({type: 'highlight', seriesIndex: 0, dataIndex: e.dataIndex});//选中的索引 高亮
        });
        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //支出构成趋势  折线图
    outConsituteCurrent();

    function outConsituteCurrent() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('outConsituteCurrent');
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '支出构成趋势',
                top: '20',
                textStyle: {
                    color: '#fff'
                }
            },
            color: ['#E7540A', '#F215F2', '#47D425', '#EBCA1B', '#39CABC'],
            tooltip: {
                trigger: 'axis',
                formatter: '{b} <br /> {a0}: {c0}亿元<br />{a1}: {c1}亿元 <br /> {a2}: {c2}亿元<br /> {a3}: {c3}亿元<br /> {a4}: {c4}亿元',
                color: '#ffffff',
                confine: true//防止点击标签超出边界
            },
            legend: {
                top: '15%',
                data: ['手续费支出', '营业费用', '利息支出', '金融机构往来利息支出', '提取准备', '提取减值准备'],
                textStyle: {
                    color: '#fff'
                }
            },
            grid: {
                top: '40%',
                left: '3%',
                right: '10%',
                bottom: '10%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                data: ['201808', '201809', '201810', '201811', '201812', '201901', '201902', '201903', '201904', '201905']
            },
            yAxis: {
                type: 'value',
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                }
            },
            series: [
                {
                    name: '手续费支出',
                    type: 'line',
                    smooth: 1,  //true 为平滑曲线，false为直线
                    data: [25, 33, 35, 45, 18, 53, 41, 30, 28, 10]
                },
                {
                    name: '营业费用',
                    type: 'line',
                    smooth: 1,  //true 为平滑曲线，false为直线
                    data: [6, 0, 8, 0, 1, 6, 7, 2, 2, 3]
                },
                {
                    name: '利息支出',
                    type: 'line',
                    smooth: 1,  //true 为平滑曲线，false为直线
                    data: [26, 33, 32, 46, 11, 57, 43, 38, 22, 11]
                },
                {
                    name: '金融机构往来利息支出',
                    type: 'line',
                    smooth: 1,  //true 为平滑曲线，false为直线
                    data: [26, 30, 38, 50, 21, 56, 47, 24, 26, 13]
                },
                {
                    name: '提取准备',
                    type: 'line',
                    smooth: 1,  //true 为平滑曲线，false为直线
                    data: [2, 3, 5, 5, 8, -1, 1, 0, 8, 0]
                },
                {
                    name: '提取减值准备',
                    type: 'line',
                    smooth: 1,  //true 为平滑曲线，false为直线
                    data: [4, 2, 2, 6, 4, 1, 7, 3, 4, 1]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //净利润变化趋势  折线图
    netProfitsChanges();

    function netProfitsChanges() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('netProfitsChanges');
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '净利润变化趋势',
                top: '20',
                textStyle: {
                    color: '#fff'
                }
            },
            color: ['#E7540A', '#F215F2', '#47D425', '#EBCA1B', '#39CABC'],
            tooltip: {
                trigger: 'axis',
                formatter: '{b} <br /> {a0}: {c0}亿元<br />{a1}: {c1}亿元 <br /> {a2}: {c2}亿元<br /> {a3}: {c3}亿元<br /> {a4}: {c4}亿元',
                color: '#ffffff',
                confine: true//防止点击标签超出边界
            },
            legend: {
                top: '15%',
                data: ['包头分行', '乌兰察布分行', '哈尔滨分行', '满洲里分行', '兴安盟分行', '呼和浩特分行', '锡林郭勒分行', '二连浩特分行', '总行营业部', '通辽分行', '乌海分行', '呼伦贝尔分行', '总行机关', '小企业金融服务中心'],
                textStyle: {
                    color: '#fff'
                }
            },
            grid: {
                top: '45%',
                left: '3%',
                right: '10%',
                bottom: '10%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                data: ['201808', '201809', '201810', '201811', '201812', '201901', '201902', '201903', '201904', '201905']
            },
            yAxis: {
                type: 'value',
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                }
            },
            series: [
                {
                    name: '包头分行',
                    type: 'line',
                    smooth: 0.8,  //true 为平滑曲线，false为直线
                    data: [1, 1.5, 0.5, 1, 1.8, 2, 0.6, 0.3, 0.5, 0.9]
                },
                {
                    name: '乌兰察布分行',
                    type: 'line',
                    smooth: 0.8,  //true 为平滑曲线，false为直线
                    data: [1.9, 1.4, 1.4, 1.9, 1.8, 5.9, 1.6, 1.3, 1.5, 1.9]
                },
                {
                    name: '哈尔滨分行',
                    type: 'line',
                    smooth: 0.8,  //true 为平滑曲线，false为直线
                    data: [0.9, 1.4, 0.4, 0.6, 1.6, 2.3, 0.8, 0.5, 0.7, 1]
                },
                {
                    name: '满洲里分行',
                    type: 'line',
                    smooth: 0.8,  //true 为平滑曲线，false为直线
                    data: [1, 1.5, 0.5, 1, 1.8, 2, 0.6, 0.3, 0.5, 0.9]
                },
                {
                    name: '兴安盟分行',
                    type: 'line',
                    smooth: 0.8,  //true 为平滑曲线，false为直线
                    data: [1, 1.5, 0.5, 0.6, 2.3, 1, 1.8, 2, 0.5, 0.9]
                },
                {
                    name: '呼和浩特分行',
                    type: 'line',
                    smooth: 0.8,  //true 为平滑曲线，false为直线
                    data: [-1, -1.5, -0.5, -1, -1.8, -2, -0.6, -0.3, -0.5, -0.9]
                },
                {
                    name: '锡林郭勒分行',
                    type: 'line',
                    smooth: 0.8,  //true 为平滑曲线，false为直线
                    data: [1, 1.5, 0.5, 1, 1.8, 2, 0.6, 0.3, 0.5, 0.9]
                },
                {
                    name: '二连浩特分行',
                    type: 'line',
                    smooth: 0.8,  //true 为平滑曲线，false为直线
                    data: [1, 1.5, 0.5, 1, 1.8, 2, 0.6, 0.3, 0.5, 0.9]
                },
                {
                    name: '总行营业部',
                    type: 'line',
                    smooth: 0.8,  //true 为平滑曲线，false为直线
                    data: [-1.9, -1.4, -1.4, -1.9, -1.8, -5.9, -1.6, -1.3, -1.5, -1.9]
                },
                {
                    name: '通辽分行',
                    type: 'line',
                    smooth: 0.8,  //true 为平滑曲线，false为直线
                    data: [-1, -1.5, -0.5, -1, -1.8, -2, -0.6, -0.3, -0.5, -0.9]
                },
                {
                    name: '乌海分行',
                    type: 'line',
                    smooth: 0.8,  //true 为平滑曲线，false为直线
                    data: [1, 1.5, 0.5, 1, 1.8, 2, 0.6, 0.3, 0.5, 0.9]
                },
                {
                    name: '呼伦贝尔分行',
                    type: 'line',
                    smooth: 0.8,  //true 为平滑曲线，false为直线
                    data: [1, 1.5, 0.5, 1, 1.8, 2, 0.6, 0.3, 0.5, 0.9]
                },
                {
                    name: '总行机关',
                    type: 'line',
                    smooth: 0.8,  //true 为平滑曲线，false为直线
                    data: [1, 1.5, 0.5, 1, 1.8, 2, 0.6, 0.3, 0.5, 0.9]
                },
                {
                    name: '小企业金融服务中心',
                    type: 'line',
                    smooth: 0.8,  //true 为平滑曲线，false为直线
                    data: [1, 1.5, 0.5, 1, 1.8, 2, 0.6, 0.3, 0.5, 0.9]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //重点指标  仪表盘
    importantTarget();

    function importantTarget() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('importantTarget');
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '重点指标',
                top: '20',
                textStyle: {
                    color: '#fff'
                }
            },
            color: ['#ffffff'],
            tooltip: {
                formatter: "{a} <br/>{b} : {c}%"
            },
            toolbox: {
                feature: {
                    restore: {},
                    saveAsImage: {}
                }
            },
            series: [
                {
                    name: '业务指标',
                    type: 'gauge',
                    center: ['50%', '55%'],    // 默认全局居中
                    radius: '80%',
                    title: {
                        color: '#ffffff'
                    },
                    axisLine: {
                        lineStyle: {
                            color: [[1, '#617BCE']]
                        }
                    },
                    splitLine: {//分隔线样式
                        show: false
                    },
                    axisTick: {//刻度样式
                        show: false
                    },
                    axisLabel: {//刻度标签
                        show: false
                    },
                    pointer: {//指针属性
                        show: false
                    },
                    detail: {formatter: '{value}%', color: '#ffffff'},//仪表盘详情
                    data: [
                        {
                            value: 1152.77,
                            name: '不良贷款率',
                            color: '#ffffff'
                        }
                    ]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    importantTargetTwo();

    function importantTargetTwo() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('importantTargetTwo');
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {

            color: ['#ffffff'],
            tooltip: {
                formatter: "{a} <br/>{b} : {c}%"
            },
            toolbox: {
                feature: {
                    restore: {},
                    saveAsImage: {}
                }
            },
            series: [
                {
                    name: '业务指标',
                    type: 'gauge',
                    center: ['50%', '55%'],    // 默认全局居中
                    radius: '80%',
                    title: {
                        color: '#ffffff'
                    },
                    axisLine: {
                        lineStyle: {
                            color: [[1, '#FDB82C']]
                        }
                    },
                    splitLine: {//分隔线样式
                        show: false
                    },
                    axisTick: {//刻度样式
                        show: false
                    },
                    axisLabel: {//刻度标签
                        show: false
                    },
                    pointer: {//指针属性
                        show: false
                    },
                    detail: {formatter: '{value}%', color: '#ffffff'},//仪表盘详情
                    data: [
                        {
                            value: 81.98,
                            name: '拨贷比(动态拨备覆盖率)',
                            color: '#ffffff'
                        }
                    ]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    importantTargetThree();

    function importantTargetThree() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('importantTargetThree');
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {

            color: ['#ffffff'],
            tooltip: {
                formatter: "{a} <br/>{b} : {c}%"
            },
            toolbox: {
                feature: {
                    restore: {},
                    saveAsImage: {}
                }
            },
            series: [
                {
                    name: '业务指标',
                    type: 'gauge',
                    center: ['50%', '55%'],    // 默认全局居中
                    radius: '80%',
                    title: {
                        color: '#ffffff'
                    },
                    axisLine: {
                        lineStyle: {
                            color: [[1, '#2DB6F4']]
                        }
                    },
                    splitLine: {//分隔线样式
                        show: false
                    },
                    axisTick: {//刻度样式
                        show: false
                    },
                    axisLabel: {//刻度标签
                        show: false
                    },
                    pointer: {//指针属性
                        show: false
                    },
                    detail: {formatter: '{value}%', color: '#ffffff'},//仪表盘详情
                    data: [
                        {
                            value: 2241465513.01,
                            name: '存贷款比例(贷存比)',
                            color: '#ffffff'
                        }
                    ]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //各分行贷款完成情况  雷达图
    loanFinishSituation();

    function loanFinishSituation() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('loanFinishSituation');
        var radius = 30, radiusOut = 70;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 45;
                radiusOut = 60;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);


        //指定图表的配置项和数据
        var option = {
            title: {
                text: '各分行贷款完成情况',
                x: 'center',
                top: '30',
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip: {
                confine: true//防止点击标签超出边界
            },
            color: ['#EBEB23', '#14CC0A'],
            legend: {
                top: '15%',
                textStyle: {
                    color: '#fff'
                },
                data: ['当日余额', '计划目标']
            },
            radar: {
                // shape: 'circle',
                name: {
                    textStyle: {
                        color: '#fff',
                        //backgroundColor: '#999',
                        //borderRadius: 3,
                        //padding: [3, 5]
                    }
                },
                center: ['50%', '50%'], // 位置
                radius: 70, //大小
                indicator: [//顺时针排列
                    {name: '包头分行', max: 50000},
                    {name: '二连浩特分行', max: 50000},
                    {name: '哈尔滨分行', max: 50000},
                    {name: '呼和浩特分行', max: 50000},
                    {name: '呼伦贝尔分行', max: 50000},
                    {name: '满洲里支行', max: 50000},
                    {name: '内蒙古银行营业部', max: 50000},
                    {name: '通辽分行', max: 50000},
                    {name: '网络金融部', max: 50000},
                    {name: '乌海分行', max: 50000},
                    {name: '乌兰察布分行', max: 50000},
                    {name: '锡林郭勒盟分行', max: 50000},
                    {name: '兴安盟分行', max: 50000},
                    {name: '总行机关', max: 50000}
                ]
            },
            series: [{
                name: '当日余额 vs 计划目标',
                type: 'radar',
                // areaStyle: {normal: {}},
                data: [
                    {
                        value: [43000, 44000, 45000, 41000, 48000, 36000, 45000, 43000, 43000, 45000, 47000, 44000, 46000, 45000],
                        name: '当日余额'
                    },
                    {
                        value: [4600, 4700, 4400, 4300, 4600, 48000, 4600, 4600, 4600, 4700, 4600, 4600, 4300, 4800],
                        name: '计划目标'
                    }
                ]
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
    }


    //各项存款余额、日均余额变化趋势  折线图
    savingsAndChange();

    function savingsAndChange() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('savingsAndChange');
        var radius = 30, radiusOut = 70;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 45;
                radiusOut = 60;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '各项存款余额、日均余额变化趋势',
                top: '20',
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip: {
                trigger: 'axis',
                formatter: '{b} <br /> {a0}: {c0}亿元<br />{a1}: {c1}亿元',
                color: '#ffffff',
                confine: true//防止点击标签超出边界
            },
            legend: {
                top: '15%',
                data: ['当日余额', '日均余额'],
                textStyle: {
                    color: '#fff'
                }
            },
            grid: {
                top: '30%',
                left: '3%',
                right: '10%',
                bottom: '13%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                data: ['201807', '201808', '201809', '201810', '201811', '201812', '201901', '201902', '201903', '201904', '201905', '201906']
            },
            yAxis: {
                type: 'value',
                name: '亿',
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                }
            },
            series: [
                {
                    name: '当日余额',
                    type: 'line',
                    lineStyle: {
                        color: '#B74C0B'
                    },
                    data: [1500, 1450, 1400, 1550, 1600, 1700, 1600, 1590, 1580, 1600, 1620, 1750, 1600]
                },
                {
                    name: '日均余额',
                    type: 'line',
                    lineStyle: {
                        color: '#0BD75E'
                    },
                    data: [1500, 1500, 1500, 1500, 1500, 1500, 1500, 1700, 1680, 1680, 1680, 1680, 1680]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //各项贷款余额、日均余额变化趋势  折线图
    LoansAndChange();

    function LoansAndChange() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('LoansAndChange');
        var radius = 30, radiusOut = 70;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 45;
                radiusOut = 60;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '各项贷款余额、日均余额变化趋势',
                top: '20',
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip: {
                trigger: 'axis',
                formatter: '{b} <br /> {a0}: {c0}亿元<br />{a1}: {c1}亿元',
                color: '#ffffff',
                confine: true//防止点击标签超出边界
            },
            legend: {
                top: '15%',
                data: ['当日余额', '日均余额'],
                textStyle: {
                    color: '#fff'
                }
            },
            grid: {
                top: '30%',
                left: '3%',
                right: '10%',
                bottom: '13%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                data: ['201807', '201808', '201809', '201810', '201811', '201812', '201901', '201902', '201903', '201904', '201905', '201906']
            },
            yAxis: {
                type: 'value',
                name: '亿',
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                }
            },
            series: [
                {
                    name: '当日余额',
                    type: 'line',
                    lineStyle: {
                        color: '#0A41F5',
                        width: 3
                    },
                    data: [1100, 1150, 1160, 1170, 1160, 1210, 1220, 1250, 1260, 1280, 1280, 0]
                },
                {
                    name: '日均余额',
                    type: 'line',
                    lineStyle: {
                        color: '#F8F707',
                        width: 3
                    },
                    data: [1100, 1170, 1200, 1200, 1210, 1220, 1250, 1260, 1280, 1280, 1280, 1280]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //储蓄存款余额、日均余额变化趋势  堆叠区域图
    savingsDepositAndChange();

    function savingsDepositAndChange() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('savingsDepositAndChange');
        var radius = 30, radiusOut = 70;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 45;
                radiusOut = 60;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '储蓄存款余额、日均余额变化趋势',
                top: '20',
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip: {
                trigger: 'axis',
                formatter: '{b} <br /> {a0}: {c0}亿元<br />{a1}: {c1}亿元',
                color: '#ffffff',
                confine: true//防止点击标签超出边界
            },
            legend: {
                top: '15%',
                data: ['当日余额', '日均余额'],
                textStyle: {
                    color: '#fff'
                }
            },
            grid: {
                top: '30%',
                left: '3%',
                right: '10%',
                bottom: '13%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                data: ['201807', '201808', '201809', '201810', '201811', '201812', '201901', '201902', '201903', '201904', '201905', '201906']
            },
            yAxis: {
                type: 'value',
                name: '亿',
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                }
            },
            series: [
                {
                    name: '当日余额',
                    type: 'line',
                    lineStyle: {
                        color: '#AC6FCB'
                    },
                    areaStyle: {
                        color: '#AC6FCB'
                    },//堆叠区域
                    data: [500, 510, 520, 530, 550, 480, 490, 560, 570, 580, 590, 595]
                },
                {
                    name: '日均余额',
                    type: 'line',
                    lineStyle: {
                        color: '#49B246'
                    },
                    areaStyle: {
                        color: '#49B246'
                    },//堆叠区域
                    data: [400, 410, 410, 410, 410, 420, 470, 480, 490, 500, 510, 520]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //对公存款余额、日均余额变化趋势  堆叠区域图
    companySavingsAndChange();

    function companySavingsAndChange() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('companySavingsAndChange');
        var radius = 30, radiusOut = 70;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 45;
                radiusOut = 60;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '对公存款余额、日均余额变化趋势',
                top: '20',
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip: {
                trigger: 'axis',
                formatter: '{b} <br /> {a0}: {c0}亿元<br />{a1}: {c1}亿元',
                color: '#ffffff',
                confine: true//防止点击标签超出边界
            },
            legend: {
                top: '15%',
                data: ['当日余额', '日均余额'],
                textStyle: {
                    color: '#fff'
                }
            },
            grid: {
                top: '30%',
                left: '3%',
                right: '10%',
                bottom: '13%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                data: ['201807', '201808', '201809', '201810', '201811', '201812', '201901', '201902', '201903', '201904', '201905', '201906']
            },
            yAxis: {
                type: 'value',
                name: '亿',
                max: '1400',
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                }
            },
            series: [

                {
                    name: '当日余额',
                    type: 'line',
                    markLine: {
                        symbol: 'none',//去掉箭头
                        data: [//标线位置
                            {
                                name: '',
                                yAxis: 1300
                            },
                        ],
                        label: {//不显示 标线标签
                            show: false
                        },
                        lineStyle: {//标线颜色
                            color: '#ffffff'
                        }
                    },
                    lineStyle: {
                        color: '#3189E0'
                    },
                    areaStyle: {
                        color: '#3189E0'
                    },//堆叠区域
                    data: [910, 900, 850, 980, 1000, 1160, 1120, 1100, 1120, 1130, 1170, 1175]
                },
                {
                    name: '日均余额',
                    type: 'line',
                    lineStyle: {
                        color: '#F0F711'
                    },
                    areaStyle: {
                        color: '#F0F711'
                    },//堆叠区域
                    data: [1100, 1050, 1000, 1000, 1000, 1000, 1150, 1125, 1120, 1200, 1200, 1125]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }
}


//tipsFive();
function tipsFive() {
    //地区排名
    areaRanking();

    function areaRanking() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('areaRanking');
        var radius = 35;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 35;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '地区排名',
                x: 'center',
                top: '30',
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            legend: {//图例
                orient: 'vertical',
                left: 'right',
                top: '5%',
                textStyle: {
                    color: '#fff'
                },
                selectedMode: 'single',
                data: ['按时点值', '按日均值'],
                selected: {
                    '按日均值': false
                }
            },
            grid: {
                left: '10%',
                right: '15%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                splitLine: {
                    show: false
                },
                minInterval: 1,
                boundaryGap: [0, 0.1],
                axisLabel: {
                    interval: 0,
                }
            },
            yAxis: {
                type: 'category',
                data: ["地区H", "地区D", "地区I", "地区B", "地区G", "地区E", "地区F", "地区J", "地区C"],
                splitLine: {show: false},
                offset: 50,
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                nameTextStyle: {
                    fontSize: 15
                },
                splitNumber: 10,
                axisLabel: {
                    show: true,
                    color: '#fff',
                    align: 'left',
                    borderRadius: 3,
                    formatter: function (value, index) {//y轴标签格式（值，索引）
                        return index + ':' + value
                    },
                    interval: 0//标签设置为全部显示
                }
            },
            series: [
                {
                    name: '按时点值',
                    type: 'bar',
                    data: ["26000", "23000", "19000", "19000", "19000", "15000", "14000", "13000", "12000"],
                    barWidth: 14,
                    barGap: 50,//10
                    smooth: true,
                    itemStyle: {
                        emphasis: {
                            barBorderRadius: 7
                        },
                        normal: {
                            barBorderRadius: 7,
                            color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                                    offset: 0, color: '#007EFF'
                                },
                                    {
                                        offset: 1, color: '#00F8FE'
                                    }]
                            )
                        }
                    }
                },
                {
                    name: '按日均值',
                    type: 'bar',
                    data: ["230000", "200000", "190000", "180000", "160000", "150000", "140000", "130000", "120000"],
                    barWidth: 14,
                    barGap: 50,//10
                    smooth: true,
                    itemStyle: {
                        emphasis: {
                            barBorderRadius: 7
                        },
                        normal: {
                            barBorderRadius: 7,
                            color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                                    offset: 0, color: '#007EFF'
                                },
                                    {
                                        offset: 1, color: '#00F8FE'
                                    }]
                            )
                        }
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //理财产品增速  堆叠区域图  区域缩放
    financeProductsSpeed();

    function financeProductsSpeed() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('financeProductsSpeed');
        var radius = 30, radiusOut = 70;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 45;
                radiusOut = 60;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '理财产品增速',
                top: '20',
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip: {
                trigger: 'axis',
                formatter: '{b} <br /> {a0}: {c0}亿元',
                color: '#ffffff',
                confine: true//防止点击标签超出边界
            },
            grid: {
                top: '30%',
                left: '3%',
                right: '15%',
                bottom: '13%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                data: ['201807', '201808', '201809', '201810', '201811', '201812']
            },
            yAxis: {
                type: 'value',
                name: '亿',
                max: '30000',
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                }
            },
            dataZoom: {//区域缩放组件
                type: 'slider',//类型 滑动条型
                xAxisIndex: 0,//指定控制的x轴索引
                filterMode: 'filter',//数据缩放规则 当前数据窗口外的数据，被 过滤掉。即 会 影响其他轴的数据范围。每个数据项，只要有一个维度在数据窗口外，整个数据项就会被过滤掉。
                backgroundColor: {//线性渐变
                    type: 'linear',
                    x: 0,
                    y: 0,
                    x2: 1,
                    y2: 0,
                    colorStops: [{
                        offset: 0, color: '#00FCFF' // 0% 处的颜色
                    }, {
                        offset: 1, color: '#0068FF' // 100% 处的颜色
                    }],
                    global: false // 缺省为 false
                },
                borderColor: '#0065FF',
                handleStyle: {
                    color: '#0065FF'
                }
            },
            series: [
                {
                    name: '金额(万)',
                    type: 'line',
                    smooth: 0.5,  //true 为平滑曲线，false为直线
                    markLine: {
                        //symbol: 'none',//去掉箭头
                        data: [//标线位置
                            {
                                name: '',
                                yAxis: 10000
                            },
                        ],
                        lineStyle: {//标线颜色
                            color: '#FF4547'
                        }
                    },
                    lineStyle: {
                        color: '#3189E0'
                    },
                    areaStyle: {
                        color: '#3189E0'
                    },//堆叠区域
                    data: [14000, 19000, 16000, 13500, 15000, 2500]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //日均余额波动情况  折线图  区域缩放
    dailyAverage();

    function dailyAverage() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('dailyAverage');
        var radius = 30, radiusOut = 70;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 45;
                radiusOut = 60;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '日均余额波动情况',
                top: '20',
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip: {
                trigger: 'axis',
                formatter: '{b} <br /> {a0}: {c0}亿元',
                color: '#ffffff',
                confine: true//防止点击标签超出边界
            },
            color: '#0065FF',
            legend: {
                show: true,
                top: '15%',
                data: ['日均余额'],
                textStyle: {
                    color: '#fff'
                }
            },
            grid: {
                top: '30%',
                left: '3%',
                right: '15%',
                bottom: '13%',
                containLabel: true
            },
            toolbox: {},
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                data: ['20190131', '20190228', '20190331', '20190430', '20190531', '20190630']
            },
            yAxis: {
                type: 'value',
                name: '亿',
                max: '21000',
                splitNumber: 7,
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                splitLine: {
                    lineStyle: {
                        type: 'dashed'
                    }
                }
            },
            dataZoom: {//区域缩放组件
                type: 'slider',//类型 滑动条型
                xAxisIndex: 0,//指定控制的x轴索引
                filterMode: 'filter',//数据缩放规则 当前数据窗口外的数据，被 过滤掉。即 会 影响其他轴的数据范围。每个数据项，只要有一个维度在数据窗口外，整个数据项就会被过滤掉。
                backgroundColor: {//线性渐变
                    type: 'linear',
                    x: 0,
                    y: 0,
                    x2: 1,
                    y2: 0,
                    colorStops: [{
                        offset: 0, color: '#00FCFF' // 0% 处的颜色
                    }, {
                        offset: 1, color: '#0068FF' // 100% 处的颜色
                    }],
                    global: false // 缺省为 false
                },
                borderColor: '#0065FF',
                handleStyle: {
                    color: '#0065FF'
                }
            },
            series: [
                {
                    name: '日均余额',
                    type: 'line',
                    smooth: 0.5,  //true 为平滑曲线，false为直线
                    lineStyle: {
                        color: '#0065FF'
                    },
                    data: [14000, 18100, 16500, 12500, 15100, 2900]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }

    //任务完成情况  堆叠区域图 
    missionFinishSituation();

    function missionFinishSituation() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('missionFinishSituation');
        var radius = 30, radiusOut = 70;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 45;
                radiusOut = 60;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '任务完成情况',
                top: '20',
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip: {
                trigger: 'axis',
                formatter: '{b} <br /> {a0}: {c0}万元 <br /> {a1}: {c1}万元',
                color: '#ffffff',
                confine: true//防止点击标签超出边界
            },
            color: ['#0065FF', '#FF4547'],
            legend: {
                show: true,
                top: '15%',
                data: ['目标', '日均余额'],
                textStyle: {
                    color: '#fff'
                }
            },
            grid: {
                top: '30%',
                left: '13%',
                right: '15%',
                bottom: '13%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                axisLabel: {
                    rotate: 30
                },
                data: ['定制化定期存款', '定期存款', '结构性存款', '大额存单', '省联社智能存款', '活期存款']
            },
            yAxis: {
                type: 'value',
                name: '万元',
                max: '40000',
                min: '-10000',
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                }
            },
            series: [
                {
                    name: '目标',
                    type: 'line',
                    smooth: 0.5,  //true 为平滑曲线，false为直线
                    lineStyle: {
                        color: '#0065FF'
                    },
                    areaStyle: {//堆叠区域
                        color: '#0065FF'
                    },
                    data: [4000, 7000, 26000, 7000, 7000, 19500]
                },
                {
                    name: '日均余额',
                    type: 'line',
                    smooth: 0.5,  //true 为平滑曲线，false为直线
                    lineStyle: {
                        color: '#FF4547'
                    },
                    areaStyle: {//堆叠区域
                        color: '#FF4547'
                    },
                    data: [4000, 7000, 18500, 7000, 7000, 19000]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //理财产品日均  交错正负轴 
    financeProductsDailyAverage();

    function financeProductsDailyAverage() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('financeProductsDailyAverage');
        var radius = 30, radiusOut = 70;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 45;
                radiusOut = 60;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var labelRight = {
            normal: {
                position: 'right'
            }
        };
        option = {
            title: {
                text: '理财产品日均',
                top: '20',
                textStyle: {
                    color: '#fff'
                }
            },
            color: [{//线性渐变   炫彩
                type: 'linear',
                x: 0,
                y: 0,
                x2: 1,
                y2: 0,
                colorStops: [{
                    offset: 0, color: '#006BFF' // 0% 处的颜色
                }, {
                    offset: 0.5, color: '#00FCFF' // 100% 处的颜色
                }],
                global: false // 缺省为 false
            }, {//线性渐变   炫彩
                type: 'linear',
                x: 0,
                y: 0,
                x2: 1,
                y2: 0,
                colorStops: [{
                    offset: 0, color: '#FF4A47' // 0% 处的颜色
                }, {
                    offset: 0.5, color: '#FFC54A' // 100% 处的颜色
                }, {
                    offset: 1, color: '#33FAA2' // 100% 处的颜色
                }],
                global: false // 缺省为 false
            }],
            legend: {
                show: true,
                top: '12%',
                data: ['保本理财', '非保本理财'],
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                top: 80,
                left: '30%',
                bottom: 30
            },
            xAxis: {
                type: 'value',
                position: 'bottom',
                splitLine: {lineStyle: {type: 'dashed'}},
                axisLabel: {
                    show: true,
                    color: '#fff'
                }
            },
            yAxis: {
                type: 'category',
                axisLine: {show: false},
                //axisLabel: { show: false },
                axisTick: {show: false},
                splitLine: {show: false},
                data: ['通辽分行', '兴安盟分行', '乌兰察布分行', '包头分行', '呼伦贝尔分行', '二连浩特分行', '乌海分行', '锡林郭勒分行', '哈尔滨分行', '呼和浩特分行'],
                axisLabel: {
                    show: true,
                    color: '#fff'
                }
            },
            series: [
                {
                    name: '保本理财',
                    type: 'bar',
                    barWidth: '20%',
                    data: [200, 300, 400, 500, 600, 700, 800, 900, 1100, 1600]
                },
                {
                    name: '非保本理财',
                    type: 'bar',
                    barWidth: '20%',
                    data: [-200, -300, -400, -500, -600, -700, -800, -1300, -1500, -2600]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


}


//tipsSix();
function tipsSix() {
    //产品占比  环形图
    productMix();

    function productMix() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('productMix');
        var radius = 30, radiusOut = 70;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 45;
                radiusOut = 60;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '产品占比',
                x: 'center',
                top: '20',
                textStyle: {
                    color: '#fff'
                },
            },
            color: ['#0066FF', '#FF4547', '#FFD34A', '#28FBA6', '#00B2FF', '#1FCF9F'],
            tooltip: {
                trigger: 'item',
                formatter: "{b}: {c} (亿元)",
                color: '#ffffff',
                confine: true//防止点击标签超出边界
            },
            grid: {
                top: '30',
                bottom: '0%'
            },
            legend: {
                icon: "circle",//  这个字段控制形状  类型包括 circle，rect ，roundRect，triangle，diamond，pin，arrow，none
                orient: 'horizontal',
                bottom: 30,
                textStyle: {
                    color: '#fff'
                },
                data: ['定制化定期存款', '结构性存款', '定期存款', '大额存单', '省联社智能存款', '活期存款']
            },
            series: [
                {
                    name: '资产构成',
                    type: 'pie',
                    radius: [radius + '%', radiusOut + '%'],
                    //minAngle: 5,           　　 //最小的扇区角度（0 ~ 360），用于防止某个值过小导致扇区太小影响交互
                    avoidLabelOverlap: true,   //是否启用防止标签重叠策略
                    label: {
                        normal: {
                            show: false,
                            color: '#fff',
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data: [
                        {value: 1000, name: '定制化定期存款'},
                        {value: 2500, name: '结构性存款'},
                        {value: 1800, name: '定期存款'},
                        {value: 1800, name: '大额存单'},
                        {value: 1900, name: '省联社智能存款'},
                        {value: 2500, name: '活期存款'}
                    ],
                    itemStyle: {
                        //borderColor:'#BE914C',//描边颜色
                        //shadowColor: '#AC9C80',//阴影颜色
                        //shadowOffsetX: '3'//阴影水平方向偏移
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }


    //产品变动  折线图  区域缩放
    productChange();

    function productChange() {
        //图表
        //初始化echarts实例
        var worldMapContainer = document.getElementById('productChange');
        var radius = 30, radiusOut = 70;
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function () {
            worldMapContainer.style.width = (window.innerWidth - 20) + 'px';
            worldMapContainer.style.height = ((window.innerWidth - 20)) / 2;//(window.innerHeight/2-20) + 'px';

            if (window.innerWidth < 400) {
                radius = 45;
                radiusOut = 60;
            }
        };
        //设置容器高宽
        resizeWorldMapContainer();
        //初始化echarts实例
        var myChart = echarts.init(worldMapContainer);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '产品变动',
                top: '20',
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip: {
                trigger: 'axis',
                formatter: '{b} <br /> {a0}: {c0}亿元',
                color: '#ffffff',
                confine: true//防止点击标签超出边界
            },
            color: ['#0066FF', '#FF4547', '#FFD34A', '#28FBA6', '#00B3FF', '#1FCF9F'],
            legend: {
                show: true,
                top: '15%',
                data: ['大额存单', '定期存款', '定制化定期存款', '活期存款', '结构性存款', '省联社智能存款'],
                textStyle: {
                    color: '#fff'
                }
            },
            grid: {
                top: '33%',
                left: '3%',
                right: '15%',
                bottom: '13%',
                containLabel: true
            },
            toolbox: {},
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                data: ['20190131', '20190228', '20190331', '20190430', '20190531', '20190630']
            },
            yAxis: {
                type: 'value',
                //name: '亿',
                max: '5000',
                splitNumber: 7,
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                splitLine: {
                    lineStyle: {
                        type: 'dashed'
                    }
                }
            },
            dataZoom: {//区域缩放组件
                type: 'slider',//类型 滑动条型
                xAxisIndex: 0,//指定控制的x轴索引
                filterMode: 'filter',//数据缩放规则 当前数据窗口外的数据，被 过滤掉。即 会 影响其他轴的数据范围。每个数据项，只要有一个维度在数据窗口外，整个数据项就会被过滤掉。
                backgroundColor: {//线性渐变
                    type: 'linear',
                    x: 0,
                    y: 0,
                    x2: 1,
                    y2: 0,
                    colorStops: [{
                        offset: 0, color: '#00FCFF' // 0% 处的颜色
                    }, {
                        offset: 1, color: '#0068FF' // 100% 处的颜色
                    }],
                    global: false // 缺省为 false
                },
                borderColor: '#0065FF',
                handleStyle: {
                    color: '#0065FF'
                }
            },
            series: [
                {
                    name: '大额存单',
                    type: 'line',
                    smooth: 0.5,  //true 为平滑曲线，false为直线
                    lineStyle: {
                        color: '#0066FF'
                    },
                    data: [1000, 1200, 2500, 1500, 1600, 150]
                },
                {
                    name: '定期存款',
                    type: 'line',
                    smooth: 0.5,  //true 为平滑曲线，false为直线
                    lineStyle: {
                        color: '#FF4547'
                    },
                    data: [1200, 5000, 1500, 1200, 1400, 300]
                },
                {
                    name: '定制化定期存款',
                    type: 'line',
                    smooth: 0.5,  //true 为平滑曲线，false为直线
                    lineStyle: {
                        color: '#FFD34A'
                    },
                    data: [100, 500, 500, 500, 500, 100]
                },
                {
                    name: '活期存款',
                    type: 'line',
                    smooth: 0.5,  //true 为平滑曲线，false为直线
                    lineStyle: {
                        color: '#28FBA6'
                    },
                    data: [1500, 1200, 1300, 1250, 1600, 150]
                },
                {
                    name: '结构性存款',
                    type: 'line',
                    smooth: 0.5,  //true 为平滑曲线，false为直线
                    lineStyle: {
                        color: '#00B3FF'
                    },
                    data: [3200, 3050, 3200, 3100, 3150, 500]
                },
                {
                    name: '省联社智能存款',
                    type: 'line',
                    smooth: 0.5,  //true 为平滑曲线，false为直线
                    lineStyle: {
                        color: '#1FCF9F'
                    },
                    data: [3400, 4600, 3200, 2000, 3200, 500]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //用于使chart自适应高度和宽度
        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer();
            myChart.resize();
        };
    }
};


/*
//事件处理

//点击 首页
$("#homePage").on('click', function () {
    location.href = "../pages/homePage.html";
});

//项目录入页
$("#insertButton").on('click', function () {
    location.href = "../pages/projectInsert.html";
});

//点击项目图表按钮
$(document).on('click', '#goChart', function () {
    location.reload();
});*/
