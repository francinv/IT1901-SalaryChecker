import React from "react";
import { styled} from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { Box } from "@mui/system";
import { TextField } from "@mui/material";
import { useSelector } from "react-redux";
import { selectAccounts } from "../../features/selectors";

const UserView = () => {

    let users = [];
    const [searchValue, setSearchValue] = React.useState('');

    const handleChange = (event) => {
        setSearchValue(event.target.value);
    }
    
    const accounts = useSelector(selectAccounts);

    const StyledTableCell = styled(TableCell)(({ theme }) => ({
        [`&.${tableCellClasses.head}`]: {
          backgroundColor: theme.palette.common.black,
          color: theme.palette.common.white,
        },
        [`&.${tableCellClasses.body}`]: {
          fontSize: 14,
        },
    }));
      
    const StyledTableRow = styled(TableRow)(({ theme }) => ({
        '&:nth-of-type(odd)': {
          backgroundColor: theme.palette.action.hover,
        },
        // hide last border
        '&:last-child td, &:last-child th': {
          border: 0,
        },
    }));

    function getUsers() {
        if (searchValue === ''){
            return (
                users = users.concat(accounts.filter(u => Object.keys(u).length > 5))
            )   
        } else {
            return (
                users = accounts.filter(u => 
                    (u.firstname.includes(searchValue) && Object.keys(u).length > 5)||
                    (u.lastname.includes(searchValue) && Object.keys(u).length > 5) ||
                    (u.email.includes(searchValue) && Object.keys(u).length > 5) 
                )
            );
        }
        
    }
    return ( 
        <Box sx={{
            width:"70%",
            margin: "0 auto",
            display:'flex',
            justifyContent:'center',
            flexDirection:'column',
        }}>
            <h1>Brukere</h1>
            <Box component="div" sx={{
                display:'flex',
                flexDirection:'row',
                margin:'1rem auto',
                width:'auto',
            }}>
                <TextField 
                    id="outlined-search" 
                    label="Search field" 
                    type="search" 
                    placeholder="Search on firstname, lastname or email"
                    value={searchValue}
                    onChange={handleChange}
                    sx={{
                        width:"400px",
                    }}
                />
            </Box>
            <TableContainer component={Paper} sx={{borderRadius:0}}>
                <Table sx={{ minWidth: 700, }} aria-label="customized table">
                    <TableHead>
                    <TableRow>
                        <StyledTableCell>Fornavn </StyledTableCell>
                        <StyledTableCell>Etternavn</StyledTableCell>
                        <StyledTableCell>E-post</StyledTableCell>
                        <StyledTableCell>Ansatt-ID</StyledTableCell>
                    </TableRow>
                    </TableHead>
                    <TableBody>
                    {getUsers().map((user) => (
                        <StyledTableRow key={user.employeeNumber}>
                        <StyledTableCell component="th" scope="row">
                            {user.firstname}
                        </StyledTableCell>
                        <StyledTableCell>{user.lastname}</StyledTableCell>
                        <StyledTableCell>{user.email}</StyledTableCell>
                        <StyledTableCell>{user.employeeNumber}</StyledTableCell>
                        </StyledTableRow>
                    ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>
    );
}

export default UserView;
