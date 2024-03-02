import React, { useState, useEffect } from 'react';
import './index.less';
import {
  getStudentInfo,
  changeStudentInfo,
  getCollege,
  getMajor,
} from '@/services/ant-design-pro/myAPI';
import { message, Form, Input, Select, Button } from 'antd';
import { useModel } from 'umi';

const { Option } = Select;

const FormComponent = () => {
  const { initialState } = useModel('@@initialState');

  const [formData, setFormData] = useState({
    user_id: initialState!.User_id,
    phone: '',
    username: '',
    collegeName: '',
    majorName: '',
    grade: '',
    education: '',
  });

  const [form] = Form.useForm();

  // 获取学院信息
  const [colleges, setColleges] = useState<string[]>([]);
  const fetchColleges = () => {
    getCollege()
      .then((res) => {
        if (res.code == 1) {
          message.success('获取学院信息成功');
          setColleges(res.data);
        } else {
          message.error('获取学院信息失败');
        }
      })
      .catch((err) => {
        message.error('获取学院信息失败');
      });
  };

  // 获取专业信息
  const [majors, setMajors] = useState<string[]>([]);
  const fetchMajors = (college: string) => {
    getMajor(college)
      .then((res) => {
        // console.log(res);
        if (res.code == 1) {
          // message.success('获取专业信息成功');
          setMajors(res.data);
        } else {
          // message.error('获取专业信息失败');
        }
      })
      .catch((err) => {
        console.log(err);
        message.error('获取专业信息失败');
      });
  };

  // 获取个人信息
  const fetchInfo = () => {
    getStudentInfo(initialState!.User_id)
      .then((res) => {
        // console.log(res);
        if (res.code == 1) {
          message.success('获取个人信息成功');
          setFormData((s) => ({ ...s, ...res.data }));
          form.setFieldsValue(res.data);
          fetchMajors(res.data.collegeName);
        } else {
          message.error('获取个人信息失败');
        }
      })
      .catch((err) => {
        // console.log(err);
        message.error('获取个人信息失败');
      });
  };

  useEffect(() => {
    fetchInfo();
    fetchColleges();
  }, []);

  const isMounted = React.useRef(false);
  useEffect(() => {
    if (isMounted.current) {
      fetchMajors(formData.collegeName);
    } else {
      isMounted.current = true;
    }
  }, [formData.collegeName]);

  const handleSubmit = () => {
    changeStudentInfo(formData)
      .then((res) => {
        // console.log(res);
        if (res.code == 1) {
          message.success('修改成功');
          fetchInfo();
        } else {
          message.error('修改失败，请重试。');
        }
      })
      .catch((err) => {
        console.log(err);
        message.error('修改失败，请重试。');
      });
  };

  const onFormChange = (changedValues: any, allValues: any) => {
    setFormData((s) => ({ ...s, ...allValues }));
  };

  return (
    <div className="form-container">
      <Form
        layout="vertical"
        initialValues={formData}
        onValuesChange={onFormChange}
        onFinish={handleSubmit}
        form={form}
      >
        <Form.Item label="姓名" name="username">
          <Input />
        </Form.Item>
        <Form.Item label="手机号" name="phone">
          <Input disabled />
        </Form.Item>
        <Form.Item label="学院" name="collegeName">
          <Select placeholder="请选择学院">
            {colleges.map((college) => (
              <Select.Option key={college} value={college}>
                {college}
              </Select.Option>
            ))}
          </Select>
        </Form.Item>
        <Form.Item label="专业" name="majorName">
          <Select placeholder="请选择专业">
            {majors.map((major) => (
              <Select.Option key={major} value={major}>
                {major}
              </Select.Option>
            ))}
          </Select>
        </Form.Item>
        <Form.Item label="年级" name="grade">
          <Select placeholder="请选择年级">
            <Option value="2023">2023</Option>
            <Option value="2022">2022</Option>
            <Option value="2021">2021</Option>
            <Option value="2020">2020</Option>
          </Select>
        </Form.Item>
        <Form.Item label="学历" name="education">
          <Select placeholder="请选择学历">
            <Option value="本科">本科</Option>
            <Option value="硕士">硕士</Option>
            <Option value="博士生">博士生</Option>
          </Select>
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit">
            提交
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
};

export default FormComponent;
