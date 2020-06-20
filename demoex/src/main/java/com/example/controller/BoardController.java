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
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, HttpSession httpSession, HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "0", required=false)int page, 
			@RequestParam(value = "text", defaultValue = "", required=false)String text) {
		
		if(page==0) { //  /board/list 만 입력후 페이지에 접속한 상태가 page가 0인 상태와 동일
			return "redirect:" + request.getContextPath() + "/board/list?page=1";
		}
		
		httpSession.setAttribute("SESSION_BOARD_HIT_CHECK", 1);
		
		//게시글 리스트 가져오기
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", page*10-9);
		map.put("end", page*10);
		map.put("text", text);
		List<BoardVO> list = bDAO.selectBoard(map);
		model.addAttribute("list",list);
		
		//게시물 개수(cnt 는 계산 끝난 페이지 값)
		int cnt = bDAO.countBoard(text); //검색어 넘겨줌
		model.addAttribute("cnt", (cnt-1)/10 + 1);

		return "/board/list";
	}
	
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public String content(Model model, HttpSession httpSession,
			@RequestParam(value = "no", defaultValue = "0", required = false) int no
			) {
		
		if (no==0) {
			return "redirect:/board/list";
		}
		
		Integer chk = (Integer) httpSession.getAttribute("SESSION_BOARD_HIT_CHECK");
		
		if (chk != null) {
			if (chk == 1) {
				bDAO.updateHit(no);
			}
			httpSession.setAttribute("SESSION_BOARD_HIT_CHECK", 0);
		}
		
		BoardVO obj = bDAO.selectBoardOne(no);
		model.addAttribute("obj", obj); 
		
		int p = bDAO.selectBoardPrev(no);
		model.addAttribute("prev", p);
		
		int n = bDAO.selectBoardNext(no);
		model.addAttribute("next", n);
		
		return "/board/content";
	}
	
	@RequestMapping(value = "/insertbatch")
	public String insertbatch() {
		return "/board/insertbatch";
	}
	
	@RequestMapping(value = "/insertbatch", method = RequestMethod.POST)
	public String insertbatch(@RequestParam("brd_title[]") String[] brd_title,
			@RequestParam("brd_content[]") String[] brd_content, @RequestParam("brd_id[]") String[] brd_id) {
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		for (int i = 0; i < brd_title.length; i++) {
			BoardVO obj = new BoardVO();
			obj.setBrd_title(brd_title[i]);
			obj.setBrd_content(brd_content[i]);
			obj.setBrd_id(brd_id[i]);

			list.add(obj); // VO가 brd_title배열 길이만큼 들어감
		}
		bDAO.insertBatch(list);
		
		return "";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
