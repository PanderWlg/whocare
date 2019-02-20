<#if package.Mapper?ends_with(".")>
 package ${package.Mapper[0..<package.Mapper?length]}
 <#else >
package ${package.Mapper};
</#if>
import org.apache.ibatis.annotations.Mapper;
import ${package.Entity}.${entity};
import ${superMapperClassPackage};

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
 @Mapper
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

}
</#if>
