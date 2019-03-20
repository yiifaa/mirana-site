package com.yiifaa.mirana.risk;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.yiifaa.mirana.BaseTest;
import com.yiifaa.mirana.exam.helper.ExamStateHelper;
import com.yiifaa.mirana.exam.service.ExamStateService;

public class RiskStateServiceTest extends BaseTest {
	
	@Resource
	private ExamStateService stateService;

	@Test
	public void test() {
		String userId = "123";
		List<ExamStateHelper> items = this.stateService.findByUserId(userId);
		for(ExamStateHelper item: items) {
			System.out.println(item);
		}
	}

}
