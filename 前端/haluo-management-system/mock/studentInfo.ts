import mockjs from 'mockjs';
import express, { Request, Response } from 'express';

export default {
  //获取学生信息
  '/api/teacher/stu/search': (req: Request, res: Response) => {
    console.log('接口调用了吗');
    const { name, pageSize, pageNum } = req.query;
    if (name === '') {
      res.send({
        code: 1,
        msg: null,
        data: {
          total: 1,
          pageNum: 3,
          pageSize: 5,
          list: [
            {
              id: 1,
              username: '张三',
              userId: 1,
              collegeId: 1,
              majorId: 1,
              grade: 2021,
              education: '本科',
              phone: '12345',
              collegeName: '计算机院',
              majorName: '计科',
            },
            {
              id: 2,
              username: '张三',
              userId: 1,
              collegeId: 1,
              majorId: 1,
              grade: 2021,
              education: '本科',
              phone: '12345',
              collegeName: '计算机院',
              majorName: '计科',
            },
            {
              id: 3,
              username: '张三',
              userId: 1,
              collegeId: 1,
              majorId: 1,
              grade: 2021,
              education: '本科',
              phone: '12345',
              collegeName: '计算机院',
              majorName: '计科',
            },
          ],
        },
      });
    } else {
      res.send({
        code: 1,
        msg: null,
        data: {
          total: 1,
          pageNum: 3,
          pageSize: 5,
          list: [
            {
              id: 1,
              username: '里斯',
              userId: 1,
              collegeId: 1,
              majorId: 1,
              grade: 2021,
              education: '本科',
              phone: '12345',
              collegeName: '计算机院',
              majorName: '计科',
            },
            {
              id: 2,
              username: '里斯',
              userId: 1,
              collegeId: 1,
              majorId: 1,
              grade: 2021,
              education: '本科',
              phone: '12345',
              collegeName: '计算机院',
              majorName: '计科',
            },
            {
              id: 3,
              username: '里斯',
              userId: 1,
              collegeId: 1,
              majorId: 1,
              grade: 2021,
              education: '本科',
              phone: '12345',
              collegeName: '计算机院',
              majorName: '计科',
            },
          ],
        },
      });
    }
  },
  //获取周报管理信息
  '/teacher/tnotices': (req: Request, res: Response) => {
    const { name, start, end, page, pagesize } = req.query;
    if (name === '') {
      res.send({
        code: 1,
        msg: 'success',
        data: {
          total: 2,
          rows: [
            {
              id: 1,
              username: '张三',
              startTime: '2023-04-01',
              endTime: '2023-04-03',
              thisWeekWork: '完成登录模块的概要设计',
              nextWeekWork: '部分需求没有搞定比如...',
              finished: 'true',
              createTime: '2024-01-12T22:26:46',
              updateTime: '2024-01-12T22:26:51',
              deleted: 0,
            },
            {
              id: 2,
              username: '张三',
              startTime: '2023-04-01',
              endTime: '2023-04-03',
              thisWeekWork: '针对算法做一个编程落地',
              nextWeekWork: '单测还不太熟悉',
              finished: 'false',
              createTime: '2024-01-12T22:26:46',
              updateTime: '2024-01-12T22:26:51',
              deleted: 0,
            },
            {
              id: 3,
              username: '张三',
              startTime: '2023-04-01',
              endTime: '2023-04-03',
              thisWeekWork: '针对算法做一个编程落地',
              nextWeekWork: '单测还不太熟悉',
              finished: 'false',
              createTime: '2024-01-12T22:26:46',
              updateTime: '2024-01-12T22:26:51',
              deleted: 0,
            },
          ],
        },
      });
    } else {
      res.send({
        code: 1,
        msg: 'success',
        data: {
          total: 2,
          rows: [
            {
              id: 1,
              username: '张三',
              startTime: '2023-04-01',
              endTime: '2023-04-03',
              thisWeekWork: '完成登录模块的概要设计',
              nextWeekWork: '部分需求没有搞定比如...',
              finished: 'true',
              createTime: '2024-01-12T22:26:46',
              updateTime: '2024-01-12T22:26:51',
              deleted: 0,
            },
            {
              id: 2,
              username: '张三',
              startTime: '2023-04-01',
              endTime: '2023-04-03',
              thisWeekWork: '针对算法做一个编程落地',
              nextWeekWork: '单测还不太熟悉',
              finished: 'false',
              createTime: '2024-01-12T22:26:46',
              updateTime: '2024-01-12T22:26:51',
              deleted: 0,
            },
          ],
        },
      });
    }
  },
  //获取周报个人信息
  '/teacher/tnotices/one': (req: Request, res: Response) => {
    res.send({
      code: 1,
      msg: 'success',
      data: {
        id: 1,
        username: 'zhouxians',
        thisWeekWork: '完成登录模块的概要设计',
        nextWeekWork: '部分需求没有搞定比如...',
        finished: 'true',
        thisWeekIdea: '太难了啊啊啊啊',
        nextWeekIdea: '摆烂',
        createTime: '2024-01-12T22:26:46',
        updateTime: '2024-01-12T22:26:51',
        deleted: 0,
      },
    });
  },
  //获取打卡信息
  '/api/teacher/sign/search': (req: Request, res: Response) => {
    console.log('接口调用了吗');
    const { name, pageSize, pageNum } = req.query;
    if (name === '') {
      res.send({
        code: 1,
        msg: 'success',
        data: {
          total: 30,
          pageSize: 5,
          pageNum: 1,
          totalPagae: 6,
          list: [
            {
              id: 1,
              userId: 1,
              validTime: 18,
              checkDate: '20240108',
              checkTime: '23-23',
              username: '张1',
            },
            {
              id: 2,
              userId: 1,
              validTime: 5,
              checkDate: '20240108',
              checkTime: '23-23',
              username: '张2',
            },
            {
              id: 3,
              userId: 1,
              validTime: 26,
              checkDate: '20240108',
              checkTime: '23-23',
              username: '张3',
            },
            {
              id: 4,
              userId: 1,
              validTime: 42,
              checkDate: '20240108',
              checkTime: '23-23',
              username: '张5',
            },
            {
              id: 5,
              userId: 1,
              validTime: 3,
              checkDate: '20240108',
              checkTime: '23-23',
              username: '张8',
            },
            {
              id: 6,
              userId: 1,
              validTime: 12,
              checkDate: '20240108',
              checkTime: '23-23',
              username: '张三',
            },
            {
              id: 7,
              userId: 1,
              validTime: 12,
              checkDate: '20240108',
              checkTime: '23-23',
              username: '张三',
            },
            {
              id: 8,
              userId: 1,
              validTime: 12,
              checkDate: '20240108',
              checkTime: '23-23',
              username: '张三',
            },
          ],
        },
      });
    } else {
      res.send({
        code: 1,
        msg: 'success',
        data: {
          total: 1,
          pageSize: 5,
          pageNum: 1,
          totalPagae: 6,
          list: [
            {
              id: 1,
              userId: 1,
              valid_time: 12,
              check_date: '20240108',
              check_time: '23-23',
              username: '李四',
            },
          ],
        },
      });
    }
  },
  //获取空闲时段消息
  '/api/teacher/freetimes': (req: Request, res: Response) => {
    const { id } = req.query;
    if (id === '1') {
      res.send({
        code: 1,
        msg: 'success',
        data: [
          {
            totalnum: 107,
            freeTime: [
              {
                id: 1,
                time: '9.00-10.00',
                Mon: 2,
                Tues: 1,
                Wednes: 5,
                Thurs: 8,
                Fri: 9,
              },
              {
                id: 2,
                time: '10.00-11.00',
                Mon: 2,
                Tues: 1,
                Wednes: 8,
                Thurs: 1,
                Fri: 3,
              },
              {
                id: 3,
                time: '11.00-12.00',
                Mon: 8,
                Tues: 5,
                Wednes: 5,
                Thurs: 1,
                Fri: 6,
              },
              {
                id: 4,
                time: '14.00-15.00',
                Mon: 5,
                Tues: 4,
                Wednes: 3,
                Thurs: 5,
                Fri: 8,
              },
              {
                id: 5,
                time: '15.00-16.00',
                Mon: 6,
                Tues: 7,
                Wednes: 5,
                Thurs: 5,
                Fri: 4,
              },
              {
                id: 6,
                time: '16.00-17.00',
                Mon: 2,
                Tues: 1,
                Wednes: 5,
                Thurs: 8,
                Fri: 9,
              },
              {
                id: 7,
                time: '17.00-18.00',
                Mon: 2,
                Tues: 1,
                Wednes: 5,
                Thurs: 8,
                Fri: 9,
              },
              {
                id: 8,
                time: '19.00-20.00',
                Mon: 2,
                Tues: 1,
                Wednes: 5,
                Thurs: 8,
                Fri: 9,
              },
              {
                id: 9,
                time: '20.00-21.00',
                Mon: 2,
                Tues: 1,
                Wednes: 5,
                Thurs: 8,
                Fri: 9,
              },
            ],
          },
        ],
      });
    } else {
      res.send({
        code: 1,
        msg: 'success',
        data: [
          {
            totalnum: 107,
            freeTime: [
              {
                id: 1,
                time: '9.00-10.00',
                Mon: 5,
                Tues: 6,
                Wednes: 5,
                Thurs: 9,
                Fri: 9,
              },
              {
                id: 2,
                time: '10.00-11.00',
                Mon: 9,
                Tues: 9,
                Wednes: 9,
                Thurs: 9,
                Fri: 9,
              },
              {
                id: 3,
                time: '11.00-12.00',
                Mon: 8,
                Tues: 5,
                Wednes: 5,
                Thurs: 1,
                Fri: 6,
              },
              {
                id: 4,
                time: '14.00-15.00',
                Mon: 5,
                Tues: 4,
                Wednes: 3,
                Thurs: 5,
                Fri: 8,
              },
              {
                id: 5,
                time: '15.00-16.00',
                Mon: 6,
                Tues: 7,
                Wednes: 5,
                Thurs: 5,
                Fri: 4,
              },
              {
                id: 6,
                time: '16.00-17.00',
                Mon: 2,
                Tues: 1,
                Wednes: 5,
                Thurs: 8,
                Fri: 9,
              },
              {
                id: 7,
                time: '17.00-18.00',
                Mon: 2,
                Tues: 1,
                Wednes: 5,
                Thurs: 8,
                Fri: 9,
              },
              {
                id: 8,
                time: '19.00-20.00',
                Mon: 2,
                Tues: 1,
                Wednes: 5,
                Thurs: 8,
                Fri: 9,
              },
              {
                id: 9,
                time: '20.00-21.00',
                Mon: 2,
                Tues: 1,
                Wednes: 5,
                Thurs: 8,
                Fri: 9,
              },
            ],
          },
        ],
      });
    }
  },
};
