package com.millennialmedia.android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;

class BridgeMMMedia
  extends MMJSObject
{
  private static final String PATH = "path";
  private static final String TAG = "BridgeMMMedia";
  private static Object pickerActivityObject;
  private String AVAILABLE_SOURCE_TYPES = "availableSourceTypes";
  private String GET_DEVICE_VOLUME = "getDeviceVolume";
  private String GET_PICTURE = "getPicture";
  private String IS_SOURCE_TYPE_AVAILABLE = "isSourceTypeAvailable";
  private String PLAY_AUDIO = "playAudio";
  private String PLAY_SOUND = "playSound";
  private String PLAY_VIDEO = "playVideo";
  private String STOP_AUDIO = "stopAudio";
  private String WRITE_TO_PHOTO_LIBRARY = "writeToPhotoLibrary";
  MediaScannerConnection mediaScanner;
  
  private static Bitmap centerOfImage(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    float f1 = (paramBitmap.getWidth() - paramInt1) / 2;
    float f2 = (paramBitmap.getHeight() - paramInt2) / 2;
    return cropImage(paramBitmap, (int)f1, (int)f2, paramInt1, paramInt2);
  }
  
  private static Bitmap cropImage(Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return Bitmap.createBitmap(paramBitmap, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  private boolean isCameraAvailable()
  {
    Context localContext = (Context)this.contextRef.get();
    boolean bool = false;
    if (localContext != null)
    {
      int i = localContext.getPackageManager().checkPermission("android.permission.CAMERA", localContext.getPackageName());
      bool = false;
      if (i == 0)
      {
        Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        int j = localContext.getPackageManager().queryIntentActivities(localIntent, 65536).size();
        bool = false;
        if (j > 0) {
          bool = true;
        }
      }
    }
    return bool;
  }
  
  private boolean isPictureChooserAvailable()
  {
    Context localContext = (Context)this.contextRef.get();
    boolean bool = false;
    if (localContext != null)
    {
      Intent localIntent = new Intent();
      localIntent.setType("image/*");
      localIntent.setAction("android.intent.action.GET_CONTENT");
      int i = localContext.getPackageManager().queryIntentActivities(localIntent, 65536).size();
      bool = false;
      if (i > 0) {
        bool = true;
      }
    }
    return bool;
  }
  
  private static Bitmap resizeImage(Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3)
  {
    return Bitmap.createScaledBitmap(paramBitmap, paramInt1, paramInt2, true);
  }
  
  static Bitmap resizeImage(Bitmap paramBitmap, String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    float f1 = paramInt1 / paramBitmap.getWidth();
    float f2 = paramInt2 / paramBitmap.getHeight();
    if (paramString.equals("Center")) {
      return centerOfImage(paramBitmap, paramInt1, paramInt2);
    }
    if (paramString.equals("ScaleToAspectFit"))
    {
      float f4 = Math.min(f1, f2);
      return resizeImage(paramBitmap, (int)(f4 * paramBitmap.getWidth()), (int)(f4 * paramBitmap.getHeight()), paramInt3);
    }
    if (paramString.equals("ScaleToAspectFill"))
    {
      float f3 = Math.max(f1, f2);
      return cropImage(resizeImage(paramBitmap, (int)(f3 * paramBitmap.getWidth()), (int)(f3 * paramBitmap.getHeight()), paramInt3), 0, 0, paramInt1, paramInt2);
    }
    return resizeImage(paramBitmap, paramInt1, paramInt2, paramInt3);
  }
  
  /* Error */
  private static final byte[] scaleBitmapToBytes(File paramFile, int paramInt1, int paramInt2, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: new 187	java/io/FileInputStream
    //   9: dup
    //   10: aload_0
    //   11: invokespecial 190	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   14: astore 6
    //   16: new 192	android/graphics/BitmapFactory$Options
    //   19: dup
    //   20: invokespecial 193	android/graphics/BitmapFactory$Options:<init>	()V
    //   23: astore 7
    //   25: aload 7
    //   27: iconst_1
    //   28: putfield 197	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   31: aload 6
    //   33: aconst_null
    //   34: aload 7
    //   36: invokestatic 203	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   39: pop
    //   40: aload 7
    //   42: getfield 207	android/graphics/BitmapFactory$Options:outHeight	I
    //   45: istore 27
    //   47: aload 7
    //   49: getfield 210	android/graphics/BitmapFactory$Options:outWidth	I
    //   52: istore 28
    //   54: iconst_1
    //   55: istore 29
    //   57: iload 27
    //   59: iload_2
    //   60: if_icmpgt +9 -> 69
    //   63: iload 28
    //   65: iload_1
    //   66: if_icmple +21 -> 87
    //   69: iload 28
    //   71: iload 27
    //   73: if_icmple +172 -> 245
    //   76: iload 27
    //   78: i2f
    //   79: iload_2
    //   80: i2f
    //   81: fdiv
    //   82: invokestatic 214	java/lang/Math:round	(F)I
    //   85: istore 29
    //   87: new 187	java/io/FileInputStream
    //   90: dup
    //   91: aload_0
    //   92: invokespecial 190	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   95: astore 30
    //   97: aload 7
    //   99: iconst_0
    //   100: putfield 197	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   103: aload 7
    //   105: iload 29
    //   107: putfield 217	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   110: aload 30
    //   112: aconst_null
    //   113: aload 7
    //   115: invokestatic 203	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   118: astore 31
    //   120: aload 31
    //   122: astore 11
    //   124: aload 6
    //   126: ifnull +8 -> 134
    //   129: aload 6
    //   131: invokevirtual 220	java/io/FileInputStream:close	()V
    //   134: aload 30
    //   136: ifnull +8 -> 144
    //   139: aload 30
    //   141: invokevirtual 220	java/io/FileInputStream:close	()V
    //   144: aconst_null
    //   145: astore 13
    //   147: aload 11
    //   149: ifnull +93 -> 242
    //   152: aload 11
    //   154: aload_3
    //   155: iload_1
    //   156: iload_2
    //   157: iconst_1
    //   158: invokestatic 222	com/millennialmedia/android/BridgeMMMedia:resizeImage	(Landroid/graphics/Bitmap;Ljava/lang/String;III)Landroid/graphics/Bitmap;
    //   161: astore 14
    //   163: aconst_null
    //   164: astore 15
    //   166: new 224	java/io/ByteArrayOutputStream
    //   169: dup
    //   170: invokespecial 225	java/io/ByteArrayOutputStream:<init>	()V
    //   173: astore 16
    //   175: aload_3
    //   176: ldc 227
    //   178: invokevirtual 160	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   181: ifeq +196 -> 377
    //   184: getstatic 233	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   187: astore 24
    //   189: aload 11
    //   191: aload 24
    //   193: bipush 100
    //   195: aload 16
    //   197: invokevirtual 237	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   200: pop
    //   201: aload 16
    //   203: invokevirtual 241	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   206: astore 22
    //   208: aload 22
    //   210: astore 13
    //   212: aload 11
    //   214: ifnull +8 -> 222
    //   217: aload 11
    //   219: invokevirtual 244	android/graphics/Bitmap:recycle	()V
    //   222: aload 14
    //   224: ifnull +8 -> 232
    //   227: aload 14
    //   229: invokevirtual 244	android/graphics/Bitmap:recycle	()V
    //   232: aload 16
    //   234: ifnull +8 -> 242
    //   237: aload 16
    //   239: invokevirtual 245	java/io/ByteArrayOutputStream:close	()V
    //   242: aload 13
    //   244: areturn
    //   245: iload 28
    //   247: i2f
    //   248: iload_1
    //   249: i2f
    //   250: fdiv
    //   251: fstore 33
    //   253: fload 33
    //   255: invokestatic 214	java/lang/Math:round	(F)I
    //   258: istore 34
    //   260: iload 34
    //   262: istore 29
    //   264: goto -177 -> 87
    //   267: astore 32
    //   269: ldc 11
    //   271: ldc 247
    //   273: aload 32
    //   275: invokestatic 253	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   278: goto -134 -> 144
    //   281: astore 8
    //   283: ldc 11
    //   285: ldc 255
    //   287: aload 8
    //   289: invokestatic 253	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   292: aload 4
    //   294: ifnull +8 -> 302
    //   297: aload 4
    //   299: invokevirtual 220	java/io/FileInputStream:close	()V
    //   302: aconst_null
    //   303: astore 11
    //   305: aload 5
    //   307: ifnull -163 -> 144
    //   310: aload 5
    //   312: invokevirtual 220	java/io/FileInputStream:close	()V
    //   315: aconst_null
    //   316: astore 11
    //   318: goto -174 -> 144
    //   321: astore 12
    //   323: ldc 11
    //   325: ldc 247
    //   327: aload 12
    //   329: invokestatic 253	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   332: aconst_null
    //   333: astore 11
    //   335: goto -191 -> 144
    //   338: astore 9
    //   340: aload 4
    //   342: ifnull +8 -> 350
    //   345: aload 4
    //   347: invokevirtual 220	java/io/FileInputStream:close	()V
    //   350: aload 5
    //   352: ifnull +8 -> 360
    //   355: aload 5
    //   357: invokevirtual 220	java/io/FileInputStream:close	()V
    //   360: aload 9
    //   362: athrow
    //   363: astore 10
    //   365: ldc 11
    //   367: ldc 247
    //   369: aload 10
    //   371: invokestatic 253	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   374: goto -14 -> 360
    //   377: aload 14
    //   379: getstatic 233	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   382: bipush 100
    //   384: aload 16
    //   386: invokevirtual 237	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   389: pop
    //   390: goto -189 -> 201
    //   393: astore 19
    //   395: aload 16
    //   397: astore 15
    //   399: ldc 11
    //   401: ldc_w 257
    //   404: aload 19
    //   406: invokestatic 253	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   409: aload 11
    //   411: ifnull +8 -> 419
    //   414: aload 11
    //   416: invokevirtual 244	android/graphics/Bitmap:recycle	()V
    //   419: aload 14
    //   421: ifnull +8 -> 429
    //   424: aload 14
    //   426: invokevirtual 244	android/graphics/Bitmap:recycle	()V
    //   429: aconst_null
    //   430: astore 13
    //   432: aload 15
    //   434: ifnull -192 -> 242
    //   437: aload 15
    //   439: invokevirtual 245	java/io/ByteArrayOutputStream:close	()V
    //   442: aconst_null
    //   443: areturn
    //   444: astore 20
    //   446: ldc 11
    //   448: ldc 247
    //   450: aload 20
    //   452: invokestatic 253	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   455: aconst_null
    //   456: areturn
    //   457: astore 23
    //   459: ldc 11
    //   461: ldc 247
    //   463: aload 23
    //   465: invokestatic 253	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   468: aload 13
    //   470: areturn
    //   471: astore 17
    //   473: aload 11
    //   475: ifnull +8 -> 483
    //   478: aload 11
    //   480: invokevirtual 244	android/graphics/Bitmap:recycle	()V
    //   483: aload 14
    //   485: ifnull +8 -> 493
    //   488: aload 14
    //   490: invokevirtual 244	android/graphics/Bitmap:recycle	()V
    //   493: aload 15
    //   495: ifnull +8 -> 503
    //   498: aload 15
    //   500: invokevirtual 245	java/io/ByteArrayOutputStream:close	()V
    //   503: aload 17
    //   505: athrow
    //   506: astore 18
    //   508: ldc 11
    //   510: ldc 247
    //   512: aload 18
    //   514: invokestatic 253	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   517: goto -14 -> 503
    //   520: astore 17
    //   522: aload 16
    //   524: astore 15
    //   526: goto -53 -> 473
    //   529: astore 19
    //   531: aconst_null
    //   532: astore 15
    //   534: goto -135 -> 399
    //   537: astore 9
    //   539: aload 6
    //   541: astore 4
    //   543: aconst_null
    //   544: astore 5
    //   546: goto -206 -> 340
    //   549: astore 9
    //   551: aload 30
    //   553: astore 5
    //   555: aload 6
    //   557: astore 4
    //   559: goto -219 -> 340
    //   562: astore 8
    //   564: aload 6
    //   566: astore 4
    //   568: aconst_null
    //   569: astore 5
    //   571: goto -288 -> 283
    //   574: astore 8
    //   576: aload 30
    //   578: astore 5
    //   580: aload 6
    //   582: astore 4
    //   584: goto -301 -> 283
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	587	0	paramFile	File
    //   0	587	1	paramInt1	int
    //   0	587	2	paramInt2	int
    //   0	587	3	paramString	String
    //   1	582	4	localObject1	Object
    //   4	575	5	localObject2	Object
    //   14	567	6	localFileInputStream1	java.io.FileInputStream
    //   23	91	7	localOptions	android.graphics.BitmapFactory.Options
    //   281	7	8	localFileNotFoundException1	java.io.FileNotFoundException
    //   562	1	8	localFileNotFoundException2	java.io.FileNotFoundException
    //   574	1	8	localFileNotFoundException3	java.io.FileNotFoundException
    //   338	23	9	localObject3	Object
    //   537	1	9	localObject4	Object
    //   549	1	9	localObject5	Object
    //   363	7	10	localIOException1	java.io.IOException
    //   122	357	11	localBitmap1	Bitmap
    //   321	7	12	localIOException2	java.io.IOException
    //   145	324	13	localObject6	Object
    //   161	328	14	localBitmap2	Bitmap
    //   164	369	15	localObject7	Object
    //   173	350	16	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    //   471	33	17	localObject8	Object
    //   520	1	17	localObject9	Object
    //   506	7	18	localException1	Exception
    //   393	12	19	localException2	Exception
    //   529	1	19	localException3	Exception
    //   444	7	20	localException4	Exception
    //   206	3	22	arrayOfByte	byte[]
    //   457	7	23	localException5	Exception
    //   187	5	24	localCompressFormat	android.graphics.Bitmap.CompressFormat
    //   45	32	27	i	int
    //   52	194	28	j	int
    //   55	208	29	k	int
    //   95	482	30	localFileInputStream2	java.io.FileInputStream
    //   118	3	31	localBitmap3	Bitmap
    //   267	7	32	localIOException3	java.io.IOException
    //   251	3	33	f	float
    //   258	3	34	m	int
    // Exception table:
    //   from	to	target	type
    //   129	134	267	java/io/IOException
    //   139	144	267	java/io/IOException
    //   6	16	281	java/io/FileNotFoundException
    //   297	302	321	java/io/IOException
    //   310	315	321	java/io/IOException
    //   6	16	338	finally
    //   283	292	338	finally
    //   345	350	363	java/io/IOException
    //   355	360	363	java/io/IOException
    //   175	201	393	java/lang/Exception
    //   201	208	393	java/lang/Exception
    //   377	390	393	java/lang/Exception
    //   414	419	444	java/lang/Exception
    //   424	429	444	java/lang/Exception
    //   437	442	444	java/lang/Exception
    //   217	222	457	java/lang/Exception
    //   227	232	457	java/lang/Exception
    //   237	242	457	java/lang/Exception
    //   166	175	471	finally
    //   399	409	471	finally
    //   478	483	506	java/lang/Exception
    //   488	493	506	java/lang/Exception
    //   498	503	506	java/lang/Exception
    //   175	201	520	finally
    //   201	208	520	finally
    //   377	390	520	finally
    //   166	175	529	java/lang/Exception
    //   16	54	537	finally
    //   76	87	537	finally
    //   87	97	537	finally
    //   253	260	537	finally
    //   97	120	549	finally
    //   16	54	562	java/io/FileNotFoundException
    //   76	87	562	java/io/FileNotFoundException
    //   87	97	562	java/io/FileNotFoundException
    //   253	260	562	java/io/FileNotFoundException
    //   97	120	574	java/io/FileNotFoundException
  }
  
  private void scanMedia(final String paramString)
  {
    Context localContext = (Context)this.contextRef.get();
    if (localContext != null)
    {
      this.mediaScanner = new MediaScannerConnection(localContext.getApplicationContext(), new MediaScannerConnection.MediaScannerConnectionClient()
      {
        public void onMediaScannerConnected()
        {
          if (BridgeMMMedia.this.mediaScanner != null) {
            BridgeMMMedia.this.mediaScanner.scanFile(paramString, null);
          }
        }
        
        public void onScanCompleted(String paramAnonymousString, Uri paramAnonymousUri)
        {
          if (paramAnonymousUri == null) {
            MMLog.d("BridgeMMMedia", "Failed to scan " + paramAnonymousString);
          }
        }
      });
      if (this.mediaScanner != null) {
        this.mediaScanner.connect();
      }
    }
  }
  
  public MMJSResponse availableSourceTypes(Map<String, String> paramMap)
  {
    JSONArray localJSONArray = new JSONArray();
    if (isCameraAvailable()) {
      localJSONArray.put("Camera");
    }
    if (isPictureChooserAvailable()) {
      localJSONArray.put("Photo Library");
    }
    MMJSResponse localMMJSResponse = new MMJSResponse();
    localMMJSResponse.result = 1;
    localMMJSResponse.response = localJSONArray;
    return localMMJSResponse;
  }
  
  MMJSResponse executeCommand(String paramString, Map<String, String> paramMap)
  {
    MMJSResponse localMMJSResponse;
    if (this.IS_SOURCE_TYPE_AVAILABLE.equals(paramString)) {
      localMMJSResponse = isSourceTypeAvailable(paramMap);
    }
    boolean bool;
    do
    {
      return localMMJSResponse;
      if (this.AVAILABLE_SOURCE_TYPES.equals(paramString)) {
        return availableSourceTypes(paramMap);
      }
      if (this.GET_PICTURE.equals(paramString)) {
        return getPicture(paramMap);
      }
      if (this.WRITE_TO_PHOTO_LIBRARY.equals(paramString)) {
        return writeToPhotoLibrary(paramMap);
      }
      if (this.PLAY_VIDEO.equals(paramString)) {
        return playVideo(paramMap);
      }
      if (this.STOP_AUDIO.equals(paramString)) {
        return stopAudio(paramMap);
      }
      if (this.GET_DEVICE_VOLUME.equals(paramString)) {
        return getDeviceVolume(paramMap);
      }
      if (this.PLAY_AUDIO.equals(paramString)) {
        return playAudio(paramMap);
      }
      bool = this.PLAY_SOUND.equals(paramString);
      localMMJSResponse = null;
    } while (!bool);
    return playSound(paramMap);
  }
  
  public MMJSResponse getDeviceVolume(Map<String, String> paramMap)
  {
    Context localContext = (Context)this.contextRef.get();
    if (localContext != null)
    {
      int i = MMSDK.getMediaVolume(localContext);
      MMJSResponse localMMJSResponse = MMJSResponse.responseWithSuccess();
      localMMJSResponse.response = Integer.valueOf(i);
      return localMMJSResponse;
    }
    return MMJSResponse.responseWithError("no volume available");
  }
  
  /* Error */
  public MMJSResponse getPicture(Map<String, String> paramMap)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 92	com/millennialmedia/android/BridgeMMMedia:contextRef	Ljava/lang/ref/WeakReference;
    //   6: invokevirtual 97	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   9: checkcast 99	android/content/Context
    //   12: astore_3
    //   13: aload_1
    //   14: ldc_w 346
    //   17: invokeinterface 351 2 0
    //   22: checkcast 156	java/lang/String
    //   25: astore 4
    //   27: aload_1
    //   28: ldc_w 353
    //   31: invokeinterface 351 2 0
    //   36: checkcast 156	java/lang/String
    //   39: astore 5
    //   41: aload_1
    //   42: ldc_w 355
    //   45: invokeinterface 351 2 0
    //   50: checkcast 156	java/lang/String
    //   53: astore 6
    //   55: aload_1
    //   56: ldc_w 357
    //   59: invokeinterface 351 2 0
    //   64: checkcast 156	java/lang/String
    //   67: astore 7
    //   69: aload 7
    //   71: ifnonnull +327 -> 398
    //   74: ldc 227
    //   76: astore 7
    //   78: goto +320 -> 398
    //   81: ldc_w 359
    //   84: invokestatic 344	com/millennialmedia/android/MMJSResponse:responseWithError	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSResponse;
    //   87: astore 8
    //   89: aload 8
    //   91: astore 9
    //   93: aload_0
    //   94: monitorexit
    //   95: aload 9
    //   97: areturn
    //   98: aload 5
    //   100: invokestatic 365	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   103: f2i
    //   104: istore 10
    //   106: aload 6
    //   108: invokestatic 365	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   111: f2i
    //   112: istore 11
    //   114: iload 10
    //   116: iload 11
    //   118: imul
    //   119: ldc_w 366
    //   122: if_icmple +14 -> 136
    //   125: ldc_w 368
    //   128: invokestatic 344	com/millennialmedia/android/MMJSResponse:responseWithError	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSResponse;
    //   131: astore 9
    //   133: goto -40 -> 93
    //   136: aload_3
    //   137: ifnull +255 -> 392
    //   140: aload 4
    //   142: ifnull +250 -> 392
    //   145: new 370	java/io/File
    //   148: dup
    //   149: aload_3
    //   150: invokestatic 376	com/millennialmedia/android/AdCache:getInternalCacheDirectory	(Landroid/content/Context;)Ljava/io/File;
    //   153: new 378	java/lang/StringBuilder
    //   156: dup
    //   157: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   160: ldc_w 381
    //   163: invokevirtual 385	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: invokestatic 391	java/lang/System:currentTimeMillis	()J
    //   169: invokestatic 394	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   172: invokevirtual 385	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: ldc_w 396
    //   178: invokevirtual 385	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: invokevirtual 399	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   184: invokespecial 402	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   187: astore 12
    //   189: aload 4
    //   191: ldc_w 285
    //   194: invokevirtual 406	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   197: ifeq +10 -> 207
    //   200: aload_0
    //   201: invokespecial 283	com/millennialmedia/android/BridgeMMMedia:isCameraAvailable	()Z
    //   204: ifne +36 -> 240
    //   207: aload 4
    //   209: ldc_w 293
    //   212: invokevirtual 406	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   215: ifne +14 -> 229
    //   218: aload 4
    //   220: ldc_w 408
    //   223: invokevirtual 406	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   226: ifeq +166 -> 392
    //   229: aload_0
    //   230: invokespecial 291	com/millennialmedia/android/BridgeMMMedia:isPictureChooserAvailable	()Z
    //   233: istore 13
    //   235: iload 13
    //   237: ifeq +155 -> 392
    //   240: new 410	java/lang/Object
    //   243: dup
    //   244: invokespecial 411	java/lang/Object:<init>	()V
    //   247: putstatic 68	com/millennialmedia/android/BridgeMMMedia:pickerActivityObject	Ljava/lang/Object;
    //   250: getstatic 68	com/millennialmedia/android/BridgeMMMedia:pickerActivityObject	Ljava/lang/Object;
    //   253: astore 18
    //   255: aload 18
    //   257: monitorenter
    //   258: aload_3
    //   259: aload 12
    //   261: aload 4
    //   263: invokestatic 417	com/millennialmedia/android/Utils$IntentUtils:startPickerActivity	(Landroid/content/Context;Ljava/io/File;Ljava/lang/String;)V
    //   266: getstatic 68	com/millennialmedia/android/BridgeMMMedia:pickerActivityObject	Ljava/lang/Object;
    //   269: invokevirtual 420	java/lang/Object:wait	()V
    //   272: aload 18
    //   274: monitorexit
    //   275: aconst_null
    //   276: putstatic 68	com/millennialmedia/android/BridgeMMMedia:pickerActivityObject	Ljava/lang/Object;
    //   279: aload 12
    //   281: ifnull +111 -> 392
    //   284: aload 12
    //   286: invokevirtual 423	java/io/File:exists	()Z
    //   289: ifeq +103 -> 392
    //   292: aload 12
    //   294: invokevirtual 426	java/io/File:length	()J
    //   297: lconst_0
    //   298: lcmp
    //   299: ifle +93 -> 392
    //   302: aload 12
    //   304: iload 11
    //   306: iload 10
    //   308: aload 7
    //   310: invokestatic 428	com/millennialmedia/android/BridgeMMMedia:scaleBitmapToBytes	(Ljava/io/File;IILjava/lang/String;)[B
    //   313: astore 16
    //   315: aload 12
    //   317: invokevirtual 431	java/io/File:delete	()Z
    //   320: pop
    //   321: aload 16
    //   323: ifnull +69 -> 392
    //   326: new 295	com/millennialmedia/android/MMJSResponse
    //   329: dup
    //   330: invokespecial 296	com/millennialmedia/android/MMJSResponse:<init>	()V
    //   333: astore 9
    //   335: aload 9
    //   337: iconst_1
    //   338: putfield 299	com/millennialmedia/android/MMJSResponse:result	I
    //   341: aload 9
    //   343: aload 16
    //   345: putfield 435	com/millennialmedia/android/MMJSResponse:dataResponse	[B
    //   348: goto -255 -> 93
    //   351: astore_2
    //   352: aload_0
    //   353: monitorexit
    //   354: aload_2
    //   355: athrow
    //   356: astore 19
    //   358: aload 18
    //   360: monitorexit
    //   361: aload 19
    //   363: athrow
    //   364: astore 15
    //   366: ldc 11
    //   368: ldc_w 437
    //   371: aload 15
    //   373: invokestatic 253	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   376: aconst_null
    //   377: putstatic 68	com/millennialmedia/android/BridgeMMMedia:pickerActivityObject	Ljava/lang/Object;
    //   380: goto -101 -> 279
    //   383: astore 14
    //   385: aconst_null
    //   386: putstatic 68	com/millennialmedia/android/BridgeMMMedia:pickerActivityObject	Ljava/lang/Object;
    //   389: aload 14
    //   391: athrow
    //   392: aconst_null
    //   393: astore 9
    //   395: goto -302 -> 93
    //   398: aload 5
    //   400: ifnull -319 -> 81
    //   403: aload 6
    //   405: ifnonnull -307 -> 98
    //   408: goto -327 -> 81
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	411	0	this	BridgeMMMedia
    //   0	411	1	paramMap	Map<String, String>
    //   351	4	2	localObject1	Object
    //   12	247	3	localContext	Context
    //   25	237	4	str1	String
    //   39	360	5	str2	String
    //   53	351	6	str3	String
    //   67	242	7	str4	String
    //   87	3	8	localMMJSResponse1	MMJSResponse
    //   91	303	9	localMMJSResponse2	MMJSResponse
    //   104	203	10	i	int
    //   112	193	11	j	int
    //   187	129	12	localFile	File
    //   233	3	13	bool	boolean
    //   383	7	14	localObject2	Object
    //   364	8	15	localException	Exception
    //   313	31	16	arrayOfByte	byte[]
    //   356	6	19	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   2	69	351	finally
    //   81	89	351	finally
    //   98	114	351	finally
    //   125	133	351	finally
    //   145	207	351	finally
    //   207	229	351	finally
    //   229	235	351	finally
    //   275	279	351	finally
    //   284	321	351	finally
    //   326	348	351	finally
    //   376	380	351	finally
    //   385	392	351	finally
    //   258	275	356	finally
    //   358	361	356	finally
    //   240	258	364	java/lang/Exception
    //   361	364	364	java/lang/Exception
    //   240	258	383	finally
    //   361	364	383	finally
    //   366	376	383	finally
  }
  
  public MMJSResponse isSourceTypeAvailable(Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("sourceType");
    if (str != null)
    {
      if ((str.equalsIgnoreCase("Camera")) && (isCameraAvailable())) {
        return MMJSResponse.responseWithSuccess("true");
      }
      if ((str.equalsIgnoreCase("Photo Library")) && (isPictureChooserAvailable())) {
        return MMJSResponse.responseWithSuccess("true");
      }
    }
    return MMJSResponse.responseWithError("false");
  }
  
  public MMJSResponse playAudio(Map<String, String> paramMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str = (String)paramMap.get("path");
    Audio localAudio;
    if ((localContext != null) && (str != null))
    {
      localAudio = Audio.sharedAudio(localContext);
      if (localAudio != null) {
        break label44;
      }
    }
    label44:
    File localFile;
    do
    {
      return null;
      if (localAudio.isPlaying()) {
        return MMJSResponse.responseWithError("Audio already playing.");
      }
      if (str.startsWith("http")) {
        return localAudio.playAudio(Uri.parse(str), Boolean.parseBoolean((String)paramMap.get("repeat")));
      }
      localFile = AdCache.getDownloadFile(localContext, str);
    } while (!localFile.exists());
    return localAudio.playAudio(Uri.fromFile(localFile), Boolean.parseBoolean((String)paramMap.get("repeat")));
  }
  
  public MMJSResponse playSound(Map<String, String> paramMap)
  {
    if (this.contextRef == null) {
      return null;
    }
    Context localContext = (Context)this.contextRef.get();
    String str = (String)paramMap.get("path");
    if ((localContext != null) && (str != null))
    {
      File localFile = AdCache.getDownloadFile(localContext, str);
      if (localFile.exists())
      {
        Audio localAudio = Audio.sharedAudio((Context)this.contextRef.get());
        if (localAudio != null) {
          return localAudio.playSound(localFile);
        }
      }
    }
    return null;
  }
  
  public MMJSResponse playVideo(Map<String, String> paramMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str = (String)paramMap.get("path");
    if ((localContext != null) && (str != null))
    {
      if (str.startsWith("http"))
      {
        Utils.IntentUtils.startVideoPlayerActivityWithData(localContext, str);
        return MMJSResponse.responseWithSuccess(str);
      }
      File localFile = AdCache.getDownloadFile(localContext, str);
      if (localFile.exists())
      {
        Utils.IntentUtils.startVideoPlayerActivityWithData(localContext, localFile);
        return MMJSResponse.responseWithSuccess(localFile.getName());
      }
    }
    return null;
  }
  
  public MMJSResponse stopAudio(Map<String, String> paramMap)
  {
    if (this.contextRef != null)
    {
      Audio localAudio = Audio.sharedAudio((Context)this.contextRef.get());
      if (localAudio != null) {
        return localAudio.stop();
      }
    }
    return null;
  }
  
  /* Error */
  public MMJSResponse writeToPhotoLibrary(Map<String, String> paramMap)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 92	com/millennialmedia/android/BridgeMMMedia:contextRef	Ljava/lang/ref/WeakReference;
    //   6: invokevirtual 97	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   9: checkcast 99	android/content/Context
    //   12: astore_3
    //   13: aload_1
    //   14: ldc 8
    //   16: invokeinterface 351 2 0
    //   21: checkcast 156	java/lang/String
    //   24: astore 4
    //   26: aload_3
    //   27: ifnull +186 -> 213
    //   30: aload 4
    //   32: invokestatic 505	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   35: ifne +178 -> 213
    //   38: aload_1
    //   39: ldc 8
    //   41: invokeinterface 351 2 0
    //   46: checkcast 156	java/lang/String
    //   49: invokestatic 465	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   52: astore 6
    //   54: aload 6
    //   56: invokevirtual 508	android/net/Uri:getLastPathSegment	()Ljava/lang/String;
    //   59: astore 7
    //   61: aload 6
    //   63: invokevirtual 511	android/net/Uri:getScheme	()Ljava/lang/String;
    //   66: astore 8
    //   68: aload 8
    //   70: ifnull +48 -> 118
    //   73: aload 8
    //   75: ldc_w 456
    //   78: invokevirtual 160	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   81: ifeq +37 -> 118
    //   84: aload 6
    //   86: invokevirtual 512	android/net/Uri:toString	()Ljava/lang/String;
    //   89: ldc_w 514
    //   92: aload 7
    //   94: aload_3
    //   95: invokestatic 518	com/millennialmedia/android/AdCache:downloadComponentExternalStorage	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;)Z
    //   98: ifne +20 -> 118
    //   101: ldc_w 520
    //   104: invokestatic 344	com/millennialmedia/android/MMJSResponse:responseWithError	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSResponse;
    //   107: astore 11
    //   109: aload 11
    //   111: astore 5
    //   113: aload_0
    //   114: monitorexit
    //   115: aload 5
    //   117: areturn
    //   118: aload_3
    //   119: ldc_w 514
    //   122: aload 6
    //   124: invokevirtual 508	android/net/Uri:getLastPathSegment	()Ljava/lang/String;
    //   127: invokestatic 523	com/millennialmedia/android/AdCache:getDownloadFile	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/io/File;
    //   130: astore 9
    //   132: aload 9
    //   134: invokevirtual 423	java/io/File:exists	()Z
    //   137: ifne +35 -> 172
    //   140: new 378	java/lang/StringBuilder
    //   143: dup
    //   144: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   147: ldc_w 525
    //   150: invokevirtual 385	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   153: aload 9
    //   155: invokevirtual 528	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   158: invokevirtual 385	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: invokevirtual 399	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   164: invokestatic 344	com/millennialmedia/android/MMJSResponse:responseWithError	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSResponse;
    //   167: astore 5
    //   169: goto -56 -> 113
    //   172: aload_0
    //   173: aload 9
    //   175: invokevirtual 528	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   178: invokespecial 530	com/millennialmedia/android/BridgeMMMedia:scanMedia	(Ljava/lang/String;)V
    //   181: invokestatic 533	com/millennialmedia/android/AdCache:isExternalMounted	()Z
    //   184: ifne +14 -> 198
    //   187: ldc_w 535
    //   190: invokestatic 344	com/millennialmedia/android/MMJSResponse:responseWithError	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSResponse;
    //   193: astore 5
    //   195: goto -82 -> 113
    //   198: ldc_w 537
    //   201: invokestatic 441	com/millennialmedia/android/MMJSResponse:responseWithSuccess	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSResponse;
    //   204: astore 10
    //   206: aload 10
    //   208: astore 5
    //   210: goto -97 -> 113
    //   213: aconst_null
    //   214: astore 5
    //   216: goto -103 -> 113
    //   219: astore_2
    //   220: aload_0
    //   221: monitorexit
    //   222: aload_2
    //   223: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	224	0	this	BridgeMMMedia
    //   0	224	1	paramMap	Map<String, String>
    //   219	4	2	localObject1	Object
    //   12	107	3	localContext	Context
    //   24	7	4	str1	String
    //   111	104	5	localObject2	Object
    //   52	71	6	localUri	Uri
    //   59	34	7	str2	String
    //   66	8	8	str3	String
    //   130	44	9	localFile	File
    //   204	3	10	localMMJSResponse1	MMJSResponse
    //   107	3	11	localMMJSResponse2	MMJSResponse
    // Exception table:
    //   from	to	target	type
    //   2	26	219	finally
    //   30	68	219	finally
    //   73	109	219	finally
    //   118	169	219	finally
    //   172	195	219	finally
    //   198	206	219	finally
  }
  
  static class Audio
    implements MediaPlayer.OnCompletionListener
  {
    private static final int MAX_SOUNDS = 4;
    private static Audio sharedInstance;
    private OnLoadCompleteListener completionListener;
    CopyOnWriteArrayList<MediaPlayer.OnCompletionListener> completionListeners;
    private WeakReference<Context> contextRef;
    private MediaPlayer mediaPlayer;
    CopyOnWriteArrayList<PeriodicListener> periodicListeners;
    Runnable periodicUpdater = new Runnable()
    {
      public void run()
      {
        if (BridgeMMMedia.Audio.this.mediaPlayer != null)
        {
          if (BridgeMMMedia.Audio.this.mediaPlayer.isPlaying())
          {
            int i = BridgeMMMedia.Audio.this.mediaPlayer.getCurrentPosition();
            if (BridgeMMMedia.Audio.this.periodicListeners != null)
            {
              Iterator localIterator = BridgeMMMedia.Audio.this.periodicListeners.iterator();
              while (localIterator.hasNext()) {
                ((BridgeMMMedia.Audio.PeriodicListener)localIterator.next()).onUpdate(i);
              }
            }
          }
          MMSDK.runOnUiThreadDelayed(this, 500L);
        }
      }
    };
    private SoundPool soundPool;
    
    private Audio() {}
    
    private Audio(Context paramContext)
    {
      this.contextRef = new WeakReference(paramContext.getApplicationContext());
    }
    
    static Audio sharedAudio(Context paramContext)
    {
      try
      {
        if (sharedInstance == null) {
          sharedInstance = new Audio(paramContext);
        }
        Audio localAudio = sharedInstance;
        return localAudio;
      }
      finally {}
    }
    
    boolean addCompletionListener(MediaPlayer.OnCompletionListener paramOnCompletionListener)
    {
      if (this.completionListeners == null) {
        this.completionListeners = new CopyOnWriteArrayList();
      }
      if (!this.completionListeners.contains(paramOnCompletionListener)) {
        return this.completionListeners.add(paramOnCompletionListener);
      }
      return false;
    }
    
    boolean addPeriodicListener(PeriodicListener paramPeriodicListener)
    {
      if (this.periodicListeners == null) {
        this.periodicListeners = new CopyOnWriteArrayList();
      }
      if (!this.periodicListeners.contains(paramPeriodicListener)) {
        return this.periodicListeners.add(paramPeriodicListener);
      }
      return false;
    }
    
    /* Error */
    boolean isPlaying()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 56	com/millennialmedia/android/BridgeMMMedia$Audio:mediaPlayer	Landroid/media/MediaPlayer;
      //   6: ifnull +21 -> 27
      //   9: aload_0
      //   10: getfield 56	com/millennialmedia/android/BridgeMMMedia$Audio:mediaPlayer	Landroid/media/MediaPlayer;
      //   13: invokevirtual 88	android/media/MediaPlayer:isPlaying	()Z
      //   16: istore_3
      //   17: iload_3
      //   18: ifeq +9 -> 27
      //   21: iconst_1
      //   22: istore_2
      //   23: aload_0
      //   24: monitorexit
      //   25: iload_2
      //   26: ireturn
      //   27: iconst_0
      //   28: istore_2
      //   29: goto -6 -> 23
      //   32: astore_1
      //   33: aload_0
      //   34: monitorexit
      //   35: aload_1
      //   36: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	37	0	this	Audio
      //   32	4	1	localObject	Object
      //   22	7	2	bool1	boolean
      //   16	2	3	bool2	boolean
      // Exception table:
      //   from	to	target	type
      //   2	17	32	finally
    }
    
    public void onCompletion(MediaPlayer paramMediaPlayer)
    {
      try
      {
        if (this.completionListeners != null)
        {
          Iterator localIterator = this.completionListeners.iterator();
          while (localIterator.hasNext()) {
            ((MediaPlayer.OnCompletionListener)localIterator.next()).onCompletion(this.mediaPlayer);
          }
        }
        if (this.mediaPlayer == null) {
          break label71;
        }
      }
      finally {}
      this.mediaPlayer.release();
      this.mediaPlayer = null;
      label71:
    }
    
    MMJSResponse playAudio(Uri paramUri, boolean paramBoolean)
    {
      try
      {
        if (this.mediaPlayer != null)
        {
          this.mediaPlayer.release();
          this.mediaPlayer = null;
        }
        this.mediaPlayer = MediaPlayer.create((Context)this.contextRef.get(), paramUri);
        this.mediaPlayer.setLooping(paramBoolean);
        this.mediaPlayer.start();
        this.mediaPlayer.setOnCompletionListener(this);
        MMSDK.runOnUiThread(this.periodicUpdater);
      }
      catch (Exception localException)
      {
        for (;;)
        {
          MMJSResponse localMMJSResponse;
          MMLog.e("BridgeMMMedia", "Exception in playAudio method", localException.getCause());
        }
      }
      finally {}
      localMMJSResponse = MMJSResponse.responseWithSuccess("Audio playback started");
      return localMMJSResponse;
    }
    
    /* Error */
    MMJSResponse playSound(File paramFile)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 162	com/millennialmedia/android/BridgeMMMedia$Audio:soundPool	Landroid/media/SoundPool;
      //   6: ifnonnull +33 -> 39
      //   9: aload_0
      //   10: new 164	android/media/SoundPool
      //   13: dup
      //   14: iconst_4
      //   15: iconst_3
      //   16: iconst_0
      //   17: invokespecial 167	android/media/SoundPool:<init>	(III)V
      //   20: putfield 162	com/millennialmedia/android/BridgeMMMedia$Audio:soundPool	Landroid/media/SoundPool;
      //   23: aload_0
      //   24: new 169	com/millennialmedia/android/BridgeMMMedia$Audio$2
      //   27: dup
      //   28: aload_0
      //   29: aload_0
      //   30: getfield 162	com/millennialmedia/android/BridgeMMMedia$Audio:soundPool	Landroid/media/SoundPool;
      //   33: invokespecial 172	com/millennialmedia/android/BridgeMMMedia$Audio$2:<init>	(Lcom/millennialmedia/android/BridgeMMMedia$Audio;Landroid/media/SoundPool;)V
      //   36: putfield 174	com/millennialmedia/android/BridgeMMMedia$Audio:completionListener	Lcom/millennialmedia/android/BridgeMMMedia$Audio$OnLoadCompleteListener;
      //   39: aload_0
      //   40: getfield 174	com/millennialmedia/android/BridgeMMMedia$Audio:completionListener	Lcom/millennialmedia/android/BridgeMMMedia$Audio$OnLoadCompleteListener;
      //   43: aload_0
      //   44: getfield 162	com/millennialmedia/android/BridgeMMMedia$Audio:soundPool	Landroid/media/SoundPool;
      //   47: aload_1
      //   48: invokevirtual 180	java/io/File:getAbsolutePath	()Ljava/lang/String;
      //   51: iconst_1
      //   52: invokevirtual 184	android/media/SoundPool:load	(Ljava/lang/String;I)I
      //   55: invokevirtual 190	com/millennialmedia/android/BridgeMMMedia$Audio$OnLoadCompleteListener:testSample	(I)V
      //   58: ldc 192
      //   60: invokestatic 144	com/millennialmedia/android/MMJSResponse:responseWithSuccess	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSResponse;
      //   63: astore 4
      //   65: aload_0
      //   66: monitorexit
      //   67: aload 4
      //   69: areturn
      //   70: astore_3
      //   71: aload_0
      //   72: monitorexit
      //   73: aload_3
      //   74: athrow
      //   75: astore_2
      //   76: goto -18 -> 58
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	79	0	this	Audio
      //   0	79	1	paramFile	File
      //   75	1	2	localException	Exception
      //   70	4	3	localObject	Object
      //   63	5	4	localMMJSResponse	MMJSResponse
      // Exception table:
      //   from	to	target	type
      //   2	39	70	finally
      //   39	58	70	finally
      //   58	65	70	finally
      //   2	39	75	java/lang/Exception
      //   39	58	75	java/lang/Exception
    }
    
    boolean removeCompletionListener(MediaPlayer.OnCompletionListener paramOnCompletionListener)
    {
      if (this.completionListeners != null) {
        return this.completionListeners.remove(paramOnCompletionListener);
      }
      return false;
    }
    
    boolean removePeriodicListener(PeriodicListener paramPeriodicListener)
    {
      if (this.periodicListeners != null) {
        return this.periodicListeners.remove(paramPeriodicListener);
      }
      return false;
    }
    
    MMJSResponse stop()
    {
      try
      {
        if (this.mediaPlayer != null) {
          onCompletion(this.mediaPlayer);
        }
        if (this.soundPool != null)
        {
          this.soundPool.release();
          this.soundPool = null;
        }
        if (this.completionListener != null)
        {
          this.completionListener.release();
          this.completionListener = null;
        }
        MMJSResponse localMMJSResponse = MMJSResponse.responseWithSuccess("Audio stopped");
        return localMMJSResponse;
      }
      finally {}
    }
    
    private abstract class OnLoadCompleteListener
    {
      private static final int TEST_PERIOD_MS = 100;
      private ArrayList<Integer> sampleIds = new ArrayList();
      private SoundPool soundPool;
      private Timer timer;
      
      public OnLoadCompleteListener(SoundPool paramSoundPool)
      {
        this.soundPool = paramSoundPool;
      }
      
      abstract void onLoadComplete(SoundPool paramSoundPool, int paramInt1, int paramInt2);
      
      void release()
      {
        try
        {
          if (this.timer != null)
          {
            this.timer.cancel();
            this.timer.purge();
          }
          return;
        }
        finally
        {
          localObject = finally;
          throw localObject;
        }
      }
      
      void testSample(int paramInt)
      {
        try
        {
          this.sampleIds.add(Integer.valueOf(paramInt));
          if (this.sampleIds.size() == 1)
          {
            this.timer = new Timer();
            this.timer.scheduleAtFixedRate(new TimerTask()
            {
              public void run()
              {
                ArrayList localArrayList = new ArrayList();
                Iterator localIterator = BridgeMMMedia.Audio.OnLoadCompleteListener.this.sampleIds.iterator();
                while (localIterator.hasNext())
                {
                  Integer localInteger = (Integer)localIterator.next();
                  int i = BridgeMMMedia.Audio.OnLoadCompleteListener.this.soundPool.play(localInteger.intValue(), 0.0F, 0.0F, 0, 0, 1.0F);
                  if (i != 0)
                  {
                    BridgeMMMedia.Audio.OnLoadCompleteListener.this.soundPool.stop(i);
                    BridgeMMMedia.Audio.OnLoadCompleteListener.this.onLoadComplete(BridgeMMMedia.Audio.OnLoadCompleteListener.this.soundPool, localInteger.intValue(), 0);
                    localArrayList.add(localInteger);
                  }
                }
                BridgeMMMedia.Audio.OnLoadCompleteListener.this.sampleIds.removeAll(localArrayList);
                if (BridgeMMMedia.Audio.OnLoadCompleteListener.this.sampleIds.size() == 0)
                {
                  BridgeMMMedia.Audio.OnLoadCompleteListener.this.timer.cancel();
                  BridgeMMMedia.Audio.OnLoadCompleteListener.this.timer.purge();
                }
              }
            }, 0L, 100L);
          }
          return;
        }
        finally
        {
          localObject = finally;
          throw localObject;
        }
      }
    }
    
    static abstract interface PeriodicListener
    {
      public abstract void onUpdate(int paramInt);
    }
  }
  
  static class PickerActivity
    extends MMBaseActivity
  {
    private Uri fileUri;
    boolean hasRequestedPic = false;
    
    /* Error */
    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
      // Byte code:
      //   0: aload_0
      //   1: iload_1
      //   2: iload_2
      //   3: aload_3
      //   4: invokespecial 20	com/millennialmedia/android/MMBaseActivity:onActivityResult	(IILandroid/content/Intent;)V
      //   7: aload_3
      //   8: ifnull +173 -> 181
      //   11: aconst_null
      //   12: astore 6
      //   14: aconst_null
      //   15: astore 7
      //   17: aload_3
      //   18: invokevirtual 26	android/content/Intent:getData	()Landroid/net/Uri;
      //   21: astore 9
      //   23: aload 9
      //   25: ifnonnull +278 -> 303
      //   28: aload_3
      //   29: invokevirtual 30	android/content/Intent:getExtras	()Landroid/os/Bundle;
      //   32: ifnull +149 -> 181
      //   35: aload_3
      //   36: invokevirtual 30	android/content/Intent:getExtras	()Landroid/os/Bundle;
      //   39: ldc 32
      //   41: invokevirtual 38	android/os/Bundle:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   44: checkcast 40	android/graphics/Bitmap
      //   47: astore 10
      //   49: new 42	java/io/File
      //   52: dup
      //   53: aload_0
      //   54: invokevirtual 46	com/millennialmedia/android/BridgeMMMedia$PickerActivity:getIntent	()Landroid/content/Intent;
      //   57: invokevirtual 26	android/content/Intent:getData	()Landroid/net/Uri;
      //   60: invokevirtual 52	android/net/Uri:getPath	()Ljava/lang/String;
      //   63: invokespecial 55	java/io/File:<init>	(Ljava/lang/String;)V
      //   66: astore 11
      //   68: new 57	java/io/ByteArrayOutputStream
      //   71: dup
      //   72: invokespecial 58	java/io/ByteArrayOutputStream:<init>	()V
      //   75: astore 12
      //   77: aload 10
      //   79: getstatic 64	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
      //   82: iconst_0
      //   83: aload 12
      //   85: invokevirtual 68	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
      //   88: pop
      //   89: new 70	java/io/ByteArrayInputStream
      //   92: dup
      //   93: aload 12
      //   95: invokevirtual 74	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   98: invokespecial 77	java/io/ByteArrayInputStream:<init>	([B)V
      //   101: astore 18
      //   103: new 79	java/io/FileOutputStream
      //   106: dup
      //   107: aload 11
      //   109: invokespecial 82	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   112: astore 19
      //   114: sipush 1024
      //   117: newarray byte
      //   119: astore 20
      //   121: aload 18
      //   123: aload 20
      //   125: invokevirtual 86	java/io/ByteArrayInputStream:read	([B)I
      //   128: istore 21
      //   130: iload 21
      //   132: ifle +71 -> 203
      //   135: aload 19
      //   137: aload 20
      //   139: iconst_0
      //   140: iload 21
      //   142: invokevirtual 92	java/io/OutputStream:write	([BII)V
      //   145: goto -24 -> 121
      //   148: astore 13
      //   150: aload 19
      //   152: astore 7
      //   154: ldc 94
      //   156: ldc 96
      //   158: aload 13
      //   160: invokestatic 102	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   163: iconst_0
      //   164: ifeq +7 -> 171
      //   167: aconst_null
      //   168: invokevirtual 107	java/io/InputStream:close	()V
      //   171: aload 7
      //   173: ifnull +8 -> 181
      //   176: aload 7
      //   178: invokevirtual 108	java/io/OutputStream:close	()V
      //   181: invokestatic 114	com/millennialmedia/android/BridgeMMMedia:access$000	()Ljava/lang/Object;
      //   184: astore 4
      //   186: aload 4
      //   188: monitorenter
      //   189: invokestatic 114	com/millennialmedia/android/BridgeMMMedia:access$000	()Ljava/lang/Object;
      //   192: invokevirtual 119	java/lang/Object:notify	()V
      //   195: aload 4
      //   197: monitorexit
      //   198: aload_0
      //   199: invokevirtual 122	com/millennialmedia/android/BridgeMMMedia$PickerActivity:finish	()V
      //   202: return
      //   203: iconst_0
      //   204: ifeq +7 -> 211
      //   207: aconst_null
      //   208: invokevirtual 107	java/io/InputStream:close	()V
      //   211: aload 19
      //   213: ifnull +8 -> 221
      //   216: aload 19
      //   218: invokevirtual 108	java/io/OutputStream:close	()V
      //   221: goto -40 -> 181
      //   224: astore 22
      //   226: ldc 94
      //   228: ldc 124
      //   230: aload 22
      //   232: invokestatic 102	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   235: goto -54 -> 181
      //   238: astore 16
      //   240: ldc 94
      //   242: ldc 124
      //   244: aload 16
      //   246: invokestatic 102	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   249: goto -68 -> 181
      //   252: astore 8
      //   254: ldc 94
      //   256: ldc 126
      //   258: aload 8
      //   260: invokestatic 102	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   263: goto -82 -> 181
      //   266: astore 14
      //   268: iconst_0
      //   269: ifeq +7 -> 276
      //   272: aconst_null
      //   273: invokevirtual 107	java/io/InputStream:close	()V
      //   276: aload 7
      //   278: ifnull +8 -> 286
      //   281: aload 7
      //   283: invokevirtual 108	java/io/OutputStream:close	()V
      //   286: aload 14
      //   288: athrow
      //   289: astore 15
      //   291: ldc 94
      //   293: ldc 124
      //   295: aload 15
      //   297: invokestatic 102	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   300: goto -14 -> 286
      //   303: aload 9
      //   305: ifnull -124 -> 181
      //   308: iconst_1
      //   309: anewarray 128	java/lang/String
      //   312: dup
      //   313: iconst_0
      //   314: ldc 130
      //   316: aastore
      //   317: astore 23
      //   319: aload_0
      //   320: invokevirtual 134	com/millennialmedia/android/BridgeMMMedia$PickerActivity:getContentResolver	()Landroid/content/ContentResolver;
      //   323: astore 24
      //   325: aload 24
      //   327: ifnull -146 -> 181
      //   330: aload 24
      //   332: aload 9
      //   334: aload 23
      //   336: aconst_null
      //   337: aconst_null
      //   338: aconst_null
      //   339: invokevirtual 140	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   342: astore 25
      //   344: aload 25
      //   346: ifnull -165 -> 181
      //   349: aload 25
      //   351: ldc 130
      //   353: invokeinterface 146 2 0
      //   358: istore 26
      //   360: iload 26
      //   362: iconst_m1
      //   363: if_icmpeq -182 -> 181
      //   366: aload 25
      //   368: invokeinterface 150 1 0
      //   373: pop
      //   374: new 42	java/io/File
      //   377: dup
      //   378: aload 25
      //   380: iload 26
      //   382: invokeinterface 154 2 0
      //   387: invokespecial 55	java/io/File:<init>	(Ljava/lang/String;)V
      //   390: astore 28
      //   392: aload 25
      //   394: invokeinterface 155 1 0
      //   399: new 42	java/io/File
      //   402: dup
      //   403: aload_0
      //   404: invokevirtual 46	com/millennialmedia/android/BridgeMMMedia$PickerActivity:getIntent	()Landroid/content/Intent;
      //   407: invokevirtual 26	android/content/Intent:getData	()Landroid/net/Uri;
      //   410: invokevirtual 52	android/net/Uri:getPath	()Ljava/lang/String;
      //   413: invokespecial 55	java/io/File:<init>	(Ljava/lang/String;)V
      //   416: astore 29
      //   418: new 157	java/io/FileInputStream
      //   421: dup
      //   422: aload 28
      //   424: invokespecial 158	java/io/FileInputStream:<init>	(Ljava/io/File;)V
      //   427: astore 30
      //   429: new 79	java/io/FileOutputStream
      //   432: dup
      //   433: aload 29
      //   435: invokespecial 82	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   438: astore 31
      //   440: sipush 1024
      //   443: newarray byte
      //   445: astore 36
      //   447: aload 30
      //   449: aload 36
      //   451: invokevirtual 159	java/io/InputStream:read	([B)I
      //   454: istore 37
      //   456: iload 37
      //   458: ifle +72 -> 530
      //   461: aload 31
      //   463: aload 36
      //   465: iconst_0
      //   466: iload 37
      //   468: invokevirtual 92	java/io/OutputStream:write	([BII)V
      //   471: goto -24 -> 447
      //   474: astore 34
      //   476: aload 31
      //   478: astore 7
      //   480: aload 30
      //   482: astore 6
      //   484: ldc 94
      //   486: ldc 161
      //   488: aload 34
      //   490: invokestatic 102	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   493: aload 6
      //   495: ifnull +8 -> 503
      //   498: aload 6
      //   500: invokevirtual 107	java/io/InputStream:close	()V
      //   503: aload 7
      //   505: ifnull -324 -> 181
      //   508: aload 7
      //   510: invokevirtual 108	java/io/OutputStream:close	()V
      //   513: goto -332 -> 181
      //   516: astore 35
      //   518: ldc 94
      //   520: ldc 124
      //   522: aload 35
      //   524: invokestatic 102	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   527: goto -346 -> 181
      //   530: aload 30
      //   532: ifnull +8 -> 540
      //   535: aload 30
      //   537: invokevirtual 107	java/io/InputStream:close	()V
      //   540: aload 31
      //   542: ifnull +8 -> 550
      //   545: aload 31
      //   547: invokevirtual 108	java/io/OutputStream:close	()V
      //   550: goto -369 -> 181
      //   553: astore 38
      //   555: ldc 94
      //   557: ldc 124
      //   559: aload 38
      //   561: invokestatic 102	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   564: goto -383 -> 181
      //   567: astore 32
      //   569: aload 6
      //   571: ifnull +8 -> 579
      //   574: aload 6
      //   576: invokevirtual 107	java/io/InputStream:close	()V
      //   579: aload 7
      //   581: ifnull +8 -> 589
      //   584: aload 7
      //   586: invokevirtual 108	java/io/OutputStream:close	()V
      //   589: aload 32
      //   591: athrow
      //   592: astore 33
      //   594: ldc 94
      //   596: ldc 124
      //   598: aload 33
      //   600: invokestatic 102	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   603: goto -14 -> 589
      //   606: astore 5
      //   608: aload 4
      //   610: monitorexit
      //   611: aload 5
      //   613: athrow
      //   614: astore 8
      //   616: goto -362 -> 254
      //   619: astore 8
      //   621: goto -367 -> 254
      //   624: astore 32
      //   626: aload 30
      //   628: astore 6
      //   630: aconst_null
      //   631: astore 7
      //   633: goto -64 -> 569
      //   636: astore 32
      //   638: aload 31
      //   640: astore 7
      //   642: aload 30
      //   644: astore 6
      //   646: goto -77 -> 569
      //   649: astore 34
      //   651: aconst_null
      //   652: astore 6
      //   654: aconst_null
      //   655: astore 7
      //   657: goto -173 -> 484
      //   660: astore 34
      //   662: aload 30
      //   664: astore 6
      //   666: aconst_null
      //   667: astore 7
      //   669: goto -185 -> 484
      //   672: astore 14
      //   674: aload 19
      //   676: astore 7
      //   678: goto -410 -> 268
      //   681: astore 13
      //   683: aconst_null
      //   684: astore 7
      //   686: goto -532 -> 154
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	689	0	this	PickerActivity
      //   0	689	1	paramInt1	int
      //   0	689	2	paramInt2	int
      //   0	689	3	paramIntent	Intent
      //   184	425	4	localObject1	Object
      //   606	6	5	localObject2	Object
      //   12	653	6	localObject3	Object
      //   15	670	7	localObject4	Object
      //   252	7	8	localException1	Exception
      //   614	1	8	localException2	Exception
      //   619	1	8	localException3	Exception
      //   21	312	9	localUri	Uri
      //   47	31	10	localBitmap	Bitmap
      //   66	42	11	localFile1	File
      //   75	19	12	localByteArrayOutputStream	java.io.ByteArrayOutputStream
      //   148	11	13	localException4	Exception
      //   681	1	13	localException5	Exception
      //   266	21	14	localObject5	Object
      //   672	1	14	localObject6	Object
      //   289	7	15	localException6	Exception
      //   238	7	16	localException7	Exception
      //   101	21	18	localByteArrayInputStream	java.io.ByteArrayInputStream
      //   112	563	19	localFileOutputStream1	java.io.FileOutputStream
      //   119	19	20	arrayOfByte1	byte[]
      //   128	13	21	i	int
      //   224	7	22	localException8	Exception
      //   317	18	23	arrayOfString	String[]
      //   323	8	24	localContentResolver	android.content.ContentResolver
      //   342	51	25	localCursor	android.database.Cursor
      //   358	23	26	j	int
      //   390	33	28	localFile2	File
      //   416	18	29	localFile3	File
      //   427	236	30	localFileInputStream	java.io.FileInputStream
      //   438	201	31	localFileOutputStream2	java.io.FileOutputStream
      //   567	23	32	localObject7	Object
      //   624	1	32	localObject8	Object
      //   636	1	32	localObject9	Object
      //   592	7	33	localException9	Exception
      //   474	15	34	localException10	Exception
      //   649	1	34	localException11	Exception
      //   660	1	34	localException12	Exception
      //   516	7	35	localException13	Exception
      //   445	19	36	arrayOfByte2	byte[]
      //   454	13	37	k	int
      //   553	7	38	localException14	Exception
      // Exception table:
      //   from	to	target	type
      //   114	121	148	java/lang/Exception
      //   121	130	148	java/lang/Exception
      //   135	145	148	java/lang/Exception
      //   207	211	224	java/lang/Exception
      //   216	221	224	java/lang/Exception
      //   167	171	238	java/lang/Exception
      //   176	181	238	java/lang/Exception
      //   17	23	252	java/lang/Exception
      //   28	49	252	java/lang/Exception
      //   240	249	252	java/lang/Exception
      //   286	289	252	java/lang/Exception
      //   291	300	252	java/lang/Exception
      //   308	325	252	java/lang/Exception
      //   330	344	252	java/lang/Exception
      //   349	360	252	java/lang/Exception
      //   366	399	252	java/lang/Exception
      //   518	527	252	java/lang/Exception
      //   589	592	252	java/lang/Exception
      //   594	603	252	java/lang/Exception
      //   49	114	266	finally
      //   154	163	266	finally
      //   272	276	289	java/lang/Exception
      //   281	286	289	java/lang/Exception
      //   440	447	474	java/lang/Exception
      //   447	456	474	java/lang/Exception
      //   461	471	474	java/lang/Exception
      //   498	503	516	java/lang/Exception
      //   508	513	516	java/lang/Exception
      //   535	540	553	java/lang/Exception
      //   545	550	553	java/lang/Exception
      //   399	429	567	finally
      //   484	493	567	finally
      //   574	579	592	java/lang/Exception
      //   584	589	592	java/lang/Exception
      //   189	198	606	finally
      //   608	611	606	finally
      //   226	235	614	java/lang/Exception
      //   555	564	619	java/lang/Exception
      //   429	440	624	finally
      //   440	447	636	finally
      //   447	456	636	finally
      //   461	471	636	finally
      //   399	429	649	java/lang/Exception
      //   429	440	660	java/lang/Exception
      //   114	121	672	finally
      //   121	130	672	finally
      //   135	145	672	finally
      //   49	114	681	java/lang/Exception
    }
    
    public void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      if (getLastNonConfigurationInstance() != null) {
        this.hasRequestedPic = ((Bundle)getLastNonConfigurationInstance()).getBoolean("hasRequestedPic");
      }
      if (!this.hasRequestedPic)
      {
        if (getIntent().getStringExtra("type").equalsIgnoreCase("Camera"))
        {
          Intent localIntent1 = new Intent("android.media.action.IMAGE_CAPTURE");
          this.fileUri = getIntent().getData();
          localIntent1.putExtra("return-data", true);
          this.hasRequestedPic = true;
          startActivityForResult(localIntent1, 0);
        }
      }
      else {
        return;
      }
      Intent localIntent2 = new Intent();
      localIntent2.setType("image/*");
      localIntent2.setAction("android.intent.action.GET_CONTENT");
      this.hasRequestedPic = true;
      startActivityForResult(localIntent2, 0);
    }
    
    public Object onRetainNonConfigurationInstance()
    {
      super.onRetainNonConfigurationInstance();
      Bundle localBundle = new Bundle();
      localBundle.putBoolean("hasRequestedPic", this.hasRequestedPic);
      return localBundle;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.BridgeMMMedia
 * JD-Core Version:    0.7.0.1
 */