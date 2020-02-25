package org.se.lab;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class EntityVisitorImpl implements EntityVisitor {
    @Override
    public String visitEntity(Class<?> clazz) {

        if (!clazz.isAnnotationPresent(Entity.class)) {
            return "";
        }

        StringBuilder s = new StringBuilder();

        // SimpleName(!!!) holen
        s.append(clazz.getSimpleName()).append("{ ");

        for(Field field : clazz.getDeclaredFields())
        {
            s.append(visitProperty(field));
        }
        s.append("}");
        return s.toString();
    }

    @Override
    public String visitProperty(Field field) {
        String s = new String();
        // Field.getType liefert die Klasse, von dieser dann wieder den SimpleName(!!!) holen
        s += field.getName() + ":" + field.getType().getSimpleName();
        s += visitType(field);
        s += " ";
        return s;
    }

    @Override
    public String visitType(Field field) {
        String s = new String();
        if (field.isAnnotationPresent(Id.class)) {
            s += "[id]";
        }
        return s;
    }
}
