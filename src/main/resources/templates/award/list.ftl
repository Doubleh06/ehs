<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">

<head>
<#include "/templates/layout/meta.ftl">
    <link href="/static/css/plugins/chosen/bootstrap-chosen.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/bootstrap-datetimepicker.min.css" />
    <link href="/static/css/plugins/select2/select2.min.css" rel="stylesheet">
    <link href="/static/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="/static/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <link href="/static/css/style.css" rel="stylesheet">
</head>

<body>
<div id="wrapper">
<#include "/templates/layout/left.ftl">
    <div id="page-wrapper" class="gray-bg">
    <#include "/templates/layout/header.ftl">

        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>奖品等级设置</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="/main">Home</a>
                    </li>
                    <li class="active">
                        <strong>奖品等级设置</strong>
                    </li>
                </ol>
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
                                    <button class="btn btn-primary" type="button" onclick="AwardBackstage.create()">新增</button>
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
        <#include "/templates/layout/footer.ftl">
    </div>
</div>


<#--分配角色弹框-->
<#include "/templates/layout/commonjs.ftl">
<#--新增弹框-->
<div class="modal fade" id="createModal" tabindex="-1"  role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalTitle">新增奖项</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="create-form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">奖品等级</label>
                        <div class="col-sm-10">
                        <#--<input id="ehsId" type="hidden" name="ehsId"/>-->
                            <input id="level" type="text" name="level"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">奖品等级描述</label>
                        <div class="col-sm-10">
                            <input id="level_desc" type="text" name="level_desc"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">奖品部门</label>
                        <div class="col-sm-10">
                            <input id="department" type="text" name="department"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门奖品数量</label>
                        <div class="col-sm-10">
                            <input id="dept_num" type="text" name="dept_num"/>
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-primary" onclick="AwardBackstage.insert()">确定</button>
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<#--编辑弹框-->
<div class="modal fade" id="modifyModal" tabindex="-1"  role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalTitle">编辑奖项</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="modify-form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">奖品等级</label>
                        <div class="col-sm-10">
                            <input id="id" type="hidden" name="id"/>
                            <input id="level" type="text" name="level"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">奖品等级描述</label>
                        <div class="col-sm-10">
                            <input id="level_desc" type="text" name="level_desc"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">奖品部门</label>
                        <div class="col-sm-10">
                            <input id="department" type="text" name="department"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门奖品数量</label>
                        <div class="col-sm-10">
                            <input id="dept_num" type="text" name="dept_num"/>
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-primary" onclick="AwardBackstage.update()">确定</button>
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script src="/static/modular/award/awardBackstage.js"></script>

<script type="text/javascript">
    $(document).ready(function(){

    });
</script>
</body>
</html>
