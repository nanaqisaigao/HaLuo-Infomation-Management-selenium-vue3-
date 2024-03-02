import { Request, Response } from 'express';

export default {
  // 登录
  'POST /api/user/login': (req: Request, res: Response) => {
    res.send({
      code: 1,
      msg: 'success',
      data: {
        token: 'xdfgxdg.fgfg.gdfgfss',
        User_id: 1,
        level: 'Student',
        username: '张三',
      },
    });
  },

  // 提交打卡时间
  'POST /api/student/sign/create': (req: Request, res: Response) => {
    res.send({
      code: 1,
      msg: 'success',
      data: req.body,
    });
  },

  // 打卡记录
  'GET /api/student/search': (req: Request, res: Response) => {
    res.send({
      code: 1,
      msg: 'success',
      test: req.query,
      data: [
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-09:00,10:00-12:00',
          validTime: 15,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
        {
          checkDate: '2022-09-02',
          checkTime: '02:00-13:00',
          validTime: 5,
        },
      ],
    });
  },

  // 空闲时间
  'GET /api/student/freetime': (req: Request, res: Response) => {
    res.send({
      code: 1,
      msg: '成功获取用户空闲时间列表',
      test: req.query,
      data: [
        {
          freeTime: [
            [1, 0, 1, 0, 1],
            [1, 0, 1, 0, 1],
            [0, 1, 0, 1, 0],
            [1, 0, 1, 0, 1],
            [0, 1, 0, 1, 0],
            [1, 0, 1, 0, 1],
            [1, 0, 1, 0, 1],
            [0, 1, 0, 1, 0],
            [1, 0, 1, 0, 1],
          ],
        },
      ],
    });
  },

  // 修改空闲时间
  'POST /api/student/changefreetime': (req: Request, res: Response) => {
    res.send({
      code: 1,
      msg: '成功',
      test: req.body,
    });
  },

  // 添加周报
  'POST /student/addnotice': (req: Request, res: Response) => {
    res.send({
      code: 1,
      msg: '添加文章成功',
      test: req.body,
    });
  },

  // 获取周报列表
  'GET /student/tnotices': (req: Request, res: Response) => {
    res.send({
      code: '1',
      msg: null,
      data: {
        total: 1,
        list: [
          {
            id: 1,
            studentId: 1,
            startTime: '2024-02-12',
            endTime: '2024-02-12',
            thisWeekWork: 'thisWeek',
            thisWeekIdea: 'thisWeekIdea',
            finished: true,
            nextWeekWork: 'nextWeek',
            createTime: '2024-02-12',
            updateTime: '2024-02-12',
            deleted: 'deleted',
            userName: '加密——学生',
          },
          {
            id: 1,
            studentId: 1,
            startTime: '2024-02-12',
            endTime: '2024-02-12',
            thisWeekWork: 'thisWeek',
            thisWeekIdea: 'thisWeekIdea',
            finished: true,
            nextWeekWork: 'nextWeek',
            createTime: '2024-02-12',
            updateTime: '2024-02-12',
            deleted: 'deleted',
            userName: '加密——学生',
          },
          {
            id: 1,
            studentId: 1,
            startTime: '2024-02-12',
            endTime: '2024-02-12',
            thisWeekWork: 'thisWeek',
            thisWeekIdea: 'thisWeekIdea',
            finished: true,
            nextWeekWork: 'nextWeek',
            createTime: '2024-02-12',
            updateTime: '2024-02-12',
            deleted: 'deleted',
            userName: '加密——学生',
          },
        ],
        pageNum: 1,
        pageSize: 1,
        size: 1,
        startRow: 0,
        endRow: 0,
        pages: 1,
        prePage: 0,
        nextPage: 0,
        isFirstPage: true,
        isLastPage: true,
        hasPreviousPage: false,
        hasNextPage: false,
        navigatePages: 8,
        navigatepageNums: [1],
        navigateFirstPage: 1,
        navigateLastPage: 1,
      },
    });
  },

  // 修改周报
  'PUT /student/changenotice': (req: Request, res: Response) => {
    res.send({
      code: 1,
      msg: '修改文章成功',
      test: req.body,
    });
  },

  // 根据id获取文章内容
  // '/student/noticeDetail': (req: Request, res: Response) => {
  //   res.send({
  //     code: 1,
  //     msg: 'success',
  //     test: req.query,
  //     data: {
  //       id: 1,
  //       thisWeekWork: '完成登录模块的概要设计',
  //       finished: 'true',
  //       thisWeekIdea: '太难了啊啊啊啊',
  //       nextWeekWork: '摆烂',
  //       createTime: '2024-01-12T22:26:46',
  //       updateTime: '2024-01-12T22:26:51',
  //       deleted: 0,
  //     },
  //   });
  // },

  // 获取用户信息
  '/api/student/getmes/:user_id': (req: Request, res: Response) => {
    const { user_id } = req.params;
    if (user_id === '0') {
      res.send({
        code: 1,
        msg: 'success',
        data: [
          {
            user_id: 0,
            phone: '293847562910',
            username: '李四',
            college_name: '信息学院',
            major_name: '电子信息工程',
            grade: '2022',
            education: '本科',
          },
        ],
      });
    } else if (user_id === '1') {
      res.send({
        code: 1,
        msg: 'success',
        data: [
          {
            user_id: 1,
            phone: '192982395982',
            username: '张三',
            college_name: '计算机学院',
            major_name: '软件工程',
            grade: '2021',
            education: '本科',
          },
        ],
      });
    } else {
      res.send({
        status: 'error',
        data: {},
      });
    }
  },
  // 修改信息
  'PUT /api/student/changemes': (req: Request, res: Response) => {
    res.send({
      status: 'ok',
      data: {
        code: 1,
        msg: 'success',
        data: req.body,
      },
    });
  },
  // 修改密码
  'PUT /api/student/changepsw': (req: Request, res: Response) => {
    res.send({
      status: 'ok',
      data: {
        code: 1,
        msg: 'success',
        data: req.body,
      },
    });
  },
};
