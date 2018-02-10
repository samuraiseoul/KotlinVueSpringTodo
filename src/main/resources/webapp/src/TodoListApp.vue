<template>
    <main>
        <h3>Todo List</h3>
        <form class="addListForm" @submit.prevent="addToList">
            <label>Todo List Text: <input name="title" type="text"/></label>
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
                fetch('http://127.0.0.1:8080/api/items/add', {
                    method:'POST',
                    body: new FormData(event.target)
                }).then(response => response.json())
                    .then(
                        json => this.lists.push(new TodoList(json.id, json.title))
                    );
                event.target.querySelector('input[name=title]').value = '';
                this.isAdding = false;
            },
            deleteList: function(index){
                fetch(`http://127.0.0.1:8080/api/items/delete?id=${this.lists[index].id}`, {
                    method:'DELETE'
                }).then(() => {
                    this.lists.splice(index, 1);
                });
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
