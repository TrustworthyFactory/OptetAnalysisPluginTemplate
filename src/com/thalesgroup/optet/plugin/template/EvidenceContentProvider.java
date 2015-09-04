package com.thalesgroup.optet.plugin.template;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;


public class EvidenceContentProvider implements ITreeContentProvider {

  private EvidenceMockModel model;

  @Override
  public void dispose() {
  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    this.model = (EvidenceMockModel) newInput;
  }

  @Override
  public Object[] getElements(Object inputElement) {
	  System.out.println("getelements" + model.getCategories().toArray().length);
    return model.getCategories().toArray();

  }

  @Override
  public Object[] getChildren(Object parentElement) {
	  System.out.println("getChildren");
	    if (parentElement instanceof Categories) {
	    	Categories categories = (Categories) parentElement;
	        return categories.getCategories().values().toArray();
	      }
	    if (parentElement instanceof Category) {
	        Category category = (Category) parentElement;
	        return category.getMetrics().values().toArray();
	      }
    return null;
  }

  @Override
  public Object getParent(Object element) {
    return null;
  }

  @Override
  public boolean hasChildren(Object element) {
	  System.out.println("haschildren");
	    if (element instanceof Categories) {
	        return true;
	      }
	    if (element instanceof Category) {
	        return true;
	      }
    return false;
  }

} 