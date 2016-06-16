package com.msulista.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.msulista.entidade.Paciente;
import com.msulista.manager.PacienteManager;

@FacesConverter(value = "pacienteConverter")
public class PacienteConverter implements Converter{	      

	    @Override
	    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	        
//	        if(value == null){
//	            return null;
//	        }
//	        PacienteManager pacienteMb = context.getApplication().evaluateExpressionGet(context, "#{paciente}", PacienteManager.class);
//	        Paciente paciente = pacienteMb.findPacientebyNome(value);
//	        return paciente;
	    	return null;
	    }

	    @Override
	    public String getAsString(FacesContext context, UIComponent component, Object value) {
	    
	        String string = null;
	        if(value instanceof Paciente){
	            string = ((Paciente) value).getNomePaciente();
	        }
	        return string;        
	}

}
