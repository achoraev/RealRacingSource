package bolts;

public abstract interface Continuation<TTaskResult, TContinuationResult>
{
  public abstract TContinuationResult then(Task<TTaskResult> paramTask)
    throws Exception;
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     bolts.Continuation
 * JD-Core Version:    0.7.0.1
 */