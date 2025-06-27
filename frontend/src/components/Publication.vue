<template>
    <div>
        <Number
            label="PublicationId"
            v-model="value.publicationId"
            :editMode="editMode"
        />
        <Number
            label="ManuscriptId"
            v-model="value.manuscriptId"
            :editMode="editMode"
        />
        <String
            label="Summary"
            v-model="value.summary"
            :editMode="editMode"
        />
        <String
            label="PostUrl"
            v-model="value.postUrl"
            :editMode="editMode"
        />
        <String
            label="Title"
            v-model="value.title"
            :editMode="editMode"
        />
        <String
            label="AuthorId"
            v-model="value.authorId"
            :editMode="editMode"
        />
        <Date
            label="PublicAt"
            v-model="value.publicAt"
            :editMode="editMode"
        />
        <String
            label="Content"
            v-model="value.content"
            :editMode="editMode"
        />
        <String
            label="BookId"
            v-model="value.bookId"
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
    name: 'Publication',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "publications",
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
