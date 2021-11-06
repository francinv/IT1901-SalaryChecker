import React from "react";
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { Box } from "@mui/system";

const SalaryTable = () => {

    const userSales = [
        {
            salesperiod: "Januar 2021",
            paid: "10000",
            expected: "15000",
            difference: "5000",
        },
        {
            salesperiod: "Februar 2021",
            paid: "10000",
            expected: "15000",
            difference: "5000",
        },
        {
            salesperiod: "Mars 2021",
            paid: "10000",
            expected: "15000",
            difference: "5000",
        },
        {
            salesperiod: "April 2021",
            paid: "10000",
            expected: "15000",
            difference: "5000",
        },
        {
            salesperiod: "Mai 2021",
            paid: "10000",
            expected: "15000",
            difference: "5000",
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
        }}>
            <h1>Mine lønninger</h1>
            <TableContainer component={Paper} sx={{borderRadius:0}}>
                <Table sx={{ minWidth: 700, }} aria-label="customized table">
                    <TableHead>
                    <TableRow>
                        <StyledTableCell>Salgsperiode </StyledTableCell>
                        <StyledTableCell align="right">Utbetalt&nbsp;(kr)</StyledTableCell>
                        <StyledTableCell align="right">Forventet&nbsp;(kr)</StyledTableCell>
                        <StyledTableCell align="right">Differanse&nbsp;(kr)</StyledTableCell>
                    </TableRow>
                    </TableHead>
                    <TableBody>
                    {userSales.map((userSale) => (
                        <StyledTableRow key={userSale.salesperiod}>
                        <StyledTableCell component="th" scope="row">
                            {userSale.salesperiod}
                        </StyledTableCell>
                        <StyledTableCell align="right">{userSale.paid}</StyledTableCell>
                        <StyledTableCell align="right">{userSale.expected}</StyledTableCell>
                        <StyledTableCell align="right">{userSale.difference}</StyledTableCell>
                        </StyledTableRow>
                    ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>
    );
}

export default SalaryTable;