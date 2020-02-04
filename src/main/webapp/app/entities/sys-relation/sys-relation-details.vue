<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="sysRelation">
                <h2 class="jh-entity-heading"><span>SysRelation</span> {{sysRelation.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span>From Id</span>
                    </dt>
                    <dd>
                        <span>{{sysRelation.fromId}}</span>
                    </dd>
                    <dt>
                        <span>To Id</span>
                    </dt>
                    <dd>
                        <span>{{sysRelation.toId}}</span>
                    </dd>
                    <dt>
                        <span>Type Code</span>
                    </dt>
                    <dd>
                        <span>{{sysRelation.typeCode}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
                </button>
                <router-link v-if="sysRelation.id" :to="{name: 'SysRelationEdit', params: {sysRelationId: sysRelation.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./sys-relation-details.component.ts">
</script>
