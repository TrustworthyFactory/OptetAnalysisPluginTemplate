package com.thalesgroup.optet.plugin.template;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;


import com.thalesgroup.optet.plugin.template.jaxb.OptetPlugin;

public class EvidenceMockModel  {
	List<Categories> categoriesList;


	public void init(){


		categoriesList = new ArrayList<Categories>();

		Categories categories = new Categories("static");

		Category category = new Category("SoftwareComplexity", categories);
		Evidence evidence = new Evidence("SoftwareComplexityMetric", category);
		category.getMetrics().put(evidence.getSummary(), evidence);

		categories.getCategories().put(category.getName(), category);


		category = new Category("Maintainability", categories);
		categories.getCategories().put(category.getName(), category);
		evidence = new Evidence("MaintainabilityMetric", category);
		category.getMetrics().put(evidence.getSummary(), evidence);



		category = new Category("Reliability", categories);
		categories.getCategories().put(category.getName(), category);
		evidence = new Evidence("ReliabilityMetric", category);
		category.getMetrics().put(evidence.getSummary(), evidence);


		category = new Category("Security",categories);
		categories.getCategories().put(category.getName(), category);
		evidence = new Evidence("SecurityMetric", category);
		category.getMetrics().put(evidence.getSummary(), evidence);

		categoriesList.add(categories);

		categories = new Categories("runtime");

		category = new Category("UnitTest", categories);
		categories.getCategories().put(category.getName(), category);

		category = new Category("CodeCoverage", categories);
		categories.getCategories().put(category.getName(), category);

		categoriesList.add(categories);

	}

	public void setCategories(List<Categories> categories) {
		if (this.categoriesList!=null)
			this.categoriesList.clear();
		this.categoriesList = categories;
	}


	public List<Categories> getCategories() {
		return categoriesList;
	}


	/**
	 * @return
	 */
	public String toXML() {
		OptetPlugin optetPluginJAXB = new OptetPlugin();

		for (Iterator iterator = categoriesList.iterator(); iterator.hasNext();) {
			Categories cates = (Categories) iterator.next();




			com.thalesgroup.optet.plugin.template.jaxb.OptetPlugin.Categories categoriesJAXB = new com.thalesgroup.optet.plugin.template.jaxb.OptetPlugin.Categories();
			categoriesJAXB.setName(cates.getName());
			System.out.println("JAXB " +cates.getName());

			Collection<Category> listcategory = cates.getCategories().values();
			for (Iterator iterator2 = listcategory.iterator(); iterator2
					.hasNext();) {
				Category cat = (Category) iterator2.next();



				com.thalesgroup.optet.plugin.template.jaxb.OptetPlugin.Categories.Category catJAXB = new com.thalesgroup.optet.plugin.template.jaxb.OptetPlugin.Categories.Category();
				catJAXB.setName(cat.getName());
				System.out.println("JAXB " + cat.getName());

				Collection<Evidence> listevidence = cat.getMetrics().values();
				for (Iterator iterator3 = listevidence.iterator(); iterator3
						.hasNext();) {
					Evidence evidence = (Evidence) iterator3.next();
					com.thalesgroup.optet.plugin.template.jaxb.OptetPlugin.Categories.Category.Evidence evidenceJAXB = 
							new com.thalesgroup.optet.plugin.template.jaxb.OptetPlugin.Categories.Category.Evidence();
					evidenceJAXB.setName(evidence.getSummary());
					System.out.println("JAXB " + evidence.getSummary());
					catJAXB.getEvidence().add(evidenceJAXB);

				}
				categoriesJAXB.getCategory().add(catJAXB);

			}
			optetPluginJAXB.getCategories().add(categoriesJAXB);

		}

		JAXBContext jaxbContext;
		StringWriter stringWriter = new StringWriter();
		try {
			jaxbContext = JAXBContext.newInstance(OptetPlugin.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


			jaxbMarshaller.marshal(optetPluginJAXB, stringWriter );

			System.out.println("res " + stringWriter);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return stringWriter.toString();
	}

} 