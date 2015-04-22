package com.jirbo.adcolony;

import java.io.PrintStream;

class ADCStringBuilder
  extends ADCWriter
{
  StringBuilder buffer = new StringBuilder();
  
  public static void main(String[] paramArrayOfString)
  {
    ADCStringBuilder localADCStringBuilder = new ADCStringBuilder();
    localADCStringBuilder.println("A king who was mad at the time");
    localADCStringBuilder.println("Declared limerick writing a crime");
    localADCStringBuilder.indent = (2 + localADCStringBuilder.indent);
    localADCStringBuilder.println("So late in the night");
    localADCStringBuilder.println("All the poets would write");
    localADCStringBuilder.indent = (-2 + localADCStringBuilder.indent);
    localADCStringBuilder.println("Verses without any rhyme or meter");
    localADCStringBuilder.println();
    localADCStringBuilder.indent = (4 + localADCStringBuilder.indent);
    localADCStringBuilder.println("David\nGerrold");
    localADCStringBuilder.indent = (2 + localADCStringBuilder.indent);
    localADCStringBuilder.println(4.0D);
    localADCStringBuilder.indent = (2 + localADCStringBuilder.indent);
    localADCStringBuilder.println(0.0D);
    localADCStringBuilder.indent = (2 + localADCStringBuilder.indent);
    localADCStringBuilder.println(-100023.0D);
    localADCStringBuilder.indent = (2 + localADCStringBuilder.indent);
    localADCStringBuilder.println(-6L);
    localADCStringBuilder.indent = (2 + localADCStringBuilder.indent);
    localADCStringBuilder.println(0L);
    localADCStringBuilder.indent = (2 + localADCStringBuilder.indent);
    localADCStringBuilder.println(234L);
    localADCStringBuilder.indent = (2 + localADCStringBuilder.indent);
    localADCStringBuilder.println(-9223372036854775808L);
    localADCStringBuilder.indent = (2 + localADCStringBuilder.indent);
    localADCStringBuilder.println(true);
    localADCStringBuilder.indent = (2 + localADCStringBuilder.indent);
    localADCStringBuilder.println(false);
    localADCStringBuilder.indent = (2 + localADCStringBuilder.indent);
    System.out.println(localADCStringBuilder);
  }
  
  void clear()
  {
    this.buffer.setLength(0);
    this.indent = 0;
  }
  
  public String toString()
  {
    return this.buffer.toString();
  }
  
  void write(char paramChar)
  {
    this.buffer.append(paramChar);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCStringBuilder
 * JD-Core Version:    0.7.0.1
 */