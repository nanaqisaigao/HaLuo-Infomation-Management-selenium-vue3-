import { Input, Button, Space, Table, Tag, Pagination } from 'antd';
import type { PaginationProps } from 'antd';
import React from 'react';
import { useEffect } from 'react';
import './index.less';
import { useState } from 'react';
import { request } from 'umi';
import Search from 'antd/lib/transfer/search';
import { getStudentList, getStudentexp } from '@/services/ant-design-pro/manager';

const StudentInfo: React.FC = () => {
  const { Column } = Table;
  interface DataType {
    id: number;
    username: string;
    userId?: number;
    collegeId?: number;
    majorId?: number;
    grade: number;
    education: string;
    phone: string;
    collegeName: string;
    majorName: string;
  }
  const [formData, setFormData] = useState<DataType[]>([]);
  const [total, setTotal] = useState<number>(150);
  const [pageNum, setPageNum] = useState<number>(1); //当前页数
  const [name, setName] = useState<string>('');
  const [pageSize, setPageSize] = useState<number>(5); //每页几个项目
  //分页搜索
  const onChange: PaginationProps['onChange'] = (pagenum: number, pagesize: number) => {
    setPageNum(pagenum);
    setPageSize(pagesize);
  };
  //姓名搜索
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

  //mockjs模拟数据
  const fetchStudentList = async (Name: string, pagesize: number, pagenum: number) => {
    try {
      console.log('调用接口');
      const response = await getStudentList(Name, pagesize, pagenum);
      console.log('数据：');
      console.log(response);
      // 处理接口返回的数据，并设置到状态中
      setFormData(response.data.list);
      setTotal(response.data.total);
    } catch (error) {
      // 处理错误
      console.error('Error fetching student list:', error);
    }
  };
  useEffect(() => {
    fetchStudentList(name, pageSize, pageNum);
  }, [pageNum, name, pageSize]);
  //导出数据
  const handleClick = async () => {
    try {
      const response = await getStudentexp();
      console.log('返回成功');
      const token = localStorage.getItem('token');
      const exportUrl = `http://localhost:8080/teacher/stu/exppart?token=${token}`;
      window.open(exportUrl, '_blank');
    } catch (error) {
      // 处理其他异常情况
      console.error('发生错误', error);
    }
  };
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
        <Button type="primary" className="daoButton" onClick={handleClick}>
          导出
        </Button>
      </div>
      <div className="table">
        <Table dataSource={formData} pagination={false}>
          <Column title="姓名" dataIndex="username" key="username" />
          <Column title="年级" dataIndex="grade" key="grade" />
          <Column title="手机号" dataIndex="phone" key="phone" />
          <Column title="学历" dataIndex="education" key="education" />
          <Column title="学院" dataIndex="collegeName" key="collegeName" />
          <Column title="专业" dataIndex="majorName" key="majorName" />
        </Table>
        <div className="bottom">
          <div className="word">
            共{total}条记录,第{pageNum}/{Math.ceil(total / pageSize)}页
          </div>
          <Pagination
            showQuickJumper
            showSizeChanger
            current={pageNum} //当前页数
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

export default StudentInfo;
