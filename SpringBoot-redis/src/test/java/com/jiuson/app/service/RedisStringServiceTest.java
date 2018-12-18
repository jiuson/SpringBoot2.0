package com.jiuson.app.service;

import com.alibaba.fastjson.JSON;
import com.jiuson.app.BTest;
import com.jiuson.app.RedisStringService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class RedisStringServiceTest extends BTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        //单个类，项目拦截器无效
        mockMvc = MockMvcBuilders.standaloneSetup(new RedisStringService()).build();
        //项目拦截器有效
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addStringValue() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/redisString?key=key&value=value")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(null));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        Assert.assertTrue("true", status == 200);
        Assert.assertFalse("false", status != 200);
        System.out.println(content);
    }

    @Test
    public void addStringValueAndExpire() {
    }

    @Test
    public void getStringValue() {
    }
}