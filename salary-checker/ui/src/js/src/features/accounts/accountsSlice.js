import { createSlice } from "@reduxjs/toolkit";

export const AccountsSlice = createSlice({
    name: 'accounts',
    initialState: {
        accountsList: [{}],
    },
    reducers: {
        addUser: (state, action) => {
            state.accountsList.push(action.payload);
        },
        getUser: (state, action) => {
            return state.accountsList.find(u => u.email === action.payload);
        },
        setAccounts: (state, action) => {
            state.accountsList = action.payload;
        },
    },
});

export const {addUser, getUser, setAccounts} = AccountsSlice.actions;

export const selectAccounts = (state) => state.accounts.accountsList;

export default AccountsSlice.reducer;