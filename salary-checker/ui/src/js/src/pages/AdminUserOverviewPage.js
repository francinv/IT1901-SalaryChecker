import React, { useEffect } from "react";
import { Box } from "@mui/system";
import PrimaryNavBar from "../components/navbar";
import UsersTable from "../components/users";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { selectUserIsLoggedIn, selectUserType } from "../features/selectors";

const AdminOverview = () => {
    const userType = useSelector(selectUserType);
    const isLoggedIn = useSelector(selectUserIsLoggedIn);
    let navigate = useNavigate();

    useEffect(() => {
        if (!isLoggedIn) {
            navigate('/');
        }
    }, [isLoggedIn])

    if (userType === 'A') {
        return(
            <Box>
                <PrimaryNavBar userType={userType}/>
                <UsersTable />
            </Box>
        );
    } else {
        return null;
    }
}

export default AdminOverview;