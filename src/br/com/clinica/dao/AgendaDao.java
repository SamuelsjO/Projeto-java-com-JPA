package br.com.clinica.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import br.com.clinica.model.FichaMedica;

//teste Commit
public class AgendaDao {
	// Parte do codigo para efetuar a persisençia no banco de dados com JPA

	private static AgendaDao instance;
	protected EntityManager entityManager;

	public static AgendaDao getInstance() {
		if (instance == null) {
			instance = new AgendaDao();
		}

		return instance;
	}

	public AgendaDao() {
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
	public FichaMedica salvar(FichaMedica agenda) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			if (agenda.getIdAgenda() == null) {
				em.persist(agenda); // executar insert
			} else {
				if (!em.contains(agenda)) {
					if (em.find(FichaMedica.class, agenda.getIdAgenda()) == null) {
						throw new Exception("Erro ao atualizar");
					}
				}
				agenda = em.merge(agenda); // executar update
			}
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return agenda;

	}

	// find() chamando o EntityManager passando o tipo de classe o id que deve
	// ser buscado, ja traz um objeto agenda
	public FichaMedica getByidAgenda(final Long idAgenda) {
		FichaMedica agenda = new FichaMedica();

		try {
			if (idAgenda != null && !idAgenda.toString().isEmpty()) {
				String jpql = "select a from Agenda a where a.idAgenda = :idagenda";
				Query query = entityManager.createQuery(jpql);
				query.setParameter("idagenda", idAgenda);
				@SuppressWarnings("unchecked")
				List<FichaMedica> agendaResutl = query.getResultList();
				agenda = agendaResutl.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "CRM inexistente!!");
		}

		return agenda;
	}

	@SuppressWarnings("unchecked")
	public List<FichaMedica> findAll() {
		return entityManager.createQuery("FROM" + FichaMedica.class.getName()).getResultList();
	}

	// usa as configurações presentes no arquivo persistence.xml para criar uma
	// instância de EntityManagerFactory.
	// Depois disso verificamos se o atributo entityManager é nulo, ou seja,
	// nunca foi criado,
	// sendo assim usamos o createEntityManager() para criar uma instância de
	// EntityManager que é responsável
	// por realizar as operações de CRUD no banco de dados.

	public void persist(FichaMedica agenda) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(agenda);
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
	public void merge(FichaMedica agenda) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(agenda);
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

	public void remove(FichaMedica agenda) {
		try {
			entityManager.getTransaction().begin();
			agenda = entityManager.find(FichaMedica.class, agenda.getIdAgenda());
			entityManager.remove(agenda);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final Long idAgenda) {
		try {
			FichaMedica agenda = getByidAgenda(idAgenda);
			remove(agenda);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
