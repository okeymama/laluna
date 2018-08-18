package com.universe.laluna.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

public class LalunaBeanUtil extends BeanUtils{

	@SuppressWarnings("unchecked")
	public static <T> List<T> copyEntityListToDTOList(Set set,Class clazz) throws InstantiationException, IllegalAccessException {
		List<T> dtoList = null;
		if(!CollectionUtils.isEmpty(set)) {
			dtoList = new ArrayList<>();
			for(Object srcObj : set) {
				T dtoBean = (T) clazz.newInstance();
				BeanUtils.copyProperties(srcObj, dtoBean);
				dtoList.add(dtoBean);
			}
		}
		return dtoList;
	}
	
}
