package com.firemint.realracing;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class TextField
  extends RelativeLayout
{
  final long m_callbackptr;
  EditText m_text;
  
  public TextField(final Context paramContext, long paramLong, final String paramString)
  {
    super(paramContext);
    logm("TextField() cachecallback ptr");
    this.m_callbackptr = paramLong;
    MainActivity.instance.runOnUiThread(new Runnable()
    {
      public void run()
      {
        TextField.logm("TextField() create text");
        TextField.this.m_text = new EditText(paramContext);
        TextField.this.m_text.setMinimumHeight(0);
        TextField.this.m_text.setMinimumWidth(0);
        TextField.this.m_text.setPadding(0, 0, 0, 0);
        TextField.this.m_text.setGravity(17);
        StringBuilder localStringBuilder = new StringBuilder().append("TextField() configure text");
        if (paramString == null) {}
        for (String str = "null";; str = paramString)
        {
          TextField.logm(str);
          TextField.this.m_text.setText(paramString);
          TextField.this.m_text.setImeOptions(6);
          TextField.this.m_text.setRawInputType(262145);
          TextField.logm("TextField() add change listener");
          TextField.this.m_text.addTextChangedListener(new TextWatcher()
          {
            public void afterTextChanged(Editable paramAnonymous2Editable)
            {
              TextField.this.TextInputChanged(paramAnonymous2Editable.toString());
            }
            
            public void beforeTextChanged(CharSequence paramAnonymous2CharSequence, int paramAnonymous2Int1, int paramAnonymous2Int2, int paramAnonymous2Int3) {}
            
            public void onTextChanged(CharSequence paramAnonymous2CharSequence, int paramAnonymous2Int1, int paramAnonymous2Int2, int paramAnonymous2Int3) {}
          });
          TextField.logm("TextField() add action listener");
          TextField.this.m_text.setOnEditorActionListener(new TextView.OnEditorActionListener()
          {
            public boolean onEditorAction(TextView paramAnonymous2TextView, int paramAnonymous2Int, KeyEvent paramAnonymous2KeyEvent)
            {
              if (paramAnonymous2Int == 6)
              {
                TextField.logm("User done");
                paramAnonymous2TextView.setActivated(false);
                paramAnonymous2TextView.setSelected(false);
              }
              return false;
            }
          });
          TextField.this.setBounds(0, 0, 0, 0);
          TextField.this.addView(TextField.this.m_text);
          return;
        }
      }
    });
  }
  
  public static void logm(String paramString)
  {
    Log.i("RR3TXTFLD", paramString);
  }
  
  public void TextInputChanged(String paramString)
  {
    logm("textInputChanged. UserText: " + paramString);
    if (!onTextInputChanged(paramString, this.m_callbackptr).equals(paramString))
    {
      logm("textInputChanged. ValidatedText: " + paramString);
      this.m_text.setText(paramString);
    }
  }
  
  public native String onTextInputChanged(String paramString, long paramLong);
  
  public void setBackgroundColor(final int paramInt1, final int paramInt2, final int paramInt3, final int paramInt4)
  {
    MainActivity.instance.runOnUiThread(new Runnable()
    {
      public void run()
      {
        int i = Color.argb(paramInt4, paramInt1, paramInt2, paramInt3);
        TextField.logm("start setBackgroundColor(): " + Integer.toHexString(i));
        if (TextField.this.m_text == null) {
          return;
        }
        TextField.this.m_text.getBackground().setColorFilter(i, PorterDuff.Mode.MULTIPLY);
        TextField.logm("end setBackgroundColor()" + Integer.toHexString(i));
      }
    });
  }
  
  public void setBounds(final int paramInt1, final int paramInt2, final int paramInt3, final int paramInt4)
  {
    MainActivity.instance.runOnUiThread(new Runnable()
    {
      public void run()
      {
        if ((TextField.this.m_text == null) || (TextField.this.m_text.isSelected())) {
          return;
        }
        TextField.this.m_text.setX(paramInt1);
        TextField.this.m_text.setY(paramInt2);
        TextField.this.m_text.setWidth(paramInt3);
        TextField.this.m_text.setHeight(paramInt4);
        TextField.this.m_text.requestLayout();
        TextField.this.requestLayout();
      }
    });
  }
  
  public void setEnabled(final boolean paramBoolean)
  {
    MainActivity.instance.runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (TextField.this.m_text == null) {
          return;
        }
        TextField.this.m_text.setFocusable(paramBoolean);
      }
    });
  }
  
  public void setHint(final String paramString)
  {
    MainActivity.instance.runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (TextField.this.m_text == null) {
          return;
        }
        TextField.this.m_text.setHint(paramString);
      }
    });
  }
  
  public void setText(final String paramString)
  {
    MainActivity.instance.runOnUiThread(new Runnable()
    {
      public void run()
      {
        TextField.logm("start setText: " + paramString);
        if (TextField.this.m_text == null) {}
        while (paramString.equals(TextField.this.m_text.getText().toString())) {
          return;
        }
        TextField.this.m_text.setText(paramString);
        TextField.logm("end setText: " + paramString);
      }
    });
  }
  
  public void setTextColor(final int paramInt1, final int paramInt2, final int paramInt3, final int paramInt4)
  {
    MainActivity.instance.runOnUiThread(new Runnable()
    {
      public void run()
      {
        int i = Color.argb(paramInt4, paramInt1, paramInt2, paramInt3);
        TextField.logm("start setTextColor(): " + Integer.toHexString(i));
        if (TextField.this.m_text == null) {
          return;
        }
        TextField.this.m_text.setTextColor(i);
        TextField.logm("end setTextColor(): " + Integer.toHexString(i));
      }
    });
  }
  
  public void setVisible(final boolean paramBoolean)
  {
    MainActivity.instance.runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (TextField.this.m_text == null) {
          return;
        }
        EditText localEditText = TextField.this.m_text;
        if (paramBoolean) {}
        for (int i = 0;; i = 4)
        {
          localEditText.setVisibility(i);
          return;
        }
      }
    });
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.TextField
 * JD-Core Version:    0.7.0.1
 */