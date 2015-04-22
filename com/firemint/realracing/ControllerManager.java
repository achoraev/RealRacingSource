package com.firemint.realracing;

import android.annotation.TargetApi;
import android.hardware.input.InputManager;
import android.hardware.input.InputManager.InputDeviceListener;
import android.util.Log;
import android.view.InputDevice;
import android.view.InputDevice.MotionRange;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class ControllerManager
{
  public static final int BUTTON_L1 = 1;
  public static final int BUTTON_L2 = 2;
  public static final int BUTTON_R1 = 8;
  public static final int BUTTON_R2 = 16;
  public static final int LTRIGGER = 4;
  public static final int RTRIGGER = 32;
  private boolean m_bEnableLogging = false;
  private ControllerManager_Moga m_controllerManager_Moga = null;
  protected ControllerInputListener m_inputListener = null;
  protected MainActivity m_mainActivity = null;
  
  ControllerManager(MainActivity paramMainActivity)
  {
    Log("onCreate");
    this.m_mainActivity = paramMainActivity;
    try
    {
      this.m_controllerManager_Moga = new ControllerManager_Moga(this.m_mainActivity, this);
      if (IsAtLeastJellyBean()) {
        this.m_inputListener = new ControllerInputListener(this);
      }
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        LogError("Failed to create MOGA controller manager: " + localException.toString());
        this.m_controllerManager_Moga = null;
      }
    }
  }
  
  protected void ControllerConnected(final String paramString1, final String paramString2, final int paramInt1, final int paramInt2)
  {
    this.m_mainActivity.getGLView().queueEvent(new Runnable()
    {
      public void run()
      {
        ControllerManager.this.ControllerConnectedJNI(paramString1, paramString2, paramInt1, paramInt2);
      }
    });
  }
  
  protected native void ControllerConnectedJNI(String paramString1, String paramString2, int paramInt1, int paramInt2);
  
  protected void ControllerDisconnected(final int paramInt)
  {
    this.m_mainActivity.getGLView().queueEvent(new Runnable()
    {
      public void run()
      {
        ControllerManager.this.ControllerDisconnectedJNI(paramInt);
      }
    });
  }
  
  protected native void ControllerDisconnectedJNI(int paramInt);
  
  float GetJoystickAxisValue(int paramInt, MotionEvent paramMotionEvent)
  {
    InputDevice localInputDevice = paramMotionEvent.getDevice();
    if (localInputDevice != null)
    {
      float f1 = paramMotionEvent.getAxisValue(paramInt);
      float f2 = Math.abs(f1);
      InputDevice.MotionRange localMotionRange = localInputDevice.getMotionRange(paramInt);
      if ((localMotionRange != null) && (f2 < localMotionRange.getFlat())) {
        f1 = 0.0F;
      }
      return f1;
    }
    Log("Input device was null!!!!!");
    return 0.0F;
  }
  
  @TargetApi(16)
  boolean HandleDpad(InputEvent paramInputEvent)
  {
    float f1;
    float f2;
    boolean bool2;
    if ((paramInputEvent instanceof MotionEvent))
    {
      MotionEvent localMotionEvent = (MotionEvent)paramInputEvent;
      f1 = localMotionEvent.getAxisValue(15);
      f2 = localMotionEvent.getAxisValue(16);
      if (Float.compare(f1, -1.0F) == 0)
      {
        Log("Dpad left pressed (MotionEvent)");
        if ((HandleKeyEvents(paramInputEvent.getDevice().getDescriptor(), 21, 0)) && (HandleKeyEvents(paramInputEvent.getDevice().getDescriptor(), 22, 1)))
        {
          bool2 = true;
          if (Float.compare(f2, -1.0F) != 0) {
            break label281;
          }
          Log("Dpad up pressed (MotionEvent)");
          if ((!HandleKeyEvents(paramInputEvent.getDevice().getDescriptor(), 19, 0)) || (!HandleKeyEvents(paramInputEvent.getDevice().getDescriptor(), 20, 1))) {
            break label276;
          }
          bool2 = true;
        }
      }
    }
    for (;;)
    {
      if (!bool2) {
        LogError("HandleDpad - No event");
      }
      return bool2;
      bool2 = false;
      break;
      if (Float.compare(f1, 1.0F) == 0)
      {
        Log("Dpad right pressed (MotionEvent)");
        if ((HandleKeyEvents(paramInputEvent.getDevice().getDescriptor(), 21, 1)) && (HandleKeyEvents(paramInputEvent.getDevice().getDescriptor(), 22, 0))) {}
        for (bool2 = true;; bool2 = false) {
          break;
        }
      }
      int i = Float.compare(f1, 0.0F);
      bool2 = false;
      if (i != 0) {
        break;
      }
      Log("Dpad left/right released (MotionEvent)");
      if ((HandleKeyEvents(paramInputEvent.getDevice().getDescriptor(), 21, 1)) && (HandleKeyEvents(paramInputEvent.getDevice().getDescriptor(), 22, 1))) {}
      for (bool2 = true;; bool2 = false) {
        break;
      }
      label276:
      bool2 = false;
      continue;
      label281:
      if (Float.compare(f2, 1.0F) == 0)
      {
        Log("Dpad down pressed (MotionEvent)");
        if ((HandleKeyEvents(paramInputEvent.getDevice().getDescriptor(), 19, 1)) && (HandleKeyEvents(paramInputEvent.getDevice().getDescriptor(), 20, 0))) {}
        for (bool2 = true;; bool2 = false) {
          break;
        }
      }
      if (Float.compare(f2, 0.0F) == 0)
      {
        Log("Dpad up/down released (MotionEvent)");
        if ((HandleKeyEvents(paramInputEvent.getDevice().getDescriptor(), 19, 1)) && (HandleKeyEvents(paramInputEvent.getDevice().getDescriptor(), 20, 1))) {}
        for (bool2 = true;; bool2 = false) {
          break;
        }
        boolean bool1 = paramInputEvent instanceof KeyEvent;
        bool2 = false;
        if (bool1)
        {
          KeyEvent localKeyEvent = (KeyEvent)paramInputEvent;
          switch (localKeyEvent.getKeyCode())
          {
          default: 
            bool2 = false;
            break;
          case 19: 
          case 20: 
          case 21: 
          case 22: 
          case 23: 
            bool2 = HandleKeyEvents(localKeyEvent.getDevice().getDescriptor(), localKeyEvent.getKeyCode(), localKeyEvent.getAction());
            Log("Dpad center pressed (KeyEvent)");
          }
        }
      }
    }
  }
  
  @TargetApi(16)
  boolean HandleJoystick(MotionEvent paramMotionEvent)
  {
    boolean bool = HandleDpad(paramMotionEvent);
    if (paramMotionEvent.getAction() == 2)
    {
      float f1 = GetJoystickAxisValue(0, paramMotionEvent);
      float f2 = GetJoystickAxisValue(1, paramMotionEvent);
      float f3 = GetJoystickAxisValue(11, paramMotionEvent);
      float f4 = GetJoystickAxisValue(14, paramMotionEvent);
      float f5 = f3 + GetJoystickAxisValue(12, paramMotionEvent);
      float f6 = f4 + GetJoystickAxisValue(13, paramMotionEvent);
      InputDevice localInputDevice = paramMotionEvent.getDevice();
      float f7;
      float f8;
      if (localInputDevice.getMotionRange(17) != null)
      {
        f7 = GetJoystickAxisValue(17, paramMotionEvent);
        if (localInputDevice.getMotionRange(18) == null) {
          break label220;
        }
        f8 = GetJoystickAxisValue(18, paramMotionEvent);
      }
      for (;;)
      {
        String str = paramMotionEvent.getDevice().getDescriptor();
        SetJoystickValues(str, f1, ControllerAxis.AXIS_LTHUMB_X);
        SetJoystickValues(str, f2, ControllerAxis.AXIS_LTHUMB_Y);
        SetJoystickValues(str, f5, ControllerAxis.AXIS_RTHUMB_X);
        SetJoystickValues(str, f6, ControllerAxis.AXIS_RTHUMB_Y);
        SetJoystickValues(str, f7, ControllerAxis.AXIS_LTRIGGER);
        SetJoystickValues(str, f8, ControllerAxis.AXIS_RTRIGGER);
        return true;
        InputDevice.MotionRange localMotionRange1 = localInputDevice.getMotionRange(23);
        f7 = 0.0F;
        if (localMotionRange1 == null) {
          break;
        }
        f7 = GetJoystickAxisValue(23, paramMotionEvent);
        break;
        label220:
        if (localInputDevice.getMotionRange(22) != null)
        {
          f8 = GetJoystickAxisValue(22, paramMotionEvent);
        }
        else
        {
          InputDevice.MotionRange localMotionRange2 = localInputDevice.getMotionRange(19);
          f8 = 0.0F;
          if (localMotionRange2 != null) {
            f8 = GetJoystickAxisValue(19, paramMotionEvent);
          }
        }
      }
    }
    return bool;
  }
  
  @TargetApi(16)
  public boolean HandleKeyEvents(KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent == null) || (paramKeyEvent.getDevice() == null) || (!IsAtLeastJellyBean()) || (paramKeyEvent.getAction() == 2)) {}
    do
    {
      int i;
      do
      {
        return false;
        if (!IsControllerEvent(paramKeyEvent.getSource())) {
          break;
        }
        i = paramKeyEvent.getKeyCode();
      } while (((i == 104) && (HasLTrigger(paramKeyEvent.getDevice()))) || ((i == 105) && (HasRTrigger(paramKeyEvent.getDevice()))));
      return HandleKeyEvents(paramKeyEvent.getDevice().getDescriptor(), paramKeyEvent.getKeyCode(), paramKeyEvent.getAction());
      if (IsJoystickEvent(paramKeyEvent.getSource()))
      {
        Log("Got key input from a joystick. KeyCode = " + paramKeyEvent.getKeyCode() + " action = " + paramKeyEvent.getAction());
        return HandleKeyEvents(paramKeyEvent.getDevice().getDescriptor(), paramKeyEvent.getKeyCode(), paramKeyEvent.getAction());
      }
    } while (!IsDpadEvent(paramKeyEvent.getSource()));
    return HandleDpad(paramKeyEvent);
  }
  
  public boolean HandleKeyEvents(final String paramString, int paramInt1, int paramInt2)
  {
    final int i = 1;
    boolean bool = true;
    int j = -1;
    switch (paramInt1)
    {
    default: 
      Log("Unhandled key event: " + paramInt1);
      bool = false;
      if ((bool) && (j != -1)) {
        if (paramInt2 == i) {
          break label508;
        }
      }
      break;
    }
    for (;;)
    {
      final int k = j;
      this.m_mainActivity.getGLView().queueEvent(new Runnable()
      {
        public void run()
        {
          ControllerManager.this.SetButtonValueJNI(paramString, i, k);
        }
      });
      return bool;
      PrintButtonString("BUTTON_A", paramInt2);
      j = ControllerButtons.BTN_A.ordinal();
      break;
      PrintButtonString("BUTTON_B", paramInt2);
      j = ControllerButtons.BTN_B.ordinal();
      break;
      PrintButtonString("BUTTON_X", paramInt2);
      j = ControllerButtons.BTN_X.ordinal();
      break;
      PrintButtonString("BUTTON_Y", paramInt2);
      j = ControllerButtons.BTN_Y.ordinal();
      break;
      PrintButtonString("BUTTON_L1", paramInt2);
      j = ControllerButtons.BTN_L1.ordinal();
      break;
      PrintButtonString("BUTTON_R1", paramInt2);
      j = ControllerButtons.BTN_R1.ordinal();
      break;
      PrintButtonString("BUTTON_L2", paramInt2);
      j = ControllerButtons.BTN_L2.ordinal();
      break;
      PrintButtonString("BUTTON_R2", paramInt2);
      j = ControllerButtons.BTN_R2.ordinal();
      break;
      PrintButtonString("BUTTON_THUMB_L", paramInt2);
      break;
      PrintButtonString("BUTTON_THUMB_R", paramInt2);
      break;
      PrintButtonString("DPAD_LEFT", paramInt2);
      j = ControllerButtons.BTN_DPAD_LEFT.ordinal();
      break;
      PrintButtonString("DPAD_UP", paramInt2);
      j = ControllerButtons.BTN_DPAD_UP.ordinal();
      break;
      PrintButtonString("DPAD_RIGHT", paramInt2);
      j = ControllerButtons.BTN_DPAD_RIGHT.ordinal();
      break;
      PrintButtonString("DPAD_RIGHT", paramInt2);
      j = ControllerButtons.BTN_DPAD_DOWN.ordinal();
      break;
      PrintButtonString("BUTTON_START", paramInt2);
      j = ControllerButtons.BTN_START.ordinal();
      break;
      PrintButtonString("BUTTON_SELECT", paramInt2);
      j = ControllerButtons.BTN_SELECT.ordinal();
      break;
      label508:
      i = 0;
    }
  }
  
  public boolean HandleMotionEvents(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent != null) && (paramMotionEvent.getDevice() != null) && (IsAtLeastJellyBean()))
    {
      if (!IsControllerEvent(paramMotionEvent.getSource())) {
        break label38;
      }
      Log("Got motion input from a gamepad??");
    }
    label38:
    do
    {
      return false;
      if (IsJoystickEvent(paramMotionEvent.getSource())) {
        return HandleJoystick(paramMotionEvent);
      }
    } while (!IsDpadEvent(paramMotionEvent.getSource()));
    return HandleDpad(paramMotionEvent);
  }
  
  boolean HasLTrigger(InputDevice paramInputDevice)
  {
    return (paramInputDevice.getMotionRange(17) != null) || (paramInputDevice.getMotionRange(23) != null);
  }
  
  boolean HasRTrigger(InputDevice paramInputDevice)
  {
    return (paramInputDevice.getMotionRange(18) != null) || (paramInputDevice.getMotionRange(22) != null) || (paramInputDevice.getMotionRange(19) != null);
  }
  
  public boolean IsAtLeastJellyBean()
  {
    return MainActivity.IsAtLeastAPI(16);
  }
  
  public boolean IsAtLeastKitKat()
  {
    return MainActivity.IsAtLeastAPI(19);
  }
  
  boolean IsControllerEvent(int paramInt)
  {
    return (paramInt & 0x401) == 1025;
  }
  
  boolean IsDpadEvent(int paramInt)
  {
    return (paramInt & 0x201) == 513;
  }
  
  boolean IsJoystickEvent(int paramInt)
  {
    return (paramInt & 0x1000010) == 16777232;
  }
  
  public void Log(String paramString)
  {
    if ((this.m_bEnableLogging) && (paramString.length() > 0)) {
      Log.i("RR3ControllerManager", paramString);
    }
  }
  
  public void LogError(String paramString)
  {
    Log.e("RR3ControllerManager", paramString);
  }
  
  void PrintButtonString(String paramString, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder().append(paramString).append(" ");
    if (paramInt == 0) {}
    for (String str = "pressed";; str = "released")
    {
      Log(str);
      return;
    }
  }
  
  protected native void SetButtonValueJNI(String paramString, boolean paramBoolean, int paramInt);
  
  protected native void SetJoystickValueJNI(String paramString, float paramFloat, int paramInt);
  
  public void SetJoystickValues(final String paramString, final float paramFloat, final ControllerAxis paramControllerAxis)
  {
    this.m_mainActivity.getGLView().queueEvent(new Runnable()
    {
      public void run()
      {
        ControllerManager.this.SetJoystickValueJNI(paramString, paramFloat, paramControllerAxis.ordinal());
      }
    });
  }
  
  public void onDestroy()
  {
    Log("onDestroy");
    if (this.m_controllerManager_Moga != null) {
      this.m_controllerManager_Moga.onDestroy();
    }
    if (this.m_inputListener != null) {
      this.m_inputListener.onDestroy();
    }
  }
  
  public void onPause()
  {
    Log("onPause");
    if (this.m_controllerManager_Moga != null) {
      this.m_controllerManager_Moga.onPause();
    }
  }
  
  public void onResume()
  {
    Log("onResume");
    if (this.m_controllerManager_Moga != null) {
      this.m_controllerManager_Moga.onResume();
    }
    if (this.m_inputListener != null) {
      this.m_inputListener.CheckExistingControllers();
    }
  }
  
  public static enum ControllerAxis
  {
    static
    {
      AXIS_LTRIGGER = new ControllerAxis("AXIS_LTRIGGER", 4);
      AXIS_RTRIGGER = new ControllerAxis("AXIS_RTRIGGER", 5);
      AXIS_COUNT = new ControllerAxis("AXIS_COUNT", 6);
      ControllerAxis[] arrayOfControllerAxis = new ControllerAxis[7];
      arrayOfControllerAxis[0] = AXIS_LTHUMB_X;
      arrayOfControllerAxis[1] = AXIS_LTHUMB_Y;
      arrayOfControllerAxis[2] = AXIS_RTHUMB_X;
      arrayOfControllerAxis[3] = AXIS_RTHUMB_Y;
      arrayOfControllerAxis[4] = AXIS_LTRIGGER;
      arrayOfControllerAxis[5] = AXIS_RTRIGGER;
      arrayOfControllerAxis[6] = AXIS_COUNT;
      $VALUES = arrayOfControllerAxis;
    }
    
    private ControllerAxis() {}
  }
  
  public static enum ControllerButtons
  {
    static
    {
      BTN_L1 = new ControllerButtons("BTN_L1", 4);
      BTN_R1 = new ControllerButtons("BTN_R1", 5);
      BTN_L2 = new ControllerButtons("BTN_L2", 6);
      BTN_R2 = new ControllerButtons("BTN_R2", 7);
      BTN_DPAD_LEFT = new ControllerButtons("BTN_DPAD_LEFT", 8);
      BTN_DPAD_RIGHT = new ControllerButtons("BTN_DPAD_RIGHT", 9);
      BTN_DPAD_UP = new ControllerButtons("BTN_DPAD_UP", 10);
      BTN_DPAD_DOWN = new ControllerButtons("BTN_DPAD_DOWN", 11);
      BTN_SELECT = new ControllerButtons("BTN_SELECT", 12);
      BTN_START = new ControllerButtons("BTN_START", 13);
      BTN_COUNT = new ControllerButtons("BTN_COUNT", 14);
      ControllerButtons[] arrayOfControllerButtons = new ControllerButtons[15];
      arrayOfControllerButtons[0] = BTN_A;
      arrayOfControllerButtons[1] = BTN_B;
      arrayOfControllerButtons[2] = BTN_X;
      arrayOfControllerButtons[3] = BTN_Y;
      arrayOfControllerButtons[4] = BTN_L1;
      arrayOfControllerButtons[5] = BTN_R1;
      arrayOfControllerButtons[6] = BTN_L2;
      arrayOfControllerButtons[7] = BTN_R2;
      arrayOfControllerButtons[8] = BTN_DPAD_LEFT;
      arrayOfControllerButtons[9] = BTN_DPAD_RIGHT;
      arrayOfControllerButtons[10] = BTN_DPAD_UP;
      arrayOfControllerButtons[11] = BTN_DPAD_DOWN;
      arrayOfControllerButtons[12] = BTN_SELECT;
      arrayOfControllerButtons[13] = BTN_START;
      arrayOfControllerButtons[14] = BTN_COUNT;
      $VALUES = arrayOfControllerButtons;
    }
    
    private ControllerButtons() {}
  }
  
  @TargetApi(16)
  public class ControllerInputListener
    implements InputManager.InputDeviceListener
  {
    ControllerManager m_controllerManager = null;
    InputManager m_inputManager = null;
    
    ControllerInputListener(ControllerManager paramControllerManager)
    {
      this.m_controllerManager = paramControllerManager;
      this.m_inputManager = ((InputManager)ControllerManager.this.m_mainActivity.getSystemService("input"));
      this.m_inputManager.registerInputDeviceListener(this, ControllerManager.this.m_mainActivity.handler);
      this.m_inputManager.getInputDevice(-1);
      CheckExistingControllers();
    }
    
    public void CheckExistingControllers()
    {
      int[] arrayOfInt = this.m_inputManager.getInputDeviceIds();
      int i = arrayOfInt.length;
      int j = 0;
      if (j < i)
      {
        int k = arrayOfInt[j];
        InputDevice localInputDevice = this.m_inputManager.getInputDevice(k);
        int m;
        if ((0x401 & localInputDevice.getSources()) == 1025)
        {
          m = 1;
          label52:
          if ((0x1000010 & localInputDevice.getSources()) != 16777232) {
            break label149;
          }
        }
        label149:
        for (int n = 1;; n = 0)
        {
          if ((m != 0) && (n != 0))
          {
            ControllerManager.this.Log("CheckExistingControllers::" + localInputDevice.toString());
            int i1 = GetOptionalButtonFlags(localInputDevice);
            this.m_controllerManager.ControllerConnected(localInputDevice.getName(), localInputDevice.getDescriptor(), k, i1);
          }
          j++;
          break;
          m = 0;
          break label52;
        }
      }
    }
    
    int GetOptionalButtonFlags(InputDevice paramInputDevice)
    {
      if (ControllerManager.this.IsAtLeastKitKat()) {}
      for (boolean[] arrayOfBoolean = paramInputDevice.hasKeys(new int[] { 102, 104, 103, 105 });; arrayOfBoolean = new boolean[] { 1, 0, 1, 0 })
      {
        int i = arrayOfBoolean[0];
        int j = 0;
        if (i != 0) {
          j = 0x0 | 0x1;
        }
        if (arrayOfBoolean[1] != 0) {
          j |= 0x2;
        }
        if (arrayOfBoolean[2] != 0) {
          j |= 0x8;
        }
        if (arrayOfBoolean[3] != 0) {
          j |= 0x10;
        }
        if (ControllerManager.this.HasLTrigger(paramInputDevice)) {
          j |= 0x4;
        }
        if (ControllerManager.this.HasRTrigger(paramInputDevice)) {
          j |= 0x20;
        }
        return j;
      }
    }
    
    public void onDestroy()
    {
      this.m_inputManager.unregisterInputDeviceListener(this);
    }
    
    public void onInputDeviceAdded(int paramInt)
    {
      InputDevice localInputDevice = this.m_inputManager.getInputDevice(paramInt);
      int i;
      if (localInputDevice != null)
      {
        if ((0x401 & localInputDevice.getSources()) != 1025) {
          break label108;
        }
        i = 1;
        if ((0x1000010 & localInputDevice.getSources()) != 16777232) {
          break label113;
        }
      }
      label108:
      label113:
      for (int j = 1;; j = 0)
      {
        if ((i != 0) && (j != 0))
        {
          ControllerManager.this.Log("onInputDeviceAdded::" + localInputDevice.toString());
          int k = GetOptionalButtonFlags(localInputDevice);
          this.m_controllerManager.ControllerConnected(localInputDevice.getName(), localInputDevice.getDescriptor(), paramInt, k);
        }
        return;
        i = 0;
        break;
      }
    }
    
    public void onInputDeviceChanged(int paramInt) {}
    
    public void onInputDeviceRemoved(int paramInt)
    {
      ControllerManager.this.Log("onInputDeviceRemoved::Id(" + paramInt + ")");
      this.m_controllerManager.ControllerDisconnected(paramInt);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.ControllerManager
 * JD-Core Version:    0.7.0.1
 */