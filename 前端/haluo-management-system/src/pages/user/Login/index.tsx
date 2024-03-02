import Footer from '@/components/Footer';
import type { RegisterParams } from '@/services/ant-design-pro/myAPI';
import { login, register } from '@/services/ant-design-pro/myAPI';
import { LockOutlined, UserOutlined, FormOutlined } from '@ant-design/icons';
import { LoginForm, ProFormText } from '@ant-design/pro-components';
import { Alert, message, Tabs } from 'antd';
import React, { useState } from 'react';
import { history, useModel } from 'umi';
import styles from './index.less';
import logo from '@/assets/哈啰.svg';
const Login: React.FC = () => {
  const [type, setType] = useState<string>('account');
  const { setInitialState } = useModel('@@initialState');
  const handleSubmit = async (values: RegisterParams) => {
    if (type === 'signUp') {
      register(values)
        .then((res: any) => {
          console.log(res);
          if (res.code == 1) {
            message.success(res.msg || '注册成功！');
            setType('account');
          } else {
            message.error(res.msg || '注册失败！');
          }
        })
        .catch((err) => {
          console.log(err);
          message.error('注册失败！');
        });
    } else {
      login(values)
        .then((res: any) => {
          console.log(res);
          if (res.code == 1) {
            setInitialState((s: any) => ({
              ...s,
              ...res.data,
              // 接口文档修改后，这里的id应该是User_id
              User_id: res.data.id,
              hasLogin: true,
            }));
            // localStorage.setItem('userID', res.data.User_id);
            localStorage.setItem('token', res.data.token);
            localStorage.setItem('loginParams', JSON.stringify(values));
            console.log(res.data);
            message.success(res.msg || '登录成功！');
            history.push('/');
          } else {
            // 信息可以换位返回的信息
            message.error(res.msg || '登录失败！');
          }
        })
        .catch((err) => {
          console.log(err);
          message.error('登录失败！');
        });
    }
  };
  return (
    <div className={styles.container}>
      <div className={styles.content}>
        <LoginForm
          logo={<img alt="logo" src={logo} />}
          title="武科大&哈啰校企合作管理系统"
          subTitle={'寒假新兵营项目'}
          initialValues={{
            autoLogin: true,
          }}
          actions={[]}
          onFinish={async (values) => {
            await handleSubmit(values as RegisterParams);
          }}
        >
          <Tabs
            activeKey={type}
            onChange={setType}
            items={[
              {
                key: 'account',
                label: '账户密码登录',
              },
              {
                key: 'signUp',
                label: '注册学生账户',
              },
            ]}
          >
            <Tabs.TabPane key="account" tab="账户密码登录" />
          </Tabs>

          {type === 'account' && (
            <>
              <ProFormText
                name="phone"
                fieldProps={{
                  size: 'large',
                  prefix: <UserOutlined className={styles.prefixIcon} />,
                }}
                placeholder="请输入账户"
                rules={[
                  {
                    required: true,
                    message: '账户是必填项！',
                  },
                ]}
              />
              <ProFormText.Password
                name="password"
                fieldProps={{
                  size: 'large',
                  prefix: <LockOutlined className={styles.prefixIcon} />,
                }}
                placeholder="请输入密码"
                rules={[
                  {
                    required: true,
                    message: '密码是必填项！',
                  },
                ]}
              />
            </>
          )}
          {type === 'signUp' && (
            <>
              <ProFormText
                name="username"
                fieldProps={{
                  size: 'large',
                  prefix: <FormOutlined className={styles.prefixIcon} />,
                }}
                placeholder="请输入用户名"
                rules={[
                  {
                    required: true,
                    message: '用户名是必填项！',
                  },
                ]}
              />
              <ProFormText
                name="phone"
                fieldProps={{
                  size: 'large',
                  prefix: <UserOutlined className={styles.prefixIcon} />,
                }}
                placeholder="请输入手机号"
                rules={[
                  {
                    required: true,
                    message: '手机号是必填项！',
                  },
                  {
                    pattern: /^1[3-9]\d{9}$/,
                    message: '请输入正确的手机号',
                  },
                ]}
              />
              <ProFormText.Password
                name="password"
                fieldProps={{
                  size: 'large',
                  prefix: <LockOutlined className={styles.prefixIcon} />,
                }}
                placeholder="请输入密码"
                rules={[
                  {
                    required: true,
                    message: '密码是必填项！',
                  },
                  {
                    pattern:
                      /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[%_@])[a-zA-Z0-9%_@]{8,}$|(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$|(?=.*[a-zA-Z])(?=.*[%_@])[a-zA-Z%_@]{8,}$|(?=.*[0-9])(?=.*[%_@])[0-9%_@]{8,}$/,
                    message:
                      '密码只能由英文、数字、特殊字符%_@组成，且至少包含其中两种类型的字符，长度大于等于8',
                  },
                ]}
              />
            </>
          )}

          {/* <div
            style={{
              marginBottom: 24,
            }}
          >
            <ProFormCheckbox noStyle name="autoLogin">
              自动登录
            </ProFormCheckbox>
            <a
              style={{
                float: 'right',
              }}
            >
              忘记密码 ?
            </a>
          </div> */}
        </LoginForm>
      </div>
      <Footer />
    </div>
  );
};
export default Login;
