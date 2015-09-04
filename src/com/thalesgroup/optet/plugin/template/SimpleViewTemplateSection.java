/*
 *		OPTET Factory
 *
 *	Class PluginHelper 1.0 7 nov. 2013
 *
 *	Copyright (c) 2013 Thales Communications & Security SAS
 *	4, Avenue des Louvresses - 92230 Gennevilliers 
 *	All rights reserved
 *
 */
package com.thalesgroup.optet.plugin.template;

import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginModelFactory;
import org.eclipse.pde.core.plugin.IPluginReference;
import org.eclipse.pde.internal.core.bundle.BundlePluginBase;
import org.eclipse.pde.internal.core.ibundle.IBundle;
import org.eclipse.pde.internal.ui.IHelpContextIds;
//import org.eclipse.pde.internal.ui.wizards.templates.PluginReference;
import org.eclipse.pde.ui.IFieldData;
import org.eclipse.pde.ui.templates.OptionTemplateSection;
import org.eclipse.pde.ui.templates.PluginReference;
import org.eclipse.pde.ui.templates.TemplateOption;
import org.eclipse.swt.widgets.Composite;


public class SimpleViewTemplateSection extends OptionTemplateSection {

	private static final String KEY_CLASS_NAME = "className";

	private static final String KEY_LIST_NAME = "listName";

	private String packageName = null;

	
	ViewOption viewOptionField;
	
	public SimpleViewTemplateSection() {
		super();
		setPageCount(2);
		createOptions();
	}


	@SuppressWarnings("restriction")
	@Override
	public void execute(IProject project, IPluginModelBase model,
			IProgressMonitor monitor) throws CoreException {
		IPluginBase pluginBase = model.createPluginBase();
		if (pluginBase instanceof BundlePluginBase) {
			IBundle bundle = ((BundlePluginBase) pluginBase).getBundle();
			bundle.setHeader("Import-Package", "org.eclipse.core.resources, org.osgi.service.event;version=\"1.3.0\"");
			model.createPluginBase().getId();
//			String packageName = getFormattedPackageName(
//					model.getPluginBase().getId()
//					);
//			bundle.setHeader(
//					"Export-Package",
//					packageName + ", " +
//							packageName + ".business"
//					);
		}
		super.execute(project, model, monitor);
	}

	private void createOptions() {		
		addOption(KEY_CLASS_NAME, "Class Name ", "tool class name", 0);
		viewOptionField = new ViewOption(this, "ViewOption", "view option");
		this.registerOption(viewOptionField, "MyViewOption", 1);
	}

	
	public void addPages(Wizard wizard) {
		WizardPage page1 = createPage(0, IHelpContextIds.TEMPLATE_VIEW);
		page1.setTitle("Simple View Template");
		page1.setDescription("Creates a simple view");
		wizard.addPage(page1);
		WizardPage page2 = createPage(1, IHelpContextIds.TEMPLATE_VIEW);
		page2.setTitle("2Simple View Template2");
		page2.setDescription("2Creates a simple view2");
		wizard.addPage(page2);
		markPagesAdded();
	}

	/**
	 * This is the folder relative to your install url and template directory
	 * where the templates are stored.
	 */
	public String getSectionId() {
		return "viewtemplate";
	}

	protected void initializeFields(IFieldData data) {
		String id = data.getId();
		initializeOption(KEY_PACKAGE_NAME, getFormattedPackageName(id));
		this.packageName = getFormattedPackageName(id);
	}

	/**
	 * Validate your options here!
	 */
	public void validateOptions(TemplateOption changed) {
		// TODO Auto-generated method stub
		System.out.println(changed.getName() + "   " + changed.getValue());

	}


	protected void updateModel(IProgressMonitor monitor) throws CoreException {
		IPluginBase plugin = model.getPluginBase();
		IPluginModelFactory factory = model.getPluginFactory();

		// org.eclipse.core.runtime.applications
		IPluginExtension extension = createExtension("org.eclipse.ui.startup", true);

		IPluginElement element = factory.createElement(extension);
		element.setName("startup");

		String fullClassName = 
				getStringOption(KEY_PACKAGE_NAME)+".Startup";

		element.setAttribute("class", fullClassName);
		extension.add(element);

		plugin.add(extension);
	}



	public String getUsedExtensionPoint() {
		return "org.eclipse.ui.startup";
	}

	/**
	 * The location of your plugin supplying the template content
	 */
	protected URL getInstallURL() {
		return Activator.getDefault().getBundle().getEntry("/");
	}

	protected ResourceBundle getPluginResourceBundle() {
		return Platform.getResourceBundle(Activator.getDefault().getBundle());
	}

	/**
	 * You can use this method to add files relative to your section id
	 */
	public String[] getNewFiles() {
		return new String[0];
	}


	public IPluginReference[] getDependencies(String schemaVersion) {
		ArrayList<PluginReference> result = new ArrayList<PluginReference>();

		result.add(new PluginReference("com.thalesgroup.optet.common;bundle-version=\"1.0.0\"", null, 0)); //$NON-NLS-1$
		result.add(new PluginReference("org.eclipse.core.runtime", null, 0)); //$NON-NLS-1$
		result.add(new PluginReference("org.eclipse.ui", null, 0)); //$NON-NLS-1$
		result.add(new PluginReference("com.thalesgroup.optet.twmanagement;bundle-version=\"1.0.0\"", null, 0)); //$NON-NLS-1$


		return (IPluginReference[]) result.toArray(
				new IPluginReference[result.size()]);
	}

	public boolean isDependentOnParentWizard() {
		return true;
	}

	public int getNumberOfWorkUnits() {
		return super.getNumberOfWorkUnits() + 1;
	}

	public String getStringOption(String name) {
		
		System.out.println("getStringoption " + name);
		if (name.equals(KEY_PACKAGE_NAME)) {
			return packageName;
		}
		
		System.out.println("super.getStringOption(name)" + super.getStringOption(name) );
		
		if (name.equals(KEY_LIST_NAME)) {
			return viewOptionField.getEvidence();
		}
		
		return super.getStringOption(name);
	}

	protected String getFormattedPackageName(String id){
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < id.length(); i++) {
			char ch = id.charAt(i);
			if (buffer.length() == 0) {
				if (Character.isJavaIdentifierStart(ch))
					buffer.append(Character.toLowerCase(ch));
			} else {
				if (Character.isJavaIdentifierPart(ch) || ch == '.')
					buffer.append(ch);
			}
		}
		return buffer.toString().toLowerCase(Locale.ENGLISH);
	}


	public String getDescription() {
		return "Creates an Optet Analysis plugin";
	}

	public String getLabel() {
		return "Optet Analysis plugin template";
	}


}
