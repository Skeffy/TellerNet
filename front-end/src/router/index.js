import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import CustomersView from '@/views/CustomersView.vue';
import AccountsView from '@/views/AccountsView.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },

    {
      path: '/accounts',
      name: 'accounts',
      component: AccountsView
    },

    {
      path: '/customers',
      name: 'customers',
      component: CustomersView
    },
  ]
});

export default router
