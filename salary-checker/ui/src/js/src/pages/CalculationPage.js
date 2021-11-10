import React, { useEffect } from 'react';
import { Box } from '@mui/system';
import PrimaryNavBar from '../components/navbar';
import CalcForm from '../components/calcform';
import { useSelector } from 'react-redux';
import { selectUserIsLoggedIn, selectUserType } from '../features/selectors';
import { useNavigate } from 'react-router-dom';


const CalculationPage = () => {
    const userType = useSelector(selectUserType);
    const isLoggedIn = useSelector(selectUserIsLoggedIn);
    let navigate = useNavigate();

    useEffect(() => {
        if (!isLoggedIn) {
            navigate('/');
        }
    }, [isLoggedIn])
    
    if (userType === 'U') {
        return(
            <Box>
                <PrimaryNavBar userType={userType}/>
                <CalcForm />
            </Box>
        );
    } else {
        return null;
    }
}
    
export default CalculationPage;