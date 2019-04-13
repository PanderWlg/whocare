package com.womow.mp.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.womow.toc.whocare.Application;
import com.womow.toc.whocare.clbx.model.Chuxing;
import com.womow.toc.whocare.clbx.service.ChuxingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MybatisPlusTest {
    @Autowired
    private ChuxingService chuxingService;

    @Test
    public void testTableField() throws JsonProcessingException {
        Chuxing chuxing = new Chuxing();
        chuxing.setDidian_("9")
                .setMudi_("路改造");
        LambdaQueryWrapper<Chuxing> wrapper = Wrappers.lambdaQuery(chuxing);
        wrapper.select(Chuxing::getId_, Chuxing::getDidian_, Chuxing::getBeizhu_, Chuxing::getSt_, Chuxing::getProposer_name_, Chuxing::getEt_);
        chuxingService.list(wrapper).forEach(System.out::println);
        chuxingService.list(wrapper).forEach(System.out::println);
        chuxing.setDidian_("");
        // chuxingService.list(Wrappers.lambdaQuery(chuxing)).forEach(System.out::println);
        /*chuxingService.updateById(new Chuxing()
                .setId_("1bbd521110514b7ab64b604a18f7f6d5")
                .setDidian_("天津市河东区大王庄鼎泰大厦").setMudi_("").setEt_(null));
                */
    }
}
