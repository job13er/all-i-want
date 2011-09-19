'''
#
# File: rpc_list.py
# Description: 
#   Handler for all List RPCs (setting up Lists and managing items) 
# 
# Copyright 2011 Adam Meadows 
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
'''

from rpc.rpc_meta import RpcGroupBase, RpcReqHandler, Rpc
from rpc.rpc_params import RpcParamString, RpcParamInt, RpcParamBoolean
from core.model import Group, GroupInvitation, GroupMember, ListOwner
from core.exception import PermissionDeniedError, DuplicateNameError

class ListRpcGroup(RpcGroupBase):
    rpcs = (
        Rpc(name='add_list', params=(
            RpcParamInt('owner_id'),
            RpcParamString('name'),
            RpcParamString('desc'),
        )),
        Rpc(name='update_list', params=(
            RpcParamInt('owner_id'),
            RpcParamInt('list_id'),
            RpcParamString('name'),
            RpcParamString('desc'),
        )),
        Rpc(name='get_lists', params=(
            RpcParamInt('owner_id'),
        )),
        Rpc(name='add_item', params=(
            RpcParamInt('list_id'),
            RpcParamString('name'),
            RpcParamString('cat'),
            RpcParamString('desc'),
            RpcParamString('url'),
            RpcParamBoolean('surprise'),
        )),
        Rpc(name='update_item', params=(
            RpcParamInt('list_id'),
            RpcParamString('name'),
            RpcParamString('cat'),
            RpcParamString('desc'),
            RpcParamString('url'),
        )),
        Rpc(name='remove_item', params=(
            RpcParamInt('item_id'),
        )),
        Rpc(name='reserve_item', params=(
            RpcParamInt('item_id'),
        )),
        Rpc(name='unreserve_item', params=(
            RpcParamInt('item_id'),
        )),
        Rpc(name='purchase_item', params=(
            RpcParamInt('item_id'),
        )),
        Rpc(name='unpurchase_item', params=(
            RpcParamInt('item_id'),
        )),
        Rpc(name='get_reserved_and_purchased_items'),
    )

    def _verify_owner(self):
        self.owner = self.db.get_owner_by_user(self.user)
        if self.owner is None:
            raise PermissionDeniedError()

    def _can_add_to_list(self, owner_id):
        self._verify_owner()
        return self.owner.key().id() == owner_id

    def _can_read_list(self, owner_id):
        _ = lambda x: x.member.key().id()
        self._verify_owner()
        oids = []
        groups, memberships = (self.owner.groups, self.owner.memberships)
        oids.extend(_(m) for g in groups for m in g.members)
        oids.extend(_(m) for gm in memberships for m in gm.group.members)
        return owner_id in oids

    def add_list(self, owner_id, name, desc):
        if not self._can_add_to_list(owner_id):
            raise PermissionDeniedError()

        if not self.db.is_list_name_unique(owner_id, name):
            raise DuplicateNameError(WishList, name)

        l = self.db.add_list(owner_id, name, desc)
        return WishList.from_db(l)

    def update_list(self, owner_id, list_id, name, desc):
        if not self._can_add_to_list(owner_id):
            raise PermissionDeniedError()
        
        l = self.db.get_list(list_id)
        if not self.db.is_list_name_unique(owner_id, name, l.key()):
            raise DuplicateNameError(WishList, name)

        l.name = name
        l.description = desc
        return WishList.from_db(l.put())

    def get_lists(self, owner_id):
        if not self._can_read_list(owner_id):
            raise PermissionDeniedError()
        
        owner = self.db.get_owner(owner_id)
        return [ WishList.from_db(l) for l in owner.lists ]
   
    def add_item(self, list_id, name, cat, desc, url, surprise):
        l = self.db.get_list(list_id)
        if not self._can_add_to_list(l.owner.key().id()):
            raise PermissionDeniedError()

        item = self.db.add_item(l, name, cat, desc, url, surprise)
        return ListItem.from_db(item)

    def update_item(self, item_id, name, cat, desc, url):
        item = self.db.get_item(item_id)
        if not self._can_add_to_list(item.parent_list.owner.key().id()):
            raise PermissionDeniedError()

        item.name = name
        item.category = cat
        item.description = desc
        item.url = url
        return ListItem.from_db(item.put())

    def remove_item(self, item_id):
        item = self.db.get_item(item_id)
        if not self._can_add_to_list(item.parent_list.owner.key().id()):
            raise PermissionDeniedError()

        if item.reserved_by is not None:
            # TODO: email reserver
        elif item.purchased_by is not None:
            # TODO: email purchaser 

        # What should I do in this instance? Should we just mark the item
        # as surprised and notify the purchaser/reserer? Or delete it and
        # notify the purchaser/reserver?

    # TODO: ARM IS HERE

        Rpc(name='remove_item', params=(
            RpcParamInt('item_id'),
        )),
        Rpc(name='reserve_item', params=(
            RpcParamInt('item_id'),
        )),
        Rpc(name='unreserve_item', params=(
            RpcParamInt('item_id'),
        )),
        Rpc(name='purchase_item', params=(
            RpcParamInt('item_id'),
        )),
        Rpc(name='unpurchase_item', params=(
            RpcParamInt('item_id'),
        )),
        Rpc(name='get_reserved_and_purchased_items'),

class ListRpcReqHandler(RpcReqHandler):
    group_cls = ListRpcGroup
