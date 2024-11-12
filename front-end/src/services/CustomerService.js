import axios from 'axios';

export default {

    getCustomers(customer) {
        return axios.get('/customers', data = {
            customer
        });
    },

    createCustomer(customer) {
        return axios.post('/customers', data = {
            customer
        });
    },

    updateCustomer(customer) {
        return axios.put('/customers', data = {
            customer
        });
    },

    deleteCustomer(customer) {
        return axios.delete('/customers', data = {
            customer
        });
    }
}