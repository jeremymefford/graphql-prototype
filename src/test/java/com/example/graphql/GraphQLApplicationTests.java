package com.example.graphql;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GraphQLApplicationTests {

    @Resource
    private ApplicationContext ctxt;

    @Test
    public void contextLoads() {
        assertThat(ctxt, Matchers.notNullValue());
    }

}
