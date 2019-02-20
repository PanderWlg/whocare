<#if package.ServiceImpl?ends_with(".")>
 package ${package.ServiceImpl[0.. <package.ServiceImpl?length]}
 <#else >
 package ${package.ServiceImpl};

</#if>


import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
* <p>
 * ${table.comment!} 服务实现类
 * </p>
*
* @author ${author}
* @since ${date}
*/
@Service
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}>  {

/**
* 保存
* @param one
* @return
*/
public boolean saveDo(${entity} one) {
return this.save(one);
}

/**
* 批量保存
* @param any
* @return
*/
public boolean saveBatchDo(Collection<${entity}> any){
return this.saveBatch(any);
}

/**
* 删除
* @param id
* @return
*/
public boolean removeByIdDo(String id){
return this.removeById(id);
}

/**
* 根据主键更新
* @param one
* @return
*/
public boolean updateByIdDo(${entity} one){
return this.updateById(one);
}

/**
* 查询
* @param id
* @return
*/
public ${entity} getByIdDo(String id){
return this.getById(id);
}

}
