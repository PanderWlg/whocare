<#assign serviceName=table.serviceImplName?uncap_first/>
<#assign modelName=entity?uncap_first>
<#if package.Controller?ends_with(".")>
 package ${package.ServiceImpl[0.. <package.Controller?length]}
<#else >
 package ${package.Controller};

</#if>
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import ${package.Entity}.${entity};
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Map;
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
 * ${table.comment!}前端控制器
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
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/${modelName}")
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

 **/
 @Autowired
 private ${table.serviceImplName} ${serviceName};

    /**
    * ${table.comment!}表格
    *
    * @param page
    * @return
    */
    @RequestMapping("/table")
    Map<String, Object> table(@RequestBody Page<${entity}> page, ${entity} ${modelName}) {
        LambdaQueryWrapper<${entity}> queryWrapper = Wrappers.lambdaQuery(${modelName});
        return ${serviceName}.getTable(page,queryWrapper);
      }

      /**
      * ${table.comment!}列表
      *
      * @param page
      * @return
      */
      @RequestMapping("/list")
      List<? extends Object> list(Page<${entity}> page,${entity} ${modelName}) {
        page.setSearchCount(false);
        return ${serviceName}.list(page,Wrappers.lambdaQuery(${modelName}));
       }

    /**
    * 创建/编辑${table.comment!}
    * @param ${modelName}
    * @return
    */
    @RequestMapping("/upsert")
    R<${entity}> upsert(${entity} ${modelName}) throws InterruptedException {
        this.${serviceName}.saveOrUpdate(${modelName});
        return R.ok(${modelName});
        }

    /**
    * 获取${table.comment!}详情
    *
    * @param id
    * @return
    */
    @RequestMapping("/info")
    R<${entity}> info(String id) {
        return R.ok(${serviceName}.getById(id));
     }

     /**
     * 删除${table.comment!}
     * @param id
     * @return
     */
     @RequestMapping("/delete")
     R<Object> delete(String id) {
        boolean ok = ${serviceName}.removeById(id);
        return ok ? R.ok("") : R.failed("操作失败");
      }
}
</#if>
