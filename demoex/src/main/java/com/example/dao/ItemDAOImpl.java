package com.example.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vo.ItemVO;

@Service
@Transactional
public class ItemDAOImpl implements ItemDAO{
	
	@Autowired
	private SqlSessionFactory sqlFactory = null;

	@Override
	public List<ItemVO> selectItemList() {
		return sqlFactory.openSession().selectList("Item.selectList");
	}

}
