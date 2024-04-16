package test;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JTextArea;


public class generatorZ extends Thread{
	JTextArea poleTekstowe;
	boolean enable;
	
	
	public generatorZ() {
	enable=false;	
	start();
	}
	
	public void uruchom() {
		enable=true;
	}
	public void zatrzymaj() {
		enable=false;
	}
	public boolean czy_wlaczony() {
		return enable;
	}
	public void ustawPoleTekstowe(JTextArea pt) {
		poleTekstowe=pt;
	}

	
	public void run() {
		int licznik=0;
		while(true) {
			if(poleTekstowe!=null)
			if(enable) {
			poleTekstowe.append("Stan licznika: " + licznik +"\n");
			licznik++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		}
	}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(()->{
			JFrame okno = new JFrame();
			JButton przycisk = new JButton("Start");
			JTextArea tekst = new JTextArea();
			
			generatorZ g = new generatorZ();
			g.ustawPoleTekstowe(tekst);
			
			przycisk.addActionListener(e->{
				if(g.czy_wlaczony()) {
					g.zatrzymaj();
					przycisk.setText("Start");
				}else {
					g.uruchom();
					przycisk.setText("Stop");
				}
			});
			
			okno.add(przycisk,BorderLayout.NORTH);
			okno.add(tekst);
			
			okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			okno.setSize(600, 400);
			okno.setVisible(true);
			
			
		});
	}

}
