import React from "react";
import { Button, FormControl, InputAdornment, InputLabel, OutlinedInput, Stack, TextField } from "@mui/material";
import { Box } from "@mui/system";
import AdapterDateFns from '@mui/lab/AdapterDateFns';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import DatePicker from '@mui/lab/DatePicker';
import { calculateSalary, fetchAccountsFromServer, getUserSale, uploadFile } from "../../core/APIfunctions";
import { useSelector } from "react-redux";
import { selectActiveUser } from "../../features/selectors";
import { addUserSale, setAccounts } from "../../features/accounts/accountsSlice";
import { useAppDispatch } from "../../features/hooks";

const actionDispatch = (dispatch) => ({
    setAccounts: (query) => dispatch(setAccounts(query)),
    addUserSale: (query) => dispatch(addUserSale),
});

const CalcForm = () => {
    const [values, setValues] = React.useState({
        salesperiod: '',
        hours: 0.0,
        mobileamount: 0,
        paid: 0.0,
    });

    const [fileState, setFileState] = React.useState('');
    const [tempperiod, setTempPeriod] = React.useState(new Date());
    const [expected, setExpected] = React.useState(0);
    const [difference, setDifference] = React.useState(0);
    const activeUser = useSelector(selectActiveUser);

    const {setAccounts, addUserSale} = actionDispatch(useAppDispatch());

    const handleChange = (prop) => (event) => {
        setValues({ ...values, [prop]: event.target.value });
    };

    const handleFileChange = (event) => {
        event.preventDefault();
        setFileState(event.target.files[0]);
    }

    const months = ["Januar", "Februar", "Mars","April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Desember"];
    const handleSubmit = async (event) => {
        event.preventDefault();
        values.salesperiod = months[tempperiod.getMonth()] + " " + tempperiod.getFullYear();
        values.hours = parseFloat(values.hours);
        values.mobileamount = parseInt(values.mobileamount);
        values.paid = parseFloat(values.paid);
        // eslint-disable-next-line no-console
        var data = new FormData();
        data.append("file", fileState);
        uploadFile(data);
        calculateSalary(values, activeUser.email);
        let temp = await getUserSale(values.salesperiod, activeUser.email);
        setExpected(temp.expected);
        setDifference(temp.difference);
        let tempSale = {
            "salesperiod": values.salesperiod,
            "expected": expected,
            "paid": values.paid,
            "difference": difference
        }
        addUserSale(tempSale);
        let tempAccounts = await fetchAccountsFromServer();
        setAccounts(tempAccounts);
    };

    
    return(
        <Box
            component="form"
            autoComplete="on"
            sx={{
                display:'flex',
                justifyContent:'center',
                alignItems:'center',
                bgcolor:'lightgray',
                width:'50vw',
                margin:'0 auto',
                padding:'1rem',
            }}
            onSubmit={handleSubmit}
        
        >   
            <Stack spacing = {3}>

                <h2> Kalkuler din lønning </h2>
                <LocalizationProvider dateAdapter={AdapterDateFns}>
                    <DatePicker
                        views={['year', 'month']}
                        label="År og Måned"
                        minDate={new Date('2012-03-01')}
                        maxDate={new Date()}
                        value={tempperiod}
                        onChange={(tempperiod) => {setTempPeriod(tempperiod)}
                        }
                        renderInput={(params) => <TextField {...params} helperText={null} />}
                        required
                    />
                </LocalizationProvider>
                <div className="fileChooser-container">
                    <InputLabel htmlFor="fileChooser"> Last opp salgsrapport </InputLabel>
                    <TextField
                        id="fileChooser"
                        required
                        type="file"
                        onChange={handleFileChange}
                    />
                </div>
                <FormControl fullWidth sx={{ m: 1 }}>
                    <InputLabel htmlFor="outlined-adornment-amount">Utbetalt</InputLabel>
                    <OutlinedInput
                        id="outlined-adornment-amount"
                        value={values.paid}
                        onChange={handleChange('paid')}
                        startAdornment={<InputAdornment position="start">kr</InputAdornment>}
                        label="Utbetalt"
                        type="number"
                        min={0}
                        required
                    />
                </FormControl>
                <FormControl fullWidth sx={{ m: 1 }}>
                    <InputLabel htmlFor="outlined-adornment-mobile">Antall mobil</InputLabel>
                    <OutlinedInput
                        id="outlined-adornment-mobile"
                        value={values.mobileamount}
                        onChange={handleChange('mobileamount')}
                        label="Antall mobil"
                        type="number"
                        min={0}
                        required
                    />
                </FormControl>   
                <FormControl fullWidth sx={{ m: 1 }}>
                    <InputLabel htmlFor="outlined-adornment-mobile">Antall timer</InputLabel>
                    <OutlinedInput
                        id="outlined-adornment-mobile"
                        value={values.hours}
                        onChange={handleChange('hours')}
                        label="Antall timer"
                        type="number"
                        min={0}
                        required
                    />
                </FormControl>   
                <Button variant="outlined" type="submit" >Kalkuler</Button>
                <Box sx={{
                    display:"flex",
                    flexDirection:"column",
                    border: "1px solid black",
                    borderRadius:"5px",
                    transition:"box-shadow 0.2s",
                    '&:hover': {
                        boxShadow: "5px 5px 12px 19px rgba(131,131,131,0.35)",
                    },
                }}>
                    <h2>Utregning:</h2>
                    <p><b>Utbetalt:</b> {values.paid}</p>
                    <p><b>Forventet:</b> {expected}</p>
                    <p><b>Differanse:</b> {difference}</p>
                </Box>
            </Stack>
        </Box>
    );
}

export default CalcForm;