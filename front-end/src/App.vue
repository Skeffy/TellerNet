<script setup>

</script>

<template>
    <div id="heading">
        <h1>TellerNet</h1>
    </div>
    <nav>
        <router-link to="/accounts">Accounts</router-link>
        <router-link to="/customers">Customers</router-link>
    </nav>
    <main>
        <router-view />
    </main>
</template>

<style>
#app {
    display: grid;
    grid-template-columns: 1fr, 4fr, 1fr;
    grid-template-areas: 
    "heading heading heading"
    "nav body hold";
}

#heading {
    grid-area: heading;
    display: flex;
    justify-content: center;
}

nav {
    display: flex;
    flex-direction: column;
    grid-area: nav;
}

main {
    grid-area: body;
}
</style>
