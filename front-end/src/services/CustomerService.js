import axios from 'axios';

export default {

    getCustomers(customer) {
        return axios.get('/customers', {
            params: {
                customerId: customer.customerId,
                firstName: customer.firstName,
                lastName: customer.lastName,
                phone: customer.phone,
                address: customer.address,
                email: customer.email,
                dob: customer.dob
            }
        });
    },

    createCustomer(customer) {
        return axios.post('/customers', {
            firstName: customer.firstName,
            lastName: customer.lastName,
            phone: customer.phone,
            address: customer.address,
            email: customer.email,
            dob: customer.dob
        });
    },

    updateCustomer(customer) {
        return axios.put('/customers', {
            firstName: customer.firstName,
            lastName: customer.lastName,
            phone: customer.phone,
            address: customer.address,
            email: customer.email,
            dob: customer.dob
        });
    },

    deleteCustomer(customer) {
        return axios.delete('/customers', {
            customerId: customer.customerId
        });
    }
}