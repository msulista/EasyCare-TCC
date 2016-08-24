package com.msulista.converter;

import java.math.BigDecimal;
import java.util.InputMismatchException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.ocpsoft.shade.org.apache.commons.beanutils.ConversionException;

@FacesConverter(value = "conversorCpf")
public class CpfMaskConverter implements Converter {

	@Override
	public Object getAsObject(final FacesContext arg0, final UIComponent arg1, final String saidaCpf)
			throws ConversionException {
		// TODO Auto-generated method stub
		if (saidaCpf != null && !saidaCpf.equals("")) {

			final String cpf = saidaCpf.replaceAll("\\.", "").replaceAll("\\-", "").replaceAll(" ", "");

			if (this.validarCPF(cpf)) {
				final FacesMessage message = new FacesMessage("Valor informado não é um Cpf válido!");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ConverterException(message);
			}
			try {
				Long.valueOf(cpf);
				return new BigDecimal(cpf.replaceAll("[^0-9]", ""));
			} catch (final NumberFormatException nfe) {
				// TODO: handle exception
				final FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversão",
						"Valor informado não é um Cpf válido!");
				throw new ConverterException(message);
			}
		} else {
			final FacesMessage message = new FacesMessage("Campo CPF é obrigatório!");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(message);
		}

	}

	@SuppressWarnings("unused")
	@Override
	public String getAsString(final FacesContext arg0, final UIComponent arg1, final Object value) {
		// TODO Auto-generated method stub
		String cpf = value.toString();
		for (int i = 0; cpf.length() < 11; i++) {
			cpf = "0" + cpf;
		}
		cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
		return cpf;
	}

	private boolean validarCPF(final String cpf) {
		// Elimina CPFs invalidos conhecidos
		if (cpf.length() != 11 || cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999")) {
			return true;
		}

		char dig10, dig11;
		int sm, i, r, num, peso;
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				num = cpf.charAt(i) - 48;
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig10 = '0';
			} else {
				dig10 = (char) (r + 48); // converte no respectivo caractere
											// numerico
			}

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = cpf.charAt(i) - 48;
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig11 = '0';
			} else {
				dig11 = (char) (r + 48);
			}

			// Verifica se os digitos calculados conferem com os digitos
			// informados.
			if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) {
				return (false);
			} else {
				return (true);
			}
		} catch (final InputMismatchException erro) {
			return (true);
		}
	}
}
