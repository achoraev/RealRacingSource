package com.google.gson.stream;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JsonWriter
  implements Closeable, Flushable
{
  private static final String[] HTML_SAFE_REPLACEMENT_CHARS;
  private static final String[] REPLACEMENT_CHARS = new String[''];
  private String deferredName;
  private boolean htmlSafe;
  private String indent;
  private boolean lenient;
  private final Writer out;
  private String separator;
  private boolean serializeNulls;
  private final List<JsonScope> stack = new ArrayList();
  
  static
  {
    for (int i = 0; i <= 31; i++)
    {
      String[] arrayOfString = REPLACEMENT_CHARS;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(i);
      arrayOfString[i] = String.format("\\u%04x", arrayOfObject);
    }
    REPLACEMENT_CHARS[34] = "\\\"";
    REPLACEMENT_CHARS[92] = "\\\\";
    REPLACEMENT_CHARS[9] = "\\t";
    REPLACEMENT_CHARS[8] = "\\b";
    REPLACEMENT_CHARS[10] = "\\n";
    REPLACEMENT_CHARS[13] = "\\r";
    REPLACEMENT_CHARS[12] = "\\f";
    HTML_SAFE_REPLACEMENT_CHARS = (String[])REPLACEMENT_CHARS.clone();
    HTML_SAFE_REPLACEMENT_CHARS[60] = "\\u003c";
    HTML_SAFE_REPLACEMENT_CHARS[62] = "\\u003e";
    HTML_SAFE_REPLACEMENT_CHARS[38] = "\\u0026";
    HTML_SAFE_REPLACEMENT_CHARS[61] = "\\u003d";
    HTML_SAFE_REPLACEMENT_CHARS[39] = "\\u0027";
  }
  
  public JsonWriter(Writer paramWriter)
  {
    this.stack.add(JsonScope.EMPTY_DOCUMENT);
    this.separator = ":";
    this.serializeNulls = true;
    if (paramWriter == null) {
      throw new NullPointerException("out == null");
    }
    this.out = paramWriter;
  }
  
  private void beforeName()
    throws IOException
  {
    JsonScope localJsonScope = peek();
    if (localJsonScope == JsonScope.NONEMPTY_OBJECT) {
      this.out.write(44);
    }
    while (localJsonScope == JsonScope.EMPTY_OBJECT)
    {
      newline();
      replaceTop(JsonScope.DANGLING_NAME);
      return;
    }
    throw new IllegalStateException("Nesting problem: " + this.stack);
  }
  
  private void beforeValue(boolean paramBoolean)
    throws IOException
  {
    switch (1.$SwitchMap$com$google$gson$stream$JsonScope[peek().ordinal()])
    {
    default: 
      throw new IllegalStateException("Nesting problem: " + this.stack);
    case 1: 
      if (!this.lenient) {
        throw new IllegalStateException("JSON must have only one top-level value.");
      }
    case 2: 
      if ((!this.lenient) && (!paramBoolean)) {
        throw new IllegalStateException("JSON must start with an array or an object.");
      }
      replaceTop(JsonScope.NONEMPTY_DOCUMENT);
      return;
    case 3: 
      replaceTop(JsonScope.NONEMPTY_ARRAY);
      newline();
      return;
    case 4: 
      this.out.append(',');
      newline();
      return;
    }
    this.out.append(this.separator);
    replaceTop(JsonScope.NONEMPTY_OBJECT);
  }
  
  private JsonWriter close(JsonScope paramJsonScope1, JsonScope paramJsonScope2, String paramString)
    throws IOException
  {
    JsonScope localJsonScope = peek();
    if ((localJsonScope != paramJsonScope2) && (localJsonScope != paramJsonScope1)) {
      throw new IllegalStateException("Nesting problem: " + this.stack);
    }
    if (this.deferredName != null) {
      throw new IllegalStateException("Dangling name: " + this.deferredName);
    }
    this.stack.remove(-1 + this.stack.size());
    if (localJsonScope == paramJsonScope2) {
      newline();
    }
    this.out.write(paramString);
    return this;
  }
  
  private void newline()
    throws IOException
  {
    if (this.indent == null) {}
    for (;;)
    {
      return;
      this.out.write("\n");
      for (int i = 1; i < this.stack.size(); i++) {
        this.out.write(this.indent);
      }
    }
  }
  
  private JsonWriter open(JsonScope paramJsonScope, String paramString)
    throws IOException
  {
    beforeValue(true);
    this.stack.add(paramJsonScope);
    this.out.write(paramString);
    return this;
  }
  
  private JsonScope peek()
  {
    int i = this.stack.size();
    if (i == 0) {
      throw new IllegalStateException("JsonWriter is closed.");
    }
    return (JsonScope)this.stack.get(i - 1);
  }
  
  private void replaceTop(JsonScope paramJsonScope)
  {
    this.stack.set(-1 + this.stack.size(), paramJsonScope);
  }
  
  private void string(String paramString)
    throws IOException
  {
    if (this.htmlSafe) {}
    int i;
    int j;
    int k;
    int m;
    for (String[] arrayOfString = HTML_SAFE_REPLACEMENT_CHARS;; arrayOfString = REPLACEMENT_CHARS)
    {
      this.out.write("\"");
      i = 0;
      j = paramString.length();
      for (k = 0;; k++)
      {
        if (k >= j) {
          break label141;
        }
        m = paramString.charAt(k);
        if (m >= 128) {
          break;
        }
        str = arrayOfString[m];
        if (str != null) {
          break label90;
        }
      }
    }
    if (m == 8232) {}
    for (String str = "\\u2028";; str = "\\u2029")
    {
      label90:
      if (i < k) {
        this.out.write(paramString, i, k - i);
      }
      this.out.write(str);
      i = k + 1;
      break;
      if (m != 8233) {
        break;
      }
    }
    label141:
    if (i < j) {
      this.out.write(paramString, i, j - i);
    }
    this.out.write("\"");
  }
  
  private void writeDeferredName()
    throws IOException
  {
    if (this.deferredName != null)
    {
      beforeName();
      string(this.deferredName);
      this.deferredName = null;
    }
  }
  
  public JsonWriter beginArray()
    throws IOException
  {
    writeDeferredName();
    return open(JsonScope.EMPTY_ARRAY, "[");
  }
  
  public JsonWriter beginObject()
    throws IOException
  {
    writeDeferredName();
    return open(JsonScope.EMPTY_OBJECT, "{");
  }
  
  public void close()
    throws IOException
  {
    this.out.close();
    int i = this.stack.size();
    if ((i > 1) || ((i == 1) && (this.stack.get(i - 1) != JsonScope.NONEMPTY_DOCUMENT))) {
      throw new IOException("Incomplete document");
    }
    this.stack.clear();
  }
  
  public JsonWriter endArray()
    throws IOException
  {
    return close(JsonScope.EMPTY_ARRAY, JsonScope.NONEMPTY_ARRAY, "]");
  }
  
  public JsonWriter endObject()
    throws IOException
  {
    return close(JsonScope.EMPTY_OBJECT, JsonScope.NONEMPTY_OBJECT, "}");
  }
  
  public void flush()
    throws IOException
  {
    if (this.stack.isEmpty()) {
      throw new IllegalStateException("JsonWriter is closed.");
    }
    this.out.flush();
  }
  
  public final boolean getSerializeNulls()
  {
    return this.serializeNulls;
  }
  
  public final boolean isHtmlSafe()
  {
    return this.htmlSafe;
  }
  
  public boolean isLenient()
  {
    return this.lenient;
  }
  
  public JsonWriter name(String paramString)
    throws IOException
  {
    if (paramString == null) {
      throw new NullPointerException("name == null");
    }
    if (this.deferredName != null) {
      throw new IllegalStateException();
    }
    if (this.stack.isEmpty()) {
      throw new IllegalStateException("JsonWriter is closed.");
    }
    this.deferredName = paramString;
    return this;
  }
  
  public JsonWriter nullValue()
    throws IOException
  {
    if (this.deferredName != null)
    {
      if (this.serializeNulls) {
        writeDeferredName();
      }
    }
    else
    {
      beforeValue(false);
      this.out.write("null");
      return this;
    }
    this.deferredName = null;
    return this;
  }
  
  public final void setHtmlSafe(boolean paramBoolean)
  {
    this.htmlSafe = paramBoolean;
  }
  
  public final void setIndent(String paramString)
  {
    if (paramString.length() == 0)
    {
      this.indent = null;
      this.separator = ":";
      return;
    }
    this.indent = paramString;
    this.separator = ": ";
  }
  
  public final void setLenient(boolean paramBoolean)
  {
    this.lenient = paramBoolean;
  }
  
  public final void setSerializeNulls(boolean paramBoolean)
  {
    this.serializeNulls = paramBoolean;
  }
  
  public JsonWriter value(double paramDouble)
    throws IOException
  {
    if ((Double.isNaN(paramDouble)) || (Double.isInfinite(paramDouble))) {
      throw new IllegalArgumentException("Numeric values must be finite, but was " + paramDouble);
    }
    writeDeferredName();
    beforeValue(false);
    this.out.append(Double.toString(paramDouble));
    return this;
  }
  
  public JsonWriter value(long paramLong)
    throws IOException
  {
    writeDeferredName();
    beforeValue(false);
    this.out.write(Long.toString(paramLong));
    return this;
  }
  
  public JsonWriter value(Number paramNumber)
    throws IOException
  {
    if (paramNumber == null) {
      return nullValue();
    }
    writeDeferredName();
    String str = paramNumber.toString();
    if ((!this.lenient) && ((str.equals("-Infinity")) || (str.equals("Infinity")) || (str.equals("NaN")))) {
      throw new IllegalArgumentException("Numeric values must be finite, but was " + paramNumber);
    }
    beforeValue(false);
    this.out.append(str);
    return this;
  }
  
  public JsonWriter value(String paramString)
    throws IOException
  {
    if (paramString == null) {
      return nullValue();
    }
    writeDeferredName();
    beforeValue(false);
    string(paramString);
    return this;
  }
  
  public JsonWriter value(boolean paramBoolean)
    throws IOException
  {
    writeDeferredName();
    beforeValue(false);
    Writer localWriter = this.out;
    if (paramBoolean) {}
    for (String str = "true";; str = "false")
    {
      localWriter.write(str);
      return this;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.gson.stream.JsonWriter
 * JD-Core Version:    0.7.0.1
 */