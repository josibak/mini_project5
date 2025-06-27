<template>

    <v-card outlined>
        <v-card-title>
            UpdateManuscript
        </v-card-title>

        <v-card-text>
            <Number label="ManuscriptId" v-model="value.manuscriptId" :editMode="editMode"/>
            <String label="Title" v-model="value.title" :editMode="editMode"/>
            <String label="Content" v-model="value.content" :editMode="editMode"/>
            <Number label="AuthorId" v-model="value.authorId" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="updateManuscript"
            >
                UpdateManuscript
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
    name: 'UpdateManuscriptCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.manuscriptId = 0;
        this.value.title = '';
        this.value.content = '';
        this.value.authorId = 0;
    },
    watch: {
    },
    methods: {
        updateManuscript() {
            this.$emit('updateManuscript', this.value);
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

