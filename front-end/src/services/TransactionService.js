import axios from 'axios';

export default {

    getTransactions(id) {
        return axios.get('/transactions', {
            params: {
                accountId: id
            }
        });
    },

    createTransaction(transaction) {
        return axios.post('/transactions', {
            params: {
                accountId: transaction.accountId,
                amount: transaction.amount,
                description: transaction.description
            }
        });
    }
}