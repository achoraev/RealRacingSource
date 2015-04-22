package com.firemint.realracing;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerialiseNotificationsHelper
{
  static final String PATH = "pastNotifications.bin";
  static final String TAG = "RR3_SerialiseNotificationsHelper";
  static boolean bLog = false;
  
  static void AddNotification(Context paramContext, String paramString)
  {
    SerialisedNotificationInfo localSerialisedNotificationInfo = LoadInformation(paramContext);
    localSerialisedNotificationInfo.AddNotification(paramString);
    SaveInformation(paramContext, localSerialisedNotificationInfo);
  }
  
  static void ClearAll(Context paramContext)
  {
    SerialisedNotificationInfo localSerialisedNotificationInfo = LoadInformation(paramContext);
    localSerialisedNotificationInfo.ClearAll();
    SaveInformation(paramContext, localSerialisedNotificationInfo);
  }
  
  public static SerialisedNotificationInfo GetSavedInfo(Context paramContext)
  {
    return LoadInformation(paramContext);
  }
  
  private static SerialisedNotificationInfo LoadInformation(Context paramContext)
  {
    Log("Attempting to load saved information");
    SerialisedNotificationInfo localSerialisedNotificationInfo = new SerialisedNotificationInfo();
    try
    {
      File localFile = new File(paramContext.getFilesDir(), "pastNotifications.bin");
      if (localFile.exists())
      {
        Log("Found saved file. Loading information");
        FileInputStream localFileInputStream = new FileInputStream(localFile);
        ObjectInputStream localObjectInputStream = new ObjectInputStream(localFileInputStream);
        localSerialisedNotificationInfo = (SerialisedNotificationInfo)localObjectInputStream.readObject();
        localObjectInputStream.close();
        localFileInputStream.close();
      }
      for (;;)
      {
        localSerialisedNotificationInfo.Print("Loaded Information");
        return localSerialisedNotificationInfo;
        Log("Saved file doesn't exist. Returning new file");
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e("RealRacing3", "Caught exception: " + localException.getMessage());
      }
    }
  }
  
  static void Log(String paramString)
  {
    if (bLog) {
      Log.i("RR3_SerialiseNotificationsHelper", paramString);
    }
  }
  
  private static void SaveInformation(Context paramContext, SerialisedNotificationInfo paramSerialisedNotificationInfo)
  {
    try
    {
      FileOutputStream localFileOutputStream = new FileOutputStream(new File(paramContext.getFilesDir(), "pastNotifications.bin"));
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localFileOutputStream);
      localObjectOutputStream.writeObject(paramSerialisedNotificationInfo);
      localObjectOutputStream.close();
      localFileOutputStream.close();
      paramSerialisedNotificationInfo.Print("Saved Information");
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e("RealRacing3", "Caught exception: " + localException.getMessage());
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.SerialiseNotificationsHelper
 * JD-Core Version:    0.7.0.1
 */