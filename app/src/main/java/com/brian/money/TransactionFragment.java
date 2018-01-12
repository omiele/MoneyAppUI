package com.brian.money;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by omachi on 9/14/16.
 */
public class TransactionFragment extends Fragment {

  private Context context;

  private Activity activity;

  public TransactionFragment() {
    // Required empty public constructor
  }

  public static TransactionFragment newInstance() {
    return new TransactionFragment();
  }

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    context = getActivity();
    //setRetainInstance(true);
  }
}
