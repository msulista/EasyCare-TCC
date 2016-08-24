package com.msulista.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.msulista.entidade.BaseEntity;

@FacesConverter("simpleEntityConverter")
public class SimpleEntityConverter implements Converter {

	@Override
	public Object getAsObject(final FacesContext ctx, final UIComponent component, final String value) {
		if (value != null) {
			return this.getAttributesFrom(component).get(value);
		}
		return null;
	}

	@Override
	public String getAsString(final FacesContext ctx, final UIComponent component, final Object value) {

		if (value != null && !"".equals(value)) {

			final BaseEntity entity = (BaseEntity) value;

			// adiciona item como atributo do componente
			this.addAttribute(component, entity);

			final Long codigo = entity.getId();
			if (codigo != null) {
				return String.valueOf(codigo);
			}
		}

		return (String) value;
	}

	protected void addAttribute(final UIComponent component, final BaseEntity o) {
		final String key = o.getId().toString(); // codigo da empresa como chave
													// neste caso
		this.getAttributesFrom(component).put(key, o);
	}

	protected Map<String, Object> getAttributesFrom(final UIComponent component) {
		return component.getAttributes();
	}

}
