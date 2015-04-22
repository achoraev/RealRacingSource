package com.jirbo.adcolony;

import java.io.IOException;
import java.io.InputStream;

class ADCParseReader
{
  int count;
  char[] data;
  int position;
  
  ADCParseReader(InputStream paramInputStream)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder(paramInputStream.available());
    int i = paramInputStream.read();
    if (i != -1)
    {
      if (((i >= 32) && (i <= 126)) || (i == 10)) {
        localStringBuilder.append((char)i);
      }
      for (;;)
      {
        i = paramInputStream.read();
        break;
        if ((i & 0x80) != 0)
        {
          if ((i & 0xE0) == 192)
          {
            int m = paramInputStream.read();
            localStringBuilder.append((char)((i & 0x1F) << 6 | m & 0x3F));
          }
          else
          {
            int j = paramInputStream.read();
            int k = paramInputStream.read();
            localStringBuilder.append((char)((i & 0xF) << 12 | (j & 0x3F) << 6 | k & 0x3F));
          }
        }
        else {
          localStringBuilder.append(' ');
        }
      }
    }
    this.count = localStringBuilder.length();
    this.data = new char[this.count];
    localStringBuilder.getChars(0, this.count, this.data, 0);
  }
  
  ADCParseReader(String paramString)
  {
    this.count = paramString.length();
    StringBuilder localStringBuilder = new StringBuilder(this.count);
    int i = 0;
    if (i < this.count)
    {
      char c = paramString.charAt(i);
      if (((c >= ' ') && (c <= '~')) || (c == '\n')) {
        localStringBuilder.append(c);
      }
      for (;;)
      {
        i++;
        break;
        if ((c & 0x80) != 0)
        {
          if (((c & 0xE0) == 'À') && (i + 1 < this.count))
          {
            int m = paramString.charAt(i + 1);
            localStringBuilder.append((char)((c & 0x1F) << '\006' | m & 0x3F));
            i++;
          }
          else if (i + 2 < this.count)
          {
            int j = paramString.charAt(i + 1);
            int k = paramString.charAt(i + 2);
            localStringBuilder.append((char)((c & 0xF) << '\f' | (j & 0x3F) << 6 | k & 0x3F));
            i += 2;
          }
          else
          {
            localStringBuilder.append('?');
          }
        }
        else {
          localStringBuilder.append(' ');
        }
      }
    }
    this.count = localStringBuilder.length();
    this.data = new char[this.count];
    localStringBuilder.getChars(0, this.count, this.data, 0);
  }
  
  public static void main(String[] paramArrayOfString) {}
  
  boolean consume(char paramChar)
  {
    if ((this.position == this.count) || (this.data[this.position] != paramChar)) {
      return false;
    }
    this.position = (1 + this.position);
    return true;
  }
  
  boolean consume(String paramString)
  {
    int i = paramString.length();
    if (i + this.position > this.count) {
      return false;
    }
    for (int j = 0;; j++)
    {
      if (j >= i) {
        break label52;
      }
      if (paramString.charAt(j) != this.data[(j + this.position)]) {
        break;
      }
    }
    label52:
    this.position = (i + this.position);
    return true;
  }
  
  void consume_ws()
  {
    for (;;)
    {
      if (this.position == this.count) {}
      int i;
      do
      {
        return;
        i = this.data[this.position];
      } while ((i != 32) && (i != 10));
      this.position = (1 + this.position);
    }
  }
  
  boolean has_another()
  {
    return this.position < this.count;
  }
  
  void must_consume(char paramChar)
  {
    if (!consume(paramChar)) {
      throw new AdColonyException("'" + paramChar + "' expected.");
    }
  }
  
  void must_consume(String paramString)
  {
    if (!consume(paramString)) {
      throw new AdColonyException("\"" + paramString + "\" expected.");
    }
  }
  
  char peek()
  {
    if (this.position == this.count) {
      return '\000';
    }
    return this.data[this.position];
  }
  
  char read()
  {
    char[] arrayOfChar = this.data;
    int i = this.position;
    this.position = (i + 1);
    return arrayOfChar[i];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCParseReader
 * JD-Core Version:    0.7.0.1
 */