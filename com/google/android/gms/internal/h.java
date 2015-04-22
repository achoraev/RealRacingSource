package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public abstract class h
  implements g
{
  protected MotionEvent kw;
  protected DisplayMetrics kx;
  protected m ky;
  private n kz;
  
  protected h(Context paramContext, m paramm, n paramn)
  {
    this.ky = paramm;
    this.kz = paramn;
    try
    {
      this.kx = paramContext.getResources().getDisplayMetrics();
      return;
    }
    catch (UnsupportedOperationException localUnsupportedOperationException)
    {
      this.kx = new DisplayMetrics();
      this.kx.density = 1.0F;
    }
  }
  
  /* Error */
  private String a(Context paramContext, String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 57	com/google/android/gms/internal/h:t	()V
    //   6: iload_3
    //   7: ifeq +31 -> 38
    //   10: aload_0
    //   11: aload_1
    //   12: invokevirtual 61	com/google/android/gms/internal/h:c	(Landroid/content/Context;)V
    //   15: aload_0
    //   16: invokespecial 65	com/google/android/gms/internal/h:u	()[B
    //   19: astore 8
    //   21: aload_0
    //   22: monitorexit
    //   23: aload 8
    //   25: arraylength
    //   26: ifne +35 -> 61
    //   29: iconst_5
    //   30: invokestatic 71	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   33: astore 10
    //   35: aload 10
    //   37: areturn
    //   38: aload_0
    //   39: aload_1
    //   40: invokevirtual 74	com/google/android/gms/internal/h:b	(Landroid/content/Context;)V
    //   43: goto -28 -> 15
    //   46: astore 7
    //   48: aload_0
    //   49: monitorexit
    //   50: aload 7
    //   52: athrow
    //   53: astore 6
    //   55: bipush 7
    //   57: invokestatic 71	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   60: areturn
    //   61: aload_0
    //   62: aload 8
    //   64: aload_2
    //   65: invokevirtual 77	com/google/android/gms/internal/h:a	([BLjava/lang/String;)Ljava/lang/String;
    //   68: astore 9
    //   70: aload 9
    //   72: areturn
    //   73: astore 5
    //   75: bipush 7
    //   77: invokestatic 71	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   80: areturn
    //   81: astore 4
    //   83: iconst_3
    //   84: invokestatic 71	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   87: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	88	0	this	h
    //   0	88	1	paramContext	Context
    //   0	88	2	paramString	String
    //   0	88	3	paramBoolean	boolean
    //   81	1	4	localIOException	IOException
    //   73	1	5	localUnsupportedEncodingException	UnsupportedEncodingException
    //   53	1	6	localNoSuchAlgorithmException	NoSuchAlgorithmException
    //   46	5	7	localObject	Object
    //   19	44	8	arrayOfByte	byte[]
    //   68	3	9	str1	String
    //   33	3	10	str2	String
    // Exception table:
    //   from	to	target	type
    //   2	6	46	finally
    //   10	15	46	finally
    //   15	23	46	finally
    //   38	43	46	finally
    //   48	50	46	finally
    //   0	2	53	java/security/NoSuchAlgorithmException
    //   23	35	53	java/security/NoSuchAlgorithmException
    //   50	53	53	java/security/NoSuchAlgorithmException
    //   61	70	53	java/security/NoSuchAlgorithmException
    //   0	2	73	java/io/UnsupportedEncodingException
    //   23	35	73	java/io/UnsupportedEncodingException
    //   50	53	73	java/io/UnsupportedEncodingException
    //   61	70	73	java/io/UnsupportedEncodingException
    //   0	2	81	java/io/IOException
    //   23	35	81	java/io/IOException
    //   50	53	81	java/io/IOException
    //   61	70	81	java/io/IOException
  }
  
  private void t()
  {
    this.kz.reset();
  }
  
  private byte[] u()
    throws IOException
  {
    return this.kz.A();
  }
  
  public String a(Context paramContext)
  {
    return a(paramContext, null, false);
  }
  
  public String a(Context paramContext, String paramString)
  {
    return a(paramContext, paramString, true);
  }
  
  String a(byte[] paramArrayOfByte, String paramString)
    throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException
  {
    if (paramArrayOfByte.length > 239)
    {
      t();
      a(20, 1L);
      paramArrayOfByte = u();
    }
    byte[] arrayOfByte5;
    if (paramArrayOfByte.length < 239)
    {
      arrayOfByte5 = new byte[239 - paramArrayOfByte.length];
      new SecureRandom().nextBytes(arrayOfByte5);
    }
    for (byte[] arrayOfByte1 = ByteBuffer.allocate(240).put((byte)paramArrayOfByte.length).put(paramArrayOfByte).put(arrayOfByte5).array();; arrayOfByte1 = ByteBuffer.allocate(240).put((byte)paramArrayOfByte.length).put(paramArrayOfByte).array())
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(arrayOfByte1);
      byte[] arrayOfByte2 = localMessageDigest.digest();
      byte[] arrayOfByte3 = ByteBuffer.allocate(256).put(arrayOfByte2).put(arrayOfByte1).array();
      byte[] arrayOfByte4 = new byte[256];
      new f().a(arrayOfByte3, arrayOfByte4);
      if ((paramString != null) && (paramString.length() > 0)) {
        a(paramString, arrayOfByte4);
      }
      return this.ky.a(arrayOfByte4, true);
    }
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.kw != null) {
      this.kw.recycle();
    }
    this.kw = MotionEvent.obtain(0L, paramInt3, 1, paramInt1 * this.kx.density, paramInt2 * this.kx.density, 0.0F, 0.0F, 0, 0.0F, 0.0F, 0, 0);
  }
  
  protected void a(int paramInt, long paramLong)
    throws IOException
  {
    this.kz.b(paramInt, paramLong);
  }
  
  protected void a(int paramInt, String paramString)
    throws IOException
  {
    this.kz.b(paramInt, paramString);
  }
  
  public void a(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 1)
    {
      if (this.kw != null) {
        this.kw.recycle();
      }
      this.kw = MotionEvent.obtain(paramMotionEvent);
    }
  }
  
  void a(String paramString, byte[] paramArrayOfByte)
    throws UnsupportedEncodingException
  {
    if (paramString.length() > 32) {
      paramString = paramString.substring(0, 32);
    }
    new pe(paramString.getBytes("UTF-8")).o(paramArrayOfByte);
  }
  
  protected abstract void b(Context paramContext);
  
  protected abstract void c(Context paramContext);
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.h
 * JD-Core Version:    0.7.0.1
 */