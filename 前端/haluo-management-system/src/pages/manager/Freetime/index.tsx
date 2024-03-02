import React, { useEffect, useState } from 'react';
import type { RadioChangeEvent } from 'antd';
import { Radio } from 'antd';
import { getFreetimeList } from '@/services/ant-design-pro/manager';
import './index.less';
const Freetime: React.FC = () => {
  interface DataType {
    id: number;
    time: string;
    mon: number;
    tues: number;
    wednes: number;
    thurs: number;
    fri: number;
  }
  const [data, setData] = useState<DataType[]>([]); //定义数据
  const [ID, setID] = useState<number>(1);

  //调用接口获取数据源
  const fetchFreetimeList = async (Id: number) => {
    try {
      console.log('调用接口', Id);
      const response = await getFreetimeList(Id);
      console.log(response);
      setData(response.data.freeTime);
      console.log('data:');
      console.log(data);
    } catch (error) {
      // 处理错误
      console.error('Error fetching student list:', error);
    }
  };
  useEffect(() => {
    fetchFreetimeList(ID);
  }, [ID]);

  const getNumber = (number: number) => {
    if (number >= 8) {
      return { background: 'aquamarine' };
    } else if (number > 5 && number < 8) {
      return { background: 'yellow' };
    } else {
      return { background: 'gainsboro' };
    }
  };
  const getID = (id: number) => {
    if (id <= 3) {
      return { background: 'gray', color: 'greenyellow' };
    } else if (id > 3 && id < 8) {
      return { background: 'gray', color: 'skyblue' };
    } else {
      return { background: 'gray', color: 'black' };
    }
  };
  //单选框
  const onChange = (e: RadioChangeEvent) => {
    console.log('radio checked', e.target.value);
    setID(e.target.value);
  };
  return (
    <div className="table-container">
      <div className="table">
        <div className="thead">
          <div className="cell">
            <Radio.Group onChange={onChange} value={ID}>
              <Radio value={1}>本周</Radio>
              <Radio value={0}>下周</Radio>
            </Radio.Group>
          </div>
          <div className="cell">周一</div>
          <div className="cell">周二</div>
          <div className="cell">周三</div>
          <div className="cell">周四</div>
          <div className="cell">周五</div>
        </div>
        <div className="tbody">
          {data.map((v) => (
            <div className="row" key={v.id} style={getID(v.id)}>
              <div className="cell" style={getID(v.id)}>
                {v.time}
              </div>
              <div className="cell" style={getNumber(v.mon)}>
                {v.mon}
              </div>
              <div className="cell" style={getNumber(v.tues)}>
                {v.tues}
              </div>
              <div className="cell" style={getNumber(v.wednes)}>
                {v.wednes}
              </div>
              <div className="cell" style={getNumber(v.thurs)}>
                {v.thurs}
              </div>
              <div className="cell" style={getNumber(v.fri)}>
                {v.fri}
              </div>
            </div>
          ))}
        </div>
      </div>
      <div className="bottombox">
        <div style={{ background: 'gainsboro', width: 100, height: 70 }}>小于50%</div>
        <div style={{ background: 'yellow', width: 100, height: 70 }}>50%~80%</div>
        <div style={{ background: 'aquamarine', width: 100, height: 70 }}>大于80%</div>
      </div>
    </div>
  );
};

export default Freetime;
