<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="bmtChangCi">
                <h2 class="jh-entity-heading"><span>BmtChangCi</span> {{bmtChangCi.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span>Name</span>
                    </dt>
                    <dd>
                        <span>{{bmtChangCi.name}}</span>
                    </dd>
                    <dt>
                        <span>Week Day</span>
                    </dt>
                    <dd>
                        <span>{{bmtChangCi.weekDay}}</span>
                    </dd>
                    <dt>
                        <span>Time Begin</span>
                    </dt>
                    <dd>
                        <span>{{bmtChangCi.timeBegin | formatDate}}</span>
                    </dd>
                    <dt>
                        <span>Time End</span>
                    </dt>
                    <dd>
                        <span>{{bmtChangCi.timeEnd | formatDate}}</span>
                    </dd>
                    <dt>
                        <span>Owner</span>
                    </dt>
                    <dd>
                        <span>{{bmtChangCi.owner}}</span>
                    </dd>
                    <dt>
                        <span>User Id</span>
                    </dt>
                    <dd>
                        <span>{{bmtChangCi.userId}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
                </button>
                <router-link v-if="bmtChangCi.id" :to="{name: 'BmtChangCiEdit', params: {bmtChangCiId: bmtChangCi.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./bmt-chang-ci-details.component.ts">
</script>
