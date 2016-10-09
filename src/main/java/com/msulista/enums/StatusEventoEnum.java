package com.msulista.enums;

public enum StatusEventoEnum {

	ADMINISTRADO(0, "Administrado"),
	NAO_ADMINISTRADO(1, "Não administrado");
	
	private int tipo;

    private String descricao;

    private StatusEventoEnum(int tipo, String descricao) {
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public static String obterDescricaoPorId(int id) {
    	for (StatusEventoEnum element : StatusEventoEnum.values()) {
			if (id == element.getTipo()) {
				return element.getDescricao();
			}
		}
    	return "Esquecimento";
    }
}
