package com.google.android.gms.dynamic;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public final class b
  extends c.a
{
  private Fragment Sj;
  
  private b(Fragment paramFragment)
  {
    this.Sj = paramFragment;
  }
  
  public static b a(Fragment paramFragment)
  {
    if (paramFragment != null) {
      return new b(paramFragment);
    }
    return null;
  }
  
  public void d(d paramd)
  {
    View localView = (View)e.f(paramd);
    this.Sj.registerForContextMenu(localView);
  }
  
  public void e(d paramd)
  {
    View localView = (View)e.f(paramd);
    this.Sj.unregisterForContextMenu(localView);
  }
  
  public Bundle getArguments()
  {
    return this.Sj.getArguments();
  }
  
  public int getId()
  {
    return this.Sj.getId();
  }
  
  public boolean getRetainInstance()
  {
    return this.Sj.getRetainInstance();
  }
  
  public String getTag()
  {
    return this.Sj.getTag();
  }
  
  public int getTargetRequestCode()
  {
    return this.Sj.getTargetRequestCode();
  }
  
  public boolean getUserVisibleHint()
  {
    return this.Sj.getUserVisibleHint();
  }
  
  public d getView()
  {
    return e.k(this.Sj.getView());
  }
  
  public boolean isAdded()
  {
    return this.Sj.isAdded();
  }
  
  public boolean isDetached()
  {
    return this.Sj.isDetached();
  }
  
  public boolean isHidden()
  {
    return this.Sj.isHidden();
  }
  
  public boolean isInLayout()
  {
    return this.Sj.isInLayout();
  }
  
  public boolean isRemoving()
  {
    return this.Sj.isRemoving();
  }
  
  public boolean isResumed()
  {
    return this.Sj.isResumed();
  }
  
  public boolean isVisible()
  {
    return this.Sj.isVisible();
  }
  
  public d iu()
  {
    return e.k(this.Sj.getActivity());
  }
  
  public c iv()
  {
    return a(this.Sj.getParentFragment());
  }
  
  public d iw()
  {
    return e.k(this.Sj.getResources());
  }
  
  public c ix()
  {
    return a(this.Sj.getTargetFragment());
  }
  
  public void setHasOptionsMenu(boolean paramBoolean)
  {
    this.Sj.setHasOptionsMenu(paramBoolean);
  }
  
  public void setMenuVisibility(boolean paramBoolean)
  {
    this.Sj.setMenuVisibility(paramBoolean);
  }
  
  public void setRetainInstance(boolean paramBoolean)
  {
    this.Sj.setRetainInstance(paramBoolean);
  }
  
  public void setUserVisibleHint(boolean paramBoolean)
  {
    this.Sj.setUserVisibleHint(paramBoolean);
  }
  
  public void startActivity(Intent paramIntent)
  {
    this.Sj.startActivity(paramIntent);
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    this.Sj.startActivityForResult(paramIntent, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.dynamic.b
 * JD-Core Version:    0.7.0.1
 */