import React, { useEffect, useState } from 'react';
import { Space, Table, Tag, message } from 'antd';
import type { TableProps } from 'antd';
import { getClockInRecord } from '@/services/ant-design-pro/myAPI';
import { useModel } from 'umi';

interface DataType {
  key: string;
  date: string;
  timeRange: string;
  effectiveDuration: number;
  tags: string[];
}

const columns: TableProps<DataType>['columns'] = [
  {
    title: '打卡日期',
    dataIndex: 'checkDate',
    key: 'checkDate',
    render: (text) => <a>{new Date(text).toLocaleDateString()}</a>,
  },
  {
    title: '打卡时段',
    dataIndex: 'checkTime',
    key: 'checkTime',
  },
  {
    title: '时长评判',
    key: 'validTime',
    dataIndex: 'validTime',
    render: (efd) => {
      const color = efd >= 4 ? 'green' : 'volcano';
      const text = efd >= 4 ? '时长达标' : '加加油门';
      return <Tag color={color}>{text}</Tag>;
    },
  },
  {
    title: '有效时长',
    dataIndex: 'validTime',
    key: 'validTime',
    render: (numb) => <p>{numb}h</p>,
  },
];

const App: React.FC = () => {
  const { initialState } = useModel('@@initialState');

  const [records, setRecords] = useState([]);
  const [pageNum, setPageNum] = useState(1);
  const [pageSize, setPageSize] = useState(5);
  const [total, setTotal] = useState(5);

  useEffect(() => {
    getClockInRecord({ userId: initialState!.User_id, pageSize: pageSize, pageNum: pageNum })
      .then((res) => {
        console.log(res);
        if (res.code == 1) {
          message.success('更新打卡记录成功');
          setRecords(res.data.list);
          setTotal(res.data.total);
        } else {
          message.error('获取打卡记录失败');
        }
      })
      .catch((err) => {
        console.log(err);
        message.error('获取打卡记录失败');
      });
  }, [pageNum, pageSize]);

  return (
    <Table
      columns={columns}
      dataSource={records}
      pagination={{
        pageSizeOptions: ['5', '10', '20', '30', '50'],
        showSizeChanger: true,
        defaultPageSize: 5,
        total: total,
        onChange: (page, pagesize) => {
          // console.log(page, pagesize);
          setPageNum(page);
          setPageSize(pagesize);
        },
      }}
    />
  );
};

export default App;
