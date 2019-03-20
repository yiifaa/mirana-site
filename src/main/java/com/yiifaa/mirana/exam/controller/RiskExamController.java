package com.yiifaa.mirana.exam.controller;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiifaa.mirana.commons.domain.Account;
import com.yiifaa.mirana.commons.utils.SecurityContextUtils;
import com.yiifaa.mirana.exam.domain.ExamState;
import com.yiifaa.mirana.exam.domain.RiskExam;
import com.yiifaa.mirana.exam.domain.RiskItem;
import com.yiifaa.mirana.exam.domain.RiskOrder;
import com.yiifaa.mirana.exam.query.RiskExamQuery;
import com.yiifaa.mirana.exam.service.ExamStateService;
import com.yiifaa.mirana.exam.service.RiskExamService;
import com.yiifaa.mirana.exam.service.RiskItemService;
import com.yiifaa.mirana.exam.service.RiskOrderService;

@Controller
public class RiskExamController {
	
	private RiskExamService riskService;
	
	private ExamStateService examStateService;
	
	private RiskItemService riskItemService;
	
	private RiskOrderService riskOrderService;
	
	@Inject
	public RiskExamController(RiskExamService riskService, ExamStateService examStateService, RiskItemService riskItemService, RiskOrderService riskOrderService) {
		super();
		this.riskService = riskService;
		this.examStateService = examStateService;
		this.riskItemService = riskItemService;
		this.riskOrderService = riskOrderService;
	}

	@RequestMapping("/admin/exam/list")
	public String list() {
		return "admin/exam/list";
	}
	
	@RequestMapping("/admin/exam/query")
	@ResponseBody
	public Page<RiskExam> query(@ModelAttribute RiskExamQuery pageQuery) {
		return this.riskService.find(pageQuery);
	}
	
	@RequestMapping("/client/exam/start/{id}")
	public String start(@PathVariable("id") Long id) {
		Account current = SecurityContextUtils.current();
		if(current != null) {
			this.examStateService.start(current.getId(), id);
		}
		return "/client/exam/start";
	}
	
	@RequestMapping("/client/exam/doing/{id}")
	public String doing(@PathVariable("id") Long id, Model model) {
		Optional<ExamState> es = examStateService.findById(id);
		if(es.isPresent()) {
			model.addAttribute("examState", es.get());
		}
		return "client/exam/doing";
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/client/exam/end", method=RequestMethod.POST)
	public String end(@RequestParam("exId") Long id) {
		this.examStateService.complete(id);
		return "redirect:/app/client/exam/done/" + id;
	}
	
	/**
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/client/exam/done/{id}")
	public String done(@PathVariable("id") Long id, Model model) {
		Optional<ExamState> es = examStateService.findById(id);
		Iterable<RiskItem> items = this.riskItemService.findAll();
		model.addAttribute("examState", es);
		model.addAttribute("riskItems", items);
		return "client/exam/done";
	}
	
	@RequestMapping("/client/risk/order/{id}")
	public String order(@PathVariable("id") Long id, Model model) {
		Optional<RiskItem> ri = this.riskItemService.findById(id);
		if(ri.isPresent()) {
			model.addAttribute("riskItem", ri.get());
		}
		return "client/risk/order";
	}
	
	@RequestMapping(value="/client/risk/book", method=RequestMethod.POST)
	public String book(@RequestParam("rId") Long id) {
		Account current = SecurityContextUtils.current();
		if(current != null) {
			RiskOrder order = this.riskOrderService.book(current.getId(), current.getUsername(), id);
			return "redirect:/app/client/risk/success/" + order.getId();
		}
		return "redirect:/app/client/risk/success";
	}
	
	@RequestMapping(value="/client/risk/success/{id}")
	public String success(@PathVariable("id") Long id, Model model) {
		Optional<RiskOrder> order = this.riskOrderService.findById(id);
		if(order.isPresent()) {
			model.addAttribute("riskOrder", order.get());
		}
		return "client/risk/success";
	}
	
	/**
	 * 订单详细信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/client/risk/detail/{id}")
	public String detail(@PathVariable("id") Long id, Model model) {
		Optional<RiskOrder> order = this.riskOrderService.findById(id);
		if(order.isPresent()) {
			model.addAttribute("riskOrder", order.get());
		}
		return "client/risk/order";
	}
	
	@RequestMapping(value="/sale/risk/accept/{id}", method=RequestMethod.POST)
	@ResponseBody
	public List<RiskOrder> accept(@PathVariable("id") Long id) {
		this.riskOrderService.accept(id);
		return this.riskOrderService.findByState(0);
	}
	
	@RequestMapping(value="/sale/risk/complete/{id}", method=RequestMethod.POST)
	public List<RiskOrder> complete(@PathVariable("id") Long id) {
		this.riskOrderService.complete(id);
		return this.riskOrderService.findByState(0);
	}
	
}
