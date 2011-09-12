/**
 * @file CategoryImpl.java
 * @author Adam Meadows
 *
 * Copyright 2011 Adam Meadows 
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 * WARNING: This file is auto-generated, don't modify it directly,
 * instead modify jhb/core/model.py and re-generate
 *
*/

package com.googlecode.jhb.gwt.client.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class CategoryImpl extends JavaScriptObject implements Category {
  
  public static final List<Category> decodeList(JsArray<CategoryImpl> js_arr) {
    List<Category> arr = new ArrayList<Category>();
    for (int i = 0; i < js_arr.length(); i++) arr.add(js_arr.get(i));
    return arr; 
  }

  public static final List<Category> decodeList(String json) {
    return decodeList(decodeArray(json)); 
  }

  public static final native JsArray<CategoryImpl> decodeArray(String json) /*-{
    eval('var js = ' + json);
    return js;
  }-*/;
  
  public static final native CategoryImpl decode(String json) /*-{
    eval('var js = ' + json);
    return js;
  }-*/;

  protected CategoryImpl() {}

  @Override
  public final native int getId() /*-{
    return this.a;
  }-*/;

  @Override
  public final native int getAid() /*-{
    return this.b;
  }-*/;

  @Override
  public final native String getName() /*-{
    return this.c;
  }-*/;

  @Override
  public final native String getDescription() /*-{
    return this.d;
  }-*/;

  @Override
  public final native double getBalance() /*-{
    return this.e;
  }-*/;

  @Override
  public final native boolean isActive() /*-{
    return this.f;
  }-*/;


} // CategoryImpl
