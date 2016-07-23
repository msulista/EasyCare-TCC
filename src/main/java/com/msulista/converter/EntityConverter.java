package com.msulista.converter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("entityConverterSelectOneMenu")
public class EntityConverter implements Converter{
	
	 private static final String KEY = EntityConverter.class.getCanonicalName();

	    @Override
	    public String getAsString(FacesContext context, UIComponent component, Object entity) {
	        if (entity != null) {
	            if (!getEntities(context).containsKey(entity)) {
	                String uuid = UUID.randomUUID().toString();
	                getEntities(context).put(entity, uuid);
	                return uuid;
	            } else {
	                return getEntities(context).get(entity);
	            }
	        }

	        return null;
	    }

	    @Override
	    public Object getAsObject(FacesContext context, UIComponent component, String uuid) {
	        for (Entry<Object, String> entry : getEntities(context).entrySet()) {
	            if (entry.getValue().equals(uuid)) {
	                return entry.getKey();
	            }
	        }

	        return null;
	    }

	    @SuppressWarnings("unchecked")
	    private Map<Object, String> getEntities(FacesContext context) {
	        Map<String, Object> viewMap = context.getViewRoot().getViewMap();
	        Map<Object, String> entities = (Map<Object, String>) viewMap.get(KEY);

	        if (entities == null) {
	            entities = new HashMap<>();
	            viewMap.put(KEY, entities);
	        }

	        return entities;
	    }

}
