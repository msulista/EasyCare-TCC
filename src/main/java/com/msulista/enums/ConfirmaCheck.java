package com.msulista.enums;

public enum ConfirmaCheck {

	S("label.sim"), N("label.nao");

	private final String label;

	private ConfirmaCheck(final String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}

}
