import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export const store = new Vuex.Store({
  state: {
    user: {id: 'id', pwd: 'pwd'},
    isLogin: false
  },
  mutations: {
    loginSuccess: (state) => {
      state.isLogin = true
    },
    logout: (state) => {
      state.isLogin = false
    }
  },
  actions: {
    login ({ commit }) {
      commit('loginSuccess')
    },
    logout ({ commit }) {
      commit('logout')
    }
  }
})
