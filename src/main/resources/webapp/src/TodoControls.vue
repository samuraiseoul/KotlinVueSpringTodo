<template>
    <div>
        <button type="button" v-on:click="toggle"><i class="fas fa-list fa-lg"></i></button>
        <button type="button" v-on:click="$emit('delete')"><i class="fas fa-trash-alt fa-lg"></i></button>
    </div>
</template>

<script>
    export default {
        name: 'TodoControls',
        methods: {
            toggle(event) {
                this.$emit('toggle', event);
            }
        }
    }
</script>

<style lang="scss" scoped>
    div{
        display: inline;
    }
    button {
        background: none;
        border: none;
        outline: none;
        cursor: pointer;
    }
</style>