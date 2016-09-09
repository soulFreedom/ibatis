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
	
	//将查询的User对象转换为xml
	@Test 
	public void getUsersToXml() throws Exception {
		XmlRowHandler xr = new XmlRowHandler();
		sqlMapClient.queryWithRowHandler("user.getAllUsers", xr);
		System.err.println(xr.getXmlDocument());
	}
	
	//批处理添加
	@Test
	public void batchInsert() throws Exception {
		User user = new User();
		user.setName("张野");
		user.setSex(1);
		user.setAge(48);
		user.setTitle("攻城狮");
		user.setPhone("15100219011");
		user.setAddress("辽宁");
		
		User user1 = new User();
		user1.setName("张尨");
		user1.setSex(1);
		user1.setAge(48);
		user1.setTitle("攻城虱");
		user1.setPhone("18100219011");
		user1.setAddress("北京");
		
		sqlMapClient.startBatch();
		sqlMapClient.insert("user.insertUserOutLine",user);
		sqlMapClient.insert("user.insertUserOutLine", user1);
		sqlMapClient.executeBatch();
	}
	
	
	
	//调用存储过程.(执行失败，没找到原因)
	/**
	 * 在MySql命令行中直接调用存储过程
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
	
	
	//动态拼接where语句 where xx in ('xx','xxx');
	@Test
	public void getUserByRoles() throws Exception{
		List<String> roles = new ArrayList<String>();
		roles.add("导演");
		roles.add("攻城狮");
		User user = new User();
		user.setRoles(roles);
		List<User> users = userDao.getUserByRoles(user);
		for (User u : users) {
			System.err.println(u.getName() + "--" + u.getTitle());	
		}
	}
	
	
	//动态拼接where语句 大于 & 小于
	@Test
	public void getRangeAgeUsers() throws Exception{
		User user = new User();
		user.setAge(30);
		List<User> users = userDao.getRangeAagUser(user);
		for (User u : users) {
			System.err.println(u.getName() + "--" + u.getTitle());			
		}
	}
	
	//动态拼接where语句查询
	@Test
	public void getUsersDynCondition() throws Exception{
		User user = new User();
		user.setTitle("导演");
		user.setName("康洪雷");
		
		List<User> users = userDao.getUsersDynConditon(user);
		for (User u : users) {
			System.err.println(u.getName() + "--" + u.getTitle());			
		}
	}
	
	
	//模糊查询
	@Test
	public void getUserLikeName() throws Exception{
		User user = userDao.getUserLikeName("康");
		System.err.println(user.getName() + "--" + user.getTitle());
	}
	
	@Test
	public void updateUser() throws Exception{
		User user = new User();
		user.setId(30);
		user.setName("康洪雷");
		user.setTitle("导演");
		
		
		userDao.update(user);
	}
	
	//删除数据，并返回影响的记录条数.
	@Test
	public void delUser() throws Exception{
		User user = new User();
		user.setId(36);
		
		IUserDao userDao = new UserDaoImpl();
		int rows = userDao.delete(user);
		System.err.println(rows);
	}
	
	//根据相关where条件查询User对象.
	@Test
	public void getUserByUid() throws Exception {
		IUserDao userDao = new UserDaoImpl();
		User user = userDao.getUser("25");
		System.err.println(user.getName());
		
	}
	
	//查询所有的用户List<User>
	@Test
	public void getAllUsers() throws Exception {
		IUserDao userDao = new UserDaoImpl();
		List<User> users = userDao.getAllUsers();
		
		for (User user : users) {
			System.err.println(user.getName());
		}
	}
	
	@Test
	// 添加数据 -外联方式
	public void addUserOutline() throws Exception {
		User user = new User();
		user.setName("兰小龙");
		user.setSex(1);
		user.setAge(46);
		user.setTitle("编剧");
		user.setPhone("15111219011");
		user.setAddress("甘肃");
		
		IUserDao userDao = new UserDaoImpl();
		userDao.addOutLine(user);
		
	}
	
	@Test
	// 添加数据 -内联方式
	public void addUserInnerline() throws Exception {
		User user = new User();
		user.setName("邢佳栋");
		user.setSex(1);
		user.setAge(46);
		user.setTitle("演员");
		user.setPhone("15111129011");
		user.setAddress("黑龙江");
		
		IUserDao userDao = new UserDaoImpl();
		userDao.addInnerLine(user);
		
	}
	
	@Test
	// 添加数据 -返回插入的自增主键。
	/**
	 * last_insert_id()这种方式是否安全？ 在返回这个值之前是否会有另外一条记录插入数据库
	 * 返回的是另外一条记录的id.
	 * @throws Exception
	 */
	public void insertAndGetPk() throws Exception {
		User user = new User();
		user.setName("邢佳栋");
		user.setSex(1);
		user.setAge(46);
		user.setTitle("演员");
		user.setPhone("15111129011");
		user.setAddress("黑龙江");
		
		IUserDao userDao = new UserDaoImpl();
		System.err.println(userDao.addGetPk(user));
	}
	
	//批量插入数据
	@Test
	public void testPkDiff() throws Exception {
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setName("兰小龙");
		user.setSex(1);
		user.setAge(46);
		user.setTitle("编剧");
		user.setPhone("15111219011");
		user.setAddress("甘肃");
		users.add(user);
		
		User user1 = new User();
		user1.setName("邢佳栋");
		user1.setSex(1);
		user1.setAge(46);
		user1.setTitle("演员");
		user1.setPhone("15111129011");
		user1.setAddress("黑龙江");
		users.add(user1);
		
		IUserDao userDao = new UserDaoImpl();
		userDao.batchAdd(users);
	}
}
