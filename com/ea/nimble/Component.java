package com.ea.nimble;

public abstract class Component
{
  protected void cleanup() {}
  
  public abstract String getComponentId();
  
  protected void restore() {}
  
  protected void resume() {}
  
  protected void setup() {}
  
  protected void suspend() {}
  
  protected void teardown() {}
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.Component
 * JD-Core Version:    0.7.0.1
 */