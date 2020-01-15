jQuery(function () {
    layui.use(['jquery', 'layer', 'form', 'table', 'layedit', 'laydate'], function () {
        var $ = layui.jquery;
        var layer = layui.layer
            , form = layui.form
            , table = layui.table
            , layedit = layui.layedit
            , laydate = layui.laydate;
        //日期
        laydate.render({
            elem: '#query_start_date'
        });
        laydate.render({
            elem: '#query_end_date'
        });

        //获取当前用户用户userId

        var urlCode = location.href.split("code=")[1].split('&')[0];
        console.log(urlCode);
        var userCode = getcookie("userId");
        //userCode = 'Zhaozulong';
        if (userCode == "") {
            $.ajax({
                url: "http://krmsoft.natapp1.cc/main/wxgetJSSUser",
                data: {
                    data: urlCode
                },
                type: "POST",
                success: function (result) {
                    console.log(result);
                    addcookie("userId", result);
                    showTable();//显示table
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR);
                    console.log(textStatus);
                    console.log(errorThrown);
                }
            });
        }

        function showTable(){
            table.render({
                elem: '#reportviewtable'
                , url: 'http://krmsoft.natapp1.cc/todaywork/doufuTodayWork/wxWorkQuery'
                //, url: 'test.json'
                , method: 'post' //如果无需自定义HTTP类型，可不加该参数
                , contentType: 'application/json'
                , where: {
                    userCode: getcookie("userId")//用户id
                    , username: $("#query_name").val()
                    , startdate: $("#query_start_date").val()
                    , enddate: $("#query_end_date").val()
                }
                , done: function (res, curr, count) {//加载完成事件
                    layer.closeAll('dialog'); //关闭信息框
                    //如果是异步请求数据方式，res即为你接口返回的信息。
                    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                    console.log(res);
                    console.log(curr);          //得到当前页码
                    console.log(count);          //得到数据总量
                }
                , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                //, skin: 'line' //表格风格
                , even: true
                , page: true //是否显示分页
                , loading: true//是否显示加载条
                , limits: [5, 7, 10]
                , limit: 10 //每页默认显示的数量
                , id: 'worklisttable'
                ,parseData: function(res){ //res 即为原始返回的数据
                    console.log(res)

                }
                , cols: [[
                    { field: 'PRODUCT_NAME', title: '项目名称', sort: true }
                    , { field: 'WORK_CONTENTS', title: '工作内容' }
                    , { field: 'REPORT_DATE', title: '日期', sort: true }
                ]]
            });
        }


        setTimeout(function () {
            showTable();//显示table
        }, 500);


        //得到前台用户录入的查询条件
        var formData = new FormData($("#queryWork")[0]);
        var jsonData01 = {};
        formData.forEach((value, key) => jsonData01[key] = value);
        jsonData01.userCode = getcookie("userId");
        var jsonQuery = {};
        jsonQuery = JSON.stringify(jsonData01);
        console.log(jsonQuery);
        var startdate = $('#query_start_date');
        var enddate = $('#query_end_date');
        var username = $('#query_name');


        //查询按钮，调用后台查询方法
        $('#queryBtn').on('click', function () {
            var loading = layer.msg("<em style='color:red'>" + '正在查询数据。。。。。。' + "</em>", { time: 100000, icon: 16 });
            var type = $(this).data('type');

            //执行重载
            table.reload('worklisttable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    startdate: startdate.val(),
                    enddate: enddate.val(),
                    username: username.val()
                }
            }, 'data');

        });

    });

    /*   jQuery("#queryReport").on('click', function () {

           var queryUrl = "http://krmsoft.natapp1.cc/todaywork/doufuTodayWork/wxWorkQuery";

           var formData = new FormData(jQuery("#queryWork")[0]);

           var jsonData01 = {};
           formData.forEach((value, key) => jsonData01[key] = value);
           jsonData01.userCode=getcookie("userId");
           var jsonQuery = {};
            jsonQuery = JSON.stringify(jsonData01);
            console.log(jsonData01);



          var loading = layer.msg("<em style='color:red'>" + '正在查询数据。。。。。。'+ "</em>", {time: 0, icon: 16});
           jQuery.ajax({
               method: "POST",
               url: queryUrl,       //提交表单的地址
               contentType: "application/json;charset-UTF-8",
               data:  jsonQuery,
               dataType: 'json',
               success: function (res) {
                   layer.close(loading);

               },
               error: function () {
                  layer.close(loading);
                 //  layer.msg("日报查询失败！", {icon: 2});
               }
           });

       });*/

})


//存Cookie的值 仅同级文件可用
function addcookie(name, value, expireHours) {
    var exp = new Date();
    exp.setTime(exp.getTime() + expireHours);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}


//存Cookie的值 加了cookie作用域 会产生全局有效的cookie
function ADDcookie(name, value, expireHours) {
    var exp = new Date();
    exp.setTime(exp.getTime() + expireHours);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString() + "; path=/";
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

