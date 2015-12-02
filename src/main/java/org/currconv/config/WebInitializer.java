package org.currconv.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

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
		//return new Class[] { SpringDBConfig.class, SpringServicesConfig.class, SpringSecurityConfig.class };
		return new Class[] { SpringDBConfig.class, SpringServicesConfig.class, SpringSecurityConfig.class };
	}

}