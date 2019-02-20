<#if package.Controller?ends_with(".")>
 package ${package.ServiceImpl[0.. <package.Controller?length]}
<#else >
 package ${package.Controller};

</#if>
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import ${package.ServiceImpl}.${table.serviceImplName};
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/${entity}")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
 /**
 ${table.comment!} 服务实现类
 <#assign serviceName=table.serviceImplName?uncap_first/>
 <#assign modelName=entity?uncap_first>
 **/
 @Autowired
 private ${table.serviceImplName} ${serviceName};
}
</#if>
