import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    accountsList: [],
    isAccountsSet: false,
    activeUser:{},
    isActiveUserLoggedIn: false,
    activeUserType: '',
    userIndex: 0,
};

export const AccountsSlice = createSlice({
    name: 'accounts',
    initialState,
    reducers: {
        addUser: (state, action) => {
            state.accountsList.push(action.payload);
        },
        setAccounts: (state, action) => {
            state.isAccountsSet = true;
            state.accountsList = [];
            state.accountsList = state.accountsList.concat(action.payload);
        },
        setActiveUser: (state,action) => {
            state.activeUser = action.payload;
            state.userIndex = state.accountsList.findIndex(u => u.email === state.activeUser.email);
        },
        logIn: (state) => {
            state.isActiveUserLoggedIn = true;
        },
        addUserSale: (state, action) => {
            state.activeUser.userSales.push(action.payload);
        },
        editEmail: (state, action) => {
            state.activeUser.email = action.payload;
        },
        editPassword: (state, action) => {
            state.activeUser.password = action.payload;
        },
        editEmployeeNumber: (state,action) => {
            state.activeUser.employeeNumber = action.payload;
        },
        editEmployerEmail: (state,action) => {
            state.activeUser.employerEmail = action.payload;
        },
        editTaxCount: (state,action) => {
            state.activeUser.taxCount = action.payload;
        },
        editHourRate: (state,action) => {
            state.activeUser.hourRate = action.payload;
        },
        setUserType:(state, action) => {
            state.activeUserType = action.payload;
        },
        logOutUser: (state) => {
            state.isActiveUserLoggedIn = false;
            state.accountsList = undefined;
            state.activeUser = undefined;
            state.isAccountsSet = false;
            state.activeUserType = undefined;
        }

    },
});

export const {addUser, setAccounts, setActiveUser, addUserSale,
editEmail, editPassword, editEmployeeNumber, editEmployerEmail, editTaxCount,
editHourRate, setUserType, logOutUser, logIn} = AccountsSlice.actions;

export default AccountsSlice.reducer;