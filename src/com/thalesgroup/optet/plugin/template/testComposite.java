package com.thalesgroup.optet.plugin.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class testComposite extends Composite {
	private Text text;
	private Text text_1;


	EvidenceMockModel evidence = new EvidenceMockModel();

	ViewOption viewOption;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 * @param viewOption 
	 */
	public testComposite(Composite parent, int style, final ViewOption viewOption) {
		super(parent, style);

		this.viewOption = viewOption;

		Group group = new Group(this, SWT.NONE);
		group.setBounds(10, 10, 224, 414);

		final CheckboxTreeViewer checkboxTreeViewer = new CheckboxTreeViewer(group, SWT.BORDER);
		Tree tree = checkboxTreeViewer.getTree();
		tree.setBounds(10, 20, 204, 269);

		//		Label lblNewLabel = new Label(group, SWT.NONE);
		//		lblNewLabel.setBounds(10, 308, 49, 13);
		//		lblNewLabel.setText("Category");
		//
		//		text = new Text(group, SWT.BORDER);
		//		text.setBounds(10, 327, 204, 19);
		//
		//		Label lblNewLabel_1 = new Label(group, SWT.NONE);
		//		lblNewLabel_1.setBounds(10, 364, 49, 13);
		//		lblNewLabel_1.setText("Evidence");
		//
		//		text_1 = new Text(group, SWT.BORDER);
		//		text_1.setBounds(10, 383, 204, 19);

		Group group_1 = new Group(this, SWT.NONE);
		group_1.setBounds(318, 10, 224, 404);

		final CheckboxTreeViewer checkboxTreeViewer_1 = new CheckboxTreeViewer(group_1, SWT.BORDER);
		Tree tree_1 = checkboxTreeViewer_1.getTree();
		tree_1.setBounds(10, 20, 204, 373);

		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object[] treeItems= checkboxTreeViewer.getCheckedElements();
				Map<String, Categories> catergoriesMap = new HashMap<String, Categories>();

				for(int i = 0; i < treeItems.length; i++){
					Object itemObject = treeItems[i];

					if (itemObject instanceof Categories) {
						Categories new_name = (Categories) itemObject;
						System.out.println("Categories " + new_name.getName());
						if (!catergoriesMap.containsKey(new_name.getName())){
						
							catergoriesMap.put(new_name.getName(), new_name);
							System.out.println("add categories " + new_name.getName());
						}
					} else if (itemObject instanceof Category) {
						Category new_name = (Category) itemObject;
						System.out.println("Category " + new_name.getName());
						if (!catergoriesMap.containsKey(new_name.getCategories().getName())){
							catergoriesMap.put(new_name.getCategories().getName(), new Categories(new_name.getCategories().getName()));
							System.out.println("add categories " + new_name.getCategories().getName());
						}
						catergoriesMap.get(new_name.getCategories().getName()).getCategories().put(new_name.getName(), new_name);							
						System.out.println("add category " + new_name.getName());
					} else if (itemObject instanceof Evidence) {
						Evidence new_name = (Evidence) itemObject;
						System.out.println("Evidence " + new_name.getSummary());	
						if (!catergoriesMap.containsKey(new_name.getCategory().getCategories().getName())){
							catergoriesMap.put(new_name.getCategory().getCategories().getName(), new Categories(new_name.getCategory().getCategories().getName()));
							System.out.println("add categories " + new_name.getCategory().getCategories().getName());
						}
						if (!catergoriesMap.get(new_name.getCategory().getCategories().getName()).getCategories().containsKey(new_name.getCategory().getName())){
							Category ct = new Category(new_name.getCategory().getName(),new_name.getCategory().getCategories());
							catergoriesMap.get(new_name.getCategory().getCategories().getName()).getCategories().put(ct.getName(), ct);
							System.out.println("add category " + new_name.getCategory().getName());
							
						}
						
						catergoriesMap.get(new_name.getCategory().getCategories().getName()).getCategories().get(new_name.getCategory().getName()).getMetrics().put(new_name.getSummary(), new_name);

						System.out.println("add evidence " + new_name.getSummary() );

					} 
				}
				evidence.setCategories(new ArrayList(catergoriesMap.values()));
				checkboxTreeViewer_1.setInput(evidence);
				checkboxTreeViewer_1.setGrayChecked(evidence, true);
				viewOption.setEvidences(evidence.toXML());

			}
		});
		btnNewButton.setBounds(240, 209, 68, 23);
		btnNewButton.setText("Add >>");

		Button button = new Button(this, SWT.NONE);
		button.setText("<< Remove all");
		button.setBounds(240, 265, 68, 23);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object[] treeItems= checkboxTreeViewer_1.getCheckedElements();
				List<Categories> evidenceList = evidence.getCategories();
				Map<String, Category> catergoryMap = new HashMap<String, Category>();
				evidence.setCategories(new ArrayList(catergoryMap.values()));
				checkboxTreeViewer_1.setInput(evidence);
				viewOption.setEvidences(evidence.toXML());
			}
		});


		checkboxTreeViewer.setContentProvider(new EvidenceContentProvider());
		checkboxTreeViewer.setLabelProvider(new EvidenceLabelProvider());
		// Expand the tree
		checkboxTreeViewer.setAutoExpandLevel(3);
		// provide the input to the ContentProvider
		EvidenceMockModel ev = new EvidenceMockModel();
		ev.init();
		checkboxTreeViewer.setInput(ev);
		checkboxTreeViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				// If the item is checked . . .
				if (event.getChecked()) {
					// . . . check all its children
					checkboxTreeViewer.setSubtreeChecked(event.getElement(), true);
				}
			}
		});


		checkboxTreeViewer_1.setContentProvider(new EvidenceContentProvider());
		checkboxTreeViewer_1.setLabelProvider(new EvidenceLabelProvider());
		// Expand the tree
		checkboxTreeViewer_1.setAutoExpandLevel(3);
		
		// provide the input to the ContentProvider
		checkboxTreeViewer_1.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				// If the item is checked . . .
				if (event.getChecked()) {
					// . . . check all its children
					checkboxTreeViewer.setSubtreeChecked(event.getElement(), true);
				}
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}