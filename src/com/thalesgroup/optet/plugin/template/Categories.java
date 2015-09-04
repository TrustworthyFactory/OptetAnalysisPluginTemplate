/*
 *		OPTET Factory
 *
 *	Class Categories 1.0 25 août 2014
 *
 *	Copyright (c) 2013 Thales Communications & Security SAS
 *	4, Avenue des Louvresses - 92230 Gennevilliers 
 *	All rights reserved
 *
 */

package com.thalesgroup.optet.plugin.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author F. Motte
 *
 */
public class Categories {

	public Categories(String name) {
		super();
		this.name = name;
	}
	private String name;
	private int sort;
	private Map<String, Category> categories = new HashMap<String, Category>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public Map<String, Category> getCategories() {
		return categories;
	}
	public void setCategories(Map<String, Category> categories) {
		this.categories = categories;
	}



}
