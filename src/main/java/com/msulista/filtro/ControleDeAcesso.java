package com.msulista.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter(servletNames = { "Faces Servlet" }, urlPatterns = {"/pages/home/inicial.xhtml"})
public class ControleDeAcesso implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		if ((session.getAttribute("user") != null)
				|| (req.getRequestURI().endsWith("inicial.xhtml"))
				|| (req.getRequestURI().endsWith("easycare"))
				|| (req.getRequestURI().endsWith("pretty:easycare"))
				|| (req.getRequestURI().contains("javax.faces.resource/"))) {

			System.out.println("Loguei!!!!!!!!!!!!");

				//redireciona("/Logado.xhtml", response);
			
			chain.doFilter(request, response);
		}

		else {
			System.out.println("Não Logado!!!!!!!!");
//			this.retorna("/pages/home/inicial.xhtml");
			redireciona("/pages/home/inicial.xhtml", response);
		}

	}
	private String retorna(String pretty) {
		return pretty;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}

	
	private void redireciona(String url, ServletResponse response)
			throws IOException {
		HttpServletResponse res = (HttpServletResponse) response;
		res.sendRedirect(url);
	}
}
