package com.google.ads.interactivemedia.v3.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.google.ads.interactivemedia.v3.b.a.c;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class q
  extends ImageView
  implements View.OnClickListener
{
  private final c a;
  private final s b;
  private final String c;
  
  public q(Context paramContext, s params, c paramc, String paramString)
  {
    super(paramContext);
    this.b = params;
    this.a = paramc;
    this.c = paramString;
    setOnClickListener(this);
    a();
  }
  
  private void a()
  {
    new AsyncTask()
    {
      Exception a = null;
      
      protected Bitmap a(Void... paramAnonymousVarArgs)
      {
        try
        {
          Bitmap localBitmap = BitmapFactory.decodeStream(new URL(q.a(q.this).src).openConnection().getInputStream());
          return localBitmap;
        }
        catch (Exception localException)
        {
          this.a = localException;
        }
        return null;
      }
      
      protected void a(Bitmap paramAnonymousBitmap)
      {
        if (paramAnonymousBitmap == null)
        {
          Log.e("IMASDK", "Loading image companion " + q.a(q.this).src + " failed: " + this.a);
          return;
        }
        q.b(q.this);
        q.this.setImageBitmap(paramAnonymousBitmap);
      }
    }.execute(new Void[0]);
  }
  
  private void b()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("companionId", this.a.companionId);
    this.b.b(new r(r.b.displayContainer, r.c.companionView, this.c, localHashMap));
  }
  
  public void onClick(View paramView)
  {
    this.b.b(this.a.clickThroughUrl);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.q
 * JD-Core Version:    0.7.0.1
 */