import { Input, Button, DatePicker, Table, Pagination, Tag, Space, Drawer } from 'antd';
import type { PaginationProps, TableProps } from 'antd';
import React from 'react';
import './index.less';
import { useState, useEffect } from 'react';
import { getWeekList } from '@/services/ant-design-pro/manager';
import type { RangePickerValue } from 'antd/lib/date-picker/interface';

const TableList: React.FC = () => {
  const { Column } = Table;
  interface StudentType {
    id?: number;
    userName?: string;
    thisWeekWork?: string;
    nextWeekWork?: string;
    finished?: boolean;
    thisWeekIdea?: string;
    nextWeekIdea?: string;
    createTime?: string;
    updateTime?: string;
    deleted?: 0;
  }
  interface DataType {
    id: number;
    username?: string;
    startTime?: string;
    endTime?: string;
    thisWeekWork?: string;
    nextWeekWork?: string;
    finished?: boolean;
    createTime?: string;
    updateTime?: string;
    deleted?: number;
  }
  const [student, setStudent] = useState<StudentType>({});
  const [formData, setFormData] = useState<DataType[]>([]);
  const [timeRange, setTimeRange] = useState<RangePickerValue | undefined>(undefined); //日期选择器的所有时间
  const [total, setTotal] = useState<number>(150); //数据总数
  const [currentPage, setCurrentPage] = useState(1); //当前页数
  const [pageSize, setPageSize] = useState(5); //每页几个项目
  const [name, setName] = useState<string>(''); //搜索名字
  const [starttime, setStartTime] = useState<string>(''); //开始时间
  const [endtime, setEndTime] = useState<string>(''); //结束时间
  const [open, setOpen] = useState(false); //控制弹窗
  const { RangePicker } = DatePicker;
  //分页搜索
  const onChange: PaginationProps['onChange'] = (pagenum: number, pagesize: number) => {
    console.log('每页个数', pageSize);
    setCurrentPage(pagenum);
    setPageSize(pagesize);
  };
  //名字搜索
  const searchName: React.ChangeEventHandler<HTMLInputElement> = (event) => {
    const res: string = event.target.value;
    setName(res);
    console.log('name:', res);
  };
  //时间搜索
  const searchTime = (dates: RangePickerValue, dateStrings: [string, string]) => {
    const [startTime, endTime] = dateStrings;
    setStartTime(startTime);
    setEndTime(endTime);
    setTimeRange(dates);
    console.log('选择的日期范围：', startTime, '至', endTime);
  };

  //mockjs模拟数据
  const fetchWeekList = async (
    Name: string,
    start: string,
    end: string,
    page: number,
    pagesize: number,
  ) => {
    try {
      console.log('调用接口');
      console.log('name', Name, 'start', start, 'end', end, 'page', page, 'pagesize', pagesize);
      const response = await getWeekList(Name, start, end, page, pagesize);
      console.log(response);
      // 处理接口返回的数据，并设置到状态中
      setFormData(response.data.list);
      setTotal(response.data.total);
      console.log('form', formData);
    } catch (error) {
      // 处理错误
      console.error('Error fetching student list:', error);
    }
  };
  useEffect(() => {
    fetchWeekList(name, starttime, endtime, currentPage, pageSize);
  }, [name, pageSize, starttime, endtime, currentPage]);

  //重置按钮
  const reset = () => {
    setName('');
    setTimeRange(undefined);
    setEndTime('');
    setStartTime('');
    fetchWeekList(name, starttime, endtime, currentPage, pageSize);
  };
  //查看学生详细信息
  const fetchStudent = async (
    Name: string,
    start: string,
    end: string,
    page: number,
    pagesize: number,
  ) => {
    try {
      console.log('调用接口');
      console.log('name', Name, 'start', start, 'end', end, 'page', page, 'pagesize', pagesize);
      const response = await getWeekList(Name, start, end, page, pagesize);
      console.log(response);
      setStudent(response.data.list[0]);
      console.log('form', formData);
    } catch (error) {
      // 处理错误
      console.error('Error fetching student list:', error);
    }
  };
  const look = (Name: string, start: string, end: string, page: number, pagesize: number) => {
    setOpen(true);
    fetchStudent(name, starttime, endtime, currentPage, pageSize);
  };
  //关闭弹窗
  const onClose = () => {
    setOpen(false);
    setName('');
    setTimeRange(undefined);
    setEndTime('');
    setStartTime('');
    fetchWeekList(name, starttime, endtime, currentPage, pageSize);
  };

  const columns: TableProps<DataType>['columns'] = [
    {
      title: '学生姓名',
      dataIndex: 'userName',
      key: 'userName',
      render: (text: string) => <a>{text}</a>,
    },
    {
      title: '开始时间',
      dataIndex: 'startTime',
      key: 'startTime',
    },
    {
      title: '结束时间',
      dataIndex: 'endTime',
      key: 'endTime',
    },
    {
      title: '本周完成任务',
      dataIndex: 'thisWeekWork',
      key: 'thisWeekWork',
    },
    {
      title: '下周计划内容',
      dataIndex: 'nextWeekWork',
      key: 'nextWeekWork',
    },

    {
      title: '是否有推迟',
      key: 'finished',
      dataIndex: 'finished',
      render: (_, record: { finished: string }) => (
        <>
          <Tag color={record.finished === 'true' ? 'geekblue' : 'green'}>
            {String(record.finished).toUpperCase()}
          </Tag>
        </>
      ),
    },
    {
      title: '操作',
      key: 'action',
      render: (_, record: { userName: string; startTime: string; endTime: string }) => (
        <Space size="middle">
          <a
            onClick={() =>
              look(record.userName, record.startTime, record.endTime, currentPage, pageSize)
            }
          >
            查看
          </a>
        </Space>
      ),
    },
  ];
  return (
    <div>
      <div className="top">
        <div className="input">
          <div className="title1">姓名:</div>
          <Input placeholder="请输入姓名" onChange={searchName} value={name} />
        </div>

        <div className="picker">
          <div className="title2">日期范围:</div>
          <RangePicker onChange={searchTime} value={timeRange} />
        </div>
        <Button type="primary" className="primaryButton">
          查询
        </Button>
        <Button onClick={reset}>重置</Button>
      </div>
      <div className="table">
        <Table dataSource={formData} pagination={false} columns={columns} />
        <div className="bottom">
          <div className="word">
            共{total}条记录,第{currentPage}/{Math.ceil(total / pageSize)}页
          </div>
          <Pagination
            showQuickJumper={true}
            showSizeChanger
            current={currentPage} //当前页数
            defaultCurrent={1}
            total={total}
            onChange={onChange}
            pageSize={pageSize}
            pageSizeOptions={[5, 10, 15]} //指定每页可以显示多少条
            className="pagination"
          />
        </div>
      </div>

      {/* 抽屉查看学生信息 */}
      <Drawer title="Basic Drawer" onClose={onClose} open={open} placement="left">
        <p>姓名：{student.userName}</p>
        <p>本周完成任务：{student.thisWeekWork}</p>
        <p>本周任务总结：{student.thisWeekIdea}</p>
        <p>下周任务：{student.nextWeekWork}</p>
      </Drawer>
    </div>
  );
};

export default TableList;
