package com.google.android.gms.games.quest;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.GameEntity;
import java.util.ArrayList;

public class QuestEntityCreator
  implements Parcelable.Creator<QuestEntity>
{
  public static final int CONTENT_DESCRIPTION;
  
  static void a(QuestEntity paramQuestEntity, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramQuestEntity.getGame(), paramInt, false);
    b.a(paramParcel, 2, paramQuestEntity.getQuestId(), false);
    b.a(paramParcel, 3, paramQuestEntity.getAcceptedTimestamp());
    b.a(paramParcel, 4, paramQuestEntity.getBannerImageUri(), paramInt, false);
    b.a(paramParcel, 5, paramQuestEntity.getBannerImageUrl(), false);
    b.a(paramParcel, 6, paramQuestEntity.getDescription(), false);
    b.a(paramParcel, 7, paramQuestEntity.getEndTimestamp());
    b.a(paramParcel, 8, paramQuestEntity.getLastUpdatedTimestamp());
    b.a(paramParcel, 9, paramQuestEntity.getIconImageUri(), paramInt, false);
    b.a(paramParcel, 10, paramQuestEntity.getIconImageUrl(), false);
    b.a(paramParcel, 12, paramQuestEntity.getName(), false);
    b.a(paramParcel, 13, paramQuestEntity.lK());
    b.a(paramParcel, 14, paramQuestEntity.getStartTimestamp());
    b.c(paramParcel, 15, paramQuestEntity.getState());
    b.c(paramParcel, 17, paramQuestEntity.lJ(), false);
    b.c(paramParcel, 16, paramQuestEntity.getType());
    b.c(paramParcel, 1000, paramQuestEntity.getVersionCode());
    b.H(paramParcel, i);
  }
  
  public QuestEntity createFromParcel(Parcel paramParcel)
  {
    int i = a.C(paramParcel);
    int j = 0;
    GameEntity localGameEntity = null;
    String str1 = null;
    long l1 = 0L;
    Uri localUri1 = null;
    String str2 = null;
    String str3 = null;
    long l2 = 0L;
    long l3 = 0L;
    Uri localUri2 = null;
    String str4 = null;
    String str5 = null;
    long l4 = 0L;
    long l5 = 0L;
    int k = 0;
    int m = 0;
    ArrayList localArrayList = null;
    while (paramParcel.dataPosition() < i)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        localGameEntity = (GameEntity)a.a(paramParcel, n, GameEntity.CREATOR);
        break;
      case 2: 
        str1 = a.o(paramParcel, n);
        break;
      case 3: 
        l1 = a.i(paramParcel, n);
        break;
      case 4: 
        localUri1 = (Uri)a.a(paramParcel, n, Uri.CREATOR);
        break;
      case 5: 
        str2 = a.o(paramParcel, n);
        break;
      case 6: 
        str3 = a.o(paramParcel, n);
        break;
      case 7: 
        l2 = a.i(paramParcel, n);
        break;
      case 8: 
        l3 = a.i(paramParcel, n);
        break;
      case 9: 
        localUri2 = (Uri)a.a(paramParcel, n, Uri.CREATOR);
        break;
      case 10: 
        str4 = a.o(paramParcel, n);
        break;
      case 12: 
        str5 = a.o(paramParcel, n);
        break;
      case 13: 
        l4 = a.i(paramParcel, n);
        break;
      case 14: 
        l5 = a.i(paramParcel, n);
        break;
      case 15: 
        k = a.g(paramParcel, n);
        break;
      case 17: 
        localArrayList = a.c(paramParcel, n, MilestoneEntity.CREATOR);
        break;
      case 16: 
        m = a.g(paramParcel, n);
        break;
      case 1000: 
        j = a.g(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    }
    return new QuestEntity(j, localGameEntity, str1, l1, localUri1, str2, str3, l2, l3, localUri2, str4, str5, l4, l5, k, m, localArrayList);
  }
  
  public QuestEntity[] newArray(int paramInt)
  {
    return new QuestEntity[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.quest.QuestEntityCreator
 * JD-Core Version:    0.7.0.1
 */