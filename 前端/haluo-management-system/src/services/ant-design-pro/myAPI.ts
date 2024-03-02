import { request } from 'umi';

// 注册
export interface RegisterParams {
  username: string;
  password: string;
  phone: string;
  collegeName?: string;
  majorName?: string;
  grade?: string;
  education?: string;
}
export async function register(params: RegisterParams) {
  return request('/user/register', {
    method: 'POST',
    data: params,
  });
}

// 登录
export async function login(params: API.LoginParams) {
  return request('/user/login', {
    method: 'POST',
    data: params,
  });
}

// 提交打卡时间
export interface clockInFormat {
  userId: number; // 用户id
  // star_time: string; // 打卡日期
  // end_time: string; // 工作时间
  checkDate: string;
  checkTime: string;
  validTime: number; // 有效工作时间（以小时为单位）
}
export async function clockIn(params: clockInFormat) {
  return request('/student/sign', {
    method: 'POST',
    data: params,
  });
}

// 获取打卡记录
export interface clockInRecordFormat {
  userId: number;
  pageSize: number;
  pageNum: number;
}
export async function getClockInRecord(params: clockInRecordFormat) {
  return request('/student/search/', { method: 'GET', params });
}

// 空闲时间
export interface freeTimeFormat {
  userId: number;
  index: number;
}
export async function getFreeTime(params: freeTimeFormat) {
  return request('/student/freetime/', { method: 'GET', params });
}

// 修改空闲时间
export async function changeFreeTime(params: any) {
  return request('/student/changefreetime/', {
    method: 'POST',
    data: params,
  });
}

// 添加周报
export interface weeklyFormat {
  userId: number;
  // studentId: number;
  startTime: string;
  endTime: string;
  thisWeekWork: string;
  finished: boolean;
  thisWeekIdea: string;
  nextWeekWork: string;
}
export async function addWeekly(params: weeklyFormat) {
  return request('/student/addnotice', {
    method: 'POST',
    data: params,
  });
}

// 获取周报列表
export interface weeklyListFormat {
  startTime?: string;
  endTime?: string;
  userId: number;
  pageSize: number;
  pageNum: number;
}
export async function getWeeklyList(params: weeklyListFormat) {
  return request('/student/tnotices', { method: 'GET', params });
}

// 修改周报
export async function changeWeekly(params: any) {
  return request('/student/changenotice', {
    method: 'PUT',
    data: params,
  });
}

// 根据id获取周报详情
// export async function getWeeklyDetail(id: number) {
//   return request('/student/noticeDetail/' + id, { method: 'GET' });
// }

// 获取个人信息
export async function getStudentInfo(user_id: number) {
  return request('/student/getmes/' + user_id, { method: 'GET' });
}

// 修改个人信息
export interface studentInfo {
  user_id: number;
  phone: string;
  username: string;
  collegeName: string;
  majorName: string;
  grade: string;
  education: string;
}
export async function changeStudentInfo(params: studentInfo) {
  return request('/student/changemes', {
    method: 'PUT',
    data: params,
  });
}

//获取学院信息
export async function getCollege() {
  return request('/student/getcollege', { method: 'GET' });
}

// 根据学院获取专业信息
export async function getMajor(name: string) {
  return request('/student/getmajor/' + name, { method: 'GET' });
}

// 修改密码
export interface changePswFormat {
  userId: number;
  password: string;
  historicalPassword: string;
}
export async function changePsw(params: changePswFormat) {
  return request('/student/changepsw', {
    method: 'PUT',
    data: params,
  });
}
