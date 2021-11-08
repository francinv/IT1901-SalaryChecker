import { Box } from "@mui/system";
import React from "react";

const PageInfo = ({userType}) => {
    const name = "Francin Vincent";

    if (userType === "U") {
        return(
            <Box>
                <h1>Hello, {name}</h1>
                <p>This app can be used to calculate your salary, and see your calculated salaries.</p>
            </Box>
        )
    }
    return (
        <Box>
            <h1>Hello, {name}</h1>
            <p>You can create users and get an overview of existing users.</p>
        </Box>
    )
    
}

export default PageInfo;