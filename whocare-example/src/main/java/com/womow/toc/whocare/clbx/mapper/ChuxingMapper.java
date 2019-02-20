package com.womow.toc.whocare.clbx.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.womow.toc.whocare.clbx.model.ChuxingDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author changqingshun
 * @since 2019-01-29
 */
 @Mapper
public interface ChuxingMapper extends BaseMapper<ChuxingDto> {

 int insertB(ChuxingDto entity);
}
