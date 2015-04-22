package com.millennialmedia.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageManager;
import android.os.Vibrator;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.Callable;

class BridgeMMNotification
  extends MMJSObject
  implements DialogInterface.OnClickListener
{
  private String ALERT = "alert";
  private String VIBRATE = "vibrate";
  private int index;
  
  public MMJSResponse alert(final Map<String, String> paramMap)
  {
    try
    {
      MMJSResponse localMMJSResponse = runOnUiThreadFuture(new Callable()
      {
        public MMJSResponse call()
        {
          MMWebView localMMWebView = (MMWebView)BridgeMMNotification.this.mmWebViewRef.get();
          if (localMMWebView != null)
          {
            Activity localActivity = localMMWebView.getActivity();
            Map localMap = paramMap;
            if (localActivity != null)
            {
              if (!localActivity.isFinishing())
              {
                AlertDialog localAlertDialog = new AlertDialog.Builder(localActivity).create();
                if (localMap.containsKey("title")) {
                  localAlertDialog.setTitle((CharSequence)localMap.get("title"));
                }
                if (localMap.containsKey("message")) {
                  localAlertDialog.setMessage((CharSequence)localMap.get("message"));
                }
                if (localMap.containsKey("cancelButton")) {
                  localAlertDialog.setButton(-2, (CharSequence)localMap.get("cancelButton"), BridgeMMNotification.this);
                }
                if (localMap.containsKey("buttons"))
                {
                  String[] arrayOfString = ((String)localMap.get("buttons")).split(",");
                  if (arrayOfString.length > 0) {
                    localAlertDialog.setButton(-3, arrayOfString[0], BridgeMMNotification.this);
                  }
                  if (arrayOfString.length > 1) {
                    localAlertDialog.setButton(-1, arrayOfString[1], BridgeMMNotification.this);
                  }
                }
                localAlertDialog.show();
              }
              MMJSResponse localMMJSResponse = new MMJSResponse();
              localMMJSResponse.result = 1;
              localMMJSResponse.response = Integer.valueOf(BridgeMMNotification.this.index);
              return localMMJSResponse;
            }
          }
          return null;
        }
      });
      return localMMJSResponse;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  MMJSResponse executeCommand(String paramString, Map<String, String> paramMap)
  {
    MMJSResponse localMMJSResponse;
    if (this.ALERT.equals(paramString)) {
      localMMJSResponse = alert(paramMap);
    }
    boolean bool;
    do
    {
      return localMMJSResponse;
      bool = this.VIBRATE.equals(paramString);
      localMMJSResponse = null;
    } while (!bool);
    return vibrate(paramMap);
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (paramInt == -2) {}
    try
    {
      this.index = 0;
      if (paramInt == -3) {
        this.index = 1;
      }
      if (paramInt == -1) {
        this.index = 2;
      }
      paramDialogInterface.cancel();
      notify();
      return;
    }
    finally {}
  }
  
  public MMJSResponse vibrate(Map<String, String> paramMap)
  {
    Context localContext = (Context)this.contextRef.get();
    long l = 0L;
    if (paramMap.containsKey("duration")) {
      l = (1000.0D * Float.parseFloat((String)paramMap.get("duration")));
    }
    if ((localContext != null) && (l > 0L))
    {
      if (localContext.getPackageManager().checkPermission("android.permission.VIBRATE", localContext.getPackageName()) == 0)
      {
        ((Vibrator)localContext.getSystemService("vibrator")).vibrate(l);
        return MMJSResponse.responseWithSuccess("Vibrating for " + l);
      }
      return MMJSResponse.responseWithError("The required permissions to vibrate are not set.");
    }
    return null;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.BridgeMMNotification
 * JD-Core Version:    0.7.0.1
 */