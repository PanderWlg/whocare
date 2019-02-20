package com.womow.toc.whocare.clbx.test;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.womow.toc.whocare.clbx.model.ChuxingDto;
import com.womow.toc.whocare.clbx.service.ChuxingService;
import com.womow.toc.whocare.helper.Helper4Common;
import com.womow.toc.whocare.helper.Helper4Json;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.womow.toc.whocare.helper.Helper4Json.jsonOm;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ChuxingTest {
    @Autowired
    ChuxingService chuxingService;
    Consumer<Object> print = System.out::println;
    SimpleJdbcInsert simpleJdbcInsert;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    public void initInsert(DataSource dataSource) {
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("t_pmis_chuxing_copy1");
    }

    @Test
    public void test() {
        Map<String, Object> map = new HashMap<>();
        map.put("proposer_name_", "张三");
        List<ChuxingDto> list = this.chuxingService.getBaseMapper().selectByMap(map);
        try {
            print.accept(jsonOm.writeValueAsString(list));
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Rollback
    public void testSave() {
        ChuxingDto chuxingDto = new ChuxingDto()
                .setBeizhu_("123")
                .setDidian_("tianjin")
                .setProposer_name_("john")
                .setMudi_("make money")
                .setSt_("2019-01-30 10:00:00")
                .setEt_("2019-02-03 18:00:00")
                .setTxr_("");
        chuxingService.saveOrUpdate(chuxingDto);
    }

    @Test
    @Rollback(false)
    public void testSaveBatch() {
        List<ChuxingDto> list = new ArrayList<>(3000);
        for (int i = 0; i < 30; i++) {
            ChuxingDto chuxingDto = new ChuxingDto()
                    .setBeizhu_("123")
                    .setDidian_("tianjin")
                    .setProposer_name_("john")
                    .setMudi_("make money")
                    .setSt_("2019-01-30 10:00:00")
                    .setEt_("2019-02-03 18:00:00")
                    .setTxr_("");
            if(i==3){
                int a=1/0;
            }
            chuxingService.save(chuxingDto);
            list.add(chuxingDto);
        }
       // chuxingService.saveBatch(list, 1000);
    }

    @Test
    @Rollback(false)
    public void testSaveBatchByJdbc() {

        List<ChuxingDto> list = new ArrayList<>(3000);
        for (int i = 0; i < 3000; i++) {
            ChuxingDto chuxingDto = new ChuxingDto()
                    .setId_(Helper4Common.getUuid32())
                    .setBeizhu_("123")
                    .setDidian_("tianjin")
                    .setProposer_name_("john")
                    .setMudi_("make money")
                    .setSt_("2019-01-30 10:00:00")
                    .setEt_("2019-02-03 18:00:00")
                    .setTxr_("");
            if(i==3){
                int a=1/0;
            }
            //simpleJdbcInsert.execute(new BeanPropertySqlParameterSource(chuxingDto));
            list.add(chuxingDto);
        }
        simpleJdbcInsert.executeBatch(SqlParameterSourceUtils.createBatch(list));
    }
    @Test public void testSaveBySql(){
        String sql="INSERT INTO t_pmis_chuxing_copy1 ( id_, didian_, mudi_, proposer_name_, st_, et_, txr_, beizhu_, create_time_ ) VALUES ( ?, 'tianjin', 'make money', 'john', '2019-01-30 10:00:00', '2019-02-03 18:00:00', '', '123', '2019-02-01 14:48:35' );\n";
        for(int i=0;i<3000;i++){
            jdbcTemplate.update(sql,Helper4Common.getUuid32());
        }

    }
    @Test
    public void testUpdate() {
        //通过updateWrapper 更新
        chuxingService.update(Wrappers.<ChuxingDto>lambdaUpdate()
                .set(ChuxingDto::getBx_id_, "beee")
                .eq(ChuxingDto::getProposer_name_, "john")
        );
        ChuxingDto chuxingDto = new ChuxingDto()
                .setBeizhu_("123").setBx_id_("344545");

        //更新字段通过设置dto字段，更新条件通过通过updateWrapper,dto字段值只作用于 set 字句,没有更新字段会报错
        chuxingService.update(chuxingDto, Wrappers.<ChuxingDto>lambdaUpdate().eq(ChuxingDto::getProposer_name_, "john"));
        //更新字段通过设置dto字段，更新条件通过通过updateWrapper,dto字段值只作用于 set 字句,where 字句,没有更新字段会报错
        ChuxingDto exam = new ChuxingDto().setProposer_name_("john");
        chuxingService.update(chuxingDto, Wrappers.<ChuxingDto>lambdaUpdate(exam).eq(ChuxingDto::getProposer_name_, "john"));

    }

    @Test
    public void testPageList() throws JsonProcessingException {
        Page<ChuxingDto> page = new Page<>();
        page.setSize(3);
        page.setDesc("create_time_");
        //page.setAsc("proposer_name_");
        print.accept(Helper4Json.jsonOm.writeValueAsString(chuxingService.list(page)));
    }
}
