package okienko2;

import java.awt.AWTEventMulticaster;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Generator extends Thread implements PulseSource  {

	final static byte BURST_MODE = 0;
    final static byte CONTINOUS_MODE = 1;
	
	ActionListener al;
	boolean thread=true;
	boolean enable;
	int licznik;
	byte mode; // 
	int delay;
	int count;
	
	public static void main(String[] args) {
        }
	
	
	public void KillThread() {
		thread=false;
		if(al!=null) al.actionPerformed(new ActionEvent(
				this,
				ActionEvent.ACTION_PERFORMED,
				"Zabilem wątek"
				));
	}
	
	@Override
	public void addActionListener(ActionListener pl) {
		// TODO Auto-generated method stub
		al=AWTEventMulticaster.add(al, pl);
	}

	@Override
	public void removeActionListener(ActionListener pl) {
		// TODO Auto-generated method stub
		al=AWTEventMulticaster.remove(al, pl);
	}
	
	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		enable=true;
		if(al!=null) al.actionPerformed(new ActionEvent(
				this,
				ActionEvent.ACTION_PERFORMED,
				"Generator Enable"
				));
	}
	
	public boolean getEnable() {
		return enable;
	}

	@Override
	public void setMode(byte mode) {
		// TODO Auto-generated method stub
		mode&=(1<<0);	
		this.mode=mode;
		if(al!=null) al.actionPerformed(new ActionEvent(
				this,
				ActionEvent.ACTION_PERFORMED,
				"Mode set"
				));
	}


	@Override
	public byte getMode() {
		// TODO Auto-generated method stub
		return mode;
	}
	
	public boolean getModeBool() {
		if (mode==1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void halt() {
		// TODO Auto-generated method stub
		enable=false;
		if(al!=null) al.actionPerformed(new ActionEvent(
				this,
				ActionEvent.ACTION_PERFORMED,
				"Generator disabled"
				));
	}

	@Override
	public void setPulseDelay(int ms) {
		// TODO Auto-generated method stub
		delay=ms;
		if(al!=null) al.actionPerformed(new ActionEvent(
				this,
				ActionEvent.ACTION_PERFORMED,
				"Delay set"
				));
	}

	@Override
	public int getPulseDelay() {
		// TODO Auto-generated method stub
		return delay;
	}

	@Override
	public void setPulseCount(int burst) {
		// TODO Auto-generated method stub
		count=burst;
		if(al!=null) al.actionPerformed(new ActionEvent(
				this,
				ActionEvent.ACTION_PERFORMED,
				"Count set"
				));
	}
	
	public int getPulseCount() {
		return count;
	}
	
	public void run() {
		while(thread) {
			if(enable) {
				System.out.println("Odpalilem sie");
				if(getMode()==CONTINOUS_MODE) {
					if(al!=null) al.actionPerformed(new ActionEvent(
							this,
							ActionEvent.ACTION_PERFORMED,
							"Wygenerowalem sygnal"
							));
			try {
				Thread.sleep(getPulseDelay());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				}
				else if(getMode()==BURST_MODE){
					if(licznik<count) {	
						count--;
						if(al!=null) al.actionPerformed(new ActionEvent(
								this,
								ActionEvent.ACTION_PERFORMED,
								"Wygenerowalem sygnal"
								));
						try {
							Thread.sleep(getPulseDelay());
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}
				}else{
					enable=false;
				}
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

}
