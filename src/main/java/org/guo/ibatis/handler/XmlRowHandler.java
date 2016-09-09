package org.guo.ibatis.handler;

import org.guo.ibatis.dao.po.User;

import com.ibatis.sqlmap.client.event.RowHandler;

/**
 * ����ѯ���Ķ�����֯Ϊxml���ĸ�ʽ
 * @author Administrator
 *
 */
public class XmlRowHandler implements RowHandler {
	//Ϊ������ʾ:�˴���StringBuilder��֯������û�����Ӳ��,ʵ��Ӧ���п�����Element��ר��Api��֯����.
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
