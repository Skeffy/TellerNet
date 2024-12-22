<script>
import TransactionService from '@/services/TransactionService';
import TransactionList from '@/components/lists/TransactionList.vue';

export default {
    components: {
        TransactionList
    },

    mounted() {
        TransactionService.getTransactions(this.$route.params.id).then( (response) => {
            this.$store.commit('SET_TRANSACTIONS', response.data);
        });
    }
}
</script>

<template>
<div id="account-view">
    <TransactionList />
</div>
</template>

<style>

</style>