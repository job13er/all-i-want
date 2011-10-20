/**
 * @file ListsView.java
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
package com.googlecode.alliwant.client.ui;

import com.google.gwt.user.client.ui.IsWidget;

public interface ListsView extends JhbView {
  void setHeader(IsWidget header);
  void clearOwners();
  void addOwnerItem(String item, String value);
  String getOwner();
  
  void clearLists();
  void addListItem(String item, String value);
  String getList();

  void setPresenter(Presenter presenter);
  interface Presenter {
    void userChanged(); 
    void addList();
    void listChanged();
    void addItem();
  }
}
