package com.jirbo.adcolony;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ADCData
{
  static Value false_value = new FalseValue();
  static Value null_value = new NullValue();
  static Value true_value = new TrueValue();
  
  public static void main(String[] paramArrayOfString)
  {
    System.out.println("==== ADCData Test ====");
    Table localTable = new Table();
    localTable.set("one", 1);
    localTable.set("pi", 3.14D);
    localTable.set("name", "\"Abe Pralle\"");
    localTable.set("list", new List());
    localTable.set("subtable", new Table());
    localTable.get_Table("subtable").set("five", 5);
    System.out.println("LIST:" + localTable.get_List("list"));
    localTable.get_List("list").add(3);
    System.out.println(localTable);
    System.out.println(localTable.get_Integer("one"));
    System.out.println(localTable.get_Real("one"));
    System.out.println(localTable.get_Integer("pi"));
    System.out.println(localTable.get_Real("pi"));
    System.out.println(localTable.get_String("name"));
    System.out.println(localTable.get_Real("name"));
    System.out.println(localTable.get_Integer("name"));
    System.out.println(localTable.get_List("list"));
    System.out.println(localTable.get_List("list2"));
    System.out.println(localTable.get_List("subtable"));
    System.out.println(localTable.get_Table("subtable"));
    System.out.println(localTable.get_Table("subtable2"));
    System.out.println(localTable.get_Table("list"));
  }
  
  static class FalseValue
    extends ADCData.Value
  {
    boolean is_Logical()
    {
      return true;
    }
    
    void print_json(ADCWriter paramADCWriter)
    {
      paramADCWriter.print("false");
    }
    
    String to_String()
    {
      return "false";
    }
  }
  
  static class IntegerValue
    extends ADCData.Value
  {
    int value;
    
    IntegerValue(int paramInt)
    {
      this.value = paramInt;
    }
    
    boolean is_Integer()
    {
      return true;
    }
    
    void print_json(ADCWriter paramADCWriter)
    {
      paramADCWriter.print(this.value);
    }
    
    int to_Integer()
    {
      return this.value;
    }
    
    double to_Real()
    {
      return this.value;
    }
  }
  
  static class List
    extends ADCData.Value
  {
    ArrayList<ADCData.Value> data = new ArrayList();
    
    List add(double paramDouble)
    {
      add(new ADCData.RealValue(paramDouble));
      return this;
    }
    
    List add(int paramInt)
    {
      add(new ADCData.IntegerValue(paramInt));
      return this;
    }
    
    List add(ADCData.Value paramValue)
    {
      this.data.add(paramValue);
      return this;
    }
    
    List add(String paramString)
    {
      add(new ADCData.StringValue(paramString));
      return this;
    }
    
    List add(boolean paramBoolean)
    {
      if (paramBoolean) {}
      for (ADCData.Value localValue = ADCData.true_value;; localValue = ADCData.false_value)
      {
        add(localValue);
        return this;
      }
    }
    
    List add_all(List paramList)
    {
      for (int i = 0; i < paramList.count(); i++) {
        add((ADCData.Value)paramList.data.get(i));
      }
      return this;
    }
    
    void clear()
    {
      this.data.clear();
    }
    
    int count()
    {
      return this.data.size();
    }
    
    int get_Integer(int paramInt)
    {
      return get_Integer(paramInt, 0);
    }
    
    int get_Integer(int paramInt1, int paramInt2)
    {
      ADCData.Value localValue = (ADCData.Value)this.data.get(paramInt1);
      if ((localValue != null) && (localValue.is_Number())) {
        paramInt2 = localValue.to_Integer();
      }
      return paramInt2;
    }
    
    List get_List(int paramInt)
    {
      List localList = get_List(paramInt, null);
      if (localList != null) {
        return localList;
      }
      return new List();
    }
    
    List get_List(int paramInt, List paramList)
    {
      ADCData.Value localValue = (ADCData.Value)this.data.get(paramInt);
      if ((localValue != null) && (localValue.is_List())) {
        paramList = localValue.to_List();
      }
      return paramList;
    }
    
    boolean get_Logical(int paramInt)
    {
      return get_Logical(paramInt, false);
    }
    
    boolean get_Logical(int paramInt, boolean paramBoolean)
    {
      ADCData.Value localValue = (ADCData.Value)this.data.get(paramInt);
      if ((localValue != null) && ((localValue.is_Logical()) || (localValue.is_String()))) {
        paramBoolean = localValue.to_Logical();
      }
      return paramBoolean;
    }
    
    double get_Real(int paramInt)
    {
      return get_Real(paramInt, 0.0D);
    }
    
    double get_Real(int paramInt, double paramDouble)
    {
      ADCData.Value localValue = (ADCData.Value)this.data.get(paramInt);
      if ((localValue != null) && (localValue.is_Number())) {
        paramDouble = localValue.to_Real();
      }
      return paramDouble;
    }
    
    String get_String(int paramInt)
    {
      return get_String(paramInt, "");
    }
    
    String get_String(int paramInt, String paramString)
    {
      ADCData.Value localValue = (ADCData.Value)this.data.get(paramInt);
      if ((localValue != null) && (localValue.is_String())) {
        paramString = localValue.to_String();
      }
      return paramString;
    }
    
    ADCData.Table get_Table(int paramInt)
    {
      ADCData.Table localTable = get_Table(paramInt, null);
      if (localTable != null) {
        return localTable;
      }
      return new ADCData.Table();
    }
    
    ADCData.Table get_Table(int paramInt, ADCData.Table paramTable)
    {
      ADCData.Value localValue = (ADCData.Value)this.data.get(paramInt);
      if ((localValue != null) && (localValue.is_Table())) {
        paramTable = localValue.to_Table();
      }
      return paramTable;
    }
    
    boolean is_List()
    {
      return true;
    }
    
    boolean is_compact()
    {
      return (this.data.size() == 0) || ((this.data.size() == 1) && (((ADCData.Value)this.data.get(0)).is_compact()));
    }
    
    void print_json(ADCWriter paramADCWriter)
    {
      int i = this.data.size();
      if (i == 0)
      {
        paramADCWriter.print("[]");
        return;
      }
      if ((i == 1) && (((ADCData.Value)this.data.get(0)).is_compact()))
      {
        paramADCWriter.print("[");
        ((ADCData.Value)this.data.get(0)).print_json(paramADCWriter);
        paramADCWriter.print("]");
        return;
      }
      paramADCWriter.println("[");
      paramADCWriter.indent = (2 + paramADCWriter.indent);
      int j = 1;
      int k = 0;
      if (k < i)
      {
        if (j != 0) {
          j = 0;
        }
        for (;;)
        {
          ((ADCData.Value)this.data.get(k)).print_json(paramADCWriter);
          k++;
          break;
          paramADCWriter.println(',');
        }
      }
      paramADCWriter.println();
      paramADCWriter.indent = (-2 + paramADCWriter.indent);
      paramADCWriter.print("]");
    }
    
    ADCData.Value remove_last()
    {
      return (ADCData.Value)this.data.remove(-1 + this.data.size());
    }
    
    List to_List()
    {
      return this;
    }
  }
  
  static class NullValue
    extends ADCData.Value
  {
    boolean is_null()
    {
      return true;
    }
    
    void print_json(ADCWriter paramADCWriter)
    {
      paramADCWriter.print("null");
    }
    
    String to_String()
    {
      return "null";
    }
  }
  
  static class RealValue
    extends ADCData.Value
  {
    double value;
    
    RealValue(double paramDouble)
    {
      this.value = paramDouble;
    }
    
    boolean is_Real()
    {
      return true;
    }
    
    void print_json(ADCWriter paramADCWriter)
    {
      paramADCWriter.print(this.value);
    }
    
    int to_Integer()
    {
      return (int)this.value;
    }
    
    double to_Real()
    {
      return this.value;
    }
  }
  
  static class StringValue
    extends ADCData.Value
    implements Serializable
  {
    String value;
    
    StringValue(String paramString)
    {
      this.value = paramString;
    }
    
    boolean is_String()
    {
      return true;
    }
    
    void print_json(ADCWriter paramADCWriter)
    {
      print_json(paramADCWriter, this.value);
    }
    
    int to_Integer()
    {
      return (int)to_Real();
    }
    
    boolean to_Logical()
    {
      String str = this.value.toLowerCase();
      return (str.equals("true")) || (str.equals("yes"));
    }
    
    double to_Real()
    {
      try
      {
        double d = Double.parseDouble(this.value);
        return d;
      }
      catch (NumberFormatException localNumberFormatException) {}
      return 0.0D;
    }
    
    String to_String()
    {
      return this.value;
    }
  }
  
  static class Table
    extends ADCData.Value
    implements Serializable
  {
    HashMap<String, ADCData.Value> data = new HashMap();
    ArrayList<String> keys = new ArrayList();
    
    boolean contains(String paramString)
    {
      return this.data.containsKey(paramString);
    }
    
    int count()
    {
      return this.keys.size();
    }
    
    int get_Integer(String paramString)
    {
      return get_Integer(paramString, 0);
    }
    
    int get_Integer(String paramString, int paramInt)
    {
      ADCData.Value localValue = (ADCData.Value)this.data.get(paramString);
      if ((localValue != null) && (localValue.is_Number())) {
        paramInt = localValue.to_Integer();
      }
      return paramInt;
    }
    
    ADCData.List get_List(String paramString)
    {
      ADCData.List localList = get_List(paramString, null);
      if (localList != null) {
        return localList;
      }
      return new ADCData.List();
    }
    
    ADCData.List get_List(String paramString, ADCData.List paramList)
    {
      ADCData.Value localValue = (ADCData.Value)this.data.get(paramString);
      if ((localValue != null) && (localValue.is_List())) {
        paramList = localValue.to_List();
      }
      return paramList;
    }
    
    boolean get_Logical(String paramString)
    {
      return get_Logical(paramString, false);
    }
    
    boolean get_Logical(String paramString, boolean paramBoolean)
    {
      ADCData.Value localValue = (ADCData.Value)this.data.get(paramString);
      if ((localValue != null) && ((localValue.is_Logical()) || (localValue.is_String()))) {
        paramBoolean = localValue.to_Logical();
      }
      return paramBoolean;
    }
    
    double get_Real(String paramString)
    {
      return get_Real(paramString, 0.0D);
    }
    
    double get_Real(String paramString, double paramDouble)
    {
      ADCData.Value localValue = (ADCData.Value)this.data.get(paramString);
      if ((localValue != null) && (localValue.is_Number())) {
        paramDouble = localValue.to_Real();
      }
      return paramDouble;
    }
    
    String get_String(String paramString)
    {
      return get_String(paramString, "");
    }
    
    String get_String(String paramString1, String paramString2)
    {
      ADCData.Value localValue = (ADCData.Value)this.data.get(paramString1);
      if ((localValue != null) && (localValue.is_String())) {
        paramString2 = localValue.to_String();
      }
      return paramString2;
    }
    
    ArrayList<String> get_StringList(String paramString)
    {
      ArrayList localArrayList = get_StringList(paramString, null);
      if (localArrayList == null) {
        localArrayList = new ArrayList();
      }
      return localArrayList;
    }
    
    ArrayList<String> get_StringList(String paramString, ArrayList<String> paramArrayList)
    {
      ADCData.List localList = get_List(paramString);
      if (localList == null) {
        return paramArrayList;
      }
      ArrayList localArrayList = new ArrayList();
      for (int i = 0; i < localList.count(); i++)
      {
        String str = localList.get_String(i);
        if (str != null) {
          localArrayList.add(str);
        }
      }
      return localArrayList;
    }
    
    Table get_Table(String paramString)
    {
      Table localTable = get_Table(paramString, null);
      if (localTable != null) {
        return localTable;
      }
      return new Table();
    }
    
    Table get_Table(String paramString, Table paramTable)
    {
      ADCData.Value localValue = (ADCData.Value)this.data.get(paramString);
      if ((localValue != null) && (localValue.is_Table())) {
        paramTable = localValue.to_Table();
      }
      return paramTable;
    }
    
    boolean is_Table()
    {
      return true;
    }
    
    boolean is_compact()
    {
      return (this.data.size() < 0) || ((this.data.size() == 1) && (((ADCData.Value)this.data.get(this.keys.get(0))).is_compact()));
    }
    
    String key_at(int paramInt)
    {
      return (String)this.keys.get(paramInt);
    }
    
    void print_json(ADCWriter paramADCWriter)
    {
      int i = this.keys.size();
      if (i == 0)
      {
        paramADCWriter.print("{}");
        return;
      }
      if ((i == 1) && (((ADCData.Value)this.data.get(this.keys.get(0))).is_compact()))
      {
        paramADCWriter.print("{");
        String str2 = (String)this.keys.get(0);
        ADCData.Value localValue2 = (ADCData.Value)this.data.get(str2);
        print_json(paramADCWriter, str2);
        paramADCWriter.print(':');
        localValue2.print_json(paramADCWriter);
        paramADCWriter.print("}");
        return;
      }
      paramADCWriter.println("{");
      paramADCWriter.indent = (2 + paramADCWriter.indent);
      int j = 1;
      int k = 0;
      if (k < i)
      {
        if (j != 0) {
          j = 0;
        }
        for (;;)
        {
          String str1 = (String)this.keys.get(k);
          ADCData.Value localValue1 = (ADCData.Value)this.data.get(str1);
          print_json(paramADCWriter, str1);
          paramADCWriter.print(':');
          if (!localValue1.is_compact()) {
            paramADCWriter.println();
          }
          localValue1.print_json(paramADCWriter);
          k++;
          break;
          paramADCWriter.println(',');
        }
      }
      paramADCWriter.println();
      paramADCWriter.indent = (-2 + paramADCWriter.indent);
      paramADCWriter.print("}");
    }
    
    void set(String paramString, double paramDouble)
    {
      set(paramString, new ADCData.RealValue(paramDouble));
    }
    
    void set(String paramString, int paramInt)
    {
      set(paramString, new ADCData.IntegerValue(paramInt));
    }
    
    void set(String paramString, ADCData.Value paramValue)
    {
      if (!this.data.containsKey(paramString)) {
        this.keys.add(paramString);
      }
      this.data.put(paramString, paramValue);
    }
    
    void set(String paramString1, String paramString2)
    {
      set(paramString1, new ADCData.StringValue(paramString2));
    }
    
    void set(String paramString, boolean paramBoolean)
    {
      if (paramBoolean) {}
      for (ADCData.Value localValue = ADCData.true_value;; localValue = ADCData.false_value)
      {
        set(paramString, localValue);
        return;
      }
    }
    
    Table to_Table()
    {
      return this;
    }
  }
  
  static class TrueValue
    extends ADCData.Value
  {
    boolean is_Logical()
    {
      return true;
    }
    
    void print_json(ADCWriter paramADCWriter)
    {
      paramADCWriter.print("true");
    }
    
    int to_Integer()
    {
      return 1;
    }
    
    boolean to_Logical()
    {
      return true;
    }
    
    double to_Real()
    {
      return 1.0D;
    }
    
    String to_String()
    {
      return "true";
    }
  }
  
  static class Value
  {
    boolean is_Integer()
    {
      return false;
    }
    
    boolean is_List()
    {
      return false;
    }
    
    boolean is_Logical()
    {
      return false;
    }
    
    boolean is_Number()
    {
      return (is_Real()) || (is_Integer());
    }
    
    boolean is_Real()
    {
      return false;
    }
    
    boolean is_String()
    {
      return false;
    }
    
    boolean is_Table()
    {
      return false;
    }
    
    boolean is_compact()
    {
      return true;
    }
    
    boolean is_null()
    {
      return false;
    }
    
    void print_json(ADCWriter paramADCWriter) {}
    
    void print_json(ADCWriter paramADCWriter, String paramString)
    {
      paramADCWriter.print('"');
      int i = paramString.length();
      int j = 0;
      if (j < i)
      {
        char c1 = paramString.charAt(j);
        switch (c1)
        {
        default: 
          if ((c1 >= ' ') && (c1 <= '~')) {
            paramADCWriter.print(c1);
          }
          break;
        case '"': 
        case '\\': 
        case '/': 
        case '\b': 
        case '\f': 
        case '\n': 
        case '\r': 
        case '\t': 
          for (;;)
          {
            j++;
            break;
            paramADCWriter.print("\\\"");
            continue;
            paramADCWriter.print("\\\\");
            continue;
            paramADCWriter.print("\\/");
            continue;
            paramADCWriter.print("\\b");
            continue;
            paramADCWriter.print("\\f");
            continue;
            paramADCWriter.print("\\n");
            continue;
            paramADCWriter.print("\\r");
            continue;
            paramADCWriter.print("\\t");
          }
        }
        paramADCWriter.print("\\u");
        char c2 = c1;
        int m = 0;
        label215:
        int n;
        if (m < 4)
        {
          n = 0xF & c2 >> '\f';
          int k;
          c2 <<= '\004';
          if (n > 9) {
            break label257;
          }
          paramADCWriter.print(n);
        }
        for (;;)
        {
          m++;
          break label215;
          break;
          label257:
          paramADCWriter.print((char)(97 + (n - 10)));
        }
      }
      paramADCWriter.print('"');
    }
    
    public String toString()
    {
      return to_json();
    }
    
    int to_Integer()
    {
      return 0;
    }
    
    ADCData.List to_List()
    {
      return null;
    }
    
    boolean to_Logical()
    {
      return false;
    }
    
    double to_Real()
    {
      return 0.0D;
    }
    
    String to_String()
    {
      return to_json();
    }
    
    ADCData.Table to_Table()
    {
      return null;
    }
    
    String to_json()
    {
      ADCStringBuilder localADCStringBuilder = new ADCStringBuilder();
      print_json(localADCStringBuilder);
      return localADCStringBuilder.toString();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCData
 * JD-Core Version:    0.7.0.1
 */