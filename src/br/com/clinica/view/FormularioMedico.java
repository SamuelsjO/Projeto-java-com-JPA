package br.com.clinica.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import br.com.clinica.dao.MedicoDao;
import br.com.clinica.model.Medico;

public class FormularioMedico extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextField txtNomeMedico;
	private JTextField txtCrmMedico;
	private JTextField txtidMedico;
	private JTextField txtCpfMedico;
	private JTextField txtCpfMedico_1;
	private JTextField txtNomeMaeMedico;
	private JTextField txtNomeRuaMedico;
	private JTextField txtCidadeMedico;
	private JTextField txtTelCelularMedico;
	private JTextField txtTelCelularMedico_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioMedico frame = new FormularioMedico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormularioMedico() {
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FormularioMedico.class.getResource("/br/com/clinica/image/doctor.png")));
		setBounds(new Rectangle(0, 0, 499, 799));
		setFont(new Font("Dialog", Font.BOLD, 12));
		setTitle("Clinica Santa Maria");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 801, 494);
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(0, 0, 490, 790));
		contentPane.setBorder(new TitledBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "CadastroMedico", TitledBorder.LEADING,
						TitledBorder.TOP, null, new Color(0, 0, 0)),
				"", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);

		JLabel lblNamecadme = new JLabel("Cadastro Medico Clinico");
		lblNamecadme.setBounds(313, 29, 204, 14);
		lblNamecadme.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNamecadme);

		// adicionando novo medico
		JButton btnnew = new JButton("");
		btnnew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fieldsActivin();
			}

		});
		btnnew.setBounds(644, 108, 60, 40);
		btnnew.setIcon(new ImageIcon(FormularioMedico.class.getResource("/br/com/clinica/image/new.png")));
		btnnew.setToolTipText("Novo");
		contentPane.add(btnnew);

		// salvando dados
		JButton btnsaveMedico = new JButton("");
		btnsaveMedico.setBounds(644, 159, 60, 40);
		btnsaveMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Medico medico = new Medico();

					savingMedico(medico);
					// validacao dos campos obrigatorios

					if (fieldsValidation()) {

						JOptionPane.showMessageDialog(null, "*Preencha todos os dados Obrigatorios!!");
					} else {
						MedicoDao.getInstance().salvar(medico);
						JOptionPane.showMessageDialog(null, "Salvo com sucesso");

						cleanFieldsEditableF();
					}

				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro ao salvar Medico");
				}

			}

		});

		btnsaveMedico.setIcon(new ImageIcon(FormularioMedico.class.getResource("/br/com/clinica/image/save2.png")));
		btnsaveMedico.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnsaveMedico.setToolTipText("Save");
		contentPane.add(btnsaveMedico);

		JButton btnpesquisamedico = new JButton("");
		btnpesquisamedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MedicoDao dao = new MedicoDao();
				Medico medico = new Medico();
				medico = dao.getByCrm(txtCrmMedico.getText().toString());

				setMedico(medico);

			}
		});
		btnpesquisamedico.setBounds(644, 260, 60, 40);
		btnpesquisamedico.setToolTipText("Pesquisar");
		btnpesquisamedico
				.setIcon(new ImageIcon(FormularioMedico.class.getResource("/br/com/clinica/image/procurar.png")));
		contentPane.add(btnpesquisamedico);

		ImageIcon icone = new ImageIcon(this.getClass().getResource("/br/com/clinica/image/userfun.jpg"));
		Image imga = icone.getImage();

		JLabel lblNewLabel = new JLabel("*Nome da Mae:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(31, 211, 108, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("*Rua:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(31, 236, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("*Cidade:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(31, 266, 86, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Telefone/Celular:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(31, 301, 126, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("*CPF:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(31, 185, 46, 14);
		contentPane.add(lblNewLabel_4);

		// Parte do codigo onde mascaramos o campo cpf do medico
		txtCpfMedico = new JTextField();
		try {
			javax.swing.text.MaskFormatter format_textField4 = new javax.swing.text.MaskFormatter("###.###.###-##");
			txtCpfMedico_1 = new javax.swing.JFormattedTextField(format_textField4);
			txtCpfMedico_1.setEditable(false);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		txtCpfMedico_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String keyCpf = "0987654321";

				if (!keyCpf.contains(e.getKeyChar() + "")) {
					e.consume();

				}
			}
		});
		txtCpfMedico_1.setColumns(10);
		txtCpfMedico_1.setBounds(89, 176, 244, 20);
		contentPane.add(txtCpfMedico_1);

		txtNomeMaeMedico = new JTextField();
		txtNomeMaeMedico.setEditable(false);
		txtNomeMaeMedico.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String maeMedico = "0987654321";
				if (maeMedico.contains(e.getKeyChar() + "")) {
					e.consume();

				}
			}
		});
		txtNomeMaeMedico.setColumns(10);
		txtNomeMaeMedico.setBounds(135, 210, 409, 20);
		contentPane.add(txtNomeMaeMedico);

		txtNomeRuaMedico = new JTextField();
		txtNomeRuaMedico.setEditable(false);
		txtNomeRuaMedico.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String ruaMedico = "0987654321";
				if (ruaMedico.contains(e.getKeyChar() + "")) {
					e.consume();

				}
			}
		});
		txtNomeRuaMedico.setColumns(10);
		txtNomeRuaMedico.setBounds(89, 236, 455, 20);
		contentPane.add(txtNomeRuaMedico);

		txtCidadeMedico = new JTextField();
		txtCidadeMedico.setEditable(false);
		txtCidadeMedico.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String cidMedico = "0987654321";
				if (cidMedico.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtCidadeMedico.setColumns(10);
		txtCidadeMedico.setBounds(145, 264, 399, 20);
		contentPane.add(txtCidadeMedico);

		txtTelCelularMedico = new JTextField();
		// validar mascara de Celular/telefone Medico

		try {
			javax.swing.text.MaskFormatter format_textField4 = new javax.swing.text.MaskFormatter("(##).#####.####");
			txtTelCelularMedico_1 = new javax.swing.JFormattedTextField(format_textField4);
			txtTelCelularMedico_1.setEditable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		txtTelCelularMedico_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {
				String telCelular = "0987654321";
				if (!telCelular.contains(ev.getKeyChar() + "")) {
					ev.consume();
				}
			}
		});
		txtTelCelularMedico_1.setColumns(10);
		txtTelCelularMedico_1.setBounds(145, 299, 399, 20);
		contentPane.add(txtTelCelularMedico_1);
		JDesktopPane deskMedico_1 = new JDesktopPane() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics gra) {
				gra.drawImage(imga, 0, 0, getWidth(), getHeight(), this);
			}
		};
		deskMedico_1.setBackground(Color.WHITE);
		deskMedico_1.setBounds(0, 0, 795, 483);
		deskMedico_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "FormularioMedico",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(deskMedico_1);

		JLabel lblNewLabel_5 = new JLabel("**Campos com restri\u00E7oes de letras ou numero");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_5.setBounds(538, 50, 227, 14);
		deskMedico_1.add(lblNewLabel_5);

		JLabel lblcamObrig = new JLabel("*Campos Obrigatorio");
		lblcamObrig.setBounds(538, 31, 108, 14);
		deskMedico_1.add(lblcamObrig);
		lblcamObrig.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JLabel lblLogoMe = new JLabel("");
		lblLogoMe.setIcon(new ImageIcon(FormularioMedico.class.getResource("/br/com/clinica/image/medica.png")));
		lblLogoMe.setBounds(31, 31, 58, 63);
		deskMedico_1.add(lblLogoMe);

		txtidMedico = new JTextField();
		txtidMedico.setBounds(499, 139, 47, 20);
		deskMedico_1.add(txtidMedico);
		txtidMedico.setEnabled(false);
		txtidMedico.setEditable(false);
		txtidMedico.setColumns(10);

		JLabel lblidmedico = new JLabel("Id:");
		lblidmedico.setBounds(464, 141, 33, 14);
		deskMedico_1.add(lblidmedico);
		lblidmedico.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtNomeMedico = new JTextField();
		txtNomeMedico.setEditable(false);
		txtNomeMedico.setBounds(89, 139, 365, 20);
		deskMedico_1.add(txtNomeMedico);
		txtNomeMedico.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String nomeLetras = "0987654321";
				if (nomeLetras.contains(e.getKeyChar() + "")) {
					e.consume();

				}
			}
		});
		txtNomeMedico.setColumns(10);

		JLabel lblnomemedico = new JLabel("*Nome:");
		lblnomemedico.setBounds(31, 141, 46, 14);
		deskMedico_1.add(lblnomemedico);
		lblnomemedico.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtCrmMedico = new JTextField();
		txtCrmMedico.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					MedicoDao dao = new MedicoDao();
					Medico medfocus = new Medico();
					medfocus = dao.getByCrmFocus(txtCrmMedico.getText().toString());

					setMedico(medfocus);

				} catch (Exception e) {
					txtCrmMedico.requestFocus();

				}

			}
		});
		txtCrmMedico.setBounds(88, 105, 244, 20);
		deskMedico_1.add(txtCrmMedico);
		txtCrmMedico.addKeyListener(new KeyAdapter() {

		});
		txtCrmMedico.setColumns(10);

		JLabel lblcrm = new JLabel("*Crm:");
		lblcrm.setBounds(31, 107, 46, 14);
		deskMedico_1.add(lblcrm);
		lblcrm.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblespecmedico = new JLabel("*Especializa\u00E7ao:");
		lblespecmedico.setBounds(342, 107, 101, 14);
		deskMedico_1.add(lblespecmedico);
		lblespecmedico.setFont(new Font("Tahoma", Font.BOLD, 12));

		JComboBox<Object> cboespecmedica = new JComboBox<Object>();
		cboespecmedica.setBounds(454, 105, 90, 20);
		deskMedico_1.add(cboespecmedica);
		cboespecmedica.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "Psicologo", "Psiquiatra", "Clinico Geral", "NeuroCirugiao", "Obstetricia " }));

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(644, 204, 60, 40);
		deskMedico_1.add(btnNewButton_2);
		btnNewButton_2.setIcon(new ImageIcon(FormularioMedico.class.getResource("/br/com/clinica/image/uptade.png")));
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { contentPane, lblnomemedico, lblcrm,
				lblespecmedico, lblidmedico, txtNomeMedico, txtCrmMedico, txtidMedico, cboespecmedica, lblNamecadme,
				lblcamObrig, btnnew, btnsaveMedico, btnNewButton_2, btnpesquisamedico, deskMedico_1 }));
	}

	// METODOS RELACIONADOS ESCOPO DO PROGRAMA

	private void fieldsActivin() {
		// Ativando os campos
		txtNomeMedico.setEditable(true);
		txtCrmMedico.setEditable(true);
		txtCpfMedico_1.setEditable(true);
		txtNomeMaeMedico.setEditable(true);
		txtNomeRuaMedico.setEditable(true);
		txtCidadeMedico.setEditable(true);
		txtTelCelularMedico_1.setEditable(true);

		// Limpando campos
		txtNomeMedico.setText(null);
		txtCrmMedico.setText(null);
		txtCpfMedico_1.setText(null);
		txtNomeMaeMedico.setText(null);
		txtNomeRuaMedico.setText(null);
		txtCidadeMedico.setText(null);
		txtTelCelularMedico_1.setText(null);
	}

	private void cleanFieldsEditableF() {

		// desativa os campos
		txtNomeMedico.setEditable(false);
		txtCrmMedico.setEditable(false);
		txtCpfMedico_1.setEditable(false);
		txtNomeMaeMedico.setEditable(false);
		txtNomeRuaMedico.setEditable(false);
		txtCidadeMedico.setEditable(false);
		txtTelCelularMedico_1.setEditable(false);
	}

	// VALIDANDO CAMPOS

	private boolean fieldsValidation() {
		return (txtNomeMedico.getText().isEmpty()) || (txtCrmMedico.getText().isEmpty())
				|| (txtCpfMedico_1.getText().isEmpty()) || (txtNomeMaeMedico.getText().isEmpty())
				|| (txtNomeRuaMedico.getText().isEmpty()) || (txtCidadeMedico.getText().isEmpty())
				|| (txtTelCelularMedico_1.getText().isEmpty());
	}

	// SALVANDO MEDICO EM BANCO DE DADOS
	@SuppressWarnings("unused")
	private void savingMedico(Medico medico) {
		medico.setNome(txtNomeMedico.getText());
		medico.setCrm(txtCrmMedico.getText());
		// medico.setEspecialidade(cboespecmedica.getSelectedItem().toString());
		medico.setCpfMedico(txtCpfMedico_1.getText());
		medico.setNomeMaeMedico(txtNomeMaeMedico.getText());
		medico.setNomeRuaMedico(txtNomeRuaMedico.getText());
		medico.setNomeCidade(txtCidadeMedico.getText());
		medico.setTelefone(txtTelCelularMedico_1.getText());
	}

	private void setMedico(Medico medico) {

		txtNomeMedico.setText(medico.getNome());
		txtCpfMedico_1.setText(medico.getCpfMedico());
		txtNomeMaeMedico.setText(medico.getNomeMaeMedico());
		txtNomeRuaMedico.setText(medico.getNomeRuaMedico());
		txtCidadeMedico.setText(medico.getNomeCidade());
		txtTelCelularMedico_1.setText(medico.getTelefone());
	}
}
