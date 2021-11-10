import React, { useEffect } from "react";
import { Box } from "@mui/system";
import PrimaryNavBar from "../components/navbar";
import CreateUser from "../components/createform";
import { useSelector } from "react-redux";
import { selectUserIsLoggedIn, selectUserType } from "../features/selectors";
import { useNavigate } from "react-router-dom";

const AdminCreateUser = () => {
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
                <CreateUser />
            </Box>
        );
    } else {
        return null;
    }
    
}

export default AdminCreateUser;