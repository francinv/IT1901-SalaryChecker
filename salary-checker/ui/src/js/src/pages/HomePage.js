import { Box } from "@mui/system";
import React from "react";
import { useSelector } from "react-redux";
import PageInfo from "../components/introinfo";
import PrimaryNavBar from "../components/navbar";
import { selectUserType } from "../features/selectors";



const HomePage = () => {
    const userType = useSelector(selectUserType);
    return (
        <Box>
            <PrimaryNavBar userType={userType}/>
            <PageInfo userType={userType} />
        </Box>
        
    );
}

export default HomePage;