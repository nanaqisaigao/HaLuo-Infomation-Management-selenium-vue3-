import { request } from 'umi';

// 获取个人信息
export const getStudentList = async (name: string, pageSize: number, pageNum: number) => {
  return request('/teacher/stu/search', {
    method: 'GET',
    params: {
      name,
      pageSize,
      pageNum,
    },
  });
};

//导出个人信息
export const getStudentexp = async () => {
  console.log('导出');
  return request('/teacher/stu/exp', {
    method: 'GET',
  });
};

//周报列表查询
export const getWeekList = async (
  name: string,
  startTime: string,
  endTime: string,
  pageNum: number,
  pageSize: number,
) => {
  return request('/teacher/tnotices', {
    method: 'GET',
    params: {
      name,
      startTime,
      endTime,
      pageNum,
      pageSize,
    },
  });
};

// //老师根据id查询周报
// export const getStudent = async (id: number) => {
//   return request('/teacher/tnotices/one', {
//     method: 'GET',
//     params: {
//       id,
//     },
//   });
// };

//打卡记录查寻
export const getClockList = async (name: string, pageSize: number, pageNum: number) => {
  return request('/teacher/search', {
    method: 'GET',
    params: {
      name,
      pageSize,
      pageNum,
    },
  });
};

//空闲时间
export const getFreetimeList = async (index: number) => {
  console.log(index);
  return request('/teacher/freetimes', {
    method: 'GET',
    params: {
      index,
    },
  });
};
