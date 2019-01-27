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
    ]
  },
  {
    title: 'Instruments',
    icon: 'nb-bar-chart',
    children: [
      {
        title: 'Search',
        link: '/pages/charts/echarts',
      },
      {
        title: 'Update',
        link: '/pages/charts/echarts',
      },
    ]
  },
  {
    title: 'Configuration',
    icon: 'nb-locked',
    children: [
      {
        title: 'Search',
        link: '/pages/charts/echarts',
      },
      {
        title: 'Update',
        link: '/pages/charts/echarts',
      },
    ]
  },
  {
    title: 'FEATURES',
    group: true,
  },
  {
    title: 'Charts',
    icon: 'nb-bar-chart',
    children: [
      {
        title: 'Echarts',
        link: '/pages/charts/echarts',
      },
      {
        title: 'Charts.js',
        link: '/pages/charts/chartjs',
      },
      {
        title: 'D3',
        link: '/pages/charts/d3',
      },
    ],
  },
  {
    title: 'Tables',
    icon: 'nb-tables',
    children: [
      {
        title: 'Smart Table',
        link: '/pages/tables/smart-table',
      },
    ],
  },
  {
    title: 'Auth',
    icon: 'nb-locked',
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
