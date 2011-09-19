'''
#
# File: rpc_user.py
# Description: Handler for all User RPCs related to AE User and ListOwnerDb
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
from rpc.rpc_params import RpcParamString, RpcParamInt
from core.model import User, ListOwner, AccessReq
from core.exception import PermissionDeniedError

class UserRpcGroup(RpcGroupBase):
    rpcs = (
        Rpc(name='get_current_user', params=(
            RpcParamString('url'),
        )),
        Rpc(name='get_owner', params=(
            RpcParamInt('owner_id'),
        )),
        Rpc(name='update_owner', params=(
            RpcParamInt('owner_id'),
            RpcParamString('name'),
            RpcParamString('nickname'),
        )),
        Rpc(name='get_requests'),
        Rpc(name='approve_request', params=(
            RpcParamInt('req_id'),
        )),
        Rpc(name='deny_request', params=(
            RpcParamInt('req_id'),
        )),
    )

    def get_current_user(self, url):
        '''
        Return a User object with details of the current authenticated user,
        including a url for logging out.
        
        If user has not yet been authenticated, return a User object with a
        url to allow user to log in.
        '''
        e, n, ui, li, lo, oid = (None, None, None, None, None, -1)
        user = self.db.user
        if user is None:
            li = self.ae.create_login_url(url)
        else:
            e, n, ui = user.email(), user.nickname(), user.user_id()
            # make sure ListOwnerDb record exists
            owner = self.db.get_owner_by_user(user)
            if owner is None:
                if not user.is_admin:
                    if self.db.get_req_by_user(user) is None:
                        self.db.add_req(user)
                else:
                    owner = self.db.add_owner(user)
            oid = owner.key().id() if owner is not None else -1
            
            from core.util import get_base_url
            base = get_base_url(url)
            lo = self.ae.create_logout_url('%s/#Goodbye:' % base)
        return User(email=e, nickname=n, user_id=ui, login_url=li,
            logout_url=lo, owner_id=oid)

    def get_owner(self, owner_id):
        '''
        Return the ListOwner object with the given owner_id
        '''
        return ListOwner.from_db(self.db.get_owner(owner_id))
   
    def update_owner(self, owner_id, name, nickname):
        '''
        Update the name and nickname of the ListOwner object with the given
        owner_id and return the updated object.
        '''
        owner = self.db.get_owner(owner_id)
        owner.name = name
        owner.nickname = nickname
        return ListOwner.from_db(owner.put())

    def get_requests(self):
        '''
        Return a list of all requests
        '''
        if not self.db.user.is_admin:
            raise PermissionDeniedError()
        
        return [ AccessReq.from_db(db) for db in self.db.get_reqs() ]

    def approve_request(self, req_id):
        '''
        Approve the given AccessRequestDb
        '''
        if not self.db.user.is_admin:
            raise PermissionDeniedError()
        
        from core.util import extract_name
        req = self.db.get_req(req_id)
        self.db.add_owner(req.user)
        self.db.delete(req)
        to = req.user.email()
        subject = 'Account Activated' 
        body = self.ae.APPROVE_TEMPLATE % extract_name(to)
        self.ae.send_mail(to, subject, body)
        return []

    def deny_request(self, req_id):
        '''
        Deny the given AccessRequestDb
        '''
        
        if not self.db.user.is_admin:
            raise PermissionDeniedError()
        
        from core.util import extract_name
        req = self.db.get_req(req_id)
        req.denied = True
        req.put()
        to = req.user.email()
        subject = 'Account Not Activated' 
        body = self.ae.DENY_TEMPLATE % extract_name(to)
        self.ae.send_mail(to, subject, body)
        return []

class UserRpcReqHandler(RpcReqHandler):
    group_cls = UserRpcGroup
