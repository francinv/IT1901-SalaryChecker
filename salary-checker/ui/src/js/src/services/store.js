import { configureStore } from '@reduxjs/toolkit';
import  AccountsReducer from '../features/accounts/accountsSlice';

export const store = configureStore({
  reducer: {
    accounts: AccountsReducer,
  },
});

export const AppDispatch = typeof store.dispatch
