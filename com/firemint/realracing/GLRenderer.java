package com.firemint.realracing;

import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class GLRenderer
  implements GLSurfaceView.Renderer
{
  public void onDrawFrame(GL10 paramGL10)
  {
    if (MainActivity.getGameState() == 5) {
      return;
    }
    try
    {
      MainActivity.instance.onViewRenderJNI(MainActivity.instance.getScreenOrientation(), MainActivity.instance.getScreenRotation());
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.e("RealRacing3", "Exception inside onDrawFrame: " + localThrowable);
    }
  }
  
  public void onSurfaceChanged(GL10 paramGL10, int paramInt1, int paramInt2)
  {
    try
    {
      MainActivity.instance.onViewChangedJNI(paramInt1, paramInt2, MainActivity.instance.getScreenOrientation(), MainActivity.instance.getScreenRotation());
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.e("RealRacing3", "Exception inside onSurfaceChanged: " + localThrowable);
    }
  }
  
  public void onSurfaceCreated(GL10 paramGL10, EGLConfig paramEGLConfig)
  {
    MainActivity.instance.onViewCreatedJNI();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.GLRenderer
 * JD-Core Version:    0.7.0.1
 */