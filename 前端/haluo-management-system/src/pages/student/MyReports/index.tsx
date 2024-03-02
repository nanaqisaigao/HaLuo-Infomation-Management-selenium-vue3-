import React, { useEffect, useState } from 'react';
import { Space, Table, Tag, message } from 'antd';
import type { TableProps } from 'antd';
import { getWeeklyList, changeWeekly } from '@/services/ant-design-pro/myAPI';
import { useModel } from 'umi';
import MyDrawer from './components/Drawer/index';

// 每行数据对象
export default () => {
  interface DataType {
    key: string;
    date: string;
    thisWeekWork: string;
    nextWeekWork: string;
    finished: boolean;
    details: {
      id: number;
      thisWeekWork: string;
      thisWeekIdea: string;
      nextWeekWork: string;
      finished: boolean;
    };
  }

  const { initialState } = useModel('@@initialState');
  const [pageNum, setPageNum] = useState(1);
  const [pageSize, setPageSize] = useState(5);
  const [total, setTotal] = useState(5);

  // 请求原始数据
  const [originList, setOriginList] = useState([]);
  const fetchList = async () => {
    getWeeklyList({ userId: initialState!.User_id, pageNum: pageNum, pageSize: pageSize }).then(
      (res) => {
        // console.log(res);
        if (res.code == 1) {
          message.success('获取周报列表成功');
          // console.log(res);
          setOriginList(res.data.list);
          setTotal(res.data.total);
        } else {
          // console.log('获取周报列表失败');
          message.error('获取周报列表失败');
          return;
        }
      },
    );
  };
  useEffect(() => {
    fetchList();
  }, [pageNum, pageSize]);

  // 将原始数据映射为需要的格式
  const [mapList, setMapList] = useState([{}]);
  useEffect(() => {
    const mappedList = originList.map((row: any) => {
      const formatDate = (timestamp: number) => {
        const date = new Date(timestamp);
        return date.toLocaleDateString('zh-CN', {
          year: 'numeric',
          month: '2-digit',
          day: '2-digit',
        });
      };

      const startFormat = formatDate(row.startTime);
      const endFormat = formatDate(row.endTime);
      const date = `${startFormat}-${endFormat}`;
      return {
        ...row,
        date: date,
        details: {
          id: row.id,
          thisWeekWork: row.thisWeekWork,
          thisWeekIdea: row.thisWeekIdea,
          nextWeekWork: row.nextWeekWork,
          finished: row.finished,
        },
      };
    });
    setMapList(mappedList);
    // console.log(mapList);
  }, [originList]);

  // 表单的状态参数
  const [drawerDetails, setDrawerDetails] = useState({
    id: '',
    thisWeekWork: '',
    thisWeekIdea: '',
    nextWeekWork: '',
    finished: '',
  });

  // 使用表单状态，修改周报
  const useChangeWeekly = async () => {
    if (
      drawerDetails.thisWeekWork == '' ||
      drawerDetails.nextWeekWork == '' ||
      drawerDetails.thisWeekIdea == ''
    ) {
      message.error('请填写完整！');
      return;
    }
    changeWeekly({ ...drawerDetails }).then((res) => {
      // console.log(drawerDetails);

      if (res.code == 1) {
        message.success('修改成功！');
        fetchList();
      } else {
        message.error('修改失败！');
      }
      // console.log(res);
    });
  };

  // Drawer的控制
  const [drawerVisible, setDrawerVisible] = useState(false);
  const showDrawer = () => {
    setDrawerVisible(true);
  };
  const closeDrawer = () => {
    setDrawerVisible(false);
    setDrawerDetails({});
  };
  // 对其中的表单是否禁用
  const [disable, setDisable] = useState(true);

  // 表格每列内容
  const columns: TableProps<DataType>['columns'] = [
    {
      title: '日期范围',
      dataIndex: 'date',
      key: 'date',
      render: (text) => <a>{text}</a>,
    },
    {
      title: '本周完成任务',
      dataIndex: 'thisWeekWork',
      key: 'thisWeekWork',
    },
    {
      title: '下周计划',
      dataIndex: 'nextWeekWork',
      key: 'nextWeekWork',
    },
    {
      title: '是否延迟',
      key: 'finished',
      dataIndex: 'finished',
      render: (hsd) => {
        const color = hsd ? 'green' : 'volcano';
        const text = !hsd ? '是' : '否';
        return <Tag color={color}>{text}</Tag>;
      },
    },
    {
      title: '操作',
      dataIndex: 'details',
      key: 'details',
      render: (details) => (
        <Space size="middle">
          {/* <a>Invite {record.date}</a> */}
          <a
            // 点击则将文章details传入Drawer,并且禁止编辑
            onClick={() => {
              setDrawerDetails(details);
              setDisable(true);
              showDrawer();
              // console.log(details);
            }}
          >
            查看
          </a>
          <a
            // 点击则将文章details传入Drawer,允许编辑
            onClick={() => {
              setDrawerDetails(details);
              setDisable(false);
              showDrawer();
              // console.log(details);
            }}
          >
            修改
          </a>
        </Space>
      ),
    },
  ];

  return (
    <>
      <Table
        columns={columns as any}
        dataSource={mapList}
        pagination={{
          pageSizeOptions: ['5', '10', '20', '30', '50'],
          showSizeChanger: true,
          defaultPageSize: 5,
          total: total,
          onChange: (page, pagesize) => {
            setPageNum(page);
            setPageSize(pagesize);
          },
        }}
      />
      <MyDrawer
        details={drawerDetails}
        setDetails={setDrawerDetails}
        open={drawerVisible}
        onClose={closeDrawer}
        disable={disable}
        submitChanges={useChangeWeekly}
      />
    </>
  );
};
