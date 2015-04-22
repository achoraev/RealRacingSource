package com.jirbo.adcolony;

import java.io.PrintStream;

abstract class ADCWriter
{
  boolean beginning_of_line = true;
  int indent = 0;
  
  public static void main(String[] paramArrayOfString)
  {
    System.out.println("Test...");
  }
  
  void indent_if_necessary()
  {
    if (this.beginning_of_line)
    {
      this.beginning_of_line = false;
      int i = this.indent;
      for (;;)
      {
        i--;
        if (i < 0) {
          break;
        }
        write(' ');
      }
    }
  }
  
  void print(char paramChar)
  {
    if (this.beginning_of_line) {
      indent_if_necessary();
    }
    write(paramChar);
    if (paramChar == '\n') {
      this.beginning_of_line = true;
    }
  }
  
  void print(double paramDouble)
  {
    if (this.beginning_of_line) {
      indent_if_necessary();
    }
    if ((Double.isNaN(paramDouble)) || (Double.isInfinite(paramDouble))) {
      print("0.0");
    }
    long l1;
    long l3;
    for (;;)
    {
      return;
      if (paramDouble < 0.0D)
      {
        paramDouble = -paramDouble;
        write('-');
      }
      if (4 == 0)
      {
        print(Math.round(paramDouble));
        return;
      }
      l1 = Math.pow(10.0D, 4);
      long l2 = Math.round(paramDouble * l1);
      print(l2 / l1);
      write('.');
      l3 = l2 % l1;
      if (l3 != 0L) {
        break;
      }
      for (int i = 0; i < 4; i++) {
        write('0');
      }
    }
    for (long l4 = l3 * 10L; l4 < l1; l4 *= 10L) {
      write('0');
    }
    print(l3);
  }
  
  void print(long paramLong)
  {
    if (this.beginning_of_line) {
      indent_if_necessary();
    }
    if (paramLong == 0L)
    {
      write('0');
      return;
    }
    if (paramLong == -paramLong)
    {
      print("-9223372036854775808");
      return;
    }
    if (paramLong < 0L)
    {
      write('-');
      print(-paramLong);
      return;
    }
    print_recursive(paramLong);
  }
  
  void print(Object paramObject)
  {
    if (this.beginning_of_line) {
      indent_if_necessary();
    }
    if (paramObject == null)
    {
      print("null");
      return;
    }
    print(paramObject.toString());
  }
  
  void print(String paramString)
  {
    int i = paramString.length();
    for (int j = 0; j < i; j++) {
      print(paramString.charAt(j));
    }
  }
  
  void print(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      print("true");
      return;
    }
    print("false");
  }
  
  void print_recursive(long paramLong)
  {
    if (paramLong == 0L) {
      return;
    }
    print_recursive(paramLong / 10L);
    write((char)(int)(48L + paramLong % 10L));
  }
  
  void println()
  {
    print('\n');
  }
  
  void println(char paramChar)
  {
    print(paramChar);
    print('\n');
  }
  
  void println(double paramDouble)
  {
    print(paramDouble);
    print('\n');
  }
  
  void println(long paramLong)
  {
    print(paramLong);
    print('\n');
  }
  
  void println(Object paramObject)
  {
    print(paramObject);
    print('\n');
  }
  
  void println(String paramString)
  {
    print(paramString);
    print('\n');
  }
  
  void println(boolean paramBoolean)
  {
    print(paramBoolean);
    print('\n');
  }
  
  abstract void write(char paramChar);
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCWriter
 * JD-Core Version:    0.7.0.1
 */