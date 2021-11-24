import React, { useEffect } from 'react';
import { Box } from '@mui/system';
import PrimaryNavBar from '../components/navbar';
import ProfileOverviewComp from '../components/profile/profileOverview';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { selectUserIsLoggedIn, selectUserType } from '../features/selectors';

const ProfilePage = () => {
    const userType = useSelector(selectUserType);
    const isLoggedIn = useSelector(selectUserIsLoggedIn);
    let navigate = useNavigate();

    useEffect(() => {
        if (!isLoggedIn) {
            navigate('/');
        }
    })

    if (userType === 'U'){
        return(
            <Box>
                <PrimaryNavBar userType={userType}/>
                <ProfileOverviewComp />
            </Box>        
        );
    } else {
        return null;
    }
}

export default ProfilePage;