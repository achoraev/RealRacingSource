package com.millennialmedia.android;

import android.text.TextUtils;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

class HttpGetRequest
{
  private static final String TAG = "HttpGetRequest";
  private HttpClient client;
  private HttpGet getRequest;
  
  HttpGetRequest()
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 10000);
    this.client = new DefaultHttpClient(localBasicHttpParams);
    this.getRequest = new HttpGet();
  }
  
  HttpGetRequest(int paramInt)
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 10000);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, paramInt);
    this.client = new DefaultHttpClient(localBasicHttpParams);
    this.getRequest = new HttpGet();
  }
  
  /* Error */
  static String convertStreamToString(java.io.InputStream paramInputStream)
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: ifnonnull +13 -> 16
    //   6: new 45	java/io/IOException
    //   9: dup
    //   10: ldc 49
    //   12: invokespecial 52	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   15: athrow
    //   16: new 54	java/io/BufferedReader
    //   19: dup
    //   20: new 56	java/io/InputStreamReader
    //   23: dup
    //   24: aload_0
    //   25: invokespecial 59	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   28: sipush 4096
    //   31: invokespecial 62	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   34: astore_2
    //   35: new 64	java/lang/StringBuilder
    //   38: dup
    //   39: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   42: astore_3
    //   43: aload_2
    //   44: invokevirtual 69	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   47: astore 7
    //   49: aload 7
    //   51: ifnull +67 -> 118
    //   54: aload_3
    //   55: new 64	java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   62: aload 7
    //   64: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: ldc 75
    //   69: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: invokevirtual 78	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   75: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: pop
    //   79: goto -36 -> 43
    //   82: astore 6
    //   84: aload_2
    //   85: astore_1
    //   86: ldc 8
    //   88: ldc 80
    //   90: aload 6
    //   92: invokestatic 86	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   95: new 45	java/io/IOException
    //   98: dup
    //   99: ldc 88
    //   101: invokespecial 52	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   104: athrow
    //   105: astore 4
    //   107: aload_1
    //   108: ifnull +7 -> 115
    //   111: aload_1
    //   112: invokevirtual 91	java/io/BufferedReader:close	()V
    //   115: aload 4
    //   117: athrow
    //   118: aload_2
    //   119: ifnull +7 -> 126
    //   122: aload_2
    //   123: invokevirtual 91	java/io/BufferedReader:close	()V
    //   126: aload_3
    //   127: invokevirtual 78	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   130: areturn
    //   131: astore 9
    //   133: ldc 8
    //   135: ldc 93
    //   137: aload 9
    //   139: invokestatic 86	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   142: goto -16 -> 126
    //   145: astore 5
    //   147: ldc 8
    //   149: ldc 93
    //   151: aload 5
    //   153: invokestatic 86	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   156: goto -41 -> 115
    //   159: astore 4
    //   161: aload_2
    //   162: astore_1
    //   163: goto -56 -> 107
    //   166: astore 6
    //   168: aconst_null
    //   169: astore_1
    //   170: goto -84 -> 86
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	173	0	paramInputStream	java.io.InputStream
    //   1	169	1	localObject1	Object
    //   34	128	2	localBufferedReader	java.io.BufferedReader
    //   42	85	3	localStringBuilder	StringBuilder
    //   105	11	4	localObject2	Object
    //   159	1	4	localObject3	Object
    //   145	7	5	localIOException1	IOException
    //   82	9	6	localOutOfMemoryError1	OutOfMemoryError
    //   166	1	6	localOutOfMemoryError2	OutOfMemoryError
    //   47	16	7	str	String
    //   131	7	9	localIOException2	IOException
    // Exception table:
    //   from	to	target	type
    //   35	43	82	java/lang/OutOfMemoryError
    //   43	49	82	java/lang/OutOfMemoryError
    //   54	79	82	java/lang/OutOfMemoryError
    //   16	35	105	finally
    //   86	105	105	finally
    //   122	126	131	java/io/IOException
    //   111	115	145	java/io/IOException
    //   35	43	159	finally
    //   43	49	159	finally
    //   54	79	159	finally
    //   16	35	166	java/lang/OutOfMemoryError
  }
  
  static void log(String[] paramArrayOfString)
  {
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0)) {
      Utils.ThreadUtils.execute(new Runnable()
      {
        public void run()
        {
          String[] arrayOfString = this.val$urls;
          int i = arrayOfString.length;
          int j = 0;
          for (;;)
          {
            if (j < i)
            {
              String str = arrayOfString[j];
              MMLog.v("HttpGetRequest", String.format("Logging event to: %s", new Object[] { str }));
              try
              {
                new HttpGetRequest().get(str);
                j++;
              }
              catch (Exception localException)
              {
                for (;;)
                {
                  MMLog.e("HttpGetRequest", "Logging request failed.", localException);
                }
              }
            }
          }
        }
      });
    }
  }
  
  HttpResponse get(String paramString)
    throws Exception
  {
    boolean bool = TextUtils.isEmpty(paramString);
    Object localObject1 = null;
    if (!bool) {}
    try
    {
      this.getRequest.setURI(new URI(paramString));
      HttpResponse localHttpResponse = this.client.execute(this.getRequest);
      localObject1 = localHttpResponse;
      localObject2 = localObject1;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      MMLog.e("HttpGetRequest", "Out of memory!", localOutOfMemoryError);
      return null;
    }
    catch (Exception localException)
    {
      do
      {
        Object localObject2 = null;
      } while (localException == null);
      MMLog.e("HttpGetRequest", "Error connecting:", localException);
    }
    return localObject2;
    return null;
  }
  
  void trackConversion(String paramString, boolean paramBoolean, long paramLong, TreeMap<String, String> paramTreeMap)
    throws Exception
  {
    int i;
    if (paramBoolean) {
      i = 1;
    }
    StringBuilder localStringBuilder;
    for (;;)
    {
      try
      {
        localStringBuilder = new StringBuilder("http://cvt.mydas.mobi/handleConversion?firstlaunch=" + i);
        if (paramString != null) {
          localStringBuilder.append("&goalId=" + paramString);
        }
        if (paramLong > 0L) {
          localStringBuilder.append("&installtime=" + paramLong / 1000L);
        }
        if (paramTreeMap == null) {
          break;
        }
        Iterator localIterator = paramTreeMap.entrySet().iterator();
        if (!localIterator.hasNext()) {
          break;
        }
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        Object[] arrayOfObject3 = new Object[2];
        arrayOfObject3[0] = localEntry.getKey();
        arrayOfObject3[1] = URLEncoder.encode((String)localEntry.getValue(), "UTF-8");
        localStringBuilder.append(String.format("&%s=%s", arrayOfObject3));
        continue;
        i = 0;
      }
      catch (IOException localIOException)
      {
        MMLog.e("HttpGetRequest", "Conversion tracking error: ", localIOException);
        return;
      }
    }
    String str = localStringBuilder.toString();
    MMLog.d("HttpGetRequest", String.format("Sending conversion tracker report: %s", new Object[] { str }));
    this.getRequest.setURI(new URI(str));
    HttpResponse localHttpResponse = this.client.execute(this.getRequest);
    if (localHttpResponse.getStatusLine().getStatusCode() == 200)
    {
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(localHttpResponse.getStatusLine().getStatusCode());
      MMLog.v("HttpGetRequest", String.format("Successful conversion tracking event: %d", arrayOfObject2));
      return;
    }
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = Integer.valueOf(localHttpResponse.getStatusLine().getStatusCode());
    MMLog.e("HttpGetRequest", String.format("Conversion tracking error: %d", arrayOfObject1));
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.HttpGetRequest
 * JD-Core Version:    0.7.0.1
 */