package com.msulista.enums;

public enum RelatorioEnum {

	RELATORIO_ATENDIMENTO("/report/relatorio_paciente.jasper"),;
	
	 private String caminho;

    /**
     * Construtor da classe {@link RelatorioEnum}
     *
     * @param caminho caminho do relat�rio.
     */
    private RelatorioEnum(final String caminho) {
        this.caminho = caminho;
    }

    /**
     * M�todo getter do atributo caminho
     *
     * @return valor do atributo caminho
     */
    public String getCaminho() {
        return this.caminho;
    }
}
