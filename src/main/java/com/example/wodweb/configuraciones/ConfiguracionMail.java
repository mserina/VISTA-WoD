package com.example.wodweb.configuraciones;

import java.util.Properties;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Clase para mandar correos
 * msm - 010525
 */
@Configuration
public class ConfiguracionMail {

	/**
	 * Metodo donde se configura los parametros para eviar correos
	 * msm - 01052
	 * @return Devuelbve un objeto mail JavaMailSender
	 */
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // Configuración para Gmail (modifica según tus necesidades)
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("willofd2004@gmail.com");
        mailSender.setPassword("dnzvwkgphvtynejk");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false"); // O "true" para ver más detalles

        return mailSender;
    }
}