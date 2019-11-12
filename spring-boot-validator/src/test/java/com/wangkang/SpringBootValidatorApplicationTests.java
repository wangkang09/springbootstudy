package com.wangkang;

import com.wangkang.service.CrossParameterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootValidatorApplicationTests {
	@Resource
	CrossParameterService crossParameterService;

	@Test
	public void contextLoads() {
		try {
			crossParameterService.CrossParameter("wk","yq");

		} catch (ConstraintViolationException e) {
			for(ConstraintViolation violation : e.getConstraintViolations()) {
				System.out.println(violation.getMessage());
			}
		}
	}

}

