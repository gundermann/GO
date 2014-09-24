package de.nordakademie.wpk.tasklist.ui.provider;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;

public class TaskListTreeLabelProvider implements ILabelProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getImage(Object element) {
		if(element instanceof TaskList){
			Bundle bundle = FrameworkUtil.getBundle(TaskListTreeLabelProvider.class);
		    URL url = FileLocator.find(bundle, new Path("icons/google.ico"), null);
			ImageDescriptor imageDcr = ImageDescriptor.createFromURL(url );
			return imageDcr.createImage();
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
