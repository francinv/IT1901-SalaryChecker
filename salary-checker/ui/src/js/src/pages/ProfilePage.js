import React from 'react';
import { Box } from '@mui/system';
import PrimaryNavBar from '../components/navbar';
import ProfileOverviewComp from '../components/profile/profileOverview';

const ProfilePage = () => {
    const userType = "U";

    if (userType === "U"){
        return(
            <Box>
                <PrimaryNavBar userType={userType}/>
                <ProfileOverviewComp />
            </Box>        
        );
    }
    return null;
}

export default ProfilePage;