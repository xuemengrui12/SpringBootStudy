package com.xmr;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@SpringBootTest
// SpringJUnit支持，由此引入Spring-Test框架支持！
@RunWith(SpringJUnit4ClassRunner.class)
// 指定我们SpringBoot工程的Application启动类
@ContextConfiguration(classes = {Application.class})
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: add(double n1, double n2)
     */
    @Test
    public void testAdd() throws Exception {
//TODO: Test goes here...
        new Calcuator().add(1,2);
    }
}
