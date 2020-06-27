package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.BoardDAO;
import com.example.vo.BoardVO;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	
	@Autowired
	private BoardDAO bDAO = null;
	
	@RequestMapping(value = "/insert", method=RequestMethod.GET)
	public String insertBoard(HttpSession httpSession, Model model) {
		String userid = (String)httpSession.getAttribute("SESSION_ID");
		if (userid ==null) {
			return "redirect:/member/login";
		}
		model.addAttribute("userid", userid);
		return "/board/insert";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertBoardPost(@ModelAttribute BoardVO obj, @RequestParam MultipartFile[] imgs) throws IOException {
		if(imgs != null && imgs.length > 0) {
			for(MultipartFile one : imgs) {
				obj.setBrd_img(one.getBytes()); 
			}
		}
		int ret = bDAO.insertBoard(obj);
		if (ret > 0) {
			return "redirect:/";
		}
		return "redirect:/board/insert";
	}
	
	@RequestMapping(value = "/list", method= RequestMethod.GET)
	public String list(Model model, HttpSession httpsession, HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "text", defaultValue = "", required = false) String text) {
		
		if(page==0) {
			return "redirect:"+request.getContextPath()+"/board/list?page=1";
		}
		
		httpsession.setAttribute("SESSION_BOARD_HIT_CHECK", 1);
	
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", page*10-9);
		map.put("end", page*10);
		map.put("text", text);
		
		List<BoardVO> list = bDAO.selectBoard(map);
		model.addAttribute("list",list);
		
		int cnt=bDAO.countBoard(text);
		model.addAttribute("cnt", (cnt-1)/10 +1);
		
		return "/board/list";
	}
	
	@RequestMapping(value = "/content", method=RequestMethod.GET)
	public String content() {
		return "/board/content";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
