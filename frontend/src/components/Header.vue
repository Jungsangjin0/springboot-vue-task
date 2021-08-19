<template>
    <div class="user-header">
        <h1>헤더 입니다.</h1>
        <router-link :to="{name: 'Login'}">로그인 페이지 이동</router-link>
        <div v-if='!isLogin'>
            비로그인
        </div>
        <div v-else>
            {{ user }}
        </div>
    </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: 'user-header',
  computed: {
    ...mapState(['user', 'isLogin'])
  }
}
</script>
