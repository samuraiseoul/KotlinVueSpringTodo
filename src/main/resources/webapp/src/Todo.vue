<template>
    <li :parentId="list.todoListId">
        {{list.title}} <button type="button" v-on:click="$emit('delete')">X</button>
    </li>
</template>

<script>
    export default {
        props: ['list']
    }
</script>

<style scoped>
    li {
        color: red;
    }
</style>
