package com.example.dao;

import java.util.HashMap;
import java.util.List;

import com.example.vo.BoardVO;

public interface BoardDAO {
	
	public int insertBoard(BoardVO obj);
	
	public List<BoardVO> selectBoard(HashMap<String, Object> map);
	
	public int countBoard(String text);
	
	public int updateHit(int no);
	
	public BoardVO selectBoardOne(int no);
	
	public int selectBoardPrev(int no);
	public int selectBoardNext(int no);
	


}
