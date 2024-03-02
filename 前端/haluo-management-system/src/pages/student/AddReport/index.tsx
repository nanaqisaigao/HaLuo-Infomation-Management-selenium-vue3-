import React from 'react';
import { useState } from 'react';
import './index.less';
import { addWeekly } from '@/services/ant-design-pro/myAPI';
import { useModel } from 'umi';
import { DatePicker, Space, message } from 'antd';

const TaskForm = () => {
  const [completedTasks, setCompletedTasks] = useState('');
  const [thoughts, setThoughts] = useState('');
  const [plannedTasks, setPlannedTasks] = useState('');
  const [hasDelay, setHasDelay] = useState(false);
  const { initialState } = useModel('@@initialState');
  const { RangePicker } = DatePicker;

  const handleSubmit = (event: any) => {
    event.preventDefault();
    console.log({
      completedTasks,
      plannedTasks,
      hasDelay,
    });
  };

  const [dates, setDates] = useState(null);
  const resetRangePicker = () => {
    setDates(null);
  };

  const [startTime, setStartTime] = useState('');
  const [endTime, setEndTime] = useState('');

  const handlChange = (dayjs: any, daystr: any) => {
    setStartTime(daystr[0]);
    setEndTime(daystr[1]);
  };

  const handleClick = async () => {
    if (startTime === '') {
      message.error('请选择时间范围！');
      return;
    }
    addWeekly({
      userId: initialState!.User_id,
      startTime: startTime,
      endTime: endTime,
      thisWeekWork: completedTasks,
      thisWeekIdea: thoughts,
      nextWeekWork: plannedTasks,
      finished: hasDelay,
    })
      .then((res) => {
        // console.log(initialState!.User_id);
        if (res.code == 1) {
          message.success('提交成功！');
          console.log(res);
          resetRangePicker();
          setCompletedTasks('');
          setPlannedTasks('');
          setThoughts('');
          setHasDelay(false);
          setStartTime('');
          setEndTime('');
        } else {
          message.error('提交失败！');
        }
      })
      .catch((err) => {
        console.log(err);
        message.error('提交失败！');
      });
  };

  return (
    <div className="task-form-container">
      <form className="task-form" onSubmit={handleSubmit}>
        <div className="form-group">
          <label>选择时间范围：</label>
          <RangePicker
            value={dates}
            onChange={(dayjs, daystr) => {
              handlChange(dayjs, daystr);
              setDates(dayjs);
            }}
          />
        </div>
        <div className="form-group">
          <label htmlFor="completedTasks">本周完成任务：</label>
          <textarea
            id="completedTasks"
            value={completedTasks}
            onChange={(e) => setCompletedTasks(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="thoughts">本周感悟：</label>
          <textarea
            id="thoughts"
            value={thoughts}
            onChange={(e) => setThoughts(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="plannedTasks">下周计划任务：</label>
          <textarea
            id="plannedTasks"
            value={plannedTasks}
            onChange={(e) => setPlannedTasks(e.target.value)}
            required
          />
        </div>
        <div className="form-group-checkbox">
          <label htmlFor="hasDelay">是否有延迟 </label>
          <input
            type="checkbox"
            className="checkbox"
            id="hasDelay"
            checked={hasDelay}
            onChange={(e) => setHasDelay(e.target.checked)}
          />
        </div>
        <button type="submit" onClick={() => handleClick()}>
          提交
        </button>
      </form>
    </div>
  );
};

export default TaskForm;
