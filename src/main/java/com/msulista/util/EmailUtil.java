package com.msulista.util;

import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryAbstractCellEditor;

public class EmailUtil {

	private static final String TITULO = "Relat�rio de atendimento EasyCare";
	private static final String MENSAGEM1 = "Ol� \n\n Segue em anexo o relat�rio de atendimento ao paciente ";
	private static final String MENSAGEM2 = " no per�odo de atendimento contratado.\n Atenciosamente.\n";
	private static final String MENSAGEM3 = "\n EasyCare - Sistema web de controle de mecicamentos";
	private static final String EMAIL = "easycareweb@gmail.com";
	private static final String PASSWORD = "easycare123";
	private static final String EASYCAREWEB = "easycareweb@gmail.com";
	private static final String SMTP_GMAIL_COM = "smtp.gmail.com";

	public static void sendEmail(final String destinatario) {

		final SimpleEmail email = new SimpleEmail();
		try {

			email.setDebug(true);
			email.setHostName(SMTP_GMAIL_COM);
			email.setAuthentication(EASYCAREWEB, PASSWORD);
			email.setSSL(true);
			email.addTo(destinatario);
			email.setFrom(EMAIL);
			email.setSubject(TITULO);
			email.setMsg(MENSAGEM1);

			email.send();

		} catch (final EmailException e) {
			Mensagem.add("Falha ao enviar o relat�rio.");
			e.printStackTrace();
		}
	}
	
	public static void enviarEmail(String nomeCuidador, String nomePaciente, String destinatario, byte[] anexo) {
		
		MultiPartEmail email = new MultiPartEmail();
		try {
			
			email.setHostName(SMTP_GMAIL_COM);
			email.setSSL(true);
			email.setAuthentication(EASYCAREWEB, PASSWORD);
			email.addTo(destinatario);
			email.setFrom(EMAIL);
			email.setSubject(TITULO);
			email.setMsg(MENSAGEM1 + nomePaciente + MENSAGEM2 + nomeCuidador + MENSAGEM3);
			ByteArrayDataSource source = new ByteArrayDataSource(anexo,	".pdf");
			email.attach(source, "relatorio_easycare.pdf", "");
			
			email.send();
			
		} catch (EmailException e) {
			Mensagem.add("Falha ao enviar o relat�rio.");
			e.printStackTrace();
		}
		
	}
	
}
