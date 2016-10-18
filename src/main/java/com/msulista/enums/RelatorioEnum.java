package com.msulista.enums;

public enum RelatorioEnum {

	// C:\Users\Marcus\workspace\EasyCare-TCC\src\main\webapp\resources\report
	// C:\Users\Marcus\workspace\EasyCare-TCC\src\main\java\resources\reports
	RELATORIO_ATENDIMENTO("/resources/reports/relatorio_paciente.jasper");

	private String caminho;

	/**
	 * Construtor da classe {@link RelatorioEnum}
	 *
	 * @param caminho
	 *            caminho do relatório.
	 */
	private RelatorioEnum(final String caminho) {
		this.caminho = caminho;
	}

	/**
	 * Método getter do atributo caminho
	 *
	 * @return valor do atributo caminho
	 */
	public String getCaminho() {
		return this.caminho;
	}
}
