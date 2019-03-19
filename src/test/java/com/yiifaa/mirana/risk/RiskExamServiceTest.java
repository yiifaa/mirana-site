package com.yiifaa.mirana.risk;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;

import com.yiifaa.mirana.BaseTest;
import com.yiifaa.mirana.exam.domain.RiskExam;
import com.yiifaa.mirana.exam.service.RiskExamService;

public class RiskExamServiceTest extends BaseTest {
	
	@Resource
	private RiskExamService riskService;

	@Ignore
	@Test
	public void test() {
		RiskExam exam = new RiskExam();
		exam.setName("网站高可用性评测");
		exam.setType(1);
		exam.setCreateTime(new Date());
		exam.setRemarks("网站可用性(web usability)是衡量用户体验的指标,是对用户使用网站达成目标是否顺利、以及在这个过程中用户是否满意的综合衡量。");
		this.riskService.save(exam);
	}
	
	@Ignore
	@Test
	public void testDB() {
		RiskExam exam = new RiskExam();
		exam.setName("信息安全等级合规测评");
		exam.setType(1);
		exam.setCreateTime(new Date());
		exam.setRemarks("信息安全合规测评是国家强制要求的，信息系统运营、使用单位或者其主管部门，必须在系统建设、改造完成后，选择具备资质测评机构，依据信息安全合规性要求，对信息系统是否合规进行检测和评估的活动。");
		this.riskService.save(exam);
	}
	
	@Test
	public void testCG() {
		RiskExam exam = new RiskExam();
		exam.setName("网页防篡改测评");
		exam.setType(1);
		exam.setCreateTime(new Date());
		exam.setRemarks("凡是开办门户网站、新闻网站、电子商务网站的，要能够防范网站、网页被篡改，被篡改后能够自动恢复（公安部第82号令）。");
		this.riskService.save(exam);
	}


}
