import Vue from 'vue'
import TodoListApp from './TodoListApp.vue'

new Vue({
    el: '#app',
    render: f => f(TodoListApp)
})
