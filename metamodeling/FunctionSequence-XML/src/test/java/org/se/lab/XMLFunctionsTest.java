package org.se.lab;

import static org.se.lab.XmlFunctions.attribute;
import static org.se.lab.XmlFunctions.endNode;
import static org.se.lab.XmlFunctions.getDocument;
import static org.se.lab.XmlFunctions.node;

import org.junit.Test;

public class XMLFunctionsTest {

	@Test
	public void testXML() {
		// @formatter:off
		node("students");
			node("student");
				attribute("name","Hans Mayer");
				attribute("startingYear","2019");
				node("grades");
					node("grade");
						attribute("lecture","MDD");
						attribute("grade","1");
					endNode();
					node("grade");
						attribute("lecture","PSE");
						attribute("grade","2");
					endNode();
				endNode();
			endNode();
			node("student");
				attribute("name","Martin Huber");
				attribute("startingYear","2018");
				node("grades");
					node("grade");
						attribute("lecture","MDD");
						attribute("grade","5");
					endNode();
					node("grade");
						attribute("lecture","PSE");
						attribute("grade","3");
					endNode();
				endNode();
			endNode();
		endNode();
		// @formatter:on
		System.out.println(new ModelToObjectDiagram().toDiagram(getDocument()));
	}

}
