package com.google.ads.mediation.millennial;

import android.location.Location;
import com.google.ads.mediation.NetworkExtras;

public final class MillennialAdapterExtras
  implements NetworkExtras
{
  public static final int NOT_SET = -1;
  private int age = -1;
  private Children children = null;
  private Education education = null;
  private Ethnicity ethnicity = null;
  private Gender gender = null;
  private Integer income = null;
  private InterstitialTime interstitialTime = InterstitialTime.UNKNOWN;
  private String keywords = null;
  private Location location = null;
  private MaritalStatus maritalStatus = null;
  private Politics politics = null;
  private String postalCode = null;
  
  public MillennialAdapterExtras clearAge()
  {
    return setAge(-1);
  }
  
  public MillennialAdapterExtras clearChildren()
  {
    return setChildren(null);
  }
  
  public MillennialAdapterExtras clearEducation()
  {
    return setEducation(null);
  }
  
  public MillennialAdapterExtras clearEthnicity()
  {
    return setEthnicity(null);
  }
  
  public MillennialAdapterExtras clearGender()
  {
    return setGender(null);
  }
  
  public MillennialAdapterExtras clearIncomeInUsDollars()
  {
    return setIncomeInUsDollars(null);
  }
  
  public MillennialAdapterExtras clearInterstitialTime()
  {
    return setInterstitialTime(null);
  }
  
  public MillennialAdapterExtras clearKeywords()
  {
    return setKeywords(null);
  }
  
  public MillennialAdapterExtras clearLocations()
  {
    return setLocations(null);
  }
  
  public MillennialAdapterExtras clearMaritalStatus()
  {
    return setMaritalStatus(null);
  }
  
  public MillennialAdapterExtras clearPolitics()
  {
    return setPolitics(null);
  }
  
  public MillennialAdapterExtras clearPostalCode()
  {
    return setPostalCode(null);
  }
  
  public int getAge()
  {
    return this.age;
  }
  
  public Children getChildren()
  {
    return this.children;
  }
  
  public Education getEducation()
  {
    return this.education;
  }
  
  public Ethnicity getEthnicity()
  {
    return this.ethnicity;
  }
  
  public Gender getGender()
  {
    return this.gender;
  }
  
  public Integer getIncomeInUsDollars()
  {
    return this.income;
  }
  
  public InterstitialTime getInterstitialTime()
  {
    return this.interstitialTime;
  }
  
  public String getKeywords()
  {
    return this.keywords;
  }
  
  public Location getLocation()
  {
    return this.location;
  }
  
  public MaritalStatus getMaritalStatus()
  {
    return this.maritalStatus;
  }
  
  public Politics getPolitics()
  {
    return this.politics;
  }
  
  public String getPostalCode()
  {
    return this.postalCode;
  }
  
  public MillennialAdapterExtras setAge(int paramInt)
  {
    this.age = paramInt;
    return this;
  }
  
  public MillennialAdapterExtras setChildren(Children paramChildren)
  {
    this.children = paramChildren;
    return this;
  }
  
  public MillennialAdapterExtras setEducation(Education paramEducation)
  {
    this.education = paramEducation;
    return this;
  }
  
  public MillennialAdapterExtras setEthnicity(Ethnicity paramEthnicity)
  {
    this.ethnicity = paramEthnicity;
    return this;
  }
  
  public MillennialAdapterExtras setGender(Gender paramGender)
  {
    this.gender = paramGender;
    return this;
  }
  
  public MillennialAdapterExtras setIncomeInUsDollars(Integer paramInteger)
  {
    this.income = paramInteger;
    return this;
  }
  
  public MillennialAdapterExtras setInterstitialTime(InterstitialTime paramInterstitialTime)
  {
    this.interstitialTime = paramInterstitialTime;
    return this;
  }
  
  public MillennialAdapterExtras setKeywords(String paramString)
  {
    this.keywords = paramString;
    return this;
  }
  
  public MillennialAdapterExtras setLocations(Location paramLocation)
  {
    this.location = paramLocation;
    return this;
  }
  
  public MillennialAdapterExtras setMaritalStatus(MaritalStatus paramMaritalStatus)
  {
    this.maritalStatus = paramMaritalStatus;
    return this;
  }
  
  public MillennialAdapterExtras setPolitics(Politics paramPolitics)
  {
    this.politics = paramPolitics;
    return this;
  }
  
  public MillennialAdapterExtras setPostalCode(String paramString)
  {
    this.postalCode = paramString;
    return this;
  }
  
  public static enum Children
  {
    private final String description;
    
    static
    {
      Children[] arrayOfChildren = new Children[2];
      arrayOfChildren[0] = HAS_KIDS;
      arrayOfChildren[1] = NO_KIDS;
      $VALUES = arrayOfChildren;
    }
    
    private Children(String paramString)
    {
      this.description = paramString;
    }
    
    public String getDescription()
    {
      return this.description;
    }
  }
  
  public static enum Education
  {
    private final String description;
    
    static
    {
      ASSOCIATES = new Education("ASSOCIATES", 3, "associates");
      BACHELORS = new Education("BACHELORS", 4, "bachelors");
      MASTERS = new Education("MASTERS", 5, "masters");
      DOCTORATE = new Education("DOCTORATE", 6, "doctorate");
      OTHER = new Education("OTHER", 7, "other");
      Education[] arrayOfEducation = new Education[8];
      arrayOfEducation[0] = HIGH_SCHOOL;
      arrayOfEducation[1] = IN_COLLEGE;
      arrayOfEducation[2] = SOME_COLLEGE;
      arrayOfEducation[3] = ASSOCIATES;
      arrayOfEducation[4] = BACHELORS;
      arrayOfEducation[5] = MASTERS;
      arrayOfEducation[6] = DOCTORATE;
      arrayOfEducation[7] = OTHER;
      $VALUES = arrayOfEducation;
    }
    
    private Education(String paramString)
    {
      this.description = paramString;
    }
    
    public String getDescription()
    {
      return this.description;
    }
  }
  
  public static enum Ethnicity
  {
    private final String description;
    
    static
    {
      AFRICAN_AMERICAN = new Ethnicity("AFRICAN_AMERICAN", 1, "africanamerican");
      ASIAN = new Ethnicity("ASIAN", 2, "asian");
      INDIAN = new Ethnicity("INDIAN", 3, "indian");
      MIDDLE_EASTERN = new Ethnicity("MIDDLE_EASTERN", 4, "middleeastern");
      NATIVE_AMERICAN = new Ethnicity("NATIVE_AMERICAN", 5, "nativeamerican");
      PACIFIC_ISLANDER = new Ethnicity("PACIFIC_ISLANDER", 6, "pacificislander");
      WHITE = new Ethnicity("WHITE", 7, "white");
      OTHER = new Ethnicity("OTHER", 8, "other");
      Ethnicity[] arrayOfEthnicity = new Ethnicity[9];
      arrayOfEthnicity[0] = HISPANIC;
      arrayOfEthnicity[1] = AFRICAN_AMERICAN;
      arrayOfEthnicity[2] = ASIAN;
      arrayOfEthnicity[3] = INDIAN;
      arrayOfEthnicity[4] = MIDDLE_EASTERN;
      arrayOfEthnicity[5] = NATIVE_AMERICAN;
      arrayOfEthnicity[6] = PACIFIC_ISLANDER;
      arrayOfEthnicity[7] = WHITE;
      arrayOfEthnicity[8] = OTHER;
      $VALUES = arrayOfEthnicity;
    }
    
    private Ethnicity(String paramString)
    {
      this.description = paramString;
    }
    
    public String getDescription()
    {
      return this.description;
    }
  }
  
  public static enum Gender
  {
    private final String description;
    
    static
    {
      FEMALE = new Gender("FEMALE", 1, "female");
      OTHER = new Gender("OTHER", 2, "other");
      Gender[] arrayOfGender = new Gender[3];
      arrayOfGender[0] = MALE;
      arrayOfGender[1] = FEMALE;
      arrayOfGender[2] = OTHER;
      $VALUES = arrayOfGender;
    }
    
    private Gender(String paramString)
    {
      this.description = paramString;
    }
    
    public String getDescription()
    {
      return this.description;
    }
  }
  
  public static enum InterstitialTime
  {
    static
    {
      APP_LAUNCH = new InterstitialTime("APP_LAUNCH", 1);
      TRANSITION = new InterstitialTime("TRANSITION", 2);
      InterstitialTime[] arrayOfInterstitialTime = new InterstitialTime[3];
      arrayOfInterstitialTime[0] = UNKNOWN;
      arrayOfInterstitialTime[1] = APP_LAUNCH;
      arrayOfInterstitialTime[2] = TRANSITION;
      $VALUES = arrayOfInterstitialTime;
    }
    
    private InterstitialTime() {}
  }
  
  public static enum MaritalStatus
  {
    private final String description;
    
    static
    {
      DIVORCED = new MaritalStatus("DIVORCED", 1, "divorced");
      ENGAGED = new MaritalStatus("ENGAGED", 2, "engaged");
      RELATIONSHIP = new MaritalStatus("RELATIONSHIP", 3, "relationship");
      MARRIED = new MaritalStatus("MARRIED", 4, "married");
      OTHER = new MaritalStatus("OTHER", 5, "other");
      MaritalStatus[] arrayOfMaritalStatus = new MaritalStatus[6];
      arrayOfMaritalStatus[0] = SINGLE;
      arrayOfMaritalStatus[1] = DIVORCED;
      arrayOfMaritalStatus[2] = ENGAGED;
      arrayOfMaritalStatus[3] = RELATIONSHIP;
      arrayOfMaritalStatus[4] = MARRIED;
      arrayOfMaritalStatus[5] = OTHER;
      $VALUES = arrayOfMaritalStatus;
    }
    
    private MaritalStatus(String paramString)
    {
      this.description = paramString;
    }
    
    public String getDescription()
    {
      return this.description;
    }
  }
  
  public static enum Politics
  {
    private final String description;
    
    static
    {
      DEMOCRAT = new Politics("DEMOCRAT", 1, "democrat");
      CONSERVATIVE = new Politics("CONSERVATIVE", 2, "conservative");
      MODERATE = new Politics("MODERATE", 3, "moderate");
      LIBERAL = new Politics("LIBERAL", 4, "liberal");
      INDEPENDENT = new Politics("INDEPENDENT", 5, "independent");
      OTHER = new Politics("OTHER", 6, "other");
      UNKNOWN = new Politics("UNKNOWN", 7, "unknown");
      Politics[] arrayOfPolitics = new Politics[8];
      arrayOfPolitics[0] = REPUBLICAN;
      arrayOfPolitics[1] = DEMOCRAT;
      arrayOfPolitics[2] = CONSERVATIVE;
      arrayOfPolitics[3] = MODERATE;
      arrayOfPolitics[4] = LIBERAL;
      arrayOfPolitics[5] = INDEPENDENT;
      arrayOfPolitics[6] = OTHER;
      arrayOfPolitics[7] = UNKNOWN;
      $VALUES = arrayOfPolitics;
    }
    
    private Politics(String paramString)
    {
      this.description = paramString;
    }
    
    public String getDescription()
    {
      return this.description;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.mediation.millennial.MillennialAdapterExtras
 * JD-Core Version:    0.7.0.1
 */