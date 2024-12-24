import { createStore as _createStore } from 'vuex';

export function createStore() {
    return _createStore({
        state: {
            customers: [],
            accounts: [],
            transactions: []
        },

        mutations: {
            SET_CUSTOMERS(state, payload) {
                state.customers = payload;
            },

            SET_ACCOUNTS(state, payload) {
                state.accounts = payload;
            },

            SET_TRANSACTIONS(state, payload) {
                state.transactions = payload;
            },

            CLEAR(state) {
                state.customers = [];
                state.accounts = [];
                state.transactions = [];
            }
        }
    });
}