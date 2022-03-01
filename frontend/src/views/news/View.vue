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
            <v-text-field label="id" v-model="news.id" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="reg_date" v-model="news.reg_date" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="category" v-model="news.category" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="thumbnail" v-model="news.thumbnail" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="summary" v-model="news.summary" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="title_thumbnail" v-model="news.title_thumbnail" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="title_name" v-model="news.title_name" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="reporter" v-model="news.reporter" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="newspaper" v-model="news.newspaper" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="open_yn" v-model="news.open_yn" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="view_count" v-model="news.view_count" type="text" :disabled="isDisabled"></v-text-field>
            <v-text-field label="link" v-model="news.link" type="text" :disabled="isDisabled"></v-text-field>

            <v-textarea height="450px" v-model="news.title_contents" label="title_contents" type="text" :rows="16" :disabled="isDisabled" aria-multiline="true"></v-textarea>
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
      news: {
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
    initValue: function (data1) {
      this.reg_date = data1.reg_date
      this.category = data1.category
      this.thumbnail = data1.thumbnail
      this.summary = data1.summary
      this.title_thumbnail = data1.title_thumbnail
      this.title_name = data1.title_name
      this.title_contents = data1.title_contents
      this.reporter = data1.reporter
      this.newspaper = data1.newspaper
      this.open_yn = data1.open_yn
      this.view_count = data1.view_count
      this.link = data1.link
    },
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
          id: this.news.id,
          category: this.news.category,
          thumbnail: this.news.thumbnail,
          summary: this.news.summary,
          title_thumbnail: this.news.title_thumbnail,
          title_name: this.news.title_name,
          title_contents: this.news.title_contents,
          reporter: this.news.reporter,
          newspaper: this.news.newspaper,
          open_yn: this.news.open_yn,
          view_count: this.news.view_count,
          link: this.news.link
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
    // this.initValue(this.$store.state.news)
    this.news = this.$store.state.news
  },
  updated () {
    if (this.title === '' && this.contents === '') {
      this.title = this.getBoardTitle
      this.contents = this.getBoardContents
    }
  }
}
</script>
