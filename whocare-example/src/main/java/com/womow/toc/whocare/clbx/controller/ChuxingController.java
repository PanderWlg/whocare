package com.womow.toc.whocare.clbx.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.womow.toc.whocare.clbx.model.ChuxingDto;
import com.womow.toc.whocare.clbx.service.ChuxingService;
import com.womow.toc.whocare.mybatis.service.MybatisBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author changqingshun
 * @since 2019-01-23
 */
@RestController
@RequestMapping("/api/clbx/chuxing")
public class ChuxingController {
    /**
     * 服务实现类
     **/
    @Autowired
    private ChuxingService chuxingService;
    @Autowired
    MybatisBaseService<ChuxingDto> mybatisBaseService;
    /**
     * 出行列表表格
     *
     * @param page
     * @return
     */
    @RequestMapping("/table")
    Map<String, Object> table(Page<ChuxingDto> page, ChuxingDto chuxingDto) {
        NameMatchTransactionAttributeSource d;
        LambdaQueryWrapper<ChuxingDto> queryWrapper = Wrappers.lambdaQuery(chuxingDto)
                .orderByDesc(ChuxingDto::getCreate_time_).orderByAsc(ChuxingDto::getSt_);
        chuxingService.page(page, queryWrapper);
        Map<String, Object> table = new HashMap<>(3);
        table.put("total", page.getTotal());
        table.put("status", 200);
        table.put("records", page.getRecords());
        return table;
    }

    /**
     * 出行列表
     *
     * @param page
     * @return
     */
    @RequestMapping("/list")
    List<? extends Object> list(Page<ChuxingDto> page,ChuxingDto chuxingDto) {
        page.setSearchCount(false);
        return mybatisBaseService.list(page,Wrappers.lambdaQuery(chuxingDto));
    }

    /**
     * 创建/编辑出行
     *
     * @param chuxingDto
     * @return
     */
    @RequestMapping("/upsert")
    R<ChuxingDto> upsert(ChuxingDto chuxingDto) throws InterruptedException {
        Thread.sleep(1000);

        //this.chuxingService.saveOrUpdate(chuxingDto);
        chuxingService.save();
        return R.ok(chuxingDto);
    }

    /**
     * 获取出行详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/info")
    R<ChuxingDto> info(String id) {
        return R.ok(chuxingService.getById(id));
    }

    /**
     * 删除出行
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    R<Object> delete(String id) {
        boolean ok = chuxingService.removeById(id);
        return ok ? R.ok("") : R.failed("操作失败");
    }

}
