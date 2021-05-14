package br.com.impacta.fullstack.conta.enums;

import java.math.BigDecimal;

public enum TipoInvestimentos {
	TESOURO_PREFIXADO_2024(new BigDecimal(10)), 
	TESOURO_PREFIXADO_2026(new BigDecimal(20)),
	TESOURO_SELIC_2024(new BigDecimal(30)), 
	TESOURO_SELIC_2027(new BigDecimal(50));
	
	private BigDecimal value;
	
	TipoInvestimentos(BigDecimal bigDecimal) {
		this.value = bigDecimal;
	}
	
	public BigDecimal getValue() {
	    return value;
	}

}
