package com.google.android.gms.games.internal.request;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.games.internal.constants.RequestUpdateResultOutcome;
import java.util.HashMap;
import java.util.Set;

public final class RequestUpdateOutcomes
{
  private static final String[] abs = { "requestId", "outcome" };
  private final int HF;
  private final HashMap<String, Integer> abt;
  
  private RequestUpdateOutcomes(int paramInt, HashMap<String, Integer> paramHashMap)
  {
    this.HF = paramInt;
    this.abt = paramHashMap;
  }
  
  public static RequestUpdateOutcomes V(DataHolder paramDataHolder)
  {
    Builder localBuilder = new Builder();
    localBuilder.dR(paramDataHolder.getStatusCode());
    int i = paramDataHolder.getCount();
    for (int j = 0; j < i; j++)
    {
      int k = paramDataHolder.ar(j);
      localBuilder.x(paramDataHolder.c("requestId", j, k), paramDataHolder.b("outcome", j, k));
    }
    return localBuilder.ly();
  }
  
  public Set<String> getRequestIds()
  {
    return this.abt.keySet();
  }
  
  public int getRequestOutcome(String paramString)
  {
    o.b(this.abt.containsKey(paramString), "Request " + paramString + " was not part of the update operation!");
    return ((Integer)this.abt.get(paramString)).intValue();
  }
  
  public static final class Builder
  {
    private int HF = 0;
    private HashMap<String, Integer> abt = new HashMap();
    
    public Builder dR(int paramInt)
    {
      this.HF = paramInt;
      return this;
    }
    
    public RequestUpdateOutcomes ly()
    {
      return new RequestUpdateOutcomes(this.HF, this.abt, null);
    }
    
    public Builder x(String paramString, int paramInt)
    {
      if (RequestUpdateResultOutcome.isValid(paramInt)) {
        this.abt.put(paramString, Integer.valueOf(paramInt));
      }
      return this;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.internal.request.RequestUpdateOutcomes
 * JD-Core Version:    0.7.0.1
 */