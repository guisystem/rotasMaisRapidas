package rocha.guilherme.jose.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import rocha.guilherme.jose.controller.RotasController;

@SuppressWarnings("serial")
public class RotasView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldLocalPartida;
	private JTextField textFieldLocalDestino;
	private JRadioButton rdbtnAdicionarLocalizao;
	private JRadioButton rdbtnMinhaLocalizacao;
	private JComboBox<String> comboBoxUFPartida;
	private JComboBox<String> comboBoxUFDestino;
	
	private RotasController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RotasView frame = new RotasView();
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
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
	public RotasView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RotasView.class.getResource("/rocha/guilherme/jose/view/icone/rota.png")));
		controller = new RotasController(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 344, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMelhorRota = new JLabel("Melhor Rota");
		lblMelhorRota.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMelhorRota.setBounds(100, 28, 108, 24);
		contentPane.add(lblMelhorRota);
		
		JLabel lblLocalDePartida = new JLabel("Local de Partida:");
		lblLocalDePartida.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLocalDePartida.setBounds(28, 88, 92, 16);
		contentPane.add(lblLocalDePartida);
		
		rdbtnMinhaLocalizacao = new JRadioButton("minha localiza\u00E7\u00E3o");
		rdbtnMinhaLocalizacao.setOpaque(false);
		rdbtnMinhaLocalizacao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldLocalPartida.setEnabled(false);
				comboBoxUFPartida.setEnabled(false);
				
			}
		});
		rdbtnMinhaLocalizacao.setSelected(true);
		rdbtnMinhaLocalizacao.setFont(new Font("Arial", Font.PLAIN, 12));
		rdbtnMinhaLocalizacao.setBounds(128, 88, 125, 16);
		contentPane.add(rdbtnMinhaLocalizacao);
		
		rdbtnAdicionarLocalizao = new JRadioButton("adicionar localiza\u00E7\u00E3o");
		rdbtnAdicionarLocalizao.setOpaque(false);
		rdbtnAdicionarLocalizao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldLocalPartida.setEnabled(true);
				comboBoxUFPartida.setEnabled(true);
			}
		});
		rdbtnAdicionarLocalizao.setFont(new Font("Arial", Font.PLAIN, 12));
		rdbtnAdicionarLocalizao.setBounds(128, 112, 140, 16);
		contentPane.add(rdbtnAdicionarLocalizao);
		
		textFieldLocalPartida = new JTextField();
		textFieldLocalPartida.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldLocalPartida.setBounds(132, 137, 120, 24);
		textFieldLocalPartida.setEnabled(false);
		contentPane.add(textFieldLocalPartida);
		textFieldLocalPartida.setColumns(10);
		
		comboBoxUFPartida = new JComboBox<>();
		comboBoxUFPartida.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxUFPartida.setBounds(255, 137, 45, 24);
		comboBoxUFPartida.setEnabled(false);
		contentPane.add(comboBoxUFPartida);
		
		JLabel lblLocalDeDestino = new JLabel("Local de Destino:");
		lblLocalDeDestino.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLocalDeDestino.setBounds(28, 185, 96, 16);
		contentPane.add(lblLocalDeDestino);
		
		textFieldLocalDestino = new JTextField();
		textFieldLocalDestino.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldLocalDestino.setColumns(10);
		textFieldLocalDestino.setBounds(132, 181, 120, 24);
		contentPane.add(textFieldLocalDestino);
		
		comboBoxUFDestino = new JComboBox<>();
		comboBoxUFDestino.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxUFDestino.setBounds(255, 181, 45, 24);
		contentPane.add(comboBoxUFDestino);
		
		JButton btnBuscar = new JButton("BUSCAR TRAJETO");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.buscarTrajetoNoGoogleMaps();
			}
		});
		btnBuscar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnBuscar.setBounds(94, 229, 140, 28);
		contentPane.add(btnBuscar);
		
		ButtonGroup grupoRdb = new ButtonGroup();
		grupoRdb.add(rdbtnMinhaLocalizacao);
		grupoRdb.add(rdbtnAdicionarLocalizao);
		
		iniciar();
	}

	private void iniciar() {
		controller.preencherComboBox();
	}
	
	public void exibeMensagemInformativa(String texto) {
		JOptionPane.showMessageDialog(null, texto, "Atenção", JOptionPane.WARNING_MESSAGE);
	}
	
	public int exibeMensagemDecisao(String texto) {
		UIManager.put("OptionPane.yesButtonText", "Buscar no Mapa");
		UIManager.put("OptionPane.noButtonText", "Informar a UF");
		return JOptionPane.showConfirmDialog(null, texto, "O que deseja fazer?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	}

	public JTextField getTextFieldLocalPartida() {
		return textFieldLocalPartida;
	}

	public void setTextFieldLocalPartida(JTextField textFieldLocalPartida) {
		this.textFieldLocalPartida = textFieldLocalPartida;
	}

	public JTextField getTextFieldLocalDestino() {
		return textFieldLocalDestino;
	}

	public void setTextFieldLocalDestino(JTextField textFieldLocalDestino) {
		this.textFieldLocalDestino = textFieldLocalDestino;
	}

	public JRadioButton getRdbtnAdicionarLocalizao() {
		return rdbtnAdicionarLocalizao;
	}

	public void setRdbtnAdicionarLocalizao(JRadioButton rdbtnAdicionarLocalizao) {
		this.rdbtnAdicionarLocalizao = rdbtnAdicionarLocalizao;
	}

	public JRadioButton getRdbtnMinhaLocalizacao() {
		return rdbtnMinhaLocalizacao;
	}

	public void setRdbtnMinhaLocalizacao(JRadioButton rdbtnMinhaLocalizacao) {
		this.rdbtnMinhaLocalizacao = rdbtnMinhaLocalizacao;
	}

	public JComboBox<String> getComboBoxUFPartida() {
		return comboBoxUFPartida;
	}

	public void setComboBoxUFPartida(JComboBox<String> comboBoxUFPartida) {
		this.comboBoxUFPartida = comboBoxUFPartida;
	}

	public JComboBox<String> getComboBoxUFDestino() {
		return comboBoxUFDestino;
	}

	public void setComboBoxUFDestino(JComboBox<String> comboBoxUFDestino) {
		this.comboBoxUFDestino = comboBoxUFDestino;
	}
	
}
