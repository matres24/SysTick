package okienko2;

interface Cortex_M0_SysTick_Interface
{
    public void tickInternal();
    public void tickExternal();
    
    public void setRVR(int value);
    public void setCVR(int value);
    public void setCSR(int value);
    
    public void reset();
    public void setEnable();
    public void setDisable();
    public void setSourceExternal();
    public void setSourceInternal();
    public void setInterruptEnable();
    public void setInterruptDisable();
    
    public int getCVR();
    public int getRVR();
    public int getCSR();
    
    public boolean getEnabled();
    public boolean getInterrupt();
    public boolean getSource();
    public boolean getCountFlag();
    
    public boolean isCountFlag();
    public boolean isEnableFlag();
    public boolean isInterruptFlag();
    public boolean isSourceFlag();    
    
}
 

 

