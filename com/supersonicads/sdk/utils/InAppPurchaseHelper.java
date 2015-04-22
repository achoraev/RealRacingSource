package com.supersonicads.sdk.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class InAppPurchaseHelper
{
  private static final String TAG = InAppPurchaseHelper.class.getSimpleName();
  public static InAppPurchaseHelper mInstance;
  private Object mService;
  private ServiceConnection mServiceConn;
  
  public static InAppPurchaseHelper getInstance()
  {
    try
    {
      if (mInstance == null) {
        mInstance = new InAppPurchaseHelper();
      }
      InAppPurchaseHelper localInAppPurchaseHelper = mInstance;
      return localInAppPurchaseHelper;
    }
    finally {}
  }
  
  public static boolean isInAppBillingServiceExist()
  {
    try
    {
      Class localClass = Class.forName("com.android.vending.billing.IInAppBillingService");
      if (localClass != null) {
        return true;
      }
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      if (localClassNotFoundException != null)
      {
        if (localClassNotFoundException.getMessage() != null) {
          Logger.i(TAG, localClassNotFoundException.getClass().getSimpleName() + ": " + localClassNotFoundException.getMessage());
        }
        if (localClassNotFoundException.getCause() != null) {
          Logger.i(TAG, localClassNotFoundException.getClass().getSimpleName() + ": " + localClassNotFoundException.getCause());
        }
      }
    }
    return false;
  }
  
  public void bindToIInAppBillingService(final Context paramContext, final OnPurchasedItemsListener paramOnPurchasedItemsListener)
  {
    this.mServiceConn = new ServiceConnection()
    {
      public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
      {
        InAppPurchaseHelper.this.mService = InAppPurchaseHelper.this.getIInAppBillingServiceClass(paramAnonymousIBinder);
        if (InAppPurchaseHelper.this.mService != null)
        {
          String str = InAppPurchaseHelper.this.queryingPurchasedItems(InAppPurchaseHelper.this.mService, paramContext.getPackageName());
          paramContext.unbindService(InAppPurchaseHelper.this.mServiceConn);
          paramOnPurchasedItemsListener.onPurchasedItemsSuccess(str);
          return;
        }
        paramOnPurchasedItemsListener.onPurchasedItemsFail();
      }
      
      public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
      {
        InAppPurchaseHelper.this.mService = null;
      }
    };
    paramContext.bindService(new Intent("com.android.vending.billing.InAppBillingService.BIND"), this.mServiceConn, 1);
  }
  
  /* Error */
  public Object getIInAppBillingServiceClass(IBinder paramIBinder)
  {
    // Byte code:
    //   0: ldc 120
    //   2: invokestatic 50	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   5: ldc 122
    //   7: iconst_1
    //   8: anewarray 16	java/lang/Class
    //   11: dup
    //   12: iconst_0
    //   13: ldc 124
    //   15: aastore
    //   16: invokevirtual 128	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   19: aconst_null
    //   20: iconst_1
    //   21: anewarray 4	java/lang/Object
    //   24: dup
    //   25: iconst_0
    //   26: aload_1
    //   27: aastore
    //   28: invokevirtual 134	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   31: astore 14
    //   33: aload 14
    //   35: astore 4
    //   37: iconst_0
    //   38: ifeq +93 -> 131
    //   41: aconst_null
    //   42: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   45: ifnull +41 -> 86
    //   48: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   51: new 55	java/lang/StringBuilder
    //   54: dup
    //   55: aconst_null
    //   56: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   59: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   62: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   65: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   68: ldc 70
    //   70: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: aconst_null
    //   74: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   77: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   83: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   86: aconst_null
    //   87: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   90: ifnull +41 -> 131
    //   93: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   96: new 55	java/lang/StringBuilder
    //   99: dup
    //   100: aconst_null
    //   101: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   104: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   107: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   110: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   113: ldc 70
    //   115: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: aconst_null
    //   119: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   122: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   125: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   128: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   131: aload 4
    //   133: areturn
    //   134: astore 12
    //   136: aconst_null
    //   137: astore 4
    //   139: aload 12
    //   141: ifnull -10 -> 131
    //   144: aload 12
    //   146: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   149: ifnull +43 -> 192
    //   152: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   155: new 55	java/lang/StringBuilder
    //   158: dup
    //   159: aload 12
    //   161: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   164: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   167: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   170: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   173: ldc 70
    //   175: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   178: aload 12
    //   180: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   183: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   189: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   192: aload 12
    //   194: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   197: astore 13
    //   199: aconst_null
    //   200: astore 4
    //   202: aload 13
    //   204: ifnull -73 -> 131
    //   207: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   210: new 55	java/lang/StringBuilder
    //   213: dup
    //   214: aload 12
    //   216: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   219: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   222: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   225: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   228: ldc 70
    //   230: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: aload 12
    //   235: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   238: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   241: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   244: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   247: aconst_null
    //   248: areturn
    //   249: astore 10
    //   251: aconst_null
    //   252: astore 4
    //   254: aload 10
    //   256: ifnull -125 -> 131
    //   259: aload 10
    //   261: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   264: ifnull +43 -> 307
    //   267: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   270: new 55	java/lang/StringBuilder
    //   273: dup
    //   274: aload 10
    //   276: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   279: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   282: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   285: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   288: ldc 70
    //   290: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   293: aload 10
    //   295: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   298: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   301: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   304: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   307: aload 10
    //   309: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   312: astore 11
    //   314: aconst_null
    //   315: astore 4
    //   317: aload 11
    //   319: ifnull -188 -> 131
    //   322: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   325: new 55	java/lang/StringBuilder
    //   328: dup
    //   329: aload 10
    //   331: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   334: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   337: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   340: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   343: ldc 70
    //   345: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   348: aload 10
    //   350: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   353: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   356: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   359: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   362: aconst_null
    //   363: areturn
    //   364: astore 8
    //   366: aconst_null
    //   367: astore 4
    //   369: aload 8
    //   371: ifnull -240 -> 131
    //   374: aload 8
    //   376: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   379: ifnull +43 -> 422
    //   382: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   385: new 55	java/lang/StringBuilder
    //   388: dup
    //   389: aload 8
    //   391: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   394: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   397: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   400: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   403: ldc 70
    //   405: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   408: aload 8
    //   410: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   413: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   416: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   419: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   422: aload 8
    //   424: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   427: astore 9
    //   429: aconst_null
    //   430: astore 4
    //   432: aload 9
    //   434: ifnull -303 -> 131
    //   437: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   440: new 55	java/lang/StringBuilder
    //   443: dup
    //   444: aload 8
    //   446: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   449: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   452: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   455: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   458: ldc 70
    //   460: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   463: aload 8
    //   465: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   468: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   471: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   474: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   477: aconst_null
    //   478: areturn
    //   479: astore 6
    //   481: aconst_null
    //   482: astore 4
    //   484: aload 6
    //   486: ifnull -355 -> 131
    //   489: aload 6
    //   491: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   494: ifnull +43 -> 537
    //   497: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   500: new 55	java/lang/StringBuilder
    //   503: dup
    //   504: aload 6
    //   506: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   509: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   512: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   515: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   518: ldc 70
    //   520: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   523: aload 6
    //   525: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   528: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   531: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   534: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   537: aload 6
    //   539: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   542: astore 7
    //   544: aconst_null
    //   545: astore 4
    //   547: aload 7
    //   549: ifnull -418 -> 131
    //   552: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   555: new 55	java/lang/StringBuilder
    //   558: dup
    //   559: aload 6
    //   561: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   564: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   567: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   570: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   573: ldc 70
    //   575: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   578: aload 6
    //   580: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   583: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   586: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   589: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   592: aconst_null
    //   593: areturn
    //   594: astore_3
    //   595: aconst_null
    //   596: astore 4
    //   598: aload_3
    //   599: ifnull -468 -> 131
    //   602: aload_3
    //   603: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   606: ifnull +41 -> 647
    //   609: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   612: new 55	java/lang/StringBuilder
    //   615: dup
    //   616: aload_3
    //   617: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   620: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   623: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   626: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   629: ldc 70
    //   631: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   634: aload_3
    //   635: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   638: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   641: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   644: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   647: aload_3
    //   648: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   651: astore 5
    //   653: aconst_null
    //   654: astore 4
    //   656: aload 5
    //   658: ifnull -527 -> 131
    //   661: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   664: new 55	java/lang/StringBuilder
    //   667: dup
    //   668: aload_3
    //   669: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   672: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   675: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   678: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   681: ldc 70
    //   683: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   686: aload_3
    //   687: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   690: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   693: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   696: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   699: aconst_null
    //   700: areturn
    //   701: astore_2
    //   702: iconst_0
    //   703: ifeq +93 -> 796
    //   706: aconst_null
    //   707: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   710: ifnull +41 -> 751
    //   713: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   716: new 55	java/lang/StringBuilder
    //   719: dup
    //   720: aconst_null
    //   721: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   724: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   727: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   730: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   733: ldc 70
    //   735: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   738: aconst_null
    //   739: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   742: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   745: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   748: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   751: aconst_null
    //   752: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   755: ifnull +41 -> 796
    //   758: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   761: new 55	java/lang/StringBuilder
    //   764: dup
    //   765: aconst_null
    //   766: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   769: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   772: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   775: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   778: ldc 70
    //   780: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   783: aconst_null
    //   784: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   787: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   790: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   793: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   796: aload_2
    //   797: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	798	0	this	InAppPurchaseHelper
    //   0	798	1	paramIBinder	IBinder
    //   701	96	2	localObject1	Object
    //   594	93	3	localInvocationTargetException	java.lang.reflect.InvocationTargetException
    //   35	620	4	localObject2	Object
    //   651	6	5	localThrowable1	java.lang.Throwable
    //   479	100	6	localIllegalArgumentException	java.lang.IllegalArgumentException
    //   542	6	7	localThrowable2	java.lang.Throwable
    //   364	100	8	localIllegalAccessException	java.lang.IllegalAccessException
    //   427	6	9	localThrowable3	java.lang.Throwable
    //   249	100	10	localNoSuchMethodException	java.lang.NoSuchMethodException
    //   312	6	11	localThrowable4	java.lang.Throwable
    //   134	100	12	localClassNotFoundException	ClassNotFoundException
    //   197	6	13	localThrowable5	java.lang.Throwable
    //   31	3	14	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   0	33	134	java/lang/ClassNotFoundException
    //   0	33	249	java/lang/NoSuchMethodException
    //   0	33	364	java/lang/IllegalAccessException
    //   0	33	479	java/lang/IllegalArgumentException
    //   0	33	594	java/lang/reflect/InvocationTargetException
    //   0	33	701	finally
  }
  
  /* Error */
  public String queryingPurchasedItems(Object paramObject, String paramString)
  {
    // Byte code:
    //   0: new 144	org/json/JSONArray
    //   3: dup
    //   4: invokespecial 145	org/json/JSONArray:<init>	()V
    //   7: astore_3
    //   8: aload_1
    //   9: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   12: astore 9
    //   14: iconst_4
    //   15: anewarray 16	java/lang/Class
    //   18: astore 10
    //   20: aload 10
    //   22: iconst_0
    //   23: getstatic 151	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   26: aastore
    //   27: aload 10
    //   29: iconst_1
    //   30: ldc 61
    //   32: aastore
    //   33: aload 10
    //   35: iconst_2
    //   36: ldc 61
    //   38: aastore
    //   39: aload 10
    //   41: iconst_3
    //   42: ldc 61
    //   44: aastore
    //   45: aload 9
    //   47: ldc 153
    //   49: aload 10
    //   51: invokevirtual 128	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   54: astore 11
    //   56: iconst_4
    //   57: anewarray 4	java/lang/Object
    //   60: astore 12
    //   62: aload 12
    //   64: iconst_0
    //   65: iconst_3
    //   66: invokestatic 156	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   69: aastore
    //   70: aload 12
    //   72: iconst_1
    //   73: aload_2
    //   74: aastore
    //   75: aload 12
    //   77: iconst_2
    //   78: ldc 158
    //   80: aastore
    //   81: aload 11
    //   83: aload_1
    //   84: aload 12
    //   86: invokevirtual 134	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   89: astore 13
    //   91: aload 13
    //   93: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   96: ldc 160
    //   98: iconst_1
    //   99: anewarray 16	java/lang/Class
    //   102: dup
    //   103: iconst_0
    //   104: ldc 61
    //   106: aastore
    //   107: invokevirtual 128	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   110: astore 14
    //   112: aload 13
    //   114: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   117: ldc 162
    //   119: iconst_1
    //   120: anewarray 16	java/lang/Class
    //   123: dup
    //   124: iconst_0
    //   125: ldc 61
    //   127: aastore
    //   128: invokevirtual 128	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   131: astore 15
    //   133: aload 13
    //   135: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   138: ldc 164
    //   140: iconst_1
    //   141: anewarray 16	java/lang/Class
    //   144: dup
    //   145: iconst_0
    //   146: ldc 61
    //   148: aastore
    //   149: invokevirtual 128	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   152: astore 16
    //   154: aload 14
    //   156: aload 13
    //   158: iconst_1
    //   159: anewarray 4	java/lang/Object
    //   162: dup
    //   163: iconst_0
    //   164: ldc 166
    //   166: aastore
    //   167: invokevirtual 134	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   170: checkcast 147	java/lang/Integer
    //   173: invokevirtual 170	java/lang/Integer:intValue	()I
    //   176: ifne +103 -> 279
    //   179: aload 15
    //   181: aload 13
    //   183: iconst_1
    //   184: anewarray 4	java/lang/Object
    //   187: dup
    //   188: iconst_0
    //   189: ldc 172
    //   191: aastore
    //   192: invokevirtual 134	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   195: checkcast 174	java/util/ArrayList
    //   198: astore 17
    //   200: aload 15
    //   202: aload 13
    //   204: iconst_1
    //   205: anewarray 4	java/lang/Object
    //   208: dup
    //   209: iconst_0
    //   210: ldc 176
    //   212: aastore
    //   213: invokevirtual 134	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   216: checkcast 174	java/util/ArrayList
    //   219: astore 18
    //   221: aload 15
    //   223: aload 13
    //   225: iconst_1
    //   226: anewarray 4	java/lang/Object
    //   229: dup
    //   230: iconst_0
    //   231: ldc 178
    //   233: aastore
    //   234: invokevirtual 134	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   237: checkcast 174	java/util/ArrayList
    //   240: astore 19
    //   242: aload 16
    //   244: aload 13
    //   246: iconst_1
    //   247: anewarray 4	java/lang/Object
    //   250: dup
    //   251: iconst_0
    //   252: ldc 180
    //   254: aastore
    //   255: invokevirtual 134	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   258: checkcast 61	java/lang/String
    //   261: pop
    //   262: iconst_0
    //   263: istore 21
    //   265: aload 18
    //   267: invokevirtual 183	java/util/ArrayList:size	()I
    //   270: istore 22
    //   272: iload 21
    //   274: iload 22
    //   276: if_icmplt +102 -> 378
    //   279: iconst_0
    //   280: ifeq +93 -> 373
    //   283: aconst_null
    //   284: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   287: ifnull +41 -> 328
    //   290: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   293: new 55	java/lang/StringBuilder
    //   296: dup
    //   297: aconst_null
    //   298: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   301: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   304: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   307: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   310: ldc 70
    //   312: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   315: aconst_null
    //   316: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   319: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   322: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   325: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   328: aconst_null
    //   329: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   332: ifnull +41 -> 373
    //   335: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   338: new 55	java/lang/StringBuilder
    //   341: dup
    //   342: aconst_null
    //   343: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   346: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   349: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   352: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   355: ldc 70
    //   357: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   360: aconst_null
    //   361: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   364: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   367: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   370: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   373: aload_3
    //   374: invokevirtual 184	org/json/JSONArray:toString	()Ljava/lang/String;
    //   377: areturn
    //   378: aload 18
    //   380: iload 21
    //   382: invokevirtual 188	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   385: checkcast 61	java/lang/String
    //   388: astore 23
    //   390: aload 19
    //   392: iload 21
    //   394: invokevirtual 188	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   397: checkcast 61	java/lang/String
    //   400: astore 24
    //   402: aload 17
    //   404: iload 21
    //   406: invokevirtual 188	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   409: checkcast 61	java/lang/String
    //   412: astore 25
    //   414: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   417: aload 23
    //   419: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   422: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   425: aload 24
    //   427: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   430: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   433: aload 25
    //   435: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   438: new 190	org/json/JSONObject
    //   441: dup
    //   442: invokespecial 191	org/json/JSONObject:<init>	()V
    //   445: astore 26
    //   447: aload 26
    //   449: ldc 193
    //   451: aload 23
    //   453: invokevirtual 197	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   456: pop
    //   457: aload 26
    //   459: ldc 199
    //   461: aload 23
    //   463: invokevirtual 197	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   466: pop
    //   467: aload 26
    //   469: ldc 201
    //   471: aload 23
    //   473: invokevirtual 197	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   476: pop
    //   477: aload_3
    //   478: aload 26
    //   480: invokevirtual 204	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   483: pop
    //   484: iinc 21 1
    //   487: goto -222 -> 265
    //   490: astore 8
    //   492: aload 8
    //   494: ifnull -121 -> 373
    //   497: aload 8
    //   499: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   502: ifnull +43 -> 545
    //   505: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   508: new 55	java/lang/StringBuilder
    //   511: dup
    //   512: aload 8
    //   514: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   517: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   520: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   523: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   526: ldc 70
    //   528: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   531: aload 8
    //   533: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   536: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   539: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   542: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   545: aload 8
    //   547: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   550: ifnull -177 -> 373
    //   553: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   556: new 55	java/lang/StringBuilder
    //   559: dup
    //   560: aload 8
    //   562: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   565: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   568: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   571: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   574: ldc 70
    //   576: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   579: aload 8
    //   581: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   584: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   587: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   590: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   593: goto -220 -> 373
    //   596: astore 7
    //   598: aload 7
    //   600: ifnull -227 -> 373
    //   603: aload 7
    //   605: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   608: ifnull +43 -> 651
    //   611: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   614: new 55	java/lang/StringBuilder
    //   617: dup
    //   618: aload 7
    //   620: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   623: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   626: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   629: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   632: ldc 70
    //   634: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   637: aload 7
    //   639: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   642: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   645: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   648: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   651: aload 7
    //   653: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   656: ifnull -283 -> 373
    //   659: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   662: new 55	java/lang/StringBuilder
    //   665: dup
    //   666: aload 7
    //   668: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   671: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   674: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   677: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   680: ldc 70
    //   682: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   685: aload 7
    //   687: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   690: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   693: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   696: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   699: goto -326 -> 373
    //   702: astore 6
    //   704: aload 6
    //   706: ifnull -333 -> 373
    //   709: aload 6
    //   711: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   714: ifnull +43 -> 757
    //   717: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   720: new 55	java/lang/StringBuilder
    //   723: dup
    //   724: aload 6
    //   726: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   729: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   732: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   735: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   738: ldc 70
    //   740: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   743: aload 6
    //   745: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   748: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   751: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   754: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   757: aload 6
    //   759: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   762: ifnull -389 -> 373
    //   765: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   768: new 55	java/lang/StringBuilder
    //   771: dup
    //   772: aload 6
    //   774: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   777: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   780: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   783: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   786: ldc 70
    //   788: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   791: aload 6
    //   793: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   796: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   799: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   802: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   805: goto -432 -> 373
    //   808: astore 5
    //   810: aload 5
    //   812: ifnull -439 -> 373
    //   815: aload 5
    //   817: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   820: ifnull +43 -> 863
    //   823: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   826: new 55	java/lang/StringBuilder
    //   829: dup
    //   830: aload 5
    //   832: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   835: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   838: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   841: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   844: ldc 70
    //   846: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   849: aload 5
    //   851: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   854: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   857: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   860: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   863: aload 5
    //   865: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   868: ifnull -495 -> 373
    //   871: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   874: new 55	java/lang/StringBuilder
    //   877: dup
    //   878: aload 5
    //   880: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   883: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   886: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   889: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   892: ldc 70
    //   894: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   897: aload 5
    //   899: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   902: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   905: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   908: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   911: goto -538 -> 373
    //   914: astore 4
    //   916: iconst_0
    //   917: ifeq +93 -> 1010
    //   920: aconst_null
    //   921: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   924: ifnull +41 -> 965
    //   927: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   930: new 55	java/lang/StringBuilder
    //   933: dup
    //   934: aconst_null
    //   935: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   938: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   941: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   944: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   947: ldc 70
    //   949: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   952: aconst_null
    //   953: invokevirtual 137	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   956: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   959: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   962: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   965: aconst_null
    //   966: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   969: ifnull +41 -> 1010
    //   972: getstatic 22	com/supersonicads/sdk/utils/InAppPurchaseHelper:TAG	Ljava/lang/String;
    //   975: new 55	java/lang/StringBuilder
    //   978: dup
    //   979: aconst_null
    //   980: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   983: invokevirtual 20	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   986: invokestatic 65	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   989: invokespecial 68	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   992: ldc 70
    //   994: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   997: aconst_null
    //   998: invokevirtual 138	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   1001: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1004: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1007: invokestatic 83	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1010: aload 4
    //   1012: athrow
    //   1013: astore 27
    //   1015: goto -531 -> 484
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1018	0	this	InAppPurchaseHelper
    //   0	1018	1	paramObject	Object
    //   0	1018	2	paramString	String
    //   7	471	3	localJSONArray	org.json.JSONArray
    //   914	97	4	localObject1	Object
    //   808	90	5	localInvocationTargetException	java.lang.reflect.InvocationTargetException
    //   702	90	6	localIllegalArgumentException	java.lang.IllegalArgumentException
    //   596	90	7	localIllegalAccessException	java.lang.IllegalAccessException
    //   490	90	8	localNoSuchMethodException	java.lang.NoSuchMethodException
    //   12	34	9	localClass	Class
    //   18	32	10	arrayOfClass	Class[]
    //   54	28	11	localMethod1	java.lang.reflect.Method
    //   60	25	12	arrayOfObject	Object[]
    //   89	156	13	localObject2	Object
    //   110	45	14	localMethod2	java.lang.reflect.Method
    //   131	91	15	localMethod3	java.lang.reflect.Method
    //   152	91	16	localMethod4	java.lang.reflect.Method
    //   198	205	17	localArrayList1	java.util.ArrayList
    //   219	160	18	localArrayList2	java.util.ArrayList
    //   240	151	19	localArrayList3	java.util.ArrayList
    //   263	222	21	i	int
    //   270	7	22	j	int
    //   388	84	23	str1	String
    //   400	26	24	str2	String
    //   412	22	25	str3	String
    //   445	34	26	localJSONObject	org.json.JSONObject
    //   1013	1	27	localJSONException	org.json.JSONException
    // Exception table:
    //   from	to	target	type
    //   8	262	490	java/lang/NoSuchMethodException
    //   265	272	490	java/lang/NoSuchMethodException
    //   378	447	490	java/lang/NoSuchMethodException
    //   447	484	490	java/lang/NoSuchMethodException
    //   8	262	596	java/lang/IllegalAccessException
    //   265	272	596	java/lang/IllegalAccessException
    //   378	447	596	java/lang/IllegalAccessException
    //   447	484	596	java/lang/IllegalAccessException
    //   8	262	702	java/lang/IllegalArgumentException
    //   265	272	702	java/lang/IllegalArgumentException
    //   378	447	702	java/lang/IllegalArgumentException
    //   447	484	702	java/lang/IllegalArgumentException
    //   8	262	808	java/lang/reflect/InvocationTargetException
    //   265	272	808	java/lang/reflect/InvocationTargetException
    //   378	447	808	java/lang/reflect/InvocationTargetException
    //   447	484	808	java/lang/reflect/InvocationTargetException
    //   8	262	914	finally
    //   265	272	914	finally
    //   378	447	914	finally
    //   447	484	914	finally
    //   447	484	1013	org/json/JSONException
  }
  
  public static abstract interface OnPurchasedItemsListener
  {
    public abstract void onPurchasedItemsFail();
    
    public abstract void onPurchasedItemsSuccess(String paramString);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.utils.InAppPurchaseHelper
 * JD-Core Version:    0.7.0.1
 */