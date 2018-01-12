package com.brian.money;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by omachi on 9/14/16.
 */
public class TransactionAdapter extends BaseAdapter {

  private SparseBooleanArray mSelectedItemsIds;
  private ArrayList<SerializableTransaction> transactionHistoryData;

  private ArrayList<SerializableTransaction> mStringFilterList;

  private final Context context;

  public TransactionAdapter(Context context, int textViewResourceId,
                                   List<SerializableTransaction> objects) {
    this.context = context;
    this.transactionHistoryData = new ArrayList<>();
    transactionHistoryData.addAll(objects);
    this.mStringFilterList = new ArrayList<>();
    mStringFilterList.addAll(transactionHistoryData);
    mSelectedItemsIds = new SparseBooleanArray();
      }
  @Override
  public int getCount() {
    return transactionHistoryData.size();
  }

  @Override
  public SerializableTransaction getItem(int position) {
    return transactionHistoryData.get(position);
  }
  @Override
  public long getItemId(int position) {
    SerializableTransaction item = getItem(position);
    return item.getId();
  }

  @Override
  public boolean hasStableIds() {
    return true;
  }

  private static class RowViewHolder{
    View rowView;
    TextView textViewDate;
    TextView textViewDateDay;
    TextView textViewNet;
    TextView textViewDateMon;
    TextView textViewTotal;
    ImageView imageView;

    public RowViewHolder(LayoutInflater inflater, ViewGroup parent) {
      rowView = inflater.inflate(R.layout.transaction_row, parent, false);
      textViewDate = (TextView) rowView.findViewById(R.id.date_wkday);
      textViewDateDay = (TextView) rowView.findViewById(R.id.date_day);
      textViewDateMon = (TextView) rowView.findViewById(R.id.date_mon);
      textViewNet = (TextView) rowView.findViewById(R.id.net_change);
      textViewTotal = (TextView) rowView.findViewById(R.id.total);
      imageView = (ImageView)rowView.findViewById(R.id.imageView);

    }
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    RowViewHolder tag;
    if (convertView == null) {
      LayoutInflater inflater = (LayoutInflater) context
              .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      tag = new RowViewHolder(inflater, parent);
      convertView = tag.rowView;
      convertView.setTag(tag);
    } else {
      tag = (RowViewHolder) convertView.getTag();
    }

    SerializableTransaction transaction = getItem(position);
    tag.textViewTotal.setText("$"+String.valueOf(transaction.getTotal()));
    tag.textViewNet.setText("$"+String.valueOf(transaction.getNetchange()));
    SimpleDateFormat month_date = new SimpleDateFormat("MMM");
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE", Locale.US);
    String month_name = month_date.format(transaction.getDate().getTime());
    String day_name = dateFormat.format(transaction.getDate().getTime());
    Calendar cal = Calendar.getInstance();
    cal.setTime(transaction.getDate());
    int day = cal.get(Calendar.DAY_OF_MONTH);
    tag.textViewDateDay.setText(String.valueOf(day));
    tag.textViewDateMon.setText(month_name);
    tag.textViewDate.setText(day_name);

    String type = transaction.getType();
    if(type != null){
      switch(type){
        case "received":
          tag.textViewNet.setTextColor(Color.parseColor("#008000"));
          tag.imageView.setImageResource(R.mipmap.ic_arrow_downward_black_18dp);
          break;
        case "outgoing":
          tag.textViewNet.setTextColor(Color.parseColor("#8B0000"));
          tag.imageView.setImageResource(R.mipmap.ic_arrow_upward_black_18dp);
          break;
      }
    }

    return tag.rowView;
  }

}
