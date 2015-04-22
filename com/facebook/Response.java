package com.facebook;

import android.content.Context;
import com.facebook.internal.FileLruCache;
import com.facebook.internal.FileLruCache.Limits;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObject.Factory;
import com.facebook.model.GraphObjectList;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Response
{
  private static final String BODY_KEY = "body";
  private static final String CODE_KEY = "code";
  private static final int INVALID_SESSION_FACEBOOK_ERROR_CODE = 190;
  public static final String NON_JSON_RESPONSE_PROPERTY = "FACEBOOK_NON_JSON_RESULT";
  private static final String RESPONSE_CACHE_TAG = "ResponseCache";
  private static final String RESPONSE_LOG_TAG = "Response";
  public static final String SUCCESS_KEY = "success";
  private static FileLruCache responseCache;
  private final HttpURLConnection connection;
  private final FacebookRequestError error;
  private final GraphObject graphObject;
  private final GraphObjectList<GraphObject> graphObjectList;
  private final boolean isFromCache;
  private final String rawResponse;
  private final Request request;
  
  static
  {
    if (!Response.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  Response(Request paramRequest, HttpURLConnection paramHttpURLConnection, FacebookRequestError paramFacebookRequestError)
  {
    this(paramRequest, paramHttpURLConnection, null, null, null, false, paramFacebookRequestError);
  }
  
  Response(Request paramRequest, HttpURLConnection paramHttpURLConnection, String paramString, GraphObject paramGraphObject, GraphObjectList<GraphObject> paramGraphObjectList, boolean paramBoolean, FacebookRequestError paramFacebookRequestError)
  {
    this.request = paramRequest;
    this.connection = paramHttpURLConnection;
    this.rawResponse = paramString;
    this.graphObject = paramGraphObject;
    this.graphObjectList = paramGraphObjectList;
    this.isFromCache = paramBoolean;
    this.error = paramFacebookRequestError;
  }
  
  Response(Request paramRequest, HttpURLConnection paramHttpURLConnection, String paramString, GraphObject paramGraphObject, boolean paramBoolean)
  {
    this(paramRequest, paramHttpURLConnection, paramString, paramGraphObject, null, paramBoolean, null);
  }
  
  Response(Request paramRequest, HttpURLConnection paramHttpURLConnection, String paramString, GraphObjectList<GraphObject> paramGraphObjectList, boolean paramBoolean)
  {
    this(paramRequest, paramHttpURLConnection, paramString, null, paramGraphObjectList, paramBoolean, null);
  }
  
  static List<Response> constructErrorResponses(List<Request> paramList, HttpURLConnection paramHttpURLConnection, FacebookException paramFacebookException)
  {
    int i = paramList.size();
    ArrayList localArrayList = new ArrayList(i);
    for (int j = 0; j < i; j++) {
      localArrayList.add(new Response((Request)paramList.get(j), paramHttpURLConnection, new FacebookRequestError(paramHttpURLConnection, paramFacebookException)));
    }
    return localArrayList;
  }
  
  private static Response createResponseFromObject(Request paramRequest, HttpURLConnection paramHttpURLConnection, Object paramObject1, boolean paramBoolean, Object paramObject2)
    throws JSONException
  {
    if ((paramObject1 instanceof JSONObject))
    {
      JSONObject localJSONObject = (JSONObject)paramObject1;
      FacebookRequestError localFacebookRequestError = FacebookRequestError.checkResponseAndCreateError(localJSONObject, paramObject2, paramHttpURLConnection);
      if (localFacebookRequestError != null)
      {
        if (localFacebookRequestError.getErrorCode() == 190)
        {
          Session localSession = paramRequest.getSession();
          if (localSession != null) {
            localSession.closeAndClearTokenInformation();
          }
        }
        return new Response(paramRequest, paramHttpURLConnection, localFacebookRequestError);
      }
      Object localObject2 = Utility.getStringPropertyAsJSON(localJSONObject, "body", "FACEBOOK_NON_JSON_RESULT");
      if ((localObject2 instanceof JSONObject))
      {
        GraphObject localGraphObject = GraphObject.Factory.create((JSONObject)localObject2);
        return new Response(paramRequest, paramHttpURLConnection, localObject2.toString(), localGraphObject, paramBoolean);
      }
      if ((localObject2 instanceof JSONArray))
      {
        GraphObjectList localGraphObjectList = GraphObject.Factory.createList((JSONArray)localObject2, GraphObject.class);
        return new Response(paramRequest, paramHttpURLConnection, localObject2.toString(), localGraphObjectList, paramBoolean);
      }
      paramObject1 = JSONObject.NULL;
    }
    Object localObject1 = JSONObject.NULL;
    if (paramObject1 == localObject1) {
      return new Response(paramRequest, paramHttpURLConnection, paramObject1.toString(), (GraphObject)null, paramBoolean);
    }
    throw new FacebookException("Got unexpected object type in response, class: " + paramObject1.getClass().getSimpleName());
  }
  
  private static List<Response> createResponsesFromObject(HttpURLConnection paramHttpURLConnection, List<Request> paramList, Object paramObject, boolean paramBoolean)
    throws FacebookException, JSONException
  {
    assert ((paramHttpURLConnection != null) || (paramBoolean));
    int i = paramList.size();
    ArrayList localArrayList = new ArrayList(i);
    Object localObject = paramObject;
    Request localRequest2;
    if (i == 1) {
      localRequest2 = (Request)paramList.get(0);
    }
    for (;;)
    {
      try
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("body", paramObject);
        if (paramHttpURLConnection == null) {
          continue;
        }
        k = paramHttpURLConnection.getResponseCode();
        localJSONObject.put("code", k);
        JSONArray localJSONArray2 = new JSONArray();
        localJSONArray2.put(localJSONObject);
        paramObject = localJSONArray2;
      }
      catch (JSONException localJSONException2)
      {
        int k;
        localArrayList.add(new Response(localRequest2, paramHttpURLConnection, new FacebookRequestError(paramHttpURLConnection, localJSONException2)));
        continue;
      }
      catch (IOException localIOException)
      {
        localArrayList.add(new Response(localRequest2, paramHttpURLConnection, new FacebookRequestError(paramHttpURLConnection, localIOException)));
        continue;
        JSONArray localJSONArray1 = (JSONArray)paramObject;
        int j = 0;
        if (j >= localJSONArray1.length()) {
          break label351;
        }
        Request localRequest1 = (Request)paramList.get(j);
        try
        {
          localArrayList.add(createResponseFromObject(localRequest1, paramHttpURLConnection, localJSONArray1.get(j), paramBoolean, localObject));
          j++;
        }
        catch (JSONException localJSONException1)
        {
          localArrayList.add(new Response(localRequest1, paramHttpURLConnection, new FacebookRequestError(paramHttpURLConnection, localJSONException1)));
          continue;
        }
        catch (FacebookException localFacebookException)
        {
          localArrayList.add(new Response(localRequest1, paramHttpURLConnection, new FacebookRequestError(paramHttpURLConnection, localFacebookException)));
          continue;
        }
      }
      if (((paramObject instanceof JSONArray)) && (((JSONArray)paramObject).length() == i)) {
        continue;
      }
      throw new FacebookException("Unexpected number of results");
      k = 200;
    }
    label351:
    return localArrayList;
  }
  
  static List<Response> createResponsesFromStream(InputStream paramInputStream, HttpURLConnection paramHttpURLConnection, RequestBatch paramRequestBatch, boolean paramBoolean)
    throws FacebookException, JSONException, IOException
  {
    String str = Utility.readStreamToString(paramInputStream);
    LoggingBehavior localLoggingBehavior = LoggingBehavior.INCLUDE_RAW_RESPONSES;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(str.length());
    arrayOfObject[1] = str;
    Logger.log(localLoggingBehavior, "Response", "Response (raw)\n  Size: %d\n  Response:\n%s\n", arrayOfObject);
    return createResponsesFromString(str, paramHttpURLConnection, paramRequestBatch, paramBoolean);
  }
  
  static List<Response> createResponsesFromString(String paramString, HttpURLConnection paramHttpURLConnection, RequestBatch paramRequestBatch, boolean paramBoolean)
    throws FacebookException, JSONException, IOException
  {
    List localList = createResponsesFromObject(paramHttpURLConnection, paramRequestBatch, new JSONTokener(paramString).nextValue(), paramBoolean);
    LoggingBehavior localLoggingBehavior = LoggingBehavior.REQUESTS;
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = paramRequestBatch.getId();
    arrayOfObject[1] = Integer.valueOf(paramString.length());
    arrayOfObject[2] = localList;
    Logger.log(localLoggingBehavior, "Response", "Response\n  Id: %s\n  Size: %d\n  Responses:\n%s\n", arrayOfObject);
    return localList;
  }
  
  /* Error */
  static List<Response> fromHttpConnection(HttpURLConnection paramHttpURLConnection, RequestBatch paramRequestBatch)
  {
    // Byte code:
    //   0: aload_1
    //   1: instanceof 273
    //   4: istore_2
    //   5: aconst_null
    //   6: astore_3
    //   7: aconst_null
    //   8: astore 4
    //   10: aconst_null
    //   11: astore 5
    //   13: iload_2
    //   14: ifeq +133 -> 147
    //   17: aload_1
    //   18: checkcast 273	com/facebook/internal/CacheableRequestBatch
    //   21: astore 17
    //   23: invokestatic 277	com/facebook/Response:getResponseCache	()Lcom/facebook/internal/FileLruCache;
    //   26: astore_3
    //   27: aload 17
    //   29: invokevirtual 280	com/facebook/internal/CacheableRequestBatch:getCacheKeyOverride	()Ljava/lang/String;
    //   32: astore 4
    //   34: aload 4
    //   36: invokestatic 284	com/facebook/internal/Utility:isNullOrEmpty	(Ljava/lang/String;)Z
    //   39: ifeq +21 -> 60
    //   42: aload_1
    //   43: invokevirtual 285	com/facebook/RequestBatch:size	()I
    //   46: iconst_1
    //   47: if_icmpne +81 -> 128
    //   50: aload_1
    //   51: iconst_0
    //   52: invokevirtual 288	com/facebook/RequestBatch:get	(I)Lcom/facebook/Request;
    //   55: invokevirtual 291	com/facebook/Request:getUrlForSingleRequest	()Ljava/lang/String;
    //   58: astore 4
    //   60: aload 17
    //   62: invokevirtual 294	com/facebook/internal/CacheableRequestBatch:getForceRoundTrip	()Z
    //   65: istore 18
    //   67: aconst_null
    //   68: astore 5
    //   70: iload 18
    //   72: ifne +75 -> 147
    //   75: aconst_null
    //   76: astore 5
    //   78: aload_3
    //   79: ifnull +68 -> 147
    //   82: aload 4
    //   84: invokestatic 284	com/facebook/internal/Utility:isNullOrEmpty	(Ljava/lang/String;)Z
    //   87: istore 19
    //   89: aconst_null
    //   90: astore 5
    //   92: iload 19
    //   94: ifne +53 -> 147
    //   97: aload_3
    //   98: aload 4
    //   100: invokevirtual 299	com/facebook/internal/FileLruCache:get	(Ljava/lang/String;)Ljava/io/InputStream;
    //   103: astore 5
    //   105: aload 5
    //   107: ifnull +35 -> 142
    //   110: aload 5
    //   112: aconst_null
    //   113: aload_1
    //   114: iconst_1
    //   115: invokestatic 301	com/facebook/Response:createResponsesFromStream	(Ljava/io/InputStream;Ljava/net/HttpURLConnection;Lcom/facebook/RequestBatch;Z)Ljava/util/List;
    //   118: astore 24
    //   120: aload 5
    //   122: invokestatic 305	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   125: aload 24
    //   127: areturn
    //   128: getstatic 260	com/facebook/LoggingBehavior:REQUESTS	Lcom/facebook/LoggingBehavior;
    //   131: ldc 23
    //   133: ldc_w 307
    //   136: invokestatic 310	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
    //   139: goto -79 -> 60
    //   142: aload 5
    //   144: invokestatic 305	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   147: aload_0
    //   148: invokevirtual 200	java/net/HttpURLConnection:getResponseCode	()I
    //   151: sipush 400
    //   154: if_icmplt +67 -> 221
    //   157: aload_0
    //   158: invokevirtual 314	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   161: astore 5
    //   163: aload 5
    //   165: aload_0
    //   166: aload_1
    //   167: iconst_0
    //   168: invokestatic 301	com/facebook/Response:createResponsesFromStream	(Ljava/io/InputStream;Ljava/net/HttpURLConnection;Lcom/facebook/RequestBatch;Z)Ljava/util/List;
    //   171: astore 16
    //   173: aload 5
    //   175: invokestatic 305	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   178: aload 16
    //   180: areturn
    //   181: astore 23
    //   183: aload 5
    //   185: invokestatic 305	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   188: goto -41 -> 147
    //   191: astore 22
    //   193: aload 5
    //   195: invokestatic 305	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   198: goto -51 -> 147
    //   201: astore 21
    //   203: aload 5
    //   205: invokestatic 305	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   208: goto -61 -> 147
    //   211: astore 20
    //   213: aload 5
    //   215: invokestatic 305	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   218: aload 20
    //   220: athrow
    //   221: aload_0
    //   222: invokevirtual 317	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   225: astore 5
    //   227: aload_3
    //   228: ifnull -65 -> 163
    //   231: aload 4
    //   233: ifnull -70 -> 163
    //   236: aload 5
    //   238: ifnull -75 -> 163
    //   241: aload_3
    //   242: aload 4
    //   244: aload 5
    //   246: invokevirtual 321	com/facebook/internal/FileLruCache:interceptAndPut	(Ljava/lang/String;Ljava/io/InputStream;)Ljava/io/InputStream;
    //   249: astore 15
    //   251: aload 15
    //   253: ifnull -90 -> 163
    //   256: aload 15
    //   258: astore 5
    //   260: goto -97 -> 163
    //   263: astore 13
    //   265: getstatic 260	com/facebook/LoggingBehavior:REQUESTS	Lcom/facebook/LoggingBehavior;
    //   268: ldc 26
    //   270: ldc_w 323
    //   273: iconst_1
    //   274: anewarray 4	java/lang/Object
    //   277: dup
    //   278: iconst_0
    //   279: aload 13
    //   281: aastore
    //   282: invokestatic 244	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   285: aload_1
    //   286: aload_0
    //   287: aload 13
    //   289: invokestatic 325	com/facebook/Response:constructErrorResponses	(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
    //   292: astore 14
    //   294: aload 5
    //   296: invokestatic 305	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   299: aload 14
    //   301: areturn
    //   302: astore 11
    //   304: getstatic 260	com/facebook/LoggingBehavior:REQUESTS	Lcom/facebook/LoggingBehavior;
    //   307: ldc 26
    //   309: ldc_w 323
    //   312: iconst_1
    //   313: anewarray 4	java/lang/Object
    //   316: dup
    //   317: iconst_0
    //   318: aload 11
    //   320: aastore
    //   321: invokestatic 244	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   324: aload_1
    //   325: aload_0
    //   326: new 163	com/facebook/FacebookException
    //   329: dup
    //   330: aload 11
    //   332: invokespecial 328	com/facebook/FacebookException:<init>	(Ljava/lang/Throwable;)V
    //   335: invokestatic 325	com/facebook/Response:constructErrorResponses	(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
    //   338: astore 12
    //   340: aload 5
    //   342: invokestatic 305	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   345: aload 12
    //   347: areturn
    //   348: astore 9
    //   350: getstatic 260	com/facebook/LoggingBehavior:REQUESTS	Lcom/facebook/LoggingBehavior;
    //   353: ldc 26
    //   355: ldc_w 323
    //   358: iconst_1
    //   359: anewarray 4	java/lang/Object
    //   362: dup
    //   363: iconst_0
    //   364: aload 9
    //   366: aastore
    //   367: invokestatic 244	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   370: aload_1
    //   371: aload_0
    //   372: new 163	com/facebook/FacebookException
    //   375: dup
    //   376: aload 9
    //   378: invokespecial 328	com/facebook/FacebookException:<init>	(Ljava/lang/Throwable;)V
    //   381: invokestatic 325	com/facebook/Response:constructErrorResponses	(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
    //   384: astore 10
    //   386: aload 5
    //   388: invokestatic 305	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   391: aload 10
    //   393: areturn
    //   394: astore 7
    //   396: getstatic 260	com/facebook/LoggingBehavior:REQUESTS	Lcom/facebook/LoggingBehavior;
    //   399: ldc 26
    //   401: ldc_w 323
    //   404: iconst_1
    //   405: anewarray 4	java/lang/Object
    //   408: dup
    //   409: iconst_0
    //   410: aload 7
    //   412: aastore
    //   413: invokestatic 244	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   416: aload_1
    //   417: aload_0
    //   418: new 163	com/facebook/FacebookException
    //   421: dup
    //   422: aload 7
    //   424: invokespecial 328	com/facebook/FacebookException:<init>	(Ljava/lang/Throwable;)V
    //   427: invokestatic 325	com/facebook/Response:constructErrorResponses	(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
    //   430: astore 8
    //   432: aload 5
    //   434: invokestatic 305	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   437: aload 8
    //   439: areturn
    //   440: astore 6
    //   442: aload 5
    //   444: invokestatic 305	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   447: aload 6
    //   449: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	450	0	paramHttpURLConnection	HttpURLConnection
    //   0	450	1	paramRequestBatch	RequestBatch
    //   4	10	2	bool1	boolean
    //   6	236	3	localFileLruCache	FileLruCache
    //   8	235	4	str	String
    //   11	432	5	localObject1	Object
    //   440	8	6	localObject2	Object
    //   394	29	7	localSecurityException	java.lang.SecurityException
    //   430	8	8	localList1	List
    //   348	29	9	localIOException1	IOException
    //   384	8	10	localList2	List
    //   302	29	11	localJSONException1	JSONException
    //   338	8	12	localList3	List
    //   263	25	13	localFacebookException1	FacebookException
    //   292	8	14	localList4	List
    //   249	8	15	localInputStream	InputStream
    //   171	8	16	localList5	List
    //   21	40	17	localCacheableRequestBatch	com.facebook.internal.CacheableRequestBatch
    //   65	6	18	bool2	boolean
    //   87	6	19	bool3	boolean
    //   211	8	20	localObject3	Object
    //   201	1	21	localIOException2	IOException
    //   191	1	22	localJSONException2	JSONException
    //   181	1	23	localFacebookException2	FacebookException
    //   118	8	24	localList6	List
    // Exception table:
    //   from	to	target	type
    //   97	105	181	com/facebook/FacebookException
    //   110	120	181	com/facebook/FacebookException
    //   97	105	191	org/json/JSONException
    //   110	120	191	org/json/JSONException
    //   97	105	201	java/io/IOException
    //   110	120	201	java/io/IOException
    //   97	105	211	finally
    //   110	120	211	finally
    //   147	163	263	com/facebook/FacebookException
    //   163	173	263	com/facebook/FacebookException
    //   221	227	263	com/facebook/FacebookException
    //   241	251	263	com/facebook/FacebookException
    //   147	163	302	org/json/JSONException
    //   163	173	302	org/json/JSONException
    //   221	227	302	org/json/JSONException
    //   241	251	302	org/json/JSONException
    //   147	163	348	java/io/IOException
    //   163	173	348	java/io/IOException
    //   221	227	348	java/io/IOException
    //   241	251	348	java/io/IOException
    //   147	163	394	java/lang/SecurityException
    //   163	173	394	java/lang/SecurityException
    //   221	227	394	java/lang/SecurityException
    //   241	251	394	java/lang/SecurityException
    //   147	163	440	finally
    //   163	173	440	finally
    //   221	227	440	finally
    //   241	251	440	finally
    //   265	294	440	finally
    //   304	340	440	finally
    //   350	386	440	finally
    //   396	432	440	finally
  }
  
  static FileLruCache getResponseCache()
  {
    if (responseCache == null)
    {
      Context localContext = Session.getStaticContext();
      if (localContext != null) {
        responseCache = new FileLruCache(localContext, "ResponseCache", new FileLruCache.Limits());
      }
    }
    return responseCache;
  }
  
  public final HttpURLConnection getConnection()
  {
    return this.connection;
  }
  
  public final FacebookRequestError getError()
  {
    return this.error;
  }
  
  public final GraphObject getGraphObject()
  {
    return this.graphObject;
  }
  
  public final <T extends GraphObject> T getGraphObjectAs(Class<T> paramClass)
  {
    if (this.graphObject == null) {
      return null;
    }
    if (paramClass == null) {
      throw new NullPointerException("Must pass in a valid interface that extends GraphObject");
    }
    return this.graphObject.cast(paramClass);
  }
  
  public final GraphObjectList<GraphObject> getGraphObjectList()
  {
    return this.graphObjectList;
  }
  
  public final <T extends GraphObject> GraphObjectList<T> getGraphObjectListAs(Class<T> paramClass)
  {
    if (this.graphObjectList == null) {
      return null;
    }
    return this.graphObjectList.castToListOf(paramClass);
  }
  
  public final boolean getIsFromCache()
  {
    return this.isFromCache;
  }
  
  public String getRawResponse()
  {
    return this.rawResponse;
  }
  
  public Request getRequest()
  {
    return this.request;
  }
  
  public Request getRequestForPagedResults(PagingDirection paramPagingDirection)
  {
    GraphObject localGraphObject = this.graphObject;
    String str = null;
    PagingInfo localPagingInfo;
    if (localGraphObject != null)
    {
      localPagingInfo = ((PagedResults)this.graphObject.cast(PagedResults.class)).getPaging();
      str = null;
      if (localPagingInfo != null) {
        if (paramPagingDirection != PagingDirection.NEXT) {
          break label64;
        }
      }
    }
    label64:
    for (str = localPagingInfo.getNext(); Utility.isNullOrEmpty(str); str = localPagingInfo.getPrevious()) {
      return null;
    }
    if ((str != null) && (str.equals(this.request.getUrlForSingleRequest()))) {
      return null;
    }
    try
    {
      Request localRequest = new Request(this.request.getSession(), new URL(str));
      return localRequest;
    }
    catch (MalformedURLException localMalformedURLException) {}
    return null;
  }
  
  public String toString()
  {
    for (;;)
    {
      try
      {
        Object[] arrayOfObject = new Object[1];
        if (this.connection == null) {
          continue;
        }
        i = this.connection.getResponseCode();
        arrayOfObject[0] = Integer.valueOf(i);
        String str2 = String.format("%d", arrayOfObject);
        str1 = str2;
      }
      catch (IOException localIOException)
      {
        int i;
        String str1 = "unknown";
        continue;
      }
      return "{Response: " + " responseCode: " + str1 + ", graphObject: " + this.graphObject + ", error: " + this.error + ", isFromCache:" + this.isFromCache + "}";
      i = 200;
    }
  }
  
  static abstract interface PagedResults
    extends GraphObject
  {
    public abstract GraphObjectList<GraphObject> getData();
    
    public abstract Response.PagingInfo getPaging();
  }
  
  public static enum PagingDirection
  {
    static
    {
      PagingDirection[] arrayOfPagingDirection = new PagingDirection[2];
      arrayOfPagingDirection[0] = NEXT;
      arrayOfPagingDirection[1] = PREVIOUS;
      $VALUES = arrayOfPagingDirection;
    }
    
    private PagingDirection() {}
  }
  
  static abstract interface PagingInfo
    extends GraphObject
  {
    public abstract String getNext();
    
    public abstract String getPrevious();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.facebook.Response
 * JD-Core Version:    0.7.0.1
 */