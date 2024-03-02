import React, { useState } from 'react';
import Footer from '@/components/Footer';
import RightContent from '@/components/RightContent';
import AvatarDropdown from '@/components/RightContent/AvatarDropdown';
import { PageLoading } from '@ant-design/pro-components';
import type { RunTimeLayoutConfig } from 'umi';
import { history, Link, useModel } from 'umi';
import { login } from '@/services/ant-design-pro/myAPI';
import defaultSettings from '../config/defaultSettings';
import type { RequestConfig } from 'umi';
import { message } from 'antd';
import { Spin } from 'antd';

// 请求拦截器
const requestInterceptor = (url: string, options: any) => {
  // 显示加载效果
  message.loading({ content: '加载中...', key: 'loading' });

  // 添加请求头
  const token = localStorage.getItem('token');
  options.headers = {
    ...options.headers,
    token: token,
  };

  return {
    url: 'http://localhost:8080' + url,
    options,
  };
};

// 响应拦截器
const responseInterceptor = async (response: Response, options: any) => {
  // 隐藏加载效果
  message.destroy('loading');
  return response;
};

// request的配置
export const request: RequestConfig = {
  timeout: 1000,
  errorConfig: {},
  middlewares: [],
  requestInterceptors: [requestInterceptor],
  responseInterceptors: [responseInterceptor],
};

const isDev = process.env.NODE_ENV === 'development';
const loginPath = '/user/login';

/** 获取用户信息比较慢的时候会展示一个 loading */
export const initialStateConfig = {
  loading: <PageLoading />,
};

export async function getInitialState() {
  const data = {
    token: '',
    User_id: -1,
    level: '',
    username: '',
    loading: false,
    hasLogin: false,
    settings: defaultSettings,
  };
  return data;
}

// ProLayout 支持的api https://procomponents.ant.design/components/layout
export const layout: RunTimeLayoutConfig = ({ initialState, setInitialState }) => {
  return {
    rightContentRender: () => <RightContent />,
    disableContentMargin: false,
    waterMarkProps: {
      content: initialState?.username,
    },
    avatarProps: {
      // src: initialState?.currentUser?.avatar,
      // title: <AvatarName />,
      title: '登出',
      render: (_: any, avatarChildren: any) => {
        return <AvatarDropdown>{avatarChildren}</AvatarDropdown>;
      },
    },
    footerRender: () => <Footer />,
    onPageChange: () => {
      const { location } = history;
      // 如果没有登录，重定向到 login
      // if (initialState?.token === '' && location.pathname !== loginPath) {
      //   history.push(loginPath);
      // }

      // 持久化登录
      const token = localStorage.getItem('token');
      // const userID = parseInt(localStorage.getItem('userID') || '0', 10);
      const loginParams = localStorage.getItem('loginParams');
      const parsedLoginParams = loginParams ? JSON.parse(loginParams) : null;
      if (token && location.pathname !== loginPath && initialState?.hasLogin === false) {
        login(parsedLoginParams).then((res: any) => {
          // console.log(res);
          if (res.code == 1) {
            // console.log('自动登录成功');
            message.success('自动登录成功！');
            setInitialState((s: any) => ({
              ...s,
              ...res.data,
              User_id: res.data.id,
              hasLogin: true,
            }));
          } else {
            // console.log('自动登录失败');
            message.error('自动登录失败！');
          }
        });
      } else if (token && location.pathname !== loginPath && initialState?.hasLogin === true) {
        // console.log('已经登录');
      } else {
        // 如果没有本地缓存登录，重定向到 login
        history.push(loginPath);
      }
      // console.log(initialState);
    },
    menuHeaderRender: undefined,
    childrenRender: (children: any) => {
      if (initialState?.loading) return <PageLoading />;
      return (
        <Spin spinning={initialState!.loading} wrapperClassName="full-screen-spin">
          {children}
        </Spin>
      );
    },
  };
};
