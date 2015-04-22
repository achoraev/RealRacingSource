package com.jirbo.adcolony;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;

public class ADCImage
{
  static int mutable_h;
  static int mutable_w;
  Bitmap bitmap;
  Rect dest_rect = new Rect();
  boolean error;
  int height;
  boolean loaded;
  Bitmap original_bitmap;
  int original_height;
  int original_width;
  Paint paint = new Paint();
  Rect src_rect = new Rect();
  int width;
  
  ADCImage(String paramString)
  {
    this(paramString, 1.0D);
  }
  
  ADCImage(String paramString, double paramDouble)
  {
    this(paramString, paramDouble, false);
  }
  
  ADCImage(String paramString, double paramDouble, boolean paramBoolean)
  {
    this(paramString, paramDouble, paramBoolean, false);
  }
  
  ADCImage(String paramString, double paramDouble, boolean paramBoolean1, boolean paramBoolean2)
  {
    for (;;)
    {
      int i;
      try
      {
        this.bitmap = BitmapFactory.decodeStream(new FileInputStream(paramString));
        this.original_bitmap = this.bitmap;
        this.width = this.bitmap.getWidth();
        this.height = this.bitmap.getHeight();
        this.original_width = this.bitmap.getWidth();
        this.original_height = this.bitmap.getHeight();
        mutable_w = this.original_width;
        mutable_h = this.original_height;
        resize(paramDouble);
        this.loaded = true;
        if (paramBoolean1)
        {
          this.bitmap = convertToMutable(this.bitmap);
          int[] arrayOfInt = new int[this.bitmap.getWidth() * this.bitmap.getHeight()];
          this.bitmap.getPixels(arrayOfInt, 0, this.bitmap.getWidth(), 0, 0, this.bitmap.getWidth(), this.bitmap.getHeight());
          i = 0;
          if (i < arrayOfInt.length)
          {
            if ((arrayOfInt[i] < 255) && (arrayOfInt[i] != 0)) {
              arrayOfInt[i] = (0xFF000000 | 0x7F7F7F & arrayOfInt[i] >> 1);
            }
          }
          else
          {
            this.bitmap.setPixels(arrayOfInt, 0, this.bitmap.getWidth(), 0, 0, this.bitmap.getWidth(), this.bitmap.getHeight());
            this.original_bitmap = this.bitmap;
          }
        }
        else
        {
          if (!paramBoolean2) {
            ADC.bitmaps.add(this.bitmap);
          }
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        ADC.log_error("Failed to load image " + paramString);
        this.error = true;
        return;
      }
      i++;
    }
  }
  
  ADCImage(String paramString, int paramInt1, int paramInt2)
  {
    this(paramString, 1.0D);
    resize(paramInt1, paramInt2);
  }
  
  ADCImage(String paramString, boolean paramBoolean)
  {
    this(paramString, 1.0D, paramBoolean);
  }
  
  ADCImage(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    this(paramString, 1.0D, paramBoolean2, paramBoolean1);
  }
  
  public static Bitmap convertToMutable(Bitmap paramBitmap)
  {
    try
    {
      File localFile = new File(Environment.getExternalStorageDirectory() + File.separator + "temp.tmp");
      RandomAccessFile localRandomAccessFile = new RandomAccessFile(localFile, "rw");
      int i = mutable_w;
      int j = mutable_h;
      Bitmap.Config localConfig = paramBitmap.getConfig();
      FileChannel localFileChannel = localRandomAccessFile.getChannel();
      MappedByteBuffer localMappedByteBuffer = localFileChannel.map(FileChannel.MapMode.READ_WRITE, 0L, j * paramBitmap.getRowBytes());
      paramBitmap.copyPixelsToBuffer(localMappedByteBuffer);
      paramBitmap = Bitmap.createBitmap(i, j, localConfig);
      localMappedByteBuffer.position(0);
      paramBitmap.copyPixelsFromBuffer(localMappedByteBuffer);
      localFileChannel.close();
      localRandomAccessFile.close();
      localFile.delete();
      return paramBitmap;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return paramBitmap;
  }
  
  void center_within(int paramInt1, int paramInt2)
  {
    set_position((paramInt1 - this.width) / 2, (paramInt2 - this.height) / 2);
  }
  
  void draw(Canvas paramCanvas)
  {
    if (this.bitmap == null) {}
    while (this.bitmap.isRecycled()) {
      return;
    }
    paramCanvas.drawBitmap(this.bitmap, this.src_rect, this.dest_rect, this.paint);
  }
  
  void draw(Canvas paramCanvas, int paramInt1, int paramInt2)
  {
    set_position(paramInt1, paramInt2);
    draw(paramCanvas);
  }
  
  int[] get_position()
  {
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = this.dest_rect.left;
    arrayOfInt[1] = this.dest_rect.top;
    return arrayOfInt;
  }
  
  void ninePatch(int paramInt1, int paramInt2)
  {
    if (this.bitmap == null) {
      return;
    }
    Bitmap localBitmap1 = Bitmap.createBitmap(this.original_bitmap, 0, 0, this.original_width / 3, this.original_height);
    Bitmap localBitmap2 = Bitmap.createBitmap(this.original_bitmap, 2 * this.original_width / 3, 0, this.original_width / 3, this.original_height);
    Bitmap localBitmap3 = Bitmap.createScaledBitmap(Bitmap.createBitmap(this.original_bitmap, this.original_width / 3, 0, this.original_width / 3, this.original_height), paramInt1, this.original_height, false);
    int[] arrayOfInt1 = new int[this.original_width / 3 * this.original_height];
    int[] arrayOfInt2 = new int[this.original_width / 3 * this.original_height];
    localBitmap1.getPixels(arrayOfInt1, 0, this.original_width / 3, 0, 0, this.original_width / 3, this.original_height);
    localBitmap2.getPixels(arrayOfInt2, 0, this.original_width / 3, 0, 0, this.original_width / 3, this.original_height);
    localBitmap3.getPixels(new int[localBitmap3.getWidth() * localBitmap3.getHeight()], 0, localBitmap3.getWidth(), 0, 0, localBitmap3.getWidth(), localBitmap3.getHeight());
    int i = 0;
    int j = 0;
    int k = 0;
    int m = 0;
    label331:
    while (i < localBitmap3.getHeight())
    {
      if (j < this.original_width / 3)
      {
        if (k < arrayOfInt1.length) {
          localBitmap3.setPixel(j, i, arrayOfInt1[k]);
        }
        k++;
      }
      for (;;)
      {
        j++;
        if (j != localBitmap3.getWidth()) {
          break label331;
        }
        i++;
        j = 0;
        break;
        if (j >= localBitmap3.getWidth() - this.original_width / 3)
        {
          if (m < arrayOfInt2.length) {
            localBitmap3.setPixel(j, i, arrayOfInt2[m]);
          }
          m++;
        }
      }
    }
    this.bitmap = localBitmap3;
    this.original_bitmap = this.bitmap;
    this.width = this.bitmap.getWidth();
    this.height = this.bitmap.getHeight();
    this.original_width = this.width;
    this.original_height = this.height;
    this.src_rect.right = this.width;
    this.src_rect.bottom = this.height;
  }
  
  void resize(double paramDouble)
  {
    resize(paramDouble, false);
  }
  
  void resize(double paramDouble, boolean paramBoolean)
  {
    if (this.bitmap == null) {}
    while (this.bitmap.isRecycled()) {
      return;
    }
    if (paramDouble != 1.0D)
    {
      int i = (int)(paramDouble * this.original_bitmap.getWidth());
      int j = (int)(paramDouble * this.original_bitmap.getHeight());
      if ((i != this.width) || (j != this.height))
      {
        this.width = i;
        this.height = j;
        this.bitmap = Bitmap.createScaledBitmap(this.original_bitmap, this.width, this.height, true);
        if (!paramBoolean) {
          ADC.bitmaps.add(this.bitmap);
        }
      }
      if (paramBoolean)
      {
        this.original_bitmap.recycle();
        this.original_bitmap = null;
      }
    }
    this.src_rect.right = this.width;
    this.src_rect.bottom = this.height;
  }
  
  void resize(int paramInt1, int paramInt2)
  {
    if (this.bitmap == null) {}
    while ((this.bitmap.isRecycled()) || ((paramInt1 == this.width) && (paramInt2 == this.height))) {
      return;
    }
    this.bitmap = Bitmap.createScaledBitmap(this.original_bitmap, paramInt1, paramInt2, true);
    this.width = paramInt1;
    this.height = paramInt2;
    this.src_rect.right = paramInt1;
    this.src_rect.bottom = paramInt2;
    ADC.bitmaps.add(this.bitmap);
  }
  
  void set_position(int paramInt1, int paramInt2)
  {
    this.dest_rect.left = paramInt1;
    this.dest_rect.top = paramInt2;
    this.dest_rect.right = (paramInt1 + this.width);
    this.dest_rect.bottom = (paramInt2 + this.height);
  }
  
  int x()
  {
    return this.dest_rect.left;
  }
  
  int y()
  {
    return this.dest_rect.top;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCImage
 * JD-Core Version:    0.7.0.1
 */