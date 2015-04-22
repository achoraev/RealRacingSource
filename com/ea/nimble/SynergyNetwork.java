package com.ea.nimble;

public class SynergyNetwork
{
  public static final String COMPONENT_ID = "com.ea.nimble.synergynetwork";
  
  public static ISynergyNetwork getComponent()
  {
    return SynergyNetworkImpl.getComponent();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.SynergyNetwork
 * JD-Core Version:    0.7.0.1
 */