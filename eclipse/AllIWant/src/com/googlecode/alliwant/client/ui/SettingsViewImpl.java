/**
 * @file SettingsViewImpl.java
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

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;

public class SettingsViewImpl extends Composite implements SettingsView {

  interface Binder extends UiBinder<FlowPanel, SettingsViewImpl> {}
  private static final Binder uiBinder = GWT.create(Binder.class);
  
  @UiField
  SimplePanel headerWrapper;

  public SettingsViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  // ================================================================
  // BEGIN: SettingsView methods
  // ================================================================
  
  @Override
  public void showProcessingOverlay() {
    ProcessingOverlay.show();
  }

  @Override
  public void hideProcessingOverlay() {
    ProcessingOverlay.hide();
  }
  
  @Override
  public void setHeader(IsWidget header) {
    headerWrapper.setWidget(header);
  }
  
  // ================================================================
  // END: SettingsView methods
  // ================================================================
  
} // SettingsViewImpl //
