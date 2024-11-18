<script>
export default {
    props: ['result']
}
</script>

<template>
    <tr>
        <td>{{ result.firstName }} {{ result.lastName }}</td>
        <td>{{ result.phone }}</td>
        <td>{{ result.email }}</td>
        <td>{{ result.address }}</td>
        <td>{{ result.dob }}</td>
    </tr>
</template>

<style scoped>

</style>