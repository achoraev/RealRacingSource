package com.google.android.gms.internal;

import java.util.Map;

@ez
public final class bv
  implements by
{
  private final bw pz;
  
  public bv(bw parambw)
  {
    this.pz = parambw;
  }
  
  public void a(gv paramgv, Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("name");
    if (str == null)
    {
      gs.W("App event with no name parameter.");
      return;
    }
    this.pz.onAppEvent(str, (String)paramMap.get("info"));
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.bv
 * JD-Core Version:    0.7.0.1
 */