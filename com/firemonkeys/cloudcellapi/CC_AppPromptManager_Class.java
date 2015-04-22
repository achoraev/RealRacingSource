package com.firemonkeys.cloudcellapi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Log;
import com.firemonkeys.cloudcellapi.util.GetInfo;

public class CC_AppPromptManager_Class
{
  AlertDialog m_pDialog = null;
  
  public CC_AppPromptManager_Class()
  {
    Log("CC_AppPromptManager_Class()");
  }
  
  private native void OnDontRate();
  
  private native void OnRateApp();
  
  private native void OnRemindLater();
  
  public void DismissRateAppDialog()
  {
    Log("DismissRateAppDialog()");
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        CC_AppPromptManager_Class.this.Log("Destroying Dialog");
        if (CC_AppPromptManager_Class.this.m_pDialog != null) {
          CC_AppPromptManager_Class.this.m_pDialog.dismiss();
        }
      }
    });
  }
  
  public void DisplayRateAppDialog(final String paramString1, final String paramString2, final String paramString3, final String paramString4)
  {
    Log("DisplayRateAppDialog()");
    DismissRateAppDialog();
    Log("UIThreadQueue - Begin");
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        CC_AppPromptManager_Class.this.Log("DisplayRateAppDialog::Thread - Begin");
        DialogInterface.OnClickListener local1 = new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            switch (paramAnonymous2Int)
            {
            default: 
              CC_AppPromptManager_Class.this.Log("Remind Later");
              CC_AppPromptManager_Class.this.OnRemindLater();
              return;
            case -1: 
              CC_AppPromptManager_Class.this.Log("Rating App");
              CC_AppPromptManager_Class.this.OpenStorePage();
              CC_AppPromptManager_Class.this.OnRateApp();
              return;
            }
            CC_AppPromptManager_Class.this.Log("Dont rate app");
            CC_AppPromptManager_Class.this.OnDontRate();
          }
        };
        CC_AppPromptManager_Class.this.Log("DisplayRateAppDialog create dialog");
        if (Build.VERSION.SDK_INT >= 11) {}
        for (CC_AppPromptManager_Class.this.m_pDialog = new AlertDialog.Builder(CC_Activity.GetActivity(), 2).setMessage(paramString1).setPositiveButton(paramString2, local1).setNeutralButton(paramString3, local1).setNegativeButton(paramString4, local1).create();; CC_AppPromptManager_Class.this.m_pDialog = new AlertDialog.Builder(CC_Activity.GetActivity()).setMessage(paramString1).setPositiveButton(paramString2, local1).setNeutralButton(paramString3, local1).setNegativeButton(paramString4, local1).create())
        {
          CC_AppPromptManager_Class.this.Log("DisplayRateAppDialog show dialog");
          CC_AppPromptManager_Class.this.m_pDialog.show();
          CC_AppPromptManager_Class.this.Log("DisplayRateAppDialog::Thread - End");
          return;
        }
      }
    });
    Log("UIThreadQueue - End");
  }
  
  protected void Log(String paramString)
  {
    Log.i("CC_PROMPTS", paramString);
  }
  
  public void OpenStorePage()
  {
    String str = CC_Activity.GetActivity().getApplicationContext().getPackageName();
    Intent localIntent = new Intent("android.intent.action.VIEW");
    if (GetInfo.GetIsAmazonDevice()) {
      localIntent.setData(Uri.parse("amzn://apps/android?p=" + str));
    }
    for (;;)
    {
      CC_Activity.GetActivity().startActivity(localIntent);
      return;
      localIntent.setData(Uri.parse("market://details?id=" + str));
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.CC_AppPromptManager_Class
 * JD-Core Version:    0.7.0.1
 */