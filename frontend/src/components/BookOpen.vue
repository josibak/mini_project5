<template>

    <v-card outlined>
        <v-card-title>
            BookOpen
        </v-card-title>

        <v-card-text>
            <Number label="UserId" v-model="value.userId" :editMode="editMode"/>
            <Number label="BookId" v-model="value.bookId" :editMode="editMode"/>
            <Boolean label="SubscribeStatus" v-model="value.subscribeStatus" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="bookOpen"
            >
                BookOpen
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
    name: 'BookOpenCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.userId = 0;
        this.value.bookId = 0;
        this.value.subscribeStatus = false;
    },
    watch: {
    },
    methods: {
        bookOpen() {
            this.$emit('bookOpen', this.value);
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

