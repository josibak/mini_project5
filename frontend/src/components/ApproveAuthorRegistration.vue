<template>

    <v-card outlined>
        <v-card-title>
            ApproveAuthorRegistration
        </v-card-title>

        <v-card-text>
            <Number label="AuthorId" v-model="value.authorId" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="approveAuthorRegistration"
            >
                ApproveAuthorRegistration
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
    name: 'ApproveAuthorRegistrationCommand',
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
        approveAuthorRegistration() {
            this.$emit('approveAuthorRegistration', this.value);
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

