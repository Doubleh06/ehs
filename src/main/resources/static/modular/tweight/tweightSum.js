var TWeightSum = {
    tableId: "#grid-table",
    pagerId: "#grid-pager",
    table: null,
    domain: "tWeightSum"
};

/**
 * jqGrid初始化参数
 */
TWeightSum.initOptions = function () {
    var options = {
        url : "/tweight/sum/grid",
        autowidth:true,
        postData : {
            date : $("#date").val()
        },
        rowNum:50,
        colNames: ['物料','物料总重量'],
        colModel: [
            {name: 'FMaterial', index: 'FMaterial', width: 150},
            {name: 'FWeight', index: 'FWeight', width: 150},

            // {name: 'operations', index: 'operations', width: 80, sortable: false, formatter: function (cellValue, options, rowObject) {
            //
            //     var id = "'"+rowObject["id"]+"'";
            //     var str = "";
            //     // str += '<input type="button" class=" btn btn-sm btn-warning"  value="删  除" onclick="TWeightSum.delete(' + id + ')"/>&nbsp;';
            //     // str += '<input type="button" class=" btn btn-sm btn-info"  value="编辑" onclick="TWeightSum.modify(' + id + ')"/>&nbsp;';
            //     // str += '<input type="button" class=" btn btn-sm btn-danger"  value="删除" onclick="TWeightSum.delete(' + id + ')"/>';
            //     return str;
            // }}
        ]
    };
    return options;
};

/**
 * 根据关键词搜索
 */
TWeightSum.search = function () {
    var searchParam = {};
    searchParam.date = $("#date").val();
    TWeightSum.table.reload(searchParam);
};

/**
 * 重置搜索
 */
TWeightSum.resetSearch = function () {
    window.location.href = "/tweight/sum";
};

/**
 *新增
 */
TWeightSum.create = function () {
    $("#createModal").modal();
}
TWeightSum.insert = function () {
    var accidentLevel = getFormJson($("#create-form"));
    $.ajax({
        url: "/tweight/insert",
        type: 'POST',
        data: JSON.stringify(accidentLevel),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (r) {
            if (r.code === 0) {
                $("#createModal").modal("hide");
                success("保存成功");
                TWeightSum.search();
                $("#create-form")[0].reset();
            }
        }
    })
}

/**
 *编辑
 */
TWeightSum.modify = function (id) {
    $.ajax({
        url: "/tweight/get?id=" + id,
        type: 'GET',
        dataType: "json",
        success: function (r) {
            if (r.code === 0) {
                var accidentLevel = r.obj;
                var form = $("#modify-form");
                form.find("input[name='name']").val(accidentLevel.name);
                form.find("input[name='id']").val(accidentLevel.id);
                $("#modifyModal").modal();
            }
        }
    })
    $("#modifyModal").modal();
}
TWeightSum.update = function () {
    var accidentLevel = getFormJson($("#modify-form"));
    $.ajax({
        url: "/tweight/update",
        type: 'POST',
        data: JSON.stringify(accidentLevel),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (r) {
            if (r.code === 0) {
                $("#modifyModal").modal("hide");
                success("编辑成功");
                TWeightSum.search();
                $("#modify-form")[0].reset();
            }
        }
    })
}
/**
 * 导出
 */
TWeightSum.export = function () {
    // $.ajax({
    //     type : 'POST',
    //     url: '/tweight/sum/export',
    //     contentType : "application/json" ,
    //     data: JSON.stringify({
    //
    //         status2 : $("#status2").val()
    //     }),
    //     success : function() {
            window.open("/tweight/sum/export?date="+$("#date").val());
        // }
    // });
}
    function dateFtt(fmt,date)
    { //author: meizz
        var o = {
            "M+" : date.getMonth()+1,                 //月份
            "d+" : date.getDate(),                    //日
            "h+" : date.getHours(),                   //小时
            "m+" : date.getMinutes(),                 //分
            "s+" : date.getSeconds(),                 //秒
            "q+" : Math.floor((date.getMonth()+3)/3), //季度
            "S"  : date.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    }

$(function() {
    // $('.chosen-select').chosen({width: "100%"});
    var jqGrid = new JqGrid("#grid-table", "#grid-pager", TWeightSum.initOptions());
    TWeightSum.table = jqGrid.init();

});