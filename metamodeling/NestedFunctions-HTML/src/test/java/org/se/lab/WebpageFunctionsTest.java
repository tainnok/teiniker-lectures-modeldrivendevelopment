package org.se.lab;

import static org.se.lab.WebpageFunctions.body;
import static org.se.lab.WebpageFunctions.h1;
import static org.se.lab.WebpageFunctions.head;
import static org.se.lab.WebpageFunctions.html;
import static org.se.lab.WebpageFunctions.p;

import org.junit.Test;

public class WebpageFunctionsTest {
	@Test
	public void testHtml() {
		// @formatter:off
		MWebPage page = html(
				head("Hello World"), 
				body(
						h1("I am a header at level 1"), 
						p("And i am a paragraph")
					)
		);
		// @formatter:on
		System.out.println(new ModelToObjectDiagram().toDiagram(page));
	}
}
