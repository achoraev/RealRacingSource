package com.google.ads.interactivemedia.v3.api.player;

public class VideoProgressUpdate
{
  public static final VideoProgressUpdate VIDEO_TIME_NOT_READY = new VideoProgressUpdate(-1L, -1L);
  private float currentTime;
  private float duration;
  
  public VideoProgressUpdate()
  {
    this(-1L, -1L);
  }
  
  public VideoProgressUpdate(long paramLong1, long paramLong2)
  {
    this.currentTime = ((float)paramLong1 / 1000.0F);
    this.duration = ((float)paramLong2 / 1000.0F);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    VideoProgressUpdate localVideoProgressUpdate;
    do
    {
      return true;
      if (paramObject == null) {
        return false;
      }
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      localVideoProgressUpdate = (VideoProgressUpdate)paramObject;
      if (Float.floatToIntBits(this.currentTime) != Float.floatToIntBits(localVideoProgressUpdate.currentTime)) {
        return false;
      }
    } while (Float.floatToIntBits(this.duration) == Float.floatToIntBits(localVideoProgressUpdate.duration));
    return false;
  }
  
  public float getCurrentTime()
  {
    return this.currentTime;
  }
  
  public float getDuration()
  {
    return this.duration;
  }
  
  public String toString()
  {
    return "VideoProgressUpdate [currentTime=" + this.currentTime + ", duration=" + this.duration + "]";
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate
 * JD-Core Version:    0.7.0.1
 */