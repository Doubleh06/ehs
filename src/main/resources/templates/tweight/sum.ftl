<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">

<head>
<#include "/templates/layout/meta.ftl">
    <link href="/static/css/style.css" rel="stylesheet">
    <link href="/static/css/plugins/datapicker/datepicker3.css" rel="stylesheet">

</head>

<body>
<div id="wrapper">
<#include "/templates/layout/left.ftl">
    <div id="page-wrapper" class="gray-bg">
    <#include "/templates/layout/header.ftl">

        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>称重列表</h2>
                <#--<ol class="breadcrumb">-->
                    <#--<li>-->
                        <#--<a href="/main">Home</a>-->
                    <#--</li>-->
                    <#--<li class="active">-->
                        <#--<strong>列表</strong>-->
                    <#--</li>-->
                <#--</ol>-->
            </div>
            <div class="col-lg-2">

            </div>
        </div>

        <div class="wrapper wrapper-content">
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox ">
                        <div class="ibox-content">
                            <div class="bar search-bar">
                                <div class="form-inline">
                                    <div class="form-group">
                                        <div class="form-group" id="data_1">
                                            <label class="font-normal">时间：</label>
                                            <div class="input-group date">
                                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input id="date" type="text" class="form-control" value="${date}">
                                            </div>
                                        </div>
                                    </div>
                                    &nbsp&nbsp&nbsp

                                    <button class="btn btn-success"  id="search" type="button" >搜索</button>&nbsp
                                    <button class="btn btn-success" type="button" onclick="TWeightSum.resetSearch()">重置</button>&nbsp
                                    <button class="btn btn-primary" type="button" onclick="TWeightSum.export()">导出</button>
                                </div>
                            </div>
                            </div>
                            <div class="jqGrid_wrapper">
                            <#--jqgrid 表格栏-->
                                <table id="grid-table"></table>
                            <#--jqgrid 分页栏-->
                                <div id="grid-pager"></div>
                            </div>

                        </div>
                    </div>
                </div>
        </div>

        <div class="wrapper wrapper-content">
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox ">
                        <div class="ibox-content">
                            <div class="bar search-bar">
                                <div class="form-inline">
                                    <div class="form-group">
                                        <#--<div class="form-group" id="data_1">-->
                                            <#--<label class="font-normal">时间：</label>-->
                                            <#--<div class="input-group date">-->
                                                <#--<span class="input-group-addon"><i class="fa fa-calendar"></i></span><input id="date1" type="text" class="form-control" value="${date}">-->
                                            <#--</div>-->
                                        <#--</div>-->
                                    </div>
                                    &nbsp&nbsp&nbsp

                                    <#--<button class="btn btn-success"  id="search" type="button" onclick="TWeight.search()">搜索</button>&nbsp-->
                                </div>
                            </div>
                        </div>
                        <div class="jqGrid_wrapper">
                        <#--jqgrid 表格栏-->
                            <table id="grid-table1"></table>
                        <#--jqgrid 分页栏-->
                            <div id="grid-pager1"></div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    <#include "/templates/layout/footer.ftl">
    </div>
</div>


<#--分配角色弹框-->
<#include "/templates/layout/commonjs.ftl">
<script src="/static/modular/tweight/tweightSum.js"></script>
<script src="/static/modular/tweight/tweight.js"></script>
<script src="/static/js/plugins/datapicker/bootstrap-datepicker.js"></script>

<script type="text/javascript">
    $(document).ready(function(){
        $("#search").click(function () {
            TWeightSum.search();
            TWeight.search();
        });
        $('#date').datepicker({
            todayBtn: "linked",
            keyboardNavigation: false,
            forceParse: false,
            calendarWeeks: true,
            autoclose: true,
            format:"yyyy-mm-dd"
        });
        // $('#date1').datepicker({
        //     todayBtn: "linked",
        //     keyboardNavigation: false,
        //     forceParse: false,
        //     calendarWeeks: true,
        //     autoclose: true,
        //     format:"yyyy-mm-dd"
        // });
    });
</script>
</body>
</html>
