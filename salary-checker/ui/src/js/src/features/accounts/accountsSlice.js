import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    accountsList: [],
    isAccountsSet: false,
    activeUser:{},
    isActiveUserLoggedIn: false,
    activeUserType: '',
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
            state.accountsList = state.accountsList.concat(action.payload);
        },
        setActiveUser: (state,action) => {
            state.activeUser = state.accountsList.find(u => u.email === action.payload);
        },
        logIn: (state) => {
            state.isActiveUserLoggedIn = true;
        },
        addUserSale: (state, action) => {
            state.activeUser.userSales.push(action.payload);
        },
        editFirstname: (state, action) => {
            state.activeUser.firstname = action.payload;
        },
        editLastname: (state, action) => {
            state.activeUser.lastname = action.payload;
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
        }
    },
});

export const {addUser, setAccounts, setActiveUser, addUserSale, editFirstname,
editLastname, editEmail, editPassword, editEmployeeNumber, editEmployerEmail, editTaxCount,
editHourRate, setUserType, logOutUser, logIn} = AccountsSlice.actions;

export default AccountsSlice.reducer;