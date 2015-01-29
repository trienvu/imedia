package com.gtotek.imedia.entry;

public class Channel {
	private int id;
	private String name;
	private String icon;
	private String desc;

	public Channel(int id, String name, String icon, String desc) {
		super();
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Channel [id=" + id + ", name=" + name + ", icon=" + icon
				+ ", desc=" + desc + "]";
	}
}
