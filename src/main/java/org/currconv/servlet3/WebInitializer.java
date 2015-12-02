package org.currconv.servlet3;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import org.currconv.config.SpringDBConfig;
import org.currconv.config.SpringWebConfig;
import org.currconv.config.SpringSecurityConfig;
import org.currconv.config.SpringServicesConfig;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringWebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringDBConfig.class, SpringServicesConfig.class, SpringSecurityConfig.class };
	}

}