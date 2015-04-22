package com.jirbo.adcolony;

import java.io.File;
import java.io.IOException;

class ADCDataFile
{
  static byte[] buffer = new byte[1024];
  String filepath;
  
  ADCDataFile(String paramString)
  {
    this.filepath = (ADC.controller.storage.data_path + paramString);
  }
  
  ADCParseReader create_reader()
  {
    try
    {
      ADCParseReader localADCParseReader = new ADCParseReader(new ADCStreamReader(this.filepath));
      return localADCParseReader;
    }
    catch (IOException localIOException) {}
    return null;
  }
  
  ADCStreamWriter create_writer()
  {
    return new ADCStreamWriter(this.filepath);
  }
  
  void delete()
  {
    new File(this.filepath).delete();
  }
  
  void save(String paramString)
  {
    ADCStreamWriter localADCStreamWriter = create_writer();
    int i = paramString.length();
    for (int j = 0; j < i; j++) {
      localADCStreamWriter.write(paramString.charAt(j));
    }
    localADCStreamWriter.close();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCDataFile
 * JD-Core Version:    0.7.0.1
 */