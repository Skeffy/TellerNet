<script>
import Search from '@/components/Search.vue';
import ResultsList from '@/components/lists/ResultsList.vue';

export default {
    components: {
        Search,
        ResultsList
    }
}
</script>

<template>
    <div id="home">
        <Search/>
        <ResultsList v-if="this.$store.state.customers.length > 0" />
    </div>
</template>

<style>
#home {
    display: flex;
    flex-direction: column;
}
</style>