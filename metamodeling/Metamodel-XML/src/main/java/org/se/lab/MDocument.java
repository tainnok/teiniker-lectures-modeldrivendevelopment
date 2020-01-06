package org.se.lab;

public class MDocument {
	private MNode rootNode;

	public MDocument(MNode rootNode) {
		this.rootNode = rootNode;
	}

	public MNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(MNode rootNode) {
		this.rootNode = rootNode;
	}

}
