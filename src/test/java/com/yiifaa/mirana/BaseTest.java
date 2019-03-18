package com.yiifaa.mirana;

import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/application-context.xml" })
public class BaseTest {
	
	@Resource
	private ApplicationContext applicationContext;

	@Test
	public void test() {
		assertThat(applicationContext, IsNull.notNullValue());
	}

}
