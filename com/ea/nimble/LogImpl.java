package com.ea.nimble;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class LogImpl
  extends Component
  implements ILog
{
  private static final int DEFAULT_CHECK_INTERVAL = 3600;
  private static final int DEFAULT_MESSAGE_LENGTH_LIMIT = 1000;
  private static final int DEFAULT_SIZE_LIMIT = 1024;
  private ArrayList<LogRecord> m_cache = new ArrayList();
  private BaseCore m_core;
  private File m_filePath = null;
  private DateFormat m_format = null;
  private Timer m_guardTimer = null;
  private int m_interval = 0;
  private int m_level = 0;
  private FileOutputStream m_logFileStream = null;
  private int m_messageLengthLimit;
  private int m_sizeLimit;
  
  private void clearLog()
  {
    try
    {
      this.m_logFileStream.close();
      this.m_logFileStream = new FileOutputStream(this.m_filePath, false);
      return;
    }
    catch (IOException localIOException)
    {
      Log.e("Nimble", "LOG: Can't clear log file");
    }
  }
  
  /* Error */
  private void configure()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 36	com/ea/nimble/LogImpl:m_level	I
    //   4: ifne +72 -> 76
    //   7: iconst_1
    //   8: istore_1
    //   9: aload_0
    //   10: getfield 91	com/ea/nimble/LogImpl:m_core	Lcom/ea/nimble/BaseCore;
    //   13: ldc 93
    //   15: invokevirtual 99	com/ea/nimble/BaseCore:getSettings	(Ljava/lang/String;)Ljava/util/Map;
    //   18: astore_2
    //   19: aload_2
    //   20: ifnonnull +61 -> 81
    //   23: aload_0
    //   24: aconst_null
    //   25: invokespecial 103	com/ea/nimble/LogImpl:parseLevel	(Ljava/lang/String;)I
    //   28: istore 26
    //   30: iload 26
    //   32: aload_0
    //   33: getfield 36	com/ea/nimble/LogImpl:m_level	I
    //   36: if_icmpeq +39 -> 75
    //   39: aload_0
    //   40: iload 26
    //   42: putfield 36	com/ea/nimble/LogImpl:m_level	I
    //   45: iconst_1
    //   46: anewarray 105	java/lang/Object
    //   49: astore 27
    //   51: aload 27
    //   53: iconst_0
    //   54: aload_0
    //   55: getfield 36	com/ea/nimble/LogImpl:m_level	I
    //   58: invokestatic 111	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   61: aastore
    //   62: ldc 74
    //   64: ldc 113
    //   66: aload 27
    //   68: invokestatic 119	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   71: invokestatic 122	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   74: pop
    //   75: return
    //   76: iconst_0
    //   77: istore_1
    //   78: goto -69 -> 9
    //   81: aload_0
    //   82: aload_2
    //   83: ldc 124
    //   85: invokeinterface 130 2 0
    //   90: checkcast 115	java/lang/String
    //   93: invokespecial 103	com/ea/nimble/LogImpl:parseLevel	(Ljava/lang/String;)I
    //   96: istore_3
    //   97: iload_3
    //   98: aload_0
    //   99: getfield 36	com/ea/nimble/LogImpl:m_level	I
    //   102: if_icmpeq +38 -> 140
    //   105: aload_0
    //   106: iload_3
    //   107: putfield 36	com/ea/nimble/LogImpl:m_level	I
    //   110: iconst_1
    //   111: anewarray 105	java/lang/Object
    //   114: astore 24
    //   116: aload 24
    //   118: iconst_0
    //   119: aload_0
    //   120: getfield 36	com/ea/nimble/LogImpl:m_level	I
    //   123: invokestatic 111	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   126: aastore
    //   127: ldc 74
    //   129: ldc 132
    //   131: aload 24
    //   133: invokestatic 119	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   136: invokestatic 122	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   139: pop
    //   140: aload_0
    //   141: getfield 36	com/ea/nimble/LogImpl:m_level	I
    //   144: bipush 100
    //   146: if_icmpgt +64 -> 210
    //   149: aload_0
    //   150: iconst_0
    //   151: putfield 134	com/ea/nimble/LogImpl:m_messageLengthLimit	I
    //   154: aload_2
    //   155: ldc 136
    //   157: invokeinterface 130 2 0
    //   162: checkcast 115	java/lang/String
    //   165: astore 6
    //   167: aload 6
    //   169: invokestatic 142	com/ea/nimble/Utility:validString	(Ljava/lang/String;)Z
    //   172: ifne +104 -> 276
    //   175: iload_1
    //   176: ifne +10 -> 186
    //   179: aload_0
    //   180: getfield 38	com/ea/nimble/LogImpl:m_filePath	Ljava/io/File;
    //   183: ifnull -108 -> 75
    //   186: aload_0
    //   187: aconst_null
    //   188: putfield 38	com/ea/nimble/LogImpl:m_filePath	Ljava/io/File;
    //   191: aload_0
    //   192: aconst_null
    //   193: putfield 40	com/ea/nimble/LogImpl:m_logFileStream	Ljava/io/FileOutputStream;
    //   196: aload_0
    //   197: iconst_0
    //   198: putfield 46	com/ea/nimble/LogImpl:m_interval	I
    //   201: ldc 74
    //   203: ldc 144
    //   205: invokestatic 122	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   208: pop
    //   209: return
    //   210: aload_2
    //   211: ldc 146
    //   213: invokeinterface 130 2 0
    //   218: checkcast 115	java/lang/String
    //   221: astore 4
    //   223: aload 4
    //   225: ifnonnull +13 -> 238
    //   228: aload_0
    //   229: sipush 1000
    //   232: putfield 134	com/ea/nimble/LogImpl:m_messageLengthLimit	I
    //   235: goto -81 -> 154
    //   238: aload_0
    //   239: aload 4
    //   241: invokestatic 149	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   244: putfield 134	com/ea/nimble/LogImpl:m_messageLengthLimit	I
    //   247: aload_0
    //   248: getfield 134	com/ea/nimble/LogImpl:m_messageLengthLimit	I
    //   251: ifge -97 -> 154
    //   254: aload_0
    //   255: sipush 1000
    //   258: putfield 134	com/ea/nimble/LogImpl:m_messageLengthLimit	I
    //   261: goto -107 -> 154
    //   264: astore 5
    //   266: aload_0
    //   267: sipush 1000
    //   270: putfield 134	com/ea/nimble/LogImpl:m_messageLengthLimit	I
    //   273: goto -119 -> 154
    //   276: aload_2
    //   277: ldc 151
    //   279: invokeinterface 130 2 0
    //   284: checkcast 115	java/lang/String
    //   287: astore 7
    //   289: new 153	java/lang/StringBuilder
    //   292: dup
    //   293: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   296: invokestatic 160	com/ea/nimble/ApplicationEnvironment:getComponent	()Lcom/ea/nimble/IApplicationEnvironment;
    //   299: invokeinterface 166 1 0
    //   304: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   307: getstatic 176	java/io/File:separator	Ljava/lang/String;
    //   310: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   313: aload 6
    //   315: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   318: invokevirtual 179	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   321: astore 8
    //   323: aload 7
    //   325: invokestatic 142	com/ea/nimble/Utility:validString	(Ljava/lang/String;)Z
    //   328: ifeq +125 -> 453
    //   331: aload 7
    //   333: ldc 181
    //   335: invokevirtual 184	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   338: ifeq +115 -> 453
    //   341: invokestatic 189	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   344: ldc 191
    //   346: invokevirtual 195	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   349: ifeq +104 -> 453
    //   352: invokestatic 199	com/ea/nimble/ApplicationEnvironment:getCurrentActivity	()Landroid/app/Activity;
    //   355: invokevirtual 203	java/lang/Object:getClass	()Ljava/lang/Class;
    //   358: invokevirtual 209	java/lang/Class:getPackage	()Ljava/lang/Package;
    //   361: invokevirtual 214	java/lang/Package:getName	()Ljava/lang/String;
    //   364: astore 19
    //   366: invokestatic 199	com/ea/nimble/ApplicationEnvironment:getCurrentActivity	()Landroid/app/Activity;
    //   369: invokevirtual 220	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   372: invokestatic 199	com/ea/nimble/ApplicationEnvironment:getCurrentActivity	()Landroid/app/Activity;
    //   375: invokevirtual 223	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   378: iconst_0
    //   379: invokevirtual 229	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   382: getfield 234	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   385: astore 19
    //   387: new 172	java/io/File
    //   390: dup
    //   391: invokestatic 238	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   394: aload 19
    //   396: invokespecial 241	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   399: astore 21
    //   401: aload 21
    //   403: invokevirtual 245	java/io/File:exists	()Z
    //   406: istore 22
    //   408: iload 22
    //   410: ifne +10 -> 420
    //   413: aload 21
    //   415: invokevirtual 248	java/io/File:mkdir	()Z
    //   418: istore 22
    //   420: iload 22
    //   422: ifeq +31 -> 453
    //   425: new 153	java/lang/StringBuilder
    //   428: dup
    //   429: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   432: aload 21
    //   434: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   437: getstatic 176	java/io/File:separator	Ljava/lang/String;
    //   440: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   443: aload 6
    //   445: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   448: invokevirtual 179	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   451: astore 8
    //   453: new 172	java/io/File
    //   456: dup
    //   457: aload 8
    //   459: invokespecial 254	java/io/File:<init>	(Ljava/lang/String;)V
    //   462: astore 9
    //   464: aload 9
    //   466: aload_0
    //   467: getfield 38	com/ea/nimble/LogImpl:m_filePath	Ljava/io/File;
    //   470: if_acmpeq +57 -> 527
    //   473: aload_0
    //   474: aload 9
    //   476: putfield 38	com/ea/nimble/LogImpl:m_filePath	Ljava/io/File;
    //   479: aload_0
    //   480: new 66	java/io/FileOutputStream
    //   483: dup
    //   484: aload_0
    //   485: getfield 38	com/ea/nimble/LogImpl:m_filePath	Ljava/io/File;
    //   488: iconst_1
    //   489: invokespecial 72	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   492: putfield 40	com/ea/nimble/LogImpl:m_logFileStream	Ljava/io/FileOutputStream;
    //   495: ldc 74
    //   497: new 153	java/lang/StringBuilder
    //   500: dup
    //   501: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   504: ldc_w 256
    //   507: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   510: aload_0
    //   511: getfield 38	com/ea/nimble/LogImpl:m_filePath	Ljava/io/File;
    //   514: invokevirtual 257	java/io/File:toString	()Ljava/lang/String;
    //   517: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   520: invokevirtual 179	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   523: invokestatic 260	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   526: pop
    //   527: aload_2
    //   528: ldc_w 262
    //   531: invokeinterface 130 2 0
    //   536: checkcast 115	java/lang/String
    //   539: astore 10
    //   541: aload 10
    //   543: ifnull +179 -> 722
    //   546: aload 10
    //   548: invokevirtual 266	java/lang/String:length	()I
    //   551: ifle +171 -> 722
    //   554: new 268	java/text/SimpleDateFormat
    //   557: dup
    //   558: aload 10
    //   560: invokestatic 274	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   563: invokespecial 277	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   566: astore 15
    //   568: aload_0
    //   569: aload 15
    //   571: putfield 42	com/ea/nimble/LogImpl:m_format	Ljava/text/DateFormat;
    //   574: aload_0
    //   575: aload_2
    //   576: ldc_w 279
    //   579: invokeinterface 130 2 0
    //   584: checkcast 115	java/lang/String
    //   587: invokestatic 149	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   590: putfield 46	com/ea/nimble/LogImpl:m_interval	I
    //   593: aload_0
    //   594: getfield 46	com/ea/nimble/LogImpl:m_interval	I
    //   597: ifgt +10 -> 607
    //   600: aload_0
    //   601: sipush 3600
    //   604: putfield 46	com/ea/nimble/LogImpl:m_interval	I
    //   607: aload_0
    //   608: aload_2
    //   609: ldc_w 281
    //   612: invokeinterface 130 2 0
    //   617: checkcast 115	java/lang/String
    //   620: invokestatic 149	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   623: putfield 57	com/ea/nimble/LogImpl:m_sizeLimit	I
    //   626: aload_0
    //   627: getfield 57	com/ea/nimble/LogImpl:m_sizeLimit	I
    //   630: ifgt +10 -> 640
    //   633: aload_0
    //   634: sipush 1024
    //   637: putfield 57	com/ea/nimble/LogImpl:m_sizeLimit	I
    //   640: new 283	com/ea/nimble/LogImpl$GuardTask
    //   643: dup
    //   644: aload_0
    //   645: aconst_null
    //   646: invokespecial 286	com/ea/nimble/LogImpl$GuardTask:<init>	(Lcom/ea/nimble/LogImpl;Lcom/ea/nimble/LogImpl$1;)V
    //   649: astore 13
    //   651: aload 13
    //   653: invokevirtual 289	com/ea/nimble/LogImpl$GuardTask:run	()V
    //   656: new 291	com/ea/nimble/Timer
    //   659: dup
    //   660: aload 13
    //   662: invokespecial 294	com/ea/nimble/Timer:<init>	(Ljava/lang/Runnable;)V
    //   665: astore 14
    //   667: aload_0
    //   668: aload 14
    //   670: putfield 44	com/ea/nimble/LogImpl:m_guardTimer	Lcom/ea/nimble/Timer;
    //   673: aload_0
    //   674: getfield 44	com/ea/nimble/LogImpl:m_guardTimer	Lcom/ea/nimble/Timer;
    //   677: aload_0
    //   678: getfield 46	com/ea/nimble/LogImpl:m_interval	I
    //   681: i2d
    //   682: iconst_1
    //   683: invokevirtual 298	com/ea/nimble/Timer:schedule	(DZ)V
    //   686: return
    //   687: astore 16
    //   689: ldc 74
    //   691: new 153	java/lang/StringBuilder
    //   694: dup
    //   695: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   698: ldc_w 300
    //   701: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   704: aload 8
    //   706: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   709: invokevirtual 179	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   712: invokestatic 82	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   715: pop
    //   716: aload_0
    //   717: aconst_null
    //   718: putfield 38	com/ea/nimble/LogImpl:m_filePath	Ljava/io/File;
    //   721: return
    //   722: aload_0
    //   723: new 268	java/text/SimpleDateFormat
    //   726: dup
    //   727: ldc_w 302
    //   730: invokestatic 274	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   733: invokespecial 277	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   736: putfield 42	com/ea/nimble/LogImpl:m_format	Ljava/text/DateFormat;
    //   739: goto -165 -> 574
    //   742: astore 11
    //   744: aload_0
    //   745: sipush 3600
    //   748: putfield 46	com/ea/nimble/LogImpl:m_interval	I
    //   751: goto -144 -> 607
    //   754: astore 12
    //   756: aload_0
    //   757: sipush 1024
    //   760: putfield 57	com/ea/nimble/LogImpl:m_sizeLimit	I
    //   763: goto -123 -> 640
    //   766: astore 20
    //   768: goto -381 -> 387
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	771	0	this	LogImpl
    //   8	168	1	i	int
    //   18	591	2	localMap	java.util.Map
    //   96	11	3	j	int
    //   221	19	4	str1	String
    //   264	1	5	localNumberFormatException1	NumberFormatException
    //   165	279	6	str2	String
    //   287	45	7	str3	String
    //   321	384	8	str4	String
    //   462	13	9	localFile1	File
    //   539	20	10	str5	String
    //   742	1	11	localNumberFormatException2	NumberFormatException
    //   754	1	12	localNumberFormatException3	NumberFormatException
    //   649	12	13	localGuardTask	GuardTask
    //   665	4	14	localTimer	Timer
    //   566	4	15	localSimpleDateFormat	java.text.SimpleDateFormat
    //   687	1	16	localFileNotFoundException	java.io.FileNotFoundException
    //   364	31	19	str6	String
    //   766	1	20	localException	java.lang.Exception
    //   399	34	21	localFile2	File
    //   406	15	22	bool	boolean
    //   114	18	24	arrayOfObject1	Object[]
    //   28	13	26	k	int
    //   49	18	27	arrayOfObject2	Object[]
    // Exception table:
    //   from	to	target	type
    //   238	261	264	java/lang/NumberFormatException
    //   479	495	687	java/io/FileNotFoundException
    //   574	607	742	java/lang/NumberFormatException
    //   607	640	754	java/lang/NumberFormatException
    //   366	387	766	java/lang/Exception
  }
  
  private void flushCache()
  {
    Iterator localIterator = this.m_cache.iterator();
    while (localIterator.hasNext())
    {
      LogRecord localLogRecord = (LogRecord)localIterator.next();
      writeLine(localLogRecord.level, localLogRecord.message);
    }
    this.m_cache = null;
  }
  
  private String formatLine(String paramString1, String paramString2)
  {
    String str = paramString1 + ">" + paramString2;
    int i = str.length();
    if ((i > this.m_messageLengthLimit) && (this.m_messageLengthLimit != 0))
    {
      int j = i - this.m_messageLengthLimit;
      StringBuilder localStringBuilder = new StringBuilder().append(str.substring(0, this.m_messageLengthLimit));
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(j);
      str = String.format("... and %d chars more", arrayOfObject);
    }
    return str;
  }
  
  private void outputMessageToFile(String paramString)
  {
    String str1 = System.getProperty("line.separator");
    if (this.m_logFileStream != null) {}
    try
    {
      String str2 = this.m_format.format(new Date()) + paramString + str1;
      this.m_logFileStream.write(str2.getBytes());
      this.m_logFileStream.flush();
      return;
    }
    catch (IOException localIOException)
    {
      Log.e("Nimble", "Error writing to log file: " + localIOException.toString());
    }
  }
  
  private int parseLevel(String paramString)
  {
    try
    {
      if (Utility.validString(paramString))
      {
        int i = Integer.parseInt(paramString);
        if (i != 0) {
          return i;
        }
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      if (paramString.equalsIgnoreCase("verbose")) {
        return 100;
      }
      if (paramString.equalsIgnoreCase("debug")) {
        return 200;
      }
      if (paramString.equalsIgnoreCase("info")) {
        return 300;
      }
      if (paramString.equalsIgnoreCase("warn")) {
        return 400;
      }
      if (paramString.equalsIgnoreCase("error")) {
        return 500;
      }
      if (paramString.equalsIgnoreCase("fatal")) {
        return 600;
      }
      if ((this.m_core.getConfiguration() == NimbleConfiguration.INTEGRATION) || (this.m_core.getConfiguration() == NimbleConfiguration.STAGE)) {
        return 100;
      }
    }
    return 400;
  }
  
  private void write(int paramInt, String paramString1, String paramString2)
  {
    if (Utility.validString(paramString1)) {}
    for (String str = paramString1 + "> " + paramString2; this.m_cache != null; str = " " + paramString2)
    {
      LogRecord localLogRecord = new LogRecord(null);
      localLogRecord.level = paramInt;
      localLogRecord.message = str;
      this.m_cache.add(localLogRecord);
      return;
    }
    writeLine(paramInt, str);
  }
  
  private void writeLine(int paramInt, String paramString)
  {
    String str;
    if (paramInt == 100)
    {
      str = formatLine("NIM_VERBOSE", paramString);
      Log.v("Nimble", str);
      outputMessageToFile(str);
    }
    while ((paramInt >= 600) && ((this.m_core.getConfiguration() == NimbleConfiguration.INTEGRATION) || (this.m_core.getConfiguration() == NimbleConfiguration.STAGE)))
    {
      throw new AssertionError(str);
      if (paramInt == 200)
      {
        str = formatLine("NIM_DEBUG", paramString);
        Log.d("Nimble", str);
        outputMessageToFile(str);
      }
      else if (paramInt == 300)
      {
        str = formatLine("NIM_INFO", paramString);
        Log.i("Nimble", str);
        outputMessageToFile(str);
      }
      else if (paramInt == 400)
      {
        str = formatLine("NIM_WARN", paramString);
        Log.w("Nimble", str);
        outputMessageToFile(str);
      }
      else if (paramInt == 500)
      {
        str = formatLine("NIM_ERROR", paramString);
        Log.e("Nimble", str);
        outputMessageToFile(str);
      }
      else if (paramInt == 600)
      {
        str = formatLine("NIM_FATAL", paramString);
        Log.wtf("Nimble", str);
        outputMessageToFile(str);
      }
      else
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramInt);
        str = formatLine(String.format("NIM(%d)", arrayOfObject), paramString);
        Log.wtf("Nimble", str);
        outputMessageToFile(str);
      }
    }
  }
  
  protected void connectToCore(BaseCore paramBaseCore)
  {
    this.m_core = paramBaseCore;
    configure();
    flushCache();
  }
  
  protected void disconnectFromCore()
  {
    this.m_core = null;
  }
  
  public String getComponentId()
  {
    return "com.ea.nimble.NimbleLog";
  }
  
  public String getLogFilePath()
  {
    return this.m_filePath.toString();
  }
  
  public int getThresholdLevel()
  {
    return this.m_level;
  }
  
  public void resume()
  {
    if (this.m_guardTimer != null)
    {
      this.m_guardTimer.fire();
      this.m_guardTimer.resume();
    }
  }
  
  public void setThresholdLevel(int paramInt)
  {
    this.m_level = paramInt;
  }
  
  public void setup()
  {
    configure();
  }
  
  public void suspend()
  {
    if (this.m_guardTimer != null) {
      this.m_guardTimer.pause();
    }
  }
  
  public void teardown()
  {
    if (this.m_guardTimer != null)
    {
      this.m_guardTimer.cancel();
      this.m_guardTimer = null;
    }
    if (this.m_logFileStream != null) {}
    try
    {
      this.m_logFileStream.close();
      this.m_logFileStream = null;
      return;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        Log.e("Nimble", "LOG: Can't close log file");
      }
    }
  }
  
  public void writeWithSource(int paramInt, Object paramObject, String paramString, Object... paramVarArgs)
  {
    if ((paramInt < this.m_level) || (!Utility.validString(paramString))) {
      return;
    }
    if (paramVarArgs.length > 0) {}
    for (String str = String.format(paramString, paramVarArgs); (paramObject instanceof LogSource); str = paramString)
    {
      write(paramInt, ((LogSource)paramObject).getLogSourceTitle(), str);
      return;
    }
    if ((this.m_level <= 100) && (paramObject != null))
    {
      write(paramInt, paramObject.getClass().getName(), str);
      return;
    }
    write(paramInt, null, str);
  }
  
  public void writeWithTitle(int paramInt, String paramString1, String paramString2, Object... paramVarArgs)
  {
    if ((paramInt < this.m_level) || (!Utility.validString(paramString2))) {
      return;
    }
    if (paramVarArgs.length > 0) {}
    for (String str = String.format(paramString2, paramVarArgs);; str = paramString2)
    {
      write(paramInt, paramString1, str);
      return;
    }
  }
  
  private class GuardTask
    implements Runnable
  {
    private GuardTask() {}
    
    public void run()
    {
      if ((LogImpl.this.m_filePath != null) && (LogImpl.this.m_filePath.length() > 1024 * LogImpl.this.m_sizeLimit)) {
        LogImpl.this.clearLog();
      }
    }
  }
  
  private class LogRecord
  {
    public int level;
    public String message;
    
    private LogRecord() {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.LogImpl
 * JD-Core Version:    0.7.0.1
 */