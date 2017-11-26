package com.message.loginmenu.model;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class ArrayListComboBoxModel extends AbstractListModel implements ComboBoxModel{
	private List arraylist;
	private Object selectedItem; 
	public ArrayListComboBoxModel(List arraylist) {
		this.arraylist=arraylist;
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return arraylist.size();
	}

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return arraylist.get(index);
	}

	@Override
	public void setSelectedItem(Object anItem) {
		// TODO Auto-generated method stub
		this.selectedItem=anItem;
	}

	@Override
	public Object getSelectedItem() {
		// TODO Auto-generated method stub
		return selectedItem;
	}
	
}
