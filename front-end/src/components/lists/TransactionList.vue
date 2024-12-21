<script>
import Transaction from './Transaction.vue';

export default {
    components: {
        Transaction
    }
}
</script>

<template>
<div id="transaction-list" class="list">
    <table>
        <tbody>
            <tr>
                <th>Date</th>
                <th>Description</th>
                <th>Amount</th>
            </tr>
            <Transaction v-for="transaction in this.$store.state.transactions" :transaction="transaction"/>
        </tbody>
    </table>
</div>
</template>

<style>

</style>