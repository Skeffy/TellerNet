<script>
export default {

    props: ['account'],
}
</script>

<template>
<tr>
    <td>{{ account.accountId }}</td>
    <td>{{ account.nickname }}</td>
    <td>{{ account.balance }}</td>
</tr>
</template>

<style>

</style>