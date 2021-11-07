import React, { useEffect } from "react";
import { styled, alpha} from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { Box } from "@mui/system";
import { Button, IconButton, InputBase, TextField } from "@mui/material";
import SearchIcon from '@mui/icons-material/Search';

const UsersTable = () => {

    const [searchValue, setSearchValue] = React.useState('');

    const handleChange = (event) => {
        setSearchValue(event.target.value);
    }


    function handleSearch() {
        console.log(users); 
    }

    let users = [
        {
            firstname: "Francin",
            lastname: "Vincent",
            email: "francin@mail.com",
            employerid: "12345",
        },
        {
            firstname: "Seran",
            lastname: "Shanmugathas",
            email: "seran@mail.com",
            employerid: "54321",
        },
        {
            firstname: "Jakob",
            lastname: "Kessler",
            email: "jakob@mail.com",
            employerid: "23456",
        },
        {
            firstname: "Hammad",
            lastname: "Siddiqui",
            email: "hammad@mail.com",
            employerid: "18903",
        },
        {
            firstname: "Test",
            lastname: "User",
            email: "test@mail.com",
            employerid: "98765",
        },
    ]

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

    return (
        <Box sx={{
            width:"70%",
            margin: "0 auto",
            display:'flex',
            justifyContent:'center',
            flexDirection:'column',
        }}>
            <h1>Brukere</h1>
            <Box component="form" onSubmit={handleSearch} sx={{
                display:'flex',
                flexDirection:'row',
                margin:'1rem auto',
                width:'auto',
            }}>
                <TextField 
                    id="outlined-search" 
                    label="Search field" 
                    type="search" 
                    value={searchValue}
                    onChange={handleChange}
                    sx={{
                        marginRight:'0.5rem',
                    }}
                />
                <IconButton type="submit">
                    <SearchIcon />
                </IconButton>
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
                    {users.map((user) => (
                        <StyledTableRow key={user.firstname}>
                        <StyledTableCell component="th" scope="row">
                            {user.firstname}
                        </StyledTableCell>
                        <StyledTableCell>{user.lastname}</StyledTableCell>
                        <StyledTableCell>{user.email}</StyledTableCell>
                        <StyledTableCell>{user.employerid}</StyledTableCell>
                        </StyledTableRow>
                    ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>
    );
}

export default UsersTable;