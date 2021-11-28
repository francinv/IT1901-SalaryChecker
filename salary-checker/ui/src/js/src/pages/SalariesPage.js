import { Box } from '@mui/system';
import React, { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import PrimaryNavBar from '../components/navbar';
import SalaryTable from '../components/salaries';
import { selectUserIsLoggedIn, selectUserType } from '../features/selectors';

const SalariesPage = () => {
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
                <SalaryTable />
            </Box>        
        );
    }
    else {
        return null;
    }
}

export default SalariesPage;