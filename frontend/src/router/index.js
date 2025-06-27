import { createRouter, createWebHashHistory } from 'vue-router';

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      component: () => import('../components/pages/Index.vue'),
    },
    {
      path: '/authors',
      component: () => import('../components/ui/AuthorGrid.vue'),
    },
    {
      path: '/manuscripts',
      component: () => import('../components/ui/ManuscriptGrid.vue'),
    },
    {
      path: '/publications',
      component: () => import('../components/ui/PublicationGrid.vue'),
    },
    {
      path: '/books',
      component: () => import('../components/ui/BookGrid.vue'),
    },
    {
      path: '/members',
      component: () => import('../components/ui/MemberGrid.vue'),
    },
    {
      path: '/userpages',
      component: () => import('../components/UserpageView.vue'),
    },
    {
      path: '/subcriptions',
      component: () => import('../components/ui/SubcriptionGrid.vue'),
    },
    {
      path: '/pointAccounts',
      component: () => import('../components/ui/PointAccountGrid.vue'),
    },
  ],
})

export default router;
