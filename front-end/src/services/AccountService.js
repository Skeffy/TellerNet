import axios from 'axios';

export default {

    getAccounts(customer) {
        return axios.get('/account', {
            customerId: customer.customerId
        });
    },

    
}