<template>
    <li :parentId="list.todoListId">
        <i class="far fa-minus-square" v-on:click="toggleChildren" v-bind:class="{hide: !isOpen}"></i>
        <i class="far fa-caret-square-down" v-on:click="toggleChildren" v-bind:class="{hide: isOpen}"></i>
        <span>{{list.title}}</span>
        <button type="button" v-on:click="toggleAdd"><i class="fas fa-list fa-lg"></i></button>
        <button type="button" v-on:click="$emit('delete')"><i class="fas fa-trash-alt fa-lg"></i></button>
        <form class="addListForm" @submit.prevent="addToList" v-bind:class="{hide: !isAdding}">
            <input type="hidden" :value="list.id" name="todoListId">
            <label><input name="title" type="text"/></label>
            <button><i class="fas fa-plus  fa-lg"></i></button>
            <button type="button" v-on:click="cancelAdd"><i class="fas fa-trash-alt  fa-lg"></i></button>
        </form>
        <ul v-bind:class="{hide: !isOpen}">
            <Todo v-for="(list, index) in list.todoListItems" :key="list.id" v-bind:list="list" v-on:delete="deleteList(index)"></Todo>
        </ul>
    </li>
</template>

<script>
    import TodoList from './TodoList';
    export default {
        props: ['list'],
        name: 'Todo',
        data: () => {return {isAdding: false, isOpen: true}},
        methods: {
            toggleChildren(){this.isOpen = !this.isOpen;},
            toggleAdd(){this.isAdding = !this.isAdding;},
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
                this.isOpen = true;
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

<style lang="scss" scoped>
    .hide{
        display: none;
    }
    ul {
        li {
            list-style-type: none;
        }
    }
    button{
        background: none;
        border: none;
    }
</style>
