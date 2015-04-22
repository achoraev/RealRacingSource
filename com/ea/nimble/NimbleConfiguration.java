package com.ea.nimble;

public enum NimbleConfiguration
{
  static
  {
    INTEGRATION = new NimbleConfiguration("INTEGRATION", 1);
    STAGE = new NimbleConfiguration("STAGE", 2);
    LIVE = new NimbleConfiguration("LIVE", 3);
    CUSTOMIZED = new NimbleConfiguration("CUSTOMIZED", 4);
    NimbleConfiguration[] arrayOfNimbleConfiguration = new NimbleConfiguration[5];
    arrayOfNimbleConfiguration[0] = UNKNOWN;
    arrayOfNimbleConfiguration[1] = INTEGRATION;
    arrayOfNimbleConfiguration[2] = STAGE;
    arrayOfNimbleConfiguration[3] = LIVE;
    arrayOfNimbleConfiguration[4] = CUSTOMIZED;
    $VALUES = arrayOfNimbleConfiguration;
  }
  
  private NimbleConfiguration() {}
  
  public static NimbleConfiguration fromName(String paramString)
  {
    if (paramString.equals("int")) {
      return INTEGRATION;
    }
    if (paramString.equals("stage")) {
      return STAGE;
    }
    if (paramString.equals("live")) {
      return LIVE;
    }
    if (paramString.equals("custom")) {
      return CUSTOMIZED;
    }
    return UNKNOWN;
  }
  
  public String toString()
  {
    switch (1.$SwitchMap$com$ea$nimble$NimbleConfiguration[ordinal()])
    {
    default: 
      return "unknown";
    case 1: 
      return "int";
    case 2: 
      return "stage";
    case 3: 
      return "live";
    }
    return "custom";
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.NimbleConfiguration
 * JD-Core Version:    0.7.0.1
 */