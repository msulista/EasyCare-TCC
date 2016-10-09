package com.msulista.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailUtil {

	private static final String TITULO = "Relatório de atendimento EasyCare";
	private static final String MENSAGEM1 = "Olá \n\n Segue em anexo o relatório de atendimento ao paciente ";
	private static final String MENSAGEM2 = " no período de atendimento contratado.\n\n Sistema EasyCare";
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
			Mensagem.add("Falha ao enviar o relatório.");
			e.printStackTrace();
		}

	}

	// public EmailUtil() throws EmailException, MalformedURLException {
	//// enviaEmailSimples();
	// enviaEmailComAnexo();
	//// enviaEmailFormatoHtml();
	// }
	/**
	 * envia email simples(somente texto)
	 *
	 * @throws EmailException
	 */
	// public void enviaEmailSimples() throws EmailException {
	//
	// SimpleEmail email = new SimpleEmail();
	//
	// email.setHostName(&quot;smtp.gmail.com&quot;); // o servidor SMTP para
	// envio do e-mail
	// email.addTo(&quot;teste@gmail.com&quot;, &quot;Guilherme&quot;);
	// //destinatário
	// email.setFrom(&quot;teste@gmail.com&quot;, &quot;Eu&quot;); // remetente
	// email.setSubject(&quot;Teste -&gt; Email simples&quot;); // assunto do
	// e-mail
	// email.setMsg(&quot;Teste de Email utilizando commons-email&quot;);
	// //conteudo do e-mail
	// email.setAuthentication(&quot;teste&quot;, &quot;xxxxx&quot;);
	// email.setSmtpPort(465);
	// email.setSSL(true);
	// email.setTLS(true);
	// email.send();
	// }
	/**
	 * envia email com arquivo anexo
	 *
	 * @throws EmailException
	 */
	// public void enviaEmailComAnexo(){
	// // cria o anexo 1.
	// EmailAttachment anexo1 = new EmailAttachment();
	//
	// anexo1.setPath("resources/reports/relatorio.jasper");
	// anexo1.setDisposition(EmailAttachment.ATTACHMENT);
	// anexo1.setDescription("Relatório semanal");
	// anexo1.setName("relatório_semanal.pdf");
	//
	//
	// anexo1.setPath(&quot;teste/teste.txt&quot;); //caminho do arquivo
	// (RAIZ_PROJETO/teste/teste.txt)
	// anexo1.setDisposition(EmailAttachment.ATTACHMENT);
	// anexo1.setDescription(&quot;Exemplo de arquivo anexo&quot;);
	// anexo1.setName(&quot;teste.txt&quot;);
	// // cria o anexo 2.
	// EmailAttachment anexo2 = new EmailAttachment();
	// anexo2.setPath(&quot;teste/teste2.jsp&quot;); //caminho do arquivo
	// (RAIZ_PROJETO/teste/teste2.jsp)
	// anexo2.setDisposition(EmailAttachment.ATTACHMENT);
	// anexo2.setDescription(&quot;Exemplo de arquivo anexo&quot;);
	// anexo2.setName(&quot;teste2.jsp&quot;);
	// // configura o email
	// MultiPartEmail email = new MultiPartEmail();
	// email.setHostName(&quot;smtp.gmail.com&quot;); // o servidor SMTP para
	// envio do e-mail
	// email.addTo(&quot;teste@gmail.com&quot;, &quot;Guilherme&quot;);
	// //destinatário
	// email.setFrom(&quot;teste@gmail.com&quot;, &quot;Eu&quot;); // remetente
	// email.setSubject(&quot;Teste -&gt; Email com anexos&quot;); // assunto do
	// e-mail
	// email.setMsg(&quot;Teste de Email utilizando commons-email&quot;);
	// //conteudo do e-mail
	// email.setAuthentication(&quot;teste&quot;, &quot;xxxxx&quot;);
	// email.setSmtpPort(465);
	// email.setSSL(true);
	// email.setTLS(true);
	// // adiciona arquivo(s) anexo(s)
	// email.attach(anexo1);
	// email.attach(anexo2);
	// // envia o email
	// email.send();
	// }
	/**
	 * Envia email no formato HTML
	 *
	 * @throws EmailException
	 * @throws MalformedURLException
	 */
	// public void enviaEmailFormatoHtml() throws EmailException,
	// MalformedURLException {
	// HtmlEmail email = new HtmlEmail();
	// // adiciona uma imagem ao corpo da mensagem e retorna seu id
	// URL url = new
	// URL(&quot;http://www.apache.org/images/asf_logo_wide.gif&quot;);
	// String cid = email.embed(url, &quot;Apache logo&quot;);
	// // configura a mensagem para o formato HTML
	// email.setHtmlMsg(&quot;&lt;html&gt;Logo do Apache - <img
	// >&lt;/html&gt;&quot;);
	// // configure uma mensagem alternativa caso o servidor não suporte HTML
	// email.setTextMsg(&quot;Seu servidor de e-mail não suporta mensagem
	// HTML&quot;);
	// email.setHostName(&quot;smtp.gmail.com&quot;); // o servidor SMTP para
	// envio do e-mail
	// email.addTo(&quot;teste@gmail.com&quot;, &quot;Guilherme&quot;);
	// //destinatário
	// email.setFrom(&quot;teste@gmail.com&quot;, &quot;Eu&quot;); // remetente
	// email.setSubject(&quot;Teste -&gt; Html Email&quot;); // assunto do
	// e-mail
	// email.setMsg(&quot;Teste de Email HTML utilizando commons-email&quot;);
	// //conteudo do e-mail
	// email.setAuthentication(&quot;teste&quot;, &quot;xxxxx&quot;);
	// email.setSmtpPort(465);
	// email.setSSL(true);
	// email.setTLS(true);
	// // envia email
	// email.send();
	// }
	// /**
	// * @param args
	// * @throws EmailException
	// * @throws MalformedURLException
	// */
	// public static void main(String[] args) throws EmailException,
	// MalformedURLException {
	// new CommonsMa();
	// }
}
