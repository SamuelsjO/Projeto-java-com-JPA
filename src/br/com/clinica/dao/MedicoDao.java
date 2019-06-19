package br.com.clinica.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import br.com.clinica.model.Medico;

public class MedicoDao {

	// Parte do codigo para efetuar a persisençia no banco de dados com JPA

	private static MedicoDao instance;
	protected EntityManager entityManager;

	public static MedicoDao getInstance() {
		if (instance == null) {
			instance = new MedicoDao();
		}

		return instance;
	}

	public MedicoDao() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("clinica");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	// Metodo de salvar e atualizar
	public Medico salvar(Medico medico) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			if (medico.getIdMedico() == null) {
				em.persist(medico); // executar insert
			} else {
				if (!em.contains(medico)) {
					if (em.find(Medico.class, medico.getIdMedico()) == null) {
						throw new Exception("Erro ao atualizar");
					}
				}
				medico = em.merge(medico); // executar update
			}
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return medico;

	}

	// find() chamando o EntityManager passando o tipo de classe o id que deve
	// ser buscado, ja traz um objeto medico
	public Medico getByCrm(final String crm) {
		Medico medico = new Medico();

		try {
			if (crm != null && !crm.isEmpty()) {
				String jpql = "select m from Medico m where m.crm = :crm";
				Query query = entityManager.createQuery(jpql);
				query.setParameter("crm", crm);
				@SuppressWarnings("unchecked")
				List<Medico> medicoResutl = query.getResultList();
				medico = medicoResutl.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "CRM inexistente!!");
		}

		return medico;
	}

	public Medico getByCrmFocus(final String crm) {
		Medico medico = new Medico();

		try {
			if (crm != null && !crm.isEmpty()) {
				String jpql = "select m from Medico m where m.crm = :crm";
				Query query = entityManager.createQuery(jpql);
				query.setParameter("crm", crm);
				@SuppressWarnings("unchecked")
				List<Medico> medicoResutl = query.getResultList();
				medico = medicoResutl.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Medico Nao cadastrado!!");
		}

		return medico;
	}

	@SuppressWarnings("unchecked")
	public List<Medico> findAll() {
		return entityManager.createQuery("FROM" + Medico.class.getName()).getResultList();
	}

	// usa as configurações presentes no arquivo persistence.xml para criar uma
	// instância de EntityManagerFactory.
	// Depois disso verificamos se o atributo entityManager é nulo, ou seja,
	// nunca foi criado,
	// sendo assim usamos o createEntityManager() para criar uma instância de
	// EntityManager que é responsável
	// por realizar as operações de CRUD no banco de dados.

	public void persist(Medico medico) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(medico);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			/*
			 * printStackTrace() exception padrão para erros de leitura e
			 * gravação. Você está a capturando e usando o método
			 * printStackTrace() para, se houver algo errado, ele escrever no
			 * console o tal erro.
			 */
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	/*
	 * o metodo merge segue o mesmo principio do metodo persist(), a unica
	 * diferença é que a merge atualiza o registro e nao apenas insere no banco.
	 * Mas seo registro nao existir no banco usando esse metodo ele ira inserir
	 * o mesmo,
	 */
	public void merge(Medico medico) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(medico);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			/*
			 * printStackTrace() exception padrão para erros de leitura e
			 * gravação. Você está a capturando e usando o método
			 * printStackTrace() para, se houver algo errado, ele escrever no
			 * console o tal erro.
			 */
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	/*
	 * primeiro procura no banco de dados para só entao remover. o removebyId()
	 * recebe o id que deve ser removido enquanto que o remove recebe o objeto.
	 */

	public void remove(Medico medico) {
		try {
			entityManager.getTransaction().begin();
			medico = entityManager.find(Medico.class, medico.getIdMedico());
			entityManager.remove(medico);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final String crm) {
		try {
			Medico medico = getByCrm(crm);
			remove(medico);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
