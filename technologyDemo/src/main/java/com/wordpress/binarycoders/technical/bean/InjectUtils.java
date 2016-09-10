package com.wordpress.binarycoders.technical.bean;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class InjectUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		InjectUtils.applicationContext = applicationContext;
	}

	public static <B> B inject(final Class<B> bean) {
		return InjectUtils.inject(bean, WordUtils.uncapitalize(bean.getSimpleName()));
	}

	public static <B> B inject(final Class<B> bean, final String name) {
		return InjectUtils.applicationContext.getBean(name, bean);
	}
}
