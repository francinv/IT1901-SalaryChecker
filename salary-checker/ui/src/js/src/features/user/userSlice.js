import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    isLoggedIn: false,
    activeUser: {
        firstname: '',
        lastname: '',
        email: '',
        password: '',
        socialNumber: '',
        employeeNumber: '',
        employerEmail: '',
        taxCount: '',
        hourRate: '',
        userSales: [],
    },
}
export const userSlice = createSlice ({
    name: 'user',
    initialState,
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
            state.activeUser.userSales.push(action.payload);
        },
        getUserSales: (state) => {
            return state.activeUser.userSales;
        },
        editUser: (state, action) => {
            switch (action.type) {
                case "firstname":
                    state.activeUser.firstname = action.payload;
                    break;
                case "lastname":
                    state.activeUser.lastname = action.payload;
                    break;
                case "email":
                    state.activeUser.email = action.payload;
                    break;
                case "password":
                    state.activeUser.password = action.payload;
                    break;
                case "socialnumber":
                    state.activeUser.socialNumber = action.payload;
                    break
                case "employeenumber":
                    state.activeUser.employeeNumber = action.payload;
                    break;
                case "employeremail":
                    state.activeUser.employerEmail = action.payload;
                    break;
                case "taxcount":
                    state.activeUser.taxCount = action.payload;
                    break;
                case "wage":
                    state.activeUser.hourRate = action.payload;
                    break;
                default:
                    return state.activeUser;
            }
        }
    }
})

export const {setUser, logOutUser, addUserSale, getUserSales, editUser} = userSlice.actions;

export default userSlice.reducer;