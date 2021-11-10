import { Box } from "@mui/system";
import React from "react";
import { useSelector } from "react-redux";
import { selectActiveUser, selectUser } from "../../features/selectors";

const PageInfo = ({userType}) => {
    const activeUser = useSelector(selectActiveUser);
    const name = activeUser.firstname +" " + activeUser.lastname;

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