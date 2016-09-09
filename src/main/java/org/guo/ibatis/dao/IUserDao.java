package org.guo.ibatis.dao;

import java.util.List;
import java.util.Map;

import org.guo.ibatis.dao.po.User;

public interface IUserDao {
	public String addGetPk(User user) throws Exception;
	public void addOutLine(User user) throws Exception;
	public void addInnerLine(User user) throws Exception;
	public int delete(User user) throws Exception;
	public User getUser(String uid) throws Exception;
	public List<User> getUsersDynConditon(User user) throws Exception;
	public User getUserLikeName(String name) throws Exception;
	public void update(User user) throws Exception;
	public List<User> getAllUsers()throws Exception;
	public void batchAdd(List<User> users) throws Exception;
	public List<User> getRangeAagUser(User user) throws Exception;
	public List<User> getUserByRoles(User user) throws Exception;
	public Object getUserAgeUseId(Map params) throws Exception;
	
}
