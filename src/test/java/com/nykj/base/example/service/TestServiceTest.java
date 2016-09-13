package com.nykj.base.example.service;

import com.nykj.base.example.model.TestModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-all.xml")
public class TestServiceTest {

    @Autowired
    private TestService testService;

    @Test
    public void testGetStr() {
        TestModel data = new TestModel();

        int res = testService.getStr(data);

        Assert.assertEquals(1, res);
    }
}
