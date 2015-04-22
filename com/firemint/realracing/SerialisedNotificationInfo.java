package com.firemint.realracing;

import java.io.Serializable;
import java.util.Vector;

public class SerialisedNotificationInfo
  implements Serializable
{
  static final boolean s_bLog;
  private Vector<String> m_vsNotifications = new Vector();
  int nVersion = 1;
  
  void AddNotification(String paramString)
  {
    LOG("Add notification to the vector (" + paramString + ")");
    this.m_vsNotifications.add(paramString);
  }
  
  void ClearAll()
  {
    LOG("Clear all notifications from vector");
    this.m_vsNotifications.clear();
  }
  
  final int GetNotificationCount()
  {
    LOG("Notification size = " + this.m_vsNotifications.size());
    return this.m_vsNotifications.size();
  }
  
  final String GetNotificationString(int paramInt)
    throws Exception
  {
    if ((paramInt >= 0) && (paramInt < this.m_vsNotifications.size()))
    {
      LOG("Notification at index (" + paramInt + ") = " + (String)this.m_vsNotifications.get(paramInt));
      return (String)this.m_vsNotifications.get(paramInt);
    }
    throw new Exception("Trying to get a notification form an invalid index!");
  }
  
  void LOG(String paramString) {}
  
  void Print(String paramString)
  {
    LOG(paramString);
    LOG("Notification count = " + this.m_vsNotifications);
    for (int i = 0; i < this.m_vsNotifications.size(); i++) {
      LOG("Notification (" + i + ") = " + (String)this.m_vsNotifications.get(i));
    }
    LOG("-----");
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.SerialisedNotificationInfo
 * JD-Core Version:    0.7.0.1
 */