package com.womow.toc.whocare.mybatis.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @param <T>
 * @author changqingshun
 */
public interface MybatisBaseService<T> extends IService<T> {
    /**
     * <p>
     * 查询列表，带分页，不执行count sql
     * </p>
     *
     * @param page         分页模型{@link com.baomidou.mybatisplus.extension.plugins.pagination.Page}
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    default List<Map<String, Object>> listMaps(Page<T> page, Wrapper<T> queryWrapper) {
        page.setSearchCount(false);
        return pageMaps(nonSearchCount(page), queryWrapper).getRecords();
    }

    /**
     * <p>
     * 查询分页列表
     * </p>
     *
     * @param page 分页模型{@link com.baomidou.mybatisplus.extension.plugins.pagination.Page}
     * @see Wrappers#emptyWrapper()
     */
    default List<Map<String, Object>> listMaps(Page<T> page) {
        return listMaps(page, Wrappers.emptyWrapper());
    }

    /**
     * 分页数据
     *
     * @param page         分页模型{@link com.baomidou.mybatisplus.extension.plugins.pagination.Page}
     * @param queryWrapper
     * @return
     */
    default List<T> list(Page<T> page, Wrapper<T> queryWrapper) {
        if (page != null) {
            page.setSearchCount(false);
        }
        return page(nonSearchCount(page), queryWrapper).getRecords();
    }

    /**
     * <p>
     * 查询分页
     * </p>
     *
     * @param page 分页模型{@link com.baomidou.mybatisplus.extension.plugins.pagination.Page}
     * @see Wrappers#emptyWrapper()
     */
    default List<T> list(Page<T> page) {
        return list(page, Wrappers.emptyWrapper());
    }

    /**
     * @param page
     * @return
     */
    default Page<T> nonSearchCount(Page<T> page) {
        if (page != null) {
            page.setSearchCount(false);
        }
        return page;
    }

    /**
     * layuitable
     *
     * @param page
     * @param wrapper
     * @return
     */
    default Map<String, Object> getTable(IPage<T> page, Wrapper<T> wrapper) {
        this.page(page, wrapper);
        Map<String, Object> table = new HashMap<>(3);
        table.put("total", page.getTotal());
        table.put("status", 200);
        table.put("records", page.getRecords());
        return table;
    }

}
