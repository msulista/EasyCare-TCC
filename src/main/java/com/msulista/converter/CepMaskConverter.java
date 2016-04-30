package com.msulista.converter;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "conversorCep")
public class CepMaskConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		if (value != null && !value.equals("")) {
			String cep = value.replaceAll("\\-", "").replaceAll(" ", "");
			try {
				Long.valueOf(cep);
				return cep;
			} catch (NumberFormatException e) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Erro de conversão Cel",
						"O valor informado não é um número de Celular!");
				throw new ConverterException(message);
			}
		} else {
			return null;
		}
	}
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		// TODO Auto-generated method stub
		String cep = value.toString();
		if(cep != null && cep.length() == 8){
			cep = cep.substring(0, 5) + "-" + cep.substring(5, 8);
		}
		return cep;
	}
}
