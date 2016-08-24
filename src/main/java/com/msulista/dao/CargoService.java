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

	public boolean save(final Cargo cargo) {
		final EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		if (DateUtil.verificaDiaUtil(cargo.getRegistroValidadeInicio())
				&& DateUtil.verificaDiaUtil(cargo.getRegistroValidadeFim())) {
			if (DateUtil.verificaDataValida(cargo.getRegistroValidadeInicio(), cargo.getRegistroValidadeFim())) {
				manager.persist(cargo);
				manager.getTransaction().commit();
				manager.close();
				return true;
			} else {
				Mensagem.add("Data final do registro anterior a data inicial!");
				manager.close();
				return false;
			}
		} else {
			Mensagem.add("Data informada não é um dia util!");
			manager.close();
			return false;
		}
	}

	public boolean update(final Cargo cargo) {
		final EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();

		if (DateUtil.verificaDiaUtil(cargo.getRegistroValidadeInicio())) {
			final Cargo cargoMerge = this.getCargoById(cargo.getId());
			if (DateUtil.verificaDataValida(cargo.getRegistroValidadeInicio(), cargo.getRegistroValidadeFim())) {
				cargoMerge.setRegistroValidadeFim(new Date());
				manager.merge(cargoMerge);
				manager.getTransaction().commit();
				manager.close();

				final Cargo cargoPersist = new Cargo();
				cargoPersist.setNome(cargo.getNome());
				cargoPersist.setRegistroValidadeInicio(cargo.getRegistroValidadeInicio());
				cargoPersist.setRegistroValidadeFim(cargo.getRegistroValidadeFim());
				this.save(cargoPersist);
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
		final EntityManager manager = JPAUtil.getEntityManager();
		final Query q = manager.createNamedQuery("Cargo.findAtivos");
		final List<Cargo> cargos = q.getResultList();
		manager.close();
		return cargos;
	}

	public Cargo getCargoById(final Long id) {
		final EntityManager manager = JPAUtil.getEntityManager();
		final Query q = manager.createNamedQuery("Cargo.findId");
		q.setParameter("id", id);
		final Cargo cargo = (Cargo) q.getSingleResult();
		manager.close();
		return cargo;
	}

	public void desativar(final Long id) throws ConverterException {
		final EntityManager manager = JPAUtil.getEntityManager();
		final Query q = manager.createNamedQuery("Profissional.findProfissionalByCargo");
		q.setParameter("id", id);
		if (q.getResultList().isEmpty()) {
			final Cargo cargoMerge = this.getCargoById(id);
			cargoMerge.setRegistroValidadeFim(new Date());
			manager.getTransaction().begin();
			manager.merge(cargoMerge);
			manager.getTransaction().commit();
			manager.close();
		} else {
			Mensagem.add("Existem profissionais ativos vinculados a este Cargo, não pode ser excluída.");
			manager.close();
		}
	}
}
