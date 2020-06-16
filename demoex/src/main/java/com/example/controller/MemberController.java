package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dao.MemberDAO;
import com.example.vo.MemberVO;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	@Autowired
	private MemberDAO mDAO = null;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinpost(MemberVO obj) {
		System.out.println(obj.toString());
		int ret = mDAO.insertMember(obj);
		
		if(ret > 0) {
			return "redirect:/";
		}
		return "redirect:/member/join";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginpost(@ModelAttribute MemberVO obj,
			HttpSession httpSession) {
		MemberVO obj1 = mDAO.selectMemberLogin(obj);
		if(obj1 != null) {
			httpSession.setAttribute("SESSION_ID", obj.getUserid());
			return "redirect:/";
		}
		return "redirect:/member/login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession httpSession) {
		httpSession.invalidate();
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/memberlist")
	public String memberlist(Model model) {
		List<MemberVO> list = mDAO.selectMemberList();
		
		model.addAttribute("list", list);
		return "memberlist";
	}
	
	
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
