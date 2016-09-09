package org.guo.ibatis.handler;

import org.guo.ibatis.dao.po.User;

import com.ibatis.sqlmap.client.event.RowHandler;

/**
 * 将查询出的对象组织为xml报文格式
 * @author Administrator
 *
 */
public class XmlRowHandler implements RowHandler {
	//为方便演示:此处用StringBuilder组织的数据没有主子层次,实际应用中可以用Element等专用Api组织数据.
	StringBuilder xmlDocument = new StringBuilder("<UserList>");
	public void handleRow(Object arg0) {
		xmlDocument.append("\n");
		User u = (User)arg0;
		xmlDocument.append("    <User>");
		xmlDocument.append("<ID>");
		xmlDocument.append(u.getId());
		xmlDocument.append("</ID>");
		xmlDocument.append("<Name>");
		xmlDocument.append(u.getName());
		xmlDocument.append("</Name>");
		xmlDocument.append("<Title>");
		xmlDocument.append(u.getTitle());
		xmlDocument.append("</Title>");
		xmlDocument.append("\n");
	}
	
	public String getXmlDocument() {
		xmlDocument.append("</UserList>");
		return xmlDocument.toString();
	}

}
