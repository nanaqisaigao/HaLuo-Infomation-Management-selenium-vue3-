export default [
  {
    path: '/user',
    layout: false,
    routes: [
      {
        name: 'login',
        path: '/user/login',
        component: './user/Login',
      },
      {
        component: './404',
      },
    ],
  },
  {
    path: '/welcome',
    name: 'welcome',
    icon: 'smile',
    component: './Welcome',
  },
  // 学生端
  {
    path: '/student',
    name: '学生端',
    icon: 'crown',
    access: 'Student',
    flatMenu: true,
    routes: [
      {
        path: 'time-management',
        name: '时间管理',
        icon: 'crown',
        access: 'canAdmin',
        routes: [
          {
            path: 'clock-in',
            name: '每日打卡',
            component: './student/ClockIn',
          },
          {
            path: 'clock-records',
            name: '打卡记录',
            component: './student/ClockRecords',
          },
          {
            path: 'free-time',
            name: '空闲时段',
            component: './student/FreeTime',
          },
          {
            component: './404',
          },
        ],
      },
      {
        name: '周报管理',
        icon: 'table',
        path: 'list',
        // component: './TableList',
        routes: [
          {
            path: 'add-report',
            name: '添加周报',
            component: './student/AddReport',
          },
          {
            path: 'my-reports',
            name: '我的周报',
            component: './student/MyReports',
          },
          {
            component: './404',
          },
        ],
      },
      {
        path: 'personalInfo',
        name: '个人信息',
        icon: 'user',
        routes: [
          {
            path: 'modify-info',
            name: '修改个人信息',
            component: './student/ModifyInfo',
          },
          {
            path: 'change-psw',
            name: '修改密码',
            component: './student/ChangePsw',
          },
          {
            component: './404',
          },
        ],
      },
      {
        component: './404',
      },
    ],
  },
  // 管理端
  {
    path: '/manager',
    name: '管理端',
    icon: 'crown',
    flatMenu: true,
    access: 'Teacher',
    routes: [
      {
        path: 'time-management',
        name: '时间管理',
        icon: 'crown',
        access: 'canAdmin',
        routes: [
          {
            path: 'clock-record',
            name: '打卡记录',
            component: './manager/Clockrecord',
          },
          {
            path: 'free-time',
            name: '空闲时段',
            component: './manager/Freetime',
          },
          {
            component: './404',
          },
        ],
      },
      {
        name: '周报管理',
        icon: 'table',
        path: 'list',
        component: './manager/TableList',
      },
      {
        path: 'StudentInfo',
        name: '个人信息',
        icon: 'user',
        component: './manager/StudentInfo',
      },
      {
        component: './404',
      },
    ],
  },
  {
    path: '/',
    redirect: '/welcome',
  },
  {
    component: './404',
  },
];
