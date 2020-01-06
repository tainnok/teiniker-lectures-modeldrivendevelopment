package org.se.lab;

import java.util.Arrays;

public class WebpageFunctions {
	public static MWebPage html(MHead head, MBody body) {
		MWebPage page = new MWebPage();
		page.setHead(head);
		page.setBody(body);
		return page;
	}

	public static MHead head(String title) {
		return new MHead(title);
	}

	public static MBody body(MPageElement... elements) {
		MBody body = new MBody();
		body.getElements().addAll(Arrays.asList(elements));
		return body;
	}

	public static MHeadline headline(String text, int level) {
		return new MHeadline(text, level);
	}

	public static MHeadline h1(String text) {
		return headline(text, 1);
	}

	public static MHeadline h2(String text) {
		return headline(text, 2);
	}

	public static MHeadline h3(String text) {
		return headline(text, 3);
	}

	public static MParagraph p(String text) {
		return new MParagraph(text);
	}
}
