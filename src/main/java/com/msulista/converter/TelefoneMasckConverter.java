package com.msulista.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "conversorFone")
public class TelefoneMasckConverter implements Converter{
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		if (value != null && !value.equals("")) {
			String fone = value.replaceAll("\\.", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\-", "").replaceAll(" ", "");
			try {
				Long.valueOf(fone);
				return fone;
			} catch (NumberFormatException e) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Erro de conversão Telefone",
						"O valor informado não é um número válido!");
				throw new ConverterException(message);
			}
		} else {			
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		// TODO Auto-generated method stub
		String fone = value.toString();
		if(fone != null && fone.length() == 10){
			fone = "(" + fone.substring(0, 2) + ") " + fone.substring(2, 6) + "." + fone.substring(5, 9);
		}
		return fone;
	}

}
