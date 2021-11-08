import { Box } from "@mui/system";
import React from "react";
import PageInfo from "../components/introinfo";
import PrimaryNavBar from "../components/navbar";



const HomePage = () => {
    const [userType, setUserType] = "U";
    return (
        <Box>
            <PrimaryNavBar userType={userType}/>
            <PageInfo userType={userType} />
        </Box>
        
    );
}

export default HomePage;