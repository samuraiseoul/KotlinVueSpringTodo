<template>
    <div>
        <AddItemForm v-on:add="addToList"></AddItemForm>
        <ul>
            <Todo v-for="(list, index) in lists" :key="list.id" v-bind:list="list"
                  v-on:delete="deleteList(index)"></Todo>
        </ul>
    </div>
</template>

<script>
    import TodoList from './TodoList.js';
    import Todo from './Todo.vue';
    import AddItemForm from './AddItemForm';
    export default {
        data: () => {
            return {lists:[]};
        },
        methods: {
            addToList: function(titleText){
                let formData = new FormData();
                formData.append('title', titleText);
                fetch('http://127.0.0.1:8080/api/items/add', {
                    method:'POST',
                    body: formData
                }).then(response => response.json())
                    .then(
                        json => this.lists.push(new TodoList(json.id, json.title))
                    );
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
        components: { Todo, AddItemForm },
        created: function(){
            fetch('http://127.0.0.1:8080/api/items/all')
                .then(response => response.json())
                .then(json => this.lists = json)
        }
    }
</script>

<style lang="scss" scoped>
    ul{
        padding: 0;
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
