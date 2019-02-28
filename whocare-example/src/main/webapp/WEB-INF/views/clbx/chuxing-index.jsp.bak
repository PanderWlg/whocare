<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setAttribute("tableId","chuxingTable");%>
<html>
<head>
    <%@include file="../head.jsp"%>
    <script id="tableToolbar" type="text/html">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="refresh"><i class="layui-icon layui-icon-refresh"></i>刷新
            </button>
            <button class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon layui-icon-add-circle"></i>添加
            </button>
        </div>
    </script>
    <script id="colToolbar" type="text/html">
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
            title: "出行申请表",
            elem: '#${tableId}',
            toolbar: '#tableToolbar',
            defaultToolbar: ['filter'],
            url: 'api/clbx/chuxing/table',
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
                {field: 'id_', title: 'ID', sort: true, fixed: 'left'},
                {field: 'proposer_name_', title: '发起人'},
                {field: 'mudi_', title: '出行目的'},
                {field: 'didian_', title: '出行地点'},
                {field: 'st_', title: '开始时间', sort: true},
                {field: 'st_', title: '结束时间'},
                {field: 'txr_', title: '同行人'},
                {field: 'create_time_', title: '发起时间', sort: true},
                {field: 'status_', title: '状态', sort: true},
                {fixed: 'right', width: 150, align: 'center', title: "操作", toolbar: "#colToolbar"}
            ]]
        });
        table.on('toolbar(${tableId})', function (obj) {
            var tableId = obj.config.id;
            switch (obj.event) {
                case 'add':
                    var loadindex=layer.load(1);
                    $.get('views/clbx/chuxing/form?tableId=${tableId}', function (data) {
                        var dom = $(data);
                        var width = dom.innerWidth();
                        layer.open({
                            title: '添加出行',
                            type: 1,
                            area: width,
                            content: data,
                            success:function(layero, index) {
                                layero.find('.layui-form').attr('data-layui-layer-index',index);
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
                    var loadindex=layer.load(1);
                    $.get('views/clbx/chuxing/form?tableId=${tableId}&id=' + data.id_, function (data) {
                        var dom = $(data);
                        var width = dom.innerWidth();
                        layer.open({
                            title: '编辑出行',
                            type: 1,
                            area: width,
                            content: data,
                            success:function(layero, index) {
                                layero.find('.layui-form').attr('data-layui-layer-index',index);
                                layer.close(loadindex);
                            }
                        });
                    });
                    break;
                case 'delete':
                    layer.confirm('确定要删除此条记录吗？', {icon: 3, title: "删除出行"}, function (index) {
                        layer.close(index);
                        $.ajax({
                            url: 'api/clbx/chuxing/delete',
                            type: 'POST',
                            data: {id: data.id_},
                            success: function (data) {
                                if(data.code==0){
                                    table.reload('${tableId}');
                                }else {
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

    });
</script>
</body>
</html>