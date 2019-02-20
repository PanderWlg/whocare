package com.womow.toc.whocare.clbx.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.womow.toc.whocare.helper.Helper4Json;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class FreemarkerScriptTest {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Test public void test() throws JsonProcessingException {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("proposer_name_","张三");
        paramMap.put("proposer_name_s", Arrays.asList("张三","李四"));
        List<Map<String,Object>> list=sqlSessionTemplate.selectList("freemarkerTest",paramMap);
        System.out.println(Helper4Json.jsonOm.writeValueAsString(list));
    }

}