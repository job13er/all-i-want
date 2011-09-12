/**
 * @file HeaderView.java
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
package com.googlecode.jhb.gwt.client.ui.widget.smart;

import com.googlecode.jhb.gwt.client.ui.JhbView;

public interface HeaderView extends JhbView {
  void setNickname(String nickname);
  void setEmail(String email);
  void setLogoutURL(String url);
  void setDashboardActive(boolean active);
  void setBalanceActive(boolean active);
  void setTransactionsActive(boolean active);
  void setReportsActive(boolean active);
  void setBillsActive(boolean active);
  void setBudgetActive(boolean active);
  void setSettingsActive(boolean active);
  String getURL();
  void redirect(String url);
}
