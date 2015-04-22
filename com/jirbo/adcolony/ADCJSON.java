package com.jirbo.adcolony;

import java.io.PrintStream;

class ADCJSON
{
  static ADCStringBuilder buffer = new ADCStringBuilder();
  
  static void discard_space(ADCParseReader paramADCParseReader)
  {
    for (int i = paramADCParseReader.peek(); (paramADCParseReader.has_another()) && ((i <= 32) || (i > 126)); i = paramADCParseReader.peek()) {
      paramADCParseReader.read();
    }
  }
  
  static int hex_character_to_value(int paramInt)
  {
    if ((paramInt >= 48) && (paramInt <= 57)) {
      return paramInt - 48;
    }
    if ((paramInt >= 97) && (paramInt <= 102)) {
      return 10 + (paramInt - 97);
    }
    if ((paramInt >= 65) && (paramInt <= 70)) {
      return 10 + (paramInt - 65);
    }
    return 0;
  }
  
  static ADCData.Value load(ADCDataFile paramADCDataFile)
  {
    ADCParseReader localADCParseReader = paramADCDataFile.create_reader();
    if (localADCParseReader == null) {
      return null;
    }
    return parse_value(localADCParseReader);
  }
  
  static ADCData.List load_List(ADCDataFile paramADCDataFile)
  {
    ADCData.Value localValue = load(paramADCDataFile);
    if ((localValue == null) || (!localValue.is_List())) {
      return null;
    }
    return localValue.to_List();
  }
  
  static ADCData.Table load_Table(ADCDataFile paramADCDataFile)
  {
    ADCData.Value localValue = load(paramADCDataFile);
    if ((localValue == null) || (!localValue.is_Table())) {
      return null;
    }
    return localValue.to_Table();
  }
  
  public static void main(String[] paramArrayOfString)
  {
    System.out.println("==== ADCJSON Test ====");
    load_Table(new ADCDataFile("test.txt"));
    save(new ADCDataFile("test2.txt"), load(new ADCDataFile("test.txt")));
    save(new ADCDataFile("test3.txt"), load(new ADCDataFile("test2.txt")));
  }
  
  static ADCData.Value parse(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return parse_value(new ADCParseReader(paramString));
  }
  
  static ADCData.List parse_List(ADCParseReader paramADCParseReader)
  {
    discard_space(paramADCParseReader);
    ADCData.List localList;
    if (!paramADCParseReader.consume('[')) {
      localList = null;
    }
    do
    {
      do
      {
        return localList;
        discard_space(paramADCParseReader);
        localList = new ADCData.List();
      } while (paramADCParseReader.consume(']'));
      for (int i = 1; (i != 0) || (paramADCParseReader.consume(',')); i = 0)
      {
        localList.add(parse_value(paramADCParseReader));
        discard_space(paramADCParseReader);
      }
    } while (paramADCParseReader.consume(']'));
    return null;
  }
  
  static ADCData.List parse_List(String paramString)
  {
    ADCData.Value localValue = parse(paramString);
    if ((localValue == null) || (!localValue.is_List())) {
      return null;
    }
    return localValue.to_List();
  }
  
  static ADCData.Value parse_Number(ADCParseReader paramADCParseReader)
  {
    discard_space(paramADCParseReader);
    double d1 = 1.0D;
    if (paramADCParseReader.consume('-'))
    {
      d1 = -1.0D;
      discard_space(paramADCParseReader);
    }
    double d2 = 0.0D;
    for (int i = paramADCParseReader.peek(); (paramADCParseReader.has_another()) && (i >= 48) && (i <= 57); i = paramADCParseReader.peek())
    {
      paramADCParseReader.read();
      d2 = 10.0D * d2 + (i - 48);
    }
    boolean bool1 = paramADCParseReader.consume('.');
    int j = 0;
    if (bool1)
    {
      j = 1;
      double d5 = 0.0D;
      double d6 = 0.0D;
      for (int n = paramADCParseReader.peek(); (paramADCParseReader.has_another()) && (n >= 48) && (n <= 57); n = paramADCParseReader.peek())
      {
        paramADCParseReader.read();
        d5 = 10.0D * d5 + (n - 48);
        d6 += 1.0D;
      }
      d2 += d5 / Math.pow(10.0D, d6);
    }
    double d3;
    if ((paramADCParseReader.consume('e')) || (paramADCParseReader.consume('E')))
    {
      boolean bool2 = paramADCParseReader.consume('+');
      int k = 0;
      if (!bool2)
      {
        boolean bool3 = paramADCParseReader.consume('-');
        k = 0;
        if (bool3) {
          k = 1;
        }
      }
      d3 = 0.0D;
      for (int m = paramADCParseReader.peek(); (paramADCParseReader.has_another()) && (m >= 48) && (m <= 57); m = paramADCParseReader.peek())
      {
        paramADCParseReader.read();
        d3 = 10.0D * d3 + (m - 48);
      }
      if (k == 0) {
        break label338;
      }
    }
    double d4;
    label338:
    for (d2 /= Math.pow(10.0D, d3);; d2 *= Math.pow(10.0D, d3))
    {
      d4 = d2 * d1;
      if ((j == 0) && (d4 == (int)d4)) {
        break;
      }
      return new ADCData.RealValue(d4);
    }
    return new ADCData.IntegerValue((int)d4);
  }
  
  static String parse_String(ADCParseReader paramADCParseReader)
  {
    discard_space(paramADCParseReader);
    int i = 34;
    if (paramADCParseReader.consume('"')) {
      i = 34;
    }
    while (!paramADCParseReader.has_another())
    {
      return "";
      if (paramADCParseReader.consume('\'')) {
        i = 39;
      }
    }
    buffer.clear();
    char c1 = paramADCParseReader.read();
    if ((paramADCParseReader.has_another()) && (c1 != i))
    {
      char c2;
      if (c1 == '\\')
      {
        c2 = paramADCParseReader.read();
        if (c2 == 'b') {
          buffer.print('\b');
        }
      }
      for (;;)
      {
        c1 = paramADCParseReader.read();
        break;
        if (c2 == 'f')
        {
          buffer.print('\f');
        }
        else if (c2 == 'n')
        {
          buffer.print('\n');
        }
        else if (c2 == 'r')
        {
          buffer.print('\r');
        }
        else if (c2 == 't')
        {
          buffer.print('\t');
        }
        else if (c2 == 'u')
        {
          buffer.print(parse_hex_quad(paramADCParseReader));
        }
        else
        {
          buffer.print(c2);
          continue;
          buffer.print(c1);
        }
      }
    }
    return buffer.toString();
  }
  
  static ADCData.Table parse_Table(ADCParseReader paramADCParseReader)
  {
    discard_space(paramADCParseReader);
    ADCData.Table localTable;
    if (!paramADCParseReader.consume('{')) {
      localTable = null;
    }
    do
    {
      do
      {
        return localTable;
        discard_space(paramADCParseReader);
        localTable = new ADCData.Table();
      } while (paramADCParseReader.consume('}'));
      int i = 1;
      if ((i != 0) || (paramADCParseReader.consume(',')))
      {
        String str = parse_identifier(paramADCParseReader);
        discard_space(paramADCParseReader);
        if (!paramADCParseReader.consume(':')) {
          localTable.set(str, true);
        }
        for (;;)
        {
          discard_space(paramADCParseReader);
          i = 0;
          break;
          discard_space(paramADCParseReader);
          localTable.set(str, parse_value(paramADCParseReader));
        }
      }
    } while (paramADCParseReader.consume('}'));
    return null;
  }
  
  static ADCData.Table parse_Table(String paramString)
  {
    ADCData.Value localValue = parse(paramString);
    if ((localValue == null) || (!localValue.is_Table())) {
      return null;
    }
    return localValue.to_Table();
  }
  
  static char parse_hex_quad(ADCParseReader paramADCParseReader)
  {
    int i = 0;
    for (int j = 0; j < 4; j++) {
      if (paramADCParseReader.has_another()) {
        i = i << 4 | hex_character_to_value(paramADCParseReader.read());
      }
    }
    return (char)i;
  }
  
  static String parse_identifier(ADCParseReader paramADCParseReader)
  {
    discard_space(paramADCParseReader);
    char c = paramADCParseReader.peek();
    if ((c == '"') || (c == '\'')) {
      return parse_String(paramADCParseReader);
    }
    buffer.clear();
    int i = 0;
    while ((i == 0) && (paramADCParseReader.has_another())) {
      if (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z')) || (c == '_') || (c == '$'))
      {
        paramADCParseReader.read();
        buffer.print(c);
        c = paramADCParseReader.peek();
      }
      else
      {
        i = 1;
      }
    }
    return buffer.toString();
  }
  
  static ADCData.Value parse_value(ADCParseReader paramADCParseReader)
  {
    discard_space(paramADCParseReader);
    int i = paramADCParseReader.peek();
    if (i == 123) {
      return parse_Table(paramADCParseReader);
    }
    if (i == 91) {
      return parse_List(paramADCParseReader);
    }
    if (i == 45) {
      return parse_Number(paramADCParseReader);
    }
    if ((i >= 48) && (i <= 57)) {
      return parse_Number(paramADCParseReader);
    }
    if ((i == 34) || (i == 39))
    {
      String str1 = parse_String(paramADCParseReader);
      if (str1.length() == 0) {
        return new ADCData.StringValue("");
      }
      int j = str1.charAt(0);
      if ((j == 116) && (str1.equals("true"))) {
        return ADCData.true_value;
      }
      if ((j == 102) && (str1.equals("false"))) {
        return ADCData.false_value;
      }
      if ((j == 110) && (str1.equals("null"))) {
        return ADCData.null_value;
      }
      return new ADCData.StringValue(str1);
    }
    if (((i >= 97) && (i <= 122)) || ((i >= 65) && (i <= 90)) || (i == 95) || (i == 36))
    {
      String str2 = parse_identifier(paramADCParseReader);
      if (str2.length() == 0) {
        return new ADCData.StringValue("");
      }
      int k = str2.charAt(0);
      if ((k == 116) && (str2.equals("true"))) {
        return ADCData.true_value;
      }
      if ((k == 102) && (str2.equals("false"))) {
        return ADCData.false_value;
      }
      if ((k == 110) && (str2.equals("null"))) {
        return ADCData.null_value;
      }
      return new ADCData.StringValue(str2);
    }
    return null;
  }
  
  static void save(ADCDataFile paramADCDataFile, ADCData.List paramList)
  {
    ADCStreamWriter localADCStreamWriter = paramADCDataFile.create_writer();
    if (paramList != null)
    {
      paramList.print_json(localADCStreamWriter);
      localADCStreamWriter.println();
    }
    for (;;)
    {
      localADCStreamWriter.close();
      return;
      ADCLog.debug.println("Saving empty property list.");
      localADCStreamWriter.println("[]");
    }
  }
  
  static void save(ADCDataFile paramADCDataFile, ADCData.Table paramTable)
  {
    ADCStreamWriter localADCStreamWriter = paramADCDataFile.create_writer();
    if (paramTable != null)
    {
      paramTable.print_json(localADCStreamWriter);
      localADCStreamWriter.println();
    }
    for (;;)
    {
      localADCStreamWriter.close();
      return;
      ADCLog.debug.println("Saving empty property table.");
      localADCStreamWriter.println("{}");
    }
  }
  
  static void save(ADCDataFile paramADCDataFile, ADCData.Value paramValue)
  {
    ADCStreamWriter localADCStreamWriter = paramADCDataFile.create_writer();
    if (paramValue == null) {
      localADCStreamWriter.println("null");
    }
    for (;;)
    {
      localADCStreamWriter.close();
      return;
      paramValue.print_json(localADCStreamWriter);
      localADCStreamWriter.println();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCJSON
 * JD-Core Version:    0.7.0.1
 */