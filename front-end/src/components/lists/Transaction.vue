<script>
export default {
    props: ['transaction'],
}
</script>

<template>
<tr>
    <td>{{ transaction.transactionDate }}</td>
    <td>{{ transaction.description }}</td>
    <td>{{ transaction.amount }}</td>
</tr>
</template>

<style>

</style>