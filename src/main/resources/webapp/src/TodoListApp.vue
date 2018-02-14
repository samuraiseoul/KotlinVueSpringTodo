<template>
    <main>
        <h3>Todo List</h3>
        <form class="addListForm" @submit.prevent="addToList">
            <label><input name="title" type="text" placeholder="New List"/></label>
            <button><i class="fas fa-plus fa-lg"></i></button>
        </form>
        <ul>
            <Todo v-for="(list, index) in lists" :key="list.id" v-bind:list="list" v-on:delete="deleteList(index)"></Todo>
        </ul>
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

<style lang="scss" scoped>
    h3{
        font-size: 3em;
    }
    *{
        width: fit-content;
        margin: .35em auto;
    }
    button{
        background: none;
        border: none;
        outline:none;
        cursor: pointer;
    }
    input[type=text] {
        border: none;
        border-radius: 0;
        border-bottom: 1px solid black;
        outline:none;
    }
</style>
