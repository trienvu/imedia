package com.gtotek.imedia.entry;

import java.io.Serializable;

public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	private int _id;
	private String _icon;
	private String _title;
	private String _count = "0";
	private boolean _selected;

	public boolean is_selected() {
		return _selected;
	}

	public void set_selected(boolean _selected) {
		this._selected = _selected;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public Category() {
	}

	public Category(int id, String title, String icon) {
		this._title = title;
		this._icon = icon;
		this._id = id;
	}

	public String getTitle() {
		return this._title;
	}

	public String getIcon() {
		return this._icon;
	}

	public String getCount() {
		return this._count;
	}

	public void setTitle(String title) {
		this._title = title;
	}

	public void setIcon(String icon) {
		this._icon = icon;
	}

	public void setCount(String count) {
		this._count = count;
	}

	@Override
	public String toString() {
		return "Category [_id=" + _id + ", _icon=" + _icon + ", _title="
				+ _title + ", _count=" + _count + "]";
	}

}
