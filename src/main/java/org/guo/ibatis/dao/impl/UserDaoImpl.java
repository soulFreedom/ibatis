package org.guo.ibatis.dao.impl;

import java.util.List;
import java.util.Map;

import org.guo.ibatis.dao.IUserDao;
import org.guo.ibatis.dao.po.User;

public class UserDaoImpl extends BaseDao implements IUserDao {
	
	//添加一个用户信息，返回自增长的主键
	public String addGetPk(User user) throws Exception {
		return (String)dao.insert("user.insertAndGetPk", user);
	}
	
	//外联方式插入
	public void addOutLine(User user) throws Exception {
		dao.insert("user.insertUserOutLine", user);
	}
	
	//内联方式插入
	public void addInnerLine(User user) throws Exception {
		dao.insert("user.insertUserInnerLine", user);
	}


	//删除一个用户信息，返回主键
	public int delete(User user) throws Exception {
		return dao.delete("user.delByUser", user);
	}
	
	//根据唯一用户标识，获取一个用户信息
	public User getUser(String uid) throws Exception {
		return (User)dao.queryForObject("user.getUserByUid", uid);
	}
	
	//获取所有的用户信息
	public List<User> getAllUsers() throws Exception {
		List<User> users = dao.queryForList("user.getAllUsers");
		return users;
	}
	
	//修改用户信息
	public void update(User user) throws Exception {
		dao.update("user.updateUser", user);
	}
	
	//批量新增数据.
	public void batchAdd(List<User> users) throws Exception {
		dao.insert("user.batchInsert", users);
	}
	
	//模糊查询
	public User getUserLikeName(String name) throws Exception {
		return (User)dao.queryForObject("user.getUserLike", name);
	}
	
	//根据条件动态拼接查询
	public List<User> getUsersDynConditon(User user) throws Exception {
		List<User > users = dao.queryForList("user.getUser-dynamic-where", user);
		return users;
	}

	public List<User> getRangeAagUser(User user) throws Exception {
		List<User> users = dao.queryForList("user.getRangeAgeUsers", user);
		return users;
	}

	public List<User> getUserByRoles(User user) throws Exception {
		List<User> users = dao.queryForList("user.getUsersUserInCondition_iterator", user);
		return users;
	}

	public Object getUserAgeUseId(Map params) throws Exception {
		return dao.queryForObject("user.p_getUserAgeUseID", params);
	}
}
