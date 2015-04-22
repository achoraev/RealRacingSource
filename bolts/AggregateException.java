package bolts;

import java.util.List;

public class AggregateException
  extends Exception
{
  private static final long serialVersionUID = 1L;
  private List<Exception> errors;
  
  public AggregateException(List<Exception> paramList)
  {
    super("There were multiple errors.");
    this.errors = paramList;
  }
  
  public List<Exception> getErrors()
  {
    return this.errors;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     bolts.AggregateException
 * JD-Core Version:    0.7.0.1
 */