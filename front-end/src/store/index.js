import { createStore as _createStore } from 'vuex';

export function createStore() {
    return _createStore({
        state: {
            customers: [],
        },

        mutations: {
            SET_CUSTOMERS(state, payload) {
                state.customers = payload;
            },
        }
    });
}