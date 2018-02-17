import Route from './route.js'
import Vue from 'vue'
import TodoListApp from './TodoListApp.vue'
import Login from './Login.vue'
import Router from 'vue-router'

Vue.use(Router);

const routes = [
    new Route('/login', Login),
    new Route('/todo', TodoListApp)
];

export default new Router({routes});