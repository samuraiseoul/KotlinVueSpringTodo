<template>
    <li :parentId="list.todoListId">
        <template v-if="list.todoListItems.length">
            <i class="far fa-minus-square listControl" v-on:click="toggleChildren" v-if="!isOpen"></i>
            <i class="far fa-caret-square-down" v-on:click="toggleChildren" v-if="isOpen"></i>
        </template>
        <span>{{list.title}}</span>
        <TodoControls v-on:toggle="toggleAdd" v-on:delete="$emit('delete')"></TodoControls>
        <AddItemForm v-on:add="addToList" v-on:cancel="cancelAdd" v-if="isAdding"></AddItemForm>
        <ul v-if="isOpen">
            <Todo v-for="(list, index) in list.todoListItems" :key="list.id" v-bind:list="list" v-on:delete="deleteList(index)"></Todo>
        </ul>
    </li>
</template>

<script>
    import TodoList from './TodoList';
    import AddItemForm from './AddItemForm';
    import TodoControls from './TodoControls';
    export default {
        props: ['list'],
        name: 'Todo',
        components: {AddItemForm, TodoControls},
        data: () => {return {isAdding: false, isOpen: true}},
        methods: {
            toggleChildren(){this.isOpen = !this.isOpen;},
            toggleAdd(event){
                this.isAdding = !this.isAdding;
                if(this.isAdding) {
                    this.$nextTick(() => event.target.closest('li').querySelector('input[name=title]').focus());
                }
            },
            cancelAdd(){this.isAdding = false;},
            addToList(titleText){
                console.log(`NoteText: ${titleText}`);
                let formData = new FormData();
                formData.append('title', titleText);
                formData.append('todoListId', this.list.id);
                fetch('http://127.0.0.1:8080/api/items/add', {
                    method:'POST',
                    body: formData
                }).then(response => response.json())
                .then(
                    json => this.list.todoListItems.push(new TodoList(json.id, json.title, json.todoListId))
                );
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
            margin-top: .5em;
        }
    }
    button{
        background: none;
        border: none;
        outline:none;
        cursor: pointer;
    } 
    .listControl {
        cursor: pointer;
    }
    input[type=text] {
        border: none;
        border-radius: 0;
        border-bottom: 1px solid black;
        outline:none;
        margin-bottom: .25em;
    }
</style>
