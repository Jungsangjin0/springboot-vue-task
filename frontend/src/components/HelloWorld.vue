<template>
  <div class="hello">
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'HelloWorld',
  data () {
    return {
      login: {
        email: 'admin',
        pwd: 'admin'
      }
    }
  },
  methods: {
    loginTest () {
      axios.post('http://localhost:9090/rest/api/v1/login', this.login).then((res) => { console.log(res); alert(JSON.stringify(res.data)) })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
