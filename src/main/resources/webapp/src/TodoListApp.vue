<template>
    <div>
        <AddItemForm v-on:add="addToList"></AddItemForm>
        <List :lists="lists"></List>
    </div>
</template>

<script>
    import TodoList from './TodoList.js';
    import List from './List.vue'
    import AddItemForm from './AddItemForm';
    import Fetcher from './Fetcher';
    export default {
        data: () => {
            return {lists:[]};
        },
        methods: {
            addToList: function(titleText){
                let formData = new FormData();
                formData.append('title', titleText);
                Fetcher.post(formData).then(json => this.lists.push(new TodoList(json.id, json.title)));
                this.isAdding = false;
            }
        },
        components: { List, AddItemForm },
        created: function(){
            Fetcher.get('api/items/all').then(json => this.lists = json);
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
