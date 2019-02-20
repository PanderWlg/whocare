package com.womow.toc.whocare.clbx.service;

import com.womow.toc.whocare.clbx.model.ChuxingDto;
import com.womow.toc.whocare.clbx.mapper.ChuxingMapper;
import com.womow.toc.whocare.helper.Helper4Common;
import com.womow.toc.whocare.mybatis.service.MybatisBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Collection;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author changqingshun
 * @since 2019-01-29
 */
@Service
public class ChuxingService extends MybatisBaseServiceImpl<ChuxingMapper, ChuxingDto> {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void save() {
        String sql = "INSERT INTO t_pmis_chuxing_copy1 ( id_, didian_, mudi_, proposer_name_, st_, et_, txr_, beizhu_, create_time_ ) VALUES ( ?, 'tianjin', 'make money', 'john', '2019-01-30 10:00:00', '2019-02-03 18:00:00', '', '123', '2019-02-01 14:48:35' );\n";
        jdbcTemplate.update(sql, Helper4Common.getUuid32());
        ChuxingDto chuxingDto = new ChuxingDto()
                .setBeizhu_("123")
                .setDidian_("tianjin")
                .setProposer_name_("john")
                .setMudi_("make money")
                .setSt_("2019-01-30 10:00:00")
                .setEt_("2019-02-03 18:00:00")
                .setTxr_("");

        this.save(chuxingDto);
        int a = 1 / 0;
    }

}
