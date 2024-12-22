<script>
import AccountService from '@/services/AccountService';
import AccountList from '@/components/lists/AccountList.vue';

export default {
    components: {
        AccountList,
    },

    mounted() {
        AccountService.getAccounts(this.$route.params.id).then( (response) => {
            this.$store.commit('SET_ACCOUNTS', response.data);
        });
    }
}
</script>

<template>
    <div id="customer-view">
        <AccountList />
    </div>
</template>

<style>

</style>