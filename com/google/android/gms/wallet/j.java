package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.jr;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.d;
import com.google.android.gms.wallet.wobs.f;
import com.google.android.gms.wallet.wobs.l;
import com.google.android.gms.wallet.wobs.n;
import com.google.android.gms.wallet.wobs.p;
import java.util.ArrayList;

public class j
  implements Parcelable.Creator<LoyaltyWalletObject>
{
  static void a(LoyaltyWalletObject paramLoyaltyWalletObject, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramLoyaltyWalletObject.getVersionCode());
    b.a(paramParcel, 2, paramLoyaltyWalletObject.fl, false);
    b.a(paramParcel, 3, paramLoyaltyWalletObject.asT, false);
    b.a(paramParcel, 4, paramLoyaltyWalletObject.asU, false);
    b.a(paramParcel, 5, paramLoyaltyWalletObject.asV, false);
    b.a(paramParcel, 6, paramLoyaltyWalletObject.Dv, false);
    b.a(paramParcel, 7, paramLoyaltyWalletObject.asW, false);
    b.a(paramParcel, 8, paramLoyaltyWalletObject.asX, false);
    b.a(paramParcel, 9, paramLoyaltyWalletObject.asY, false);
    b.a(paramParcel, 10, paramLoyaltyWalletObject.asZ, false);
    b.a(paramParcel, 11, paramLoyaltyWalletObject.ata, false);
    b.c(paramParcel, 12, paramLoyaltyWalletObject.state);
    b.c(paramParcel, 13, paramLoyaltyWalletObject.atb, false);
    b.a(paramParcel, 14, paramLoyaltyWalletObject.atc, paramInt, false);
    b.c(paramParcel, 15, paramLoyaltyWalletObject.atd, false);
    b.a(paramParcel, 17, paramLoyaltyWalletObject.atf, false);
    b.a(paramParcel, 16, paramLoyaltyWalletObject.ate, false);
    b.a(paramParcel, 19, paramLoyaltyWalletObject.ath);
    b.c(paramParcel, 18, paramLoyaltyWalletObject.atg, false);
    b.c(paramParcel, 21, paramLoyaltyWalletObject.atj, false);
    b.c(paramParcel, 20, paramLoyaltyWalletObject.ati, false);
    b.a(paramParcel, 23, paramLoyaltyWalletObject.atl, paramInt, false);
    b.c(paramParcel, 22, paramLoyaltyWalletObject.atk, false);
    b.H(paramParcel, i);
  }
  
  public LoyaltyWalletObject dv(Parcel paramParcel)
  {
    int i = a.C(paramParcel);
    int j = 0;
    String str1 = null;
    String str2 = null;
    String str3 = null;
    String str4 = null;
    String str5 = null;
    String str6 = null;
    String str7 = null;
    String str8 = null;
    String str9 = null;
    String str10 = null;
    int k = 0;
    ArrayList localArrayList1 = jr.hz();
    l locall = null;
    ArrayList localArrayList2 = jr.hz();
    String str11 = null;
    String str12 = null;
    ArrayList localArrayList3 = jr.hz();
    boolean bool = false;
    ArrayList localArrayList4 = jr.hz();
    ArrayList localArrayList5 = jr.hz();
    ArrayList localArrayList6 = jr.hz();
    f localf = null;
    while (paramParcel.dataPosition() < i)
    {
      int m = a.B(paramParcel);
      switch (a.aD(m))
      {
      default: 
        a.b(paramParcel, m);
        break;
      case 1: 
        j = a.g(paramParcel, m);
        break;
      case 2: 
        str1 = a.o(paramParcel, m);
        break;
      case 3: 
        str2 = a.o(paramParcel, m);
        break;
      case 4: 
        str3 = a.o(paramParcel, m);
        break;
      case 5: 
        str4 = a.o(paramParcel, m);
        break;
      case 6: 
        str5 = a.o(paramParcel, m);
        break;
      case 7: 
        str6 = a.o(paramParcel, m);
        break;
      case 8: 
        str7 = a.o(paramParcel, m);
        break;
      case 9: 
        str8 = a.o(paramParcel, m);
        break;
      case 10: 
        str9 = a.o(paramParcel, m);
        break;
      case 11: 
        str10 = a.o(paramParcel, m);
        break;
      case 12: 
        k = a.g(paramParcel, m);
        break;
      case 13: 
        localArrayList1 = a.c(paramParcel, m, p.CREATOR);
        break;
      case 14: 
        locall = (l)a.a(paramParcel, m, l.CREATOR);
        break;
      case 15: 
        localArrayList2 = a.c(paramParcel, m, LatLng.CREATOR);
        break;
      case 17: 
        str12 = a.o(paramParcel, m);
        break;
      case 16: 
        str11 = a.o(paramParcel, m);
        break;
      case 19: 
        bool = a.c(paramParcel, m);
        break;
      case 18: 
        localArrayList3 = a.c(paramParcel, m, d.CREATOR);
        break;
      case 21: 
        localArrayList5 = a.c(paramParcel, m, com.google.android.gms.wallet.wobs.j.CREATOR);
        break;
      case 20: 
        localArrayList4 = a.c(paramParcel, m, n.CREATOR);
        break;
      case 23: 
        localf = (f)a.a(paramParcel, m, f.CREATOR);
        break;
      case 22: 
        localArrayList6 = a.c(paramParcel, m, n.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    }
    return new LoyaltyWalletObject(j, str1, str2, str3, str4, str5, str6, str7, str8, str9, str10, k, localArrayList1, locall, localArrayList2, str11, str12, localArrayList3, bool, localArrayList4, localArrayList5, localArrayList6, localf);
  }
  
  public LoyaltyWalletObject[] fw(int paramInt)
  {
    return new LoyaltyWalletObject[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.wallet.j
 * JD-Core Version:    0.7.0.1
 */