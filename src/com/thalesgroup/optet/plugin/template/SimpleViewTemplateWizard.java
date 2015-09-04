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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.ui.IFieldData;
import org.eclipse.pde.ui.templates.ITemplateSection;
import org.eclipse.pde.ui.templates.NewPluginTemplateWizard;
import org.eclipse.pde.ui.templates.OptionTemplateSection;

public class SimpleViewTemplateWizard extends NewPluginTemplateWizard {

	
    public void init(IFieldData data) {
        super.init(data);
        setWindowTitle("Optet Simple View Wizard");   

    }

    public ITemplateSection[] createTemplateSections() {
        return new ITemplateSection[] {new SimpleViewTemplateSection()};
    }
}
