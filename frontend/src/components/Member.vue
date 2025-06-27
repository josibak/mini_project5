<template>
    <div>
        <Number
            label="UserId"
            v-model="value.userId"
            :editMode="editMode"
        />
        <Number
            label="BookId"
            v-model="value.bookId"
            :editMode="editMode"
        />
        <String
            label="Name"
            v-model="value.name"
            :editMode="editMode"
        />
        <String
            label="Email"
            v-model="value.email"
            :editMode="editMode"
        />
        <Boolean
            label="SubscribeStatus"
            v-model="value.subscribeStatus"
            :editMode="editMode"
        />
        <Boolean
            label="IsKtUser"
            v-model="value.isKtUser"
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
    name: 'Member',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "members",
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
