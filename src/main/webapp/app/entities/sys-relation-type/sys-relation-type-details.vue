<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="sysRelationType">
                <h2 class="jh-entity-heading"><span>SysRelationType</span> {{sysRelationType.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span>Type Code</span>
                    </dt>
                    <dd>
                        <span>{{sysRelationType.typeCode}}</span>
                    </dd>
                    <dt>
                        <span>Typed Name</span>
                    </dt>
                    <dd>
                        <span>{{sysRelationType.typedName}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
                </button>
                <router-link v-if="sysRelationType.id" :to="{name: 'SysRelationTypeEdit', params: {sysRelationTypeId: sysRelationType.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./sys-relation-type-details.component.ts">
</script>
