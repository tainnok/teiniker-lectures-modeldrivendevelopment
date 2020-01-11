package org.se.lab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MetamodelTest {
	protected MProject ant;

	@Before
	public void setup() {
		// Project values
		ant = new MProject();
		ant.setName("SimpleProject");
		ant.setBaseDir(".");

		// Description
		MDescription description = new MDescription();
		description.setText("Simple project build file");
		ant.setDescription(description);

		// Properties
		MProperty src = new MProperty();
		src.setName("src");
		src.setValue("./src");
		ant.getProperties().add(src);

		MProperty build = new MProperty();
		build.setName("build");
		build.setValue("./build");
		ant.getProperties().add(build);

		MProperty dist = new MProperty();
		dist.setName("dist");
		dist.setValue("./dist");
		ant.getProperties().add(dist);
	}

	@Test
	public void testProject() {
		Assert.assertEquals("SimpleProject", ant.getName());
		Assert.assertEquals(".", ant.getBaseDir());
		Assert.assertEquals("Simple project build file", ant.getDescription().getText());
	}

	@Test
	public void testPropertySrc() {
		Assert.assertEquals("src", ant.getProperties().get(0).getName());
		Assert.assertEquals("./src", ant.getProperties().get(0).getValue());
	}

	@Test
	public void testPropertyBuild() {
		Assert.assertEquals("build", ant.getProperties().get(1).getName());
		Assert.assertEquals("./build", ant.getProperties().get(1).getValue());
	}

	@Test
	public void testPropertyDist() {
		Assert.assertEquals("dist", ant.getProperties().get(2).getName());
		Assert.assertEquals("./dist", ant.getProperties().get(2).getValue());
	}
}
