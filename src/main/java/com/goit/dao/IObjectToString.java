package com.goit.dao;

import com.google.gson.*;

public interface IObjectToString {

  default Gson jsonObjectString() {
    GsonBuilder builder = new GsonBuilder();
    builder.setDateFormat("yyyy-MM-dd");
    return builder.create();
  }
}