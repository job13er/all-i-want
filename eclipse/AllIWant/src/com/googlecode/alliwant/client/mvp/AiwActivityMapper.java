/**
 * @file JhbActivityMapper.java
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
*/
package com.googlecode.alliwant.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.googlecode.alliwant.client.ClientFactory;
import com.googlecode.alliwant.client.activity.ListsActivity;
import com.googlecode.alliwant.client.activity.GoodbyeActivity;
import com.googlecode.alliwant.client.place.ListsPlace;
import com.googlecode.alliwant.client.place.GoodbyePlace;

public class AiwActivityMapper implements ActivityMapper {

  private final ClientFactory clientFactory;
  
  public AiwActivityMapper(final ClientFactory clientFactory) {
    super();
    this.clientFactory = clientFactory;
  }
  
  @Override
  public Activity getActivity(Place place) {
    if (place instanceof ListsPlace) {
      return new ListsActivity((ListsPlace)place, clientFactory);
    } else if (place instanceof GoodbyePlace) {
      return new GoodbyeActivity((GoodbyePlace)place, clientFactory);
    }
    return null;
  } // getActivity //

} // JhbActivityMapper //
