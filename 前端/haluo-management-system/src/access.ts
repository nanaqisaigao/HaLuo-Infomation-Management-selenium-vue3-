// src/access.ts
export default function (initialState: any) {
  return {
    Student: () => initialState.level === 'Student',
    Teacher: () => initialState.level === 'Teacher',
  };
}
