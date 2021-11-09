import { useSelector } from 'react-redux';

export const selectAccounts = (state) => state.accounts.accountsList;
export const selectIsAccountsSet = (state) => state.accounts.accountsList;
export const selectActiveUser = (state) => state.accounts.activeUser;
export const selectUser = (state) => state.user.activeUser;
export const selectUserIsLoggedIn = (state) => state.user.isLoggedIn;