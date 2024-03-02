import { Input, Button, Space, Table, Tag, Pagination } from 'antd';
import type { PaginationProps, TableColumnsType } from 'antd';
import React from 'react';
import './index.less';
import { useState, useEffect } from 'react';
import { getClockList } from '@/services/ant-design-pro/manager';

const TableList: React.FC = () => {
  interface DataType {
    id: number;
    userId: number;
    validTime: number;
    checkDate: number;
    checkTime: string;
    userName: string;
  }
  const columns: TableColumnsType<DataType> = [
    {
      title: '学生姓名',
      dataIndex: 'userName',
      key: 'userName',
      render: (text: string) => <a>{text}</a>,
    },
    {
      title: '打卡日期',
      dataIndex: 'checkDate',
      key: 'checkDate',
      defaultSortOrder: 'descend',
      sorter: (a: DataType, b: DataType) => a.checkDate - b.checkDate,
    },
    {
      title: '打卡时段',
      dataIndex: 'checkTime',
      key: 'checkTime',
    },
    {
      title: '有效工时',
      dataIndex: 'validTime',
      key: 'validTime',
      defaultSortOrder: 'descend',
      sorter: (a: DataType, b: DataType) => a.validTime - b.validTime,
    },
  ];
  const [formData, setFormData] = useState<DataType[]>([]);
  const [total, setTotal] = useState<number>(150); //数据总数
  const [currentPage, setCurrentPage] = useState(1); //当前页数
  const [pageSize, setPageSize] = useState(5); //每页几个项目
  const [name, setName] = useState<string>(''); //搜索名字
  //分页搜索
  const onChange: PaginationProps['onChange'] = (pagenum: number, pagesize: number) => {
    console.log('每页项目个数', pageSize);
    setCurrentPage(pagenum);
    setPageSize(pagesize);
  };
  //名字搜索
  const searchName: React.ChangeEventHandler<HTMLInputElement> = (event) => {
    const res: string = event.target.value;
    setName(res);
    console.log('name:', res);
  };
  //重置按钮
  const reset = () => {
    setName('');
    console.log(name);
  };

  //调用api获取数据
  const fetchClockList = async (Name: string, pagesize: number, pagenum: number) => {
    try {
      console.log('调用接口');
      const response = await getClockList(Name, pagesize, pagenum);
      // 处理接口返回的数据，并设置到状态中
      setFormData(response.data.list);
      setTotal(response.data.total);
    } catch (error) {
      // 处理错误
      console.error('Error fetching student list:', error);
    }
  };
  useEffect(() => {
    fetchClockList(name, pageSize, currentPage);
  }, [currentPage, name, pageSize]);
  return (
    <div>
      <div className="top">
        <div className="input">
          <div className="title">姓名:</div>
          <Input placeholder="输入姓名模糊搜索" onChange={searchName} value={name} />
        </div>
        <Button type="primary" className="primaryButton">
          查询
        </Button>
        <Button className="resetting" onClick={reset}>
          重置
        </Button>
      </div>
      <div className="table">
        <Table dataSource={formData} pagination={false} columns={columns} />
        <div className="bottom">
          <div className="word">
            共{total}条记录,第{currentPage}/{Math.ceil(total / pageSize)}页
          </div>
          <Pagination
            showSizeChanger
            showQuickJumper
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
    </div>
  );
};

export default TableList;
