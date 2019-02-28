
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<#assign modelName=entity?uncap_first>
<#assign formName>form-${modelName}</#assign>
<#assign upsertUrl><#if package.ModuleName??>/${package.ModuleName}</#if>/${modelName}/upsert</#assign>
<#assign infoUrl><#if package.ModuleName??>/${package.ModuleName}</#if>/${modelName}/info</#assign>
<div style="width: 600px;padding: 10px">
    <form class="layui-form" action="${upsertUrl?remove_beginning('/')}" lay-filter="${formName}">
        <#list table.fields as field>
            <#if field.keyFlag>
                <#assign keyPropertyName="${field.propertyName}"/>
            </#if>
            <#if field.keyFlag>
            <#-- 主键 -->

                <input name="${field.propertyName}" type="hidden"/>

            <#-- 普通字段 -->
            <#elseif field.fill??>
            <#else>
        <div class="layui-form-item">
            <label class="layui-form-label"><#if field.comment!?length gt 0> ${field.comment}<#else >${field.propertyName}</#if></label>
            <div class="layui-input-block">
                <input name="${field.propertyName}" required lay-verify="required" <#if field.comment!?length gt 0>placeholder="请输入${field.comment}"</#if>
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>
            </#if>
        </#list>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="${formName}">立即提交</button>
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
            <#noparse>
            <c:if test="${not empty param.id}">
            var id = '${param.id}';
            </#noparse>
            $.get('${infoUrl?remove_beginning('/')}?id=' + id, function (data) {
                if (data.code == 0) {
                    form.val('${formName}', data.data);
                }
            });
            <#noparse></c:if></#noparse>
            form.on('submit(${formName})', function (data) {
                var form = data.form;
                var formData = data.field;
                var btn = data.elem;
                var index = layer.load(1);
                $.ajax({
                        url: '${upsertUrl?remove_beginning('/')}',
                        type: "POST",
                        data: formData,
                        success: function (data) {
                            if (data.code == 0) {
                                <#noparse >
                                layui.table.reload('${param.tableId}');
                                var layerIndex = $(form).attr('data-layui-layer-index');
                                layer.close(layerIndex);
                                </#noparse>
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