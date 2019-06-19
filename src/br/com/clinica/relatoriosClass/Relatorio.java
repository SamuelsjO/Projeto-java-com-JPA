package br.com.clinica.relatoriosClass;

import java.sql.Connection;

import javax.swing.JOptionPane;

import br.com.clinica.dao.ConnectionFactory;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Relatorio {
	Connection conexao = null;

	public void GeraRelatorioPaciente() {
		conexao = ConnectionFactory.conector();

		int confirm = JOptionPane.showConfirmDialog(null, "Confirma a Emissao relatorio de Paciente?", null,
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			try {
				JasperPrint print = JasperFillManager
						.fillReport("D:/nova/faculdade 4°/ProjectoClinica/ClinicaRel/Pacientes.jasper", null, conexao);
				JasperViewer.viewReport(print, false);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}

		}

	}

	public void GeraRelatorioMedico() {

		conexao = ConnectionFactory.conector();

		int confirm = JOptionPane.showConfirmDialog(null, "Confirma a Emissao relatorio de Medicos?", null,
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			try {
				JasperPrint print = JasperFillManager
						.fillReport("br/com/clinica/relatorios/Medicos.jasper", null, conexao);
				JasperViewer.viewReport(print, false);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}

		}

	}

	public void GeraRelatorioUsuario() {

		conexao = ConnectionFactory.conector();

		int confirm = JOptionPane.showConfirmDialog(null, "Confirma a emissao relatorio usuario?", null,
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {

			try {
				JasperPrint print = JasperFillManager
						.fillReport("D:/nova/faculdade 4°/ProjectoClinica/ClinicaRel/usuarios.jasper", null, conexao);
				JasperViewer.viewReport(print, false);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}

	public void ImprimirRelatorio() {
		conexao = ConnectionFactory.conector();

		int confirm = JOptionPane.showConfirmDialog(null, "Confirma impressao?", null, JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			try {
				JasperPrint print = JasperFillManager.fillReport(
						"D:/nova/faculdade 4°/ProjectoClinica/ClinicaRel/FormularioMedico.jasper", null, conexao);
				JasperViewer.viewReport(print, false);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}

		}
	}

}
