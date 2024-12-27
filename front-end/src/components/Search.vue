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
        search() {
            CustomerService.getCustomers(this.customer).then( (response) => {
                this.$store.commit("SET_CUSTOMERS", response.data);
            });
        }
    }
}
</script>

<template>
    <div id="search">
        <form action="">
            <label for="fname">First: </label>
            <input type="text" name="fname" id="fname" v-model="customer.firstName">
            <label for="lname">Last: </label>
            <input type="text" name="lname" id="lname" v-model="customer.lastName">
            <br>
            <label for="phone">Phone: </label>
            <input type="tel" name="phone" id="phone" v-model="customer.phone">
            <label for="address">Address: </label>
            <input type="text" name="address" id="address" v-model="customer.address">
            <br>
            <label for="email">Email: </label>
            <input type="email" name="email" id="email" v-model="customer.email">
            <label for="dob">DOB: </label>
            <input type="date" name="dob" id="dob" v-model="customer.dob">
            <input class="submit" type="button" value="Search" @click="search">
        </form>
    </div>
</template>

<style>
#search {
    display: flex;
    align-items: center;
    height: 100px;
    margin-top: 25px;
    margin-bottom: 25px;
    padding-left: 10px;
    padding-right: 10px;
}

.submit {
    margin-left: 20px;
}

label {
    padding-left: 15px;
}
</style>