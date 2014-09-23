package de.nordakademie.wpk.tasklist.ui;

import java.util.ArrayList;
import java.util.List;


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
