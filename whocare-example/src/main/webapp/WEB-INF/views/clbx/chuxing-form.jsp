<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="width: 600px;padding: 10px">
    <form class="layui-form" action="api/clbx/chuxing/upsert" lay-filter="chuxingForm">
        <input name="id_" type="hidden"/>
        <div class="layui-form-item">
            <label class="layui-form-label">出行人</label>
            <div class="layui-input-block">
                <input name="proposer_name_" required lay-verify="required" placeholder="请输入出行人"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">出行地点</label>
            <div class="layui-input-block">
                <input type="text" name="didian_" required lay-verify="required" placeholder="请输入出行地点"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">出行目的</label>
            <div class="layui-input-block">
                <input type="text" name="mudi_" required lay-verify="required" placeholder="请输入出行目的" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">同行人</label>
            <div class="layui-input-block">
                <input type="text" name="txr_" required lay-verify="required" placeholder="请输入同行人" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">开始时间</label>
            <div class="layui-input-block">
                <input class="layui-input datetime" name="st_" required lay-verify="required" readonly="readonly"
                       placeholder="请选择开始时间" autocomplete="off">
            </div>

        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">结束时间</label>
            <div class="layui-input-block">
                <input class="layui-input datetime" name="et_" required lay-verify="required" readonly="readonly"
                       placeholder="请输入结束时间" autocomplete="off">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea type="text" name="beizhu_" required lay-verify="required" placeholder="请输入备注"
                          autocomplete="off"
                          class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formChuxing">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>

    </form>
    <script>
        layui.use('laydate', function () {
            laydate = layui.laydate;
            laydate.render({
                elem: ".datetime",
                type: 'datetime',
                format: "yyyy-MM-dd HH:mm:ss"

            })
        });
        layui.use('form', function () {
            var form = layui.form;
            <c:if test="${not empty param.id}">
            var id = '${param.id}';
            $.get('api/clbx/chuxing/info?id=' + id, function (data) {
                if (data.code == 0) {
                    form.val('chuxingForm', data.data);
                }
            });
            </c:if>

            form.on('submit(formChuxing)', function (data) {
                var form = data.form;
                var formData = data.field;
                var btn = data.elem;
                var index = layer.load(1);
                $.ajax({
                        url: 'api/clbx/chuxing/upsert',
                        type: "POST",
                        data: formData,
                        success: function (data) {
                            if (data.code == 0) {
                                layui.table.reload('${RequestParameters.tableId}');
                                var layerIndex = $(form).attr('data-layui-layer-index');
                                layer.close(layerIndex);
                            }
                        },
                        beforeSend: function () {
                            $(btn).attr('disabled', 'disabled');
                        },
                        error: function (req, error, exp) {
                            console.log(req.responseJSON);
                        },
                        complete: function () {
                            layer.close(index);
                            $(btn).removeAttr('disabled');
                        }
                    }
                )
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });
        });
    </script>
</div>