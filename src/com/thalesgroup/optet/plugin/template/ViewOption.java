/**
 * 
 */
package com.thalesgroup.optet.plugin.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.pde.ui.templates.BaseOptionTemplateSection;
import org.eclipse.pde.ui.templates.TemplateOption;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

/**
 * @author Marine
 *
 */
public class ViewOption extends TemplateOption {

	
	private List listField;

	private Text text;
	private Text text_1;


	EvidenceMockModel evidence = new EvidenceMockModel();

	private String evidences;
	
	public ViewOption(BaseOptionTemplateSection section, String name,
			String label) {
		super(section, name, label);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.TemplateField#createControl(org.eclipse.swt.widgets.Composite, int)
	 */
	@Override
	public void createControl(Composite parent, int span) {
		parent.setParent(new testComposite(parent, 0, this));
		parent.getShell().setSize(505, 602);
		parent.getShell().setText("Optet Configuration Plug-in");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.TemplateOption#getValue()
	 */
	@Override
	public Object getValue() {
		if (listField != null) {
			String[] items = listField.getItems();
			if (items != null) {
				return items;
			}
		}
		return new String[0];
	}

	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.TemplateOption#getMessageLabel()
	 */
	@Override
	public String getMessageLabel() {
		// TODO Auto-generated method stub
		return "View List";
	}


	/**
	 * @return the listField
	 */
	public List getListField() {
		return listField;
	}

	public void setEvidences(String evidences){
		this.evidences = evidences;
	}
	
	/**
	 * @return
	 */
	public String getEvidence() {
		// TODO Auto-generated method stub
		return evidences;
	}
}
