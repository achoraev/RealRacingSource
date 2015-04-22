package com.jirbo.adcolony;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class ADCStreamWriter
  extends ADCWriter
{
  static final int buffer_size = 1024;
  byte[] buffer = new byte[1024];
  int dx;
  String filepath;
  OutputStream out;
  int position = 0;
  int x;
  
  ADCStreamWriter(String paramString)
  {
    this.filepath = paramString;
    if (ADC.log_level != 0)
    {
      this.dx = 23;
      this.x = this.dx;
    }
    try
    {
      if ((ADC.controller != null) && (ADC.controller.storage != null)) {
        ADC.controller.storage.validate_storage_paths();
      }
      this.out = new FileOutputStream(paramString);
      return;
    }
    catch (IOException localIOException)
    {
      on_error(localIOException);
    }
  }
  
  ADCStreamWriter(String paramString, OutputStream paramOutputStream)
  {
    this.filepath = paramString;
    this.out = paramOutputStream;
  }
  
  public static void main(String[] paramArrayOfString)
  {
    ADCStreamWriter localADCStreamWriter = new ADCStreamWriter("test.txt");
    localADCStreamWriter.println("A king who was mad at the time");
    localADCStreamWriter.println("Declared limerick writing a crime");
    localADCStreamWriter.indent = (2 + localADCStreamWriter.indent);
    localADCStreamWriter.println("So late in the night");
    localADCStreamWriter.println("All the poets would write");
    localADCStreamWriter.indent = (-2 + localADCStreamWriter.indent);
    localADCStreamWriter.println("Verses without any rhyme or meter");
    localADCStreamWriter.println();
    localADCStreamWriter.indent = (4 + localADCStreamWriter.indent);
    localADCStreamWriter.println("David\nGerrold");
    localADCStreamWriter.indent = (2 + localADCStreamWriter.indent);
    localADCStreamWriter.println(4.0D);
    localADCStreamWriter.indent = (2 + localADCStreamWriter.indent);
    localADCStreamWriter.println(0.0D);
    localADCStreamWriter.indent = (2 + localADCStreamWriter.indent);
    localADCStreamWriter.println(-100023.0D);
    localADCStreamWriter.indent = (2 + localADCStreamWriter.indent);
    localADCStreamWriter.println(-6L);
    localADCStreamWriter.indent = (2 + localADCStreamWriter.indent);
    localADCStreamWriter.println(0L);
    localADCStreamWriter.indent = (2 + localADCStreamWriter.indent);
    localADCStreamWriter.println(234L);
    localADCStreamWriter.indent = (2 + localADCStreamWriter.indent);
    localADCStreamWriter.println(-9223372036854775808L);
    localADCStreamWriter.indent = (2 + localADCStreamWriter.indent);
    localADCStreamWriter.println(true);
    localADCStreamWriter.indent = (2 + localADCStreamWriter.indent);
    localADCStreamWriter.println(false);
    localADCStreamWriter.indent = (2 + localADCStreamWriter.indent);
    localADCStreamWriter.close();
  }
  
  void close()
  {
    flush();
    try
    {
      if (this.out != null)
      {
        this.out.close();
        this.out = null;
      }
      return;
    }
    catch (IOException localIOException)
    {
      this.out = null;
      on_error(localIOException);
    }
  }
  
  void flush()
  {
    if ((this.position > 0) && (this.out != null)) {}
    try
    {
      this.out.write(this.buffer, 0, this.position);
      this.position = 0;
      this.out.flush();
      return;
    }
    catch (IOException localIOException)
    {
      this.position = 0;
      on_error(localIOException);
    }
  }
  
  void on_error(IOException paramIOException)
  {
    ADCLog.error.print("Error writing \"").print(this.filepath).println("\":");
    ADCLog.error.println(paramIOException.toString());
    close();
  }
  
  void write(char paramChar)
  {
    this.buffer[this.position] = ((byte)(paramChar ^ this.x));
    this.x += this.dx;
    int i = 1 + this.position;
    this.position = i;
    if (i == 1024) {
      flush();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCStreamWriter
 * JD-Core Version:    0.7.0.1
 */