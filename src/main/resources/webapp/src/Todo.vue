<template>
    <li :parentId="list.todoListId">
        {{list.title}}
        <button type="button" v-on:click="$emit('delete')">X</button>
        <button type="button" v-on:click="add">+</button>
        <form class="addListForm" @submit.prevent="addList"  v-bind:class="{hide: !isAdding}">
            <label>Add Entry: <input type="text"/></label>
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
            addList(event){
                let form = event.target;
                let input = form.querySelector('input');
                this.list.todoListItems.push(new TodoList(0, input.value, 0, 0));
                input.value = '';
                this.isAdding = false;
            },
            deleteList(index){
                this.list.todoListItems.splice(index, 1);
            }
        }
    }
</script>

<style scoped>
    .hide{
        display: none;
    }
</style>
