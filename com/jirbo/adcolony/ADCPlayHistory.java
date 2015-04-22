package com.jirbo.adcolony;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

class ADCPlayHistory
{
  ADCController controller;
  boolean loaded = false;
  boolean modified = false;
  HashMap<Integer, Integer> play_counts = new HashMap();
  ArrayList<PlayEvent> play_events = new ArrayList();
  HashMap<String, Integer> reward_credit = new HashMap();
  
  ADCPlayHistory(ADCController paramADCController)
  {
    this.controller = paramADCController;
  }
  
  int ad_daily_play_count(int paramInt)
  {
    return ad_interval_play_count(paramInt, 86400.0D);
  }
  
  int ad_half_year_play_count(int paramInt)
  {
    return ad_interval_play_count(paramInt, 15768000.0D);
  }
  
  /* Error */
  int ad_interval_play_count(int paramInt, double paramDouble)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 57	com/jirbo/adcolony/ADCUtil:current_time	()D
    //   5: dload_2
    //   6: dsub
    //   7: dstore 5
    //   9: iconst_0
    //   10: istore 7
    //   12: iconst_m1
    //   13: aload_0
    //   14: getfield 27	com/jirbo/adcolony/ADCPlayHistory:play_events	Ljava/util/ArrayList;
    //   17: invokevirtual 61	java/util/ArrayList:size	()I
    //   20: iadd
    //   21: istore 8
    //   23: iload 8
    //   25: iflt +28 -> 53
    //   28: aload_0
    //   29: getfield 27	com/jirbo/adcolony/ADCPlayHistory:play_events	Ljava/util/ArrayList;
    //   32: iload 8
    //   34: invokevirtual 65	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   37: checkcast 67	com/jirbo/adcolony/ADCPlayHistory$PlayEvent
    //   40: getfield 71	com/jirbo/adcolony/ADCPlayHistory$PlayEvent:timestamp	D
    //   43: dstore 9
    //   45: dload 9
    //   47: dload 5
    //   49: dcmpg
    //   50: ifge +8 -> 58
    //   53: aload_0
    //   54: monitorexit
    //   55: iload 7
    //   57: ireturn
    //   58: aload_0
    //   59: getfield 27	com/jirbo/adcolony/ADCPlayHistory:play_events	Ljava/util/ArrayList;
    //   62: iload 8
    //   64: invokevirtual 65	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   67: checkcast 67	com/jirbo/adcolony/ADCPlayHistory$PlayEvent
    //   70: getfield 75	com/jirbo/adcolony/ADCPlayHistory$PlayEvent:ad_id	I
    //   73: istore 11
    //   75: iload_1
    //   76: iload 11
    //   78: if_icmpne +6 -> 84
    //   81: iinc 7 1
    //   84: iinc 8 255
    //   87: goto -64 -> 23
    //   90: astore 4
    //   92: aload_0
    //   93: monitorexit
    //   94: aload 4
    //   96: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	97	0	this	ADCPlayHistory
    //   0	97	1	paramInt	int
    //   0	97	2	paramDouble	double
    //   90	5	4	localObject	Object
    //   7	41	5	d1	double
    //   10	72	7	i	int
    //   21	64	8	j	int
    //   43	3	9	d2	double
    //   73	6	11	k	int
    // Exception table:
    //   from	to	target	type
    //   2	9	90	finally
    //   12	23	90	finally
    //   28	45	90	finally
    //   58	75	90	finally
  }
  
  int ad_monthly_play_count(int paramInt)
  {
    return ad_interval_play_count(paramInt, 2628000.0D);
  }
  
  int ad_weekly_play_count(int paramInt)
  {
    return ad_interval_play_count(paramInt, 604800.0D);
  }
  
  void add_play_event(String paramString, int paramInt)
  {
    ADCLog.dev.println("Adding play event to play history");
    this.modified = true;
    this.play_events.add(new PlayEvent(this.controller.session_manager.current_session_id, ADCUtil.current_time(), paramString, paramInt));
    Integer localInteger = (Integer)this.play_counts.get(Integer.valueOf(paramInt));
    ADCLog.dev.print("Got play count of ").print(localInteger).println(" for this ad");
    if (localInteger == null)
    {
      this.play_counts.put(Integer.valueOf(paramInt), Integer.valueOf(1));
      return;
    }
    this.play_counts.put(Integer.valueOf(paramInt), Integer.valueOf(1 + localInteger.intValue()));
  }
  
  void configure()
  {
    load();
    this.modified = false;
  }
  
  int get_reward_credit(String paramString)
  {
    Integer localInteger = (Integer)this.reward_credit.get(paramString);
    if (localInteger == null) {
      return 0;
    }
    return localInteger.intValue();
  }
  
  void load()
  {
    ADC.active = true;
    if ((this.loaded) && (((PlayEvent)this.play_events.get(-1 + this.play_events.size())).session_id == this.controller.session_manager.current_session_id)) {}
    ADCData.Table localTable1;
    do
    {
      return;
      localTable1 = ADCJSON.load_Table(new ADCDataFile("play_history_info.txt"));
    } while (localTable1 == null);
    this.play_events.clear();
    this.reward_credit = new HashMap();
    ADCData.Table localTable2 = localTable1.get_Table("reward_credit");
    for (int i = 0; i < localTable2.count(); i++)
    {
      String str2 = localTable2.key_at(i);
      int i2 = localTable2.get_Integer(str2);
      this.reward_credit.put(str2, Integer.valueOf(i2));
    }
    ADCData.List localList = localTable1.get_List("play_events");
    for (int j = 0; j < localList.count(); j++)
    {
      ADCData.Table localTable4 = localList.get_Table(j);
      double d = localTable4.get_Real("timestamp");
      String str1 = localTable4.get_String("zone_id");
      int i1 = localTable4.get_Integer("ad_id");
      if ((d != 0.0D) && (str1 != null) && (i1 != 0)) {
        this.play_events.add(new PlayEvent(null, d, str1, i1));
      }
    }
    this.play_counts = new HashMap();
    ADCData.Table localTable3 = localTable1.get_Table("play_counts");
    for (int k = 0; k < localTable3.count(); k++)
    {
      int m = Integer.parseInt(localTable3.key_at(k));
      int n = localTable3.get_Integer("" + m);
      this.play_counts.put(Integer.valueOf(m), Integer.valueOf(n));
    }
    this.loaded = true;
  }
  
  void save()
  {
    ADCData.List localList = new ADCData.List();
    ADCData.Table localTable1 = new ADCData.Table();
    ADCData.Table localTable2 = new ADCData.Table();
    double d = ADCUtil.current_time() - 2678400.0D;
    for (int i = -1 + this.play_events.size();; i--)
    {
      PlayEvent localPlayEvent;
      if (i >= 0)
      {
        localPlayEvent = (PlayEvent)this.play_events.get(i);
        if (localPlayEvent.timestamp >= d) {}
      }
      else
      {
        localTable1.set("play_events", localList);
        Iterator localIterator1 = this.play_counts.keySet().iterator();
        while (localIterator1.hasNext())
        {
          int j = ((Integer)localIterator1.next()).intValue();
          int k = ((Integer)this.play_counts.get(Integer.valueOf(j))).intValue();
          localTable2.set("" + j, k);
        }
      }
      ADCData.Table localTable4 = new ADCData.Table();
      localTable4.set("zone_id", localPlayEvent.zone_id);
      localTable4.set("ad_id", localPlayEvent.ad_id);
      localTable4.set("timestamp", localPlayEvent.timestamp);
      localList.add(localTable4);
    }
    localTable1.set("play_counts", localTable2);
    ADCData.Table localTable3 = new ADCData.Table();
    if (this.reward_credit.size() > 0)
    {
      Iterator localIterator2 = this.reward_credit.keySet().iterator();
      while (localIterator2.hasNext())
      {
        String str = (String)localIterator2.next();
        localTable3.set(str, ((Integer)this.reward_credit.get(str)).intValue());
      }
    }
    localTable1.set("reward_credit", localTable3);
    ADCLog.dev.print("Saving play history");
    ADCJSON.save(new ADCDataFile("play_history_info.txt"), localTable1);
  }
  
  void set_reward_credit(String paramString, int paramInt)
  {
    this.reward_credit.put(paramString, Integer.valueOf(paramInt));
    this.modified = true;
  }
  
  void update()
  {
    if (this.modified)
    {
      this.modified = false;
      save();
    }
  }
  
  int zone_daily_play_count(String paramString)
  {
    return zone_interval_play_count(paramString, 86400.0D);
  }
  
  /* Error */
  int zone_interval_play_count(String paramString, double paramDouble)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 57	com/jirbo/adcolony/ADCUtil:current_time	()D
    //   5: dload_2
    //   6: dsub
    //   7: dstore 5
    //   9: iconst_0
    //   10: istore 7
    //   12: iconst_m1
    //   13: aload_0
    //   14: getfield 27	com/jirbo/adcolony/ADCPlayHistory:play_events	Ljava/util/ArrayList;
    //   17: invokevirtual 61	java/util/ArrayList:size	()I
    //   20: iadd
    //   21: istore 8
    //   23: iload 8
    //   25: iflt +28 -> 53
    //   28: aload_0
    //   29: getfield 27	com/jirbo/adcolony/ADCPlayHistory:play_events	Ljava/util/ArrayList;
    //   32: iload 8
    //   34: invokevirtual 65	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   37: checkcast 67	com/jirbo/adcolony/ADCPlayHistory$PlayEvent
    //   40: getfield 71	com/jirbo/adcolony/ADCPlayHistory$PlayEvent:timestamp	D
    //   43: dstore 9
    //   45: dload 9
    //   47: dload 5
    //   49: dcmpg
    //   50: ifge +8 -> 58
    //   53: aload_0
    //   54: monitorexit
    //   55: iload 7
    //   57: ireturn
    //   58: aload_1
    //   59: aload_0
    //   60: getfield 27	com/jirbo/adcolony/ADCPlayHistory:play_events	Ljava/util/ArrayList;
    //   63: iload 8
    //   65: invokevirtual 65	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   68: checkcast 67	com/jirbo/adcolony/ADCPlayHistory$PlayEvent
    //   71: getfield 263	com/jirbo/adcolony/ADCPlayHistory$PlayEvent:zone_id	Ljava/lang/String;
    //   74: invokevirtual 292	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   77: istore 11
    //   79: iload 11
    //   81: ifeq +6 -> 87
    //   84: iinc 7 1
    //   87: iinc 8 255
    //   90: goto -67 -> 23
    //   93: astore 4
    //   95: aload_0
    //   96: monitorexit
    //   97: aload 4
    //   99: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	100	0	this	ADCPlayHistory
    //   0	100	1	paramString	String
    //   0	100	2	paramDouble	double
    //   93	5	4	localObject	Object
    //   7	41	5	d1	double
    //   10	75	7	i	int
    //   21	67	8	j	int
    //   43	3	9	d2	double
    //   77	3	11	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	9	93	finally
    //   12	23	93	finally
    //   28	45	93	finally
    //   58	79	93	finally
  }
  
  /* Error */
  int zone_session_play_count(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 40	com/jirbo/adcolony/ADCPlayHistory:controller	Lcom/jirbo/adcolony/ADCController;
    //   6: getfield 101	com/jirbo/adcolony/ADCController:session_manager	Lcom/jirbo/adcolony/ADCSessionManager;
    //   9: getfield 107	com/jirbo/adcolony/ADCSessionManager:current_session_id	Ljava/lang/String;
    //   12: astore_3
    //   13: iconst_0
    //   14: istore 4
    //   16: iconst_0
    //   17: istore 5
    //   19: iconst_m1
    //   20: aload_0
    //   21: getfield 27	com/jirbo/adcolony/ADCPlayHistory:play_events	Ljava/util/ArrayList;
    //   24: invokevirtual 61	java/util/ArrayList:size	()I
    //   27: iadd
    //   28: istore 6
    //   30: iload 6
    //   32: iflt +37 -> 69
    //   35: aload_0
    //   36: getfield 27	com/jirbo/adcolony/ADCPlayHistory:play_events	Ljava/util/ArrayList;
    //   39: iload 6
    //   41: invokevirtual 65	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   44: ifnull +25 -> 69
    //   47: aload_0
    //   48: getfield 27	com/jirbo/adcolony/ADCPlayHistory:play_events	Ljava/util/ArrayList;
    //   51: iload 6
    //   53: invokevirtual 65	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   56: checkcast 67	com/jirbo/adcolony/ADCPlayHistory$PlayEvent
    //   59: getfield 154	com/jirbo/adcolony/ADCPlayHistory$PlayEvent:session_id	Ljava/lang/String;
    //   62: astore 7
    //   64: aload 7
    //   66: ifnonnull +8 -> 74
    //   69: aload_0
    //   70: monitorexit
    //   71: iload 5
    //   73: ireturn
    //   74: aload_0
    //   75: getfield 27	com/jirbo/adcolony/ADCPlayHistory:play_events	Ljava/util/ArrayList;
    //   78: iload 6
    //   80: invokevirtual 65	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   83: checkcast 67	com/jirbo/adcolony/ADCPlayHistory$PlayEvent
    //   86: getfield 154	com/jirbo/adcolony/ADCPlayHistory$PlayEvent:session_id	Ljava/lang/String;
    //   89: aload_3
    //   90: invokevirtual 292	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   93: ifeq +41 -> 134
    //   96: iconst_1
    //   97: istore 4
    //   99: aload_0
    //   100: getfield 27	com/jirbo/adcolony/ADCPlayHistory:play_events	Ljava/util/ArrayList;
    //   103: iload 6
    //   105: invokevirtual 65	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   108: checkcast 67	com/jirbo/adcolony/ADCPlayHistory$PlayEvent
    //   111: getfield 263	com/jirbo/adcolony/ADCPlayHistory$PlayEvent:zone_id	Ljava/lang/String;
    //   114: aload_1
    //   115: invokevirtual 292	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   118: istore 8
    //   120: iload 8
    //   122: ifeq +6 -> 128
    //   125: iinc 5 1
    //   128: iinc 6 255
    //   131: goto -101 -> 30
    //   134: iload 4
    //   136: ifeq -8 -> 128
    //   139: goto -70 -> 69
    //   142: astore_2
    //   143: aload_0
    //   144: monitorexit
    //   145: aload_2
    //   146: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	147	0	this	ADCPlayHistory
    //   0	147	1	paramString	String
    //   142	4	2	localObject	Object
    //   12	78	3	str1	String
    //   14	121	4	i	int
    //   17	109	5	j	int
    //   28	101	6	k	int
    //   62	3	7	str2	String
    //   118	3	8	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	13	142	finally
    //   19	30	142	finally
    //   35	64	142	finally
    //   74	96	142	finally
    //   99	120	142	finally
  }
  
  static class PlayEvent
  {
    int ad_id;
    String session_id;
    double timestamp;
    String zone_id;
    
    PlayEvent(String paramString1, double paramDouble, String paramString2, int paramInt)
    {
      this.session_id = paramString1;
      this.timestamp = paramDouble;
      this.zone_id = paramString2;
      this.ad_id = paramInt;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCPlayHistory
 * JD-Core Version:    0.7.0.1
 */