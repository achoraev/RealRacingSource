package com.millennialmedia.google.gson.internal.bind;

import com.millennialmedia.google.gson.Gson;
import com.millennialmedia.google.gson.JsonSyntaxException;
import com.millennialmedia.google.gson.TypeAdapter;
import com.millennialmedia.google.gson.TypeAdapterFactory;
import com.millennialmedia.google.gson.reflect.TypeToken;
import com.millennialmedia.google.gson.stream.JsonReader;
import com.millennialmedia.google.gson.stream.JsonToken;
import com.millennialmedia.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class DateTypeAdapter
  extends TypeAdapter<Date>
{
  public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory()
  {
    public <T> TypeAdapter<T> create(Gson paramAnonymousGson, TypeToken<T> paramAnonymousTypeToken)
    {
      if (paramAnonymousTypeToken.getRawType() == Date.class) {
        return new DateTypeAdapter();
      }
      return null;
    }
  };
  private final DateFormat enUsFormat = DateFormat.getDateTimeInstance(2, 2, Locale.US);
  private final DateFormat iso8601Format = buildIso8601Format();
  private final DateFormat localFormat = DateFormat.getDateTimeInstance(2, 2);
  
  private static DateFormat buildIso8601Format()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    return localSimpleDateFormat;
  }
  
  private Date deserializeToDate(String paramString)
  {
    try
    {
      Date localDate3 = this.localFormat.parse(paramString);
      localObject2 = localDate3;
    }
    catch (ParseException localParseException1)
    {
      try
      {
        Date localDate2 = this.enUsFormat.parse(paramString);
        localObject2 = localDate2;
      }
      catch (ParseException localParseException2)
      {
        try
        {
          Date localDate1 = this.iso8601Format.parse(paramString);
          Object localObject2 = localDate1;
        }
        catch (ParseException localParseException3)
        {
          throw new JsonSyntaxException(paramString, localParseException3);
        }
      }
    }
    finally {}
    return localObject2;
  }
  
  public Date read(JsonReader paramJsonReader)
    throws IOException
  {
    if (paramJsonReader.peek() == JsonToken.NULL)
    {
      paramJsonReader.nextNull();
      return null;
    }
    return deserializeToDate(paramJsonReader.nextString());
  }
  
  public void write(JsonWriter paramJsonWriter, Date paramDate)
    throws IOException
  {
    if (paramDate == null) {}
    for (;;)
    {
      try
      {
        paramJsonWriter.nullValue();
        return;
      }
      finally {}
      paramJsonWriter.value(this.enUsFormat.format(paramDate));
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.google.gson.internal.bind.DateTypeAdapter
 * JD-Core Version:    0.7.0.1
 */