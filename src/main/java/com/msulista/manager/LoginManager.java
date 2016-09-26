package com.msulista.manager;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.msulista.entidade.Cuidador;
import com.msulista.negocio.CuidadorNegocio;
import com.msulista.util.Mensagem;
import com.msulista.util.SessionUtil;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean
@SessionScoped
@URLMappings(mappings = {
//		@URLMapping(id = "easycare", pattern = "/easycare", viewId = "/pages/home/inicial.xhtml"),
		@URLMapping(id = "logado", pattern = "/logado", viewId = "/pages/usuario/home/index.xhtml")})
public class LoginManager implements Serializable{    
    
	private static final long serialVersionUID = 7165000715351164498L;
	
    private String email;
    private String cpf;
    
    private Cuidador cuidadorLogado;
    
    public String autentica() {
    	CuidadorNegocio cuidadorNegocio = new CuidadorNegocio();
    	List<Cuidador> cuidadores = cuidadorNegocio.obterLista();
    	for (Cuidador cuidador : cuidadores) {

    		if (email.equals(cuidador.getEmail()) && cpf.equals(cuidador.getCpf())) {
    			
    			Mensagem.add("Login realizado com sucesso!");
    			
    			SessionUtil.setParam("user", cuidador);
    			
    			return "pretty:logado";
    		}
		}
    	Mensagem.add("Email ou senha informados estão errados!");
    	return "/pages/home/inicial.xhtml";
    }
    
    public String logout() {
    	
    	SessionUtil.remove("user");
    	return "pretty:easycare";
    }
   
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

	public Cuidador getCuidadorLogado() {
		return cuidadorLogado;
	}

	public void setCuidadorLogado(Cuidador cuidadorLogado) {
		this.cuidadorLogado = cuidadorLogado;
	}

	public boolean logadoOn(){
        return (cuidadorLogado != null);
    }
   
}
