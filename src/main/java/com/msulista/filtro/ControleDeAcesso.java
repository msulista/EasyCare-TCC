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

import com.msulista.entidade.Cuidador;

@WebFilter(filterName = "controleAcesso", urlPatterns = { "/pages/usuario/*" })
public class ControleDeAcesso implements Filter {
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpSession session = req.getSession();

		if ((session.getAttribute("user") != null && ((Cuidador) (session.getAttribute("user"))).getId() != null)
				|| (req.getRequestURI().endsWith("inicial.xhtml"))) {

			chain.doFilter(request, response);
		}

		else {
			System.out.println("Não Logado!!!!!!!!");
			this.redireciona("/pages/home/inicial.xhtml", response);
		}

	}

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	private void redireciona(final String url, final ServletResponse response) throws IOException {
		final HttpServletResponse res = (HttpServletResponse) response;
		res.sendRedirect(url);
	}
}
