package com.ea.nimble;

public class Base
{
  public static Component getComponent(String paramString)
  {
    return BaseCore.getInstance().activeValidate().getComponentManager().getComponent(paramString);
  }
  
  public static Component[] getComponentList(String paramString)
  {
    return BaseCore.getInstance().activeValidate().getComponentManager().getComponentList(paramString);
  }
  
  public static NimbleConfiguration getConfiguration()
  {
    return BaseCore.getInstance().getConfiguration();
  }
  
  public static void registerComponent(Component paramComponent, String paramString)
  {
    BaseCore.getInstance().getComponentManager().registerComponent(paramComponent, paramString);
  }
  
  public static void restartWithConfiguration(NimbleConfiguration paramNimbleConfiguration)
  {
    BaseCore.getInstance().activeValidate().restartWithConfiguration(paramNimbleConfiguration);
  }
  
  public static void setupNimble()
  {
    BaseCore.getInstance().setup();
  }
  
  public static void teardownNimble()
  {
    BaseCore.getInstance().teardown();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.Base
 * JD-Core Version:    0.7.0.1
 */