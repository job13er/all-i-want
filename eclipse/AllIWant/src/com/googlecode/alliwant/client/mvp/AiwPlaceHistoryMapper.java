/**
 * @file AiwPlaceHistoryMapper.java
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

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.googlecode.alliwant.client.place.GroupsPlace;
import com.googlecode.alliwant.client.place.ListsPlace;
import com.googlecode.alliwant.client.place.GoodbyePlace;
import com.googlecode.alliwant.client.place.RequestsPlace;
import com.googlecode.alliwant.client.place.SettingsPlace;

/**
 * PlaceHistoryMapper interface is used to attach all places which the
 * PlaceHistoryHandler should be aware of. This is done via the @WithTokenizers
 * annotation or by extending PlaceHistoryMapperWithFactory and creating a
 * separate TokenizerFactory.
 */
@WithTokenizers({ 
  ListsPlace.Tokenizer.class,
  GroupsPlace.Tokenizer.class,
  RequestsPlace.Tokenizer.class,
  SettingsPlace.Tokenizer.class,
  GoodbyePlace.Tokenizer.class
})
public interface AiwPlaceHistoryMapper extends PlaceHistoryMapper {

}
