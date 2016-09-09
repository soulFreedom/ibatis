package org.guo.ibatis.dao.impl;

import org.guo.ibatis.factory.SqlMapClientFactory;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BaseDao {
	protected SqlMapClient dao = null;
	
	protected BaseDao() {
		this.dao = SqlMapClientFactory.getInstance();
	}
	
}
