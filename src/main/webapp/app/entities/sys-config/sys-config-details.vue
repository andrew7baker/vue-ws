<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="sysConfig">
                <h2 class="jh-entity-heading"><span>SysConfig</span> {{sysConfig.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span>Name</span>
                    </dt>
                    <dd>
                        <span>{{sysConfig.name}}</span>
                    </dd>
                    <dt>
                        <span>Code</span>
                    </dt>
                    <dd>
                        <span>{{sysConfig.code}}</span>
                    </dd>
                    <dt>
                        <span>Dict Flag</span>
                    </dt>
                    <dd>
                        <span>{{sysConfig.dictFlag}}</span>
                    </dd>
                    <dt>
                        <span>Dict Type Id</span>
                    </dt>
                    <dd>
                        <span>{{sysConfig.dictTypeId}}</span>
                    </dd>
                    <dt>
                        <span>Value</span>
                    </dt>
                    <dd>
                        <span>{{sysConfig.value}}</span>
                    </dd>
                    <dt>
                        <span>Remark</span>
                    </dt>
                    <dd>
                        <span>{{sysConfig.remark}}</span>
                    </dd>
                    <dt>
                        <span>Create Time</span>
                    </dt>
                    <dd>
                        <span>{{sysConfig.createTime | formatDate}}</span>
                    </dd>
                    <dt>
                        <span>Create User</span>
                    </dt>
                    <dd>
                        <span>{{sysConfig.createUser}}</span>
                    </dd>
                    <dt>
                        <span>Update Time</span>
                    </dt>
                    <dd>
                        <span>{{sysConfig.updateTime | formatDate}}</span>
                    </dd>
                    <dt>
                        <span>Update User</span>
                    </dt>
                    <dd>
                        <span>{{sysConfig.updateUser}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
                </button>
                <router-link v-if="sysConfig.id" :to="{name: 'SysConfigEdit', params: {sysConfigId: sysConfig.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./sys-config-details.component.ts">
</script>
