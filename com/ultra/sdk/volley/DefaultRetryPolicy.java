package com.ultra.sdk.volley;

public class DefaultRetryPolicy
  implements RetryPolicy
{
  public static final float DEFAULT_BACKOFF_MULT = 1.0F;
  public static final int DEFAULT_MAX_RETRIES = 0;
  public static final int DEFAULT_TIMEOUT_MS = 2500;
  private final float mBackoffMultiplier;
  private int mCurrentRetryCount;
  private int mCurrentTimeoutMs;
  private final int mMaxNumRetries;
  
  public DefaultRetryPolicy()
  {
    this(2500, 0, 1.0F);
  }
  
  public DefaultRetryPolicy(int paramInt1, int paramInt2, float paramFloat)
  {
    this.mCurrentTimeoutMs = paramInt1;
    this.mMaxNumRetries = paramInt2;
    this.mBackoffMultiplier = paramFloat;
  }
  
  public int getCurrentRetryCount()
  {
    return this.mCurrentRetryCount;
  }
  
  public int getCurrentTimeout()
  {
    return this.mCurrentTimeoutMs;
  }
  
  protected boolean hasAttemptRemaining()
  {
    return this.mCurrentRetryCount <= this.mMaxNumRetries;
  }
  
  public void retry(VolleyError paramVolleyError)
    throws VolleyError
  {
    this.mCurrentRetryCount = (1 + this.mCurrentRetryCount);
    this.mCurrentTimeoutMs = ((int)(this.mCurrentTimeoutMs + this.mCurrentTimeoutMs * this.mBackoffMultiplier));
    if (!hasAttemptRemaining()) {
      throw paramVolleyError;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ultra.sdk.volley.DefaultRetryPolicy
 * JD-Core Version:    0.7.0.1
 */