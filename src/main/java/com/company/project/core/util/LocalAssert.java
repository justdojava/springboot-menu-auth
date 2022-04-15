package com.company.project.core.util;

import com.company.project.core.execption.CommonException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * 断言
 */
public abstract class LocalAssert extends Assert{
	
	public static void isTrue(boolean expression, String message) throws CommonException {
		if (!expression) {
			throw new CommonException(message);
		}
	}
	public static void isStringEmpty(String param, String message) throws CommonException{
		if(StringUtils.isEmpty(param)) {
			throw new CommonException(message);
		}
	}

	public static void isObjectEmpty(Object object, String message) throws CommonException {
		if (object == null) {
			throw new CommonException(message);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void isCollectionEmpty(Collection coll, String message) throws CommonException {
		if (coll == null || (coll.size() == 0)) {
			throw new CommonException(message);
		}
	}

}
