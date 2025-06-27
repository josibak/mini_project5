<template>
    <div>
        <Number
            label="SubscribeId"
            v-model="value.subscribeId"
            :editMode="editMode"
        />
        <Number
            label="UserId"
            v-model="value.userId"
            :editMode="editMode"
        />
        <Date
            label="SubscriptionStartedAt"
            v-model="value.subscriptionStartedAt"
            :editMode="editMode"
        />
        <Date
            label="SubscriptionExpiredAt"
            v-model="value.subscriptionExpiredAt"
            :editMode="editMode"
        />
        <v-row class="ma-0 pa-0">
            <v-spacer></v-spacer>
            <v-btn width="64px" color="primary" @click="save">
                저장
            </v-btn>
        </v-row>
    </div>
</template>


<script>
import BaseEntity from './base-ui/BaseEntity.vue'

export default {
    name: 'Subcription',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "subcriptions",
        value: {
        }
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
