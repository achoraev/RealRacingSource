package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.android.gms.internal.ju;
import com.google.android.gms.internal.jw;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.http.impl.client.DefaultHttpClient;

class cb
  implements at
{
  private static final String AY = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", new Object[] { "gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time" });
  private final String Bb;
  private long Bd;
  private final int Be;
  private final b apW;
  private volatile ab apX;
  private final au apY;
  private final Context mContext;
  private ju yD;
  
  cb(au paramau, Context paramContext)
  {
    this(paramau, paramContext, "gtm_urls.db", 2000);
  }
  
  cb(au paramau, Context paramContext, String paramString, int paramInt)
  {
    this.mContext = paramContext.getApplicationContext();
    this.Bb = paramString;
    this.apY = paramau;
    this.yD = jw.hA();
    this.apW = new b(this.mContext, this.Bb);
    this.apX = new db(new DefaultHttpClient(), this.mContext, new a());
    this.Bd = 0L;
    this.Be = paramInt;
  }
  
  private SQLiteDatabase al(String paramString)
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = this.apW.getWritableDatabase();
      return localSQLiteDatabase;
    }
    catch (SQLiteException localSQLiteException)
    {
      bh.W(paramString);
    }
    return null;
  }
  
  private void c(long paramLong1, long paramLong2)
  {
    SQLiteDatabase localSQLiteDatabase = al("Error opening database for getNumStoredHits.");
    if (localSQLiteDatabase == null) {
      return;
    }
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("hit_first_send_time", Long.valueOf(paramLong2));
    try
    {
      String[] arrayOfString = new String[1];
      arrayOfString[0] = String.valueOf(paramLong1);
      localSQLiteDatabase.update("gtm_hits", localContentValues, "hit_id=?", arrayOfString);
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      bh.W("Error setting HIT_FIRST_DISPATCH_TIME for hitId: " + paramLong1);
      y(paramLong1);
    }
  }
  
  private void eM()
  {
    int i = 1 + (eO() - this.Be);
    if (i > 0)
    {
      List localList = F(i);
      bh.V("Store full, deleting " + localList.size() + " hits to make room.");
      b((String[])localList.toArray(new String[0]));
    }
  }
  
  private void g(long paramLong, String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = al("Error opening database for putHit");
    if (localSQLiteDatabase == null) {
      return;
    }
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("hit_time", Long.valueOf(paramLong));
    localContentValues.put("hit_url", paramString);
    localContentValues.put("hit_first_send_time", Integer.valueOf(0));
    try
    {
      localSQLiteDatabase.insert("gtm_hits", null, localContentValues);
      this.apY.z(false);
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      bh.W("Error storing hit");
    }
  }
  
  private void y(long paramLong)
  {
    String[] arrayOfString = new String[1];
    arrayOfString[0] = String.valueOf(paramLong);
    b(arrayOfString);
  }
  
  /* Error */
  List<String> F(int paramInt)
  {
    // Byte code:
    //   0: new 236	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 237	java/util/ArrayList:<init>	()V
    //   7: astore_2
    //   8: iload_1
    //   9: ifgt +10 -> 19
    //   12: ldc 239
    //   14: invokestatic 126	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   17: aload_2
    //   18: areturn
    //   19: aload_0
    //   20: ldc 241
    //   22: invokespecial 133	com/google/android/gms/tagmanager/cb:al	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   25: astore_3
    //   26: aload_3
    //   27: ifnonnull +5 -> 32
    //   30: aload_2
    //   31: areturn
    //   32: aload_3
    //   33: ldc 29
    //   35: iconst_1
    //   36: anewarray 39	java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc 31
    //   43: aastore
    //   44: aconst_null
    //   45: aconst_null
    //   46: aconst_null
    //   47: aconst_null
    //   48: ldc 243
    //   50: iconst_1
    //   51: anewarray 4	java/lang/Object
    //   54: dup
    //   55: iconst_0
    //   56: ldc 31
    //   58: aastore
    //   59: invokestatic 43	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   62: iload_1
    //   63: invokestatic 246	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   66: invokevirtual 250	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   69: astore 7
    //   71: aload 7
    //   73: astore 5
    //   75: aload 5
    //   77: invokeinterface 256 1 0
    //   82: ifeq +35 -> 117
    //   85: aload_2
    //   86: aload 5
    //   88: iconst_0
    //   89: invokeinterface 260 2 0
    //   94: invokestatic 149	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   97: invokeinterface 264 2 0
    //   102: pop
    //   103: aload 5
    //   105: invokeinterface 267 1 0
    //   110: istore 9
    //   112: iload 9
    //   114: ifne -29 -> 85
    //   117: aload 5
    //   119: ifnull +10 -> 129
    //   122: aload 5
    //   124: invokeinterface 270 1 0
    //   129: aload_2
    //   130: areturn
    //   131: astore 6
    //   133: aconst_null
    //   134: astore 5
    //   136: new 159	java/lang/StringBuilder
    //   139: dup
    //   140: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   143: ldc_w 272
    //   146: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: aload 6
    //   151: invokevirtual 275	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   154: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   160: invokestatic 126	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   163: aload 5
    //   165: ifnull -36 -> 129
    //   168: aload 5
    //   170: invokeinterface 270 1 0
    //   175: goto -46 -> 129
    //   178: astore 4
    //   180: aconst_null
    //   181: astore 5
    //   183: aload 5
    //   185: ifnull +10 -> 195
    //   188: aload 5
    //   190: invokeinterface 270 1 0
    //   195: aload 4
    //   197: athrow
    //   198: astore 4
    //   200: goto -17 -> 183
    //   203: astore 6
    //   205: goto -69 -> 136
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	208	0	this	cb
    //   0	208	1	paramInt	int
    //   7	123	2	localArrayList	java.util.ArrayList
    //   25	8	3	localSQLiteDatabase	SQLiteDatabase
    //   178	18	4	localObject1	Object
    //   198	1	4	localObject2	Object
    //   73	116	5	localCursor1	Cursor
    //   131	19	6	localSQLiteException1	SQLiteException
    //   203	1	6	localSQLiteException2	SQLiteException
    //   69	3	7	localCursor2	Cursor
    //   110	3	9	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   32	71	131	android/database/sqlite/SQLiteException
    //   32	71	178	finally
    //   75	85	198	finally
    //   85	112	198	finally
    //   136	163	198	finally
    //   75	85	203	android/database/sqlite/SQLiteException
    //   85	112	203	android/database/sqlite/SQLiteException
  }
  
  /* Error */
  public List<ap> G(int paramInt)
  {
    // Byte code:
    //   0: new 236	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 237	java/util/ArrayList:<init>	()V
    //   7: astore_2
    //   8: aload_0
    //   9: ldc_w 278
    //   12: invokespecial 133	com/google/android/gms/tagmanager/cb:al	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   15: astore_3
    //   16: aload_3
    //   17: ifnonnull +9 -> 26
    //   20: aload_2
    //   21: astore 9
    //   23: aload 9
    //   25: areturn
    //   26: aconst_null
    //   27: astore 4
    //   29: aload_3
    //   30: ldc 29
    //   32: iconst_3
    //   33: anewarray 39	java/lang/String
    //   36: dup
    //   37: iconst_0
    //   38: ldc 31
    //   40: aastore
    //   41: dup
    //   42: iconst_1
    //   43: ldc 33
    //   45: aastore
    //   46: dup
    //   47: iconst_2
    //   48: ldc 37
    //   50: aastore
    //   51: aconst_null
    //   52: aconst_null
    //   53: aconst_null
    //   54: aconst_null
    //   55: ldc 243
    //   57: iconst_1
    //   58: anewarray 4	java/lang/Object
    //   61: dup
    //   62: iconst_0
    //   63: ldc 31
    //   65: aastore
    //   66: invokestatic 43	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   69: iload_1
    //   70: invokestatic 246	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   73: invokevirtual 250	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   76: astore 10
    //   78: aload 10
    //   80: astore 11
    //   82: new 236	java/util/ArrayList
    //   85: dup
    //   86: invokespecial 237	java/util/ArrayList:<init>	()V
    //   89: astore 12
    //   91: aload 11
    //   93: invokeinterface 256 1 0
    //   98: ifeq +56 -> 154
    //   101: aload 12
    //   103: new 280	com/google/android/gms/tagmanager/ap
    //   106: dup
    //   107: aload 11
    //   109: iconst_0
    //   110: invokeinterface 260 2 0
    //   115: aload 11
    //   117: iconst_1
    //   118: invokeinterface 260 2 0
    //   123: aload 11
    //   125: iconst_2
    //   126: invokeinterface 260 2 0
    //   131: invokespecial 283	com/google/android/gms/tagmanager/ap:<init>	(JJJ)V
    //   134: invokeinterface 264 2 0
    //   139: pop
    //   140: aload 11
    //   142: invokeinterface 267 1 0
    //   147: istore 15
    //   149: iload 15
    //   151: ifne -50 -> 101
    //   154: aload 11
    //   156: ifnull +10 -> 166
    //   159: aload 11
    //   161: invokeinterface 270 1 0
    //   166: aload_3
    //   167: ldc 29
    //   169: iconst_2
    //   170: anewarray 39	java/lang/String
    //   173: dup
    //   174: iconst_0
    //   175: ldc 31
    //   177: aastore
    //   178: dup
    //   179: iconst_1
    //   180: ldc 35
    //   182: aastore
    //   183: aconst_null
    //   184: aconst_null
    //   185: aconst_null
    //   186: aconst_null
    //   187: ldc 243
    //   189: iconst_1
    //   190: anewarray 4	java/lang/Object
    //   193: dup
    //   194: iconst_0
    //   195: ldc 31
    //   197: aastore
    //   198: invokestatic 43	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   201: iload_1
    //   202: invokestatic 246	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   205: invokevirtual 250	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   208: astore 24
    //   210: aload 24
    //   212: invokeinterface 256 1 0
    //   217: ifeq +63 -> 280
    //   220: iconst_0
    //   221: istore 25
    //   223: aload 24
    //   225: checkcast 285	android/database/sqlite/SQLiteCursor
    //   228: invokevirtual 289	android/database/sqlite/SQLiteCursor:getWindow	()Landroid/database/CursorWindow;
    //   231: invokevirtual 294	android/database/CursorWindow:getNumRows	()I
    //   234: ifle +132 -> 366
    //   237: aload 12
    //   239: iload 25
    //   241: invokeinterface 298 2 0
    //   246: checkcast 280	com/google/android/gms/tagmanager/ap
    //   249: aload 24
    //   251: iconst_1
    //   252: invokeinterface 301 2 0
    //   257: invokevirtual 304	com/google/android/gms/tagmanager/ap:ak	(Ljava/lang/String;)V
    //   260: iload 25
    //   262: iconst_1
    //   263: iadd
    //   264: istore 27
    //   266: aload 24
    //   268: invokeinterface 267 1 0
    //   273: istore 28
    //   275: iload 28
    //   277: ifne +340 -> 617
    //   280: aload 24
    //   282: ifnull +10 -> 292
    //   285: aload 24
    //   287: invokeinterface 270 1 0
    //   292: aload 12
    //   294: areturn
    //   295: astore 6
    //   297: aload 6
    //   299: astore 7
    //   301: aconst_null
    //   302: astore 8
    //   304: aload_2
    //   305: astore 9
    //   307: new 159	java/lang/StringBuilder
    //   310: dup
    //   311: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   314: ldc_w 272
    //   317: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   320: aload 7
    //   322: invokevirtual 275	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   325: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   328: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   331: invokestatic 126	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   334: aload 8
    //   336: ifnull -313 -> 23
    //   339: aload 8
    //   341: invokeinterface 270 1 0
    //   346: aload 9
    //   348: areturn
    //   349: astore 5
    //   351: aload 4
    //   353: ifnull +10 -> 363
    //   356: aload 4
    //   358: invokeinterface 270 1 0
    //   363: aload 5
    //   365: athrow
    //   366: iconst_1
    //   367: anewarray 4	java/lang/Object
    //   370: astore 26
    //   372: aload 26
    //   374: iconst_0
    //   375: aload 12
    //   377: iload 25
    //   379: invokeinterface 298 2 0
    //   384: checkcast 280	com/google/android/gms/tagmanager/ap
    //   387: invokevirtual 308	com/google/android/gms/tagmanager/ap:eG	()J
    //   390: invokestatic 142	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   393: aastore
    //   394: ldc_w 310
    //   397: aload 26
    //   399: invokestatic 43	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   402: invokestatic 126	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   405: goto -145 -> 260
    //   408: astore 16
    //   410: aload 24
    //   412: astore 11
    //   414: new 159	java/lang/StringBuilder
    //   417: dup
    //   418: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   421: ldc_w 312
    //   424: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   427: aload 16
    //   429: invokevirtual 275	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   432: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   435: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   438: invokestatic 126	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   441: new 236	java/util/ArrayList
    //   444: dup
    //   445: invokespecial 237	java/util/ArrayList:<init>	()V
    //   448: astore 18
    //   450: iconst_0
    //   451: istore 19
    //   453: aload 12
    //   455: invokeinterface 316 1 0
    //   460: astore 20
    //   462: aload 20
    //   464: invokeinterface 321 1 0
    //   469: ifeq +35 -> 504
    //   472: aload 20
    //   474: invokeinterface 325 1 0
    //   479: checkcast 280	com/google/android/gms/tagmanager/ap
    //   482: astore 21
    //   484: aload 21
    //   486: invokevirtual 328	com/google/android/gms/tagmanager/ap:ou	()Ljava/lang/String;
    //   489: invokestatic 334	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   492: istore 22
    //   494: iload 22
    //   496: ifeq +26 -> 522
    //   499: iload 19
    //   501: ifeq +18 -> 519
    //   504: aload 11
    //   506: ifnull +10 -> 516
    //   509: aload 11
    //   511: invokeinterface 270 1 0
    //   516: aload 18
    //   518: areturn
    //   519: iconst_1
    //   520: istore 19
    //   522: aload 18
    //   524: aload 21
    //   526: invokeinterface 264 2 0
    //   531: pop
    //   532: goto -70 -> 462
    //   535: astore 17
    //   537: aload 11
    //   539: ifnull +10 -> 549
    //   542: aload 11
    //   544: invokeinterface 270 1 0
    //   549: aload 17
    //   551: athrow
    //   552: astore 17
    //   554: aload 24
    //   556: astore 11
    //   558: goto -21 -> 537
    //   561: astore 16
    //   563: goto -149 -> 414
    //   566: astore 5
    //   568: aload 11
    //   570: astore 4
    //   572: goto -221 -> 351
    //   575: astore 5
    //   577: aload 8
    //   579: astore 4
    //   581: goto -230 -> 351
    //   584: astore 29
    //   586: aload 29
    //   588: astore 7
    //   590: aload 11
    //   592: astore 8
    //   594: aload_2
    //   595: astore 9
    //   597: goto -290 -> 307
    //   600: astore 13
    //   602: aload 13
    //   604: astore 7
    //   606: aload 11
    //   608: astore 8
    //   610: aload 12
    //   612: astore 9
    //   614: goto -307 -> 307
    //   617: iload 27
    //   619: istore 25
    //   621: goto -398 -> 223
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	624	0	this	cb
    //   0	624	1	paramInt	int
    //   7	588	2	localArrayList1	java.util.ArrayList
    //   15	152	3	localSQLiteDatabase	SQLiteDatabase
    //   27	553	4	localObject1	Object
    //   349	15	5	localObject2	Object
    //   566	1	5	localObject3	Object
    //   575	1	5	localObject4	Object
    //   295	3	6	localSQLiteException1	SQLiteException
    //   299	306	7	localObject5	Object
    //   302	307	8	localObject6	Object
    //   21	592	9	localObject7	Object
    //   76	3	10	localCursor1	Cursor
    //   80	527	11	localObject8	Object
    //   89	522	12	localArrayList2	java.util.ArrayList
    //   600	3	13	localSQLiteException2	SQLiteException
    //   147	3	15	bool1	boolean
    //   408	20	16	localSQLiteException3	SQLiteException
    //   561	1	16	localSQLiteException4	SQLiteException
    //   535	15	17	localObject9	Object
    //   552	1	17	localObject10	Object
    //   448	75	18	localArrayList3	java.util.ArrayList
    //   451	70	19	i	int
    //   460	13	20	localIterator	java.util.Iterator
    //   482	43	21	localap	ap
    //   492	3	22	bool2	boolean
    //   208	347	24	localCursor2	Cursor
    //   221	399	25	j	int
    //   370	28	26	arrayOfObject	Object[]
    //   264	354	27	k	int
    //   273	3	28	bool3	boolean
    //   584	3	29	localSQLiteException5	SQLiteException
    // Exception table:
    //   from	to	target	type
    //   29	78	295	android/database/sqlite/SQLiteException
    //   29	78	349	finally
    //   210	220	408	android/database/sqlite/SQLiteException
    //   223	260	408	android/database/sqlite/SQLiteException
    //   266	275	408	android/database/sqlite/SQLiteException
    //   366	405	408	android/database/sqlite/SQLiteException
    //   166	210	535	finally
    //   414	450	535	finally
    //   453	462	535	finally
    //   462	494	535	finally
    //   522	532	535	finally
    //   210	220	552	finally
    //   223	260	552	finally
    //   266	275	552	finally
    //   366	405	552	finally
    //   166	210	561	android/database/sqlite/SQLiteException
    //   82	91	566	finally
    //   91	101	566	finally
    //   101	149	566	finally
    //   307	334	575	finally
    //   82	91	584	android/database/sqlite/SQLiteException
    //   91	101	600	android/database/sqlite/SQLiteException
    //   101	149	600	android/database/sqlite/SQLiteException
  }
  
  void b(String[] paramArrayOfString)
  {
    boolean bool = true;
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {}
    SQLiteDatabase localSQLiteDatabase;
    do
    {
      return;
      localSQLiteDatabase = al("Error opening database for deleteHits.");
    } while (localSQLiteDatabase == null);
    Object[] arrayOfObject = new Object[bool];
    arrayOfObject[0] = TextUtils.join(",", Collections.nCopies(paramArrayOfString.length, "?"));
    String str = String.format("HIT_ID in (%s)", arrayOfObject);
    for (;;)
    {
      try
      {
        localSQLiteDatabase.delete("gtm_hits", str, paramArrayOfString);
        au localau = this.apY;
        if (eO() == 0)
        {
          localau.z(bool);
          return;
        }
      }
      catch (SQLiteException localSQLiteException)
      {
        bh.W("Error deleting hits");
        return;
      }
      bool = false;
    }
  }
  
  public void dispatch()
  {
    bh.V("GTM Dispatch running...");
    if (!this.apX.dX()) {}
    do
    {
      return;
      List localList = G(40);
      if (localList.isEmpty())
      {
        bh.V("...nothing to dispatch");
        this.apY.z(true);
        return;
      }
      this.apX.j(localList);
    } while (oH() <= 0);
    cy.pw().dispatch();
  }
  
  int eN()
  {
    boolean bool = true;
    long l1 = this.yD.currentTimeMillis();
    if (l1 <= 86400000L + this.Bd) {}
    SQLiteDatabase localSQLiteDatabase;
    do
    {
      return 0;
      this.Bd = l1;
      localSQLiteDatabase = al("Error opening database for deleteStaleHits.");
    } while (localSQLiteDatabase == null);
    long l2 = this.yD.currentTimeMillis() - 2592000000L;
    String[] arrayOfString = new String[bool];
    arrayOfString[0] = Long.toString(l2);
    int i = localSQLiteDatabase.delete("gtm_hits", "HIT_TIME < ?", arrayOfString);
    au localau = this.apY;
    if (eO() == 0) {}
    for (;;)
    {
      localau.z(bool);
      return i;
      bool = false;
    }
  }
  
  int eO()
  {
    Cursor localCursor = null;
    SQLiteDatabase localSQLiteDatabase = al("Error opening database for getNumStoredHits.");
    int i = 0;
    if (localSQLiteDatabase == null) {}
    for (;;)
    {
      return i;
      try
      {
        localCursor = localSQLiteDatabase.rawQuery("SELECT COUNT(*) from gtm_hits", null);
        boolean bool = localCursor.moveToFirst();
        i = 0;
        if (bool)
        {
          long l = localCursor.getLong(0);
          i = (int)l;
        }
        return i;
      }
      catch (SQLiteException localSQLiteException)
      {
        bh.W("Error getting numStoredHits");
        i = 0;
        return 0;
      }
      finally
      {
        if (localCursor != null) {
          localCursor.close();
        }
      }
    }
  }
  
  public void f(long paramLong, String paramString)
  {
    eN();
    eM();
    g(paramLong, paramString);
  }
  
  /* Error */
  int oH()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: ldc 131
    //   5: invokespecial 133	com/google/android/gms/tagmanager/cb:al	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   8: astore_2
    //   9: aload_2
    //   10: ifnonnull +5 -> 15
    //   13: iconst_0
    //   14: ireturn
    //   15: aload_2
    //   16: ldc 29
    //   18: iconst_2
    //   19: anewarray 39	java/lang/String
    //   22: dup
    //   23: iconst_0
    //   24: ldc 31
    //   26: aastore
    //   27: dup
    //   28: iconst_1
    //   29: ldc 37
    //   31: aastore
    //   32: ldc_w 420
    //   35: aconst_null
    //   36: aconst_null
    //   37: aconst_null
    //   38: aconst_null
    //   39: invokevirtual 423	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   42: astore 8
    //   44: aload 8
    //   46: invokeinterface 426 1 0
    //   51: istore 10
    //   53: iload 10
    //   55: istore 7
    //   57: aload 8
    //   59: ifnull +10 -> 69
    //   62: aload 8
    //   64: invokeinterface 270 1 0
    //   69: iload 7
    //   71: ireturn
    //   72: astore 4
    //   74: aconst_null
    //   75: astore 5
    //   77: ldc_w 428
    //   80: invokestatic 126	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   83: aload 5
    //   85: ifnull +56 -> 141
    //   88: aload 5
    //   90: invokeinterface 270 1 0
    //   95: iconst_0
    //   96: istore 7
    //   98: goto -29 -> 69
    //   101: astore_3
    //   102: aload_1
    //   103: ifnull +9 -> 112
    //   106: aload_1
    //   107: invokeinterface 270 1 0
    //   112: aload_3
    //   113: athrow
    //   114: astore_3
    //   115: aload 8
    //   117: astore_1
    //   118: goto -16 -> 102
    //   121: astore 6
    //   123: aload 5
    //   125: astore_1
    //   126: aload 6
    //   128: astore_3
    //   129: goto -27 -> 102
    //   132: astore 9
    //   134: aload 8
    //   136: astore 5
    //   138: goto -61 -> 77
    //   141: iconst_0
    //   142: istore 7
    //   144: goto -75 -> 69
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	147	0	this	cb
    //   1	125	1	localObject1	Object
    //   8	8	2	localSQLiteDatabase	SQLiteDatabase
    //   101	12	3	localObject2	Object
    //   114	1	3	localObject3	Object
    //   128	1	3	localObject4	Object
    //   72	1	4	localSQLiteException1	SQLiteException
    //   75	62	5	localCursor1	Cursor
    //   121	6	6	localObject5	Object
    //   55	88	7	i	int
    //   42	93	8	localCursor2	Cursor
    //   132	1	9	localSQLiteException2	SQLiteException
    //   51	3	10	j	int
    // Exception table:
    //   from	to	target	type
    //   15	44	72	android/database/sqlite/SQLiteException
    //   15	44	101	finally
    //   44	53	114	finally
    //   77	83	121	finally
    //   44	53	132	android/database/sqlite/SQLiteException
  }
  
  class a
    implements db.a
  {
    a() {}
    
    public void a(ap paramap)
    {
      cb.a(cb.this, paramap.eG());
    }
    
    public void b(ap paramap)
    {
      cb.a(cb.this, paramap.eG());
      bh.V("Permanent failure dispatching hitId: " + paramap.eG());
    }
    
    public void c(ap paramap)
    {
      long l = paramap.ot();
      if (l == 0L) {
        cb.a(cb.this, paramap.eG(), cb.a(cb.this).currentTimeMillis());
      }
      while (l + 14400000L >= cb.a(cb.this).currentTimeMillis()) {
        return;
      }
      cb.a(cb.this, paramap.eG());
      bh.V("Giving up on failed hitId: " + paramap.eG());
    }
  }
  
  class b
    extends SQLiteOpenHelper
  {
    private boolean Bf;
    private long Bg = 0L;
    
    b(Context paramContext, String paramString)
    {
      super(paramString, null, 1);
    }
    
    private void a(SQLiteDatabase paramSQLiteDatabase)
    {
      Cursor localCursor = paramSQLiteDatabase.rawQuery("SELECT * FROM gtm_hits WHERE 0", null);
      HashSet localHashSet = new HashSet();
      try
      {
        String[] arrayOfString = localCursor.getColumnNames();
        for (int i = 0; i < arrayOfString.length; i++) {
          localHashSet.add(arrayOfString[i]);
        }
        localCursor.close();
        if ((!localHashSet.remove("hit_id")) || (!localHashSet.remove("hit_url")) || (!localHashSet.remove("hit_time")) || (!localHashSet.remove("hit_first_send_time"))) {
          throw new SQLiteException("Database column missing");
        }
      }
      finally
      {
        localCursor.close();
      }
      if (!localHashSet.isEmpty()) {
        throw new SQLiteException("Database has extra columns");
      }
    }
    
    /* Error */
    private boolean a(String paramString, SQLiteDatabase paramSQLiteDatabase)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_3
      //   2: aload_2
      //   3: ldc 76
      //   5: iconst_1
      //   6: anewarray 78	java/lang/String
      //   9: dup
      //   10: iconst_0
      //   11: ldc 80
      //   13: aastore
      //   14: ldc 82
      //   16: iconst_1
      //   17: anewarray 78	java/lang/String
      //   20: dup
      //   21: iconst_0
      //   22: aload_1
      //   23: aastore
      //   24: aconst_null
      //   25: aconst_null
      //   26: aconst_null
      //   27: invokevirtual 86	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   30: astore 8
      //   32: aload 8
      //   34: invokeinterface 89 1 0
      //   39: istore 10
      //   41: aload 8
      //   43: ifnull +10 -> 53
      //   46: aload 8
      //   48: invokeinterface 49 1 0
      //   53: iload 10
      //   55: ireturn
      //   56: astore 5
      //   58: aconst_null
      //   59: astore 6
      //   61: new 91	java/lang/StringBuilder
      //   64: dup
      //   65: invokespecial 92	java/lang/StringBuilder:<init>	()V
      //   68: ldc 94
      //   70: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   73: aload_1
      //   74: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   77: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   80: invokestatic 107	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
      //   83: aload 6
      //   85: ifnull +10 -> 95
      //   88: aload 6
      //   90: invokeinterface 49 1 0
      //   95: iconst_0
      //   96: ireturn
      //   97: astore 4
      //   99: aload_3
      //   100: ifnull +9 -> 109
      //   103: aload_3
      //   104: invokeinterface 49 1 0
      //   109: aload 4
      //   111: athrow
      //   112: astore 4
      //   114: aload 8
      //   116: astore_3
      //   117: goto -18 -> 99
      //   120: astore 7
      //   122: aload 6
      //   124: astore_3
      //   125: aload 7
      //   127: astore 4
      //   129: goto -30 -> 99
      //   132: astore 9
      //   134: aload 8
      //   136: astore 6
      //   138: goto -77 -> 61
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	141	0	this	b
      //   0	141	1	paramString	String
      //   0	141	2	paramSQLiteDatabase	SQLiteDatabase
      //   1	124	3	localObject1	Object
      //   97	13	4	localObject2	Object
      //   112	1	4	localObject3	Object
      //   127	1	4	localObject4	Object
      //   56	1	5	localSQLiteException1	SQLiteException
      //   59	78	6	localCursor1	Cursor
      //   120	6	7	localObject5	Object
      //   30	105	8	localCursor2	Cursor
      //   132	1	9	localSQLiteException2	SQLiteException
      //   39	15	10	bool	boolean
      // Exception table:
      //   from	to	target	type
      //   2	32	56	android/database/sqlite/SQLiteException
      //   2	32	97	finally
      //   32	41	112	finally
      //   61	83	120	finally
      //   32	41	132	android/database/sqlite/SQLiteException
    }
    
    public SQLiteDatabase getWritableDatabase()
    {
      if ((this.Bf) && (3600000L + this.Bg > cb.a(cb.this).currentTimeMillis())) {
        throw new SQLiteException("Database creation failed");
      }
      this.Bf = true;
      this.Bg = cb.a(cb.this).currentTimeMillis();
      try
      {
        SQLiteDatabase localSQLiteDatabase2 = super.getWritableDatabase();
        localSQLiteDatabase1 = localSQLiteDatabase2;
      }
      catch (SQLiteException localSQLiteException)
      {
        for (;;)
        {
          cb.c(cb.this).getDatabasePath(cb.b(cb.this)).delete();
          SQLiteDatabase localSQLiteDatabase1 = null;
        }
      }
      if (localSQLiteDatabase1 == null) {
        localSQLiteDatabase1 = super.getWritableDatabase();
      }
      this.Bf = false;
      return localSQLiteDatabase1;
    }
    
    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      ak.ag(paramSQLiteDatabase.getPath());
    }
    
    public void onOpen(SQLiteDatabase paramSQLiteDatabase)
    {
      Cursor localCursor;
      if (Build.VERSION.SDK_INT < 15) {
        localCursor = paramSQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
      }
      try
      {
        localCursor.moveToFirst();
        localCursor.close();
        if (!a("gtm_hits", paramSQLiteDatabase))
        {
          paramSQLiteDatabase.execSQL(cb.oI());
          return;
        }
      }
      finally
      {
        localCursor.close();
      }
      a(paramSQLiteDatabase);
    }
    
    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.cb
 * JD-Core Version:    0.7.0.1
 */