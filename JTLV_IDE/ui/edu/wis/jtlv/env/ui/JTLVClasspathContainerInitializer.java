package edu.wis.jtlv.env.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.ClasspathContainerInitializer;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.osgi.framework.Bundle;

import edu.wis.jtlv.env.Env;

public class JTLVClasspathContainerInitializer extends
		ClasspathContainerInitializer {

	@Override
	public void initialize(IPath containerPath, IJavaProject project)
			throws CoreException {
		IJavaProject[] ijp = new IJavaProject[] { project };
		IClasspathContainer[] icc = new IClasspathContainer[] { new JTLVClasspathContainer(
				getJTLVClasspathEntries()) };
		JavaCore.setClasspathContainer(containerPath, ijp, icc, null);

	}

	private final static String[] jar_lib_names = {
			"jtlv-core" + Env.version + ".jar", "antlr-runtime-3.0.1.jar",
			"javabdd-2.0.jar", "jdd.jar", "jtlv-api" + Env.version + ".jar" };

	private IClasspathEntry[] getJTLVClasspathEntries() {
		Bundle bundle = Platform.getBundle("JTLV");
		if (bundle == null)
			return null;

		Vector<IClasspathEntry> res_vec = new Vector<IClasspathEntry>(6);
		try {
			URL local = FileLocator.toFileURL(bundle.getEntry("/"));
			String install_path = new File(local.getPath()).getAbsolutePath();

			for (int i = 0; i < jar_lib_names.length; i++) {
				IPath lib = new Path(install_path + "/resources/"
						+ jar_lib_names[i]);
				res_vec.add(JavaCore.newLibraryEntry(lib, null, null));
			}
		} catch (IOException e) {
		}
		IClasspathEntry[] res = new IClasspathEntry[res_vec.size()];
		res_vec.toArray(res);
		return res;
	}

	private final static String[] dynamic_lib_names = { "libbuddy64.so",
			"libbuddy.so", "buddy.dll", "libbuddy.jnilib", "libcal.so",
			"libcal64.so", "cal.dll", "libcal.jnilib", "libcudd.so",
			"libcudd64.so", "cudd.dll", "libcudd.jnilib" };

	public void addJTLVExternalClasspathEntries(IProject project) {
		URL local;
		try {
			Bundle bundle = Platform.getBundle("JTLV");
			if (bundle == null)
				return;
			local = FileLocator.toFileURL(bundle.getEntry("/"));
		} catch (IOException e1) {
			return;
		}

		String install_path = new File(local.getPath()).getAbsolutePath();
		IFolder newFolder = project.getFolder("JTLV_DLIB");
		try {
			newFolder.create(true, true, null);
			newFolder.getResourceAttributes().setReadOnly(true);
			newFolder.getResourceAttributes().setHidden(true);
			for (int i = 0; i < dynamic_lib_names.length; i++) {
				try {
					IFile newLib0 = newFolder.getFile(dynamic_lib_names[i]);
					FileInputStream fileStream = new FileInputStream(
							install_path + "/resources/JTLV_DLIB/"
									+ dynamic_lib_names[i]);
					newLib0.create(fileStream, true, null);
				} catch (IOException e) {
				} catch (CoreException e) {
				}
			}
		} catch (CoreException e1) {
		}
	}

	public void removeJTLVExternalClasspathEntries(IProject project) {
		Bundle bundle = Platform.getBundle("JTLV");
		if (bundle == null)
			return;
		IFolder rmFol = project.getFolder("JTLV_DLIB");
		try {
			rmFol.delete(true, null);
		} catch (CoreException e) {
		}
		// for (int i = 0; i < dynamic_lib_names.length; i++) {
		// try {
		// IFile rmLib = project.getFile(dynamic_lib_names[i]);
		// rmLib.delete(true, null);
		// } catch (CoreException e) {
		// }
		// }
	}

	private static class JTLVClasspathContainer implements IClasspathContainer {

		/** classpath entries */
		private final IClasspathEntry[] fEntries;

		public JTLVClasspathContainer(final IClasspathEntry[] entries) {
			this.fEntries = entries;
		}

		public IClasspathEntry[] getClasspathEntries() {
			return this.fEntries;
		}

		public String getDescription() {
			return "JTLV Library";
		}

		public int getKind() {
			return IClasspathContainer.K_APPLICATION;
		}

		public IPath getPath() {
			return new Path("JTLV.classpathContainerInitializer");
		}

	}
}
