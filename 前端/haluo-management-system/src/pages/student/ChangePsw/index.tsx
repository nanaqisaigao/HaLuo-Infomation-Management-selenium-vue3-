import React, { useState } from 'react';
import './index.less';
import { changePsw } from '@/services/ant-design-pro/myAPI';
import { history, useModel } from 'umi';
import { Form, Input, Button, message } from 'antd';

const ChangePasswordForm = () => {
  const { initialState } = useModel('@@initialState');
  const [form] = Form.useForm();
  const [data, setData] = useState({
    userId: initialState!.User_id,
    password: '',
    historicalPassword: '',
    confirmPassword: '',
  });

  const handleChange = () => {
    if (data.password !== data.confirmPassword) {
      message.error('两次输入的密码不一致，请重新输入！');
      return;
    } else {
      const passwordData = {
        userId: data.userId,
        password: data.password,
        historicalPassword: data.historicalPassword,
      };

      changePsw({ ...passwordData, userId: initialState!.User_id })
        .then((res) => {
          console.log(res);

          if (res.code == 1) {
            message.success('修改成功！');
            message.success('请重新登录！');
            // Optionally reset the form here
            setData({
              ...data,
              password: '',
              historicalPassword: '',
              confirmPassword: '',
            });
            form.resetFields();
            localStorage.removeItem('token');
            history.push('/user/login');
          } else {
            message.error(res.msg || '修改失败！');
          }
        })
        .catch((err) => {
          console.error(err);
          message.error('修改失败！');
        });
    }
  };

  const onFormChange = (changedValues: any, allValues: any) => {
    setData(allValues);
  };

  return (
    <div className="form-container">
      <Form
        form={form}
        layout="vertical"
        initialValues={data}
        onValuesChange={onFormChange}
        onFinish={handleChange}
      >
        <Form.Item
          label="原密码"
          name="historicalPassword"
          rules={[{ required: true, message: '请输入原密码!' }]}
        >
          <Input.Password />
        </Form.Item>
        <Form.Item
          label="新密码"
          name="password"
          rules={[
            { required: true, message: '请输入新密码!' },
            {
              pattern:
                /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[%_@])[a-zA-Z0-9%_@]{8,}$|(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$|(?=.*[a-zA-Z])(?=.*[%_@])[a-zA-Z%_@]{8,}$|(?=.*[0-9])(?=.*[%_@])[0-9%_@]{8,}$/,
              message:
                '密码只能由英文、数字、特殊字符%_@组成，且至少包含其中两种类型的字符，长度大于等于8',
            },
          ]}
        >
          <Input.Password />
        </Form.Item>
        <Form.Item
          label="确认新密码"
          name="confirmPassword"
          dependencies={['password']}
          rules={[
            { required: true, message: '请确认新密码!' },
            ({ getFieldValue }) => ({
              validator(_, value) {
                if (!value || getFieldValue('password') === value) {
                  return Promise.resolve();
                }
                return Promise.reject(new Error('两次输入的密码不一致!'));
              },
            }),
          ]}
        >
          <Input.Password />
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit">
            修改密码
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
};

export default ChangePasswordForm;
