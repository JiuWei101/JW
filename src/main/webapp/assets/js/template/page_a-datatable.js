var QueryForm = function () {

    // 查询表单
    formObject = $('#query_form');
    // 结果dataTable
    tableObject = $('#result_table');
    
    // 触发按钮
    addButtonObject = $('#button_add');
    editButtonObject = $('#button_edit');
    deleteButtonObject = $('#button_delete');
    
    // 隐藏的表单
    addFormDiv = $('#form_add_div');
    addFormSubmit = $('#form_add_btn', addFormDiv);
    editFormDiv = $('#form_edit_div');
    editFormSubmit = $('#form_edit_btn', editFormDiv);
    
    // ==================
    // 初始化DataTable方法
    // ==================
    var initStandardTable = function (apiUrl) {
        
        // 如果DataTable已初始化则reload数据
        if ($.fn.DataTable.isDataTable(tableObject)) {
            tableObject.dataTable().api().ajax.reload();
            return;
        }

        // 从result_table中th标签获取初始化dataTable的信息
        columnsSettings = [];
        $('th', tableObject).each(function () {
            var item = {};
            item["data"] = $(this).attr("name");
            if (undefined == $(this).attr("invisible")) {
                item["visible"] = true;
            } else {
                item["visible"] = false;
            }
            columnsSettings.push(item);
        });

        // 初始化DataTable
        var oTable = tableObject.dataTable({
            "serverSide": true,
            "lengthMenu": [
                [10, 20, 30],
                [10, 20, 30]
            ],
            "ajax": {
                "url": apiUrl,    // <--- 查询请求地址
                "type": "POST",                 // <--- 查询请求方法
                "data": function (d) {
                    var request = {
                        draw: d.draw,
                        start: d.start,
                        length: d.length
                    };
                    return jQuery.extend(request, formObject.serializeForm());
                }
            },
            "columns": columnsSettings,
            "pageLength": 10
        });

        // -------------------
        // 注册DataTable条目选择器
        // -------------------
        $('tbody', tableObject).off("click", $('tbody', tableObject));
        $('tbody', tableObject).on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            } else {
                oTable.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        });
    }

    // ===================
    // 初始化 新增 按钮的方法
    // ===================
    var initItemAddFunc = function (apiUrl) {

        // 注册新增按钮点击事件
        addButtonObject.off("click", addButtonObject);
        addButtonObject.click(function () {
            // 展现隐藏的表单DIV
            addFormDiv.modal('show');
        });

        // 注册隐藏表单点击保存按钮事件
        addFormSubmit.off("click", addFormSubmit);
        addFormSubmit.click(function () {
            // DIV内含一个FORM表单
            var editForm = $('form', addFormDiv);
            if (editForm.valid()) {
                dataExchange(
                    apiUrl,                     // <--- 新增数据提交url
                    formObjectToRequest(editForm),
                    function (json) {           // <--- 新增数据收到结果的处理方法
                        if (0 != json.code) {
                            alert(json.message);
                        }
                        if ($.fn.DataTable.isDataTable(tableObject)) {
                            tableObject.dataTable().api().ajax.reload();
                        }
                    });
                addFormDiv.modal('hide');

                $('input', editForm).each(function () {
                    $(this).val("");
                });
            }

            // tagsinput问题待解决
            // $('#add_form_choice', editForm).tagsinput('removeAll');
        });
    }

    // ===================
    // 初始化 编辑 按钮的方法
    // ===================
    var initItemEditFunc = function (apiUrl) {

        // 注册新增按钮点击事件
        editButtonObject.off("click", editButtonObject);
        editButtonObject.click(function () {
            var oTable = tableObject.dataTable();
            var row = oTable.fnGetData(oTable.$('tr.selected'));

            if (null == row) {
                return;
            }

            // 展现隐藏的表单DIV
            editFormDiv.modal('show');

            // DIV内含一个FORM表单
            var editForm = $('form', editFormDiv);

            // 编辑按钮的行为
            $(':input', editForm).each(function () {
                $(this).val(row[this.name]);
            });
        });

        // 注册隐藏表单点击保存按钮事件
        editFormSubmit.off("click", editFormSubmit);
        editFormSubmit.click(function () {
            // DIV内含一个FORM表单
            var editForm = $('form', editFormDiv);

            if (editForm.valid()) {
                dataExchangeConfirm(
                    apiUrl,                     // <--- 新增数据提交url
                    formObjectToRequest(editForm),
                    function (json) {           // <--- 新增数据收到结果的处理方法
                        if (0 != json.code) {
                            alert(json.message);
                        }
                        if ($.fn.DataTable.isDataTable(tableObject)) {
                            tableObject.dataTable().api().ajax.reload();
                        }
                    });
                editFormDiv.modal('hide');

                $('input', editForm).each(function () {
                    $(this).val("");
                });
            }
        });
    }

    // ===================
    // 初始化 删除 按钮的方法
    // ===================
    var initItemDeleteFunc = function (apiUrl) {

        var oTable = tableObject.dataTable();

        deleteButtonObject.off("click", deleteButtonObject);
        deleteButtonObject.click(function () {
            // 获取DataTable数据
            var row = oTable.fnGetData(oTable.$('tr.selected'));

            if (null == row) return;

            // 删除按钮的行为
            dataExchangeConfirm(
                apiUrl,                         // <--- 删除数据提交url
                dataTableRowToRequest(row),
                function (json) {               // <--- 删除数据收到结果的处理方法
                    if (0 != json.code) {
                        alert(json.message);
                    }
                    if ($.fn.DataTable.isDataTable(tableObject)) {
                        tableObject.dataTable().api().ajax.reload();
                    }
                });

        });
    }

    return {
        // 初始化方法
        initTable: function (apiUrl) {
            initStandardTable(apiUrl);
        },

        // 初始化新增按钮
        initItemAdd: function (apiUrl) {
            initItemAddFunc(apiUrl);
        },

        // 初始化编辑按钮
        initItemEdit: function (apiUrl) {
            initItemEditFunc(apiUrl);
        },

        // 初始化删除按钮
        initItemDelete: function (apiUrl) {
            initItemDeleteFunc(apiUrl);
        }
    };
}();
