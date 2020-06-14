package com.example.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vo.MemberVO;

@Service
@Transactional
public class MemberDAO {
	
	@Autowired
	private SqlSessionFactory sqlFactory = null;
	
	public int insertMember(MemberVO obj) {
		return sqlFactory.openSession().insert("Member.join", obj);
	}
	
	public MemberVO selectMemberLogin(MemberVO obj){
		return sqlFactory.openSession().selectOne("Member.login", obj);
	}

}