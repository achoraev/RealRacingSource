package com.firemint.realracing;

import android.annotation.TargetApi;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.EGLConfigChooser;
import android.opengl.GLSurfaceView.EGLContextFactory;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.MotionEvent;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

public class GLView
  extends GLSurfaceView
{
  private static final int EGL_CONTEXT_CLIENT_VERSION = 12440;
  private static final int EGL_COVERAGE_BUFFERS_NV = 12512;
  private static final int EGL_COVERAGE_SAMPLES_NV = 12513;
  private static final int EGL_DEPTH_ENCODING_NONLINEAR_NV = 12515;
  private static final int EGL_DEPTH_ENCODING_NV = 12514;
  private static final int EGL_OPENGL_ES2_BIT = 4;
  private EGLContext m_glContext = null;
  
  @TargetApi(11)
  public GLView(Context paramContext)
  {
    super(paramContext);
    if (Build.VERSION.SDK_INT >= 11) {
      setPreserveEGLContextOnPause(true);
    }
    setFocusable(true);
    setFocusableInTouchMode(true);
    setEGLConfigChooser(new ConfigChooser(null));
    setEGLContextFactory(new ContextFactory(null));
    setRenderer(new GLRenderer());
  }
  
  public EGLContext getGLContext()
  {
    return this.m_glContext;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (MainActivity.instance.getPaused()) {}
    int i;
    do
    {
      for (;;)
      {
        return true;
        i = paramMotionEvent.getActionMasked();
        if ((i == 0) || (i == 5))
        {
          int j = paramMotionEvent.getActionIndex();
          queueEvent(new TouchEvent(i, paramMotionEvent.getPointerId(j), paramMotionEvent.getX(j), paramMotionEvent.getY(j), false));
          return true;
        }
        if (i != 2) {
          break;
        }
        for (int i1 = 0; i1 < paramMotionEvent.getPointerCount(); i1++) {
          queueEvent(new TouchEvent(i, paramMotionEvent.getPointerId(i1), paramMotionEvent.getX(i1), paramMotionEvent.getY(i1), false));
        }
      }
      if ((i == 1) || (i == 6))
      {
        int k = paramMotionEvent.getActionIndex();
        int m = paramMotionEvent.getPointerId(k);
        float f1 = paramMotionEvent.getX(k);
        float f2 = paramMotionEvent.getY(k);
        int n = paramMotionEvent.getActionMasked();
        boolean bool = false;
        if (n == 1) {
          bool = true;
        }
        queueEvent(new TouchEvent(i, m, f1, f2, bool));
        return true;
      }
    } while (i != 3);
    queueEvent(new TouchEvent(i, 0, 0.0F, 0.0F, false));
    return true;
  }
  
  class ConfigAttribs
  {
    int alphaBits = 0;
    int blueBits = 0;
    int caveat = 0;
    int depthBits = 0;
    int greenBits = 0;
    int level = 0;
    int nvSamples = 0;
    int nv_depthEncoding = 0;
    int redBits = 0;
    int renderableType = 0;
    int samples = 0;
    int stencilBits = 0;
    int surfaceType = 0;
    int transparentType = 0;
    
    ConfigAttribs() {}
    
    ConfigAttribs(EGL10 paramEGL10, EGLDisplay paramEGLDisplay, EGLConfig paramEGLConfig)
    {
      this.redBits = getEGLConfigAttrib(paramEGL10, paramEGLDisplay, paramEGLConfig, 12324);
      this.greenBits = getEGLConfigAttrib(paramEGL10, paramEGLDisplay, paramEGLConfig, 12323);
      this.blueBits = getEGLConfigAttrib(paramEGL10, paramEGLDisplay, paramEGLConfig, 12322);
      this.alphaBits = getEGLConfigAttrib(paramEGL10, paramEGLDisplay, paramEGLConfig, 12321);
      this.depthBits = getEGLConfigAttrib(paramEGL10, paramEGLDisplay, paramEGLConfig, 12325);
      this.stencilBits = getEGLConfigAttrib(paramEGL10, paramEGLDisplay, paramEGLConfig, 12326);
      this.samples = getEGLConfigAttrib(paramEGL10, paramEGLDisplay, paramEGLConfig, 12337);
      this.nvSamples = getEGLConfigAttrib(paramEGL10, paramEGLDisplay, paramEGLConfig, 12513);
      this.level = getEGLConfigAttrib(paramEGL10, paramEGLDisplay, paramEGLConfig, 12329);
      this.caveat = getEGLConfigAttrib(paramEGL10, paramEGLDisplay, paramEGLConfig, 12327);
      this.surfaceType = getEGLConfigAttrib(paramEGL10, paramEGLDisplay, paramEGLConfig, 12339);
      this.renderableType = getEGLConfigAttrib(paramEGL10, paramEGLDisplay, paramEGLConfig, 12352);
      this.transparentType = getEGLConfigAttrib(paramEGL10, paramEGLDisplay, paramEGLConfig, 12340);
      this.nv_depthEncoding = getEGLConfigAttrib(paramEGL10, paramEGLDisplay, paramEGLConfig, 12514);
    }
    
    private int getEGLConfigAttrib(EGL10 paramEGL10, EGLDisplay paramEGLDisplay, EGLConfig paramEGLConfig, int paramInt)
    {
      int[] arrayOfInt = { 0 };
      paramEGL10.eglGetConfigAttrib(paramEGLDisplay, paramEGLConfig, paramInt, arrayOfInt);
      return arrayOfInt[0];
    }
    
    private void print(boolean paramBoolean)
    {
      String str1 = "false";
      if ((0x4 & this.surfaceType) != 0) {
        str1 = "true";
      }
      String str2 = "false";
      if ((0x4 & this.renderableType) != 0) {
        str2 = "true";
      }
      String str3 = "none";
      String str4;
      label88:
      String str5;
      switch (this.caveat)
      {
      default: 
        str4 = "none";
        switch (this.nv_depthEncoding)
        {
        default: 
          str5 = "none";
          switch (this.transparentType)
          {
          }
          break;
        }
        break;
      }
      for (;;)
      {
        if (!paramBoolean) {
          break label529;
        }
        Log.i("RealRacing3", "EGL_RED_SIZE: " + this.redBits);
        Log.i("RealRacing3", "EGL_GREEN_SIZE: " + this.greenBits);
        Log.i("RealRacing3", "EGL_BLUE_SIZE: " + this.blueBits);
        Log.i("RealRacing3", "EGL_ALPHA_SIZE: " + this.alphaBits);
        Log.i("RealRacing3", "EGL_DEPTH_SIZE: " + this.depthBits);
        Log.i("RealRacing3", "EGL_STENCIL_SIZE: " + this.stencilBits);
        Log.i("RealRacing3", "EGL_SAMPLES: " + this.samples);
        Log.i("RealRacing3", "EGL_COVERAGE_SAMPLES_NV: " + this.nvSamples);
        Log.i("RealRacing3", "EGL_LEVEL: " + this.level);
        Log.i("RealRacing3", "EGL_TRANSPARENT_TYPE: " + str5);
        Log.i("RealRacing3", "EGL_WINDOW_BIT: " + str1);
        Log.i("RealRacing3", "EGL_OPENGL_ES2_BIT: " + str2);
        Log.i("RealRacing3", "EGL_CONFIG_CAVEAT: " + str3);
        Log.i("RealRacing3", "EGL_DEPTH_ENCODING_NV: " + str4);
        return;
        str3 = "bad";
        break;
        str3 = "slow";
        break;
        str4 = "nonlinear";
        break label88;
        str5 = "rgb";
      }
      label529:
      Object[] arrayOfObject = new Object[14];
      arrayOfObject[0] = Integer.valueOf(this.redBits);
      arrayOfObject[1] = Integer.valueOf(this.greenBits);
      arrayOfObject[2] = Integer.valueOf(this.blueBits);
      arrayOfObject[3] = Integer.valueOf(this.alphaBits);
      arrayOfObject[4] = Integer.valueOf(this.depthBits);
      arrayOfObject[5] = Integer.valueOf(this.stencilBits);
      arrayOfObject[6] = Integer.valueOf(this.samples);
      arrayOfObject[7] = Integer.valueOf(this.nvSamples);
      arrayOfObject[8] = Integer.valueOf(this.level);
      arrayOfObject[9] = str5;
      arrayOfObject[10] = str2;
      arrayOfObject[11] = str1;
      arrayOfObject[12] = str3;
      arrayOfObject[13] = str4;
      Log.i("RealRacing3", String.format("EGLConfig r:%d g:%d b:%d a:%d d:%d s:%d aa:%d nvaa:%d level:%d trans:%s gles2:%s window:%s caveat:%s nvDepthEnc:%s", arrayOfObject));
    }
  }
  
  private class ConfigChooser
    implements GLSurfaceView.EGLConfigChooser
  {
    static
    {
      if (!GLView.class.desiredAssertionStatus()) {}
      for (boolean bool = true;; bool = false)
      {
        $assertionsDisabled = bool;
        return;
      }
    }
    
    private ConfigChooser() {}
    
    private int scoreConfig(GLView.ConfigAttribs paramConfigAttribs1, GLView.ConfigAttribs paramConfigAttribs2)
    {
      if (((0x4 & paramConfigAttribs1.surfaceType) == 0) || ((0x4 & paramConfigAttribs1.renderableType) == 0)) {}
      while ((paramConfigAttribs1.redBits < paramConfigAttribs2.redBits) || (paramConfigAttribs1.greenBits < paramConfigAttribs2.greenBits) || (paramConfigAttribs1.blueBits < paramConfigAttribs2.blueBits) || (paramConfigAttribs1.stencilBits < paramConfigAttribs2.stencilBits) || (paramConfigAttribs1.depthBits < paramConfigAttribs2.depthBits)) {
        return -1;
      }
      int i = Math.abs(paramConfigAttribs1.depthBits - paramConfigAttribs2.depthBits);
      int j = 0 + i * i;
      int k = Math.abs(paramConfigAttribs1.redBits - paramConfigAttribs2.redBits);
      int m = j + k * k;
      int n = Math.abs(paramConfigAttribs1.greenBits - paramConfigAttribs2.greenBits);
      int i1 = m + n * n;
      int i2 = Math.abs(paramConfigAttribs1.blueBits - paramConfigAttribs2.blueBits);
      int i3 = i1 + i2 * i2;
      int i4 = Math.abs(paramConfigAttribs1.alphaBits - paramConfigAttribs2.alphaBits);
      int i5 = i3 + i4 * i4;
      int i6 = Math.abs(paramConfigAttribs1.stencilBits - paramConfigAttribs2.stencilBits);
      int i7 = i5 + i6 * i6;
      int i8 = Math.abs(paramConfigAttribs1.samples - paramConfigAttribs2.samples);
      int i9 = i7 + i8 * i8;
      int i10 = Math.abs(paramConfigAttribs1.nvSamples - paramConfigAttribs2.nvSamples);
      int i11 = i9 + i10 * i10;
      if (paramConfigAttribs1.nv_depthEncoding != 12515) {
        i11++;
      }
      if ((paramConfigAttribs2.nvSamples > 0) && (paramConfigAttribs1.nvSamples > 0)) {
        i11++;
      }
      return i11;
    }
    
    public EGLConfig chooseConfig(EGL10 paramEGL10, EGLDisplay paramEGLDisplay)
    {
      GLView.ConfigAttribs localConfigAttribs1 = new GLView.ConfigAttribs(GLView.this);
      localConfigAttribs1.redBits = 8;
      localConfigAttribs1.greenBits = 8;
      localConfigAttribs1.blueBits = 8;
      localConfigAttribs1.alphaBits = 0;
      localConfigAttribs1.stencilBits = 8;
      localConfigAttribs1.depthBits = 24;
      localConfigAttribs1.samples = 0;
      localConfigAttribs1.nvSamples = 0;
      int[] arrayOfInt = new int[1];
      paramEGL10.eglGetConfigs(paramEGLDisplay, null, 0, arrayOfInt);
      if (arrayOfInt[0] > 0)
      {
        EGLConfig[] arrayOfEGLConfig = new EGLConfig[arrayOfInt[0]];
        paramEGL10.eglGetConfigs(paramEGLDisplay, arrayOfEGLConfig, arrayOfInt[0], arrayOfInt);
        int i = -1;
        int j = -1;
        for (int k = 0; k < arrayOfEGLConfig.length; k++)
        {
          GLView.ConfigAttribs localConfigAttribs2 = new GLView.ConfigAttribs(GLView.this, paramEGL10, paramEGLDisplay, arrayOfEGLConfig[k]);
          localConfigAttribs2.print(false);
          int m = scoreConfig(localConfigAttribs2, localConfigAttribs1);
          if ((m != -1) && ((m < j) || (j == -1)))
          {
            j = m;
            i = k;
          }
        }
        Log.i("GLView", "found config = " + i);
        if (i == -1)
        {
          localConfigAttribs1.depthBits = 16;
          for (int n = 0; n < arrayOfEGLConfig.length; n++)
          {
            GLView.ConfigAttribs localConfigAttribs3 = new GLView.ConfigAttribs(GLView.this, paramEGL10, paramEGLDisplay, arrayOfEGLConfig[n]);
            localConfigAttribs3.print(false);
            int i1 = scoreConfig(localConfigAttribs3, localConfigAttribs1);
            if ((i1 != -1) && ((i1 < j) || (j == -1)))
            {
              j = i1;
              i = n;
            }
          }
        }
        new GLView.ConfigAttribs(GLView.this, paramEGL10, paramEGLDisplay, arrayOfEGLConfig[i]).print(true);
        return arrayOfEGLConfig[i];
      }
      Log.e("GLView", "Failed to find acceptable EGLConfig!");
      if (!$assertionsDisabled) {
        throw new AssertionError();
      }
      return null;
    }
  }
  
  private class ContextFactory
    implements GLSurfaceView.EGLContextFactory
  {
    private ContextFactory() {}
    
    public EGLContext createContext(EGL10 paramEGL10, EGLDisplay paramEGLDisplay, EGLConfig paramEGLConfig)
    {
      Log.w("RealRacing3", "creating OpenGL ES 2.0 context");
      int[] arrayOfInt = { 12440, 2, 12344 };
      GLView.access$302(GLView.this, paramEGL10.eglCreateContext(paramEGLDisplay, paramEGLConfig, EGL10.EGL_NO_CONTEXT, arrayOfInt));
      new GLView.ConfigAttribs(GLView.this, paramEGL10, paramEGLDisplay, paramEGLConfig).print(true);
      return GLView.this.m_glContext;
    }
    
    public void destroyContext(EGL10 paramEGL10, EGLDisplay paramEGLDisplay, EGLContext paramEGLContext)
    {
      paramEGL10.eglDestroyContext(paramEGLDisplay, paramEGLContext);
      GLView.access$302(GLView.this, null);
    }
  }
  
  class TouchEvent
    implements Runnable
  {
    int m_action;
    int m_id;
    boolean m_last;
    float m_x;
    float m_y;
    
    TouchEvent(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, boolean paramBoolean)
    {
      this.m_action = paramInt1;
      this.m_id = paramInt2;
      this.m_x = paramFloat1;
      this.m_y = paramFloat2;
      this.m_last = paramBoolean;
    }
    
    public void run()
    {
      try
      {
        if ((this.m_action == 0) || (this.m_action == 5))
        {
          MainActivity.instance.onTouchBeginJNI(this.m_id, this.m_x, this.m_y);
          return;
        }
        if (this.m_action == 2)
        {
          MainActivity.instance.onTouchMoveJNI(this.m_id, this.m_x, this.m_y);
          return;
        }
      }
      catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
      {
        Log.e("RealRacing3", "UnsatisfiedLinkError when processing touch input: " + localUnsatisfiedLinkError.getMessage());
        return;
      }
      if ((this.m_action == 1) || (this.m_action == 6))
      {
        MainActivity.instance.onTouchEndJNI(this.m_id, this.m_x, this.m_y, this.m_last);
        return;
      }
      if (this.m_action == 3) {
        MainActivity.instance.onTouchCancelJNI();
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.GLView
 * JD-Core Version:    0.7.0.1
 */