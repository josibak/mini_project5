<template>
    <v-container>
        <v-snackbar
            v-model="snackbar.status"
            :timeout="snackbar.timeout"
            :color="snackbar.color"
        >
            
            <v-btn style="margin-left: 80px;" text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
        <div class="panel">
            <div class="gs-bundle-of-buttons" style="max-height:10vh;">
                <v-btn @click="addNewRow" @class="contrast-primary-text" small color="primary">
                    <v-icon small style="margin-left: -5px;">mdi-plus</v-icon>등록
                </v-btn>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="openEditDialog()" class="contrast-primary-text" small color="primary">
                    <v-icon small>mdi-pencil</v-icon>수정
                </v-btn>
                <v-btn style="margin-left: 5px;" @click="openBookPointDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('User')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>포인트로 도서 열람
                </v-btn>
                <v-dialog v-model="openBookPointDialog" width="500">
                    <OpenBookPoint
                        @closeDialog="openBookPointDialog = false"
                        @openBookPoint="openBookPoint"
                    ></OpenBookPoint>
                </v-dialog>
                <v-btn style="margin-left: 5px;" @click="subscribtionRequestDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('User')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>구독 신청
                </v-btn>
                <v-dialog v-model="subscribtionRequestDialog" width="500">
                    <SubscribtionRequest
                        @closeDialog="subscribtionRequestDialog = false"
                        @subscribtionRequest="subscribtionRequest"
                    ></SubscribtionRequest>
                </v-dialog>
                <v-btn style="margin-left: 5px;" @click="registerMemberDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('User')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>회원가입
                </v-btn>
                <v-dialog v-model="registerMemberDialog" width="500">
                    <RegisterMember
                        @closeDialog="registerMemberDialog = false"
                        @registerMember="registerMember"
                    ></RegisterMember>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="authKtDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('User')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>KT고객 인증
                </v-btn>
                <v-dialog v-model="authKtDialog" width="500">
                    <AuthKt
                        @closeDialog="authKtDialog = false"
                        @authKt="authKt"
                    ></AuthKt>
                </v-dialog>
                <v-btn style="margin-left: 5px;" @click="bookOpenDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('User')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>도서 열람
                </v-btn>
                <v-dialog v-model="bookOpenDialog" width="500">
                    <BookOpen
                        @closeDialog="bookOpenDialog = false"
                        @bookOpen="bookOpen"
                    ></BookOpen>
                </v-dialog>
            </div>
            <Userpage @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></Userpage>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>BookId</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>SubscribeStatus</th>
                        <th>IsKtUser</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="BookId">{{ val.bookId }}</td>
                            <td class="whitespace-nowrap" label="Name">{{ val.name }}</td>
                            <td class="whitespace-nowrap" label="Email">{{ val.email }}</td>
                            <td class="whitespace-nowrap" label="SubscribeStatus">{{ val.subscribeStatus }}</td>
                            <td class="whitespace-nowrap" label="IsKtUser">{{ val.isKtUser }}</td>
                            <v-row class="ma-0 pa-4 align-center">
                                <v-spacer></v-spacer>
                                <Icon style="cursor: pointer;" icon="mi:delete" @click="deleteRow(val)" />
                            </v-row>
                        </tr>
                    </tbody>
                </v-table>
            </div>
        </div>
        <v-col>
            <v-dialog
                v-model="openDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0 pa-4"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">Member 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Member :offline="offline"
                            :isNew="!value.idx"
                            :editMode="true"
                            :inList="false"
                            v-model="newValue"
                            @add="append"
                        />
                    </v-card-text>
                </v-card>
            </v-dialog>
            <v-dialog
                v-model="editDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0 pa-4"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">Member 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="UserId" v-model="selectedRow.userId" :editMode="true"/>
                            <Number label="BookId" v-model="selectedRow.bookId" :editMode="true"/>
                            <String label="Name" v-model="selectedRow.name" :editMode="true"/>
                            <String label="Email" v-model="selectedRow.email" :editMode="true"/>
                            <Boolean label="SubscribeStatus" v-model="selectedRow.subscribeStatus" :editMode="true"/>
                            <Boolean label="IsKtUser" v-model="selectedRow.isKtUser" :editMode="true"/>
                            <v-divider class="border-opacity-100 my-divider"></v-divider>
                            <v-layout row justify-end>
                                <v-btn
                                    width="64px"
                                    color="primary"
                                    @click="save"
                                >
                                    수정
                                </v-btn>
                            </v-layout>
                        </div>
                    </v-card-text>
                </v-card>
            </v-dialog>
        </v-col>
    </v-container>
</template>

<script>
import { ref } from 'vue';
import { useTheme } from 'vuetify';
import BaseGrid from '../base-ui/BaseGrid.vue'


export default {
    name: 'memberGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'members',
        openBookPointDialog: false,
        subscribtionRequestDialog: false,
        registerMemberDialog: false,
        authKtDialog: false,
        bookOpenDialog: false,
    }),
    watch: {
    },
    methods:{
        async openBookPoint(params){
            try{
                var path = "openBookPoint".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','OpenBookPoint 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.openBookPointDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async subscribtionRequest(params){
            try{
                var path = "subscribtionRequest".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','SubscribtionRequest 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.subscribtionRequestDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async registerMember(params){
            try{
                var path = "registerMember".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','RegisterMember 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.registerMemberDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async authKt(params){
            try{
                var path = "authKt".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','AuthKT 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.authKtDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async bookOpen(params){
            try{
                var path = "bookOpen".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','BookOpen 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.bookOpenDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>