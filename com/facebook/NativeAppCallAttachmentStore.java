package com.facebook;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

public final class NativeAppCallAttachmentStore
  implements NativeAppCallContentProvider.AttachmentDataSource
{
  static final String ATTACHMENTS_DIR_NAME = "com.facebook.NativeAppCallAttachmentStore.files";
  private static final String TAG = NativeAppCallAttachmentStore.class.getName();
  private static File attachmentsDirectory;
  
  /* Error */
  private <T> void addAttachments(Context paramContext, UUID paramUUID, Map<String, T> paramMap, ProcessAttachment<T> paramProcessAttachment)
  {
    // Byte code:
    //   0: aload_3
    //   1: invokeinterface 38 1 0
    //   6: ifne +4 -> 10
    //   9: return
    //   10: getstatic 40	com/facebook/NativeAppCallAttachmentStore:attachmentsDirectory	Ljava/io/File;
    //   13: ifnonnull +8 -> 21
    //   16: aload_0
    //   17: aload_1
    //   18: invokevirtual 44	com/facebook/NativeAppCallAttachmentStore:cleanupAllAttachments	(Landroid/content/Context;)V
    //   21: aload_0
    //   22: aload_1
    //   23: invokevirtual 48	com/facebook/NativeAppCallAttachmentStore:ensureAttachmentsDirectoryExists	(Landroid/content/Context;)Ljava/io/File;
    //   26: pop
    //   27: new 50	java/util/ArrayList
    //   30: dup
    //   31: invokespecial 51	java/util/ArrayList:<init>	()V
    //   34: astore 6
    //   36: aload_3
    //   37: invokeinterface 55 1 0
    //   42: invokeinterface 61 1 0
    //   47: astore 13
    //   49: aload 13
    //   51: invokeinterface 67 1 0
    //   56: ifeq -47 -> 9
    //   59: aload 13
    //   61: invokeinterface 71 1 0
    //   66: checkcast 73	java/util/Map$Entry
    //   69: astore 14
    //   71: aload 14
    //   73: invokeinterface 76 1 0
    //   78: checkcast 78	java/lang/String
    //   81: astore 15
    //   83: aload 14
    //   85: invokeinterface 81 1 0
    //   90: astore 16
    //   92: aload_0
    //   93: aload_2
    //   94: aload 15
    //   96: iconst_1
    //   97: invokevirtual 85	com/facebook/NativeAppCallAttachmentStore:getAttachmentFile	(Ljava/util/UUID;Ljava/lang/String;Z)Ljava/io/File;
    //   100: astore 17
    //   102: aload 6
    //   104: aload 17
    //   106: invokeinterface 91 2 0
    //   111: pop
    //   112: aload 4
    //   114: aload 16
    //   116: aload 17
    //   118: invokeinterface 97 3 0
    //   123: goto -74 -> 49
    //   126: astore 7
    //   128: getstatic 23	com/facebook/NativeAppCallAttachmentStore:TAG	Ljava/lang/String;
    //   131: new 99	java/lang/StringBuilder
    //   134: dup
    //   135: invokespecial 100	java/lang/StringBuilder:<init>	()V
    //   138: ldc 102
    //   140: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: aload 7
    //   145: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   148: invokevirtual 112	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   151: invokestatic 118	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   154: pop
    //   155: aload 6
    //   157: invokeinterface 119 1 0
    //   162: astore 9
    //   164: aload 9
    //   166: invokeinterface 67 1 0
    //   171: ifeq +29 -> 200
    //   174: aload 9
    //   176: invokeinterface 71 1 0
    //   181: checkcast 121	java/io/File
    //   184: astore 10
    //   186: aload 10
    //   188: invokevirtual 124	java/io/File:delete	()Z
    //   191: pop
    //   192: goto -28 -> 164
    //   195: astore 11
    //   197: goto -33 -> 164
    //   200: new 126	com/facebook/FacebookException
    //   203: dup
    //   204: aload 7
    //   206: invokespecial 129	com/facebook/FacebookException:<init>	(Ljava/lang/Throwable;)V
    //   209: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	210	0	this	NativeAppCallAttachmentStore
    //   0	210	1	paramContext	Context
    //   0	210	2	paramUUID	UUID
    //   0	210	3	paramMap	Map<String, T>
    //   0	210	4	paramProcessAttachment	ProcessAttachment<T>
    //   34	122	6	localArrayList	java.util.ArrayList
    //   126	79	7	localIOException	IOException
    //   162	13	9	localIterator1	java.util.Iterator
    //   184	3	10	localFile1	File
    //   195	1	11	localException	java.lang.Exception
    //   47	13	13	localIterator2	java.util.Iterator
    //   69	15	14	localEntry	java.util.Map.Entry
    //   81	14	15	str	String
    //   90	25	16	localObject	Object
    //   100	17	17	localFile2	File
    // Exception table:
    //   from	to	target	type
    //   36	49	126	java/io/IOException
    //   49	123	126	java/io/IOException
    //   186	192	195	java/lang/Exception
  }
  
  static File getAttachmentsDirectory(Context paramContext)
  {
    try
    {
      if (attachmentsDirectory == null) {
        attachmentsDirectory = new File(paramContext.getCacheDir(), "com.facebook.NativeAppCallAttachmentStore.files");
      }
      File localFile = attachmentsDirectory;
      return localFile;
    }
    finally {}
  }
  
  public void addAttachmentFilesForCall(Context paramContext, UUID paramUUID, Map<String, File> paramMap)
  {
    Validate.notNull(paramContext, "context");
    Validate.notNull(paramUUID, "callId");
    Validate.containsNoNulls(paramMap.values(), "imageAttachmentFiles");
    Validate.containsNoNullOrEmpty(paramMap.keySet(), "imageAttachmentFiles");
    addAttachments(paramContext, paramUUID, paramMap, new ProcessAttachment()
    {
      public void processAttachment(File paramAnonymousFile1, File paramAnonymousFile2)
        throws IOException
      {
        FileOutputStream localFileOutputStream = new FileOutputStream(paramAnonymousFile2);
        try
        {
          localFileInputStream1 = new FileInputStream(paramAnonymousFile1);
          try
          {
            byte[] arrayOfByte = new byte[1024];
            for (;;)
            {
              int i = localFileInputStream1.read(arrayOfByte);
              if (i <= 0) {
                break;
              }
              localFileOutputStream.write(arrayOfByte, 0, i);
            }
            Utility.closeQuietly(localFileOutputStream);
          }
          finally
          {
            localFileInputStream2 = localFileInputStream1;
          }
        }
        finally
        {
          for (;;)
          {
            FileInputStream localFileInputStream1;
            FileInputStream localFileInputStream2 = null;
          }
        }
        Utility.closeQuietly(localFileInputStream2);
        throw localObject1;
        Utility.closeQuietly(localFileOutputStream);
        Utility.closeQuietly(localFileInputStream1);
      }
    });
  }
  
  public void addAttachmentsForCall(Context paramContext, UUID paramUUID, Map<String, Bitmap> paramMap)
  {
    Validate.notNull(paramContext, "context");
    Validate.notNull(paramUUID, "callId");
    Validate.containsNoNulls(paramMap.values(), "imageAttachments");
    Validate.containsNoNullOrEmpty(paramMap.keySet(), "imageAttachments");
    addAttachments(paramContext, paramUUID, paramMap, new ProcessAttachment()
    {
      public void processAttachment(Bitmap paramAnonymousBitmap, File paramAnonymousFile)
        throws IOException
      {
        FileOutputStream localFileOutputStream = new FileOutputStream(paramAnonymousFile);
        try
        {
          paramAnonymousBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localFileOutputStream);
          return;
        }
        finally
        {
          Utility.closeQuietly(localFileOutputStream);
        }
      }
    });
  }
  
  void cleanupAllAttachments(Context paramContext)
  {
    Utility.deleteDirectory(getAttachmentsDirectory(paramContext));
  }
  
  public void cleanupAttachmentsForCall(Context paramContext, UUID paramUUID)
  {
    Utility.deleteDirectory(getAttachmentsDirectoryForCall(paramUUID, false));
  }
  
  File ensureAttachmentsDirectoryExists(Context paramContext)
  {
    File localFile = getAttachmentsDirectory(paramContext);
    localFile.mkdirs();
    return localFile;
  }
  
  File getAttachmentFile(UUID paramUUID, String paramString, boolean paramBoolean)
    throws IOException
  {
    File localFile1 = getAttachmentsDirectoryForCall(paramUUID, paramBoolean);
    if (localFile1 == null) {
      return null;
    }
    try
    {
      File localFile2 = new File(localFile1, URLEncoder.encode(paramString, "UTF-8"));
      return localFile2;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
    return null;
  }
  
  File getAttachmentsDirectoryForCall(UUID paramUUID, boolean paramBoolean)
  {
    File localFile;
    if (attachmentsDirectory == null) {
      localFile = null;
    }
    do
    {
      return localFile;
      localFile = new File(attachmentsDirectory, paramUUID.toString());
    } while ((!paramBoolean) || (localFile.exists()));
    localFile.mkdirs();
    return localFile;
  }
  
  public File openAttachment(UUID paramUUID, String paramString)
    throws FileNotFoundException
  {
    if ((Utility.isNullOrEmpty(paramString)) || (paramUUID == null)) {
      throw new FileNotFoundException();
    }
    try
    {
      File localFile = getAttachmentFile(paramUUID, paramString, false);
      return localFile;
    }
    catch (IOException localIOException)
    {
      throw new FileNotFoundException();
    }
  }
  
  static abstract interface ProcessAttachment<T>
  {
    public abstract void processAttachment(T paramT, File paramFile)
      throws IOException;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.facebook.NativeAppCallAttachmentStore
 * JD-Core Version:    0.7.0.1
 */