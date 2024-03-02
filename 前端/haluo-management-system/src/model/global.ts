// src/models/loading.js
export default {
  namespace: 'loading',
  state: {
    global: false,
  },
  reducers: {
    show(state: any) {
      return {
        ...state,
        global: true,
      };
    },
    hide(state: any) {
      return {
        ...state,
        global: false,
      };
    },
  },
};
