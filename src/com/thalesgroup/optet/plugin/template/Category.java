package com.thalesgroup.optet.plugin.template;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Category {
	private String name;
	private int sort;
	private Map<String, Evidence> evidences = new HashMap<String, Evidence>();
	private Categories categories;

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

	public Map<String, Evidence> getMetrics() {
		return evidences;
	}

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public Category(String name, Categories categories) {
		super();
		this.name = name;
		this.categories = categories;
	}
} 