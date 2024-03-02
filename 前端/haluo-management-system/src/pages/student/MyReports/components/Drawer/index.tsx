import { Button, Col, Drawer, Form, Input, Row, Space } from 'antd';
import React, { useEffect } from 'react';
import { Checkbox } from 'antd';

const App = ({
  details,
  setDetails,
  open,
  onClose,
  disable,
  submitChanges,
}: {
  details: {};
  setDetails: any;
  open: boolean;
  onClose: () => void;
  disable: boolean;
  submitChanges: any;
}) => {
  // 对表单状态进行重置
  const [form] = Form.useForm();
  useEffect(() => {
    if (open) {
      // 每次打开抽屉时用 details 设置表单初始值
      form.setFieldsValue(details);
    }
  }, [open, details, form]);
  return (
    <>
      <Drawer
        title={disable ? '查看详情' : '修改详情'}
        width={720}
        onClose={onClose}
        open={open}
        bodyStyle={{ paddingBottom: 80 }}
        extra={
          <Space>
            <Button onClick={onClose}>取消</Button>
            {!disable && (
              // 提交修改
              <Button onClick={() => submitChanges()} type="primary">
                提交修改
              </Button>
            )}
          </Space>
        }
      >
        <Form
          form={form}
          layout="vertical"
          hideRequiredMark
          initialValues={details}
          onValuesChange={(changedValues, allValues) =>
            setDetails((s: any) => ({ ...s, ...allValues }))
          }
          disabled={disable}
        >
          <Row gutter={16}>
            <Col span={24}>
              <Form.Item
                name="thisWeekWork"
                label="本周完成内容"
                rules={[{ required: true, message: '请输入本周完成内容' }]}
              >
                <Input placeholder="请输入本周完成内容" />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={16}>
            <Col span={24}>
              <Form.Item
                name="thisWeekIdea"
                label="本周感想"
                rules={[{ required: true, message: '请输入本周感想' }]}
              >
                <Input.TextArea rows={4} placeholder="请输入本周感想" />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={16}>
            <Col span={24}>
              <Form.Item
                name="nextWeekWork"
                label="下周计划内容"
                rules={[{ required: true, message: '请输入下周计划内容' }]}
              >
                <Input.TextArea rows={4} placeholder="请输入下周计划内容" />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={16}>
            <Col span={24}>
              <Form.Item
                name="finished"
                label="是否完成了本周任务"
                valuePropName="checked" // 由于是 Checkbox，所以使用 valuePropName
              >
                <Checkbox>完成</Checkbox>
              </Form.Item>
            </Col>
          </Row>
          {/* 其他表单元素 */}
        </Form>
      </Drawer>
    </>
  );
};

export default App;
