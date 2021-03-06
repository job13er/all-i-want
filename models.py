"""
#
# File: models.py
# Description: Model objects
#
# Copyright 2011-2013 Adam Meadows
#
#    Licensed under the Apache License, Version 2.0 (the "License");
#    you may not use this file except in compliance with the License.
#    You may obtain a copy of the License at
#
#        http://www.apache.org/licenses/LICENSE-2.0
#
#    Unless required by applicable law or agreed to in writing, software
#    distributed under the License is distributed on an "AS IS" BASIS,
#    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#    See the License for the specific language governing permissions and
#    limitations under the License.
#
"""

from google.appengine.ext import db
import model_fields as mf

FIELD_MAP = {
    db.DateTimeProperty: mf.DateTime,
    db.StringProperty: mf.String,
    db.ReferenceProperty: mf.Reference,
    db.BooleanProperty: mf.Boolean,
    db.UserProperty: mf.User,
}


class Db(db.Model):
    last_modified = db.DateTimeProperty(indexed=False, auto_now=True)

    def put(self):
        super(Db, self).put()
        return self


class AccessReqDb(Db):
    user = db.UserProperty(auto_current_user_add=True)
    denied = db.BooleanProperty(default=False)


class ListOwnerDb(Db):
    name = db.StringProperty(indexed=False)
    nickname = db.StringProperty(indexed=False)
    email = db.StringProperty(indexed=False)
    user = db.UserProperty(auto_current_user_add=True)

    def label(self):
        return '{} ({})'.format(self.name, self.email)


class ListPermissionDb(Db):
    owner = db.ReferenceProperty(ListOwnerDb, collection_name='permissions')
    email = db.StringProperty()


class GroupDb(Db):
    name = db.StringProperty()
    description = db.StringProperty(indexed=False, multiline=True)
    owner = db.ReferenceProperty(ListOwnerDb, collection_name='groups')


class GroupInvitationDb(Db):
    group = db.ReferenceProperty(GroupDb, collection_name='invitations')
    email = db.StringProperty()


class GroupMemberDb(Db):
    member = db.ReferenceProperty(ListOwnerDb, collection_name='memberships')
    group = db.ReferenceProperty(GroupDb, collection_name='members')


class ListDb(Db):
    name = db.StringProperty()
    description = db.StringProperty(indexed=False)
    owner = db.ReferenceProperty(ListOwnerDb, collection_name='lists')


class ListItemDb(Db):
    parent_list = db.ReferenceProperty(ListDb, collection_name='items')
    name = db.StringProperty(indexed=False)
    category = db.StringProperty(indexed=False)
    description = db.StringProperty(indexed=False, multiline=True)
    url = db.StringProperty(indexed=False)
    reserved_by = db.ReferenceProperty(ListOwnerDb,
                                       collection_name='reservations',
                                       default=None)
    purchased_by = db.ReferenceProperty(ListOwnerDb,
                                        collection_name='purchases',
                                        default=None)
    is_surprise = db.BooleanProperty(indexed=False, default=False)
