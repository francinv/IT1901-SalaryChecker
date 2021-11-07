import React from "react";
import { Box } from "@mui/system";
import PrimaryNavBar from "../components/navbar";
import CalculationPage from "./CalculationPage";
import UsersTable from "../components/users";

const AdminOverview = () => {
    const userType = "A";
    if (userType === "A") {
        return(
            <Box>
                <PrimaryNavBar userType={userType}/>
                <UsersTable />
            </Box>
        );
    }
}

export default AdminOverview;