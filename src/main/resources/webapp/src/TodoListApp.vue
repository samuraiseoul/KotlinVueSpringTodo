<template>
    <main>
        <h3>Todo List</h3>
        <form class="addListForm" @submit.prevent="addToList">
            <label>Todo List Text: <input type="text"/></label>
            <button>Add List</button>
        </form>
        <ol>
            <Todo v-for="(list, index) in lists" v-bind:list="list" v-on:delete="deleteList(index)"></Todo>
        </ol>
    </main>
</template>

<script>
    import TodoList from './TodoList.js';
    import Todo from './Todo.vue';
    export default {
        data: () => {
            return {lists:[]};
        },
        methods: {
            addToList: function(event){
                let form = event.target;
                let input = form.querySelector('input');
                this.lists.push(new TodoList(0, input.value, 0, 0));
                input.value = '';
            },
            deleteList: function(index){
                this.lists.splice(index, 1);
            }
        },
        components: { Todo },
        created: function(){
            fetch('http://127.0.0.1:8080/api/items/all')
                .then(response => response.json())
                .then(json => this.lists = json)
        }
    }
</script>

<style scoped>
</style>
