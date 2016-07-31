package negocioTest;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.msulista.dao.PacienteDao;
import com.msulista.entidade.Paciente;
import com.msulista.negocio.PacienteNegocio;

public class PacienteNegocioTest {
	
	@Mock
	PacienteNegocio pacienteNegocio = Mockito.spy(PacienteNegocio.class);

	@Mock
	PacienteDao pacienteDao = Mockito.spy(PacienteDao.class);
	
	 @Before
	    public void setup() {
	        MockitoAnnotations.initMocks(this);
	    }
	
	private Paciente mokaPaciente() {
		
		Paciente paciente = new Paciente();
		paciente.setId(1L);
		paciente.setNomePaciente("NomePaci");
		paciente.setFonePaciente("12134756");
		
		return paciente;
	}
	
	@Test
	public void gravarTestComFalhaSalvar(){
		
//		Mockito.doThrow(SQLException.class).when(this.pacienteNegocio).s
	}
}
