package com.brian.money;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by omachi on 9/14/16.
 */
public class HomeFragment extends Fragment {

  private Context context;
  private ListView listview;
  private TransactionAdapter adapter;
  private Activity activity;
  private ArrayList<SerializableTransaction> transactions;

  public HomeFragment() {
    // Required empty public constructor
  }

  public static HomeFragment newInstance() {
    return new HomeFragment();
  }

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

      }
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    transactions = new ArrayList<>();
    SerializableTransaction trans1 = new SerializableTransaction();
    trans1.setDate(new Date());
    trans1.setNetchange((double) 20000);
    trans1.setType("received");
    trans1.setId(new Long(179418204));
    trans1.setTotal((double) 400);
    transactions.add(trans1);
    SerializableTransaction trans2 = new SerializableTransaction();
    trans2.setDate(new Date());
    trans2.setNetchange((double) 5000);
    trans2.setType("outgoing");
    trans2.setId(new Long(179415204));
    trans2.setTotal((double) 400);
    transactions.add(trans2);
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_home, container, false);
    listview = (ListView) view.findViewById(R.id.trans_list);
    adapter = new TransactionAdapter(getActivity(),
            android.R.layout.simple_list_item_1, transactions);
    listview.setAdapter(adapter);

    return view;
  }
}
