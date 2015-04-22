package com.google.android.gms.cast;

import android.text.TextUtils;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.internal.ik;
import com.google.android.gms.internal.jz;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public final class MediaTrack
{
  public static final int SUBTYPE_CAPTIONS = 2;
  public static final int SUBTYPE_CHAPTERS = 4;
  public static final int SUBTYPE_DESCRIPTIONS = 3;
  public static final int SUBTYPE_METADATA = 5;
  public static final int SUBTYPE_NONE = 0;
  public static final int SUBTYPE_SUBTITLES = 1;
  public static final int SUBTYPE_UNKNOWN = -1;
  public static final int TYPE_AUDIO = 2;
  public static final int TYPE_TEXT = 1;
  public static final int TYPE_UNKNOWN = 0;
  public static final int TYPE_VIDEO = 3;
  private long Dj;
  private int FD;
  private int FE;
  private String Fc;
  private String Fe;
  private String Fg;
  private JSONObject Fl;
  private String mName;
  
  MediaTrack(long paramLong, int paramInt)
    throws IllegalArgumentException
  {
    clear();
    this.Dj = paramLong;
    if ((paramInt <= 0) || (paramInt > 3)) {
      throw new IllegalArgumentException("invalid type " + paramInt);
    }
    this.FD = paramInt;
  }
  
  MediaTrack(JSONObject paramJSONObject)
    throws JSONException
  {
    c(paramJSONObject);
  }
  
  private void c(JSONObject paramJSONObject)
    throws JSONException
  {
    clear();
    this.Dj = paramJSONObject.getLong("trackId");
    String str1 = paramJSONObject.getString("type");
    String str2;
    if ("TEXT".equals(str1))
    {
      this.FD = 1;
      this.Fe = paramJSONObject.optString("trackContentId", null);
      this.Fg = paramJSONObject.optString("trackContentType", null);
      this.mName = paramJSONObject.optString("name", null);
      this.Fc = paramJSONObject.optString("language", null);
      if (!paramJSONObject.has("subtype")) {
        break label276;
      }
      str2 = paramJSONObject.getString("subtype");
      if (!"SUBTITLES".equals(str2)) {
        break label181;
      }
      this.FE = 1;
    }
    label276:
    for (;;)
    {
      this.Fl = paramJSONObject.optJSONObject("customData");
      return;
      if ("AUDIO".equals(str1))
      {
        this.FD = 2;
        break;
      }
      if ("VIDEO".equals(str1))
      {
        this.FD = 3;
        break;
      }
      throw new JSONException("invalid type: " + str1);
      label181:
      if ("CAPTIONS".equals(str2))
      {
        this.FE = 2;
      }
      else if ("DESCRIPTIONS".equals(str2))
      {
        this.FE = 3;
      }
      else if ("CHAPTERS".equals(str2))
      {
        this.FE = 4;
      }
      else if ("METADATA".equals(str2))
      {
        this.FE = 5;
      }
      else
      {
        throw new JSONException("invalid subtype: " + str2);
        this.FE = 0;
      }
    }
  }
  
  private void clear()
  {
    this.Dj = 0L;
    this.FD = 0;
    this.Fe = null;
    this.mName = null;
    this.Fc = null;
    this.FE = -1;
    this.Fl = null;
  }
  
  void aa(int paramInt)
    throws IllegalArgumentException
  {
    if ((paramInt <= -1) || (paramInt > 5)) {
      throw new IllegalArgumentException("invalid subtype " + paramInt);
    }
    if ((paramInt != 0) && (this.FD != 1)) {
      throw new IllegalArgumentException("subtypes are only valid for text tracks");
    }
    this.FE = paramInt;
  }
  
  public JSONObject bK()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("trackId", this.Dj);
      switch (this.FD)
      {
      default: 
        if (this.Fe != null) {
          localJSONObject.put("trackContentId", this.Fe);
        }
        if (this.Fg != null) {
          localJSONObject.put("trackContentType", this.Fg);
        }
        if (this.mName != null) {
          localJSONObject.put("name", this.mName);
        }
        if (!TextUtils.isEmpty(this.Fc)) {
          localJSONObject.put("language", this.Fc);
        }
        switch (this.FE)
        {
        }
        break;
      }
      for (;;)
      {
        if (this.Fl == null) {
          break label276;
        }
        localJSONObject.put("customData", this.Fl);
        return localJSONObject;
        localJSONObject.put("type", "TEXT");
        break;
        localJSONObject.put("type", "AUDIO");
        break;
        localJSONObject.put("type", "VIDEO");
        break;
        localJSONObject.put("subtype", "SUBTITLES");
        continue;
        localJSONObject.put("subtype", "CAPTIONS");
        continue;
        localJSONObject.put("subtype", "DESCRIPTIONS");
        continue;
        localJSONObject.put("subtype", "CHAPTERS");
        continue;
        localJSONObject.put("subtype", "METADATA");
      }
      label276:
      return localJSONObject;
    }
    catch (JSONException localJSONException) {}
    return localJSONObject;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool1 = true;
    boolean bool3;
    if (this == paramObject) {
      bool3 = bool1;
    }
    MediaTrack localMediaTrack;
    boolean bool4;
    boolean bool5;
    label52:
    boolean bool6;
    do
    {
      do
      {
        boolean bool2;
        do
        {
          return bool3;
          bool2 = paramObject instanceof MediaTrack;
          bool3 = false;
        } while (!bool2);
        localMediaTrack = (MediaTrack)paramObject;
        if (this.Fl != null) {
          break;
        }
        bool4 = bool1;
        if (localMediaTrack.Fl != null) {
          break label204;
        }
        bool5 = bool1;
        bool3 = false;
      } while (bool4 != bool5);
      if ((this.Fl == null) || (localMediaTrack.Fl == null)) {
        break;
      }
      bool6 = jz.d(this.Fl, localMediaTrack.Fl);
      bool3 = false;
    } while (!bool6);
    if ((this.Dj == localMediaTrack.Dj) && (this.FD == localMediaTrack.FD) && (ik.a(this.Fe, localMediaTrack.Fe)) && (ik.a(this.Fg, localMediaTrack.Fg)) && (ik.a(this.mName, localMediaTrack.mName)) && (ik.a(this.Fc, localMediaTrack.Fc)) && (this.FE == localMediaTrack.FE)) {}
    for (;;)
    {
      return bool1;
      bool4 = false;
      break;
      label204:
      bool5 = false;
      break label52;
      bool1 = false;
    }
  }
  
  public String getContentId()
  {
    return this.Fe;
  }
  
  public String getContentType()
  {
    return this.Fg;
  }
  
  public JSONObject getCustomData()
  {
    return this.Fl;
  }
  
  public long getId()
  {
    return this.Dj;
  }
  
  public String getLanguage()
  {
    return this.Fc;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public int getSubtype()
  {
    return this.FE;
  }
  
  public int getType()
  {
    return this.FD;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[8];
    arrayOfObject[0] = Long.valueOf(this.Dj);
    arrayOfObject[1] = Integer.valueOf(this.FD);
    arrayOfObject[2] = this.Fe;
    arrayOfObject[3] = this.Fg;
    arrayOfObject[4] = this.mName;
    arrayOfObject[5] = this.Fc;
    arrayOfObject[6] = Integer.valueOf(this.FE);
    arrayOfObject[7] = this.Fl;
    return n.hashCode(arrayOfObject);
  }
  
  public void setContentId(String paramString)
  {
    this.Fe = paramString;
  }
  
  public void setContentType(String paramString)
  {
    this.Fg = paramString;
  }
  
  void setCustomData(JSONObject paramJSONObject)
  {
    this.Fl = paramJSONObject;
  }
  
  void setLanguage(String paramString)
  {
    this.Fc = paramString;
  }
  
  void setName(String paramString)
  {
    this.mName = paramString;
  }
  
  public static class Builder
  {
    private final MediaTrack FF;
    
    public Builder(long paramLong, int paramInt)
      throws IllegalArgumentException
    {
      this.FF = new MediaTrack(paramLong, paramInt);
    }
    
    public MediaTrack build()
    {
      return this.FF;
    }
    
    public Builder setContentId(String paramString)
    {
      this.FF.setContentId(paramString);
      return this;
    }
    
    public Builder setContentType(String paramString)
    {
      this.FF.setContentType(paramString);
      return this;
    }
    
    public Builder setCustomData(JSONObject paramJSONObject)
    {
      this.FF.setCustomData(paramJSONObject);
      return this;
    }
    
    public Builder setLanguage(String paramString)
    {
      this.FF.setLanguage(paramString);
      return this;
    }
    
    public Builder setLanguage(Locale paramLocale)
    {
      this.FF.setLanguage(ik.b(paramLocale));
      return this;
    }
    
    public Builder setName(String paramString)
    {
      this.FF.setName(paramString);
      return this;
    }
    
    public Builder setSubtype(int paramInt)
      throws IllegalArgumentException
    {
      this.FF.aa(paramInt);
      return this;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.cast.MediaTrack
 * JD-Core Version:    0.7.0.1
 */