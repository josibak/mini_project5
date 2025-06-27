<template>

    <v-card outlined>
        <v-card-title>
            RejectAuthorRegistration
        </v-card-title>

        <v-card-text>
            <Number label="AuthorId" v-model="value.authorId" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="rejectAuthorRegistration"
            >
                RejectAuthorRegistration
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
    name: 'RejectAuthorRegistrationCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.authorId = 0;
    },
    watch: {
    },
    methods: {
        rejectAuthorRegistration() {
            this.$emit('rejectAuthorRegistration', this.value);
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

