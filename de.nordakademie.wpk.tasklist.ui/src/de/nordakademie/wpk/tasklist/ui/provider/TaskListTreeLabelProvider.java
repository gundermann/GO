package de.nordakademie.wpk.tasklist.ui.provider;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.ui.util.TaskHelper;

/**
 * Label-Provider für den Tasklisten-Baum.
 * Für Tasklisten wird der Name der Taskliste angezeigt.
 * Für Tasks wir der Title der Task angezeigt.
 * Bestimmte Elemente haben Icons.
 * @author Niels Gundermann
 *
 */
public class TaskListTreeLabelProvider implements ILabelProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
	}

	/**
	 * Eine Taskliste hat ein spezielles Icon, welches ihre Herkunft (Provider) identifiziert.
	 * Eine Task, die in höchstens drei Tagen fällig wird, erhält ein einsprechendes Icon.
	 */
	@Override
	public Image getImage(Object element) {
		if(element instanceof TaskList){
			Bundle bundle = FrameworkUtil.getBundle(TaskListTreeLabelProvider.class);
		    URL url = FileLocator.find(bundle, new Path("icons/google.ico"), null);
			ImageDescriptor imageDcr = ImageDescriptor.createFromURL(url );
			return imageDcr.createImage();
		}
		else if (element instanceof Task) {
			if(TaskHelper.isTaskDueWithinThreeDays((Task)element)){
				Bundle bundle = FrameworkUtil.getBundle(TaskListTreeLabelProvider.class);
			    URL url = FileLocator.find(bundle, new Path("icons/due.png"), null);
				ImageDescriptor imageDcr = ImageDescriptor.createFromURL(url );
				return imageDcr.createImage();
			}
		}
		return null;
	}

	@Override
	public String getText(Object element) {
		if(element instanceof TaskList)
			return ((TaskList) element).getName();
		else if(element instanceof Task)
			return ((Task) element).getTitle();
		return element.toString();
	}


}
