package br.com.clinica.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import br.com.clinica.model.Paciente;

public class PacienteDao {
	private static PacienteDao instance;
	protected EntityManager entityManager;

	public static PacienteDao getInstance() {
		if (instance == null) {
			instance = new PacienteDao();
		}

		return instance;
	}

	public PacienteDao() {
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
	public Paciente salvar(Paciente paciente) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			if (paciente.getIdpaciente() == null) {
				em.persist(paciente); // executar insert
			} else {
				if (!em.contains(paciente)) {
					if (em.find(Paciente.class, paciente.getIdpaciente()) == null) {
						throw new Exception("Erro ao atualizar");
					}
				}
				paciente = em.merge(paciente); // executar update
			}
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return paciente;

	}

	// find() chamando o EntityManager passando o tipo de classe o id que deve
	// ser buscado, ja traz um objeto medico

	public Paciente getById(final int idPaciente) {
		return entityManager.find(Paciente.class, idPaciente);
	}

	public Paciente getByCpf(final String cpf) {
		Paciente paciente = new Paciente();

		try {
			if (cpf != null && !cpf.isEmpty()) {
				String jpql = "select p from Paciente p where p.cpf = :cpf";
				Query query = entityManager.createQuery(jpql);
				query.setParameter("cpf", cpf);
				@SuppressWarnings("unchecked")
				List<Paciente> pacienteResult = query.getResultList();

				paciente = pacienteResult.get(0);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "CPF inexistente!!!");
		}
		return paciente;

	}

	public Paciente getByfocus(final String cpf) {
		Paciente pacfocus = new Paciente();

		try {
			if (cpf != null && !cpf.isEmpty()) {
				String jpql = "select p from Paciente p where p.cpf = :cpf";
				Query query = entityManager.createQuery(jpql);
				query.setParameter("cpf", cpf);
				@SuppressWarnings("unchecked")
				List<Paciente> pacienteResult = query.getResultList();

				pacfocus = pacienteResult.get(0);
			}
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Cliente nao cadastrado!!!");
		}
		return pacfocus;
	}

	@SuppressWarnings("unchecked")
	public List<Paciente> findAll() {
		return entityManager.createQuery("FROM" + Paciente.class.getName()).getResultList();
	}

	// usa as configura��es presentes no arquivo persistence.xml para criar uma
	// inst�ncia de EntityManagerFactory.
	// Depois disso verificamos se o atributo entityManager � nulo, ou seja,
	// nunca foi criado,
	// sendo assim usamos o createEntityManager() para criar uma inst�ncia de
	// EntityManager que � respons�vel
	// por realizar as opera��es de CRUD no banco de dados.

	public void persist(Paciente paciente) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(paciente);
			entityManager.getTransaction().commit();

		} catch (Exception e2) {
			/*
			 * printStackTrace() exception padr�o para erros de leitura e
			 * grava��o. Voc� estar� capturando e usando o m�todo
			 * printStackTrace() para, se houver algo errado, ele escreve no
			 * console o tal erro.
			 */
			e2.printStackTrace();
			entityManager.getTransaction().rollback();

		}
	}

	/*
	 * o metodo merge segue o mesmo principio do metodo persist(), a unica
	 * diferen�a � que a merge atualiza o registro e nao apenas insere no banco.
	 * Mas se registro nao existir no banco usando esse metodo ele ira inserir o
	 * mesmo,
	 */
	public void merge(Paciente paciente) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(paciente);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			/*
			 * printStackTrace() exception padr�o para erros de leitura e
			 * grava��o. Voc� est� a capturando e usando o m�todo
			 * printStackTrace() para, se houver algo errado, ele escrever no
			 * console o tal erro.
			 */
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	/*
	 * primeiro procura no banco de dados para s� entao remover. o removebyId()
	 * recebe o id que deve ser removido enquanto que o remove recebe o objeto.
	 */

	public void remove(Long Idpaciente) {
		EntityManager em = getEntityManager();
		Paciente paciente = em.find(Paciente.class, Idpaciente);
		try {
			em.getTransaction().begin();
			em.remove(paciente);// executa remove
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erroo");
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int idPaciente) {
		try {
			@SuppressWarnings("unused")
			Paciente paciente = getById(idPaciente);
			// remove(paciente);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ");
		}

	}

}
