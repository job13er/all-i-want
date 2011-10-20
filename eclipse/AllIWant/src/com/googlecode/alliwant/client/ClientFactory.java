/**
 * @file ClientFactory.java
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
package com.googlecode.alliwant.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.googlecode.alliwant.client.rpc.Manager;
import com.googlecode.alliwant.client.ui.GroupsView;
import com.googlecode.alliwant.client.ui.ListsView;
import com.googlecode.alliwant.client.ui.GoodbyeView;
import com.googlecode.alliwant.client.ui.RequestsView;
import com.googlecode.alliwant.client.ui.SettingsView;
import com.googlecode.alliwant.client.ui.widget.Alert;
import com.googlecode.alliwant.client.ui.widget.smart.Header;

public interface ClientFactory {
  EventBus getEventBus();
  PlaceController getPlaceController();
  Alert getAlert();
  Manager getManager();
  Header getHeader();
  
  ListsView getListsView();
  GroupsView getGroupsView();
  RequestsView getRequestsView();
  SettingsView getSettingsView();
  GoodbyeView getGoodbyeView();
  
} // ClientFactory //
