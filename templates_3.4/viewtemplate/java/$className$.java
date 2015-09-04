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
package $packageName$;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ISelection;
import com.thalesgroup.optet.twmanagement.Evidence;

public class $className$ extends AuditToolUtil {
	
	public $className$(IProject iProject, List<IFile> files) {
		super(iProject, files);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void runAudit(IProject project, List<Evidence> evidences) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void runMetric(IProject project) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void runRuntimeAnalysis(IProject project) throws Exception {
		// TODO Auto-generated method stub
		
	}
}