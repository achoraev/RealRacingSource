package com.google.android.gms.cast;

import android.text.TextUtils;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.internal.ik;
import com.google.android.gms.internal.jz;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class MediaInfo
{
  public static final int STREAM_TYPE_BUFFERED = 1;
  public static final int STREAM_TYPE_INVALID = -1;
  public static final int STREAM_TYPE_LIVE = 2;
  public static final int STREAM_TYPE_NONE;
  private final String Fe;
  private int Ff;
  private String Fg;
  private MediaMetadata Fh;
  private long Fi;
  private List<MediaTrack> Fj;
  private TextTrackStyle Fk;
  private JSONObject Fl;
  
  MediaInfo(String paramString)
    throws IllegalArgumentException
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("content ID cannot be null or empty");
    }
    this.Fe = paramString;
    this.Ff = -1;
  }
  
  MediaInfo(JSONObject paramJSONObject)
    throws JSONException
  {
    this.Fe = paramJSONObject.getString("contentId");
    String str = paramJSONObject.getString("streamType");
    if ("NONE".equals(str)) {
      this.Ff = 0;
    }
    for (;;)
    {
      this.Fg = paramJSONObject.getString("contentType");
      if (paramJSONObject.has("metadata"))
      {
        JSONObject localJSONObject2 = paramJSONObject.getJSONObject("metadata");
        this.Fh = new MediaMetadata(localJSONObject2.getInt("metadataType"));
        this.Fh.c(localJSONObject2);
      }
      this.Fi = ik.b(paramJSONObject.optDouble("duration", 0.0D));
      if (!paramJSONObject.has("tracks")) {
        break;
      }
      this.Fj = new ArrayList();
      JSONArray localJSONArray = paramJSONObject.getJSONArray("tracks");
      while (i < localJSONArray.length())
      {
        MediaTrack localMediaTrack = new MediaTrack(localJSONArray.getJSONObject(i));
        this.Fj.add(localMediaTrack);
        i++;
      }
      if ("BUFFERED".equals(str)) {
        this.Ff = 1;
      } else if ("LIVE".equals(str)) {
        this.Ff = 2;
      } else {
        this.Ff = -1;
      }
    }
    this.Fj = null;
    TextTrackStyle localTextTrackStyle;
    if (paramJSONObject.has("textTrackStyle"))
    {
      JSONObject localJSONObject1 = paramJSONObject.getJSONObject("textTrackStyle");
      localTextTrackStyle = new TextTrackStyle();
      localTextTrackStyle.c(localJSONObject1);
    }
    for (this.Fk = localTextTrackStyle;; this.Fk = null)
    {
      this.Fl = paramJSONObject.optJSONObject("customData");
      return;
    }
  }
  
  void a(MediaMetadata paramMediaMetadata)
  {
    this.Fh = paramMediaMetadata;
  }
  
  public JSONObject bK()
  {
    JSONObject localJSONObject = new JSONObject();
    for (;;)
    {
      try
      {
        localJSONObject.put("contentId", this.Fe);
        switch (this.Ff)
        {
        default: 
          localJSONObject.put("streamType", localObject);
          if (this.Fg != null) {
            localJSONObject.put("contentType", this.Fg);
          }
          if (this.Fh != null) {
            localJSONObject.put("metadata", this.Fh.bK());
          }
          localJSONObject.put("duration", ik.o(this.Fi));
          if (this.Fj != null)
          {
            JSONArray localJSONArray = new JSONArray();
            Iterator localIterator = this.Fj.iterator();
            if (localIterator.hasNext())
            {
              localJSONArray.put(((MediaTrack)localIterator.next()).bK());
              continue;
            }
            localJSONObject.put("tracks", localJSONArray);
          }
          if (this.Fk != null) {
            localJSONObject.put("textTrackStyle", this.Fk.bK());
          }
          if (this.Fl == null) {
            break label223;
          }
          localJSONObject.put("customData", this.Fl);
          return localJSONObject;
        }
      }
      catch (JSONException localJSONException) {}
      Object localObject = "NONE";
      continue;
      label223:
      return localJSONObject;
      localObject = "BUFFERED";
      continue;
      localObject = "LIVE";
    }
  }
  
  void c(List<MediaTrack> paramList)
  {
    this.Fj = paramList;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool1 = true;
    boolean bool3;
    if (this == paramObject) {
      bool3 = bool1;
    }
    MediaInfo localMediaInfo;
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
          bool2 = paramObject instanceof MediaInfo;
          bool3 = false;
        } while (!bool2);
        localMediaInfo = (MediaInfo)paramObject;
        if (this.Fl != null) {
          break;
        }
        bool4 = bool1;
        if (localMediaInfo.Fl != null) {
          break label177;
        }
        bool5 = bool1;
        bool3 = false;
      } while (bool4 != bool5);
      if ((this.Fl == null) || (localMediaInfo.Fl == null)) {
        break;
      }
      bool6 = jz.d(this.Fl, localMediaInfo.Fl);
      bool3 = false;
    } while (!bool6);
    if ((ik.a(this.Fe, localMediaInfo.Fe)) && (this.Ff == localMediaInfo.Ff) && (ik.a(this.Fg, localMediaInfo.Fg)) && (ik.a(this.Fh, localMediaInfo.Fh)) && (this.Fi == localMediaInfo.Fi)) {}
    for (;;)
    {
      return bool1;
      bool4 = false;
      break;
      label177:
      bool5 = false;
      break label52;
      bool1 = false;
    }
  }
  
  void fv()
    throws IllegalArgumentException
  {
    if (TextUtils.isEmpty(this.Fe)) {
      throw new IllegalArgumentException("content ID cannot be null or empty");
    }
    if (TextUtils.isEmpty(this.Fg)) {
      throw new IllegalArgumentException("content type cannot be null or empty");
    }
    if (this.Ff == -1) {
      throw new IllegalArgumentException("a valid stream type must be specified");
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
  
  public List<MediaTrack> getMediaTracks()
  {
    return this.Fj;
  }
  
  public MediaMetadata getMetadata()
  {
    return this.Fh;
  }
  
  public long getStreamDuration()
  {
    return this.Fi;
  }
  
  public int getStreamType()
  {
    return this.Ff;
  }
  
  public TextTrackStyle getTextTrackStyle()
  {
    return this.Fk;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[6];
    arrayOfObject[0] = this.Fe;
    arrayOfObject[1] = Integer.valueOf(this.Ff);
    arrayOfObject[2] = this.Fg;
    arrayOfObject[3] = this.Fh;
    arrayOfObject[4] = Long.valueOf(this.Fi);
    arrayOfObject[5] = String.valueOf(this.Fl);
    return n.hashCode(arrayOfObject);
  }
  
  void m(long paramLong)
    throws IllegalArgumentException
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("Stream duration cannot be negative");
    }
    this.Fi = paramLong;
  }
  
  void setContentType(String paramString)
    throws IllegalArgumentException
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("content type cannot be null or empty");
    }
    this.Fg = paramString;
  }
  
  void setCustomData(JSONObject paramJSONObject)
  {
    this.Fl = paramJSONObject;
  }
  
  void setStreamType(int paramInt)
    throws IllegalArgumentException
  {
    if ((paramInt < -1) || (paramInt > 2)) {
      throw new IllegalArgumentException("invalid stream type");
    }
    this.Ff = paramInt;
  }
  
  public void setTextTrackStyle(TextTrackStyle paramTextTrackStyle)
  {
    this.Fk = paramTextTrackStyle;
  }
  
  public static class Builder
  {
    private final MediaInfo Fm;
    
    public Builder(String paramString)
      throws IllegalArgumentException
    {
      if (TextUtils.isEmpty(paramString)) {
        throw new IllegalArgumentException("Content ID cannot be empty");
      }
      this.Fm = new MediaInfo(paramString);
    }
    
    public MediaInfo build()
      throws IllegalArgumentException
    {
      this.Fm.fv();
      return this.Fm;
    }
    
    public Builder setContentType(String paramString)
      throws IllegalArgumentException
    {
      this.Fm.setContentType(paramString);
      return this;
    }
    
    public Builder setCustomData(JSONObject paramJSONObject)
    {
      this.Fm.setCustomData(paramJSONObject);
      return this;
    }
    
    public Builder setMediaTracks(List<MediaTrack> paramList)
    {
      this.Fm.c(paramList);
      return this;
    }
    
    public Builder setMetadata(MediaMetadata paramMediaMetadata)
    {
      this.Fm.a(paramMediaMetadata);
      return this;
    }
    
    public Builder setStreamDuration(long paramLong)
      throws IllegalArgumentException
    {
      this.Fm.m(paramLong);
      return this;
    }
    
    public Builder setStreamType(int paramInt)
      throws IllegalArgumentException
    {
      this.Fm.setStreamType(paramInt);
      return this;
    }
    
    public Builder setTextTrackStyle(TextTrackStyle paramTextTrackStyle)
    {
      this.Fm.setTextTrackStyle(paramTextTrackStyle);
      return this;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.cast.MediaInfo
 * JD-Core Version:    0.7.0.1
 */