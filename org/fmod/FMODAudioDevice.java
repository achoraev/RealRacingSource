package org.fmod;

import android.media.AudioTrack;
import android.util.Log;
import java.nio.ByteBuffer;

public class FMODAudioDevice
  implements Runnable
{
  private static int FMOD_INFO_CHANNELCOUNT = 4;
  private static int FMOD_INFO_DSPBUFFERLENGTH;
  private static int FMOD_INFO_DSPNUMBUFFERS;
  private static int FMOD_INFO_MIXERRUNNING;
  private static int FMOD_INFO_SAMPLERATE = 0;
  private boolean mInitialised = false;
  private boolean mRunning = false;
  private Thread mThread = null;
  private AudioTrack mTrack = null;
  
  static
  {
    FMOD_INFO_DSPBUFFERLENGTH = 1;
    FMOD_INFO_DSPNUMBUFFERS = 2;
    FMOD_INFO_MIXERRUNNING = 3;
  }
  
  private int fetchChannelConfigFromCount(int paramInt)
  {
    if (paramInt == 1) {
      return 2;
    }
    if (paramInt == 2) {
      return 3;
    }
    if (paramInt == 6) {
      return 252;
    }
    return 0;
  }
  
  private native int fmodGetInfo(int paramInt);
  
  private native int fmodProcess(ByteBuffer paramByteBuffer);
  
  private void shutDown()
  {
    if (this.mTrack != null)
    {
      this.mTrack.stop();
      this.mTrack.release();
      this.mTrack = null;
    }
    this.mInitialised = false;
  }
  
  public boolean isMixing()
  {
    return this.mInitialised;
  }
  
  public void run()
  {
    int i = 3;
    Object localObject1 = null;
    Object localObject2 = null;
    int j;
    int k;
    int n;
    int i1;
    int i2;
    label107:
    int i3;
    if (this.mRunning) {
      if (!this.mInitialised)
      {
        j = fmodGetInfo(FMOD_INFO_SAMPLERATE);
        k = fmodGetInfo(FMOD_INFO_DSPBUFFERLENGTH);
        int m = fmodGetInfo(FMOD_INFO_DSPNUMBUFFERS);
        n = fmodGetInfo(FMOD_INFO_CHANNELCOUNT);
        if ((j > 0) && (k > 0) && (m > 0) && (n > 0))
        {
          i1 = fetchChannelConfigFromCount(n);
          i2 = AudioTrack.getMinBufferSize(j, i1, 2);
          if (i2 < 0)
          {
            Log.w("fmod", "FMOD: AudioDevice::run               : Couldn't query minimum buffer size, possibly unsupported sample rate or channel count");
            i3 = 2 * (n * (m * k));
            if (i3 <= i2) {
              break label412;
            }
          }
        }
      }
    }
    for (;;)
    {
      Log.i("fmod", "FMOD: AudioDevice::run               : Actual buffer size: " + i3 + " bytes");
      ByteBuffer localByteBuffer = ByteBuffer.allocateDirect(2 * (k * n));
      byte[] arrayOfByte = new byte[localByteBuffer.capacity()];
      this.mTrack = new AudioTrack(3, j, i1, 2, i3, 1);
      int i4;
      label224:
      Object localObject3;
      if (this.mTrack.getState() == 1)
      {
        this.mTrack.play();
        this.mInitialised = true;
        i4 = i;
        i = i4;
        localObject3 = localByteBuffer;
        localObject1 = arrayOfByte;
      }
      for (;;)
      {
        for (;;)
        {
          localObject2 = localObject3;
          break;
          Log.i("fmod", "FMOD: AudioDevice::run               : Min buffer size: " + i2 + " bytes");
          break label107;
          this.mTrack.release();
          this.mTrack = null;
          i4 = i - 1;
          if (i4 == 0)
          {
            Log.e("fmod", "FMOD: AudioDevice::run               : Couldn't initialize AudioTrack, giving up");
            this.mRunning = false;
            break label224;
          }
          Log.w("fmod", "FMOD: AudioDevice::run               : Couldn't initialize AudioTrack, retrying...");
          try
          {
            Thread.sleep(1000L);
          }
          catch (InterruptedException localInterruptedException2) {}
        }
        break label224;
        try
        {
          Thread.sleep(100L);
          localObject3 = localObject2;
        }
        catch (InterruptedException localInterruptedException1)
        {
          localObject3 = localObject2;
        }
      }
      if (fmodGetInfo(FMOD_INFO_MIXERRUNNING) == 1)
      {
        fmodProcess(localObject2);
        localObject2.get(localObject1);
        localObject2.position(0);
        this.mTrack.write(localObject1, 0, localObject2.capacity());
        break;
      }
      shutDown();
      break;
      shutDown();
      return;
      label412:
      i3 = i2;
    }
  }
  
  public void start()
  {
    if (this.mThread != null) {
      stop();
    }
    this.mThread = new Thread(this);
    this.mThread.setPriority(10);
    this.mRunning = true;
    this.mThread.start();
  }
  
  public void stop()
  {
    while (this.mThread != null)
    {
      this.mRunning = false;
      try
      {
        this.mThread.join();
        this.mThread = null;
      }
      catch (InterruptedException localInterruptedException) {}
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     org.fmod.FMODAudioDevice
 * JD-Core Version:    0.7.0.1
 */