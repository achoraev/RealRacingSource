package com.supersonicads.sdk.data;

public class SSAEventCalendar
  extends SSAObj
{
  private String DAILY = "daily";
  private String DAYS_IN_MONTH = "daysInMonth";
  private String DAYS_IN_WEEK = "daysInWeek";
  private String DAYS_IN_YEAR = "daysInYear";
  private String DESCRIPTION = "description";
  private String END = "end";
  private String EXCEPTIONDATES = "exceptionDates";
  private String EXPIRES = "expires";
  private String FREQUENCY = "frequency";
  private String ID = "id";
  private String INTERVAL = "interval";
  private String MONTHLY = "monthly";
  private String MONTHS_IN_YEAR = "monthsInYear";
  private String RECURRENCE = "recurrence";
  private String REMINDER = "reminder";
  private String START = "start";
  private String STATUS = "status";
  private String WEEKLY = "weekly";
  private String WEEKS_IN_MONTH = "weeksInMonth";
  private String YEARLY = "yearly";
  private String mDescription;
  private String mEnd;
  private String mStart;
  
  public SSAEventCalendar(String paramString)
  {
    super(paramString);
    if (containsKey(this.DESCRIPTION)) {
      setDescription(getString(this.DESCRIPTION));
    }
    if (containsKey(this.START)) {
      setStart(getString(this.START));
    }
    if (containsKey(this.END)) {
      setEnd(getString(this.END));
    }
  }
  
  public String getDescription()
  {
    return this.mDescription;
  }
  
  public String getEnd()
  {
    return this.mEnd;
  }
  
  public String getStart()
  {
    return this.mStart;
  }
  
  public void setDescription(String paramString)
  {
    this.mDescription = paramString;
  }
  
  public void setEnd(String paramString)
  {
    this.mEnd = paramString;
  }
  
  public void setStart(String paramString)
  {
    this.mStart = paramString;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.data.SSAEventCalendar
 * JD-Core Version:    0.7.0.1
 */