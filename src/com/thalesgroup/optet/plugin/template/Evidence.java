package com.thalesgroup.optet.plugin.template;
public class Evidence {
	private String summary = "";
	private Category category = null;




	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public Evidence(String summary, Category category) {
		super();
		this.summary = summary;
		this.category = category;
	}



} 