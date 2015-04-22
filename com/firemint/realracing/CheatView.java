package com.firemint.realracing;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

class CheatView
  extends FrameLayout
  implements TextView.OnEditorActionListener
{
  boolean m_bEnableLogging = false;
  LinearLayout m_layout;
  CheatListener m_listener;
  CheatEditText m_text;
  boolean touchOffToClose;
  
  public CheatView(Context paramContext, CheatListener paramCheatListener)
  {
    super(paramContext);
    this.m_listener = paramCheatListener;
    this.m_text = new CheatEditText(paramContext, this.m_listener);
    this.m_text.setWidth(0);
    this.m_text.setHeight(0);
    this.m_layout = new LinearLayout(paramContext);
    this.m_layout.addView(this.m_text);
    addView(this.m_layout);
    this.touchOffToClose = true;
    this.m_layout.setVisibility(4);
    this.m_text.setRawInputType(129);
    this.m_text.setImeOptions(268435462);
    this.m_text.setOnEditorActionListener(this);
    this.m_text.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable) {}
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        CharSequence localCharSequence = CheatView.this.m_listener.onInputUpdate(true, CheatView.this.m_text.getText());
        if (!localCharSequence.toString().trim().equals(CheatView.this.m_text.getText().toString().trim())) {
          CheatView.this.m_text.setText(localCharSequence);
        }
        CheatView.this.m_text.setSelection(CheatView.this.m_text.getText().length());
      }
    });
  }
  
  void Log(String paramString)
  {
    if ((this.m_bEnableLogging) && (paramString.length() > 0)) {
      Log.i("CHEAT", paramString);
    }
  }
  
  @TargetApi(11)
  public void begin()
  {
    Log("begin");
    if (Build.VERSION.SDK_INT >= 11) {
      MainActivity.instance.getWindow().setSoftInputMode(48);
    }
    this.m_text.setText("");
    onResume();
  }
  
  public void end()
  {
    MainActivity.instance.runOnUiThread(new Runnable()
    {
      public void run()
      {
        CheatView.this.Log("end");
        InputMethodManager localInputMethodManager = (InputMethodManager)CheatView.this.getContext().getSystemService("input_method");
        IBinder localIBinder = CheatView.this.m_text.getWindowToken();
        if (MainActivity.getIsAmazon()) {}
        for (int i = 1;; i = 0)
        {
          localInputMethodManager.hideSoftInputFromWindow(localIBinder, i);
          if (Build.VERSION.SDK_INT >= 11) {
            MainActivity.instance.getWindow().setSoftInputMode(0);
          }
          return;
        }
      }
    });
  }
  
  public boolean onEditorAction(TextView paramTextView, int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 6)
    {
      Log("IME_ACTION_DONE");
      this.m_listener.onInputDone(true, paramTextView.getText());
    }
    return false;
  }
  
  public void onPause()
  {
    end();
  }
  
  public void onResume()
  {
    Log("onResume");
    this.m_text.requestFocus();
    InputMethodManager localInputMethodManager = (InputMethodManager)getContext().getSystemService("input_method");
    CheatEditText localCheatEditText = this.m_text;
    if (MainActivity.getIsAmazon()) {}
    for (int i = 1;; i = 2)
    {
      localInputMethodManager.showSoftInput(localCheatEditText, i);
      return;
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.touchOffToClose)
    {
      if (paramMotionEvent.getActionMasked() == 0)
      {
        Log("cancel");
        this.m_listener.onInputDone(false, "");
      }
      return true;
    }
    onResume();
    return false;
  }
  
  class CheatEditText
    extends EditText
  {
    private CheatView.CheatListener m_cheatListener = null;
    
    CheatEditText(Context paramContext, CheatView.CheatListener paramCheatListener)
    {
      super();
      this.m_cheatListener = paramCheatListener;
    }
    
    public boolean onKeyPreIme(int paramInt, KeyEvent paramKeyEvent)
    {
      if (paramInt == 4)
      {
        CheatView.this.Log("Consume the back key before it gets to the keyboard");
        this.m_cheatListener.onInputDone(false, "");
        return true;
      }
      if (paramInt == 82)
      {
        CheatView.this.Log("Consume the menu key before it gets to the keyboard");
        return true;
      }
      return super.onKeyPreIme(paramInt, paramKeyEvent);
    }
  }
  
  public static abstract interface CheatListener
  {
    public abstract void onInputDone(boolean paramBoolean, CharSequence paramCharSequence);
    
    public abstract CharSequence onInputUpdate(boolean paramBoolean, CharSequence paramCharSequence);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.CheatView
 * JD-Core Version:    0.7.0.1
 */