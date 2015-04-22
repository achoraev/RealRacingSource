package com.firemint.realracing;

import android.media.AudioManager;
import org.fmod.FMODAudioDevice;

class AudioStreamManager
{
  private static AudioStreamManager m_instance = null;
  private FMODAudioDevice m_FMODAudioDevice = new FMODAudioDevice();
  private AudioManager m_audioManager = null;
  private boolean m_soloed = false;
  
  public AudioStreamManager()
  {
    m_instance = this;
  }
  
  private boolean available()
  {
    return this.m_audioManager != null;
  }
  
  private void setMediaStreamSolo(boolean paramBoolean)
  {
    if (this.m_soloed != paramBoolean)
    {
      this.m_soloed = paramBoolean;
      if (!paramBoolean) {}
    }
  }
  
  public static void staticOnPause()
  {
    if (m_instance != null) {
      m_instance.onPause();
    }
  }
  
  public static void staticOnResume()
  {
    if (m_instance != null) {
      m_instance.onResume();
    }
  }
  
  public void onPause()
  {
    if (available()) {
      setMediaStreamSolo(false);
    }
  }
  
  public void onResume()
  {
    if (available()) {
      setMediaStreamSolo(true);
    }
  }
  
  public void onStart()
  {
    this.m_FMODAudioDevice.start();
  }
  
  public void onStop()
  {
    this.m_FMODAudioDevice.stop();
  }
  
  public void setAudioManager(AudioManager paramAudioManager)
  {
    this.m_audioManager = paramAudioManager;
    setMediaStreamSolo(true);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.AudioStreamManager
 * JD-Core Version:    0.7.0.1
 */