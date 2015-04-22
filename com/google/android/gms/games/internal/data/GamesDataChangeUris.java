package com.google.android.gms.games.internal.data;

import android.net.Uri;
import android.net.Uri.Builder;

public final class GamesDataChangeUris
{
  private static final Uri aan = Uri.parse("content://com.google.android.gms.games/").buildUpon().appendPath("data_change").build();
  public static final Uri aao = aan.buildUpon().appendPath("invitations").build();
  public static final Uri aap = aan.buildUpon().appendEncodedPath("players").build();
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.internal.data.GamesDataChangeUris
 * JD-Core Version:    0.7.0.1
 */