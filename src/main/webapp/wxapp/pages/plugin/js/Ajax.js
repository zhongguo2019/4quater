//公共Ajax请求

function ajaxSendMessage(typeData, sendData, urlData, dataTypeData) {

    var getData = function () {
        if (dataTypeData == 'text') {//获取字符串数据
            //获取数据的 ajax
            console.log("获取数据")
            $.ajax({
                //请求方式
                type: typeData,
                //请求的媒体类型
                contentType: "application/json;charset=UTF-8",
                //请求地址
                url: urlData,
                dataType: 'text',
                //数据，json字符串
                data: {},
                //请求成功
                success: function (result) {
                    //console.log(result);
                    var data = eval("(" + result + ")");
                    //console.log(data);

                    ajaxCallBack('success', data);
                },
                //请求失败，包含具体的错误信息
                error: function (e) {
                    console.log(e.status);
                    console.log(e.responseText);
                    ajaxCallBack('error', e.responseText);
                }
            });
        } else {
            console.log("提交数据")
            //提交数据的 ajax
            $.ajax({
                url: urlData,
                data: {
                    data: eval('(' + sendData + ')')
                },
                type: typeData,
                success: function (result) {
                    //console.log(result);
                    var data = eval("(" + result + ")");
                    //console.log(data);
                    return data;
                    ajaxCallBack('success', data);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR.responseText);
                    ajaxCallBack('error', e.responseText);
                }
            });
        }
    };

    return getData();
};

