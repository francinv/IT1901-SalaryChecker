import React from "react";
import { Button, Grid, TextField } from "@mui/material";
import { Box } from "@mui/system";



const CreateUser = () => {

    const [values, setValues] = React.useState({
        firstname: '',
        lastname: '',
        employeeid: 0,
        email: '',
        social: '',
        password: '',
        hour:0,
        tax:0,
    })

    const handleSubmit = (event) => {
        event.preventDefault();
        // eslint-disable-next-line no-console
        console.log(values);
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
                        value={values.employeeid}
                        onChange={handleChange('employeeid')}
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
                        value={values.social}
                        onChange={handleChange('social')}
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
                        value={values.hour}
                        onChange={handleChange('hour')}
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
                        value={values.tax}
                        onChange={handleChange('tax')}
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