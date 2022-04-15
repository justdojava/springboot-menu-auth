/**
 * 
 */
package com.company.project.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class BeanToMapUtil {

	private static final Logger log = LoggerFactory.getLogger(BeanToMapUtil.class);

	/**
	 * map转bean
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> beanToMap(Object bean) {
		if (bean == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(bean);
					map.put(key, value);
				}
			}
		} catch (Exception e) {
			log.error("对象转Map失败",e);
		}
		return map;
	}

	/**
	 * bean转map
	 * @param map
	 * @param obj
	 */
	public static void mapToBean(Map<String, Object> map, Object obj)  {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (map.containsKey(key)) {
					Object value = map.get(key);
					// 得到property对应的setter方法
					Method setter = property.getWriteMethod();
					setter.invoke(obj, value);
				}
			}
		} catch (Exception e) {
			log.error("map转对象失败",e);
		}
	}

	
//	public static void main(String[] args) {
//		Dept dept = new Dept();
//		dept.setDeptId(1l);
//		dept.setDeptName("技术部");
//		Map map = beanToMap(dept);
//		System.out.println("对象转Map：" + JSONObject.toJSONString(map));
//		Dept newDept = new Dept();
//		mapToBean(map, newDept);
//		System.out.println("map转对象：" + JSONObject.toJSONString(newDept));
//	}
}
