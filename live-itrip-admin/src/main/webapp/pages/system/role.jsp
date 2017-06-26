<%--
  Created by IntelliJ IDEA.
  User: Feng
  Date: 2016/10/17
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="/pages/importcss.jsp"/>
<link href="/css/plugins/jsTree/style.min.css" rel="stylesheet">


<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox-title">
                <div class="ibox-tools">
                    <div class="btn-group">
                        <a onclick="funRefresh();" class="btn btn-primary ">
                            <span class="fa fa-refresh"></span> 刷新
                        </a>
                    </div>
                    <div class="btn-group">
                        <a class="btn btn-primary dropdown-text" onclick="funClickAddRow()">
                            <i class="fa fa-plus"></i> 新增
                        </a>
                    </div>
                </div>
            </div>
            <div class="ibox-content table-responsive">
                <table class="table table-hover table-bordered dataTables-example" id="tabRole"
                       style="font-size: 12px;">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>字典名称</th>
                        <th>显示文本</th>
                        <%--<th>是否删除</th>--%>
                        <th>创建时间</th>
                        <th>更新时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>

                </table>
            </div>
        </div>
    </div>
</div>

<div class="modal fade modal-default" id="formEditRole">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="formEditTitle">角色编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <input id="editRoleId" type="hidden">
                        <label for="editRoleName" class="col-sm-3 control-label">角色名称</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="editRoleName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editRoleText" class="col-sm-3 control-label">显示文本</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="editRoleText">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn   btn-primary" data-dismiss="modal">取消</button>
                <button type="button" id="publicBtn" class="btn   btn-primary" onclick="editSaveRoleInfo()">确定
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade modal-default" id="formEditPermission">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title">权限编辑</h4>
            </div>
            <div class="modal-body">
                <input id="editPermissionRoleId" type="hidden">
                <div id="permissionTree"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn   btn-primary" data-dismiss="modal">取消</button>
                <button type="button" id="btnSavePermission" class="btn btn-primary" onclick="editSavePermission()">确定
                </button>
            </div>
        </div>
    </div>
</div>

<c:import url="/pages/importjs.jsp"/>
<script src="/js/plugins/jsTree/jstree.min.js"></script>

<script src="/javascript/system/role.js"></script>

