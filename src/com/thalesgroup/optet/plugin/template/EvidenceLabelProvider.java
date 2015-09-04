package com.thalesgroup.optet.plugin.template;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;


public class EvidenceLabelProvider extends LabelProvider {

  
  @Override
  public String getText(Object element) {
	    if (element instanceof Categories) {
	    	Categories categories = (Categories) element;
	        return categories.getName();
	      }
	    if (element instanceof Category) {
	        Category category = (Category) element;
	        return category.getName();
	      }
    return ((Evidence) element).getSummary();
  }

  @Override
  public Image getImage(Object element) {

    return null;
  }


} 