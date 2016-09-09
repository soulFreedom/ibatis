package org.guo.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.guo.ibatis.dao.IUserDao;
import org.guo.ibatis.dao.impl.UserDaoImpl;
import org.guo.ibatis.dao.po.Actor;
import org.guo.ibatis.dao.po.Director;
import org.guo.ibatis.dao.po.User;
import org.guo.ibatis.factory.SqlMapClientFactory;
import org.guo.ibatis.handler.XmlRowHandler;
import org.junit.Before;
import org.junit.Test;

import com.ibatis.sqlmap.client.SqlMapClient;


/**
 * 
 * @author Administrator
 *
 */
public class DaoTest {
	private static SqlMapClient sqlMapClient;
	private static Logger logger = Logger.getLogger(DaoTest.class);
	private static IUserDao userDao = new UserDaoImpl();
	@Before
	public void initResouce() throws Exception {
		sqlMapClient = SqlMapClientFactory.getInstance();
	}
	
	@Test
	public void complexSearchTest() throws Exception {
		Object obj = sqlMapClient.queryForObject("user.getUserComplexSearch","32");
		System.err.println(obj.getClass().getName());
		System.err.println(obj);
//		User user = (User)obj;
	/*	Director dirctor = (Director)obj;*/
//		Actor actor = (Actor)obj;
//		System.err.println("actor phone=" + actor.getPhone());
		/*System.err.println("dirctor address" + dirctor.getAddress());
		System.err.println("user name=" + user.getName());*/
	}
	
	//����ѯ��User����ת��Ϊxml
	@Test 
	public void getUsersToXml() throws Exception {
		XmlRowHandler xr = new XmlRowHandler();
		sqlMapClient.queryWithRowHandler("user.getAllUsers", xr);
		System.err.println(xr.getXmlDocument());
	}
	
	//���������
	@Test
	public void batchInsert() throws Exception {
		User user = new User();
		user.setName("��Ұ");
		user.setSex(1);
		user.setAge(48);
		user.setTitle("����ʨ");
		user.setPhone("15100219011");
		user.setAddress("����");
		
		User user1 = new User();
		user1.setName("�Ō�");
		user1.setSex(1);
		user1.setAge(48);
		user1.setTitle("����ʭ");
		user1.setPhone("18100219011");
		user1.setAddress("����");
		
		sqlMapClient.startBatch();
		sqlMapClient.insert("user.insertUserOutLine",user);
		sqlMapClient.insert("user.insertUserOutLine", user1);
		sqlMapClient.executeBatch();
	}
	
	
	
	//���ô洢����.(ִ��ʧ�ܣ�û�ҵ�ԭ��)
	/**
	 * ��MySql��������ֱ�ӵ��ô洢����
	 * 
	 * call bbs.age_from_user(29,@a);
	 * select @a;
	 * 
	 * @throws Exception
	 */
	@Test
	public void getUserAgeUseID() throws Exception{
		Map procParam = new HashMap();
		procParam.put("user_id", 45);
		procParam.put("user_age", 0);
		
		
		procParam = (Map)userDao.getUserAgeUseId(procParam);
		
//		System.err.println(procParam.get("user_id"));
//		System.err.println(procParam.get("user_age"));
		
	}
	
	
	//��̬ƴ��where��� where xx in ('xx','xxx');
	@Test
	public void getUserByRoles() throws Exception{
		List<String> roles = new ArrayList<String>();
		roles.add("����");
		roles.add("����ʨ");
		User user = new User();
		user.setRoles(roles);
		List<User> users = userDao.getUserByRoles(user);
		for (User u : users) {
			System.err.println(u.getName() + "--" + u.getTitle());	
		}
	}
	
	
	//��̬ƴ��where��� ���� & С��
	@Test
	public void getRangeAgeUsers() throws Exception{
		User user = new User();
		user.setAge(30);
		List<User> users = userDao.getRangeAagUser(user);
		for (User u : users) {
			System.err.println(u.getName() + "--" + u.getTitle());			
		}
	}
	
	//��̬ƴ��where����ѯ
	@Test
	public void getUsersDynCondition() throws Exception{
		User user = new User();
		user.setTitle("����");
		user.setName("������");
		
		List<User> users = userDao.getUsersDynConditon(user);
		for (User u : users) {
			System.err.println(u.getName() + "--" + u.getTitle());			
		}
	}
	
	
	//ģ����ѯ
	@Test
	public void getUserLikeName() throws Exception{
		User user = userDao.getUserLikeName("��");
		System.err.println(user.getName() + "--" + user.getTitle());
	}
	
	@Test
	public void updateUser() throws Exception{
		User user = new User();
		user.setId(30);
		user.setName("������");
		user.setTitle("����");
		
		
		userDao.update(user);
	}
	
	//ɾ�����ݣ�������Ӱ��ļ�¼����.
	@Test
	public void delUser() throws Exception{
		User user = new User();
		user.setId(36);
		
		IUserDao userDao = new UserDaoImpl();
		int rows = userDao.delete(user);
		System.err.println(rows);
	}
	
	//�������where������ѯUser����.
	@Test
	public void getUserByUid() throws Exception {
		IUserDao userDao = new UserDaoImpl();
		User user = userDao.getUser("25");
		System.err.println(user.getName());
		
	}
	
	//��ѯ���е��û�List<User>
	@Test
	public void getAllUsers() throws Exception {
		IUserDao userDao = new UserDaoImpl();
		List<User> users = userDao.getAllUsers();
		
		for (User user : users) {
			System.err.println(user.getName());
		}
	}
	
	@Test
	// ������� -������ʽ
	public void addUserOutline() throws Exception {
		User user = new User();
		user.setName("��С��");
		user.setSex(1);
		user.setAge(46);
		user.setTitle("���");
		user.setPhone("15111219011");
		user.setAddress("����");
		
		IUserDao userDao = new UserDaoImpl();
		userDao.addOutLine(user);
		
	}
	
	@Test
	// ������� -������ʽ
	public void addUserInnerline() throws Exception {
		User user = new User();
		user.setName("�ϼѶ�");
		user.setSex(1);
		user.setAge(46);
		user.setTitle("��Ա");
		user.setPhone("15111129011");
		user.setAddress("������");
		
		IUserDao userDao = new UserDaoImpl();
		userDao.addInnerLine(user);
		
	}
	
	@Test
	// ������� -���ز��������������
	/**
	 * last_insert_id()���ַ�ʽ�Ƿ�ȫ�� �ڷ������ֵ֮ǰ�Ƿ��������һ����¼�������ݿ�
	 * ���ص�������һ����¼��id.
	 * @throws Exception
	 */
	public void insertAndGetPk() throws Exception {
		User user = new User();
		user.setName("�ϼѶ�");
		user.setSex(1);
		user.setAge(46);
		user.setTitle("��Ա");
		user.setPhone("15111129011");
		user.setAddress("������");
		
		IUserDao userDao = new UserDaoImpl();
		System.err.println(userDao.addGetPk(user));
	}
	
	//������������
	@Test
	public void testPkDiff() throws Exception {
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setName("��С��");
		user.setSex(1);
		user.setAge(46);
		user.setTitle("���");
		user.setPhone("15111219011");
		user.setAddress("����");
		users.add(user);
		
		User user1 = new User();
		user1.setName("�ϼѶ�");
		user1.setSex(1);
		user1.setAge(46);
		user1.setTitle("��Ա");
		user1.setPhone("15111129011");
		user1.setAddress("������");
		users.add(user1);
		
		IUserDao userDao = new UserDaoImpl();
		userDao.batchAdd(users);
	}
}
