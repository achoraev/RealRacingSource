package com.ultra.sdk.volley.toolbox;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ByteArrayPool
{
  protected static final Comparator<byte[]> BUF_COMPARATOR = new Comparator()
  {
    public int compare(byte[] paramAnonymousArrayOfByte1, byte[] paramAnonymousArrayOfByte2)
    {
      return paramAnonymousArrayOfByte1.length - paramAnonymousArrayOfByte2.length;
    }
  };
  private List<byte[]> mBuffersByLastUse = new LinkedList();
  private List<byte[]> mBuffersBySize = new ArrayList(64);
  private int mCurrentSize = 0;
  private final int mSizeLimit;
  
  public ByteArrayPool(int paramInt)
  {
    this.mSizeLimit = paramInt;
  }
  
  /* Error */
  private void trim()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 38	com/ultra/sdk/volley/toolbox/ByteArrayPool:mCurrentSize	I
    //   6: istore_2
    //   7: aload_0
    //   8: getfield 40	com/ultra/sdk/volley/toolbox/ByteArrayPool:mSizeLimit	I
    //   11: istore_3
    //   12: iload_2
    //   13: iload_3
    //   14: if_icmpgt +6 -> 20
    //   17: aload_0
    //   18: monitorexit
    //   19: return
    //   20: aload_0
    //   21: getfield 30	com/ultra/sdk/volley/toolbox/ByteArrayPool:mBuffersByLastUse	Ljava/util/List;
    //   24: iconst_0
    //   25: invokeinterface 47 2 0
    //   30: checkcast 49	[B
    //   33: astore 4
    //   35: aload_0
    //   36: getfield 36	com/ultra/sdk/volley/toolbox/ByteArrayPool:mBuffersBySize	Ljava/util/List;
    //   39: aload 4
    //   41: invokeinterface 52 2 0
    //   46: pop
    //   47: aload_0
    //   48: aload_0
    //   49: getfield 38	com/ultra/sdk/volley/toolbox/ByteArrayPool:mCurrentSize	I
    //   52: aload 4
    //   54: arraylength
    //   55: isub
    //   56: putfield 38	com/ultra/sdk/volley/toolbox/ByteArrayPool:mCurrentSize	I
    //   59: goto -57 -> 2
    //   62: astore_1
    //   63: aload_0
    //   64: monitorexit
    //   65: aload_1
    //   66: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	67	0	this	ByteArrayPool
    //   62	4	1	localObject	Object
    //   6	9	2	i	int
    //   11	4	3	j	int
    //   33	20	4	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   2	12	62	finally
    //   20	59	62	finally
  }
  
  public byte[] getBuf(int paramInt)
  {
    int i = 0;
    for (;;)
    {
      try
      {
        if (i >= this.mBuffersBySize.size())
        {
          arrayOfByte = new byte[paramInt];
          return arrayOfByte;
        }
        byte[] arrayOfByte = (byte[])this.mBuffersBySize.get(i);
        if (arrayOfByte.length >= paramInt)
        {
          this.mCurrentSize -= arrayOfByte.length;
          this.mBuffersBySize.remove(i);
          this.mBuffersByLastUse.remove(arrayOfByte);
        }
        else
        {
          i++;
        }
      }
      finally {}
    }
  }
  
  /* Error */
  public void returnBuf(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnull +18 -> 21
    //   6: aload_1
    //   7: arraylength
    //   8: istore_3
    //   9: aload_0
    //   10: getfield 40	com/ultra/sdk/volley/toolbox/ByteArrayPool:mSizeLimit	I
    //   13: istore 4
    //   15: iload_3
    //   16: iload 4
    //   18: if_icmple +6 -> 24
    //   21: aload_0
    //   22: monitorexit
    //   23: return
    //   24: aload_0
    //   25: getfield 30	com/ultra/sdk/volley/toolbox/ByteArrayPool:mBuffersByLastUse	Ljava/util/List;
    //   28: aload_1
    //   29: invokeinterface 66 2 0
    //   34: pop
    //   35: aload_0
    //   36: getfield 36	com/ultra/sdk/volley/toolbox/ByteArrayPool:mBuffersBySize	Ljava/util/List;
    //   39: aload_1
    //   40: getstatic 23	com/ultra/sdk/volley/toolbox/ByteArrayPool:BUF_COMPARATOR	Ljava/util/Comparator;
    //   43: invokestatic 72	java/util/Collections:binarySearch	(Ljava/util/List;Ljava/lang/Object;Ljava/util/Comparator;)I
    //   46: istore 6
    //   48: iload 6
    //   50: ifge +10 -> 60
    //   53: iconst_m1
    //   54: iload 6
    //   56: ineg
    //   57: iadd
    //   58: istore 6
    //   60: aload_0
    //   61: getfield 36	com/ultra/sdk/volley/toolbox/ByteArrayPool:mBuffersBySize	Ljava/util/List;
    //   64: iload 6
    //   66: aload_1
    //   67: invokeinterface 75 3 0
    //   72: aload_0
    //   73: aload_0
    //   74: getfield 38	com/ultra/sdk/volley/toolbox/ByteArrayPool:mCurrentSize	I
    //   77: aload_1
    //   78: arraylength
    //   79: iadd
    //   80: putfield 38	com/ultra/sdk/volley/toolbox/ByteArrayPool:mCurrentSize	I
    //   83: aload_0
    //   84: invokespecial 77	com/ultra/sdk/volley/toolbox/ByteArrayPool:trim	()V
    //   87: goto -66 -> 21
    //   90: astore_2
    //   91: aload_0
    //   92: monitorexit
    //   93: aload_2
    //   94: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	95	0	this	ByteArrayPool
    //   0	95	1	paramArrayOfByte	byte[]
    //   90	4	2	localObject	Object
    //   8	11	3	i	int
    //   13	6	4	j	int
    //   46	19	6	k	int
    // Exception table:
    //   from	to	target	type
    //   6	15	90	finally
    //   24	48	90	finally
    //   60	87	90	finally
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ultra.sdk.volley.toolbox.ByteArrayPool
 * JD-Core Version:    0.7.0.1
 */