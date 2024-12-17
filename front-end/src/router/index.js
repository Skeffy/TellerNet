import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import CustomersView from '@/views/CustomersView.vue';
import CreateAccountView from '@/views/CreateAccountView.vue';
import CreateCustomerView from '@/views/CreateCustomerView.vue';
import AccountView from '@/views/AccountView.vue';
import CustomerView from '@/views/CustomerView.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },

    {
      path: '/customers',
      name: 'customers',
      component: CustomersView
    },

    {
      path: '/newcustomer',
      name: 'newCustomer',
      component: CreateCustomerView
    },

    {
      path: '/newaccount',
      name: 'newAccount',
      component: CreateAccountView
    },

    {
      path: '/account/:id',
      name: 'account',
      component: AccountView
    },

    {
      path: '/customer/:id',
      name: 'customer',
      component: CustomerView
    },
  ]
});

export default router
