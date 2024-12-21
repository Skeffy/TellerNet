<script>
import CustomerResult from '@/components/lists/CustomerResult.vue';

export default {
    components: {
        CustomerResult
    },
}
</script>

<template>
    <div id="search-results" class="list">
        <table>
            <tbody>
                <tr>
                    <th>Name</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th>Date of Birth</th>
                </tr>
                <CustomerResult v-for="result in this.$store.state.customers" :result="result"/>
            </tbody>
        </table>
    </div>
</template>

<style scoped>
#search-results {
    justify-items: center;
}

th {
    width: 20%;
}
</style>