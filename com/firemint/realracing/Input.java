package com.firemint.realracing;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class Input
  implements SensorEventListener
{
  private Sensor m_accelerometer = null;
  private Sensor m_gyroscope = null;
  private SensorManager m_sensorManager = null;
  
  private native void updateAccelValues(float paramFloat1, float paramFloat2, float paramFloat3);
  
  private native void updateGyroValues(float paramFloat1, float paramFloat2, float paramFloat3);
  
  public void enableAccelerometer(boolean paramBoolean)
  {
    if (this.m_accelerometer != null)
    {
      if (paramBoolean)
      {
        this.m_sensorManager.registerListener(this, this.m_accelerometer, 1);
        Log.i("RealRacing3", "ACCELEROMETER ENABLED");
      }
    }
    else {
      return;
    }
    this.m_sensorManager.unregisterListener(this, this.m_accelerometer);
    Log.i("RealRacing3", "ACCELEROMETER DISABLED");
  }
  
  public void enableGyroscope(boolean paramBoolean)
  {
    if (this.m_gyroscope != null)
    {
      if (paramBoolean)
      {
        this.m_sensorManager.registerListener(this, this.m_gyroscope, 1);
        Log.i("RealRacing3", "GYROSCOPE ENABLED");
      }
    }
    else {
      return;
    }
    this.m_sensorManager.unregisterListener(this, this.m_gyroscope);
    Log.i("RealRacing3", "GYROSCOPE DISABLED");
  }
  
  public boolean isAccelerometerAvailable()
  {
    return this.m_accelerometer != null;
  }
  
  public boolean isGyroscopeAvailable()
  {
    return this.m_gyroscope != null;
  }
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt)
  {
    String str = "unknown";
    if (paramSensor == this.m_gyroscope) {
      str = "gyro";
    }
    if (paramSensor == this.m_accelerometer) {
      str = "accel";
    }
    Log.i("RealRacing3", "ACCURACY CHANGED! sensor = " + str + ", accuracy = " + paramInt);
  }
  
  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    if (paramSensorEvent.sensor == this.m_accelerometer) {
      updateAccelValues(paramSensorEvent.values[0], paramSensorEvent.values[1], paramSensorEvent.values[2]);
    }
    while (paramSensorEvent.sensor != this.m_gyroscope) {
      return;
    }
    updateGyroValues(paramSensorEvent.values[0], paramSensorEvent.values[1], paramSensorEvent.values[2]);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.Input
 * JD-Core Version:    0.7.0.1
 */