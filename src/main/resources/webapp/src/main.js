import Vue from 'vue'
import router from './routes.js'
import App from './App.vue'

new Vue({
    el: '#app',
    router,
    template: '<App/>',
    components: {App}
})