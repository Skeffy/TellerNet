<script>
import Account from './Account.vue';

export default {
    components: {
        Account
    }
}
</script>

<template>
<div id="account-list">
    <table class="list">
        <tbody>
            <tr>
                <th>Account Number</th>
                <th>Nickname</th>
                <th>Balance</th>
            </tr>
            <Account v-for="account in this.$store.state.accounts" :account="account" />
        </tbody>
    </table>
</div>
</template>

<style>

</style>