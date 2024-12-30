<script>
export default {
    methods: {
        clearStore() {
            this.$store.commit('CLEAR');
        }
    }
}
</script>

<template>
    <aside id="directory">
        <nav>
            <router-link to="/" @click="clearStore">Home</router-link>
            <router-link to="/customers">Customers</router-link>
            <router-link to="/newcustomer">New Customer</router-link>
            <router-link to="/newaccount">New Account</router-link>
        </nav>
    </aside>
</template>

<style scoped>
nav{
    display: flex;
    flex-direction: column;
    padding: 10px;
    justify-self: center;
}
</style>