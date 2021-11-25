import React from "react";
import { Button, Grid, TextField } from "@mui/material";
import { Box } from "@mui/system";
import { useSelector } from "react-redux";
import { selectActiveUser } from "../../features/selectors";
import { postCreatUser } from "../../core/APIfunctions";
import { addUser } from "../../features/accounts/accountsSlice";
import { useAppDispatch } from '../../features/hooks';


const actionDispatch = (dispatch) => ({
    addUser: (query) => dispatch(addUser(query)),
});

const CreateUser = () => {
    const activeUser = useSelector(selectActiveUser);
    const { addUser } = actionDispatch(useAppDispatch());

    const [values, setValues] = React.useState({
        firstname: '',
        lastname: '',
        email: '',
        password: '',
        socialNumber: '',
        employeeNumber: 0,
        employerEmail: activeUser.email,
        taxCount: 0.0,
        hourRate:0.0,
    })

    const handleSubmit = (event) => {
        event.preventDefault();
        // eslint-disable-next-line no-console
        addUser(values);
        postCreatUser(values);
    };

    const handleChange = (prop) => (event) => {
        setValues({ ...values, [prop]: event.target.value });
    };

    return(
        <Box
            component="div"
            sx={{
                border:'2px solid lightgray',
                borderRadius:'5px',
                width:'80vw',
                margin:'2rem auto',
                padding:'1rem',

            }}
        >
            <Grid component="form" onSubmit={handleSubmit} container spacing={2} autoComplete="on" sx={{
                width:'100%',
            }}>
                <Grid item md={12}>
                    <h2>Opprett bruker</h2>
                </Grid>
                <Grid item md={6}>
                    <TextField 
                        variant="outlined"
                        type="text"
                        label="Fornavn"
                        value={values.firstname}
                        onChange={handleChange('firstname')}
                        sx={{
                            flex:1,
                            width:'100%',
                        }}
                        required
                    />
                </Grid>
                <Grid item md={6}>
                    <TextField 
                        variant="outlined"
                        type="text"
                        label="Etternavn"
                        value={values.lastname}
                        onChange={handleChange('lastname')}
                        sx={{
                            flex:1,
                            width:'100%',
                        }}
                        required
                    />
                </Grid>
                <Grid item md={6}>
                    <TextField 
                        variant="outlined"
                        type="number"
                        label="Ansatt-ID"
                        value={values.employeeNumber}
                        onChange={handleChange('employeeNumber')}
                        sx={{
                            flex:1,
                            width:'100%',
                        }}
                        required
                        InputProps={{ inputProps: { min: 0, } }}
                    />
                </Grid>
                <Grid item md={6}>
                    <TextField 
                        variant="outlined"
                        type="email"
                        label="E-post"
                        value={values.email}
                        onChange={handleChange('email')}
                        sx={{
                            flex:1,
                            width:'100%',
                        }}
                        required
                    />
                </Grid>
                <Grid item md={6}>
                    <TextField 
                        variant="outlined"
                        type="text"
                        label="Fødselsnummer"
                        value={values.socialNumber}
                        onChange={handleChange('socialNumber')}
                        sx={{
                            flex:1,
                            width:'100%',
                        }}
                        required
                    />
                </Grid>
                <Grid item md={6}>
                    <TextField 
                        variant="outlined"
                        type="password"
                        label="Passord"
                        value={values.password}
                        onChange={handleChange('password')}
                        sx={{
                            flex:1,
                            width:'100%',
                        }}
                        required
                        
                    />
                </Grid>
                <Grid item md={6}> 
                    <TextField 
                        variant="outlined"
                        type="text"
                        label="Timesats"
                        value={values.hourRate}
                        onChange={handleChange('hourRate')}
                        sx={{
                            flex:1,
                            width:'100%',
                        }}
                        required
                    />
                </Grid>
                <Grid item md={6}>
                    <TextField 
                        variant="outlined"
                        type="text"
                        label="Skattesats"
                        value={values.taxCount}
                        onChange={handleChange('taxCount')}
                        sx={{
                            flex:1,
                            width:'100%',
                        }}
                        required
                    />
                </Grid>
                <Grid item md={12}>
                    <Button
                        type="submit"
                        variant="outlined"
                        sx={{
                            bgcolor:'black',
                            color:'white',
                            borderColor:'white',
                            '&:hover':{
                                bgcolor:'white',
                                color:'black',
                                borderColor:'black',
                            }
                        }}
                        
                    >
                        Opprett bruker
                    </Button>
                </Grid>
            </Grid>


        </Box>
    )
}

export default CreateUser;