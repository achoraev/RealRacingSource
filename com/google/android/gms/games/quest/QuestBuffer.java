package com.google.android.gms.games.quest;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class QuestBuffer
  extends g<Quest>
{
  public QuestBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  protected String gD()
  {
    return "external_quest_id";
  }
  
  protected Quest m(int paramInt1, int paramInt2)
  {
    return new QuestRef(this.II, paramInt1, paramInt2);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.quest.QuestBuffer
 * JD-Core Version:    0.7.0.1
 */