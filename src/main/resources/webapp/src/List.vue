<template>
    <ul>
        <Todo v-for="(list, index) in lists" :key="list.id" :list="list" v-on:delete="deleteList(index)"></Todo>
    </ul>
</template>

<script>
    import Fetcher from './Fetcher';
    export default {
        props: ['lists'],
        beforeCreate: function() { //https://vuejs.org/v2/guide/components.html#Circular-References-Between-Components
            this.$options.components.Todo = require('./Todo.vue').default
        },
        name: 'List',
        methods: {
            deleteList: function(index) {
                Fetcher.delete(`api/items/delete?id=${this.lists[index].id}`).then(() => {
                    this.lists.splice(index, 1);
                });
            }
        }
    }
</script>

<style lang="scss" scoped>
    ul {
        li {
            list-style-type: none;
            margin-top: .5em;
        }
    }
</style>