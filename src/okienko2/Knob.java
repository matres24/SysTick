package okienko2;

import static java.lang.Math.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.AWTEventMulticaster;


public class Knob extends JComponent 
	implements MouseListener,MouseMotionListener{
	
	ActionListener al;
	int xKlik;
	int yKlik;
	double theta;
	double kat;
	public Knob() {
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		g.setColor(Color.CYAN);
		
		int r=Math.min(getWidth(), getHeight())/2;
		int x=getWidth()/2;
		int y=getHeight()/2;
		g.fillOval(x-r, y-r, r*2, r*2);
		
		g.setColor(Color.GRAY);
		int rm=r/5;
		//int xm=x;
		//int ym=y-r+rm;
		//int xm=xKlik;
		//int ym=yKlik;
		int ms=0;
		int xm;
		int ym;
		theta=Math.atan2((yKlik-y),(xKlik-x));
		kat=(360/Math.PI)*theta;
		System.out.println(kat);
		
		double z = 13.88;
		if(kat>=0) 
		{
			
			ms=(int) round(kat*z);
		}
		else if(kat<0) {
			int p=(int) round(-(-180-kat)*z);
			int t=7500;
			ms= t+p;
		}
		System.out.println(ms);
		xm=x+(int)(Math.cos(theta)*0.75*r);
		ym=y+(int)(Math.sin(theta)*0.75*r);
		
		g.fillOval(xm-rm, ym-rm, rm*2, rm*2);
		
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->{
			JFrame okno=new JFrame();
			
			Knob k=new Knob();
			
			okno.add(k);
			
			okno.setSize(600, 400);
			okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			okno.setVisible(true);
			
			
			
		});
		

	}

	public int zwroc_ms() {
		int ms=0;
		double z = 13.88;
		if(kat>=0) 
		{
			
			ms=(int) round(kat*z);
		}
		else if(kat<0) {
			int p=(int) round(-(-180-kat)*z);
			int t=7500;
			ms= t+p;
		}
		return ms;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		xKlik=e.getX();
		yKlik=e.getY();
		
		
		if(al!=null) al.actionPerformed(new ActionEvent(
				this,
				ActionEvent.ACTION_PERFORMED,
				"obrocilem sie"
				));
		
		
		repaint();
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void addActionListener(ActionListener l) {
		al=AWTEventMulticaster.add(al, l);
	}
	public void removActionListener(ActionListener l) {
		al=AWTEventMulticaster.remove(al, l);
	}

}