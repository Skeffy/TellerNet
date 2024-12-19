<script>
export default {
    props: ['result']
}
</script>

<template>
    <tr>
        
        <td>
            <router-link :to="`/customer/${result.customerId}`">
                {{ result.firstName }} {{ result.lastName }}
            </router-link>
        </td>
        <td>{{ result.phone }}</td>
        <td>{{ result.email }}</td>
        <td>{{ result.address }}</td>
        <td>{{ result.dob }}</td>
    </tr>
</template>

<style scoped>

</style>