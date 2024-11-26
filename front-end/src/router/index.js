import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import CustomersView from '@/views/CustomersView.vue';
import CreateAccountView from '@/views/CreateAccountView.vue';
import CreateCustomerView from '@/views/CreateCustomerView.vue';

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
    }
  ]
});

export default router
