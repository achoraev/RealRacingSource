package com.google.android.gms.tagmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

public class PreviewActivity
  extends Activity
{
  private void d(String paramString1, String paramString2, String paramString3)
  {
    AlertDialog localAlertDialog = new AlertDialog.Builder(this).create();
    localAlertDialog.setTitle(paramString1);
    localAlertDialog.setMessage(paramString2);
    localAlertDialog.setButton(-1, paramString3, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localAlertDialog.show();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    try
    {
      super.onCreate(paramBundle);
      bh.U("Preview activity");
      Uri localUri = getIntent().getData();
      if (!TagManager.getInstance(this).i(localUri))
      {
        String str = "Cannot preview the app with the uri: " + localUri + ". Launching current version instead.";
        bh.W(str);
        d("Preview failure", str, "Continue");
      }
      Intent localIntent = getPackageManager().getLaunchIntentForPackage(getPackageName());
      if (localIntent != null)
      {
        bh.U("Invoke the launch activity for package name: " + getPackageName());
        startActivity(localIntent);
        return;
      }
      bh.U("No launch activity found for package name: " + getPackageName());
      return;
    }
    catch (Exception localException)
    {
      bh.T("Calling preview threw an exception: " + localException.getMessage());
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.PreviewActivity
 * JD-Core Version:    0.7.0.1
 */