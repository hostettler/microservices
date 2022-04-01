import { NbMenuItem } from '@nebular/theme';

export const MENU_ITEMS: NbMenuItem[] = [
  {
    title: 'Finance Dashboard',
    icon: 'shopping-cart-outline',
    link: '/pages/e-commerce',
    home: true,
  },
  {
    title: 'FEATURES',
    group: true,
  },
  {
    title: 'Miscellaneous',
    icon: 'shuffle-2-outline',
    children: [
      {
        title: '404',
        link: '/pages/miscellaneous/404',
      },
    ],
  },
];
