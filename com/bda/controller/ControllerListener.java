package com.bda.controller;

public abstract interface ControllerListener
{
  public abstract void onKeyEvent(KeyEvent paramKeyEvent);
  
  public abstract void onMotionEvent(MotionEvent paramMotionEvent);
  
  public abstract void onStateEvent(StateEvent paramStateEvent);
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.bda.controller.ControllerListener
 * JD-Core Version:    0.7.0.1
 */