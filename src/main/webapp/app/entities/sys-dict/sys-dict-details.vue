<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="sysDict">
                <h2 class="jh-entity-heading"><span>SysDict</span> {{sysDict.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span>Dic Type Id</span>
                    </dt>
                    <dd>
                        <span>{{sysDict.dicTypeId}}</span>
                    </dd>
                    <dt>
                        <span>Code</span>
                    </dt>
                    <dd>
                        <span>{{sysDict.code}}</span>
                    </dd>
                    <dt>
                        <span>Name</span>
                    </dt>
                    <dd>
                        <span>{{sysDict.name}}</span>
                    </dd>
                    <dt>
                        <span>Parent Id</span>
                    </dt>
                    <dd>
                        <span>{{sysDict.parentId}}</span>
                    </dd>
                    <dt>
                        <span>Parent Ids</span>
                    </dt>
                    <dd>
                        <span>{{sysDict.parentIds}}</span>
                    </dd>
                    <dt>
                        <span>Status</span>
                    </dt>
                    <dd>
                        <span>{{sysDict.status}}</span>
                    </dd>
                    <dt>
                        <span>Sort</span>
                    </dt>
                    <dd>
                        <span>{{sysDict.sort}}</span>
                    </dd>
                    <dt>
                        <span>Description</span>
                    </dt>
                    <dd>
                        <span>{{sysDict.description}}</span>
                    </dd>
                    <dt>
                        <span>Create Time</span>
                    </dt>
                    <dd>
                        <span>{{sysDict.createTime | formatDate}}</span>
                    </dd>
                    <dt>
                        <span>Create User</span>
                    </dt>
                    <dd>
                        <span>{{sysDict.createUser}}</span>
                    </dd>
                    <dt>
                        <span>Update Time</span>
                    </dt>
                    <dd>
                        <span>{{sysDict.updateTime | formatDate}}</span>
                    </dd>
                    <dt>
                        <span>Update User</span>
                    </dt>
                    <dd>
                        <span>{{sysDict.updateUser}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
                </button>
                <router-link v-if="sysDict.id" :to="{name: 'SysDictEdit', params: {sysDictId: sysDict.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./sys-dict-details.component.ts">
</script>
