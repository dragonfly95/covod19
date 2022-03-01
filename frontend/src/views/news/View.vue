<template>
  <v-main class="py-12">
    <v-container class="fill-height" fluid>
      <v-row align="center" justify="center">
        <v-col cols="12" sm="6">
          <v-card>
            <v-toolbar dark flat height="35px">
              <v-spacer />
                <v-toolbar-title>B o a r d</v-toolbar-title>
              <v-spacer />
            </v-toolbar>
          </v-card>
          <v-card-text>
            <v-text-field label="id" v-model="id" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="reg_date" v-model="$store.state.news.reg_date" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="category" v-model="$store.state.news.category" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="thumbnail" v-model="$store.state.news.thumbnail" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="summary" v-model="$store.state.news.summary" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="title_thumbnail" v-model="$store.state.news.title_thumbnail" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="title_name" v-model="$store.state.news.title_name" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="reporter" v-model="$store.state.news.reporter" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="newspaper" v-model="$store.state.news.newspaper" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="open_yn" v-model="$store.state.news.open_yn" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="view_count" v-model="$store.state.news.view_count" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="link" v-model="$store.state.news.link" type="text" :disabled="isDisabled"></v-text-field>

            <v-textarea height="450px" v-model="$store.state.news.title_contents" label="title_contents" type="text" :rows="16" :disabled="isDisabled" aria-multiline="true"></v-textarea>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn dark v-if="(isDisabled)" @click="setModify">수정</v-btn>
            <v-btn dark v-if="(!isDisabled)" @click="modifyBoard()">수정 완료</v-btn>
            <v-btn dark v-if="(isDisabled)" @click="removeBoard()">삭제</v-btn>
            <v-btn dark @click="moveBack">취소</v-btn>
          </v-card-actions>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>
<script>
import router from '@/router/'
import { mapGetters, mapActions } from 'vuex'
import axios from 'axios'

export default {
  name: 'BoardReadView',
  data: function () {
    return {
      isDisabled: true,
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
      link: '',
      news: {}
    }
  },
  props: {
    id: {
      type: String,
      required: true
    }
  },
  computed: {
    ...mapGetters([
      'getNews'
    ]),
    isValidateBoardInfo: function () {
      return this.id.trim() !== ''
    }
  },
  methods: {
    ...mapActions([
      'getNewsAction'
    ]),
    setModify: function () {
      this.isDisabled = !this.isDisabled
    },
    moveBack: function () {
      if (!this.isDisabled) {
        this.isDisabled = !this.isDisabled
      } else {
        router.go(-1)
      }
    },
    modifyBoard: function () {
      if (this.isValidateBoardInfo) {
        const data = {
          id: this.id,
          category: this.category,
          thumbnail: this.thumbnail,
          summary: this.summary,
          title_thumbnail: this.title_thumbnail,
          title_name: this.title_name,
          title_contents: this.title_contents,
          reporter: this.reporter,
          newspaper: this.newspaper,
          open_yn: this.open_yn,
          view_count: this.view_count,
          link: this.link
        }
        axios.put(`http://localhost:7777/api/news/${this.id}`, data).then(res => {
          if (res.status === 200 && res.data.msg === 'OK') {
            alert('글 수정 성공')
          } else {
            alert('글 수정 실패')
          }
          router.push({ name: 'NewsList' })
        }).catch(err => {
          console.log(err)
        })
      } else {
        alert('Title과 contents를 확인해주세요')
      }
    },
    removeBoard: function () {
      // console.log('removeBoard() data - boardNo : ' + this.boardNo)
      axios.delete(`http://localhost:7777/api/news/${this.id}`).then(res => {
        if (res.status === 200 && res.data.msg === 'OK') {
          alert('글 삭제 성공')
        } else {
          alert('글 삭제 실패')
        }
        router.push({ name: 'NewsList' })
      }).catch(err => {
        console.log(err)
      })
    }
  },
  mounted () {
    this.getNewsAction(this.id)
  },
  updated () {
    if (this.title === '' && this.contents === '') {
      this.title = this.getBoardTitle
      this.contents = this.getBoardContents
    }
  }
}
</script>
