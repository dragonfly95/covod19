<template>
  <v-main class="py-12">
    <v-container class="fill-height" fluid>
      <v-row align="center" justify="center">
        <v-col cols="12" sm="6">
          <v-card>
            <v-toolbar dark flat height="35px">
              <v-spacer />
                <v-toolbar-title>DAUM NEWS</v-toolbar-title>
              <v-spacer />
            </v-toolbar>
          </v-card>
          <v-card-text>
            <v-text-field label="id" v-model="board.id" type="text"></v-text-field>
            <v-text-field label="reg_date" v-model="board.reg_date" type="text"></v-text-field>
            <v-text-field label="category" v-model="board.category" type="text"></v-text-field>
            <v-text-field label="thumbnail" v-model="board.thumbnail" type="text"></v-text-field>
            <v-text-field label="summary" v-model="board.summary" type="text"></v-text-field>
            <v-text-field label="title_thumbnail" v-model="board.title_thumbnail" type="text"></v-text-field>
            <v-text-field label="title_name" v-model="board.title_name" type="text"></v-text-field>
            <v-text-field label="reporter" v-model="board.reporter" type="text"></v-text-field>
            <v-text-field label="newspaper" v-model="board.newspaper" type="text"></v-text-field>
            <v-text-field label="open_yn" v-model="board.open_yn" type="text"></v-text-field>
            <v-text-field label="view_count" v-model="board.view_count" type="text"></v-text-field>
            <v-text-field label="link" v-model="board.link" type="text"></v-text-field>

            <v-textarea height="450px" v-model="board.title_contents" label="title_contents" type="text" :rows="16" aria-multiline="true"></v-textarea>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn dark @click="boardWrite">글쓰기</v-btn>
            <v-btn dark @click="moveBack">취소</v-btn>
          </v-card-actions>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>
<script>

import { mapState, mapGetters } from 'vuex'
import router from '@/router/'
import axios from 'axios'

export default {
  name: 'boardWriteView',
  data: function () {
    return {
      board: {
        id: '',
        reg_date: '',
        category: '',
        thumbnail: '',
        summary: '',
        title_thumbnail: '',
        title_name: '',
        title_contents: '',
        reporter: '',
        newspaper: '',
        open_yn: '',
        view_count: '',
        link: ''
      }
    }
  },
  computed: {
    ...mapState([
    ]),
    ...mapGetters([
      'getLoginInfo'
    ]),
    isValidateBoardInfo: function () {
      return this.board && this.board.id.trim() !== ''
    }
  },
  methods: {
    boardWrite: function () {
      if (this.isValidateBoardInfo) {
        // console.log('title : ' + this.board.title)
        // console.log('contents : ' + this.board.contents)

        //        var info = this.getLoginInfo

        const data = {
          id: this.board.id,
          reg_date: new Date(),
          category: this.board.category,
          thumbnail: this.board.thumbnail,
          summary: this.board.summary,
          title_thumbnail: this.board.title_thumbnail,
          title_name: this.board.title_name,
          title_contents: this.board.title_contents,
          reporter: this.board.reporter,
          newspaper: this.board.newspaper,
          open_yn: this.board.open_yn,
          view_count: this.board.view_count,
          link: this.board.link
        }

        axios.post('http://localhost:7777/api/news', data).then(res => {
          if (res.status === 200 && res.data.msg === 'OK') {
            alert('등록 성공')
          } else {
            alert('등록 실패')
          }
          router.push({ name: 'NewsList' })
        }).catch(err => {
          console.log(err)
        })
      } else {
        alert('Title과 contents를 확인해주세요')
      }
    },
    moveBack: function () {
      router.go(-1)
    }
  }
}
</script>
