package com.wander.paper.mvc;

public class PaperKey {
	private String key;

	public PaperKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		if (key == null || key.equals("")) {
			return "[]";
		} else {
			return key;
			// return "[" + key + "]";
		}
	}

	@Override
	public int hashCode() {
		if (key == null || key.equals("")) {
			return "".hashCode();
		} else {
			return key.hashCode();
		}
	}

}
