import axios from 'axios';

export default {

    getAccounts(id) {
        return axios.get('/accounts', {
            params: {
                customerId: id
            }
        });
    },

    
}