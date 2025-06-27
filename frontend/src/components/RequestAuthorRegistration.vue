<template>

    <v-card outlined>
        <v-card-title>
            RequestAuthorRegistration
        </v-card-title>

        <v-card-text>
            <String label="Name" v-model="value.name" :editMode="editMode"/>
            <String label="Bio" v-model="value.bio" :editMode="editMode"/>
            <String label="Portfolio" v-model="value.portfolio" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="requestAuthorRegistration"
            >
                RequestAuthorRegistration
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>

export default {
    name: 'RequestAuthorRegistrationCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.name = '';
        this.value.bio = '';
        this.value.portfolio = '';
    },
    watch: {
    },
    methods: {
        requestAuthorRegistration() {
            this.$emit('requestAuthorRegistration', this.value);
        },
        close() {
            this.$emit('closeDialog');
        },
        change() {
            this.$emit("update:modelValue", this.value);
        },
    }
}
</script>

