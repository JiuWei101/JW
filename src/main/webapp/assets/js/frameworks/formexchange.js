function dataExchange(url, data, callback) {
    $.ajax({
        type: "post",
        dataType: "json",
        url: url,
        data: data,
        contentType: "application/json",
        success: function (json) {
            callback(json);
        },
        error: function (json) {
        }
    });
}

function dataExchangeConfirm(url, data, callback) {
    if (confirm("你确认要提交吗？")) {
        $.ajax({
            type: "post",
            dataType: "json",
            url: url,
            data: data,
            contentType: "application/json",
            success: function (json) {
                callback(json);
            },
            error: function (json) {
            }
        });
    }
}

// 表单转换jsonObject
$.fn.serializeForm = function () {
    var result = {};
    var array = this.serializeArray();
    $(array).each(function (index) {

    });
    $(':input', this).each(function (type) {
        if ("" == this.name) {
            return;
        }
        if ("text" == this.type) {
            result[this.name] = this.value;
        }
        if ("textarea" == this.type) {
            result[this.name] = this.value;
        }
        if ("hidden" == this.type) {
            result[this.name] = this.value;
        }
        if ("select-multiple" == this.type) {
            var items = [];
            var itemsMap = {};
            $(this.options).each(function(){
                if (this.selected) {
                    if (!itemsMap[this.value]) {
                        items.push(this.value);
                        itemsMap[this.value] = 1;
                    }
                }
            });
            result[this.name] = items;
        }
    });
    return result;
}


// 根据Form表单对象封装标准请求参数
function formObjectToRequest(formObject) {
    var request = {};
    request.version = "1.0";
    request.data = formObject.serializeForm();
    return JSON.stringify(request);
}

// 根据DataTable的行数据封装标准请求参数
function dataTableRowToRequest(row) {
    var request = {}
    request.version = "1.0";
    request.data = row;
    return JSON.stringify(request);
}