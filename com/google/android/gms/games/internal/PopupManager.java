package com.google.android.gms.games.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Display;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import com.google.android.gms.internal.kc;
import java.lang.ref.WeakReference;

public class PopupManager
{
  protected GamesClientImpl XZ;
  protected PopupLocationInfo Ya;
  
  private PopupManager(GamesClientImpl paramGamesClientImpl, int paramInt)
  {
    this.XZ = paramGamesClientImpl;
    dG(paramInt);
  }
  
  public static PopupManager a(GamesClientImpl paramGamesClientImpl, int paramInt)
  {
    if (kc.hC()) {
      return new PopupManagerHCMR1(paramGamesClientImpl, paramInt);
    }
    return new PopupManager(paramGamesClientImpl, paramInt);
  }
  
  protected void dG(int paramInt)
  {
    this.Ya = new PopupLocationInfo(paramInt, new Binder(), null);
  }
  
  public void kM()
  {
    this.XZ.a(this.Ya.Yb, this.Ya.kP());
  }
  
  public Bundle kN()
  {
    return this.Ya.kP();
  }
  
  public IBinder kO()
  {
    return this.Ya.Yb;
  }
  
  public void l(View paramView) {}
  
  public void setGravity(int paramInt)
  {
    this.Ya.gravity = paramInt;
  }
  
  public static final class PopupLocationInfo
  {
    public IBinder Yb;
    public int Yc = -1;
    public int bottom = 0;
    public int gravity;
    public int left = 0;
    public int right = 0;
    public int top = 0;
    
    private PopupLocationInfo(int paramInt, IBinder paramIBinder)
    {
      this.gravity = paramInt;
      this.Yb = paramIBinder;
    }
    
    public Bundle kP()
    {
      Bundle localBundle = new Bundle();
      localBundle.putInt("popupLocationInfo.gravity", this.gravity);
      localBundle.putInt("popupLocationInfo.displayId", this.Yc);
      localBundle.putInt("popupLocationInfo.left", this.left);
      localBundle.putInt("popupLocationInfo.top", this.top);
      localBundle.putInt("popupLocationInfo.right", this.right);
      localBundle.putInt("popupLocationInfo.bottom", this.bottom);
      return localBundle;
    }
  }
  
  private static final class PopupManagerHCMR1
    extends PopupManager
    implements View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener
  {
    private boolean Wy = false;
    private WeakReference<View> Yd;
    
    protected PopupManagerHCMR1(GamesClientImpl paramGamesClientImpl, int paramInt)
    {
      super(paramInt, null);
    }
    
    private void m(View paramView)
    {
      int i = -1;
      if (kc.hG())
      {
        Display localDisplay = paramView.getDisplay();
        if (localDisplay != null) {
          i = localDisplay.getDisplayId();
        }
      }
      IBinder localIBinder = paramView.getWindowToken();
      int[] arrayOfInt = new int[2];
      paramView.getLocationInWindow(arrayOfInt);
      int j = paramView.getWidth();
      int k = paramView.getHeight();
      this.Ya.Yc = i;
      this.Ya.Yb = localIBinder;
      this.Ya.left = arrayOfInt[0];
      this.Ya.top = arrayOfInt[1];
      this.Ya.right = (j + arrayOfInt[0]);
      this.Ya.bottom = (k + arrayOfInt[1]);
      if (this.Wy)
      {
        kM();
        this.Wy = false;
      }
    }
    
    protected void dG(int paramInt)
    {
      this.Ya = new PopupManager.PopupLocationInfo(paramInt, null, null);
    }
    
    public void kM()
    {
      if (this.Ya.Yb != null)
      {
        super.kM();
        return;
      }
      if (this.Yd != null) {}
      for (boolean bool = true;; bool = false)
      {
        this.Wy = bool;
        return;
      }
    }
    
    public void l(View paramView)
    {
      this.XZ.kx();
      ViewTreeObserver localViewTreeObserver;
      if (this.Yd != null)
      {
        View localView2 = (View)this.Yd.get();
        Context localContext2 = this.XZ.getContext();
        if ((localView2 == null) && ((localContext2 instanceof Activity))) {
          localView2 = ((Activity)localContext2).getWindow().getDecorView();
        }
        if (localView2 != null)
        {
          localView2.removeOnAttachStateChangeListener(this);
          localViewTreeObserver = localView2.getViewTreeObserver();
          if (!kc.hF()) {
            break label184;
          }
          localViewTreeObserver.removeOnGlobalLayoutListener(this);
        }
      }
      for (;;)
      {
        this.Yd = null;
        Context localContext1 = this.XZ.getContext();
        if ((paramView == null) && ((localContext1 instanceof Activity)))
        {
          View localView1 = ((Activity)localContext1).findViewById(16908290);
          if (localView1 == null) {
            localView1 = ((Activity)localContext1).getWindow().getDecorView();
          }
          GamesLog.p("PopupManager", "You have not specified a View to use as content view for popups. Falling back to the Activity content view which may not work properly in future versions of the API. Use setViewForPopups() to set your content view.");
          paramView = localView1;
        }
        if (paramView == null) {
          break;
        }
        m(paramView);
        this.Yd = new WeakReference(paramView);
        paramView.addOnAttachStateChangeListener(this);
        paramView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        return;
        label184:
        localViewTreeObserver.removeGlobalOnLayoutListener(this);
      }
      GamesLog.q("PopupManager", "No content view usable to display popups. Popups will not be displayed in response to this client's calls. Use setViewForPopups() to set your content view.");
    }
    
    public void onGlobalLayout()
    {
      if (this.Yd == null) {}
      View localView;
      do
      {
        return;
        localView = (View)this.Yd.get();
      } while (localView == null);
      m(localView);
    }
    
    public void onViewAttachedToWindow(View paramView)
    {
      m(paramView);
    }
    
    public void onViewDetachedFromWindow(View paramView)
    {
      this.XZ.kx();
      paramView.removeOnAttachStateChangeListener(this);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.internal.PopupManager
 * JD-Core Version:    0.7.0.1
 */