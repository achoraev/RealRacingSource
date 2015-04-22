package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.b;
import java.util.List;

public abstract interface f<F>
{
  public abstract <T> F b(b<T> paramb, T paramT);
  
  public abstract <T> F b(Operator paramOperator, MetadataField<T> paramMetadataField, T paramT);
  
  public abstract F b(Operator paramOperator, List<F> paramList);
  
  public abstract F d(MetadataField<?> paramMetadataField);
  
  public abstract <T> F d(MetadataField<T> paramMetadataField, T paramT);
  
  public abstract F is();
  
  public abstract F j(F paramF);
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.query.internal.f
 * JD-Core Version:    0.7.0.1
 */