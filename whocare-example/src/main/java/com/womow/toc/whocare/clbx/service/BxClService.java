package com.womow.toc.whocare.clbx.service;

import com.womow.toc.whocare.clbx.model.BxClDto;
import com.womow.toc.whocare.clbx.mapper.BxClMapper;
import com.womow.toc.whocare.mybatis.service.MybatisBaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author changqingshun
* @since 2019-01-29
*/
@Service
public class BxClService extends MybatisBaseServiceImpl<BxClMapper, BxClDto>  {

/**
* 保存
* @param one
* @return
*/
public boolean saveDo(BxClDto one) {
return this.save(one);
}

/**
* 批量保存
* @param any
* @return
*/
public boolean saveBatchDo(Collection<BxClDto> any){
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
public boolean updateByIdDo(BxClDto one){
return this.updateById(one);
}

/**
* 查询
* @param id
* @return
*/
public BxClDto getByIdDo(String id){
return this.getById(id);
}

}
