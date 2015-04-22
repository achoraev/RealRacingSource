package com.ea.nimble;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

class NetworkConnection
  implements NetworkConnectionHandle, Runnable, LogSource
{
  private static int MAXIMUM_RAW_DATA_LENGTH = 1048576;
  static int s_loggingIdCount = 100;
  private NetworkConnectionCallback m_completionCallback;
  private NetworkConnectionCallback m_headerCallback;
  private String m_loggingId;
  private NetworkImpl m_manager;
  private NetworkConnectionCallback m_progressCallback;
  private HttpRequest m_request;
  private String m_requestDataForLog;
  private HttpResponse m_response;
  private StringBuilder m_responseDataForLog;
  private Thread m_thread;
  
  public NetworkConnection(NetworkImpl paramNetworkImpl, HttpRequest paramHttpRequest)
  {
    this.m_manager = paramNetworkImpl;
    this.m_thread = null;
    this.m_request = paramHttpRequest;
    this.m_response = new HttpResponse();
    this.m_headerCallback = null;
    this.m_progressCallback = null;
    this.m_completionCallback = null;
    this.m_loggingId = String.valueOf(s_loggingIdCount);
    int i = s_loggingIdCount;
    s_loggingIdCount = i + 1;
    if (i >= 1000) {
      s_loggingIdCount = 100;
    }
  }
  
  private String beautifyJSONString(String paramString)
  {
    if ((paramString == null) || (paramString.length() <= 0)) {
      return paramString;
    }
    String str = System.getProperty("line.separator");
    StringBuilder localStringBuilder = new StringBuilder(2048 + paramString.length());
    Stack localStack = new Stack();
    int i = 0;
    int j = 0;
    int k = 1;
    int m = 0;
    if (m < paramString.length())
    {
      char c = paramString.charAt(m);
      switch (c)
      {
      default: 
        localStringBuilder.append(c);
        j = 0;
        k = 0;
      }
      for (;;)
      {
        m++;
        break;
        if (j == 0)
        {
          localStringBuilder.append(c);
          continue;
          if (k == 0) {
            localStringBuilder.append(str).append(multiplyStringNTimes("\t", i));
          }
          i++;
          localStack.push(Character.valueOf(c));
          localStringBuilder.append(c).append(str).append(multiplyStringNTimes("\t", i));
          j = 1;
          k = j;
          continue;
          localStringBuilder.append(c).append(str).append(multiplyStringNTimes("\t", i));
          j = 1;
          k = j;
          continue;
          i--;
          int n = ((Character)localStack.pop()).charValue();
          if (((c == '}') && (n != 123)) || ((c == ']') && (n != 91)))
          {
            Log.Helper.LOGE(this, "JSONString expect valid closing brackets but found none", new Object[0]);
            return paramString;
          }
          localStringBuilder.append(str).append(multiplyStringNTimes("\t", i)).append(c);
          j = 1;
        }
      }
    }
    if (!localStack.isEmpty())
    {
      Log.Helper.LOGE(this, "JSONString did not close it's brackets, invalid json", new Object[0]);
      return paramString;
    }
    return localStringBuilder.toString();
  }
  
  private void downloadToBuffer(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    int i = 0;
    prepareResponseLog();
    InputStream localInputStream = paramHttpURLConnection.getInputStream();
    if (i >= 0) {
      label159:
      for (;;)
      {
        byte[] arrayOfByte;
        int j;
        try
        {
          arrayOfByte = this.m_response.data.prepareSegment();
          j = 0;
          i = localInputStream.read(arrayOfByte, j, arrayOfByte.length - j);
          if (Thread.interrupted()) {
            throw new InterruptedIOException();
          }
        }
        finally
        {
          localInputStream.close();
        }
        if (i < 0)
        {
          this.m_response.data.appendSegmentToBuffer(arrayOfByte, j);
          if (this.m_progressCallback == null) {
            break;
          }
          this.m_progressCallback.callback(this);
          break;
        }
        if (i == 0) {
          Thread.yield();
        }
        for (;;)
        {
          if (j < arrayOfByte.length) {
            break label159;
          }
          break;
          prepareResponseLog(arrayOfByte, j, i);
          j += i;
          HttpResponse localHttpResponse = this.m_response;
          localHttpResponse.downloadedContentLength += i;
        }
      }
    }
    localInputStream.close();
  }
  
  private void downloadToBufferWithError(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    prepareResponseLog();
    InputStream localInputStream = paramHttpURLConnection.getErrorStream();
    int i = 0;
    if (localInputStream == null)
    {
      return;
      if (i >= 0) {
        break label108;
      }
    }
    label161:
    for (;;)
    {
      byte[] arrayOfByte;
      int j;
      try
      {
        this.m_response.data.appendSegmentToBuffer(arrayOfByte, j);
        if (this.m_progressCallback != null) {
          this.m_progressCallback.callback(this);
        }
        if (i < 0) {
          break label163;
        }
        arrayOfByte = this.m_response.data.prepareSegment();
        j = 0;
        i = localInputStream.read(arrayOfByte, j, arrayOfByte.length - j);
        if (!Thread.interrupted()) {
          break;
        }
        throw new InterruptedIOException();
      }
      finally
      {
        localInputStream.close();
      }
      label108:
      if (i == 0) {
        Thread.yield();
      }
      for (;;)
      {
        if (j < arrayOfByte.length) {
          break label161;
        }
        break;
        prepareResponseLog(arrayOfByte, j, i);
        j += i;
        HttpResponse localHttpResponse = this.m_response;
        localHttpResponse.downloadedContentLength += i;
      }
    }
    label163:
    localInputStream.close();
  }
  
  private void downloadToFile(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    if (skipDownloadForOverwritePolicy(paramHttpURLConnection)) {}
    for (;;)
    {
      return;
      File localFile1 = new File(this.m_request.targetFilePath);
      String str = ApplicationEnvironment.getComponent().getCachePath();
      File localFile2 = new File(str + File.separator + localFile1.getName());
      boolean bool;
      InputStream localInputStream;
      FileOutputStream localFileOutputStream;
      byte[] arrayOfByte;
      if ((localFile2.exists()) && (this.m_request.overwritePolicy.contains(IHttpRequest.OverwritePolicy.RESUME_DOWNLOAD)))
      {
        bool = true;
        localInputStream = paramHttpURLConnection.getInputStream();
        localFileOutputStream = new FileOutputStream(localFile2, bool);
        arrayOfByte = new byte[65536];
        Log.Helper.LOGI(this, "Started File Download for " + localFile1.toString(), new Object[0]);
      }
      for (;;)
      {
        int i;
        try
        {
          i = localInputStream.read(arrayOfByte);
          if (i < 0) {
            break label249;
          }
          if (i != 0) {
            break label199;
          }
          Thread.yield();
          continue;
          bool = false;
        }
        finally
        {
          localInputStream.close();
          localFileOutputStream.close();
        }
        break;
        label199:
        localFileOutputStream.write(arrayOfByte, 0, i);
        HttpResponse localHttpResponse = this.m_response;
        localHttpResponse.downloadedContentLength += i;
        if (this.m_progressCallback != null) {
          this.m_progressCallback.callback(this);
        }
      }
      label249:
      localInputStream.close();
      localFileOutputStream.close();
      if ((localFile1.exists()) && (!localFile1.delete())) {
        Log.Helper.LOGE(this, "Failed to delete existed target file " + localFile1, new Object[0]);
      }
      if (localFile2.renameTo(localFile1)) {
        continue;
      }
      Log.Helper.LOGI(this, "Failed to move temp file " + localFile2 + " to target file " + localFile1, new Object[0]);
      Log.Helper.LOGI(this, "Using fallback, and copying file instead " + localFile2 + "to target file " + localFile1, new Object[0]);
      if (!localFile1.exists()) {
        localFile1.createNewFile();
      }
      FileChannel localFileChannel1 = null;
      FileChannel localFileChannel2 = null;
      try
      {
        localFileChannel1 = new FileInputStream(localFile2).getChannel();
        localFileChannel2 = new FileOutputStream(localFile1).getChannel();
        localFileChannel2.transferFrom(localFileChannel1, 0L, localFileChannel1.size());
        return;
      }
      catch (Exception localException)
      {
        Log.Helper.LOGE(this, "ERROR while copying file, " + localException, new Object[0]);
        return;
      }
      finally
      {
        if (localFileChannel1 != null) {
          localFileChannel1.close();
        }
        if (localFileChannel2 != null) {
          localFileChannel2.close();
        }
        if (localFile2.exists()) {
          localFile2.delete();
        }
      }
    }
  }
  
  private void finish()
  {
    this.m_response.isCompleted = true;
    if (this.m_completionCallback != null) {
      this.m_completionCallback.callback(this);
    }
    try
    {
      notifyAll();
      this.m_manager.removeConnection(this);
      return;
    }
    finally {}
  }
  
  private void httpRecv(HttpURLConnection paramHttpURLConnection)
    throws IOException, Error
  {
    try
    {
      InputStream localInputStream2 = paramHttpURLConnection.getInputStream();
      localObject1 = localInputStream2;
    }
    catch (Exception localException1)
    {
      Object localObject1;
      boolean bool;
      for (;;)
      {
        try
        {
          InputStream localInputStream1 = paramHttpURLConnection.getErrorStream();
          localObject1 = localInputStream1;
        }
        catch (Exception localException2)
        {
          throw new Error(Error.Code.NETWORK_CONNECTION_ERROR, "Exception when getting error stream from HTTP connection.", localException2);
        }
        int i = 0;
      }
      label187:
      this.m_response.downloadedContentLength = 0L;
      if (this.m_headerCallback == null) {
        break label212;
      }
      this.m_headerCallback.callback(this);
      label212:
      if ((!bool) || (localObject1 == null)) {
        break label242;
      }
      downloadToFile(paramHttpURLConnection);
      label242:
      while (this.m_response.expectedContentLength == 0L)
      {
        if (localObject1 != null) {
          ((InputStream)localObject1).close();
        }
        logCommunication();
        return;
      }
      if (this.m_response.data != null) {
        break label307;
      }
      this.m_response.data = new ByteBufferIOStream((int)this.m_response.expectedContentLength);
      for (;;)
      {
        if (this.m_response.statusCode != 200) {
          break label320;
        }
        downloadToBuffer(paramHttpURLConnection);
        break;
        label307:
        this.m_response.data.clear();
      }
      label320:
      downloadToBufferWithError(paramHttpURLConnection);
      throw new HttpError(this.m_response.statusCode, "Request " + this + " failed for HTTP error");
    }
    this.m_response.url = paramHttpURLConnection.getURL();
    try
    {
      this.m_response.statusCode = paramHttpURLConnection.getResponseCode();
      this.m_response.expectedContentLength = paramHttpURLConnection.getContentLength();
      this.m_response.lastModified = paramHttpURLConnection.getLastModified();
      if (this.m_response.expectedContentLength > MAXIMUM_RAW_DATA_LENGTH)
      {
        i = 1;
        bool = Utility.validString(this.m_request.targetFilePath);
        if ((i == 0) || (bool)) {
          break label187;
        }
        throw new Error(Error.Code.NETWORK_OVERSIZE_DATA, "Request " + this + " is oversize, please provide a local file path to download it as file.");
      }
    }
    finally
    {
      if (localObject1 != null) {
        ((InputStream)localObject1).close();
      }
      logCommunication();
    }
  }
  
  private void httpSend(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    if (this.m_request.headers != null)
    {
      Iterator localIterator = this.m_request.headers.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str2 = (String)localIterator.next();
        paramHttpURLConnection.setRequestProperty(str2, (String)this.m_request.headers.get(str2));
      }
    }
    if ((this.m_request.getMethod() != IHttpRequest.Method.POST) && (this.m_request.getMethod() != IHttpRequest.Method.PUT)) {
      logRequest();
    }
    for (;;)
    {
      return;
      byte[] arrayOfByte = this.m_request.data.toByteArray();
      if ((arrayOfByte == null) || (arrayOfByte.length <= 0))
      {
        logRequest();
        return;
      }
      prepareRequestLog(arrayOfByte);
      logRequest();
      paramHttpURLConnection.setDoOutput(true);
      paramHttpURLConnection.setFixedLengthStreamingMode(arrayOfByte.length);
      OutputStream localOutputStream = null;
      try
      {
        localOutputStream = paramHttpURLConnection.getOutputStream();
        localOutputStream.write(arrayOfByte);
        return;
      }
      catch (Exception localException)
      {
        StringWriter localStringWriter = new StringWriter();
        localException.printStackTrace(new PrintWriter(localStringWriter));
        String str1 = localStringWriter.toString();
        Log.Helper.LOGE(this, "Exception in network connection:" + str1, new Object[0]);
        return;
      }
      finally
      {
        if (localOutputStream != null) {
          localOutputStream.close();
        }
      }
    }
  }
  
  private void logCommunication()
  {
    if (Log.getComponent().getThresholdLevel() > 100) {
      return;
    }
    int i = 4096;
    if (this.m_requestDataForLog != null) {
      i += this.m_requestDataForLog.length();
    }
    if (this.m_responseDataForLog != null) {
      i += this.m_responseDataForLog.length();
    }
    StringBuilder localStringBuilder = new StringBuilder(i);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.m_loggingId;
    localStringBuilder.append(String.format("\n>>>> CONNECTION ID %s FINISHED >>>>>>>>>>>>>>\n", arrayOfObject));
    localStringBuilder.append("\n>>>> REQUEST >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
    localStringBuilder.append("REQUEST: ").append(this.m_request.method.toString());
    localStringBuilder.append(' ').append(this.m_request.url.toString()).append('\n');
    if ((this.m_request.headers != null) && (this.m_request.headers.size() > 0))
    {
      Iterator localIterator2 = this.m_request.headers.keySet().iterator();
      while (localIterator2.hasNext())
      {
        String str7 = (String)localIterator2.next();
        localStringBuilder.append("REQ HEADER: ").append(str7);
        String str8 = (String)this.m_request.headers.get(str7);
        localStringBuilder.append(" VALUE: ").append(str8).append('\n');
      }
    }
    if ((this.m_requestDataForLog != null) && (this.m_requestDataForLog.length() > 0))
    {
      localStringBuilder.append("REQ BODY:\n");
      String str5 = this.m_requestDataForLog.toString();
      String str6 = (String)this.m_request.headers.get("Content-Type");
      if ((str6 != null) && ((str6.contains("application/json")) || (str6.contains("text/json")))) {
        str5 = beautifyJSONString(str5);
      }
      localStringBuilder.append(str5).append('\n');
    }
    localStringBuilder.append("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
    localStringBuilder.append(">>>> RESPONSE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
    localStringBuilder.append("RESP URL: ").append(this.m_response.url.toString()).append('\n');
    localStringBuilder.append("RESP STATUS: ").append(this.m_response.statusCode).append('\n');
    if ((this.m_response.headers != null) && (this.m_response.headers.size() > 0))
    {
      Iterator localIterator1 = this.m_response.headers.keySet().iterator();
      while (localIterator1.hasNext())
      {
        String str3 = (String)localIterator1.next();
        localStringBuilder.append("RESP HEADER: ").append(str3);
        String str4 = (String)this.m_response.headers.get(str3);
        localStringBuilder.append(" VALUE: ").append(str4).append('\n');
      }
    }
    localStringBuilder.append("RESP BODY:\n");
    localObject = "";
    try
    {
      Log.Helper.LOGV(this, "There is no response body for this call", new Object[0]);
      if (this.m_responseDataForLog != null)
      {
        String str2 = this.m_responseDataForLog.toString();
        localObject = str2;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        String str1;
        Log.Helper.LOGE(this, "Attempting to process the response body failed.", new Object[0]);
        if ((this.m_response != null) && (this.m_response.getError() != null) && (this.m_response.getError().getMessage() != null)) {
          localObject = this.m_response.getError().getMessage();
        }
      }
    }
    str1 = (String)this.m_request.headers.get("Content-Type");
    if ((str1 != null) && ((str1.contains("application/json")) || (str1.contains("text/json")))) {
      localObject = beautifyJSONString((String)localObject);
    }
    localStringBuilder.append((String)localObject).append('\n');
    localStringBuilder.append("<<<< CONNECTION FINISHED <<<<<<<<<<<<<<<<<<<<<");
    Log.Helper.LOGV(this, localStringBuilder.toString(), new Object[0]);
  }
  
  private void logRequest()
  {
    if (Log.getComponent().getThresholdLevel() > 100) {
      return;
    }
    int i = 2048;
    if (this.m_requestDataForLog != null) {
      i += this.m_requestDataForLog.length();
    }
    StringBuilder localStringBuilder = new StringBuilder(i);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.m_loggingId;
    localStringBuilder.append(String.format("\n>>>> CONNECTION ID %s BEGIN >>>>>>>>>>>>>>>>>\n", arrayOfObject));
    localStringBuilder.append("REQUEST: ").append(this.m_request.method.toString());
    localStringBuilder.append(' ').append(this.m_request.url.toString()).append('\n');
    if ((this.m_request.headers != null) && (this.m_request.headers.size() > 0))
    {
      Iterator localIterator = this.m_request.headers.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str3 = (String)localIterator.next();
        localStringBuilder.append("REQ HEADER: ").append(str3);
        String str4 = (String)this.m_request.headers.get(str3);
        localStringBuilder.append(" VALUE: ").append(str4).append('\n');
      }
    }
    if ((this.m_requestDataForLog != null) && (this.m_requestDataForLog.length() > 0))
    {
      localStringBuilder.append("REQ BODY:\n");
      String str1 = this.m_requestDataForLog.toString();
      String str2 = (String)this.m_request.headers.get("Content-Type");
      if ((str2 != null) && ((str2.contains("application/json")) || (str2.contains("text/json")))) {
        str1 = beautifyJSONString(str1);
      }
      localStringBuilder.append(str1).append('\n');
    }
    localStringBuilder.append("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    Log.Helper.LOGV(this, localStringBuilder.toString(), new Object[0]);
  }
  
  private String multiplyStringNTimes(String paramString, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramInt * paramString.length());
    for (int i = 0; i < paramInt; i++) {
      localStringBuilder.append(paramString);
    }
    return localStringBuilder.toString();
  }
  
  private void prepareRequestLog(byte[] paramArrayOfByte)
  {
    if (Log.getComponent().getThresholdLevel() > 100) {
      return;
    }
    try
    {
      this.m_requestDataForLog = new String(paramArrayOfByte, "UTF-8");
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      this.m_requestDataForLog = null;
    }
  }
  
  private void prepareResponseLog()
  {
    if (Log.getComponent().getThresholdLevel() > 100) {
      return;
    }
    if (this.m_response.expectedContentLength > 0L) {}
    for (int i = (int)this.m_response.expectedContentLength;; i = 4096)
    {
      this.m_responseDataForLog = new StringBuilder(i);
      return;
    }
  }
  
  private void prepareResponseLog(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if ((Log.getComponent().getThresholdLevel() > 100) || (this.m_responseDataForLog == null)) {
      return;
    }
    try
    {
      String str = new String(paramArrayOfByte, paramInt1, paramInt2, "UTF-8");
      this.m_responseDataForLog.append(str);
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      this.m_responseDataForLog = null;
    }
  }
  
  private boolean skipDownloadForOverwritePolicy(HttpURLConnection paramHttpURLConnection)
  {
    boolean bool = true;
    File localFile = new File(this.m_request.targetFilePath);
    if (!localFile.exists()) {
      bool = false;
    }
    while (((localFile.length() != this.m_response.expectedContentLength) && (this.m_request.overwritePolicy.contains(IHttpRequest.OverwritePolicy.LENGTH_CHECK))) || (localFile.lastModified() < this.m_response.lastModified) || (!this.m_request.overwritePolicy.contains(IHttpRequest.OverwritePolicy.DATE_CHECK))) {
      return bool;
    }
    return bool;
  }
  
  /* Error */
  public void cancel()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 45	com/ea/nimble/NetworkConnection:m_thread	Ljava/lang/Thread;
    //   6: ifnull +13 -> 19
    //   9: aload_0
    //   10: getfield 45	com/ea/nimble/NetworkConnection:m_thread	Ljava/lang/Thread;
    //   13: invokevirtual 619	java/lang/Thread:interrupt	()V
    //   16: aload_0
    //   17: monitorexit
    //   18: return
    //   19: aload_0
    //   20: new 340	com/ea/nimble/Error
    //   23: dup
    //   24: getstatic 622	com/ea/nimble/Error$Code:NETWORK_OPERATION_CANCELLED	Lcom/ea/nimble/Error$Code;
    //   27: ldc_w 624
    //   30: invokespecial 385	com/ea/nimble/Error:<init>	(Lcom/ea/nimble/Error$Code;Ljava/lang/String;)V
    //   33: invokevirtual 628	com/ea/nimble/NetworkConnection:finishWithError	(Ljava/lang/Exception;)V
    //   36: goto -20 -> 16
    //   39: astore_1
    //   40: aload_0
    //   41: monitorexit
    //   42: aload_1
    //   43: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	44	0	this	NetworkConnection
    //   39	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	16	39	finally
    //   16	18	39	finally
    //   19	36	39	finally
    //   40	42	39	finally
  }
  
  void finishWithError(Exception paramException)
  {
    if (this.m_response.isCompleted)
    {
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = toString();
      arrayOfObject2[1] = paramException.toString();
      Log.Helper.LOGI(this, "Finished connection %s skipped an error %s", arrayOfObject2);
      return;
    }
    Object[] arrayOfObject1 = new Object[3];
    arrayOfObject1[0] = this.m_loggingId;
    arrayOfObject1[1] = toString();
    arrayOfObject1[2] = paramException.toString();
    Log.Helper.LOGW(this, "Running connection number %s with name %s failed for error %s", arrayOfObject1);
    this.m_response.error = paramException;
    finish();
  }
  
  public NetworkConnectionCallback getCompletionCallback()
  {
    return this.m_completionCallback;
  }
  
  public NetworkConnectionCallback getHeaderCallback()
  {
    return this.m_headerCallback;
  }
  
  public String getLogSourceTitle()
  {
    return "Network";
  }
  
  public NetworkConnectionCallback getProgressCallback()
  {
    return this.m_progressCallback;
  }
  
  public HttpRequest getRequest()
  {
    return this.m_request;
  }
  
  public HttpResponse getResponse()
  {
    return this.m_response;
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 52	com/ea/nimble/NetworkConnection:m_response	Lcom/ea/nimble/HttpResponse;
    //   4: getfield 328	com/ea/nimble/HttpResponse:isCompleted	Z
    //   7: istore 15
    //   9: iload 15
    //   11: ifeq +20 -> 31
    //   14: aload_0
    //   15: monitorenter
    //   16: aload_0
    //   17: aconst_null
    //   18: putfield 45	com/ea/nimble/NetworkConnection:m_thread	Ljava/lang/Thread;
    //   21: aload_0
    //   22: monitorexit
    //   23: return
    //   24: astore 19
    //   26: aload_0
    //   27: monitorexit
    //   28: aload 19
    //   30: athrow
    //   31: invokestatic 173	java/lang/Thread:interrupted	()Z
    //   34: ifeq +88 -> 122
    //   37: new 175	java/io/InterruptedIOException
    //   40: dup
    //   41: invokespecial 176	java/io/InterruptedIOException:<init>	()V
    //   44: athrow
    //   45: astore 13
    //   47: aload_0
    //   48: new 340	com/ea/nimble/Error
    //   51: dup
    //   52: getstatic 668	com/ea/nimble/Error$Code:NETWORK_UNSUPPORTED_CONNECTION_TYPE	Lcom/ea/nimble/Error$Code;
    //   55: new 81	java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial 232	java/lang/StringBuilder:<init>	()V
    //   62: ldc_w 380
    //   65: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: aload_0
    //   69: invokevirtual 629	java/lang/Object:toString	()Ljava/lang/String;
    //   72: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: ldc_w 670
    //   78: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: aload_0
    //   82: getfield 47	com/ea/nimble/NetworkConnection:m_request	Lcom/ea/nimble/HttpRequest;
    //   85: invokevirtual 673	com/ea/nimble/HttpRequest:getUrl	()Ljava/net/URL;
    //   88: invokevirtual 676	java/net/URL:getProtocol	()Ljava/lang/String;
    //   91: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   97: aload 13
    //   99: invokespecial 396	com/ea/nimble/Error:<init>	(Lcom/ea/nimble/Error$Code;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   102: invokevirtual 628	com/ea/nimble/NetworkConnection:finishWithError	(Ljava/lang/Exception;)V
    //   105: aload_0
    //   106: monitorenter
    //   107: aload_0
    //   108: aconst_null
    //   109: putfield 45	com/ea/nimble/NetworkConnection:m_thread	Ljava/lang/Thread;
    //   112: aload_0
    //   113: monitorexit
    //   114: return
    //   115: astore 14
    //   117: aload_0
    //   118: monitorexit
    //   119: aload 14
    //   121: athrow
    //   122: aload_0
    //   123: monitorenter
    //   124: aload_0
    //   125: invokestatic 680	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   128: putfield 45	com/ea/nimble/NetworkConnection:m_thread	Ljava/lang/Thread;
    //   131: aload_0
    //   132: monitorexit
    //   133: aload_0
    //   134: getfield 47	com/ea/nimble/NetworkConnection:m_request	Lcom/ea/nimble/HttpRequest;
    //   137: invokevirtual 673	com/ea/nimble/HttpRequest:getUrl	()Ljava/net/URL;
    //   140: invokevirtual 684	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   143: checkcast 148	java/net/HttpURLConnection
    //   146: astore 17
    //   148: aload 17
    //   150: aload_0
    //   151: getfield 47	com/ea/nimble/NetworkConnection:m_request	Lcom/ea/nimble/HttpRequest;
    //   154: getfield 532	com/ea/nimble/HttpRequest:method	Lcom/ea/nimble/IHttpRequest$Method;
    //   157: invokevirtual 533	com/ea/nimble/IHttpRequest$Method:toString	()Ljava/lang/String;
    //   160: invokevirtual 687	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   163: aload 17
    //   165: ldc2_w 688
    //   168: aload_0
    //   169: getfield 47	com/ea/nimble/NetworkConnection:m_request	Lcom/ea/nimble/HttpRequest;
    //   172: getfield 693	com/ea/nimble/HttpRequest:timeout	D
    //   175: dmul
    //   176: d2i
    //   177: invokevirtual 696	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   180: aload 17
    //   182: ldc2_w 688
    //   185: aload_0
    //   186: getfield 47	com/ea/nimble/NetworkConnection:m_request	Lcom/ea/nimble/HttpRequest;
    //   189: getfield 693	com/ea/nimble/HttpRequest:timeout	D
    //   192: dmul
    //   193: d2i
    //   194: invokevirtual 699	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   197: aload 17
    //   199: ldc_w 701
    //   202: ldc_w 702
    //   205: invokevirtual 445	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   208: invokestatic 173	java/lang/Thread:interrupted	()Z
    //   211: ifeq +146 -> 357
    //   214: new 175	java/io/InterruptedIOException
    //   217: dup
    //   218: invokespecial 176	java/io/InterruptedIOException:<init>	()V
    //   221: athrow
    //   222: astore 11
    //   224: aload_0
    //   225: new 340	com/ea/nimble/Error
    //   228: dup
    //   229: getstatic 705	com/ea/nimble/Error$Code:NETWORK_TIMEOUT	Lcom/ea/nimble/Error$Code;
    //   232: new 81	java/lang/StringBuilder
    //   235: dup
    //   236: invokespecial 232	java/lang/StringBuilder:<init>	()V
    //   239: ldc_w 707
    //   242: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   245: aload_0
    //   246: invokevirtual 629	java/lang/Object:toString	()Ljava/lang/String;
    //   249: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   252: ldc_w 709
    //   255: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   258: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   261: aload 11
    //   263: invokespecial 396	com/ea/nimble/Error:<init>	(Lcom/ea/nimble/Error$Code;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   266: invokevirtual 628	com/ea/nimble/NetworkConnection:finishWithError	(Ljava/lang/Exception;)V
    //   269: aload_0
    //   270: monitorenter
    //   271: aload_0
    //   272: aconst_null
    //   273: putfield 45	com/ea/nimble/NetworkConnection:m_thread	Ljava/lang/Thread;
    //   276: aload_0
    //   277: monitorexit
    //   278: return
    //   279: astore 12
    //   281: aload_0
    //   282: monitorexit
    //   283: aload 12
    //   285: athrow
    //   286: astore 16
    //   288: aload_0
    //   289: monitorexit
    //   290: aload 16
    //   292: athrow
    //   293: astore 9
    //   295: aload_0
    //   296: new 340	com/ea/nimble/Error
    //   299: dup
    //   300: getstatic 622	com/ea/nimble/Error$Code:NETWORK_OPERATION_CANCELLED	Lcom/ea/nimble/Error$Code;
    //   303: new 81	java/lang/StringBuilder
    //   306: dup
    //   307: invokespecial 232	java/lang/StringBuilder:<init>	()V
    //   310: ldc_w 707
    //   313: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   316: aload_0
    //   317: invokevirtual 629	java/lang/Object:toString	()Ljava/lang/String;
    //   320: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   323: ldc_w 711
    //   326: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   329: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   332: aload 9
    //   334: invokespecial 396	com/ea/nimble/Error:<init>	(Lcom/ea/nimble/Error$Code;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   337: invokevirtual 628	com/ea/nimble/NetworkConnection:finishWithError	(Ljava/lang/Exception;)V
    //   340: aload_0
    //   341: monitorenter
    //   342: aload_0
    //   343: aconst_null
    //   344: putfield 45	com/ea/nimble/NetworkConnection:m_thread	Ljava/lang/Thread;
    //   347: aload_0
    //   348: monitorexit
    //   349: return
    //   350: astore 10
    //   352: aload_0
    //   353: monitorexit
    //   354: aload 10
    //   356: athrow
    //   357: aload_0
    //   358: aload 17
    //   360: invokespecial 713	com/ea/nimble/NetworkConnection:httpSend	(Ljava/net/HttpURLConnection;)V
    //   363: invokestatic 173	java/lang/Thread:interrupted	()Z
    //   366: ifeq +36 -> 402
    //   369: new 175	java/io/InterruptedIOException
    //   372: dup
    //   373: invokespecial 176	java/io/InterruptedIOException:<init>	()V
    //   376: athrow
    //   377: astore 7
    //   379: aload_0
    //   380: aload 7
    //   382: invokevirtual 628	com/ea/nimble/NetworkConnection:finishWithError	(Ljava/lang/Exception;)V
    //   385: aload_0
    //   386: monitorenter
    //   387: aload_0
    //   388: aconst_null
    //   389: putfield 45	com/ea/nimble/NetworkConnection:m_thread	Ljava/lang/Thread;
    //   392: aload_0
    //   393: monitorexit
    //   394: return
    //   395: astore 8
    //   397: aload_0
    //   398: monitorexit
    //   399: aload 8
    //   401: athrow
    //   402: aload_0
    //   403: aload 17
    //   405: invokespecial 715	com/ea/nimble/NetworkConnection:httpRecv	(Ljava/net/HttpURLConnection;)V
    //   408: aload_0
    //   409: invokespecial 643	com/ea/nimble/NetworkConnection:finish	()V
    //   412: aload_0
    //   413: monitorenter
    //   414: aload_0
    //   415: aconst_null
    //   416: putfield 45	com/ea/nimble/NetworkConnection:m_thread	Ljava/lang/Thread;
    //   419: aload_0
    //   420: monitorexit
    //   421: return
    //   422: astore 18
    //   424: aload_0
    //   425: monitorexit
    //   426: aload 18
    //   428: athrow
    //   429: astore 5
    //   431: aload_0
    //   432: aload 5
    //   434: invokevirtual 628	com/ea/nimble/NetworkConnection:finishWithError	(Ljava/lang/Exception;)V
    //   437: aload_0
    //   438: monitorenter
    //   439: aload_0
    //   440: aconst_null
    //   441: putfield 45	com/ea/nimble/NetworkConnection:m_thread	Ljava/lang/Thread;
    //   444: aload_0
    //   445: monitorexit
    //   446: return
    //   447: astore 6
    //   449: aload_0
    //   450: monitorexit
    //   451: aload 6
    //   453: athrow
    //   454: astore_3
    //   455: aload_0
    //   456: new 340	com/ea/nimble/Error
    //   459: dup
    //   460: getstatic 718	com/ea/nimble/Error$Code:SYSTEM_UNEXPECTED	Lcom/ea/nimble/Error$Code;
    //   463: ldc_w 720
    //   466: aload_3
    //   467: invokespecial 396	com/ea/nimble/Error:<init>	(Lcom/ea/nimble/Error$Code;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   470: invokevirtual 628	com/ea/nimble/NetworkConnection:finishWithError	(Ljava/lang/Exception;)V
    //   473: aload_0
    //   474: monitorenter
    //   475: aload_0
    //   476: aconst_null
    //   477: putfield 45	com/ea/nimble/NetworkConnection:m_thread	Ljava/lang/Thread;
    //   480: aload_0
    //   481: monitorexit
    //   482: return
    //   483: astore 4
    //   485: aload_0
    //   486: monitorexit
    //   487: aload 4
    //   489: athrow
    //   490: astore_1
    //   491: aload_0
    //   492: monitorenter
    //   493: aload_0
    //   494: aconst_null
    //   495: putfield 45	com/ea/nimble/NetworkConnection:m_thread	Ljava/lang/Thread;
    //   498: aload_0
    //   499: monitorexit
    //   500: aload_1
    //   501: athrow
    //   502: astore_2
    //   503: aload_0
    //   504: monitorexit
    //   505: aload_2
    //   506: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	507	0	this	NetworkConnection
    //   490	11	1	localObject1	Object
    //   502	4	2	localObject2	Object
    //   454	13	3	localException	Exception
    //   483	5	4	localObject3	Object
    //   429	4	5	localError	Error
    //   447	5	6	localObject4	Object
    //   377	4	7	localIOException	IOException
    //   395	5	8	localObject5	Object
    //   293	40	9	localInterruptedIOException	InterruptedIOException
    //   350	5	10	localObject6	Object
    //   222	40	11	localSocketTimeoutException	java.net.SocketTimeoutException
    //   279	5	12	localObject7	Object
    //   45	53	13	localClassCastException	java.lang.ClassCastException
    //   115	5	14	localObject8	Object
    //   7	3	15	bool	boolean
    //   286	5	16	localObject9	Object
    //   146	258	17	localHttpURLConnection	HttpURLConnection
    //   422	5	18	localObject10	Object
    //   24	5	19	localObject11	Object
    // Exception table:
    //   from	to	target	type
    //   16	23	24	finally
    //   26	28	24	finally
    //   0	9	45	java/lang/ClassCastException
    //   31	45	45	java/lang/ClassCastException
    //   122	124	45	java/lang/ClassCastException
    //   133	222	45	java/lang/ClassCastException
    //   290	293	45	java/lang/ClassCastException
    //   357	377	45	java/lang/ClassCastException
    //   402	412	45	java/lang/ClassCastException
    //   107	114	115	finally
    //   117	119	115	finally
    //   0	9	222	java/net/SocketTimeoutException
    //   31	45	222	java/net/SocketTimeoutException
    //   122	124	222	java/net/SocketTimeoutException
    //   133	222	222	java/net/SocketTimeoutException
    //   290	293	222	java/net/SocketTimeoutException
    //   357	377	222	java/net/SocketTimeoutException
    //   402	412	222	java/net/SocketTimeoutException
    //   271	278	279	finally
    //   281	283	279	finally
    //   124	133	286	finally
    //   288	290	286	finally
    //   0	9	293	java/io/InterruptedIOException
    //   31	45	293	java/io/InterruptedIOException
    //   122	124	293	java/io/InterruptedIOException
    //   133	222	293	java/io/InterruptedIOException
    //   290	293	293	java/io/InterruptedIOException
    //   357	377	293	java/io/InterruptedIOException
    //   402	412	293	java/io/InterruptedIOException
    //   342	349	350	finally
    //   352	354	350	finally
    //   0	9	377	java/io/IOException
    //   31	45	377	java/io/IOException
    //   122	124	377	java/io/IOException
    //   133	222	377	java/io/IOException
    //   290	293	377	java/io/IOException
    //   357	377	377	java/io/IOException
    //   402	412	377	java/io/IOException
    //   387	394	395	finally
    //   397	399	395	finally
    //   414	421	422	finally
    //   424	426	422	finally
    //   0	9	429	com/ea/nimble/Error
    //   31	45	429	com/ea/nimble/Error
    //   122	124	429	com/ea/nimble/Error
    //   133	222	429	com/ea/nimble/Error
    //   290	293	429	com/ea/nimble/Error
    //   357	377	429	com/ea/nimble/Error
    //   402	412	429	com/ea/nimble/Error
    //   439	446	447	finally
    //   449	451	447	finally
    //   0	9	454	java/lang/Exception
    //   31	45	454	java/lang/Exception
    //   122	124	454	java/lang/Exception
    //   133	222	454	java/lang/Exception
    //   290	293	454	java/lang/Exception
    //   357	377	454	java/lang/Exception
    //   402	412	454	java/lang/Exception
    //   475	482	483	finally
    //   485	487	483	finally
    //   0	9	490	finally
    //   31	45	490	finally
    //   47	105	490	finally
    //   122	124	490	finally
    //   133	222	490	finally
    //   224	269	490	finally
    //   290	293	490	finally
    //   295	340	490	finally
    //   357	377	490	finally
    //   379	385	490	finally
    //   402	412	490	finally
    //   431	437	490	finally
    //   455	473	490	finally
    //   493	500	502	finally
    //   503	505	502	finally
  }
  
  public void setCompletionCallback(NetworkConnectionCallback paramNetworkConnectionCallback)
  {
    this.m_completionCallback = paramNetworkConnectionCallback;
  }
  
  public void setHeaderCallback(NetworkConnectionCallback paramNetworkConnectionCallback)
  {
    this.m_headerCallback = paramNetworkConnectionCallback;
  }
  
  public void setProgressCallback(NetworkConnectionCallback paramNetworkConnectionCallback)
  {
    this.m_progressCallback = paramNetworkConnectionCallback;
  }
  
  public void suspend()
  {
    cancel();
  }
  
  public void waitOn()
  {
    try
    {
      for (;;)
      {
        boolean bool = this.m_response.isCompleted;
        if (bool) {
          break;
        }
        try
        {
          wait();
        }
        catch (InterruptedException localInterruptedException) {}
      }
      return;
    }
    finally {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.NetworkConnection
 * JD-Core Version:    0.7.0.1
 */