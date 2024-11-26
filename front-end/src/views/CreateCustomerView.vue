<script>
import CustomerService from '@/services/CustomerService';

export default {
    data() {
        return {
            customer: {
                customerId: 0,
                firstName: '',
                lastName: '',
                phone: '',
                address: '',
                email: '',
                dob: ''
            }
        }
    },

    methods: {
        create() {
            CustomerService.createCustomer(this.customer).then( (response) => {
                if (response.status == 400) {

                } else {
                    this.$store.commit("CREATE_CUSTOMER", response.data);
                }
            });
        }
    }
}
</script>

<template>
    <div id="create-customer">
        <form @submit.prevent="create">
            <label for="fname">First: </label>
            <input type="text" name="fname" id="fname" v-model="customer.firstName" required>
            <label for="lname">Last: </label>
            <input type="text" name="lname" id="lname" v-model="customer.lastName" required>
            <label for="phone">Phone: </label>
            <input type="tel" name="phone" id="phone" v-model="customer.phone" required>
            <br>
            <label for="address">Address: </label>
            <input type="text" name="address" id="address" v-model="customer.address" required>
            <label for="email">Email: </label>
            <input type="email" name="email" id="email" v-model="customer.email" required>
            <label for="dob">DOB: </label>
            <input type="date" name="dob" id="dob" v-model="customer.dob" required>
            <input type="submit" value="Create">
        </form>
    </div>
</template>

<style>

</style>