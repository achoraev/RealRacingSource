package com.jirbo.adcolony;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

class Installation
{
  private static final String INSTALLATION = "INSTALLATION";
  private static String sID = null;
  
  /* Error */
  public static String id(android.content.Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 12	com/jirbo/adcolony/Installation:sID	Ljava/lang/String;
    //   6: ifnonnull +35 -> 41
    //   9: new 21	java/io/File
    //   12: dup
    //   13: aload_0
    //   14: invokevirtual 27	android/content/Context:getFilesDir	()Ljava/io/File;
    //   17: ldc 7
    //   19: invokespecial 30	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   22: astore_2
    //   23: aload_2
    //   24: invokevirtual 34	java/io/File:exists	()Z
    //   27: ifne +7 -> 34
    //   30: aload_2
    //   31: invokestatic 38	com/jirbo/adcolony/Installation:writeInstallationFile	(Ljava/io/File;)V
    //   34: aload_2
    //   35: invokestatic 42	com/jirbo/adcolony/Installation:readInstallationFile	(Ljava/io/File;)Ljava/lang/String;
    //   38: putstatic 12	com/jirbo/adcolony/Installation:sID	Ljava/lang/String;
    //   41: getstatic 12	com/jirbo/adcolony/Installation:sID	Ljava/lang/String;
    //   44: astore 4
    //   46: ldc 2
    //   48: monitorexit
    //   49: aload 4
    //   51: areturn
    //   52: astore_3
    //   53: new 44	com/jirbo/adcolony/AdColonyException
    //   56: dup
    //   57: aload_3
    //   58: invokevirtual 48	java/lang/Exception:toString	()Ljava/lang/String;
    //   61: invokespecial 51	com/jirbo/adcolony/AdColonyException:<init>	(Ljava/lang/String;)V
    //   64: athrow
    //   65: astore_1
    //   66: ldc 2
    //   68: monitorexit
    //   69: aload_1
    //   70: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	71	0	paramContext	android.content.Context
    //   65	5	1	localObject	Object
    //   22	13	2	localFile	File
    //   52	6	3	localException	java.lang.Exception
    //   44	6	4	str	String
    // Exception table:
    //   from	to	target	type
    //   23	34	52	java/lang/Exception
    //   34	41	52	java/lang/Exception
    //   3	23	65	finally
    //   23	34	65	finally
    //   34	41	65	finally
    //   41	46	65	finally
    //   53	65	65	finally
  }
  
  private static String readInstallationFile(File paramFile)
    throws IOException
  {
    RandomAccessFile localRandomAccessFile = new RandomAccessFile(paramFile, "r");
    byte[] arrayOfByte = new byte[(int)localRandomAccessFile.length()];
    localRandomAccessFile.readFully(arrayOfByte);
    localRandomAccessFile.close();
    return new String(arrayOfByte);
  }
  
  private static void writeInstallationFile(File paramFile)
    throws IOException
  {
    FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
    localFileOutputStream.write(UUID.randomUUID().toString().getBytes());
    localFileOutputStream.close();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.Installation
 * JD-Core Version:    0.7.0.1
 */