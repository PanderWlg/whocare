package com.womow.toc.whocare;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.womow.toc.whocare.helper.Helper4Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author changqingshun
 */
@Component
public class DemoMeataObjectHandler implements MetaObjectHandler {
    /**
     * 插入元对象字段填充（用于插入时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        String createTime = Helper4Date.getCurrentTime();
        if (this.isFill("create_time_", createTime, metaObject, FieldFill.INSERT)) {
            final Object moCreateTime = getFieldValByName("create_time_", metaObject);
            if (moCreateTime == null || StringUtils.isBlank(moCreateTime.toString())) {
                setInsertFieldValByName("create_time_",createTime, metaObject);
            }
        }
        if (this.isFill("status_", "1", metaObject, FieldFill.INSERT)) {
            final Object status = getFieldValByName("status_", metaObject);
            if (status == null || StringUtils.isBlank(status.toString())) {
                setInsertFieldValByName("status_", "1", metaObject);
            }
        }

    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
