<template>

    <v-card outlined>
        <v-card-title>
            CreateManuscript
        </v-card-title>

        <v-card-text>
            <Number label="AuthorId" v-model="value.authorId" :editMode="editMode"/>
            <Number label="ManuscriptId" v-model="value.manuscriptId" :editMode="editMode"/>
            <String label="Title" v-model="value.title" :editMode="editMode"/>
            <String label="Content" v-model="value.content" :editMode="editMode"/>
            <Date label="CreatedAt" v-model="value.createdAt" :editMode="editMode"/>
            <Date label="UpdateAt" v-model="value.updateAt" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="createManuscript"
            >
                CreateManuscript
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
    name: 'CreateManuscriptCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.authorId = 0;
        this.value.manuscriptId = 0;
        this.value.title = '';
        this.value.content = '';
        this.value.createdAt = '2025-06-27';
        this.value.updateAt = '2025-06-27';
    },
    watch: {
    },
    methods: {
        createManuscript() {
            this.$emit('createManuscript', this.value);
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

