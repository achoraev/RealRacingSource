package com.firemint.realracing;

import android.app.Activity;
import android.content.Context;

public class AppProxy
{
  private static Activity m_activity = null;
  private static Context m_context = null;
  
  public static Activity GetActivity()
  {
    return m_activity;
  }
  
  public static Context GetContext()
  {
    return m_context;
  }
  
  public static void SetActivity(Activity paramActivity)
  {
    m_activity = paramActivity;
    m_context = paramActivity;
  }
  
  public static void SetContext(Context paramContext)
  {
    m_context = paramContext;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.AppProxy
 * JD-Core Version:    0.7.0.1
 */