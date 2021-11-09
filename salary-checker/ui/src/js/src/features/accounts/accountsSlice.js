import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    accountsList: [],
    isAccountsSet: false,
    activeUser:{},
};

export const AccountsSlice = createSlice({
    name: 'accounts',
    initialState,
    reducers: {
        addUser: (state, action) => {
            state.accountsList.push(action.payload);
        },
        getUser: (state, action) => {
            state.activeUser = state.accountsList.find(u => u.email === action.payload);
        },
        setAccounts: (state, action) => {
            state.isAccountsSet = true;
            state.accountsList = state.accountsList.concat(action.payload);
        },
    },
});

export const {addUser, getUser, setAccounts} = AccountsSlice.actions;

export default AccountsSlice.reducer;