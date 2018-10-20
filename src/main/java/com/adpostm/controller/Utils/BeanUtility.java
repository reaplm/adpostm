package com.adpostm.controller.Utils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class BeanUtility {

	public static Set<String> getNullPropertyNames(Object source, String... extra){
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor pds[] = src.getPropertyDescriptors();
		
		Set<String> emptyFields = new HashSet<String>();
		for(java.beans.PropertyDescriptor pd:pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if(srcValue == null)
				emptyFields.add(pd.getName());
		}
		
		for(String field:extra)
			emptyFields.add(field);
		
		return emptyFields;
	}
}
