import { NbMenuItem } from '@nebular/theme';

export const MENU_ITEMS: NbMenuItem[] = [
  {
    title: 'Dashboards',
    group: true,
  },
  {
    title: 'Dashboard',
    icon: 'nb-bar-chart',
    link: '/pages/fi-dashboard',
    home: true,
  },
  {
    title: 'Master Data',
    group: true,
  },
  {
    title: 'Counterparties',
    icon: 'nb-person',
    children: [
      {
        title: 'Search',
        link: '/pages/charts/echarts',
      },
      {
        title: 'Update',
        link: '/pages/charts/echarts',
      },
    ],
  },
  {
    title: 'Instruments',
    icon: 'ion-ios-pricetags',
    children: [
      {
        title: 'Search',
        link: '/pages/charts/echarts',
      },
      {
        title: 'Update',
        link: '/pages/charts/echarts',
      },
    ],
  },
  {
    title: 'Configuration',
    icon: 'nb-gear',
    children: [
      {
        title: 'Login',
        link: '/auth/login',
      },
      {
        title: 'Register',
        link: '/auth/register',
      },
      {
        title: 'Request Password',
        link: '/auth/request-password',
      },
      {
        title: 'Reset Password',
        link: '/auth/reset-password',
      },
    ],
  },
];
