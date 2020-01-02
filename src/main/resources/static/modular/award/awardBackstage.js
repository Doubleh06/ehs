var AwardBackstage = {
    tableId: "#grid-table",
    pagerId: "#grid-pager",
    table: null,
    domain: "award"
};

/**
 * jqGrid初始化参数
 */
AwardBackstage.initOptions = function () {
    var options = {
        url : "/awardBackstage/grid",
        autowidth:true,
        colNames: ['ID','奖品等级', '奖品等级描述',"奖品部门",'部门奖品数量','操作'],
        colModel: [
            {name: 'id', index: 'id', width: 20},
            {name: 'level', index: 'level', width: 80},
            {name: 'levelDesc', index: 'levelDesc', width: 80},
            {name: 'department', index: 'department', width: 80},
            {name: 'deptNum', index: 'deptNum', width: 80},
            {name: 'operations', index: 'operations', width: 100, sortable: false, formatter: function (cellValue, options, rowObject) {
                var id = "'"+rowObject["id"]+"'";
                var str = "";
                str += '<input type="button" class="control-auth btn btn-sm btn-warning" data-auth="award_delete"  value="删除" onclick="AwardBackstage.delete(' + id + ')"/>&nbsp;';
                str += '<input type="button" class="control-auth btn btn-sm btn-warning" data-auth="award_modify"  value="编辑" onclick="AwardBackstage.modify(' + id + ')"/>&nbsp;';
                return str;
            }}
        ],
        gridComplete: function () {
            refreshPermission(AwardBackstage.domain);
        }
    };
    return options;
};

/**
 * 根据关键词搜索
 */
AwardBackstage.search = function () {
    var searchParam = {};
    AwardBackstage.table.reload(searchParam);
};

/**
 *新增
 */
AwardBackstage.create = function () {
    $("#createModal").modal();
}
AwardBackstage.insert = function () {
    var awardLevel = getFormJson($("#create-form"));
    $.ajax({
        url: "/awardBackstage/award/insert",
        type: 'POST',
        data: JSON.stringify(awardLevel),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (r) {
            if (r.code === 0) {
                $("#createModal").modal("hide");
                success("保存成功");
                AwardBackstage.search();
                $("#create-form")[0].reset();
            }
        }
    })
}

/**
 *编辑
 */
AwardBackstage.modify = function (id) {
    $.ajax({
        url: "/awardBackstage/award/get?id=" + id,
        type: 'GET',
        dataType: "json",
        success: function (r) {
            if (r.code === 0) {
                var awardLevel = r.obj;
                var form = $("#modify-form");
                form.find("input[name='id']").val(awardLevel.id);
                form.find("input[name='level']").val(awardLevel.level);
                form.find("input[name='level_desc']").val(awardLevel.levelDesc);
                form.find("input[name='department']").val(awardLevel.department);
                form.find("input[name='dept_num']").val(awardLevel.deptNum);
                $("#modifyModal").modal();
            }
        }
    })
    $("#modifyModal").modal();
}
AwardBackstage.update = function () {
    var accidentLevel = getFormJson($("#modify-form"));
    $.ajax({
        url: "/awardBackstage/award/update",
        type: 'POST',
        data: JSON.stringify(accidentLevel),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (r) {
            if (r.code === 0) {
                $("#modifyModal").modal("hide");
                success("编辑成功");
                AwardBackstage.search();
                $("#modify-form")[0].reset();
            }
        }
    })
}


/**
 * 删除
 *
 * @param id    userId
 */
AwardBackstage.delete = function del(id) {
    warning("确定删除吗", "", function () {
        $.get("/awardBackstage/award/delete?id=" + id, function () {
            success("成功删除");
            AwardBackstage.search();
        });
    })
};



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
    var jqGrid = new JqGrid("#grid-table", "#grid-pager", AwardBackstage.initOptions());
    AwardBackstage.table = jqGrid.init();

});