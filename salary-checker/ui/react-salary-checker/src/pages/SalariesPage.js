import { Box } from '@mui/system';
import React from 'react';
import PrimaryNavBar from '../components/navbar';
import SalaryTable from '../components/salaries';

const SalariesPage = () => {
    const userType = "U";

    if (userType === "U"){
        return(
            <Box>
                <PrimaryNavBar userType={userType}/>
                <SalaryTable />
            </Box>        
        );
    }
    return null;
}

export default SalariesPage;