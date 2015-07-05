package com.wordpress.binarycoders.calculator.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;

public class TestInjectorUtility {

    private static final List<Class<? extends Annotation>> EJB_ANNOTATIONS;
    static {
        EJB_ANNOTATIONS = new ArrayList<>();
        EJB_ANNOTATIONS.add(EJB.class);
        EJB_ANNOTATIONS.add(PersistenceContext.class);
        EJB_ANNOTATIONS.add(Inject.class);
    }
    
    final Map<Class<?>, Object> mappings = new HashMap<>();

    public void inject(final Object bean) throws Exception {

        for (final Field field : getEJBAnnotatedFields(bean)) {
            injectField(field, bean);
        }
    }

    public void assign(final Class<?> type, final Object instance) {

        mappings.put(type, instance);
    }

    private void injectField(final Field field, final Object bean) throws Exception {

        final Object instanceToInject = mappings.get(field.getType());
        
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        
        field.set(bean, instanceToInject);
    }

    private List<Field> getEJBAnnotatedFields(final Object bean) {

        final Class<? extends Object> beanClass = bean.getClass();
        final List<Field> annotatedFields = new ArrayList<>();
        
        for (final Field field : getAllFields(beanClass)) {
            if (hasEJBAnnotation(field)) {
                annotatedFields.add(field);
            }
        }
        
        return annotatedFields;
    }

    private static Field[] getAllFields(Class<?> type) {

        List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFieldsAux(fields, type.getSuperclass());
        }

        Field[] fieldsProcessed = new Field[fields.size()];

        for (int i = 0; i < fields.size(); i++) {
            fieldsProcessed[i] = fields.get(i);
        }

        return fieldsProcessed;
    }

    private static void getAllFieldsAux(List<Field> fields, Class<?> type) {

        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFieldsAux(fields, type.getSuperclass());
        }
    }

    private static boolean hasEJBAnnotation(final Field field) {

        return EJB_ANNOTATIONS.stream().anyMatch((annotation) -> (field.isAnnotationPresent(annotation)));
    }
}
