package com.yiifaa.mirana.risk;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;

import com.yiifaa.mirana.BaseTest;
import com.yiifaa.mirana.exam.domain.RiskItem;
import com.yiifaa.mirana.exam.service.RiskItemService;

public class RiskItemServiceTest extends BaseTest {
	
	@Resource
	private RiskItemService riskService;

	@Test
	public void test() {
		RiskItem Item = new RiskItem();
		Item.setName("网站服务入侵保险");
		Item.setCreateTime(new Date());
		Item.setRemarks("网站入侵保险");
		this.riskService.save(Item);
	}
	
	@Test
	public void testDB() {
		RiskItem Item = new RiskItem();
		Item.setName("等级评估保险");
		Item.setCreateTime(new Date());
		Item.setRemarks("信息安全合规测评是国家强制要求的，信息系统运营、使用单位或者其主管部门，必须在系统建设、改造完成后，选择具备资质测评机构，依据信息安全合规性要求，对信息系统是否合规进行检测和评估的活动。");
		this.riskService.save(Item);
	}
	


}
