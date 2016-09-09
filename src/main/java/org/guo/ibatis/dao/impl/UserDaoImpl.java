package org.guo.ibatis.dao.impl;

import java.util.List;
import java.util.Map;

import org.guo.ibatis.dao.IUserDao;
import org.guo.ibatis.dao.po.User;

public class UserDaoImpl extends BaseDao implements IUserDao {
	
	//���һ���û���Ϣ������������������
	public String addGetPk(User user) throws Exception {
		return (String)dao.insert("user.insertAndGetPk", user);
	}
	
	//������ʽ����
	public void addOutLine(User user) throws Exception {
		dao.insert("user.insertUserOutLine", user);
	}
	
	//������ʽ����
	public void addInnerLine(User user) throws Exception {
		dao.insert("user.insertUserInnerLine", user);
	}


	//ɾ��һ���û���Ϣ����������
	public int delete(User user) throws Exception {
		return dao.delete("user.delByUser", user);
	}
	
	//����Ψһ�û���ʶ����ȡһ���û���Ϣ
	public User getUser(String uid) throws Exception {
		return (User)dao.queryForObject("user.getUserByUid", uid);
	}
	
	//��ȡ���е��û���Ϣ
	public List<User> getAllUsers() throws Exception {
		List<User> users = dao.queryForList("user.getAllUsers");
		return users;
	}
	
	//�޸��û���Ϣ
	public void update(User user) throws Exception {
		dao.update("user.updateUser", user);
	}
	
	//������������.
	public void batchAdd(List<User> users) throws Exception {
		dao.insert("user.batchInsert", users);
	}
	
	//ģ����ѯ
	public User getUserLikeName(String name) throws Exception {
		return (User)dao.queryForObject("user.getUserLike", name);
	}
	
	//����������̬ƴ�Ӳ�ѯ
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
