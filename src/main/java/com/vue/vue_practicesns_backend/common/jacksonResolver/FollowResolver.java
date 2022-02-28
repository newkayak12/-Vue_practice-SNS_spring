package com.vue.vue_practicesns_backend.common.jacksonResolver;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;

public class FollowResolver  implements ObjectIdResolver {
    @Override
    public void bindItem(ObjectIdGenerator.IdKey id, Object pojo) {

    }

    @Override
    public Object resolveId(ObjectIdGenerator.IdKey id) {
        return null;
    }

    @Override
    public ObjectIdResolver newForDeserialization(Object context) {
        return null;
    }

    @Override
    public boolean canUseFor(ObjectIdResolver resolverType) {
        return false;
    }
}
