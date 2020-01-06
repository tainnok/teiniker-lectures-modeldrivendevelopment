package org.se.lab;

import java.util.ArrayList;
import java.util.List;

public class MNode {
	private String name;
	private List<MNode> children = new ArrayList<>();
	private List<MAttribute> attributes = new ArrayList<>();

	public MNode(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MNode> getChildren() {
		return children;
	}

	public void setChildren(List<MNode> children) {
		this.children = children;
	}

	public List<MAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<MAttribute> attributes) {
		this.attributes = attributes;
	}

}
