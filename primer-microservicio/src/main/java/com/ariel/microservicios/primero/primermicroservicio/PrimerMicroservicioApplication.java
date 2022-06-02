package com.ariel.microservicios.primero.primermicroservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class PrimerMicroservicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimerMicroservicioApplication.class, args);
	}

	/*Bean para i18n*/
	@Bean //Encargado de resolver en que idioma se trabajara
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);

		return localeResolver;
	}

	/*Bean para i18n*/
	@Bean //Encargado de obtener el fichero de traduccion
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setBasenames("messages");

		return messageSource;
	}

}
