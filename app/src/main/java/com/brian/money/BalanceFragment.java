package com.brian.money;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by omachi on 9/14/16.
 */
public class BalanceFragment extends Fragment {
  private Context context;

  private Activity activity;

  public BalanceFragment() {
    // Required empty public constructor
  }

  public static BalanceFragment newInstance() {
    return new BalanceFragment();
  }

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    context = getActivity();
    //setRetainInstance(true);
  }
}
