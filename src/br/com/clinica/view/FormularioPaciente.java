/******************************************
 * PROJETO GERENCIAMENTO CLINICO
 * DESENVOLVEDOR: SAMUEL OLIVEIRA
 * DATA: MAIO/2017
 * TESTE COM TECNOLOGIAS JPA
 ******************************************/



package br.com.clinica.view;

import java.awt.Color;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.com.clinica.dao.PacienteDao;
import br.com.clinica.model.Paciente;

public class FormularioPaciente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtidpaciente;
	private JTextField txtnomepaciente;
	private JTextField txtdanasc;
	private JTextField txtnomemae;
	private JTextField txtnomepai;
	private JTextField txtruapaciente;
	private JTextField txtcidadepaciente;
	private JTextField txtfonepaciente;
	private JTextField txtCpF_1;
	private JTextField txtnumeroconvenio;
	private JTextField txtNumEndPaciente;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioPaciente frame = new FormularioPaciente();
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
	public FormularioPaciente() {
		setTitle("Clinica Medica");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FormularioPaciente.class.getResource("/br/com/clinica/image/paciente.png")));
		setBounds(new Rectangle(0, 0, 799, 499));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 801, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);

		JLabel lblidpaciente = new JLabel("*Id:");
		lblidpaciente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblidpaciente.setBounds(79, 48, 46, 14);
		contentPane.add(lblidpaciente);

		JLabel lblnomemae = new JLabel("Nome M\u00E3e:");
		lblnomemae.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblnomemae.setBounds(79, 170, 106, 14);
		contentPane.add(lblnomemae);

		JLabel lblnomepai = new JLabel("Nome de Pai:");
		lblnomepai.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblnomepai.setBounds(79, 211, 91, 14);
		contentPane.add(lblnomepai);

		JLabel lblobspaciente = new JLabel("Observa\u00E7ao sobre o Paciente:");
		lblobspaciente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblobspaciente.setBounds(481, 254, 202, 14);
		contentPane.add(lblobspaciente);

		JLabel lblplanosaude = new JLabel("Plano Saude:");
		lblplanosaude.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblplanosaude.setBounds(339, 368, 84, 14);
		contentPane.add(lblplanosaude);

		JLabel lblnamecadpaci = new JLabel("Cadastro de Paciente");
		lblnamecadpaci.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblnamecadpaci.setBounds(283, 11, 159, 29);
		contentPane.add(lblnamecadpaci);

		JLabel lblnumeroconvenio = new JLabel("Numero de convenio:");
		lblnumeroconvenio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblnumeroconvenio.setBounds(510, 368, 124, 14);
		contentPane.add(lblnumeroconvenio);

		txtidpaciente = new JTextField();
		txtidpaciente.setEditable(false);
		txtidpaciente.setEnabled(false);
		txtidpaciente.setBounds(107, 46, 51, 20);
		contentPane.add(txtidpaciente);
		txtidpaciente.setColumns(10);

		txtnomemae = new JTextField();
		txtnomemae.setEditable(false);
		txtnomemae.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String nomeMaeKey = "0987654321";
				if (nomeMaeKey.contains(e.getKeyChar() + "")) {
					e.consume();

				}
			}
		});
		txtnomemae.setBounds(146, 168, 332, 20);
		contentPane.add(txtnomemae);
		txtnomemae.setColumns(10);

		txtnomepai = new JTextField();
		txtnomepai.setEditable(false);
		txtnomepai.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String nomePaiKey = "0987654321";
				if (nomePaiKey.contains(e.getKeyChar() + "")) {
					if (nomePaiKey.contains(e.getKeyChar() + "")) {
						e.consume();

					}

				}
			}
		});
		txtnomepai.setBounds(156, 209, 322, 20);
		contentPane.add(txtnomepai);
		txtnomepai.setColumns(10);

		JTextPane txtobs = new JTextPane();
		txtobs.setEditable(false);
		txtobs.setBounds(491, 279, 213, 69);
		contentPane.add(txtobs);

		JComboBox<Object> cbogpsanguineo = new JComboBox<Object>();
		cbogpsanguineo.setModel(
				new DefaultComboBoxModel<Object>(new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }));
		cbogpsanguineo.setBounds(394, 46, 84, 20);
		contentPane.add(cbogpsanguineo);

		JComboBox<?> cboplanosaude = new JComboBox();
		cboplanosaude.setModel(new DefaultComboBoxModel(new String[] { "UNIMED", "BRADESCO", "PRIME" }));
		cboplanosaude.setBounds(427, 366, 73, 20);
		contentPane.add(cboplanosaude);

		txtnumeroconvenio = new JTextField();
		txtnumeroconvenio.setEditable(false);
		txtnumeroconvenio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String numConvenioKey = "0987654321";
				if (!numConvenioKey.contains(e.getKeyChar() + "")) {
					e.consume();

				}
			}
		});
		txtnumeroconvenio.setBounds(636, 366, 66, 20);
		contentPane.add(txtnumeroconvenio);
		txtnumeroconvenio.setColumns(10);

		JButton btnSavePaciente = new JButton("");
		btnSavePaciente.setEnabled(false);
		btnSavePaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Paciente paciente = new Paciente();

					// setando campos no banco informaçoes da tela
					savePaciente(txtobs, cbogpsanguineo, cboplanosaude, paciente);

					// validaçao obrigatoria de campos
					if (fieldValidation()) {
						JOptionPane.showMessageDialog(null, "*Preencha todos os dados Obrigatorios!!");

					} else {
						PacienteDao.getInstance().salvar(paciente);
						JOptionPane.showMessageDialog(null, "Salvo com sucesso");

						cleansFields();
					}

				} catch (Exception e2) {

					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro ao salvar!!");

				}
			}

		});

		btnSavePaciente.setToolTipText("Save");
		btnSavePaciente.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/br/com/clinica/image/save2.png")));
		btnSavePaciente.setBounds(600, 100, 60, 40);
		contentPane.add(btnSavePaciente);

		JButton btnnewupdate = new JButton("");
		btnnewupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnnewupdate.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/br/com/clinica/image/uptade.png")));
		btnnewupdate.setToolTipText("Atualizar");
		btnnewupdate.setBounds(600, 150, 60, 40);
		contentPane.add(btnnewupdate);

		ImageIcon icone = new ImageIcon(this.getClass().getResource("/br/com/clinica/image/userfun.jpg"));
		Image image = icone.getImage();
		JDesktopPane dskPaciente = new JDesktopPane() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics grafics) {
				grafics.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};

		dskPaciente.setBackground(Color.WHITE);
		dskPaciente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "FormularioPaciente",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		dskPaciente.setBounds(0, 0, 795, 453);
		contentPane.add(dskPaciente);
		dskPaciente.setLayout(null);

		JLabel lblgpsanguineo = new JLabel("GrupoSanguineo:");
		lblgpsanguineo.setBounds(271, 54, 106, 14);
		dskPaciente.add(lblgpsanguineo);
		lblgpsanguineo.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel lblLogopaciente = new JLabel("");
		lblLogopaciente
				.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/br/com/clinica/image/paciente.png")));
		lblLogopaciente.setBounds(20, 28, 46, 40);
		dskPaciente.add(lblLogopaciente);

		JLabel lblNewLabel = new JLabel("N\u00B0:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(371, 296, 24, 14);
		dskPaciente.add(lblNewLabel);

		txtNumEndPaciente = new JTextField();
		txtNumEndPaciente.setEditable(false);
		txtNumEndPaciente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent eve) {
				String numKey = "0987654321";
				if (!numKey.contains(eve.getKeyChar() + "")) {
					eve.consume();

				}
			}
		});
		txtNumEndPaciente.setBounds(395, 290, 80, 20);
		dskPaciente.add(txtNumEndPaciente);
		txtNumEndPaciente.setColumns(10);

		// Parte do codigo onde mascaramos os campos de CPF
		txtCpF_1 = new JTextField();
		try {
			javax.swing.text.MaskFormatter format_textField4 = new javax.swing.text.MaskFormatter("###.###.###-##");
			txtCpF_1 = new javax.swing.JFormattedTextField(format_textField4);
			txtCpF_1.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent ex) {

					try {

						PacienteDao dao = new PacienteDao();
						Paciente pacfocus = new Paciente();
						pacfocus = dao.getByfocus(txtCpF_1.getText().toString());
						setpaciente(pacfocus);

					} catch (Exception e) {

						txtCpF_1.requestFocus();

					}

				}
			});
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		txtCpF_1.setBounds(106, 89, 154, 20);
		dskPaciente.add(txtCpF_1);
		txtCpF_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String cpfKey = "0987654321";
				if (!cpfKey.contains(e.getKeyChar() + "")) {
					e.consume();

				}

			}
		});
		txtCpF_1.setColumns(10);

		JLabel llblCpF = new JLabel("CPF:");
		llblCpF.setBounds(74, 91, 91, 14);
		dskPaciente.add(llblCpF);
		llblCpF.setFont(new Font("Tahoma", Font.PLAIN, 12));

		// Parte do codigo onde mascaramos os campos de telefone
		txtfonepaciente = new JTextField();

		try {
			javax.swing.text.MaskFormatter format_textField4 = new javax.swing.text.MaskFormatter("(##).#####.####");
			txtfonepaciente = new javax.swing.JFormattedTextField(format_textField4);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		txtfonepaciente.setEditable(false);
		txtfonepaciente.setBounds(176, 365, 161, 20);
		dskPaciente.add(txtfonepaciente);
		txtfonepaciente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String fonePacienteKey = "0987654321";
				if (!fonePacienteKey.contains(e.getKeyChar() + "")) {
					e.consume();

				}
			}
		});
		txtfonepaciente.setColumns(10);

		JLabel lblfonecel = new JLabel("Telefone/Celular:");
		lblfonecel.setBounds(80, 367, 106, 14);
		dskPaciente.add(lblfonecel);
		lblfonecel.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel lblcidadepaciente = new JLabel("Cidade:");
		lblcidadepaciente.setBounds(76, 327, 46, 14);
		dskPaciente.add(lblcidadepaciente);
		lblcidadepaciente.setFont(new Font("Tahoma", Font.PLAIN, 12));

		txtcidadepaciente = new JTextField();
		txtcidadepaciente.setEditable(false);
		txtcidadepaciente.setBounds(125, 325, 350, 20);
		dskPaciente.add(txtcidadepaciente);
		txtcidadepaciente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String cidadePacienteKey = "0987654321";
				if (cidadePacienteKey.contains(e.getKeyChar() + "")) {
					e.consume();

				}
			}
		});
		txtcidadepaciente.setColumns(10);

		txtruapaciente = new JTextField();
		txtruapaciente.setEditable(false);
		txtruapaciente.setBounds(106, 290, 255, 20);
		dskPaciente.add(txtruapaciente);
		txtruapaciente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String ruaPacienteKey = "0987654321";
				if (ruaPacienteKey.contains(e.getKeyChar() + "")) {
					e.consume();

				}
			}
		});
		txtruapaciente.setColumns(10);

		JLabel lblruapaciente = new JLabel("Rua:");
		lblruapaciente.setBounds(76, 292, 46, 14);
		dskPaciente.add(lblruapaciente);
		lblruapaciente.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel lbldatnascimento = new JLabel("Data de Nascimento:");
		lbldatnascimento.setBounds(76, 254, 124, 14);
		dskPaciente.add(lbldatnascimento);
		lbldatnascimento.setFont(new Font("Tahoma", Font.PLAIN, 12));

		txtdanasc = new JTextField();
		// Parte do codigo onde mascaramos os campos de Data de Nascimento

		try {
			javax.swing.text.MaskFormatter format_textField4 = new javax.swing.text.MaskFormatter("##/##/####");
			txtdanasc = new javax.swing.JFormattedTextField(format_textField4);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		txtdanasc.setEditable(false);
		txtdanasc.setBounds(202, 252, 273, 20);
		dskPaciente.add(txtdanasc);
		txtdanasc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String dataNasKey = "0987654321//";
				if (!dataNasKey.contains(e.getKeyChar() + "")) {
					e.consume();

				}
			}
		});
		txtdanasc.setColumns(10);

		txtnomepaciente = new JTextField();
		txtnomepaciente.setEditable(false);
		txtnomepaciente.setBounds(115, 126, 361, 20);
		dskPaciente.add(txtnomepaciente);
		txtnomepaciente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String pacienteKey = "0987654321";
				if (pacienteKey.contains(e.getKeyChar() + "")) {
					e.consume();

				}
			}
		});
		txtnomepaciente.setColumns(10);

		JLabel lblnomepaciente = new JLabel("Nome:");
		lblnomepaciente.setBounds(77, 128, 46, 14);
		dskPaciente.add(lblnomepaciente);
		lblnomepaciente.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel lblpesquisaViaCpf = new JLabel("*pesquisa via cpf");
		lblpesquisaViaCpf.setBounds(106, 71, 100, 14);
		dskPaciente.add(lblpesquisaViaCpf);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setBounds(601, 198, 60, 40);
		dskPaciente.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				PacienteDao dao = new PacienteDao();
				Paciente paciente = new Paciente();
				paciente = dao.getByCpf(txtCpF_1.getText().toString());

				setpaciente(paciente);

			}

		});
		btnNewButton_3
				.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/br/com/clinica/image/procurar.png")));

		JButton btnnewpaciente = new JButton("");
		btnnewpaciente.setBounds(601, 40, 60, 40);
		dskPaciente.add(btnnewpaciente);
		btnnewpaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// ativando campos
				fieldActivating();
				btnSavePaciente.setEnabled(true);
			}
		});

		btnnewpaciente.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/br/com/clinica/image/new.png")));

		// VALIDANDO CAMPOS DE E-MAIL.
		txtEmail = new JTextField();
		txtEmail.setEnabled(false);
		txtEmail.setEditable(false);
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if ((txtEmail.getText().contains("@")) && (txtEmail.getText().contains("."))
						&& (!txtEmail.getText().contains(" "))) {

					String usuario = new String(txtEmail.getText().substring(0, txtEmail.getText().lastIndexOf('@')));
					String dominio = new String(txtEmail.getText().substring(txtEmail.getText().lastIndexOf('@') + 1,
							txtEmail.getText().length()));

					if ((usuario.length() >= 1) && (!usuario.contains("@")) && (dominio.contains("."))
							&& (!dominio.contains("@")) && (dominio.indexOf(".") >= 1)
							&& (dominio.lastIndexOf(".") < dominio.length() - 1)) {

						Paciente paciente = new Paciente();
						paciente.setEmail(txtEmail.getText());

					} else {

						txtEmail.setText("E-mail Inválido");
						txtEmail.requestFocus();
					}
				} else {
					txtEmail.setText("E-Mail Invalido");
					txtEmail.requestFocus();
				}

			}
		});
		txtEmail.setBounds(309, 89, 167, 20);
		dskPaciente.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("E-MAIL");
		lblNewLabel_1.setBounds(271, 92, 46, 14);
		dskPaciente.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("?");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					txtEmail.setEnabled(true);
				} else {
					txtEmail.setEnabled(false);
					txtEmail.setActionCommand(null);
				}
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBounds(486, 88, 37, 20);
		dskPaciente.add(btnNewButton);

		// METODOS DO ESCOPO DO PROGRAMA
	}

	private boolean fieldValidation() {
		return txtnomepaciente.getText().isEmpty() || (txtdanasc.getText().isEmpty() || txtnomemae.getText().isEmpty())
				|| txtfonepaciente.getText().isEmpty() || txtCpF_1.getText().isEmpty();
	}

	private void cleansFields() {
		// Limpando campos
		txtCpF_1.setText(null);
		txtnomepaciente.setText(null);
		txtdanasc.setText(null);
		txtnomemae.setText(null);
		txtnomepai.setText(null);
		txtruapaciente.setText(null);
		txtcidadepaciente.setText(null);
		txtfonepaciente.setText(null);
		txtEmail.setText(null);
		txtCpF_1.setText(null);
		txtnumeroconvenio.setText(null);

		// Desativando campos

		txtnomepaciente.setEditable(false);
		txtdanasc.setEditable(false);
		txtnomemae.setEditable(false);
		txtnomepai.setEditable(false);
		txtruapaciente.setEditable(false);
		txtcidadepaciente.setEditable(false);
		txtfonepaciente.setEditable(false);
		txtEmail.setEditable(false);
		txtCpF_1.setEditable(false);
		txtnumeroconvenio.setEditable(false);
	}

	private void savePaciente(JTextPane txtobs, JComboBox<Object> cbogpsanguineo, JComboBox<?> cboplanosaude,
			Paciente paciente) {
		paciente.setGpsaguineo((String) cbogpsanguineo.getSelectedItem());
		paciente.setNomepaciente(txtnomepaciente.getText());
		paciente.setNumeroCasa(txtNumEndPaciente.getText());
		paciente.setDatanascimento(txtdanasc.getText());
		paciente.setNomemae(txtnomemae.getText());
		paciente.setNomepai(txtnomepai.getText());
		paciente.setRua(txtruapaciente.getText());
		paciente.setCidade(txtcidadepaciente.getText());
		paciente.setFone(txtfonepaciente.getText());
		paciente.setEmail(txtEmail.getText());
		paciente.setCpf(txtCpF_1.getText());
		paciente.setPlanosaude((String) cboplanosaude.getSelectedItem());
		paciente.setNumeroconvenio(txtnumeroconvenio.getText());
		paciente.setObservacao(txtobs.getText());
	}

	private void fieldActivating() {
		// ativando campos

		txtnomepaciente.setEditable(true);
		txtdanasc.setEditable(true);
		txtnomemae.setEditable(true);
		txtnomepai.setEditable(true);
		txtruapaciente.setEditable(true);
		txtcidadepaciente.setEditable(true);
		txtfonepaciente.setEditable(true);
		txtEmail.setEditable(true);
		txtNumEndPaciente.setEditable(true);
		txtCpF_1.setEditable(true);
		txtnumeroconvenio.setEditable(true);

		txtnomepaciente.setText(null);
		txtCpF_1.setText(null);
		txtdanasc.setText(null);
		txtnomemae.setText(null);
		txtnomepai.setText(null);
		txtruapaciente.setText(null);
		txtcidadepaciente.setText(null);
		txtfonepaciente.setText(null);
		txtEmail.setText(null);
		txtnumeroconvenio.setText(null);

	}

	private void setpaciente(Paciente paciente) {
		txtnomepaciente.setText(paciente.getNomepaciente());
		txtdanasc.setText(paciente.getDatanascimento());
		txtnomemae.setText(paciente.getNomemae());
		txtnomepai.setText(paciente.getNomepai());
		txtruapaciente.setText(paciente.getRua());
		txtcidadepaciente.setText(paciente.getCidade());
		txtfonepaciente.setText(paciente.getFone());
		txtEmail.setText(paciente.getEmail());
		txtnumeroconvenio.setText(paciente.getNumeroconvenio());
		txtNumEndPaciente.setText(paciente.getNumeroCasa());
	}
}
