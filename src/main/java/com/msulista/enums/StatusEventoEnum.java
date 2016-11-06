package com.msulista.enums;

public enum StatusEventoEnum {

	ADMINISTRADO(0, "Administrado"), NAO_ADMINISTRADO(1, "Não administrado");

	private Integer tipo;

	private String descricao;

	private StatusEventoEnum(final Integer tipo, final String descricao) {
		this.tipo = tipo;
		this.descricao = descricao;
	}

	public int getTipo() {
		return this.tipo;
	}

	public void setTipo(final int tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public static String obterDescricaoPorId(final Integer id) {
		for (final StatusEventoEnum element : StatusEventoEnum.values()) {
			if (id.equals(element.getTipo())) {
				return element.getDescricao();
			}
		}
		return null;
	}
}
