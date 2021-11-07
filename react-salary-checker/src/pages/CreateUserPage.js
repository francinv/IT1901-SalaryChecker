import React from "react";
import { Box } from "@mui/system";
import PrimaryNavBar from "../components/navbar";
import UsersTable from "../components/users";
import CreateUser from "../components/createform";

const AdminCreateUser = () => {
    const userType = "A";
    if (userType === "A") {
        return(
            <Box>
                <PrimaryNavBar userType={userType}/>
                <CreateUser />
            </Box>
        );
    }
}

export default AdminCreateUser;