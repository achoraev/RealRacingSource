package com.facebook.internal;

import android.content.Context;
import android.net.Uri.Builder;
import java.net.URI;
import java.net.URISyntaxException;

public class ImageRequest
{
  private static final String HEIGHT_PARAM = "height";
  private static final String MIGRATION_PARAM = "migration_overrides";
  private static final String MIGRATION_VALUE = "{october_2012:true}";
  private static final String PROFILEPIC_URL_FORMAT = "https://graph.facebook.com/%s/picture";
  public static final int UNSPECIFIED_DIMENSION = 0;
  private static final String WIDTH_PARAM = "width";
  private boolean allowCachedRedirects;
  private Callback callback;
  private Object callerTag;
  private Context context;
  private URI imageUri;
  
  private ImageRequest(Builder paramBuilder)
  {
    this.context = paramBuilder.context;
    this.imageUri = paramBuilder.imageUrl;
    this.callback = paramBuilder.callback;
    this.allowCachedRedirects = paramBuilder.allowCachedRedirects;
    if (paramBuilder.callerTag == null) {}
    for (Object localObject = new Object();; localObject = paramBuilder.callerTag)
    {
      this.callerTag = localObject;
      return;
    }
  }
  
  public static URI getProfilePictureUrl(String paramString, int paramInt1, int paramInt2)
    throws URISyntaxException
  {
    Validate.notNullOrEmpty(paramString, "userId");
    int i = Math.max(paramInt1, 0);
    int j = Math.max(paramInt2, 0);
    if ((i == 0) && (j == 0)) {
      throw new IllegalArgumentException("Either width or height must be greater than 0");
    }
    Uri.Builder localBuilder = new Uri.Builder().encodedPath(String.format("https://graph.facebook.com/%s/picture", new Object[] { paramString }));
    if (j != 0) {
      localBuilder.appendQueryParameter("height", String.valueOf(j));
    }
    if (i != 0) {
      localBuilder.appendQueryParameter("width", String.valueOf(i));
    }
    localBuilder.appendQueryParameter("migration_overrides", "{october_2012:true}");
    return new URI(localBuilder.toString());
  }
  
  public Callback getCallback()
  {
    return this.callback;
  }
  
  public Object getCallerTag()
  {
    return this.callerTag;
  }
  
  public Context getContext()
  {
    return this.context;
  }
  
  public URI getImageUri()
  {
    return this.imageUri;
  }
  
  public boolean isCachedRedirectAllowed()
  {
    return this.allowCachedRedirects;
  }
  
  public static class Builder
  {
    private boolean allowCachedRedirects;
    private ImageRequest.Callback callback;
    private Object callerTag;
    private Context context;
    private URI imageUrl;
    
    public Builder(Context paramContext, URI paramURI)
    {
      Validate.notNull(paramURI, "imageUrl");
      this.context = paramContext;
      this.imageUrl = paramURI;
    }
    
    public ImageRequest build()
    {
      return new ImageRequest(this, null);
    }
    
    public Builder setAllowCachedRedirects(boolean paramBoolean)
    {
      this.allowCachedRedirects = paramBoolean;
      return this;
    }
    
    public Builder setCallback(ImageRequest.Callback paramCallback)
    {
      this.callback = paramCallback;
      return this;
    }
    
    public Builder setCallerTag(Object paramObject)
    {
      this.callerTag = paramObject;
      return this;
    }
  }
  
  public static abstract interface Callback
  {
    public abstract void onCompleted(ImageResponse paramImageResponse);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.facebook.internal.ImageRequest
 * JD-Core Version:    0.7.0.1
 */