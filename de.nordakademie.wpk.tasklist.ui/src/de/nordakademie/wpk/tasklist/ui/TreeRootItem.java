package de.nordakademie.wpk.tasklist.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Das Root-Item f�r den Tree, welches einen Liste von TreeTasklistItems
 * enth�lt.
 * 
 * @author Niels Gundermann
 *
 */
public class TreeRootItem {

	List<TreeTasklistsItem> children;

	public TreeRootItem(TreeTasklistsItem child) {
		this.children = new ArrayList<TreeTasklistsItem>();
		this.children.add(child);
	}

	public Object[] getChilden() {
		return children.toArray();
	}

}
