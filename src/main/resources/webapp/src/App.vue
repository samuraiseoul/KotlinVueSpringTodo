<template>
    <div id="app">
        <main>
            <h3>Todo List</h3>
            <router-link to="/login">Login</router-link>
            <router-view></router-view>
        </main>
    </div>
</template>

<script>
    export default {
        name: 'app'
    }
</script>


<style lang="scss" scoped>
    h3 {
        font-size: 3em;
    }

    main {
        padding: 1em;
    }

    * {
        font-family: 'Font Awesome 5 Free', sans-serif;
        margin: .35em auto;
    }
</style>
