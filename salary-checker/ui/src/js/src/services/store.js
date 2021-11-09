import { configureStore } from '@reduxjs/toolkit';
import  AccountsReducer from '../features/accounts/accountsSlice';
import userReducer from '../features/user/userSlice';

export const store = configureStore({
  reducer: {
    accounts: AccountsReducer,
    user: userReducer
  },
});

export const AppDispatch = typeof store.dispatch
