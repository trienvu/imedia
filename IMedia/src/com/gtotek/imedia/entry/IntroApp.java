package com.gtotek.imedia.entry;

public class IntroApp {
	private int icon;
	private String apkName;
	private String name;

	public IntroApp(int icon, String url, String name) {
		super();
		this.icon = icon;
		this.apkName = url;
		this.name = name;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getApkName() {
		return apkName;
	}

	public void setApkName(String apkName) {
		this.apkName = apkName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "IntroApp [icon=" + icon + ", apkName=" + apkName + ", name="
				+ name + "]";
	}

}
