/**
 * 后台列表格式化金额
 * @param {Object} s 金额字符串
 * @param {Object} n 保留小数位数
 * @return {TypeName}
 */
function fmoney(s, n) {
    n = n > 0 && n <= 20 ? n : 2;
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
    t = "";
    for (i = 0; i < l.length; i++) {
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
    }
    return t.split("").reverse().join("") + "." + r;
}

/*将form表单元素的值序列化成对象 */
function serializeObject(form) {
    var o = {};
    $.each(form.serializeArray(), function (index) {
        if (o[this['name']]) {
            o[this['name']] = o[this['name']] + "," + this['value'];
        } else {
            o[this['name']] = this['value'];
        }
    });
    return o;
};

/*清空*/
function clearForm(datagrid) {
    //清空combobox
    $("#searchForm select[class^=easyui-combobox]").each(function () {
        $("#" + $(this).attr("id")).combobox('setValue', '');
    });

    //清空textbox
    $("#searchForm input[class^=easyui-textbox]").each(function () {
        $("#" + $(this).attr("id")).textbox('setValue', '');
    });

    //清空datebox
    $("#searchForm input[class^=easyui-datebox]").each(function () {
        $("#" + $(this).attr("id")).datebox('setValue', '');
    });

    //清空datetimebox
    $("#searchForm input[class^=easyui-datetimebox]").each(function () {
        $("#" + $(this).attr("id")).datetimebox('setValue', '');
    });
    //清空datetimebox
    $("#searchForm input[class^=easyui-numberbox]").each(function () {
        $("#" + $(this).attr("id")).numberbox('setValue', '');
    });


    datagrid.datagrid('load', {});
    datagrid.datagrid('clearSelections');
    datagrid.datagrid('clearChecked');
}