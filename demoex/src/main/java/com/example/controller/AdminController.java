package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dao.ItemDAO;
import com.example.vo.ItemVO;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired
	private ItemDAO iDAO = null;
	
	@RequestMapping(value="/home")
	public String home() {
		return "/admin/home";
	}
	
	@RequestMapping(value = "/item")
	public String itemlist(Model model) {
		List<ItemVO> list = iDAO.selectItemList();
		model.addAttribute("list", list);
		return "/admin/item";
	}
	
	@RequestMapping(value = "/item", method=RequestMethod.POST)
	public String itembatch(@RequestParam("btn") String btn,
			@RequestParam(value = "chk[]", required = false) int[] itemno) {
		if(btn.equals("일괄삭제")) {
			iDAO.delegeItemBatch(itemno);
		}
		return "";
	}
	
//	@RequestMapping(value = "/itemupdate")
//	public String itemupdate() {
//		
//		return "";
//	}
	
}
