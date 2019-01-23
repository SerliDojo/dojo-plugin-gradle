package com.serli.dojo.plugingradledemoapp;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PluginGradleDemoAppApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	@Ignore
	public void failingTest() {
		Assert.fail("I want to break"); // free !
	}

}

