import React from 'react';
import { Box } from '@mui/system';
import PrimaryNavBar from '../components/navbar';
import CalcForm from '../components/calcform';


const CalculationPage = () => {
    const userType = "U";
    if (userType === "U") {
        return(
            <Box>
                <PrimaryNavBar userType={userType}/>
                <CalcForm />
            </Box>
        );
    }
}
    
export default CalculationPage;