package com.livioneiva.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration //transforma numa classe de configuração
public class intercionalizacaoConfig {

	//definimos um arquivo vai ser a fonte das messagens, que será utilizado no sistema "messages.proerties"
	@Bean  //quando scannear essa classe de configuração, ele registrara o bean dentro do contexto, ingeção de dependencia
	public MessageSource messageSource() {
		//para configurarmos  um fonte de mesagem para arquivos do tipo properties, trataremos com ReloadableResourceBundleMessageSource
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");//passando o nome do arquivo de mesagem, classpath: informa que o arquivo está na raiz
		messageSource.setDefaultEncoding("iso-8859-1");//escreve os caracteres especiais da msg, vai reconhecer os caracteres brasileiros. ç^~
		messageSource.setDefaultLocale(Locale.getDefault());//local.getDefault() indentifica a localizaçao pelo sistema operacional. Brasil
		
		return messageSource;
		
	}
	
	//a classe LocalValidatorFactoryBean vai criar o obj q vai fazer a interpolação, faz a integração enre as mensagens e a nossa validações e especificações do java
	@Bean //essa classe faz a comunicação entre @NotNull, @CPF, @EMmprty com as mensagens do arquivo messages.properties
	public LocalValidatorFactoryBean validatorFactoryBean() {
		//instanciando obj
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());//a fonte de mensagem é metodo messageSource()
		return bean;
	}
	
	
}
/*
foi criado um arquivo messages.proerties na pasta resources

a validadorFactoryBean()
estamos configurando com spring, para trabalhar com a integração com a especificação com validaition



 */