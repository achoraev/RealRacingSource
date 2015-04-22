package com.firemonkeys.cloudcellapi;

import android.text.TextUtils;
import android.util.Log;
import com.firemonkeys.cloudcellapi.util.Base64;
import com.firemonkeys.cloudcellapi.util.Base64DecoderException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CC_Security
{
  private static final String KEY_FACTORY_ALGORITHM = "RSA";
  private static final SecureRandom RANDOM = new SecureRandom();
  private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
  private static final String TAG = "CC_Security";
  private static HashSet<Long> sKnownNonces = new HashSet();
  
  public static long generateNonce()
  {
    long l = RANDOM.nextLong();
    sKnownNonces.add(Long.valueOf(l));
    return l;
  }
  
  public static PublicKey generatePublicKey(String paramString)
  {
    try
    {
      byte[] arrayOfByte = Base64.decode(paramString);
      PublicKey localPublicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(arrayOfByte));
      return localPublicKey;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new RuntimeException(localNoSuchAlgorithmException);
    }
    catch (InvalidKeySpecException localInvalidKeySpecException)
    {
      Log.e("CC_Security", "Invalid key specification.");
      throw new IllegalArgumentException(localInvalidKeySpecException);
    }
    catch (Base64DecoderException localBase64DecoderException)
    {
      Log.e("CC_Security", "Base64 decoding failed.");
      throw new IllegalArgumentException(localBase64DecoderException);
    }
  }
  
  public static ArrayList<VerifiedPurchase> getVerifiedPurchaseList(String paramString1, String paramString2, String paramString3)
  {
    if (paramString1 == null)
    {
      Log.e("CC_Security", "public key is null");
      return null;
    }
    if (paramString2 == null)
    {
      Log.e("CC_Security", "data is null");
      return null;
    }
    if (Consts.DEBUG) {
      Log.i("CC_Security", "signedData: " + paramString2);
    }
    boolean bool1 = TextUtils.isEmpty(paramString3);
    boolean bool2 = false;
    if (!bool1)
    {
      bool2 = verify(generatePublicKey(paramString1), paramString2, paramString3);
      if (!bool2)
      {
        Log.w("CC_Security", "signature does not match data.");
        return null;
      }
    }
    long l1;
    JSONArray localJSONArray;
    int i;
    try
    {
      JSONObject localJSONObject1 = new JSONObject(paramString2);
      l1 = localJSONObject1.optLong("nonce");
      localJSONArray = localJSONObject1.optJSONArray("orders");
      i = 0;
      if (localJSONArray != null)
      {
        int j = localJSONArray.length();
        i = j;
      }
      if (!isNonceKnown(l1))
      {
        Log.w("CC_Security", "Nonce not found: " + l1);
        return null;
      }
    }
    catch (JSONException localJSONException1)
    {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    for (int k = 0;; k++) {
      if (k < i)
      {
        try
        {
          JSONObject localJSONObject2 = localJSONArray.getJSONObject(k);
          Consts.PurchaseState localPurchaseState = Consts.PurchaseState.valueOf(localJSONObject2.getInt("purchaseState"));
          String str1 = localJSONObject2.getString("productId");
          String str2 = localJSONObject2.getString("packageName");
          long l2 = localJSONObject2.getLong("purchaseTime");
          String str3 = localJSONObject2.optString("orderId", "");
          boolean bool3 = localJSONObject2.has("notificationId");
          String str4 = null;
          if (bool3) {
            str4 = localJSONObject2.getString("notificationId");
          }
          String str5 = localJSONObject2.optString("developerPayload", null);
          if ((localPurchaseState == Consts.PurchaseState.PURCHASED) && (!bool2)) {
            continue;
          }
          localArrayList.add(new VerifiedPurchase(localPurchaseState, str4, str1, str3, str2, l2, str5));
        }
        catch (JSONException localJSONException2)
        {
          Log.e("CC_Security", "JSON exception: ", localJSONException2);
          return null;
        }
      }
      else
      {
        removeNonce(l1);
        return localArrayList;
      }
    }
  }
  
  public static boolean isNonceKnown(long paramLong)
  {
    return sKnownNonces.contains(Long.valueOf(paramLong));
  }
  
  public static void removeNonce(long paramLong)
  {
    sKnownNonces.remove(Long.valueOf(paramLong));
  }
  
  public static boolean verify(PublicKey paramPublicKey, String paramString1, String paramString2)
  {
    if (Consts.DEBUG) {
      Log.i("CC_Security", "signature: " + paramString2);
    }
    try
    {
      Signature localSignature = Signature.getInstance("SHA1withRSA");
      localSignature.initVerify(paramPublicKey);
      localSignature.update(paramString1.getBytes());
      if (!localSignature.verify(Base64.decode(paramString2)))
      {
        Log.e("CC_Security", "Signature verification failed.");
        return false;
      }
      return true;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      Log.e("CC_Security", "NoSuchAlgorithmException.");
      return false;
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      Log.e("CC_Security", "Invalid key specification.");
      return false;
    }
    catch (SignatureException localSignatureException)
    {
      Log.e("CC_Security", "Signature exception.");
      return false;
    }
    catch (Base64DecoderException localBase64DecoderException)
    {
      Log.e("CC_Security", "Base64 decoding failed.");
    }
    return false;
  }
  
  public static class VerifiedPurchase
  {
    public String developerPayload;
    public String notificationId;
    public String orderId;
    public String packageName;
    public String productId;
    public Consts.PurchaseState purchaseState;
    public long purchaseTime;
    
    public VerifiedPurchase(Consts.PurchaseState paramPurchaseState, String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, String paramString5)
    {
      this.purchaseState = paramPurchaseState;
      this.notificationId = paramString1;
      this.productId = paramString2;
      this.orderId = paramString3;
      this.packageName = paramString4;
      this.purchaseTime = paramLong;
      this.developerPayload = paramString5;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.CC_Security
 * JD-Core Version:    0.7.0.1
 */