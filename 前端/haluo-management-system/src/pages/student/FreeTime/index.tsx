import React, { useEffect, useState } from 'react';
import { Button, Table, message } from 'antd';
import type { TableColumnsType } from 'antd';
import { CheckOutlined, CloseOutlined } from '@ant-design/icons';
import { getFreeTime, changeFreeTime } from '@/services/ant-design-pro/myAPI';
import { useModel } from 'umi';
import './index.less';
import e from 'express';

const App: React.FC = () => {
  const { initialState } = useModel('@@initialState');

  interface DataType {
    key: number;
    period: string;
    monday: {
      free: number;
      week: number;
      day: number;
      period: number;
    };
    tuesday: number;
    wednesday: number;
    thursday: number;
    friday: number;
  }

  const [freeTime, setFreeTime] = useState([[]]);
  const [week, setWeek] = useState(1);

  const fetchData = () => {
    getFreeTime({ userId: initialState!.User_id, index: week })
      .then((res) => {
        // console.log(res);
        if (res.code == 1) {
          setFreeTime(res.data);
          message.success('获取空闲时间成功');
        } else {
          message.error('获取空闲时间失败');
        }
      })
      .catch((err) => {
        // console.log(err);
        message.error('获取空闲时间失败');
      });
  };

  const toChangeFreeTime = (data: any) => {
    const index = data.data.free === 1 ? 0 : 1;
    changeFreeTime({
      userId: initialState?.User_id,
      index: data.data.week,
      day: data.data.day,
      time: data.data.period,
      status: index,
    })
      .then((res) => {
        // console.log(res);
        if (res.code == 1) {
          message.success('修改成功');
          fetchData();
        } else {
          message.error('修改失败');
        }
      })
      .catch((err) => {
        // console.log(err);
        message.error('修改失败');
      });
  };

  const Free = (data: any) => {
    return (
      <CheckOutlined
        onClick={() => {
          toChangeFreeTime(data);
        }}
        style={{ color: 'green', fontSize: '20px' }}
      />
    );
  };
  const NotFree = (data: any) => {
    return (
      <CloseOutlined
        onClick={() => {
          toChangeFreeTime(data);
        }}
        style={{ color: 'red', fontSize: '20px' }}
      />
    );
  };

  const columns: TableColumnsType<DataType> = [
    {
      title: '时间段',
      dataIndex: 'period',
      key: 'period',
    },
    {
      title: '周一',
      dataIndex: 'monday',
      key: 'monday',
      render: (f) => {
        if (f.free === 1) return <Free data={f} />;
        else return <NotFree data={f} />;
      },
    },
    {
      title: '周二',
      dataIndex: 'tuesday',
      key: 'tuesday',
      render: (f) => {
        if (f.free === 1) return <Free data={f} />;
        else return <NotFree data={f} />;
      },
    },
    {
      title: '周三',
      dataIndex: 'wednesday',
      key: 'wednesday',
      render: (f) => {
        if (f.free === 1) return <Free data={f} />;
        else return <NotFree data={f} />;
      },
    },
    {
      title: '周四',
      dataIndex: 'thursday',
      key: 'thursday',
      render: (f) => {
        if (f.free === 1) return <Free data={f} />;
        else return <NotFree data={f} />;
      },
    },
    {
      title: '周五',
      dataIndex: 'friday',
      key: 'friday',
      render: (f) => {
        if (f.free === 1) return <Free data={f} />;
        else return <NotFree data={f} />;
      },
    },
  ];

  useEffect(() => {
    fetchData();
  }, [week]);

  const changeWeek = () => {
    setWeek(week === 1 ? 0 : 1);
  };

  const getRowClassName = (record: any, index: number) => {
    // 通过index判断属于哪个时间段
    if (index >= 0 && index <= 2) {
      return 'time-morning'; // 9:00-12:00
    } else if (index >= 3 && index <= 6) {
      return 'time-afternoon'; // 14:00-18:00
    } else if (index >= 7 && index <= 8) {
      return 'time-evening'; // 19:00-21:00
    } else {
      return '';
    }
  };

  const formatHour = (hour: number) => {
    // 如果小时数小于10，则在前面添加'0'来格式化为两位数
    return hour < 10 ? `0${hour}` : hour.toString();
  };

  const dataSource = freeTime.map((row, index) => {
    let startHour = index + 9;
    let endHour = index + 10;
    if (index >= 3) {
      startHour += 2; // 从第四个时间段开始，加11
      endHour += 2; // 结束时间也相应调整
    }
    if (index >= 7) {
      startHour += 1; // 从第八个时间段开始，再加1
      endHour += 1; // 结束时间也相应调整
    }

    return {
      key: index,
      period: `${formatHour(startHour)}:00-${formatHour(endHour)}:00`,
      // monday: row[0] === 1,
      monday: {
        free: row[0],
        week: week,
        day: 1,
        period: index + 1,
      },
      tuesday: {
        free: row[1],
        week: week,
        day: 2,
        period: index + 1,
      },
      wednesday: {
        free: row[2],
        week: week,
        day: 3,
        period: index + 1,
      },
      thursday: {
        free: row[3],
        week: week,
        day: 4,
        period: index + 1,
      },
      friday: {
        free: row[4],
        week: week,
        day: 5,
        period: index + 1,
      },
    };
  });

  return (
    <div>
      <div style={{ marginBottom: 16 }}>
        <Button type={week === 1 ? 'primary' : 'default'} onClick={() => changeWeek()}>
          {week === 1 ? '点击切换为下周' : '点击切换回本周'}
        </Button>
      </div>
      <Table columns={columns} dataSource={dataSource} rowClassName={getRowClassName} />
    </div>
  );
};

export default App;
