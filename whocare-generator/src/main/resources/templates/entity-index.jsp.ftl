
<#assign modelName=entity?uncap_first/>
<#assign tableId>table-${modelName}</#assign>
<#assign tableUrl><#if package.ModuleName??>/${package.ModuleName}</#if>/${modelName}/table</#assign>
<#assign formUrl>/views<#if package.ModuleName??>/${package.ModuleName}</#if>/${modelName}/form</#assign>
<#assign deletEntityUrl><#if package.ModuleName??>/${package.ModuleName}</#if>/${modelName}/delete</#assign>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<#noparse > <c:set var="ctx" value="${pageContext.request.contextPath}"/></#noparse>
<html>
<head>
    <#noparse ><base href="${ctx}/"/></#noparse>
    <link rel="stylesheet" href="webjars/layui/2.4.5/css/layui.css">
    <script src="webjars/jquery/1.12.4/jquery.min.js"></script>
    <script src="webjars/layui/2.4.5/layui.all.js"></script>
    <script id="tableToolbar" type="text/html">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="refresh"><i class="layui-icon layui-icon-refresh"></i>刷新
            </button>
            <button class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon layui-icon-add-circle"></i>添加
            </button>
        </div>
    </script>
    <script id="columnToolbar" type="text/html">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm " lay-event="update">编辑
            </button>
            <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete">删除
            </button>
        </div>
    </script>
</head>
<body>

<table id="${tableId}" lay-filter="${tableId}">
</table>
<script>
    layui.use('table', function () {
        var table = layui.table;

        //第一个实例
        table.render({
            title: "${table.comment!}",
            elem: '#${tableId}',
            method:'post',
            contentType:'application/json',
            toolbar: '#tableToolbar',
            defaultToolbar: ['filter'],
            url: '${tableUrl?remove_beginning('/')}',
            page: true,
            request: {
                pageName: 'current',
                limitName: 'size'
            },
            response: {
                statusName: 'status',//规定数据状态的字段名称，默认：code
                statusCode: 200,//规定成功的状态码，默认：0
                msgName: 'msg', //规定状态信息的字段名称，默认：msg
                countName: 'total', //规定数据总数的字段名称，默认：count
                dataName: 'records' //规定数据列表的字段名称，默认：data
            },
            cols: [[
                <#list table.fields as field>
                {field: '${field.propertyName}', title: "${field.comment!field.name}", sort: true},
                </#list>
                {fixed: 'right', width: 150, align: 'center', title: "操作", toolbar: "#columnToolbar"}
            ]]
        });
        table.on('toolbar(${tableId})', function (obj) {
            var tableId = obj.config.id;
            switch (obj.event) {
                case 'add':
                    var loadindex = layer.load(1);
                    $.get('${formUrl?remove_beginning('/')}?tableId=${tableId}', function (data) {
                        var dom = $(data);
                        var width = dom.innerWidth();
                        layer.open({
                            title: '添加${table.comment!}',
                            type: 1,
                            area: width+'px',
                            maxHeight:'90%',
                            content: data,
                            success: function (layero, index) {
                                layero.find('.layui-form').attr('data-layui-layer-index', index);
                                layer.close(loadindex);
                            }
                        });
                    });

                    break;
                case 'refresh':
                    table.reload(tableId);
                    break;
            }
        });
        //
        table.on('tool(${tableId})', function (obj) {
            var data = obj.data;
            data.st_ = "2018-12-31 00:12:00";
            switch (obj.event) {
                case 'update':
                    var loadindex = layer.load(1);
                    $.get('${formUrl?remove_beginning('/')}?tableId=${tableId}&id=' + data.id_, function (data) {
                        var dom = $(data);
                        var width = dom.innerWidth();
                        layer.open({
                            title: '编辑${table.comment!}',
                            type: 1,
                            area: width+'px',
                            maxHeight:'90%',
                            content: data,
                            success: function (layero, index) {
                                layero.find('.layui-form').attr('data-layui-layer-index', index);
                                layer.close(loadindex);
                            }
                        });
                    });
                    break;
                case 'delete':
                    layer.confirm('确定要删除此条记录吗？', {icon: 3, title: "删除${table.comment!}"}, function (index) {
                        layer.close(index);
                        $.ajax({
                            url: '${deletEntityUrl?remove_beginning('/')}',
                            type: 'POST',
                            data: {id: data.id_},
                            success: function (data) {
                                if (data.code == 0) {
                                    table.reload('${tableId}');
                                } else {
                                    layer.msg(data.msg);
                                }

                            },
                            error: function (req, error, exp) {
                                console.log(req.responseJSON);
                            }
                        });
                    });

            }
        });
        table.on('sort(${tableId})', function(obj){
            var type=obj.type;
            var param={};
            if('desc'==type){
                param.descs=[obj.field];
            }else if('asc'==type) {
                param.ascs=[obj.field];
            }
            table.reload('${tableId}', {
                initSort: obj,
                where: param

            });
        });
    });
</script>
</body>
</html>