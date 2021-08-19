<template>
    <div class="login">
        <!-- <div v-if='!isLogin'> -->
            <!-- <button @click="gotoPage()">로그인</button> -->
            <!-- <input type="text" v-on="user.email" v-value="user.email"> -->
            <!-- <input type="text" v-on="user.pwd" v-value="user.pwd"> -->
        <!-- </div> -->
        <!-- <div v-else> -->
            <!-- <button @click="logout()">로그아웃</button> -->
        <!-- </div> -->
    </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  name: 'login',
  data () {
    return {
      user: {
        email: '',
        pwd: ''
      }
    }
  },
  computed: {
    ...mapState(['isLogin'])
  },
  methods: {
    ...mapActions(['login', 'logout']),
    gotoPage () {
      this.login()
      this.$router.push({name: '/HelloWorld'})
    }
  }
}
</script>
