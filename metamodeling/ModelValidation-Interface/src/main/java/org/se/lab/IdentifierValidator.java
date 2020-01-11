package org.se.lab;

import org.se.lab.metamodel.MInterface;
import org.se.lab.metamodel.MNamedElement;
import org.se.lab.metamodel.MOperation;
import org.se.lab.metamodel.MPackage;
import org.se.lab.metamodel.MParameter;
import org.se.lab.metamodel.MType;

public class IdentifierValidator extends AbstractValidator {
	@Override
	public void visit(MPackage pkg) {
		checkName(pkg);
		super.visit(pkg);
	}

	@Override
	public void visit(MInterface iface) {
		checkName(iface);
		super.visit(iface);
	}

	@Override
	public void visit(MOperation op) {
		checkName(op);
		super.visit(op);
	}

	@Override
	public void visit(MParameter par) {
		checkName(par);
		super.visit(par);
	}

	@Override
	public void visit(MType type) {
		checkName(type);
	}

	/*
	 * Helper methods
	 */

	private void checkName(MNamedElement element) {
		if (element.getName() == null || element.getName().trim().length() == 0)
			throw new IllegalStateException(
					"Invalid identifier " + element.getName() + " at " + element.getClass().getTypeName());
	}
}
