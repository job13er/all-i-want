/**
 * @file UserTestImpl.java
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
 * instead modify core/model.py and re-generate
 *
*/

package com.googlecode.alliwant.client.model;

import static com.googlecode.alliwant.client.logging.Logging.logger;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserTestImpl extends ModelJson
 implements User {

  public static List<User> parseArray(String json) {
    List<User> al = new ArrayList<User>();
    try {
      JSONArray arr = new JSONArray(json);
      for (int i = 0; i < arr.length(); i++) {
        al.add(new UserTestImpl(arr.getJSONObject(i))); 
      }
    } catch (JSONException e) {
      logger().severe("UserTestImpl::parseArray: " +
        e.getLocalizedMessage()); 
    }
    return al;
  } // parseArray //

  public UserTestImpl(String json) {
    super(json);
  }
  
  public UserTestImpl(JSONObject obj) {
    super(obj);
  }


  @Override
  public String getEmail() {
    return getStr("a");
  }

  @Override
  public String getNickname() {
    return getStr("b");
  }

  @Override
  public String getUserId() {
    return getStr("c");
  }

  @Override
  public String getLoginUrl() {
    return getStr("d");
  }

  @Override
  public String getLogoutUrl() {
    return getStr("e");
  }

  @Override
  public int getOwnerId() {
    return getInt("f");
  }

  @Override
  public boolean wasReqDenied() {
    return getBool("g");
  }

  @Override
  public boolean isAdmin() {
    return getBool("h");
  }


} // UserTestImpl //
