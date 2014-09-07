package edu.wis.jtlv.env.ui;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

import edu.wis.jtlv.env.ui.editors.smv.SMVBuilder;
import edu.wis.jtlv.env.ui.editors.spc.SPCBuilder;

public class JTLVNature implements IProjectNature {

	public JTLVNature() {
	}

	/**
	 * ID of this project nature
	 */
	public static final String NATURE_ID = "JTLV.JTLVNature";

	private IProject project;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IProjectNature#configure()
	 */
	public void configure() throws CoreException {
		addJTLVLibrary(getProject());

		IProjectDescription desc = getProject().getDescription();
		ICommand[] commands = desc.getBuildSpec();

		boolean smv_in = false;
		boolean spc_in = false;
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(SMVBuilder.BUILDER_ID))
				smv_in = true;
			if (commands[i].getBuilderName().equals(SPCBuilder.BUILDER_ID))
				spc_in = true;
		}
		if (smv_in & spc_in)
			return;

		if (!smv_in) {
			ICommand[] newCommands = new ICommand[commands.length + 1];
			System.arraycopy(commands, 0, newCommands, 1, commands.length);
			ICommand command = desc.newCommand();
			command.setBuilderName(SMVBuilder.BUILDER_ID);
			newCommands[0] = command;
			commands = newCommands;
			desc.setBuildSpec(newCommands);
			getProject().setDescription(desc, null);
		}

		if (!spc_in) {
			ICommand[] newCommands = new ICommand[commands.length + 1];
			System.arraycopy(commands, 0, newCommands, 1, commands.length);
			ICommand command = desc.newCommand();
			command.setBuilderName(SPCBuilder.BUILDER_ID);
			newCommands[0] = command;
			desc.setBuildSpec(newCommands);
			getProject().setDescription(desc, null);
		}
		
		// finally rebuilding
		getProject().build(IncrementalProjectBuilder.FULL_BUILD, null);

		try {
			getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
		}
		
		//SafeRunner.run(getSafeRunnable(trigger, args, status, monitor));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IProjectNature#deconfigure()
	 */
	public void deconfigure() throws CoreException {
		removeJTLVLibrary(getProject());

		getProject().deleteMarkers(SMVBuilder.MARKER_TYPE, true,
				IResource.DEPTH_INFINITE);
		getProject().deleteMarkers(SPCBuilder.MARKER_TYPE, true,
				IResource.DEPTH_INFINITE);
		IProjectDescription description = getProject().getDescription();
		ICommand[] commands = description.getBuildSpec();
		boolean removed_smv = false;
		boolean removed_spc = false;
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(SMVBuilder.BUILDER_ID)) {
				ICommand[] newCommands = new ICommand[commands.length - 1];
				System.arraycopy(commands, 0, newCommands, 0, i);
				System.arraycopy(commands, i + 1, newCommands, i,
						commands.length - i - 1);
				description.setBuildSpec(newCommands);

				// finally rebuilding
				getProject().build(IncrementalProjectBuilder.FULL_BUILD, null);
				removed_smv = true;
			}
			if (commands[i].getBuilderName().equals(SPCBuilder.BUILDER_ID)) {
				ICommand[] newCommands = new ICommand[commands.length - 1];
				System.arraycopy(commands, 0, newCommands, 0, i);
				System.arraycopy(commands, i + 1, newCommands, i,
						commands.length - i - 1);
				description.setBuildSpec(newCommands);

				// finally rebuilding
				getProject().build(IncrementalProjectBuilder.FULL_BUILD, null);
				removed_spc = true;
			}
			if (removed_smv & removed_spc)
				break;
		}
		// deleting SPC markers in any case...
		SPCBuilder.deleteMarkers();

		try {
			getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IProjectNature#getProject()
	 */
	public IProject getProject() {
		return project;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IProjectNature#setProject(org.eclipse.core.resources.IProject)
	 */
	public void setProject(IProject project) {
		this.project = project;
		// building when the project is set
		try {
			this.project.build(IncrementalProjectBuilder.FULL_BUILD, null);
		} catch (CoreException e) {
		}
		// // when coming to set the project, I'm checking if it has already the
		// project nature.
		// IProjectDescription description =
		// JavaCore.create(project).getDescription();
		// String[] natures = description.getNatureIds();
		//
		// for (int i = 0; i < natures.length; ++i) {
		// if (JTLVNature.NATURE_ID.equals(natures[i])) {
		// // Remove the nature
		// String[] newNatures = new String[natures.length - 1];
		// System.arraycopy(natures, 0, newNatures, 0, i);
		// System.arraycopy(natures, i + 1, newNatures, i, natures.length
		// - i - 1);
		// description.setNatureIds(newNatures);
		// project.setDescription(description, null);
		// return;
		// }
		// }

	}

	private void removeJTLVLibrary(IProject project) {
		// here i use the version 4
		IPath path = new Path("JTLV.classpathContainerInitializer");
		IJavaProject ijp = JavaCore.create(project);

		try {
			IClasspathEntry[] old_containers = ijp.getRawClasspath();
			IClasspathEntry[] all_containers = new IClasspathEntry[old_containers.length - 1];
			int find_idx = -1;
			for (int i = 0; i < old_containers.length; i++)
				if (old_containers[i].getPath().equals(path)) {
					find_idx = i;
					break;
				}
			if (find_idx == -1)
				return;
			for (int i = 0; i < find_idx; i++)
				all_containers[i] = old_containers[i];
			for (int i = find_idx + 1; i < all_containers.length; i++)
				all_containers[i] = old_containers[i + 1];
			ijp.setRawClasspath(all_containers, true, null);
			// /////////
			getInitializer().removeJTLVExternalClasspathEntries(project);
		} catch (CoreException e) {
			// e.printStackTrace();
		}
	}

	private JTLVClasspathContainerInitializer getInitializer() {
		IPath path = new Path("JTLV.classpathContainerInitializer");
		JTLVClasspathContainerInitializer initializer = (JTLVClasspathContainerInitializer) JavaCore
				.getClasspathContainerInitializer(path.segment(0));
		return initializer;

	}

	private void addJTLVLibrary(IProject project) {
		IPath path = new Path("JTLV.classpathContainerInitializer");
		JTLVClasspathContainerInitializer initializer = getInitializer();

		IJavaProject ijp = JavaCore.create(project);
		try {
			initializer.initialize(path, ijp);
			IClasspathEntry a_container = JavaCore.newContainerEntry(path);
			IClasspathEntry[] old_containers = ijp.getRawClasspath();
			IClasspathEntry[] all_containers = new IClasspathEntry[old_containers.length + 1];
			for (int i = 0; i < old_containers.length; i++) {
				all_containers[i] = old_containers[i];
			}
			all_containers[old_containers.length] = a_container;
			ijp.setRawClasspath(all_containers, true, null);
			// /////////
			initializer.addJTLVExternalClasspathEntries(project);
		} catch (CoreException e) {
			// e.printStackTrace();
		}

	}
}
