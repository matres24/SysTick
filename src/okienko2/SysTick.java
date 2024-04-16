package okienko2;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.AWTEventMulticaster;

public class SysTick implements Cortex_M0_SysTick_Interface {
	
	ActionListener al;
	
	private int CVR; // 24bity RVR&(2^24-1) Current value register (unknown on reset) =0x00FFFFFF
	private int RVR; // 24bity Reload value register  = 0x00FFFFFF
	private int CSR; // 16bitów 16 bit to COUNTFLAG, 2bit to CLKSRC, 1bit to TICKINT, 0 enable  0=0x00000000
	
	private boolean CSRENABLE; //private int CSRENABLE=(1<<0) \\ zalaczenie licznika
	private boolean CSRTICKINT; //private int CSRTICKINT =(1<<1)  \\ przerwanie
	private boolean CSRCLKSRC;  //private int CSRCLKSRC =(1<<2) \\ true == 1 internal false == 0 external
	private boolean CSRCOUNTFLAG; //private int CSRCOUNTFLAG =(1<<15) \\ flaga obliczenia do 0 
	
	
	
	
	public static void main(String[] args) {
		SysTick s = new SysTick();
		
		s.setRVR(5);
		s.setSourceExternal();
		System.out.println(s.getCSR());
		System.out.println(s.getRVR());
		s.setEnable();
		System.out.println("Enabled:"+s.getEnabled());
		
		System.out.println("Enabled:"+s.getEnabled());
		s.setInterruptDisable();
		System.out.println("Flag:"+s.isInterruptFlag());
		s.setInterruptEnable();
		System.out.println("Flag:"+s.isInterruptFlag());
		s.setSourceInternal();
		
		System.out.println("Source:"+s.getSource());
		s.setSourceExternal();
		System.out.println("Source:"+s.getSource());
		
		s.tickInternal();
		System.out.println("Current value:"+s.getCVR());
		s.tickExternal();
		System.out.println("Current value:"+s.getCVR());
		s.tickExternal();
		s.tickExternal();
		s.tickInternal();
		System.out.println("Current value:"+s.getCVR());
		s.setSourceInternal();
		System.out.println("Current value:"+s.getCVR());
		s.tickExternal();
		System.out.println("Current value:"+s.getCVR());
		s.tickInternal();
		System.out.println("Current value:"+s.getCVR());
		s.tickInternal();
		System.out.println("Flag:"+s.isCountFlag());
		s.tickInternal();
		System.out.println("Current value:"+s.getCVR());
		System.out.println("Flag:"+s.isCountFlag());
		s.tickInternal();
		System.out.println("Current value:"+s.getCVR());
		System.out.println("Flag:"+s.isCountFlag());
		s.tickInternal();
        }

	
	private void resetCountFLAG() {
		CSRCOUNTFLAG=false;
		CSR&=~(1<<15);
	}
	
	private void tick (boolean a) {
		if(CSRENABLE==true&CSRCLKSRC==a) {
			CVR--;	
				if(CVR==0) {
					CSRCOUNTFLAG=true;
				}
				if(CVR<0&&RVR!=0) {
					CVR=RVR;
				}
				else if(CVR<0&&RVR==0){
					CVR=0;
					CSRENABLE=false;
				}
			}
	}
	
	private void action(String s) {
		if(al!=null) al.actionPerformed(new ActionEvent(
				this,
				ActionEvent.ACTION_PERFORMED,
				s
				));
	}
	
	@Override
	public void tickInternal() {

		tick(true);
		action("Tick Internal");
	}

	@Override
	public void tickExternal() {

	tick(false);
	action("Tick External");
				
	}

	@Override
	public void setRVR(int value) {
		RVR=value&((1<<24)-1);
		
		if(al!=null) al.actionPerformed(new ActionEvent(
				this,
				ActionEvent.ACTION_PERFORMED,
				"RVR set"
				));
	}

	@Override
	public void setCVR(int value) {
		CVR=0;
		resetCountFLAG();
		if(al!=null) al.actionPerformed(new ActionEvent(
				this,
				ActionEvent.ACTION_PERFORMED,
				"CVR set"
				));
		
	}

	@Override
	public void setCSR(int value) {
	
		value&=(1<<16)-1;
		if((value & 1)!=0) {
			setEnable();
			//CSRENABLE=true;
		}
		else {
			setDisable();
			//CSRENABLE=false;
		}
		
		if((value & 1<<1)!=0) {
			setInterruptEnable();
			//CSRTICKINT=true;
		}
		else {
			setInterruptDisable();
			//CSRTICKINT=false;
		}
		
		if((value & 1<<2)!=0) {
			setSourceInternal();
			//CSRCLKSRC=true;
		}
		else {
			setSourceExternal();
			//CSRCLKSRC=false;
		}
		
		
		/*if((value & 1<<15)!=0) {

			//CSRCOUNTFLAG=true;
		}
		else {
			//CSRCOUNTFLAG=false;
		}*/
		
		CSR=value;
		if(al!=null) al.actionPerformed(new ActionEvent(
				this,
				ActionEvent.ACTION_PERFORMED,
				"CSR set"
				));
	}

	@Override
	public void reset() {

		CSRENABLE=false; // timer disable
		CSRTICKINT=false;
		CSRCLKSRC=false;
		resetCountFLAG();
		if(al!=null) al.actionPerformed(new ActionEvent(
				this,
				ActionEvent.ACTION_PERFORMED,
				"Reset"
				));
	}

	@Override
	public void setEnable() {

		CSRENABLE=true;
		CSR|=(1<<0);
		if(al!=null) al.actionPerformed(new ActionEvent(
				this,
				ActionEvent.ACTION_PERFORMED,
				"Enable set"
				));
	}

	@Override
	public void setDisable() {

		CSRENABLE=false;
		CSR&=~(1<<0);
		if(al!=null) al.actionPerformed(new ActionEvent(
				this,
				ActionEvent.ACTION_PERFORMED,
				"Disable set"
				));
	}

	@Override
	public void setSourceExternal() {

		CSRCLKSRC=false;
		CSR&=~(1<<2);
		if(al!=null) al.actionPerformed(new ActionEvent(
				this,
				ActionEvent.ACTION_PERFORMED,
				"Source External"
				));
	}

	@Override
	public void setSourceInternal() {

		CSRCLKSRC=true;
		CSR|=(1<<2);
		if(al!=null) al.actionPerformed(new ActionEvent(
				this,
				ActionEvent.ACTION_PERFORMED,
				"Source Internal"
				));
	}

	@Override
	public void setInterruptEnable() {

		CSRTICKINT=true;
		CSR|=(1<<1);
		if(al!=null) al.actionPerformed(new ActionEvent(
				this,
				ActionEvent.ACTION_PERFORMED,
				"Interrupt enable"
				));
	}

	@Override
	public void setInterruptDisable() {

		CSRTICKINT=false;
		CSR&=~(1<<1);
		if(al!=null) al.actionPerformed(new ActionEvent(
				this,
				ActionEvent.ACTION_PERFORMED,
				"interrupt disable"
				));
	}

	@Override
	public int getCVR() {

		return CVR;
	}

	@Override
	public int getRVR() {

		return RVR;
	}

	@Override
	public int getCSR() {

		resetCountFLAG();
		return CSR;
	}
	
	public int viewCSR() {
		return CSR;
	}

	@Override
	public boolean getEnabled() {
		resetCountFLAG();

		return CSRENABLE;
	}

	@Override
	public boolean getInterrupt() {
		resetCountFLAG();

		return CSRTICKINT;
	}

	@Override
	public boolean getSource() {
		resetCountFLAG();

		return CSRCLKSRC;
	}

	@Override
	public boolean getCountFlag() {
		resetCountFLAG();

		return CSRCOUNTFLAG;
	}

	@Override
	public boolean isCountFlag() {

		return CSRCOUNTFLAG;
	}

	@Override
	public boolean isEnableFlag() {

		return CSRENABLE;
	}

	@Override
	public boolean isInterruptFlag() {

		return CSRTICKINT;
	}
	@Override
	public boolean isSourceFlag() {

		return CSRCLKSRC;
	}
	
	public void addActionListener(ActionListener l) {
		al=AWTEventMulticaster.add(al, l);
	}
	public void removActionListener(ActionListener l) {
		al=AWTEventMulticaster.remove(al, l);
	}

}
