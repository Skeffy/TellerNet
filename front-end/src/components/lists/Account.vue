<script>
export default {
    props: ['account'],
}
</script>

<template>
<tr>
    <router-link :to="`/account/${account.accountId}`">
        <td>{{ account.accountId }}</td>
    </router-link>
    <td>{{ account.nickname }}</td>
    <td>{{ account.balance }}</td>
</tr>
</template>

<style>

</style>