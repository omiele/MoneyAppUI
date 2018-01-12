package com.brian.money;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.HashMap;

/**
 * Created by omachi on 9/14/16.
 */
public class StateRetentionFragment extends android.app.Fragment {
  private Bitmap bitmap;
  public final HashMap<String, Object> data = new HashMap<>();

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Retain this fragment across configuration changes.
    setRetainInstance(true);
  }

  public Bitmap getBitmap() {
    return bitmap;
  }

  public void setBitmap(Bitmap bitmap) {
    this.bitmap = bitmap;
  }
}
