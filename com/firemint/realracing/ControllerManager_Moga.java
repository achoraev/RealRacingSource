package com.firemint.realracing;

import com.bda.controller.Controller;
import com.bda.controller.ControllerListener;
import com.bda.controller.KeyEvent;
import com.bda.controller.MotionEvent;
import com.bda.controller.StateEvent;

public class ControllerManager_Moga
  implements ControllerListener
{
  Controller m_controller = null;
  ControllerManager m_controllerManager = null;
  MainActivity m_mainActivity = null;
  final String m_strDescriptor = "Moga";
  
  ControllerManager_Moga(MainActivity paramMainActivity, ControllerManager paramControllerManager)
  {
    this.m_mainActivity = paramMainActivity;
    this.m_controllerManager = paramControllerManager;
    this.m_controller = Controller.getInstance(this.m_mainActivity);
    if (this.m_controller != null)
    {
      this.m_controller.init();
      this.m_controller.setListener(this, this.m_mainActivity.handler);
    }
  }
  
  public void onDestroy()
  {
    if (this.m_controller != null)
    {
      this.m_controllerManager.Log("Moga::onDestroy");
      this.m_controller.exit();
    }
  }
  
  public void onKeyEvent(KeyEvent paramKeyEvent)
  {
    int i = paramKeyEvent.getKeyCode();
    if (((i == 104) || (i == 105)) && (this.m_controller.getState(4) == 1)) {
      return;
    }
    this.m_controllerManager.HandleKeyEvents("Moga", paramKeyEvent.getKeyCode(), paramKeyEvent.getAction());
  }
  
  public void onMotionEvent(MotionEvent paramMotionEvent)
  {
    this.m_controllerManager.SetJoystickValues("Moga", paramMotionEvent.getAxisValue(0), ControllerManager.ControllerAxis.AXIS_LTHUMB_X);
    this.m_controllerManager.SetJoystickValues("Moga", paramMotionEvent.getAxisValue(1), ControllerManager.ControllerAxis.AXIS_LTHUMB_Y);
    this.m_controllerManager.SetJoystickValues("Moga", paramMotionEvent.getAxisValue(11), ControllerManager.ControllerAxis.AXIS_RTHUMB_X);
    this.m_controllerManager.SetJoystickValues("Moga", paramMotionEvent.getAxisValue(14), ControllerManager.ControllerAxis.AXIS_RTHUMB_Y);
    this.m_controllerManager.SetJoystickValues("Moga", paramMotionEvent.getAxisValue(17), ControllerManager.ControllerAxis.AXIS_LTRIGGER);
    this.m_controllerManager.SetJoystickValues("Moga", paramMotionEvent.getAxisValue(18), ControllerManager.ControllerAxis.AXIS_RTRIGGER);
  }
  
  public void onPause()
  {
    if (this.m_controller != null)
    {
      this.m_controllerManager.Log("Moga::onPause");
      this.m_controller.onPause();
    }
  }
  
  public void onResume()
  {
    if (this.m_controller != null)
    {
      this.m_controllerManager.Log("Moga::onResume");
      this.m_controller.onResume();
    }
  }
  
  public void onStateEvent(StateEvent paramStateEvent)
  {
    switch (paramStateEvent.getState())
    {
    default: 
      this.m_controllerManager.Log("Moga::Unhandled state event:");
      return;
    case 1: 
      switch (paramStateEvent.getAction())
      {
      default: 
        this.m_controllerManager.Log("Moga::Unhandled action in STATE_CONNECTION::" + paramStateEvent.getAction());
        return;
      case 1: 
        this.m_controllerManager.Log("Moga::ACTION_CONNECTED");
        int i = 9;
        String str = "MOGA Pocket";
        if (this.m_controller.getState(4) == 1)
        {
          str = "MOGA Pro";
          i |= 0x24;
        }
        this.m_controllerManager.ControllerConnected(str, "Moga", 0, i);
        return;
      case 0: 
        this.m_controllerManager.Log("Moga::ACTION_DISCONNECTED");
        this.m_controllerManager.ControllerDisconnected(0);
        return;
      }
      this.m_controllerManager.Log("Moga::ACTION_CONNECTING");
      return;
    }
    if (paramStateEvent.getAction() == 1)
    {
      this.m_controllerManager.Log("Moga::STATE_POWER_LOW (TRUE)");
      return;
    }
    this.m_controllerManager.Log("Moga::STATE_POWER_LOW (FALSE)");
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.ControllerManager_Moga
 * JD-Core Version:    0.7.0.1
 */