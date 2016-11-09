package com.huituopin.wechat.core.utils;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.huituopin.wechat.entity.ReceiveXmlEntity;


/*解析xml消息，返回到消息实体*/
public class ParseReceiveXml {
	
	public static ReceiveXmlEntity getMsgEntity(HttpServletRequest request) {
		ReceiveXmlEntity msg = null;
		try {
			InputStream inputStream = request.getInputStream();
			SAXReader reader = new SAXReader();
			// 从流中获得XML文档对象
			Document document = reader.read(inputStream);
			// 获得文档的根节点
			Element root = document.getRootElement();
			// 遍历根节点下所有子节点
			Iterator<?> iter = root.elementIterator();
			// 遍历所有结点
			msg = new ReceiveXmlEntity();
			// 利用反射机制，调用set方法
			// 获取该实体的元类型
			Class<?> c = Class.forName("entity.ReceiveXmlEntity");
			msg = (ReceiveXmlEntity) c.newInstance();// 创建这个实体的对象
			while (iter.hasNext()) {
				Element ele = (Element) iter.next();
				// 获取set方法中的参数字段（实体类的属性）
				Field field = c.getDeclaredField(ele.getName());
				// 获取set方法，field.getType())获取它的参数数据类型
				Method method = c.getDeclaredMethod("set" + ele.getName(),
						field.getType());
				// 调用set方法
				method.invoke(msg, ele.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
}
