package edu.wis.jtlv.env.ui.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.ui.wizards.NewJavaProjectWizardPageOne;
import org.eclipse.jdt.ui.wizards.NewJavaProjectWizardPageTwo;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import edu.wis.jtlv.env.ui.JTLVActivator;
import edu.wis.jtlv.env.ui.ToggleNatureAction;

/**
 * This is a new wizard for creating a JTLV project.
 */

public class JTLVProjectWizard extends Wizard implements INewWizard {
	// private OpenNewJavaProjectWizardAction openJavaWizard;
	/**
	 * Constructor for JTLVNewWizard.
	 */
	public JTLVProjectWizard() {
		super();
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(JTLVActivator.NEW_PROJ_WIZ);
	}

	private NewJavaProjectWizardPageOne pageOne;
	private NewJavaProjectWizardPageTwo pageTwo;

	public void addPages() {
		pageOne = new NewJavaProjectWizardPageOne();
		pageTwo = new NewJavaProjectWizardPageTwo(pageOne);

		// adjusting the project
		pageOne.setTitle("Create a JTLV Project");
		pageTwo.setTitle("Java Settings for JTLV Project");

		addPage(pageOne);
		addPage(pageTwo);

	}

	@Override
	public boolean performFinish() {
		// making sure that the second page will instantiate the project
		// pageTwo.setVisible(true);
		try {
			pageTwo.performFinish(new NullProgressMonitor());
		} catch (CoreException e) {
			return false;
		} catch (InterruptedException e) {
			return false;
		}

		// Add JTLV nature
		IProject project = pageTwo.getJavaProject().getProject();
		ToggleNatureAction.toggleJTLVNature(project);

		return true;
	}

	@Override
	public boolean performCancel() {
		pageTwo.performCancel();
		return super.performCancel();
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}
}