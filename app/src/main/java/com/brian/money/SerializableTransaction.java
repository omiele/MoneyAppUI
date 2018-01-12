package com.brian.money;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by omachi on 9/14/16.
 */
public class SerializableTransaction implements Serializable {
  private Date date;
  private Long id;
  private double total;
  private double netchange;
  private String type;

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public double getNetchange() {
    return netchange;
  }

  public void setNetchange(Double netchange) {
    this.netchange = netchange;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
