package com.boot.dubbo.utils;

/**
 * Created by ZhouFangyuan on 2020-02-24.
 * Time: 20:02
 * Information:
 */
public enum StatusResponse {

  /**
   * 成功
   */
  SUCCESS(200, "成功");


  private StatusResponse(Integer value, String msg) {
    this.val = value;
    this.msg = msg;
  }

  public Integer val() {
    return val;
  }

  public String msg() {
    return msg;
  }

  private Integer val;
  private String  msg;

}
