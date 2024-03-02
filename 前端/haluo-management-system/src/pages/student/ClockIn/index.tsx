import React, { useState } from 'react';
import { DatePicker, Button, message, Spin } from 'antd';
import './index.less';
import { clockIn } from '@/services/ant-design-pro/myAPI';
import { useModel } from 'umi';

export default () => {
  const { RangePicker } = DatePicker;
  const { initialState } = useModel('@@initialState');

  const [valid_time, setValid_time] = useState(0);
  const [start_time, setStart_time] = useState('0');
  const [end_time, setEnd_time] = useState('0');

  const [checkDate, setCheckDate] = useState('0');
  const [checkTime, setCheckTime] = useState('0');

  const handle = (date: any, dateStr: string[]) => {
    console.log(date, dateStr);
    console.log(dateStr[0], dateStr[1]);

    // setStart_time(dateStr[0]);
    // setEnd_time(dateStr[1]);
    // console.log(start_time, end_time);

    const dateRegex = /(\d{4}-\d{2}-\d{2})/;
    const timeRegex = /(\d{2}:\d{2}):\d{2}/;
    const dateMatch = dateStr[0].match(dateRegex);
    const startMatch = dateStr[0].match(timeRegex);
    const endMatch = dateStr[1].match(timeRegex);
    if (dateMatch) {
      setCheckDate(dateMatch[1]);
    }
    if (startMatch && endMatch) {
      setCheckTime(`${startMatch[1]}-${endMatch[1]}`);
    }

    const startDate = new Date(dateStr[0]);
    const endDate = new Date(dateStr[1]);
    // 计算相差的总毫秒数
    const differenceInMilliseconds = endDate.getTime() - startDate.getTime();
    // 将毫秒数转换为小时数
    const differenceInHours = differenceInMilliseconds / (1000 * 60 * 60);
    // 保留一位小数
    setValid_time(parseFloat(differenceInHours.toFixed(1)));
  };

  // 创建一个状态来存储RangePicker的值
  const [dates, setDates] = useState(null);

  // 定义一个函数来重置RangePicker
  const resetRangePicker = () => {
    setDates(null);
  };

  // ! 重复传递会失败
  const submit = async () => {
    if (valid_time !== 0) {
      clockIn({
        userId: initialState!.User_id!,
        checkDate: checkDate,
        checkTime: checkTime,
        //start_time:start_time,
        // end_time: end_time,
        validTime: valid_time,
      })
        .then((res) => {
          console.log(res);
          console.log('checkTime:' + checkTime);

          if (res.code == 1) {
            message.success(`打卡成功！\n增加有效时长${valid_time}h`);
            // 重置
            setValid_time(0);
            resetRangePicker();
          } else {
            message.error('打卡失败');
          }
        })
        .catch((err) => {
          console.log(err);
          message.error('打卡失败');
        });
    } else {
      message.error('有效时长为0');
    }
  };

  return (
    <div className="top">
      <div className="container">
        <div className="timeContainer">
          <div>
            <div className="tip">请选择打卡日期及时段</div>
            <RangePicker
              onChange={(date, dateStr) => {
                handle(date, dateStr);
                setDates(date);
              }}
              showTime
              showSecond={false}
              value={dates}
            />
          </div>
        </div>
        <div className="btnContainer">
          <Button onClick={submit} className="btn" type="primary" block>
            提交打卡
          </Button>
        </div>
      </div>
    </div>
  );
};
