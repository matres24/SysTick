package okienko2;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import java.awt.Color;

public class okno extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCvr;
	private JTextField RVR_text;
	private JTextField text_CSR;
	private JTextField text_enable;
	private JTextField text_source;
	private JTextField text_init;
	private JTextField text_countflag;
	private JTextField textDelay;
	private JTextField textCount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					okno frame = new okno();
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
	public okno() {
		setResizable(false);
		SysTick s = new SysTick();
		Generator g = new Generator();
		g.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtCvr = new JTextField();
		txtCvr.setEditable(false);
		txtCvr.setToolTipText("CVR");
		txtCvr.setBounds(57, 6, 138, 34);
		panel.add(txtCvr);
		txtCvr.setColumns(10);
		//??txtCvr.setText(String.valueOf(s.getCVR()));
		
		RVR_text = new JTextField();
		RVR_text.setEditable(false);
		RVR_text.setToolTipText("RVR");
		RVR_text.setBounds(57, 41, 138, 34);
		panel.add(RVR_text);
		RVR_text.setColumns(10);
		//??RVR_text.setText(String.valueOf(s.getRVR()));
		
		JRadioButton button_enable = new JRadioButton("SysTick Enable");
		button_enable.setBounds(16, 116, 113, 34);
		button_enable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(button_enable.isSelected()==true) {
					s.setEnable();
				}else {
					s.setDisable();
				}
				
			}
		});
		panel.add(button_enable);
		
		JRadioButton button__source = new JRadioButton("Source");
		button__source.setBounds(16, 152, 87, 34);
		button__source.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(button__source.isSelected()==true) {
					s.setSourceInternal();
				}else {
					s.setSourceExternal();
				}
			}
		});
		panel.add(button__source);
		
		JRadioButton button_tickinit = new JRadioButton("TickInit");
		button_tickinit.setBounds(16, 188, 87, 34);
		button_tickinit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(button_tickinit.isSelected()==true) {
					s.setInterruptEnable();
					
				}else {
					s.setInterruptDisable();
				}
			}
		});
		panel.add(button_tickinit);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(251, 206, 76, 20);
		panel.add(spinner);
		
		JButton btn_setCVR = new JButton("SetCVR");
		btn_setCVR.setBounds(337, 182, 87, 21);
		panel.add(btn_setCVR);
		btn_setCVR.addActionListener(e->{
			s.setCVR((Integer)spinner.getValue());
			//txtCvr.setText(String.valueOf(s.getCVR()));
		});
		
		JButton btn_setRVR = new JButton("SetRVR");
		btn_setRVR.setBounds(337, 205, 87, 21);
		panel.add(btn_setRVR);
		btn_setRVR.addActionListener(e->{
			s.setRVR((Integer)spinner.getValue());
			//RVR_text.setText(String.valueOf(s.getRVR()));
		});
		
		JButton btn_setCSR = new JButton("SetCSR");
		btn_setCSR.setBounds(337, 230, 87, 21);
		panel.add(btn_setCSR);
		btn_setCSR.addActionListener(e->{
			s.setCSR((Integer)spinner.getValue());
			//text_CSR.setText(String.valueOf(s.getCSR()));
			
		});
		
		JButton btn_tickint = new JButton("TickInt");
		btn_tickint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s.tickInternal();
			}
		});
		btn_tickint.setBounds(364, 11, 87, 21);
		panel.add(btn_tickint);
		
		JButton btn_tickext = new JButton("TickExt");
		btn_tickext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s.tickExternal();
			}
		});
		btn_tickext.setBounds(364, 35, 87, 21);
		panel.add(btn_tickext);
		
		text_CSR = new JTextField();
		text_CSR.setEditable(false);
		text_CSR.setToolTipText("CSR");
		text_CSR.setColumns(10);
		text_CSR.setBounds(57, 76, 138, 34);
		//text_CSR.setText(String.valueOf(s.viewCSR()));
		
		panel.add(text_CSR);
		
		text_enable = new JTextField();
		text_enable.setEditable(false);
		text_enable.setToolTipText("ENABLE");
		text_enable.setColumns(10);
		text_enable.setBounds(228, 10, 61, 20);
		//text_enable.setText(String.valueOf(s.isEnableFlag()));
		panel.add(text_enable);
		
		text_source = new JTextField();
		text_source.setEditable(false);
		text_source.setToolTipText("Source");
		text_source.setColumns(10);
		text_source.setBounds(228, 36, 61, 20);
		//text_source.setText(String.valueOf(s.isSourceFlag()));
		panel.add(text_source);
		
		text_init = new JTextField();
		text_init.setEditable(false);
		text_init.setToolTipText("Interrupt");
		text_init.setColumns(10);
		text_init.setBounds(228, 66, 61, 20);
		//text_init.setText(String.valueOf(s.isInterruptFlag()));
		
		panel.add(text_init);
		
		text_countflag = new JTextField();
		text_countflag.setEditable(false);
		text_countflag.setToolTipText("Countflag");
		text_countflag.setColumns(10);
		text_countflag.setBounds(228, 89, 61, 20);
		panel.add(text_countflag);
		//text_countflag.setText(String.valueOf(s.isCountFlag()));
		
		JTextArea txtrEnable = new JTextArea();
		txtrEnable.setBackground(new Color(192, 192, 192));
		txtrEnable.setText("Enable");
		txtrEnable.setEditable(false);
		txtrEnable.setBounds(299, 12, 55, 16);
		panel.add(txtrEnable);
		
		JTextArea txtrSource = new JTextArea();
		txtrSource.setText("Source");
		txtrSource.setEditable(false);
		txtrSource.setBackground(Color.LIGHT_GRAY);
		txtrSource.setBounds(299, 38, 55, 16);
		panel.add(txtrSource);
		
		JTextArea txtrInit = new JTextArea();
		txtrInit.setText("Init");
		txtrInit.setEditable(false);
		txtrInit.setBackground(Color.LIGHT_GRAY);
		txtrInit.setBounds(299, 68, 55, 16);
		panel.add(txtrInit);
		
		JTextArea txtrCountflag = new JTextArea();
		txtrCountflag.setText("Countflag");
		txtrCountflag.setEditable(false);
		txtrCountflag.setBackground(Color.LIGHT_GRAY);
		txtrCountflag.setBounds(299, 91, 55, 16);
		panel.add(txtrCountflag);
		
		JTextArea txtrCvr = new JTextArea();
		txtrCvr.setBackground(Color.LIGHT_GRAY);
		txtrCvr.setEditable(false);
		txtrCvr.setText("CVR");
		txtrCvr.setBounds(16, 24, 31, 16);
		panel.add(txtrCvr);
		
		JTextArea txtrRvr = new JTextArea();
		txtrRvr.setBackground(Color.LIGHT_GRAY);
		txtrRvr.setEditable(false);
		txtrRvr.setText("RVR");
		txtrRvr.setBounds(16, 59, 31, 16);
		panel.add(txtrRvr);
		
		JTextArea txtrCsr = new JTextArea();
		txtrCsr.setBackground(Color.LIGHT_GRAY);
		txtrCsr.setEditable(false);
		txtrCsr.setText("CSR");
		txtrCsr.setBounds(16, 94, 31, 16);
		panel.add(txtrCsr);
		
		JSpinner spinner_generator = new JSpinner();
		spinner_generator.setBounds(543, 196, 71, 20);
		panel.add(spinner_generator);
		
		JButton btnKillThread = new JButton("Kill Thread");
		btnKillThread.setBounds(624, 245, 111, 21);
		btnKillThread.addActionListener(e->{
			g.KillThread();
		});
		panel.add(btnKillThread);
		
		JButton btnsetCount = new JButton("setPulseCount");
		btnsetCount.setBounds(624, 159, 111, 21);
		btnsetCount.addActionListener(e->{
			g.setPulseCount((Integer)spinner_generator.getValue());
		});
		panel.add(btnsetCount);
		
		JButton btnHalt = new JButton("Halt");
		btnHalt.setBounds(624, 214, 111, 21);
		btnHalt.addActionListener(e->{
			g.halt();
		});
		panel.add(btnHalt);
		
		
		JButton btnTriger = new JButton("Trigger");
		btnTriger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g.trigger();
			}
		});
		btnTriger.setBounds(624, 188, 111, 21);
		panel.add(btnTriger);
		
		textDelay = new JTextField();
		textDelay.setEditable(false);
		textDelay.setColumns(10);
		textDelay.setBounds(461, 6, 71, 19);
		panel.add(textDelay);
		
		textCount = new JTextField();
		textCount.setEditable(false);
		textCount.setColumns(10);
		textCount.setBounds(461, 27, 71, 19);
		panel.add(textCount);
		
		Knob k = new Knob();
		k.setBounds(444, 171, 80,80);
		k.addActionListener(e->{
			g.setPulseDelay(k.zwroc_ms());
		});
		panel.add(k);
		
		
		
		JRadioButton rdbtngenEnable = new JRadioButton("Generator enable");
		rdbtngenEnable.setBounds(624, 12, 111, 21);
		rdbtngenEnable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtngenEnable.isSelected()==true) {
					g.trigger();
				}else {
					g.halt();
				}
				
			}
		});
		panel.add(rdbtngenEnable);
		
		JRadioButton rdbtnMode = new JRadioButton("Mode");
		rdbtnMode.setBounds(624, 41, 103, 21);
		rdbtnMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnMode.isSelected()==true) {
					g.setMode(Generator.CONTINOUS_MODE);
				}else {
					g.setMode(Generator.BURST_MODE);
				}
				
			}
		});
		panel.add(rdbtnMode);
		
		
		JTextArea txtrDelay = new JTextArea();
		txtrDelay.setText("Delay");
		txtrDelay.setEditable(false);
		txtrDelay.setBounds(543, 9, 46, 16);
		panel.add(txtrDelay);
		
		JTextArea txtrCount = new JTextArea();
		txtrCount.setText("Count");
		txtrCount.setEditable(false);
		txtrCount.setBounds(542, 30, 46, 16);
		panel.add(txtrCount);
		
		JButton btncontinous = new JButton("Continous mode");
		btncontinous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g.setMode(Generator.CONTINOUS_MODE);
			}
		});
		btncontinous.setBounds(624, 129, 111, 21);
		panel.add(btncontinous);
		
		JButton btnBurstMode = new JButton("Burst mode");
		btnBurstMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g.setMode(Generator.BURST_MODE);
			}
		});
		btnBurstMode.setBounds(624, 104, 111, 21);
		panel.add(btnBurstMode);
		
		
		g.addActionListener(e->{
			
			if(e.getActionCommand()=="Wygenerowalem sygnal") {
			s.tickInternal();
			}
			textCount.setText(String.valueOf(g.getPulseCount()));
			textDelay.setText(String.valueOf(g.getPulseDelay()));
			rdbtngenEnable.setSelected(g.getEnable());
			rdbtnMode.setSelected(g.getModeBool());
			
		});
		s.addActionListener(e->{
		
			
			
			text_countflag.setText(String.valueOf(s.isCountFlag()));
			text_init.setText(String.valueOf(s.isInterruptFlag()));
			if(s.isSourceFlag()==true) {
				text_source.setText(String.valueOf("Internal"));
			}else {
				text_source.setText(String.valueOf("External"));
			}
			text_enable.setText(String.valueOf(s.isEnableFlag()));
			text_CSR.setText(String.valueOf(s.viewCSR()));
			RVR_text.setText(String.valueOf(s.getRVR()));
			txtCvr.setText(String.valueOf(s.getCVR()));
			button_enable.setSelected(s.isEnableFlag());
			button__source.setSelected(s.isSourceFlag());
			button_tickinit.setSelected(s.isInterruptFlag());
			
			
		});
		
		
		
	}
}
