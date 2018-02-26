<template>
    <form class="addListForm" @submit.prevent="add">
        <label><input name="title" type="text" placeholder="New Todo"/></label>
        <button><i class="fas fa-plus fa-lg"></i></button>
        <button type="button" v-on:click="cancel"><i class="fas fa-trash-alt fa-lg"></i></button>
    </form>
</template>

<script>
    import TodoList from './TodoList';
    export default {
        name: 'AddItemForm',
        methods: {
            cancel(event){
                event.target.parentElement.querySelector('input[name=title]').value = '';
                this.$emit('cancel');
            },
            add(event){
                let title = event.target.querySelector('input[name=title]');
                this.$emit('add', title.value);
                title.value = '';
            }
        }
    }
</script>

<style lang="scss" scoped>
    button {
        background: none;
        border: none;
        outline: none;
        cursor: pointer;
    }

    input[type=text] {
        border: none;
        border-radius: 0;
        border-bottom: 1px solid black;
        outline: none;
        margin-bottom: .25em;
        &:-webkit-autofill {
            -webkit-box-shadow: 0 0 0 30px white inset;
        }
    }
</style>