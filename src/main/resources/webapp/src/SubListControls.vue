<template>
    <div>
        <i class="far fa-minus-square listControl" v-on:click="$emit('toggle')" v-if="!show"></i>
        <i class="far fa-caret-square-down" v-on:click="$emit('toggle')" v-if="show"></i>
    </div>
</template>

<script>
    export default {
        props: ['show'],
        name: 'SubListControls',
        methods: {
            toggle(event) {
                this.$emit('toggle', event);
            }
        }
    }
</script>

<style lang="scss" scoped>
    div {
        display: inline;
    }

    button {
        background: none;
        border: none;
        outline: none;
        cursor: pointer;
    }
</style>