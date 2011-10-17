/**
 * @file JhbConstants.java
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
package com.googlecode.alliwant.client.i18n;

import com.google.gwt.i18n.client.Constants;

public interface AiwConstants extends Constants {
  
  @DefaultStringValue("All I Want")
  String allIWant();
  
  @DefaultStringValue("OK")
  String ok();
  
  @DefaultStringValue("Cancel")
  String cancel();
  
  @DefaultStringValue("Logout")
  String logout();
  
  @DefaultStringValue("Group")
  String group();
  
  @DefaultStringValue("Groups")
  String groups();
  
  @DefaultStringValue("Name")
  String name();
  
  @DefaultStringValue("Description")
  String description();
  
  @DefaultStringValue("Owner")
  String owner();
  
  @DefaultStringValue(" | ")
  String pipe();
  
  @DefaultStringValue("List")
  String list();
  
  @DefaultStringValue("Lists")
  String lists();
  
  @DefaultStringValue("Purchased")
  String purchased();
  
  @DefaultStringValue("Reserved")
  String reserved();
  
  @DefaultStringValue("URL")
  String url();
  
  @DefaultStringValue("Category")
  String category();
  
  @DefaultStringValue("You have been successfully logged out.")
  String logoutSuccess();
  
  @DefaultStringValue("You are still logged in.")
  String logoutFail();

  @DefaultStringValue("Settings")
  String settings();

  @DefaultStringValue("Your request for access to All I Want is being processed.\nYou will be notified via email when a decision has been made.")
  String reqBeingProcessed();
  
  @DefaultStringValue("Your request for access to All I Want could not be granted at this time.")
  String reqDenied();
  
  @DefaultStringValue("Requests")
  String requests();

  @DefaultStringValue("Email")
  String email();
 
  @DefaultStringValue("Approve")
  String approve();
  
  @DefaultStringValue("Deny")
  String deny();
  
  @DefaultStringValue("Denied")
  String denied();
  
  @DefaultStringValue("Yes")
  String yes();
  
  @DefaultStringValue("No")
  String no();
  
} // AiwConstants //