<template>
    <li :parentId="list.todoListId">
        {{list.title}}
        <button type="button" v-on:click="$emit('delete')">X</button>
        <button type="button" v-on:click="add">+</button>
        <form class="addListForm" @submit.prevent="addToList" v-bind:class="{hide: !isAdding}">
            <input type="hidden" :value="list.id" name="todoListId">
            <label>Add Entry: <input name="title" type="text"/></label>
            <button>Add List</button> <button type="button" v-on:click="cancelAdd">Cancel</button>
        </form>
        <ol>
            <Todo v-for="(list, index) in list.todoListItems" v-bind:list="list" v-on:delete="deleteList(index)"></Todo>
        </ol>
    </li>
</template>

<script>
    import TodoList from './TodoList';
    export default {
        props: ['list'],
        name: 'Todo',
        data: () => {return {isAdding: false}},
        methods: {
            add(){this.isAdding = true;},
            cancelAdd(){this.isAdding = false;},
            addToList(event){
                fetch('http://127.0.0.1:8080/api/items/add', {
                    method:'POST',
                    body: new FormData(event.target)
                }).then(response => response.json())
                .then(
                    json => this.list.todoListItems.push(new TodoList(json.id, json.title, json.todoListId))
                );
                event.target.querySelector('input[name=title]').value = '';
                this.isAdding = false;
            },
            deleteList(index){
                fetch(`http://127.0.0.1:8080/api/items/delete?id=${this.list.todoListItems[index].id}`, {
                    method:'DELETE'
                }).then(() => {
                    this.list.todoListItems.splice(index, 1);
                });
            }
        }
    }
</script>

<style scoped>
    .hide{
        display: none;
    }
</style>
