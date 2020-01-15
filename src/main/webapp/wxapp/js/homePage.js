//首页
console.log("我的项目");


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
    layui.use(['layer'], function () {

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


//listview
//创建MeScroll对象
var listData;//存全局列表数据
var mescroll = new MeScroll("mescroll", {
    down: {
        auto: false, //是否在初始化完毕之后自动执行下拉回调callback; 默认true
        callback: downCallback //下拉刷新的回调
    },
    up: {
        auto: true, //是否在初始化时以上拉加载的方式自动加载第一页数据; 默认false
        isBounce: false, //此处禁止ios回弹,解析(务必认真阅读,特别是最后一点): http://www.mescroll.com/qa.html#q10
        callback: upCallback, //上拉回调,此处可简写; 相当于 callback: function (page) { upCallback(page); }
        toTop: { //配置回到顶部按钮
            src: "../plugin/images/mescroll-totop.png", //默认滚动到1000px显示,可配置offset修改
            //offset : 1000
        }
    }
});


/*下拉刷新的回调 */
function downCallback() {
    //联网加载数据
    getListDataFromNet(0, 1, function (data) {
        //联网成功的回调,隐藏下拉刷新的状态
        mescroll.endSuccess();
        //设置列表数据
        setListData(data, false);
    }, function () {
        //联网失败的回调,隐藏下拉刷新的状态
        mescroll.endErr();
    });
}

/*上拉加载的回调 page = {num:1, size:10}; num:当前页 从1开始, size:每页数据条数 */
function upCallback(page) {
    //联网加载数据
    getListDataFromNet(page.num, page.size, function (curPageData) {
        //联网成功的回调,隐藏下拉刷新和上拉加载的状态;
        //mescroll会根据传的参数,自动判断列表如果无任何数据,则提示空;列表无下一页数据,则提示无更多数据;
        console.log("page.num=" + page.num + ", page.size=" + page.size + ", curPageData.length=" + curPageData.length);

        //方法一(推荐): 后台接口有返回列表的总页数 totalPage
        //mescroll.endByPage(curPageData.length, totalPage); //必传参数(当前页的数据个数, 总页数)

        //方法二(推荐): 后台接口有返回列表的总数据量 totalSize
        //mescroll.endBySize(curPageData.length, totalSize); //必传参数(当前页的数据个数, 总数据量)

        //方法三(推荐): 您有其他方式知道是否有下一页 hasNext
        //mescroll.endSuccess(curPageData.length, hasNext); //必传参数(当前页的数据个数, 是否有下一页true/false)

        //方法四 (不推荐),会存在一个小问题:比如列表共有20条数据,每页加载10条,共2页.如果只根据当前页的数据个数判断,则需翻到第三页才会知道无更多数据,如果传了hasNext,则翻到第二页即可显示无更多数据.
        mescroll.endSuccess(curPageData.length, '21');

        //提示:curPageData.length必传的原因:
        // 1.判断是否有下一页的首要依据: 当传的值小于page.size时,则一定会认为无更多数据.
        // 2.比传入的totalPage, totalSize, hasNext具有更高的判断优先级
        // 3.使配置的noMoreSize生效
        listData = curPageData;//列表数据存全局
        //设置列表数据
        setListData(curPageData, true);
    }, function () {
        //联网失败的回调,隐藏下拉刷新和上拉加载的状态;
        mescroll.endErr();
    });
}

/*设置列表数据*/
function setListData(curPageData, isAppend) {
    console.log("设置列表数据")
    var listDom = document.getElementById("newsList");
    console.log(curPageData);
    for (var i = 0; i < curPageData.length; i++) {
        var newObj = curPageData[i];

        var str = '<div id="value_' + newObj.id + '" name="listDataTips" class="layui-table" lay-skin="row" style="width:auto;padding: 5px;"><div>项目名称：' +
            newObj.projectname + '</div><div>完成进度：90%</div></div>';
        var liDom = document.createElement("li");
        liDom.innerHTML = str;

        // if (isAppend) {
        //     listDom.appendChild(liDom);//加在列表的后面,上拉加载
        // } else {
        //     listDom.insertBefore(liDom, listDom.firstChild);//加在列表的前面,下拉刷新
        // }

        listDom.appendChild(liDom);//加在列表的后面
    }

    //列表项点击事件
    $("[name='listDataTips']").on('click', function (e) {
        console.log(e.currentTarget.id);
        var dataId = e.currentTarget.id.split('_')[1];
        console.log(dataId);
        var detailData;
        for (var i = 0; i < listData.length; i++) {
            if (listData[i].id == dataId) {//找到点击的这条数据
                detailData = listData[i];//存点击的数据
                $("#projectName").html(detailData.projectname);//项目名称
                $("#projectDetails").html(detailData.projectdetail);//项目内容
                break;
            }
        }

        //弹出列表详情框
        layui.use(['layer'], function () {
            layer.open({
                type: 1
                , title: '详情'
                , closeBtn: 2
                , shadeClose: true
                , area: ['300px', '300px']
                , shade: 0.8
                , id: 'layui_list_detail' //设定一个id，防止重复弹出
                , btnAlign: 'c'
                , btn: ['确定']
                , moveType: 1 //拖拽模式，0或者1
                , content: $('#detailPage')
                , end: function (res) {
                    $("#detailPage").css("display", 'none');
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

/*联网加载列表数据
 在您的实际项目中,请参考官方写法: http://www.mescroll.com/api.html#tagUpCallback
 请忽略getListDataFromNet的逻辑,这里仅仅是在本地模拟分页数据,本地演示用
 实际项目以您服务器接口返回的数据为准,无需本地处理分页.
 * */

var downIndex = 0;
function getListDataFromNet(pageNum, pageSize, successCallback, errorCallback) {
    //延时一秒,模拟联网
    setTimeout(function () {
        try {
            var newArr = [];
            if (pageNum == 0) {
                //此处模拟下拉刷新返回的数据
                // downIndex++;
                // var newObj = { title: "【新增新闻" + downIndex + "】 新增新闻的标题", content: "新增新闻的内容" };
                // newArr.push(newObj);
                $("#newsList").html("");
                //调接口获取数据  到时候添加 页数
                $.ajax({
                    //请求方式
                    type: 'get',
                    //请求的媒体类型
                    contentType: "application/json;charset=UTF-8",
                    //请求地址
                    url: '../plugin/data/table.json',
                    dataType: 'text',
                    //数据，json字符串
                    data: {},
                    //请求成功
                    success: function (result) {
                        //console.log(result);
                        var data = eval("(" + result + ")");
                        console.log(data);

                        //ajaxCallBack('success', data);

                        successCallback && successCallback(data);
                    },
                    //请求失败，包含具体的错误信息
                    error: function (e) {
                        console.log(e.status);
                        console.log(e.responseText);
                        ajaxCallBack('error', e.responseText);
                    }
                });
            } else {
                //此处模拟上拉加载返回的数据
                // for (var i = 0; i < pageSize; i++) {
                //     var upIndex = (pageNum - 1) * pageSize + i + 1;
                //     var newObj = { title: "【新闻" + upIndex + "】 标题标题标题标题标题标题", content: "内容内容内容内容内容内容内容内容内容内容" };
                //     newArr.push(newObj);
                // }

                //调接口获取数据
                $.ajax({
                    //请求方式
                    type: 'get',
                    //请求的媒体类型
                    contentType: "application/json;charset=UTF-8",
                    //请求地址
                    url: '../plugin/data/table.json',
                    dataType: 'text',
                    //数据，json字符串
                    data: {},
                    //请求成功
                    success: function (result) {
                        //console.log(result);
                        var data = eval("(" + result + ")");
                        console.log(data);

                        //ajaxCallBack('success', data);

                        successCallback && successCallback(data);
                    },
                    //请求失败，包含具体的错误信息
                    error: function (e) {
                        console.log(e.status);
                        console.log(e.responseText);
                        ajaxCallBack('error', e.responseText);
                    }
                });
            }
            //联网成功的回调
            //successCallback && successCallback(newArr);
        } catch (e) {
            //联网失败的回调
            errorCallback && errorCallback();
        }
    }, 1000)
}




//事件处理

//底部菜单点击事件

$("#insertButton").on('click',function(){
    location.href = "../pages/projectInsert.html";
});