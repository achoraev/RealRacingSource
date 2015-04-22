package com.jirbo.adcolony;

class ADCLog
{
  static ADCLog debug;
  static ADCLog dev = new ADCLog(0, false);
  static ADCLog error = new ADCLog(3, true);
  static ADCLog info;
  StringBuilder buffer = new StringBuilder();
  boolean enabled;
  int log_level;
  
  static
  {
    debug = new ADCLog(1, false);
    info = new ADCLog(2, true);
  }
  
  ADCLog(int paramInt, boolean paramBoolean)
  {
    this.log_level = paramInt;
    this.enabled = paramBoolean;
  }
  
  boolean fail(String paramString)
  {
    print(paramString + '\n');
    return false;
  }
  
  int int_fail(String paramString)
  {
    print(paramString + '\n');
    return 0;
  }
  
  /* Error */
  ADCLog print(char paramChar)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 40	com/jirbo/adcolony/ADCLog:enabled	Z
    //   6: istore_3
    //   7: iload_3
    //   8: ifne +11 -> 19
    //   11: aload_0
    //   12: astore 5
    //   14: aload_0
    //   15: monitorexit
    //   16: aload 5
    //   18: areturn
    //   19: aload_0
    //   20: getfield 36	com/jirbo/adcolony/ADCLog:buffer	Ljava/lang/StringBuilder;
    //   23: iload_1
    //   24: invokevirtual 49	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   27: pop
    //   28: iload_1
    //   29: bipush 10
    //   31: if_icmpne +25 -> 56
    //   34: aload_0
    //   35: getfield 38	com/jirbo/adcolony/ADCLog:log_level	I
    //   38: aload_0
    //   39: getfield 36	com/jirbo/adcolony/ADCLog:buffer	Ljava/lang/StringBuilder;
    //   42: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   45: invokestatic 66	com/jirbo/adcolony/ADC:log	(ILjava/lang/String;)V
    //   48: aload_0
    //   49: getfield 36	com/jirbo/adcolony/ADCLog:buffer	Ljava/lang/StringBuilder;
    //   52: iconst_0
    //   53: invokevirtual 70	java/lang/StringBuilder:setLength	(I)V
    //   56: aload_0
    //   57: astore 5
    //   59: goto -45 -> 14
    //   62: astore_2
    //   63: aload_0
    //   64: monitorexit
    //   65: aload_2
    //   66: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	67	0	this	ADCLog
    //   0	67	1	paramChar	char
    //   62	4	2	localObject	Object
    //   6	2	3	bool	boolean
    //   12	46	5	localADCLog	ADCLog
    // Exception table:
    //   from	to	target	type
    //   2	7	62	finally
    //   19	28	62	finally
    //   34	56	62	finally
  }
  
  /* Error */
  ADCLog print(double paramDouble)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 40	com/jirbo/adcolony/ADCLog:enabled	Z
    //   6: istore 4
    //   8: iload 4
    //   10: ifne +11 -> 21
    //   13: aload_0
    //   14: astore 5
    //   16: aload_0
    //   17: monitorexit
    //   18: aload 5
    //   20: areturn
    //   21: dload_1
    //   22: iconst_2
    //   23: aload_0
    //   24: getfield 36	com/jirbo/adcolony/ADCLog:buffer	Ljava/lang/StringBuilder;
    //   27: invokestatic 77	com/jirbo/adcolony/ADCUtil:format	(DILjava/lang/StringBuilder;)V
    //   30: aload_0
    //   31: astore 5
    //   33: goto -17 -> 16
    //   36: astore_3
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_3
    //   40: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	41	0	this	ADCLog
    //   0	41	1	paramDouble	double
    //   36	4	3	localObject	Object
    //   6	3	4	bool	boolean
    //   14	18	5	localADCLog	ADCLog
    // Exception table:
    //   from	to	target	type
    //   2	8	36	finally
    //   21	30	36	finally
  }
  
  /* Error */
  ADCLog print(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 40	com/jirbo/adcolony/ADCLog:enabled	Z
    //   6: istore_3
    //   7: iload_3
    //   8: ifne +11 -> 19
    //   11: aload_0
    //   12: astore 5
    //   14: aload_0
    //   15: monitorexit
    //   16: aload 5
    //   18: areturn
    //   19: aload_0
    //   20: getfield 36	com/jirbo/adcolony/ADCLog:buffer	Ljava/lang/StringBuilder;
    //   23: iload_1
    //   24: invokevirtual 81	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   27: pop
    //   28: aload_0
    //   29: astore 5
    //   31: goto -17 -> 14
    //   34: astore_2
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_2
    //   38: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	39	0	this	ADCLog
    //   0	39	1	paramInt	int
    //   34	4	2	localObject	Object
    //   6	2	3	bool	boolean
    //   12	18	5	localADCLog	ADCLog
    // Exception table:
    //   from	to	target	type
    //   2	7	34	finally
    //   19	28	34	finally
  }
  
  /* Error */
  ADCLog print(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 40	com/jirbo/adcolony/ADCLog:enabled	Z
    //   6: ifeq +14 -> 20
    //   9: aload_1
    //   10: ifnonnull +14 -> 24
    //   13: aload_0
    //   14: ldc 84
    //   16: invokevirtual 57	com/jirbo/adcolony/ADCLog:print	(Ljava/lang/String;)Lcom/jirbo/adcolony/ADCLog;
    //   19: pop
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_0
    //   23: areturn
    //   24: aload_0
    //   25: aload_1
    //   26: invokevirtual 85	java/lang/Object:toString	()Ljava/lang/String;
    //   29: invokevirtual 57	com/jirbo/adcolony/ADCLog:print	(Ljava/lang/String;)Lcom/jirbo/adcolony/ADCLog;
    //   32: pop
    //   33: goto -13 -> 20
    //   36: astore_2
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_2
    //   40: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	41	0	this	ADCLog
    //   0	41	1	paramObject	Object
    //   36	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	9	36	finally
    //   13	20	36	finally
    //   24	33	36	finally
  }
  
  ADCLog print(String paramString)
  {
    for (;;)
    {
      try
      {
        boolean bool = this.enabled;
        if (!bool)
        {
          localADCLog = this;
          return localADCLog;
        }
        if (paramString == null)
        {
          this.buffer.append("null");
        }
        else
        {
          int i = paramString.length();
          int j = 0;
          if (j < i)
          {
            print(paramString.charAt(j));
            j++;
            continue;
          }
        }
        ADCLog localADCLog = this;
      }
      finally {}
    }
  }
  
  /* Error */
  ADCLog print(boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 40	com/jirbo/adcolony/ADCLog:enabled	Z
    //   6: istore_3
    //   7: iload_3
    //   8: ifne +11 -> 19
    //   11: aload_0
    //   12: astore 5
    //   14: aload_0
    //   15: monitorexit
    //   16: aload 5
    //   18: areturn
    //   19: aload_0
    //   20: getfield 36	com/jirbo/adcolony/ADCLog:buffer	Ljava/lang/StringBuilder;
    //   23: iload_1
    //   24: invokevirtual 101	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   27: pop
    //   28: aload_0
    //   29: astore 5
    //   31: goto -17 -> 14
    //   34: astore_2
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_2
    //   38: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	39	0	this	ADCLog
    //   0	39	1	paramBoolean	boolean
    //   34	4	2	localObject	Object
    //   6	2	3	bool	boolean
    //   12	18	5	localADCLog	ADCLog
    // Exception table:
    //   from	to	target	type
    //   2	7	34	finally
    //   19	28	34	finally
  }
  
  ADCLog println()
  {
    try
    {
      ADCLog localADCLog = print('\n');
      return localADCLog;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  ADCLog println(double paramDouble)
  {
    try
    {
      print(paramDouble);
      ADCLog localADCLog = print('\n');
      return localADCLog;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  ADCLog println(int paramInt)
  {
    try
    {
      print(paramInt);
      ADCLog localADCLog = print('\n');
      return localADCLog;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  ADCLog println(Object paramObject)
  {
    try
    {
      print(paramObject);
      ADCLog localADCLog = print('\n');
      return localADCLog;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  ADCLog println(boolean paramBoolean)
  {
    try
    {
      print(paramBoolean);
      ADCLog localADCLog = print('\n');
      return localADCLog;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCLog
 * JD-Core Version:    0.7.0.1
 */