package org.audit4j.microservice.transport.thrift;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.audit4j.core.dto.AuditEvent;
import org.audit4j.core.dto.EventMeta;
import org.audit4j.core.dto.Field;

public class TAuditEventTransformer {

    public AuditEvent transformToEvent(TAuditEvent event) {
        AuditEvent auditEvent = new AuditEvent();
        auditEvent.setUuid(event.getUuid());

        SimpleDateFormat format = new SimpleDateFormat(event.getTimestampFormat());
        Date date = null;
        try {
            date = format.parse(event.getTimestamp());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        auditEvent.setTimestamp(date);
        auditEvent.setMeta(transformToEventMeta(event.getMeta()));
        auditEvent.setAction(event.getAction());
        auditEvent.setActor(event.getActor());
        auditEvent.setOrigin(event.getOrigin());
        auditEvent.setTag(event.getTag());
        auditEvent.setRepository(event.getRepository());
        if (event.getFields() != null) {
            for (TField field : event.getFields()) {
                auditEvent.addField(transformToField(field));
            }
        }
        
        return auditEvent;
    }
    
    public EventMeta transformToEventMeta(TEventMeta meta) {
        if (meta == null) {
            return null;
        }
        EventMeta eventMeta = new EventMeta();
        eventMeta.setClient(meta.getClient());
        return eventMeta;
    }

    
    public Field transformToField (TField field) {
        Field auditField = new Field();
        auditField.setName(field.getName());
        auditField.setValue(field.getValue());
        auditField.setType(field.getType());
        return auditField;
    }
}
