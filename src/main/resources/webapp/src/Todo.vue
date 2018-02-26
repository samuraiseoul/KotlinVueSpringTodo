<template>
    <li :parentId="list.todoListId">
        <SubListControls v-if="list.todoListItems.length" :show="isOpen" v-on:toggle="toggleChildren"></SubListControls>
        <span>{{list.title}}</span>
        <TodoControls v-on:toggle="toggleAdd" v-on:delete="$emit('delete')"></TodoControls>
        <AddItemForm v-on:add="addToList" v-on:cancel="cancelAdd" v-if="isAdding"></AddItemForm>
        <List v-if="isOpen" :lists="list.todoListItems"></List>
    </li>
</template>

<script>
    import TodoList from './TodoList';
    import AddItemForm from './AddItemForm';
    import TodoControls from './TodoControls';
    import SubListControls from './SubListControls';
    import Fetcher from './Fetcher';
    import List from './List.vue';
    export default {
        props: ['list'],
        name: 'Todo',
        components: {AddItemForm, TodoControls, SubListControls, List},
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
                let formData = new FormData();
                formData.append('title', titleText);
                formData.append('todoListId', this.list.id);
                Fetcher.post('api/items/add', formData).then(
                    json => this.list.todoListItems.push(new TodoList(json.id, json.title, json.todoListId))
                );
                this.isAdding = false;
                this.isOpen = true;
            }
        }
    }
</script>

<style lang="scss" scoped>
    li {
        list-style-type: none;
        margin-top: .5em;
    }
</style>
