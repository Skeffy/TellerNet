<script setup>
import directory from './components/Directory.vue';
</script>

<template>
    <div id="heading">
        <h1>TellerNet</h1>
    </div>
    <directory />
    <main>
        <router-view />
    </main>
</template>

<style>
#app {
    display: grid;
    grid-template-columns: 1fr 4fr 1fr;
    grid-template-areas: 
    "heading heading heading"
    "nav body hold";
}

#heading {
    grid-area: heading;
    display: flex;
    justify-content: center;
    border-bottom: 2px solid rgb(35, 35, 158);
}

aside {
    width: 125px;
    height: 100%;
    grid-area: nav;
    padding-top: 25px;
    background-color: rgb(35, 35, 158);
}

main {
    grid-area: body;
}
</style>
