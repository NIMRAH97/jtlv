package edu.wis.jtlv.env.ui;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import edu.wis.jtlv.env.ui.editors.fds.FDSPartitionScanner;
import edu.wis.jtlv.env.ui.editors.smv.SMVPartitionScanner;
import edu.wis.jtlv.env.ui.editors.spc.SPCPartitionScanner;

/**
 * The activator class controls the plug-in life cycle
 */
public class JTLVActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "JTLV";

	public final static String JTLV_HOME = "JTLV_HOME";

	// The shared instance
	private static JTLVActivator plugin;

	/**
	 * The constructor
	 */
	public JTLVActivator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	// static {
	// startJTLVNature();
	// }
	//	
	// private static void startJTLVNature() {
	// IProject[] all_project = ResourcesPlugin.getWorkspace().getRoot()
	// .getProjects();
	// for (IProject project : all_project) {
	// try {
	// IProjectDescription description = project.getDescription();
	// String[] natures = description.getNatureIds();
	//
	// // Add the nature
	// String[] newNatures = new String[natures.length + 1];
	// System.arraycopy(natures, 0, newNatures, 0, natures.length);
	// newNatures[natures.length] = JTLVNature.NATURE_ID;
	// description.setNatureIds(newNatures);
	// project.setDescription(description, null);
	// } catch (CoreException e) {
	// }
	// }
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static JTLVActivator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////

	public final static String SMV_PARTITIONING = "___SMV__partitioning____";

	private SMVPartitionScanner fsmvPartitionScanner;

	public SMVPartitionScanner getSMVPartitionScanner() {
		if (fsmvPartitionScanner == null)
			fsmvPartitionScanner = new SMVPartitionScanner();
		return fsmvPartitionScanner;
	}

	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////

	public final static String FDS_PARTITIONING = "___FDS__partitioning____";

	private FDSPartitionScanner ffdsPartitionScanner;

	public FDSPartitionScanner getFDSPartitionScanner() {
		if (ffdsPartitionScanner == null)
			ffdsPartitionScanner = new FDSPartitionScanner();
		return ffdsPartitionScanner;
	}

	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////

	public final static String SPC_PARTITIONING = "___SPC__partitioning____";

	private SPCPartitionScanner fspcPartitionScanner;

	public SPCPartitionScanner getSPCPartitionScanner() {
		if (fspcPartitionScanner == null)
			fspcPartitionScanner = new SPCPartitionScanner();
		return fspcPartitionScanner;
	}

	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	

	public static final IPath ICONS_PATH= new Path("./icons/"); 
	public static final ImageDescriptor NEW_PROJ_WIZ = createImageDescriptor("new_proj_wiz.png");


	
	public static ImageDescriptor createImageDescriptor(String name) {
		IPath path= ICONS_PATH.append(name);
		Bundle bundle = Platform.getBundle("JTLV");
		URL url= FileLocator.find(bundle, path, null);
		if (url != null) {
			return ImageDescriptor.createFromURL(url);
		}
		return null;
	}
}
