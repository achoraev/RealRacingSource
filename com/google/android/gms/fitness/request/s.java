package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.Session;
import java.util.ArrayList;

public class s
  implements Parcelable.Creator<SessionInsertRequest>
{
  static void a(SessionInsertRequest paramSessionInsertRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramSessionInsertRequest.getSession(), paramInt, false);
    b.c(paramParcel, 1000, paramSessionInsertRequest.getVersionCode());
    b.c(paramParcel, 2, paramSessionInsertRequest.getDataSets(), false);
    b.c(paramParcel, 3, paramSessionInsertRequest.getAggregateDataPoints(), false);
    b.H(paramParcel, i);
  }
  
  public SessionInsertRequest bK(Parcel paramParcel)
  {
    Object localObject1 = null;
    int i = a.C(paramParcel);
    int j = 0;
    Object localObject2 = null;
    Object localObject3 = null;
    if (paramParcel.dataPosition() < i)
    {
      int k = a.B(paramParcel);
      Object localObject4;
      Object localObject5;
      Object localObject6;
      int m;
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        localObject4 = localObject1;
        localObject5 = localObject2;
        localObject6 = localObject3;
        m = j;
      }
      for (;;)
      {
        j = m;
        localObject3 = localObject6;
        localObject2 = localObject5;
        localObject1 = localObject4;
        break;
        Session localSession = (Session)a.a(paramParcel, k, Session.CREATOR);
        m = j;
        Object localObject9 = localObject2;
        localObject6 = localSession;
        localObject4 = localObject1;
        localObject5 = localObject9;
        continue;
        int n = a.g(paramParcel, k);
        Object localObject8 = localObject1;
        localObject5 = localObject2;
        localObject6 = localObject3;
        m = n;
        localObject4 = localObject8;
        continue;
        ArrayList localArrayList = a.c(paramParcel, k, DataSet.CREATOR);
        localObject6 = localObject3;
        m = j;
        Object localObject7 = localObject1;
        localObject5 = localArrayList;
        localObject4 = localObject7;
        continue;
        localObject4 = a.c(paramParcel, k, DataPoint.CREATOR);
        localObject5 = localObject2;
        localObject6 = localObject3;
        m = j;
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    }
    return new SessionInsertRequest(j, localObject3, localObject2, localObject1);
  }
  
  public SessionInsertRequest[] dc(int paramInt)
  {
    return new SessionInsertRequest[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.fitness.request.s
 * JD-Core Version:    0.7.0.1
 */