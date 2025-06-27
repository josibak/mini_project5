<template>
    <div>
        <Number
            label="PointAccountId"
            v-model="value.pointAccountId"
            :editMode="editMode"
        />
        <Number
            label="UserId"
            v-model="value.userId"
            :editMode="editMode"
        />
        <Number
            label="Balance"
            v-model="value.balance"
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
    name: 'PointAccount',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "pointAccounts",
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
