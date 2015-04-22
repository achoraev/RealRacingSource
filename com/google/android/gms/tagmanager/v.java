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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class v
  implements DataLayer.c
{
  private static final String aoQ = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", new Object[] { "datalayer", "ID", "key", "value", "expires" });
  private final Executor aoR;
  private a aoS;
  private int aoT;
  private final Context mContext;
  private ju yD;
  
  public v(Context paramContext)
  {
    this(paramContext, jw.hA(), "google_tagmanager.db", 2000, Executors.newSingleThreadExecutor());
  }
  
  v(Context paramContext, ju paramju, String paramString, int paramInt, Executor paramExecutor)
  {
    this.mContext = paramContext;
    this.yD = paramju;
    this.aoT = paramInt;
    this.aoR = paramExecutor;
    this.aoS = new a(this.mContext, paramString);
  }
  
  private SQLiteDatabase al(String paramString)
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = this.aoS.getWritableDatabase();
      return localSQLiteDatabase;
    }
    catch (SQLiteException localSQLiteException)
    {
      bh.W(paramString);
    }
    return null;
  }
  
  /* Error */
  private void b(List<b> paramList, long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 65	com/google/android/gms/tagmanager/v:yD	Lcom/google/android/gms/internal/ju;
    //   6: invokeinterface 112 1 0
    //   11: lstore 6
    //   13: aload_0
    //   14: lload 6
    //   16: invokespecial 116	com/google/android/gms/tagmanager/v:x	(J)V
    //   19: aload_0
    //   20: aload_1
    //   21: invokeinterface 122 1 0
    //   26: invokespecial 126	com/google/android/gms/tagmanager/v:fg	(I)V
    //   29: aload_0
    //   30: aload_1
    //   31: lload 6
    //   33: lload_2
    //   34: ladd
    //   35: invokespecial 129	com/google/android/gms/tagmanager/v:c	(Ljava/util/List;J)V
    //   38: aload_0
    //   39: invokespecial 132	com/google/android/gms/tagmanager/v:ol	()V
    //   42: aload_0
    //   43: monitorexit
    //   44: return
    //   45: astore 4
    //   47: aload_0
    //   48: invokespecial 132	com/google/android/gms/tagmanager/v:ol	()V
    //   51: aload 4
    //   53: athrow
    //   54: astore 5
    //   56: aload_0
    //   57: monitorexit
    //   58: aload 5
    //   60: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	61	0	this	v
    //   0	61	1	paramList	List<b>
    //   0	61	2	paramLong	long
    //   45	7	4	localObject1	Object
    //   54	5	5	localObject2	Object
    //   11	21	6	l	long
    // Exception table:
    //   from	to	target	type
    //   2	38	45	finally
    //   38	42	54	finally
    //   47	54	54	finally
  }
  
  private void c(List<b> paramList, long paramLong)
  {
    SQLiteDatabase localSQLiteDatabase = al("Error opening database for writeEntryToDatabase.");
    if (localSQLiteDatabase == null) {}
    for (;;)
    {
      return;
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        b localb = (b)localIterator.next();
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("expires", Long.valueOf(paramLong));
        localContentValues.put("key", localb.JO);
        localContentValues.put("value", localb.aoZ);
        localSQLiteDatabase.insert("datalayer", null, localContentValues);
      }
    }
  }
  
  private void cy(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = al("Error opening database for clearKeysWithPrefix.");
    if (localSQLiteDatabase == null) {
      return;
    }
    try
    {
      String[] arrayOfString = new String[2];
      arrayOfString[0] = paramString;
      arrayOfString[1] = (paramString + ".%");
      int i = localSQLiteDatabase.delete("datalayer", "key = ? OR key LIKE ?", arrayOfString);
      bh.V("Cleared " + i + " items");
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      bh.W("Error deleting entries with key prefix: " + paramString + " (" + localSQLiteException + ").");
      return;
    }
    finally
    {
      ol();
    }
  }
  
  private void fg(int paramInt)
  {
    int i = paramInt + (ok() - this.aoT);
    if (i > 0)
    {
      List localList = fh(i);
      bh.U("DataLayer store full, deleting " + localList.size() + " entries to make room.");
      i((String[])localList.toArray(new String[0]));
    }
  }
  
  /* Error */
  private List<String> fh(int paramInt)
  {
    // Byte code:
    //   0: new 250	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 251	java/util/ArrayList:<init>	()V
    //   7: astore_2
    //   8: iload_1
    //   9: ifgt +10 -> 19
    //   12: ldc 253
    //   14: invokestatic 105	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   17: aload_2
    //   18: areturn
    //   19: aload_0
    //   20: ldc 255
    //   22: invokespecial 136	com/google/android/gms/tagmanager/v:al	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   25: astore_3
    //   26: aload_3
    //   27: ifnonnull +5 -> 32
    //   30: aload_2
    //   31: areturn
    //   32: aload_3
    //   33: ldc 24
    //   35: iconst_1
    //   36: anewarray 34	java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc 26
    //   43: aastore
    //   44: aconst_null
    //   45: aconst_null
    //   46: aconst_null
    //   47: aconst_null
    //   48: ldc_w 257
    //   51: iconst_1
    //   52: anewarray 4	java/lang/Object
    //   55: dup
    //   56: iconst_0
    //   57: ldc 26
    //   59: aastore
    //   60: invokestatic 38	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   63: iload_1
    //   64: invokestatic 262	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   67: invokevirtual 266	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   70: astore 7
    //   72: aload 7
    //   74: astore 5
    //   76: aload 5
    //   78: invokeinterface 271 1 0
    //   83: ifeq +35 -> 118
    //   86: aload_2
    //   87: aload 5
    //   89: iconst_0
    //   90: invokeinterface 275 2 0
    //   95: invokestatic 278	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   98: invokeinterface 282 2 0
    //   103: pop
    //   104: aload 5
    //   106: invokeinterface 285 1 0
    //   111: istore 9
    //   113: iload 9
    //   115: ifne -29 -> 86
    //   118: aload 5
    //   120: ifnull +10 -> 130
    //   123: aload 5
    //   125: invokeinterface 288 1 0
    //   130: aload_2
    //   131: areturn
    //   132: astore 6
    //   134: aconst_null
    //   135: astore 5
    //   137: new 188	java/lang/StringBuilder
    //   140: dup
    //   141: invokespecial 189	java/lang/StringBuilder:<init>	()V
    //   144: ldc_w 290
    //   147: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: aload 6
    //   152: invokevirtual 293	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   155: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: invokevirtual 199	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   161: invokestatic 105	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   164: aload 5
    //   166: ifnull -36 -> 130
    //   169: aload 5
    //   171: invokeinterface 288 1 0
    //   176: goto -46 -> 130
    //   179: astore 4
    //   181: aconst_null
    //   182: astore 5
    //   184: aload 5
    //   186: ifnull +10 -> 196
    //   189: aload 5
    //   191: invokeinterface 288 1 0
    //   196: aload 4
    //   198: athrow
    //   199: astore 4
    //   201: goto -17 -> 184
    //   204: astore 6
    //   206: goto -69 -> 137
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	209	0	this	v
    //   0	209	1	paramInt	int
    //   7	124	2	localArrayList	ArrayList
    //   25	8	3	localSQLiteDatabase	SQLiteDatabase
    //   179	18	4	localObject1	Object
    //   199	1	4	localObject2	Object
    //   74	116	5	localCursor1	Cursor
    //   132	19	6	localSQLiteException1	SQLiteException
    //   204	1	6	localSQLiteException2	SQLiteException
    //   70	3	7	localCursor2	Cursor
    //   111	3	9	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   32	72	132	android/database/sqlite/SQLiteException
    //   32	72	179	finally
    //   76	86	199	finally
    //   86	113	199	finally
    //   137	164	199	finally
    //   76	86	204	android/database/sqlite/SQLiteException
    //   86	113	204	android/database/sqlite/SQLiteException
  }
  
  private List<DataLayer.a> h(List<b> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      b localb = (b)localIterator.next();
      localArrayList.add(new DataLayer.a(localb.JO, j(localb.aoZ)));
    }
    return localArrayList;
  }
  
  private List<b> i(List<DataLayer.a> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      DataLayer.a locala = (DataLayer.a)localIterator.next();
      localArrayList.add(new b(locala.JO, m(locala.wq)));
    }
    return localArrayList;
  }
  
  private void i(String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {}
    SQLiteDatabase localSQLiteDatabase;
    do
    {
      return;
      localSQLiteDatabase = al("Error opening database for deleteEntries.");
    } while (localSQLiteDatabase == null);
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = "ID";
    arrayOfObject[1] = TextUtils.join(",", Collections.nCopies(paramArrayOfString.length, "?"));
    String str = String.format("%s in (%s)", arrayOfObject);
    try
    {
      localSQLiteDatabase.delete("datalayer", str, paramArrayOfString);
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      bh.W("Error deleting entries " + Arrays.toString(paramArrayOfString));
    }
  }
  
  /* Error */
  private Object j(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: new 348	java/io/ByteArrayInputStream
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 351	java/io/ByteArrayInputStream:<init>	([B)V
    //   8: astore_2
    //   9: new 353	java/io/ObjectInputStream
    //   12: dup
    //   13: aload_2
    //   14: invokespecial 356	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   17: astore_3
    //   18: aload_3
    //   19: invokevirtual 359	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   22: astore 10
    //   24: aload_3
    //   25: ifnull +7 -> 32
    //   28: aload_3
    //   29: invokevirtual 360	java/io/ObjectInputStream:close	()V
    //   32: aload_2
    //   33: invokevirtual 361	java/io/ByteArrayInputStream:close	()V
    //   36: aload 10
    //   38: areturn
    //   39: astore 14
    //   41: aconst_null
    //   42: astore_3
    //   43: aload_3
    //   44: ifnull +7 -> 51
    //   47: aload_3
    //   48: invokevirtual 360	java/io/ObjectInputStream:close	()V
    //   51: aload_2
    //   52: invokevirtual 361	java/io/ByteArrayInputStream:close	()V
    //   55: aconst_null
    //   56: areturn
    //   57: astore 5
    //   59: aconst_null
    //   60: areturn
    //   61: astore 13
    //   63: aconst_null
    //   64: astore_3
    //   65: aload_3
    //   66: ifnull +7 -> 73
    //   69: aload_3
    //   70: invokevirtual 360	java/io/ObjectInputStream:close	()V
    //   73: aload_2
    //   74: invokevirtual 361	java/io/ByteArrayInputStream:close	()V
    //   77: aconst_null
    //   78: areturn
    //   79: astore 7
    //   81: aconst_null
    //   82: areturn
    //   83: astore 12
    //   85: aconst_null
    //   86: astore_3
    //   87: aload 12
    //   89: astore 8
    //   91: aload_3
    //   92: ifnull +7 -> 99
    //   95: aload_3
    //   96: invokevirtual 360	java/io/ObjectInputStream:close	()V
    //   99: aload_2
    //   100: invokevirtual 361	java/io/ByteArrayInputStream:close	()V
    //   103: aload 8
    //   105: athrow
    //   106: astore 9
    //   108: goto -5 -> 103
    //   111: astore 8
    //   113: goto -22 -> 91
    //   116: astore 6
    //   118: goto -53 -> 65
    //   121: astore 4
    //   123: goto -80 -> 43
    //   126: astore 11
    //   128: aload 10
    //   130: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	131	0	this	v
    //   0	131	1	paramArrayOfByte	byte[]
    //   8	92	2	localByteArrayInputStream	java.io.ByteArrayInputStream
    //   17	79	3	localObjectInputStream	java.io.ObjectInputStream
    //   121	1	4	localIOException1	java.io.IOException
    //   57	1	5	localIOException2	java.io.IOException
    //   116	1	6	localClassNotFoundException1	java.lang.ClassNotFoundException
    //   79	1	7	localIOException3	java.io.IOException
    //   89	15	8	localObject1	Object
    //   111	1	8	localObject2	Object
    //   106	1	9	localIOException4	java.io.IOException
    //   22	107	10	localObject3	Object
    //   126	1	11	localIOException5	java.io.IOException
    //   83	5	12	localObject4	Object
    //   61	1	13	localClassNotFoundException2	java.lang.ClassNotFoundException
    //   39	1	14	localIOException6	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   9	18	39	java/io/IOException
    //   47	51	57	java/io/IOException
    //   51	55	57	java/io/IOException
    //   9	18	61	java/lang/ClassNotFoundException
    //   69	73	79	java/io/IOException
    //   73	77	79	java/io/IOException
    //   9	18	83	finally
    //   95	99	106	java/io/IOException
    //   99	103	106	java/io/IOException
    //   18	24	111	finally
    //   18	24	116	java/lang/ClassNotFoundException
    //   18	24	121	java/io/IOException
    //   28	32	126	java/io/IOException
    //   32	36	126	java/io/IOException
  }
  
  /* Error */
  private byte[] m(Object paramObject)
  {
    // Byte code:
    //   0: new 363	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: invokespecial 364	java/io/ByteArrayOutputStream:<init>	()V
    //   7: astore_2
    //   8: new 366	java/io/ObjectOutputStream
    //   11: dup
    //   12: aload_2
    //   13: invokespecial 369	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   16: astore_3
    //   17: aload_3
    //   18: aload_1
    //   19: invokevirtual 373	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   22: aload_2
    //   23: invokevirtual 377	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   26: astore 8
    //   28: aload_3
    //   29: ifnull +7 -> 36
    //   32: aload_3
    //   33: invokevirtual 378	java/io/ObjectOutputStream:close	()V
    //   36: aload_2
    //   37: invokevirtual 379	java/io/ByteArrayOutputStream:close	()V
    //   40: aload 8
    //   42: areturn
    //   43: astore 11
    //   45: aconst_null
    //   46: astore_3
    //   47: aload_3
    //   48: ifnull +7 -> 55
    //   51: aload_3
    //   52: invokevirtual 378	java/io/ObjectOutputStream:close	()V
    //   55: aload_2
    //   56: invokevirtual 379	java/io/ByteArrayOutputStream:close	()V
    //   59: aconst_null
    //   60: areturn
    //   61: astore 5
    //   63: aconst_null
    //   64: areturn
    //   65: astore 10
    //   67: aconst_null
    //   68: astore_3
    //   69: aload 10
    //   71: astore 6
    //   73: aload_3
    //   74: ifnull +7 -> 81
    //   77: aload_3
    //   78: invokevirtual 378	java/io/ObjectOutputStream:close	()V
    //   81: aload_2
    //   82: invokevirtual 379	java/io/ByteArrayOutputStream:close	()V
    //   85: aload 6
    //   87: athrow
    //   88: astore 7
    //   90: goto -5 -> 85
    //   93: astore 6
    //   95: goto -22 -> 73
    //   98: astore 4
    //   100: goto -53 -> 47
    //   103: astore 9
    //   105: aload 8
    //   107: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	this	v
    //   0	108	1	paramObject	Object
    //   7	75	2	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    //   16	62	3	localObjectOutputStream	java.io.ObjectOutputStream
    //   98	1	4	localIOException1	java.io.IOException
    //   61	1	5	localIOException2	java.io.IOException
    //   71	15	6	localObject1	Object
    //   93	1	6	localObject2	Object
    //   88	1	7	localIOException3	java.io.IOException
    //   26	80	8	arrayOfByte	byte[]
    //   103	1	9	localIOException4	java.io.IOException
    //   65	5	10	localObject3	Object
    //   43	1	11	localIOException5	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   8	17	43	java/io/IOException
    //   51	55	61	java/io/IOException
    //   55	59	61	java/io/IOException
    //   8	17	65	finally
    //   77	81	88	java/io/IOException
    //   81	85	88	java/io/IOException
    //   17	28	93	finally
    //   17	28	98	java/io/IOException
    //   32	36	103	java/io/IOException
    //   36	40	103	java/io/IOException
  }
  
  private List<DataLayer.a> oi()
  {
    try
    {
      x(this.yD.currentTimeMillis());
      List localList = h(oj());
      return localList;
    }
    finally
    {
      ol();
    }
  }
  
  private List<b> oj()
  {
    SQLiteDatabase localSQLiteDatabase = al("Error opening database for loadSerialized.");
    localArrayList = new ArrayList();
    if (localSQLiteDatabase == null) {
      return localArrayList;
    }
    Cursor localCursor = localSQLiteDatabase.query("datalayer", new String[] { "key", "value" }, null, null, null, null, "ID", null);
    try
    {
      if (localCursor.moveToNext()) {
        localArrayList.add(new b(localCursor.getString(0), localCursor.getBlob(1)));
      }
      return localArrayList;
    }
    finally
    {
      localCursor.close();
    }
  }
  
  private int ok()
  {
    Cursor localCursor = null;
    SQLiteDatabase localSQLiteDatabase = al("Error opening database for getNumStoredEntries.");
    int i = 0;
    if (localSQLiteDatabase == null) {}
    for (;;)
    {
      return i;
      try
      {
        localCursor = localSQLiteDatabase.rawQuery("SELECT COUNT(*) from datalayer", null);
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
        bh.W("Error getting numStoredEntries");
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
  
  private void ol()
  {
    try
    {
      this.aoS.close();
      return;
    }
    catch (SQLiteException localSQLiteException) {}
  }
  
  private void x(long paramLong)
  {
    SQLiteDatabase localSQLiteDatabase = al("Error opening database for deleteOlderThan.");
    if (localSQLiteDatabase == null) {
      return;
    }
    try
    {
      String[] arrayOfString = new String[1];
      arrayOfString[0] = Long.toString(paramLong);
      int i = localSQLiteDatabase.delete("datalayer", "expires <= ?", arrayOfString);
      bh.V("Deleted " + i + " expired items");
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      bh.W("Error deleting old entries.");
    }
  }
  
  public void a(final DataLayer.c.a parama)
  {
    this.aoR.execute(new Runnable()
    {
      public void run()
      {
        parama.g(v.a(v.this));
      }
    });
  }
  
  public void a(List<DataLayer.a> paramList, final long paramLong)
  {
    final List localList = i(paramList);
    this.aoR.execute(new Runnable()
    {
      public void run()
      {
        v.a(v.this, localList, paramLong);
      }
    });
  }
  
  public void cx(final String paramString)
  {
    this.aoR.execute(new Runnable()
    {
      public void run()
      {
        v.a(v.this, paramString);
      }
    });
  }
  
  class a
    extends SQLiteOpenHelper
  {
    a(Context paramContext, String paramString)
    {
      super(paramString, null, 1);
    }
    
    private void a(SQLiteDatabase paramSQLiteDatabase)
    {
      Cursor localCursor = paramSQLiteDatabase.rawQuery("SELECT * FROM datalayer WHERE 0", null);
      HashSet localHashSet = new HashSet();
      try
      {
        String[] arrayOfString = localCursor.getColumnNames();
        for (int i = 0; i < arrayOfString.length; i++) {
          localHashSet.add(arrayOfString[i]);
        }
        localCursor.close();
        if ((!localHashSet.remove("key")) || (!localHashSet.remove("value")) || (!localHashSet.remove("ID")) || (!localHashSet.remove("expires"))) {
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
      //   3: ldc 70
      //   5: iconst_1
      //   6: anewarray 72	java/lang/String
      //   9: dup
      //   10: iconst_0
      //   11: ldc 74
      //   13: aastore
      //   14: ldc 76
      //   16: iconst_1
      //   17: anewarray 72	java/lang/String
      //   20: dup
      //   21: iconst_0
      //   22: aload_1
      //   23: aastore
      //   24: aconst_null
      //   25: aconst_null
      //   26: aconst_null
      //   27: invokevirtual 80	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   30: astore 8
      //   32: aload 8
      //   34: invokeinterface 83 1 0
      //   39: istore 10
      //   41: aload 8
      //   43: ifnull +10 -> 53
      //   46: aload 8
      //   48: invokeinterface 43 1 0
      //   53: iload 10
      //   55: ireturn
      //   56: astore 5
      //   58: aconst_null
      //   59: astore 6
      //   61: new 85	java/lang/StringBuilder
      //   64: dup
      //   65: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   68: ldc 88
      //   70: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   73: aload_1
      //   74: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   77: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   80: invokestatic 101	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
      //   83: aload 6
      //   85: ifnull +10 -> 95
      //   88: aload 6
      //   90: invokeinterface 43 1 0
      //   95: iconst_0
      //   96: ireturn
      //   97: astore 4
      //   99: aload_3
      //   100: ifnull +9 -> 109
      //   103: aload_3
      //   104: invokeinterface 43 1 0
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
      //   0	141	0	this	a
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
      try
      {
        SQLiteDatabase localSQLiteDatabase2 = super.getWritableDatabase();
        localSQLiteDatabase1 = localSQLiteDatabase2;
      }
      catch (SQLiteException localSQLiteException)
      {
        for (;;)
        {
          v.b(v.this).getDatabasePath("google_tagmanager.db").delete();
          SQLiteDatabase localSQLiteDatabase1 = null;
        }
      }
      if (localSQLiteDatabase1 == null) {
        localSQLiteDatabase1 = super.getWritableDatabase();
      }
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
        if (!a("datalayer", paramSQLiteDatabase))
        {
          paramSQLiteDatabase.execSQL(v.om());
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
  
  private static class b
  {
    final String JO;
    final byte[] aoZ;
    
    b(String paramString, byte[] paramArrayOfByte)
    {
      this.JO = paramString;
      this.aoZ = paramArrayOfByte;
    }
    
    public String toString()
    {
      return "KeyAndSerialized: key = " + this.JO + " serialized hash = " + Arrays.hashCode(this.aoZ);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.v
 * JD-Core Version:    0.7.0.1
 */