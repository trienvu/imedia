package com.gtotek.imedia.util;

import java.io.Serializable;
import java.util.ArrayList;

import com.gtotek.imedia.entry.Category;

public class ListCategoryWrapper implements Serializable {
	private static final long serialVersionUID = 5458353202523209639L;
	private ArrayList<Category> lstCategory;

	public ListCategoryWrapper(ArrayList<Category> data) {
		this.lstCategory = data;
	}

	public ArrayList<Category> getCategories() {
		return this.lstCategory;
	}

}