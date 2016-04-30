package com.msulista.dao;

import java.util.Date;
import java.util.List;

import javax.faces.convert.ConverterException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.msulista.entidade.Cargo;
import com.msulista.util.DateUtil;
import com.msulista.util.JPAUtil;
import com.msulista.util.Mensagem;

public class CargoService {

	public boolean save(Cargo cargo) {
		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		if (DateUtil.verificaDiaUtil(cargo.getRegistroValidadeInicio())&&DateUtil.verificaDiaUtil(cargo.getRegistroValidadeFim())) {
			if(DateUtil.verificaDataValida(cargo.getRegistroValidadeInicio(), cargo.getRegistroValidadeFim())){
			manager.persist(cargo);
			manager.getTransaction().commit();
			manager.close();
			return true;
		}else{
			Mensagem.add("Data final do registro anterior a data inicial!");
			manager.close();
			return false;
		}
		}else{
		Mensagem.add("Data informada não é um dia util!");
		manager.close();
		return false;
		}
	}

	public boolean update(Cargo cargo) {
		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();

		if (DateUtil.verificaDiaUtil(cargo.getRegistroValidadeInicio())) {
			Cargo cargoMerge = (Cargo) getCargoById(cargo.getId());
			if (DateUtil.verificaDataValida(cargo.getRegistroValidadeInicio(), cargo.getRegistroValidadeFim())) {
				cargoMerge.setRegistroValidadeFim(new Date());
				manager.merge(cargoMerge);
				manager.getTransaction().commit();
				manager.close();

				Cargo cargoPersist = new Cargo();
				cargoPersist.setNome(cargo.getNome());
				cargoPersist.setRegistroValidadeInicio(cargo.getRegistroValidadeInicio());
				cargoPersist.setRegistroValidadeFim(cargo.getRegistroValidadeFim());
				save(cargoPersist);
				return true;
			} else {
				Mensagem.add("Erro, data final menor que a inicial!");
				manager.close();
				return false;
			}
		} else {
			Mensagem.add("Data informada não é um dia util!");
			manager.close();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cargo> listarAtivos() {
		EntityManager manager = JPAUtil.getEntityManager();
		Query q = manager.createNamedQuery("Cargo.findAtivos");
		List<Cargo> cargos = q.getResultList();
		manager.close();
		return cargos;
	}

	public Cargo getCargoById(Long id) {
		EntityManager manager = JPAUtil.getEntityManager();
		Query q = manager.createNamedQuery("Cargo.findId");
		q.setParameter("id", id);
		Cargo cargo = (Cargo) q.getSingleResult();
		manager.close();
		return cargo;
	}

	public void desativar(Long id) throws ConverterException {
		EntityManager manager = JPAUtil.getEntityManager();
		Query q = manager.createNamedQuery("Profissional.findProfissionalByCargo");
		q.setParameter("id", id);
		if(q.getResultList().isEmpty()){
			Cargo cargoMerge = (Cargo) getCargoById(id);
			cargoMerge.setRegistroValidadeFim(new Date());
			manager.getTransaction().begin();
			manager.merge(cargoMerge);
			manager.getTransaction().commit();
			manager.close();
		}else {
			Mensagem.add("Existem profissionais ativos vinculados a este Cargo, não pode ser excluída.");
			manager.close();
		}
	}
}
