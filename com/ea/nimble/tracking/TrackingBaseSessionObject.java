package com.ea.nimble.tracking;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrackingBaseSessionObject
  implements Externalizable
{
  private static final long serialVersionUID = 1L;
  public List<Map<String, String>> events = new ArrayList();
  public int repostCount = 0;
  public Map<String, Object> sessionData = new HashMap();
  
  public int countOfEvents()
  {
    if (this.events == null) {
      return 0;
    }
    return this.events.size();
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.events = ((List)paramObjectInput.readObject());
    this.sessionData = ((Map)paramObjectInput.readObject());
    this.repostCount = paramObjectInput.readInt();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(this.events);
    paramObjectOutput.writeObject(this.sessionData);
    paramObjectOutput.writeInt(this.repostCount);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.tracking.TrackingBaseSessionObject
 * JD-Core Version:    0.7.0.1
 */