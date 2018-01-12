package com.brian.money;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by omachi on 9/14/16.
 */
public class ChatFragment extends Fragment {
  private Context context;

  private Activity activity;

  public ChatFragment() {
    // Required empty public constructor
  }

  public static ChatFragment newInstance() {
    return new ChatFragment();
  }

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    context = getActivity();
    //setRetainInstance(true);
  }
}
