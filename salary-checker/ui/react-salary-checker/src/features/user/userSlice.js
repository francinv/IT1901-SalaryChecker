import { createSlice } from "@reduxjs/toolkit";

export const userSlice = createSlice ({
    name: 'user',
    initialState : {
        isLoggedIn: false,
        activeUser: {
            firstname: '',
            lastname: '',
            email: '',
            password: '',
            socialnumber: '',
            employeenumber: '',
            employeremail: '',
            taxcount: '',
            wage: '',
            userSale: [],
        },
    },
    reducers: {
        setUser: (state, action) => {
            state.isLoggedIn = true;
            state.activeUser = action.payload;
        },
        logOutUser: (state) => {
            state.isLoggedIn = true;
            state.activeUser = undefined;
        },
        addUserSale: (state, action) => {
            state.activeUser.userSale.push(action.payload);
        },
        getUserSales: (state) => {
            return state.activeUser.userSale;
        },
        editUser: (state, action) => {
            switch (action.type) {
                case "firstname":
                    state.activeUser.firstname = action.payload;
                case "lastname":
                    state.activeUser.lastname = action.payload;
                case "email":
                    state.activeUser.email = action.payload;
                case "password":
                    state.activeUser.password = action.payload;
                case "socialnumber":
                    state.activeUser.socialnumber = action.payload;
                case "employeenumber":
                    state.activeUser.employeenumber = action.payload;
                case "employeremail":
                    state.activeUser.employeremail = action.payload;
                case "taxcount":
                    state.activeUser.taxcount = action.payload;
                case "wage":
                    state.activeUser.wage = action.payload;
            }
        }
    }
})