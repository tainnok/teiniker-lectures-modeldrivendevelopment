package org.se.lab;

import java.util.Stack;

public class XmlFunctions {
	private static MNode rootNode;
	private static Stack<MNode> previousNodes = new Stack<>();
	private static MNode currentNode;

	public static void node(String name) {
		MNode node = new MNode(name);
		if (rootNode == null) {
			rootNode = node;
		} else {
			if (currentNode == null) {
				throw new IllegalArgumentException("More than one nodes is not supported");
			}
		}

		if (currentNode != null) {
			currentNode.getChildren().add(node);
			previousNodes.add(currentNode);
		}
		currentNode = node;
	}

	public static void endNode() {
		if (currentNode == null) {
			throw new IllegalArgumentException("No open node");
		}
		if (!previousNodes.isEmpty()) {
			currentNode = previousNodes.pop();
		} else {
			currentNode = null;
		}
	}

	public static void attribute(String name, String value) {
		if (currentNode == null) {
			throw new IllegalArgumentException("No open node");
		}
		MAttribute attribute = new MAttribute(name, value);
		currentNode.getAttributes().add(attribute);
	}

	public static MDocument getDocument() {
		if (rootNode == null) {
			throw new IllegalArgumentException("No root node");
		}
		MDocument document = new MDocument(rootNode);
		rootNode = null;
		return document;
	}
}
